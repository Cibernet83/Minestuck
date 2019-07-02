package com.mraof.minestuck.tileentity;

import com.mraof.minestuck.client.gui.GuiComputer;
import com.mraof.minestuck.network.skaianet.ComputerData;
import com.mraof.minestuck.network.skaianet.SburbConnection;
import com.mraof.minestuck.network.skaianet.SkaianetHandler;
import com.mraof.minestuck.util.ComputerProgram;
import com.mraof.minestuck.util.IdentifierHandler;
import com.mraof.minestuck.util.IdentifierHandler.PlayerIdentifier;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class TileEntityComputer extends TileEntity
{
	public TileEntityComputer()
	{
		super(MinestuckTiles.COMPUTER);
	}
	
	/**
	 * 0 = client, 1 = server
	 */
	public Hashtable<Integer, Boolean> installedPrograms = new Hashtable<Integer, Boolean>();
	public GuiComputer gui;
	public PlayerIdentifier owner;
	@OnlyIn(Dist.CLIENT)
	public int ownerId;
	public Hashtable<Integer, String> latestmessage = new Hashtable<Integer, String>();
	public NBTTagCompound programData = new NBTTagCompound();
	public int programSelected = -1;
	
	@OnlyIn(Dist.CLIENT)
	public ComputerProgram program;
	
	@Override
	public void read(NBTTagCompound compound)
	{
		super.read(compound);
		if (compound.contains("programs"))
		{
			NBTTagCompound programs = compound.getCompound("programs");
			for (Object name : programs.keySet())
			{
				installedPrograms.put(programs.getInt((String)name), true);
			}
		}

		latestmessage.clear();
		for(Entry<Integer,Boolean> e : installedPrograms.entrySet())
			if(e.getValue())
				latestmessage.put(e.getKey(), compound.getString("text" + e.getKey()));

		programData = compound.getCompound("programData");

		if(!compound.contains("programData"))
		{
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.putBoolean("connectedToServer", compound.getBoolean("connectServer"));
			nbt.putBoolean("isResuming", compound.getBoolean("resumeClient"));
			programData.put("program_0", nbt);
			nbt = new NBTTagCompound();
			nbt.putString("connectedClient", compound.getString("connectClient"));
			nbt.putBoolean("isOpen", compound.getBoolean("serverOpen"));
			programData.put("program_1", nbt);
		}
		if(compound.contains("ownerId"))
			ownerId = compound.getInt("ownerId");
		else this.owner = IdentifierHandler.load(compound, "owner");
		if(gui != null)
			gui.updateGui();
	}
	
	@Override
	public NBTTagCompound write(NBTTagCompound compound)
	{
		super.write(compound);
		NBTTagCompound programs = new NBTTagCompound();
		Iterator<Entry<Integer, Boolean>> it = this.installedPrograms.entrySet().iterator();
		//int place = 0;
		while (it.hasNext()) 
		{
			Map.Entry<Integer, Boolean> pairs = it.next();
			int program = pairs.getKey();
			programs.putInt("program" + program,program);
			//place++;
		}
		for(Entry<Integer, String> e : latestmessage.entrySet())
			compound.putString("text" + e.getKey(), e.getValue());
		compound.put("programs",programs);
		compound.put("programData", (NBTTagCompound) programData.copy());
		if (owner != null) 
			owner.saveToNBT(compound, "owner");
		return compound;
	}
	
	@Override
	public NBTTagCompound getUpdateTag()
	{
		NBTTagCompound tagCompound = new NBTTagCompound();
		this.write(tagCompound);
		tagCompound.remove("owner");
		tagCompound.remove("ownerMost");
		tagCompound.remove("ownerLeast");
		if(owner != null)
			tagCompound.putInt("ownerId", owner.getId());
		if(hasProgram(1))
		{
			SburbConnection c = SkaianetHandler.get(getWorld()).getServerConnection(ComputerData.createData(this));
			if(c != null)
				tagCompound.getCompound("programData").getCompound("program_1").putInt("connectedClient", c.getClientIdentifier().getId());
		}
		return tagCompound;
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		return new SPacketUpdateTileEntity(this.pos, 2, getUpdateTag());
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) 
	{
		this.read(pkt.getNbtCompound());
	}

	public boolean hasProgram(int id) 
	{
		return installedPrograms.get(id) == null ? false:installedPrograms.get(id);
	}

	public NBTTagCompound getData(int id) 
	{
		if(!programData.contains("program_"+id))
			programData.put("program_" + id, new NBTTagCompound());
		return programData.getCompound("program_" + id);
	}

	public void closeAll() 
	{
		for(Entry<Integer, Boolean> entry : installedPrograms.entrySet())
			if(entry.getValue() && entry.getKey() != -1 && ComputerProgram.getProgram(entry.getKey()) != null)
				ComputerProgram.getProgram(entry.getKey()).onClosed(this);
	}

	public void connected(PlayerIdentifier player, boolean isClient)
	{
		if(isClient)
		{
			getData(0).putBoolean("isResuming", false);
			getData(0).putBoolean("connectedToServer", true);
		}
		else
		{
			this.getData(1).putBoolean("isOpen", false);
		}
	}
	
	public void markBlockForUpdate()
	{
		IBlockState state = world.getBlockState(pos);
		this.world.notifyBlockUpdate(pos, state, state, 3);
	}
	
}
