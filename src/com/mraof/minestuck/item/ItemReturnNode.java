package com.mraof.minestuck.item;

import com.mraof.minestuck.block.BlockGate;
import com.mraof.minestuck.block.MinestuckBlocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemReturnNode extends Item
{
	public ItemReturnNode()
	{
		setUnlocalizedName("returnNode");
		setCreativeTab(TabMinestuck.instance);
		setMaxStackSize(1);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos posIn, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		boolean flip = false;
		facing = player.getHorizontalFacing();
		posIn = posIn.up();
		
		for(int j = 0; j < 4; j++)
		{

			BlockPos pos = posIn.offset(facing, j % 2).offset(facing.rotateY(),j/2);
			System.out.println("can stone block go on " + pos + "? " + Blocks.STONE.canPlaceBlockAt(world, pos));
			if(!Blocks.STONE.canPlaceBlockAt(world, pos))
			{
				flip = true;
				for(int k = 0; j < 4; k++)
				{
					pos = posIn.offset(facing, k % 2).offset(facing.rotateYCCW(),k/2);
					if(!Blocks.STONE.canPlaceBlockAt(world, pos))
						return EnumActionResult.FAIL;
				}
			}
		}
		
		for(int i = 0; i < 4; i++)
		{
			//posIn.add(i % 2, 1, i/2);
			BlockPos pos = posIn.offset(facing, i % 2).offset(flip ? facing.rotateYCCW() : facing.rotateY(),i/2);
			if(i == 1)
			{
				world.setBlockState(pos, MinestuckBlocks.returnNode.getDefaultState().cycleProperty(BlockGate.isMainComponent), 2);
				//Do something with the tile entity?
			} else world.setBlockState(pos, MinestuckBlocks.returnNode.getDefaultState(), 2);
		}
		
		if(!player.isCreative())
			player.getHeldItem(hand).shrink(1);
		
		return EnumActionResult.SUCCESS;
	}
}
