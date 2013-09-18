package com.mraof.minestuck.network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.mraof.minestuck.util.Debug;
import com.mraof.minestuck.util.SburbConnection;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class SburbConnectPacket extends MinestuckPacket {

	public String client;
	public String server;
	public SburbConnectPacket() 
	{
		super(Type.SBURB_CONNECT);
	}

	@Override
	public byte[] generatePacket(Object... data) 
	{
		ByteArrayDataOutput dat = ByteStreams.newDataOutput();
		dat.write(data[0].toString().getBytes());
		dat.write('\n');
		dat.write(data[1].toString().getBytes());
		return dat.toByteArray();
	}

	@Override
	public MinestuckPacket consumePacket(byte[] data) 
	{
		ByteArrayDataInput dat = ByteStreams.newDataInput(data);

		client = dat.readLine();
		server = dat.readLine();
		
		return this;
	}

	@Override
	public void execute(INetworkManager network, MinestuckPacketHandler handler, Player player, String userName)
	{
		Debug.print("Got connect packet, client:"+client+" server:"+server);
		SburbConnection.connect(client,server);
		
		if (!((EntityPlayer)player).worldObj.isRemote) {
			Packet250CustomPayload packet = new Packet250CustomPayload();
			packet.channel = "Minestuck";
			packet.data = MinestuckPacket.makePacket(Type.SBURB_CONNECT,client,server);
			packet.length = packet.data.length;
			PacketDispatcher.sendPacketToAllPlayers(packet);
		}
	}

}
