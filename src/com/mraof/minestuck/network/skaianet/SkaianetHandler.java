package com.mraof.minestuck.network.skaianet;

import com.mraof.minestuck.Minestuck;
import com.mraof.minestuck.MinestuckConfig;
import com.mraof.minestuck.advancements.MinestuckCriteriaTriggers;
import com.mraof.minestuck.editmode.EditData;
import com.mraof.minestuck.editmode.ServerEditHandler;
import com.mraof.minestuck.event.ConnectionClosedEvent;
import com.mraof.minestuck.event.ConnectionCreatedEvent;
import com.mraof.minestuck.network.MinestuckPacketHandler;
import com.mraof.minestuck.network.ServerEditPacket;
import com.mraof.minestuck.network.SkaianetInfoPacket;
import com.mraof.minestuck.tileentity.TileEntityComputer;
import com.mraof.minestuck.util.Debug;
import com.mraof.minestuck.util.IdentifierHandler;
import com.mraof.minestuck.util.IdentifierHandler.PlayerIdentifier;
import com.mraof.minestuck.util.Location;
import com.mraof.minestuck.util.Teleport;
import com.mraof.minestuck.world.MinestuckDimensionHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListOpsEntry;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraft.world.storage.WorldSavedDataStorage;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;

import java.util.*;
import java.util.Map.Entry;

/**
 * This class handles server sided stuff about the sburb connection network.
 * This class also handles the main saving and loading.
 * @author kirderf1
 */
public class SkaianetHandler extends WorldSavedData
{
	private static final String DATA_NAME = Minestuck.MOD_ID+"_skaianet";
	
	Map<PlayerIdentifier, ComputerData> serversOpen = new TreeMap<>();
	private Map<PlayerIdentifier, ComputerData> resumingClients = new HashMap<>();
	private Map<PlayerIdentifier, ComputerData> resumingServers = new HashMap<>();
	List<SburbConnection> connections = new ArrayList<>();
	private Map<PlayerIdentifier, PlayerIdentifier[]> infoToSend = new HashMap<>();	//Key: player, value: data to send to player
	private List<ComputerData> movingComputers = new ArrayList<>();
	
	/**
	 * Chains of lands to be used by the skybox render
	 */
	private List<List<Integer>> landChains = new LinkedList<>();
	
	private final MinecraftServer mcServer;
	
	private SkaianetHandler(MinecraftServer mcServer)
	{
		super(DATA_NAME);
		this.mcServer = mcServer;
	}
	
	private SkaianetHandler(String name, MinecraftServer mcServer)
	{
		super(name);
		this.mcServer = mcServer;
	}
	
	public SburbConnection getClientConnection(PlayerIdentifier client)
	{
		for(SburbConnection c : connections)
			if(c.isActive && c.client.owner.equals(client))
				return c;
		return null;
	}
	
	public PlayerIdentifier getAssociatedPartner(PlayerIdentifier player, boolean isClient)
	{
		for(SburbConnection c : connections)
			if(c.isMain)
				if(isClient && c.getClientIdentifier().equals(player))
					return c.getServerIdentifier().equals(IdentifierHandler.nullIdentifier) ? null : c.getServerIdentifier();
				else if(!isClient && c.getServerIdentifier().equals(player))
					return c.getClientIdentifier();
		return null;
	}
	
	public SburbConnection getMainConnection(PlayerIdentifier player, boolean isClient)
	{
		if(player.equals(IdentifierHandler.nullIdentifier))
			return null;
		for(SburbConnection c : connections)
			if(c.isMain)
				if(isClient ? (c.getClientIdentifier().equals(player))
						: c.getServerIdentifier().equals(player))
					return c;
		return null;
	}
	
	public  boolean giveItems(PlayerIdentifier player)
	{
		SburbConnection c = getClientConnection(player);
		if(c != null && !c.isMain && getAssociatedPartner(c.getClientIdentifier(), true) == null
				&& getAssociatedPartner(c.getServerIdentifier(), false) == null) {
			c.isMain = true;
			SburbHandler.onFirstItemGiven(c);
			updatePlayer(c.getClientIdentifier());
			updatePlayer(c.getServerIdentifier());
			return true;
		}
		return false;
	}
	
	/**
	 * Note that this is when a player logs in to the server.
	 * @param player
	 */
	public void playerConnected(EntityPlayerMP player)
	{
		PlayerIdentifier identifier = IdentifierHandler.encode(player);
		PlayerIdentifier[] s = new PlayerIdentifier[5];
		s[0] = identifier;
		infoToSend.put(identifier, s);
		updatePlayer(identifier);
		SkaianetInfoPacket packet = createLandChainPacket();
		MinestuckPacketHandler.sendToPlayer(packet, player);
	}
	
	public void requestConnection(ComputerData player, PlayerIdentifier otherPlayer, boolean isClient)
	{
		if(player.getDimension() == DimensionType.NETHER)
			return;
		TileEntityComputer te = getComputer(mcServer, player.getLocation());
		if(te == null)
			return;
		if(!isClient)	//Is server
		{
			if(serversOpen.containsKey(player.owner) || resumingServers.containsKey(player.owner))
				return;
			if(otherPlayer == null)	//Wants to open
			{
				if(resumingClients.containsKey(getAssociatedPartner(player.owner, false)))
					connectTo(player, false, getAssociatedPartner(player.owner, false), resumingClients);
				else
				{
					te.getData(1).setBoolean("isOpen", true);
					serversOpen.put(player.owner, player);
				}
			}
			else if(otherPlayer != null && getAssociatedPartner(player.owner, false).equals(otherPlayer))	//Wants to resume
			{
				if(resumingClients.containsKey(otherPlayer))	//The client is already waiting
					connectTo(player, false, otherPlayer, resumingClients);
				else	//Client is not currently trying to resume
				{
					te.getData(1).setBoolean("isOpen", true);
					resumingServers.put(player.owner, player);
				}
			}
			else return;
		} else	//Is client
		{
			if(getClientConnection(player.owner) != null || resumingClients.containsKey(player.owner))
				return;
			PlayerIdentifier p = getAssociatedPartner(player.owner, true);
			if(p != null && (otherPlayer == null || p.equals(otherPlayer)))	//If trying to connect to the associated partner
			{
				if(resumingServers.containsKey(p))	//If server is "resuming".
					connectTo(player, true, p, resumingServers);
				else if(serversOpen.containsKey(p))	//If server is normally open.
					connectTo(player, true, p, serversOpen);
				else	//If server isn't open
				{
					te.getData(0).setBoolean("isResuming", true);
					resumingClients.put(player.owner, player);
				}
			}
			else if(serversOpen.containsKey(otherPlayer))	//If the server is open.
				connectTo(player, true, otherPlayer, serversOpen);
		}
		te.markBlockForUpdate();
		updateAll();
	}
	
	public void closeConnection(PlayerIdentifier player, PlayerIdentifier otherPlayer, boolean isClient)
	{
		if(otherPlayer == null)
		{
			if(isClient)
			{
				if(movingComputers.contains(resumingClients.get(player)))
					return;
				TileEntityComputer te = getComputer(mcServer, resumingClients.remove(player).location);
				if(te != null)
				{
					te.getData(0).setBoolean("isResuming", false);
					te.latestmessage.put(0, "computer.messageResumeStop");
					te.markBlockForUpdate();
				}
			} else if(serversOpen.containsKey(player))
			{
				if(movingComputers.contains(serversOpen.get(player)))
					return;
				TileEntityComputer te = getComputer(mcServer, serversOpen.remove(player).getLocation());
				if(te != null)
				{
					te.getData(1).setBoolean("isOpen", false);
					te.latestmessage.put(1, "computer.messageClosedServer");
					te.markBlockForUpdate();
				}
			} else if(resumingServers.containsKey(player))
			{
				if(movingComputers.contains(resumingServers.get(player)))
					return;
				TileEntityComputer te = getComputer(mcServer, resumingServers.remove(player).getLocation());
				if(te != null)
				{
					te.getData(1).setBoolean("isOpen", false);
					te.latestmessage.put(1, "computer.messageResumeStop");
					te.markBlockForUpdate();
				}
			} else Debug.warn("[SKAIANET] Got disconnect request but server is not open! "+player);
		} else {
			SburbConnection c = isClient?getConnection(player, otherPlayer):getConnection(otherPlayer, player);
			if(c != null)
			{
				if(c.isActive)
				{
					if(movingComputers.contains(isClient ? c.client : c.server))
						return;
					TileEntityComputer cc = getComputer(mcServer, c.client.getLocation()), sc = getComputer(mcServer, c.server.getLocation());
					if(cc != null)
					{
						cc.getData(0).setBoolean("connectedToServer", false);
						cc.latestmessage.put(0, "computer.messageClosed");
						cc.markBlockForUpdate();
					}
					if(sc != null)
					{
						sc.getData(1).setString("connectedClient", "");
						sc.latestmessage.put(1, "computer.messageClosed");
						sc.markBlockForUpdate();
					}
					SessionHandler.onConnectionClosed(mcServer, c, true);
					ServerEditHandler.onDisconnect(c);
					if(c.isMain)
						c.isActive = false;	//That's everything that is neccesary.
					else connections.remove(c);
					
					ConnectionCreatedEvent.ConnectionType type = !c.isMain && getMainConnection(c.getClientIdentifier(), true) != null
							? ConnectionCreatedEvent.ConnectionType.SECONDARY : ConnectionCreatedEvent.ConnectionType.REGULAR;
					MinecraftForge.EVENT_BUS.post(new ConnectionClosedEvent(c, SessionHandler.getPlayerSession(c.getClientIdentifier()), type));
				} else if(getAssociatedPartner(player, isClient).equals(otherPlayer))
				{
					if(movingComputers.contains(isClient?resumingClients.get(player):resumingServers.get(player)))
						return;
					TileEntityComputer te = getComputer(mcServer, (isClient?resumingClients.remove(player):resumingServers.remove(player)).getLocation());
					if(te != null)
					{
						te.latestmessage.put(isClient?0:1, "computer.messageResumeStop");
						te.markBlockForUpdate();
					}
				}
			}
		}
		updateAll();
	}
	
	private void connectTo(ComputerData player, boolean isClient, PlayerIdentifier otherPlayer, Map<PlayerIdentifier, ComputerData> map)
	{
		TileEntityComputer c1 = getComputer(mcServer, player.location), c2 = getComputer(mcServer, map.get(otherPlayer).location);
		if(c2 == null)
		{
			map.remove(otherPlayer);	//Invalid, should not be in the list
			return;
		}
		if(c1 == null)
			return;
		SburbConnection c;
		boolean newConnection = false;	//True if new, false if resuming.
		if(isClient)
		{
			c = getConnection(player.owner, otherPlayer);
			if(c == null)
			{
				c = new SburbConnection();
				connections.add(c);
				newConnection = true;
			}
			c.client = player;
			c.server = map.remove(otherPlayer);
			c.isActive = true;
		} else
		{
			c = getConnection(otherPlayer, player.owner);
			if(c == null)
				return;	//A server should only be able to resume
			c.client = map.remove(otherPlayer);
			c.server = player;
			c.isActive = true;
		}
		
		//Get session type for event
		Session s1 = SessionHandler.getPlayerSession(c.getClientIdentifier()), s2 = SessionHandler.getPlayerSession(c.getServerIdentifier());
		ConnectionCreatedEvent.SessionJoinType joinType = s1 == null || s2 == null ? ConnectionCreatedEvent.SessionJoinType.JOIN
				: s1 == s2 ? ConnectionCreatedEvent.SessionJoinType.INTERNAL : ConnectionCreatedEvent.SessionJoinType.MERGE;
		ConnectionCreatedEvent.ConnectionType type = ConnectionCreatedEvent.ConnectionType.REGULAR;
		
		boolean updateLandChain = false;
		if(newConnection)
		{
			SburbConnection conn = getMainConnection(c.getClientIdentifier(), true);
			if(conn != null && conn.getServerIdentifier().equals(IdentifierHandler.nullIdentifier) && getMainConnection(c.getServerIdentifier(), false) == null)
			{
				connections.remove(c);
				conn.client = c.client;
				conn.server = c.server;
				conn.serverIdentifier = c.getServerIdentifier();
				conn.isActive = true;
				c = conn;
				type = ConnectionCreatedEvent.ConnectionType.RESUME;
				updateLandChain = true;
			} else
			{
				String s = SessionHandler.onConnectionCreated(mcServer, c);
				if(s != null)
				{
					Debug.warnf("SessionHandler denied connection between %s and %s, reason: %s", c.getClientIdentifier().getUsername(), c.getServerIdentifier().getUsername(), s);
					connections.remove(c);
					TileEntityComputer cte = getComputer(mcServer, c.client.location);
					if(cte != null)
						cte.latestmessage.put(0, s);
					map.put(c.server.owner, c.server);
					return;
				
				}
				SburbHandler.onConnectionCreated(c);
				
				if(conn != null)
				{
					c.enteredGame = conn.enteredGame;
					c.canSplit = conn.canSplit;
					c.centerX = conn.centerX;
					c.centerZ = conn.centerZ;
					c.clientHomeLand = conn.clientHomeLand;
					c.artifactType = conn.artifactType;
					if(c.inventory != null)
						c.inventory = conn.inventory.copy();
					type = ConnectionCreatedEvent.ConnectionType.SECONDARY;
				}
			}
		} else type = ConnectionCreatedEvent.ConnectionType.RESUME;
		
		c1.connected(otherPlayer, isClient);
		c2.connected(player.owner, !isClient);
		if(c1 != c2)
			c2.markBlockForUpdate();
		
		MinecraftForge.EVENT_BUS.post(new ConnectionCreatedEvent(c, SessionHandler.getPlayerSession(c.getClientIdentifier()), type, joinType));
		if(updateLandChain)
			sendLandChainUpdate();
	}
	
	public void requestInfo(EntityPlayerMP player, PlayerIdentifier p1)
	{
		checkData();
		PlayerIdentifier p0 = IdentifierHandler.encode(player);
		PlayerIdentifier[] s = infoToSend.get(p0);
		if(s == null)
		{
			Debug.error("[SKAIANET] Something went wrong with player \"" + player.getName() + "\"'s skaianet data!");
			return;
		}
		UserListOpsEntry opsEntry = player.getServer().getPlayerList().getOppedPlayers().getEntry(player.getGameProfile());
		if(MinestuckConfig.privateComputers && !p0.equals(p1) && !(opsEntry != null && opsEntry.getPermissionLevel() >= 2))
		{
			player.sendMessage(new TextComponentString("[Minestuck] ").setStyle(new Style().setColor(TextFormatting.RED)).appendSibling(new TextComponentTranslation("message.privateComputerMessage")));
			return;
		}
		int i = 0;
		for(;i < s.length; i++)
		{
			if(s[i] == null)
				break;
			if(s[i].equals(p1))
			{
				Debug.warnf("[Skaianet] Player %s already got the requested data.", player.getName());
				updatePlayer(p0);	//Update anyway, to fix whatever went wrong
				return;
			}
		}
		if(i == s.length)	//If the array is full, increase size with 5.
		{
			PlayerIdentifier[] newS = new PlayerIdentifier[s.length+5];
			System.arraycopy(s, 0, newS, 0, s.length);
			s = newS;
			infoToSend.put(p0, s);
		}
		
		s[i] = p1;
		
		updatePlayer(p0);
	}
	
	@Override
	public void read(NBTTagCompound nbt)
	{
		connections.clear();
		serversOpen.clear();
		resumingClients.clear();
		resumingServers.clear();
		SessionHandler.sessions.clear();
		SessionHandler.sessionsByName.clear();
		SburbHandler.titleSelectionMap.clear();
		NBTTagList list = nbt.getList("sessions", 10);
		for(int i = 0; i < list.size(); i++)
		{
			Session session = new Session().read(list.getCompound(i));
			SessionHandler.sessions.add(session);
			connections.addAll(session.connections);
			
			if(session.isCustom())
			{
				if(SessionHandler.sessionsByName.containsKey(session.name))
					Debug.warnf("A session with a duplicate name has been loaded! (Session '%s') Either a bug or someone messing with the data file.", session.name);
				SessionHandler.sessionsByName.put(session.name, session);
			}
		}
		
		String[] s = {"serversOpen", "resumingClients", "resumingServers"};
		Map<PlayerIdentifier, ComputerData>[] maps = new Map[]{serversOpen, resumingClients, resumingServers};
		for(int e = 0; e < 3; e++)
		{
			list = nbt.getList(s[e], 10);
			for(int i = 0; i < list.size(); i++)
			{
				NBTTagCompound cmp = list.getCompound(i);
				ComputerData c = new ComputerData();
				c.read(cmp);
				maps[e].put(c.owner, c);
			}
		}
		
		SessionHandler.serverStarted();
		
		updateLandChain();
	}
	
	@Override
	public NBTTagCompound write(NBTTagCompound compound)
	{
		//checkData();
		NBTTagList list = new NBTTagList();
		
		for(Session s : SessionHandler.sessions)
			list.add(s.write());
		
		compound.setTag("sessions", list);
		
		String[] s = {"serversOpen","resumingClients","resumingServers"};
		@SuppressWarnings("unchecked")
		Map<PlayerIdentifier, ComputerData>[] maps = new Map[]{serversOpen, resumingClients, resumingServers};
		for(int i = 0; i < 3; i++)
		{
			list = new NBTTagList();
			for(ComputerData c:maps[i].values())
				list.add(c.write());
			compound.setTag(s[i], list);
		}
		
		return compound;
	}
	
	public SkaianetInfoPacket createLandChainPacket()
	{
		return SkaianetInfoPacket.landChains(landChains);
	}
	
	void updateLandChain()
	{
		landChains.clear();
		
		Set<Integer> checked = new HashSet<>();
		for(SburbConnection c : connections)
		{
			if(c.isMain() && c.enteredGame() && !checked.contains(c.clientHomeLand.getId()))
			{
				LinkedList<Integer> chain = new LinkedList<>();
				chain.add(c.clientHomeLand.getId());
				checked.add(c.clientHomeLand.getId());
				SburbConnection cIter = c;
				while(true)
				{
					cIter = getMainConnection(cIter.getClientIdentifier(), false);
					if(cIter != null && cIter.enteredGame())
					{
						if(!checked.contains(cIter.clientHomeLand.getId()))
						{
							chain.addLast(cIter.clientHomeLand.getId());
							checked.add(cIter.clientHomeLand.getId());
						} else break;
					} else
					{
						chain.addLast(0);
						break;
					}
				}
				cIter = c;
				while(true)
				{
					cIter = getMainConnection(cIter.getServerIdentifier(), true);
					if(cIter != null && cIter.enteredGame() && !checked.contains(cIter.clientHomeLand.getId()))
					{
						chain.addFirst(cIter.clientHomeLand.getId());
						checked.add(cIter.clientHomeLand.getId());
					} else
					{
						break;
					}
				}
				landChains.add(chain);
			}
		}
	}
	
	void sendLandChainUpdate()
	{
		updateLandChain();
		SkaianetInfoPacket packet = createLandChainPacket();
		MinestuckPacketHandler.sendToAll(packet);
	}
	
	void updateAll()
	{
		checkData();
		for(PlayerIdentifier i : infoToSend.keySet())
			updatePlayer(i);
	}
	
	private void updatePlayer(PlayerIdentifier player)
	{
		PlayerIdentifier[] iden = infoToSend.get(player);
		EntityPlayerMP playerMP = player.getPlayer(mcServer);
		if(iden == null || playerMP == null)//If the player disconnected
			return;
		for(SburbConnection c : connections)
			if(c.isActive && (c.getClientIdentifier().equals(player) || c.getServerIdentifier().equals(player)))
			{
				MinestuckCriteriaTriggers.SBURB_CONNECTION.trigger(playerMP);
				break;
			}
		for(PlayerIdentifier i : iden)
		{
			if(i != null)
			{
				SkaianetInfoPacket packet = generateClientInfoPacket(i);
				MinestuckPacketHandler.sendToPlayer(packet, playerMP);
			}
		}
	}
	
	private SkaianetInfoPacket generateClientInfoPacket(PlayerIdentifier player)
	{
		boolean clientResuming = resumingClients.containsKey(player);
		boolean serverResuming = resumingServers.containsKey(player) || serversOpen.containsKey(player);
		
		Map<Integer, String> serverMap = SessionHandler.getServerList(mcServer, player);
		
		List<SburbConnection> list = new ArrayList<>();
		for(SburbConnection c : connections)
			if(c.getClientIdentifier().equals(player) || c.getServerIdentifier().equals(player))
				list.add(c);
		
		return SkaianetInfoPacket.update(player.getId(), clientResuming, serverResuming, serverMap, list);
	}
	
	private void checkData()
	{
		if(!MinestuckConfig.skaianetCheck)
			return;
		
		Iterator<PlayerIdentifier> iter0 = infoToSend.keySet().iterator();
		while(iter0.hasNext())
			if(iter0.next().getPlayer(mcServer) == null)
			{
				//Debug.print("[SKAIANET] Player disconnected, removing data.");
				iter0.remove();
			}
		
		@SuppressWarnings("unchecked")
		Iterator<ComputerData>[] iter1 = new Iterator[]{serversOpen.values().iterator(),resumingClients.values().iterator(),resumingServers.values().iterator()};
		
		for(Iterator<ComputerData> i : iter1)
			while(i.hasNext())
			{
				ComputerData data = i.next();
				TileEntityComputer computer = getComputer(mcServer, data.location);
				if(computer == null || data.getDimension() == DimensionType.NETHER || !computer.owner.equals(data.owner)
						|| !(i == iter1[1] && computer.getData(0).getBoolean("isResuming")
								|| i != iter1[1] && computer.getData(1).getBoolean("isOpen")))
				{
					Debug.warn("[SKAIANET] Invalid computer in waiting list!");
					i.remove();
				}
			}
		
		Iterator<SburbConnection> iter2 = connections.iterator();
		while(iter2.hasNext())
		{
			SburbConnection c = iter2.next();
			if(c.getClientIdentifier() == null || c.getServerIdentifier() == null)
			{
				Debug.warn("Found a broken connection with the client \""+c.getClientIdentifier()+"\" and server \""+c.getServerIdentifier()+". If this message continues to show up, something isn't working as it should.");
				iter2.remove();
				continue;
			}
			if(c.isActive)
			{
				TileEntityComputer cc = getComputer(mcServer, c.client.location), sc = getComputer(mcServer, c.server.location);
				if(cc == null || sc == null || c.client.getDimension() == DimensionType.NETHER || c.server.getDimension() == DimensionType.NETHER || !c.getClientIdentifier().equals(cc.owner)
						|| !c.getServerIdentifier().equals(sc.owner) || !cc.getData(0).getBoolean("connectedToServer"))
				{
					Debug.warnf("[SKAIANET] Invalid computer in connection between %s and %s.", c.getClientIdentifier(), c.getServerIdentifier());
					if(!c.isMain)
						iter2.remove();
					else c.isActive = false;
					SessionHandler.onConnectionClosed(mcServer, c, true);
					ServerEditHandler.onDisconnect(c);
					
					if(cc != null)
					{
						cc.getData(0).setBoolean("connectedToServer", false);
						cc.latestmessage.put(0, "computer.messageClosed");
						cc.markBlockForUpdate();
					} else if(sc != null)
					{
						sc.latestmessage.put(1, "computer.messageClosed");
						sc.markBlockForUpdate();
					}
				}
				if(cc != null && c.enteredGame && !MinestuckDimensionHandler.isLandDimension(c.clientHomeLand))
					c.clientHomeLand = c.client.getDimension();
			}
			if(c.enteredGame && !MinestuckDimensionHandler.isLandDimension(c.clientHomeLand))
			{
				EntityPlayerMP player = c.getClientIdentifier().getPlayer(mcServer);
				if(player != null)
				{
					c.clientHomeLand = player.dimension;
					if(!MinestuckDimensionHandler.isLandDimension(c.clientHomeLand))
					{
						iter2.remove();
						SessionHandler.onConnectionClosed(mcServer, c, false);
						if(c.isActive)
						{
							TileEntityComputer cc = getComputer(mcServer, c.client.location), sc = getComputer(mcServer, c.server.location);
							cc.getData(0).setBoolean("connectedToServer", false);
							cc.latestmessage.put(0, "computer.messageClosed");
							cc.markBlockForUpdate();
							sc.getData(1).setString("connectedClient", "");
							sc.latestmessage.put(1, "computer.messageClosed");
							sc.markBlockForUpdate();
						}
					}
				}
			}
		}
		
		if(MinestuckConfig.privateComputers)
		{
			for(Entry<PlayerIdentifier,PlayerIdentifier[]> entry : infoToSend.entrySet())
			{
				EntityPlayerMP player = entry.getKey().getPlayer(mcServer);
				UserListOpsEntry opsEntry = player == null ? null : player.getServer().getPlayerList().getOppedPlayers().getEntry(player.getGameProfile());
				if(opsEntry != null && opsEntry.getPermissionLevel() >= 2)
					continue;
				
				for(int i = 0; i < entry.getValue().length; i++)
					if(entry.getValue()[i] != null && !entry.getValue()[i].equals(entry.getKey()))
						entry.getValue()[i] = null;
			}
		}
	}
	
	public SburbConnection getConnection(PlayerIdentifier client, PlayerIdentifier server)
	{
		for(SburbConnection c : connections)
			if(c.getClientIdentifier().equals(client) && c.getServerIdentifier().equals(server))
				return c;
		return null;
	}
	
	/**
	 * Gets the <code>TileEntityComputer</code> at the given position.
	 * @param server The server which this takes place.
	 * @param location A <code>Location</code> pointing to the computer.
	 * @return The <code>TileEntityComputer</code> at the given position,
	 * or <code>null</code> if there isn't one there.
	 */
	public static TileEntityComputer getComputer(MinecraftServer server, Location location)
	{
		if(location == null)
			return null;
		World world = DimensionManager.getWorld(server, location.dim, false, true);	//TODO look over code to limit force loading of dimensions
		if(world == null)
			return null;
		TileEntity te = world.getTileEntity(location.pos);
		if(!(te instanceof TileEntityComputer))
			return null;
		else return (TileEntityComputer)te;
	}
	
	public SburbConnection getServerConnection(ComputerData data)
	{
		for(SburbConnection c : connections)
			if(c.isActive && c.server.equals(data))
				return c;
		return null;
	}
	
	public DimensionType enterMedium(EntityPlayerMP player, DimensionType dimensionId, Teleport.ITeleporter teleport)
	{
		PlayerIdentifier username = IdentifierHandler.encode(player);
		SburbConnection c = getMainConnection(username, true);
		if(c == null)
		{
			c = getClientConnection(username);
			if(c == null)
			{
				Debug.infof("Player %s entered without connection. Creating connection... ", player.getName());
				c = new SburbConnection();
				c.isActive = false;
				c.isMain = true;
				c.clientIdentifier = username;
				c.serverIdentifier = IdentifierHandler.nullIdentifier;
				String s = SessionHandler.onConnectionCreated(mcServer, c);
				if(s == null)
				{
					SburbHandler.onFirstItemGiven(c);
					connections.add(c);
				}
				else if(SessionHandler.singleSession)
				{
					Debug.warnf("Failed to create connection: %s. Trying again with global session disabled for this world...", s);
					SessionHandler.singleSession = false;
					SessionHandler.split();
					s = SessionHandler.onConnectionCreated(mcServer, c);
					if(s == null)
					{
						SburbHandler.onFirstItemGiven(c);
						connections.add(c);
					} else
					{
						Debug.errorf("Couldn't create a connection for %s: %s. Stopping entry.", player.getName(), s);
						return null;
					}
				} else
				{
					Debug.errorf("Couldn't create a connection for %s: %s. Stopping entry.", player.getName(), s);
					return null;
				}
			}
			else giveItems(username);
		}
		else if(c.enteredGame)
			return c.clientHomeLand;
		
		c.clientHomeLand = dimensionId;
		SburbHandler.onLandCreated(player.getServer(), c);
		
		if(teleport != null && Teleport.teleportEntity(player, dimensionId, teleport))
		{
			c.enteredGame = true;
			SburbHandler.onGameEntered(c);
			
			c.centerX = 0;
			c.centerZ = 0;
			c.useCoordinates = false;
			updateAll();
			sendLandChainUpdate();
		} else //TODO Look at effects of cancelling entry at this point
			Debug.errorf("Couldn't move %s to their Land. Stopping entry.", player.getName());
		return dimensionId;
	}
	
	public void resetGivenItems()
	{
		for(SburbConnection c : connections)
		{
			for(int i = 0; i < c.givenItemList.length; i++)
				c.givenItemList[i] = false;
			c.unregisteredItems = new NBTTagList();
			EditData data = ServerEditHandler.getData(c);
			if(data != null)
			{
				ServerEditPacket packet = ServerEditPacket.givenItems(c.givenItemList);
				MinestuckPacketHandler.sendToPlayer(packet, data.getEditor());
			}
		}
	}
	
	public void movingComputer(TileEntityComputer oldTE, TileEntityComputer newTE)
	{
		ComputerData dataOld = ComputerData.createData(oldTE), dataNew = ComputerData.createData(newTE);
		for(SburbConnection c : connections)
		{
			if(dataOld.equals(c.client))
				c.client = dataNew;
			if(dataOld.equals(c.server))
				c.server = dataNew;
		}
		
		if(resumingClients.containsKey(dataOld.owner) && resumingClients.get(dataOld.owner).equals(dataOld))
			resumingClients.put(dataOld.owner, dataNew);	//Used to be map.replace until someone had a NoSuchMethodError
		if(resumingServers.containsKey(dataOld.owner) && resumingServers.get(dataOld.owner).equals(dataOld))
			resumingServers.put(dataOld.owner, dataNew);
		if(serversOpen.containsKey(dataOld.owner) && serversOpen.get(dataOld.owner).equals(dataOld))
			serversOpen.put(dataOld.owner, dataNew);
		
		movingComputers.add(dataNew);
	}
	
	public void clearMovingList()
	{
		movingComputers.clear();
	}
	
	public static SkaianetHandler get(MinecraftServer server)
	{
		return get(server.getWorld(DimensionType.OVERWORLD));
	}
	
	public static SkaianetHandler get(World world)
	{
		if(world.isRemote)
			throw new IllegalStateException("Should not attempt to get saved data on the client side!");
		
		WorldSavedDataStorage storage = world.getMapStorage();
		SkaianetHandler instance = storage.func_212426_a(DimensionType.OVERWORLD, s -> new SkaianetHandler(s, world.getServer()), DATA_NAME);
		
		if(instance == null)	//There is no save data
		{
			instance = new SkaianetHandler(world.getServer());
			storage.func_212424_a(DimensionType.OVERWORLD, DATA_NAME, instance);
		}
		
		return instance;
	}
}