package com.mraof.minestuck.network;

import java.util.ArrayList;
import java.util.EnumSet;

import com.mraof.minestuck.item.MinestuckItems;
import com.mraof.minestuck.tileentity.TileEntityTransportalizer;
import com.mraof.minestuck.util.Debug;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;

public class StoneTabletPacket extends MinestuckPacket
{
	public String text;
	
	@Override
	public MinestuckPacket generatePacket(Object... dat)
	{
		if(dat.length > 0)
			data.writeBytes(((String) dat[0]).getBytes());
		return this;
	}

	@Override
	public MinestuckPacket consumePacket(ByteBuf data)
	{
		int size = data.readableBytes();
		byte[] destBytes = new byte[size];
		for(int i = 0; i < size; i++)
			destBytes[i] = data.readByte();
		text = new String(destBytes);
		return this;
	}

	@Override
	public void execute(EntityPlayer player)
	{
		ItemStack stack = player.getHeldItemMainhand();
		ItemStack tablet = new ItemStack(MinestuckItems.stoneSlab);
		if(!stack.isItemEqual(tablet)) 
			if(!(stack = player.getHeldItemOffhand()).isItemEqual(tablet)) 
				return;
		
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString("text",text);
		stack.setTagCompound(nbt);
	}

	@Override
	public EnumSet<Side> getSenderSide()
	{
		return EnumSet.of(Side.CLIENT);
	}
}
