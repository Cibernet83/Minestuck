package com.mraof.minestuck.network;

import com.mraof.minestuck.tileentity.TileEntityAlchemiter;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class AlchemiterPacket
{
	public BlockPos pos;
	public int quantity;
	
	public AlchemiterPacket(BlockPos pos, int quantity)
	{
		this.pos = pos;
		this.quantity = quantity;
	}
	
	public void encode(PacketBuffer buffer)
	{
		buffer.writeBlockPos(pos);
		buffer.writeInt(quantity);
	}
	
	public static AlchemiterPacket decode(PacketBuffer buffer)
	{
		BlockPos pos = buffer.readBlockPos();
		int quantity = buffer.readInt();
		
		return new AlchemiterPacket(pos, quantity);
	}
	
	public void consume(Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
			ctx.get().enqueueWork(() -> this.execute(ctx.get().getSender()));
		
		ctx.get().setPacketHandled(true);
	}
	
	public void execute(EntityPlayerMP player)
	{
		if(player.getEntityWorld().isBlockLoaded(pos))
		{
			TileEntity te;
			te = player.getEntityWorld().getTileEntity(pos);
			if(te instanceof TileEntityAlchemiter)
			{
				((TileEntityAlchemiter) te).processContents(quantity, player);
			}
		}
	}
}