package com.mraof.minestuck.block.multiblock;

import com.mraof.minestuck.block.BlockPunchDesignix;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.registries.IForgeRegistry;

public class MultiblockPunchDesignix extends MultiblockMachine
{
	public Block LEFT_LEG, RIGHT_LEG, SLOT, KEYBOARD;
	
	@Override
	public Block getMainBlock()
	{
		return SLOT;
	}
	
	@Override
	public void registerBlocks(IForgeRegistry<Block> registry)
	{
		registry.register(LEFT_LEG = new BlockPunchDesignix(this, BlockPunchDesignix.LEG_SHAPE, new BlockPos(0, 1, 0), Block.Properties.create(Material.IRON).hardnessAndResistance(3.0F)).setRegistryName("punch_designix_left_leg"));
		registry.register(RIGHT_LEG = new BlockPunchDesignix(this, BlockPunchDesignix.LEG_SHAPE, new BlockPos(1, 1, 0), Block.Properties.create(Material.IRON).hardnessAndResistance(3.0F)).setRegistryName("punch_designix_right_leg"));
		registry.register(SLOT = new BlockPunchDesignix.Slot(this, BlockPunchDesignix.SLOT_SHAPE, Block.Properties.create(Material.IRON).hardnessAndResistance(3.0F)).setRegistryName("punch_designix_slot"));
		registry.register(KEYBOARD = new BlockPunchDesignix(this, BlockPunchDesignix.KEYBOARD_SHAPE, new BlockPos(1, 0, 0), Block.Properties.create(Material.IRON).hardnessAndResistance(3.0F)).setRegistryName("punch_designix_keyboard"));
		
	}
}