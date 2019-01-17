package com.mraof.minestuck.world.lands.terrain;

import com.mraof.minestuck.block.BlockMinestuckStone;
import com.mraof.minestuck.block.MinestuckBlocks;
import com.mraof.minestuck.entity.consort.EnumConsort;
import com.mraof.minestuck.world.biome.BiomeMinestuck;
import com.mraof.minestuck.world.gen.feature.WorldGenEndTree;
import com.mraof.minestuck.world.lands.decorator.FireFieldDecorator;
import com.mraof.minestuck.world.lands.decorator.ILandDecorator;
import com.mraof.minestuck.world.lands.decorator.SurfaceDecoratorVein;
import com.mraof.minestuck.world.lands.decorator.UndergroundDecoratorVein;
import com.mraof.minestuck.world.lands.decorator.WorldgenTreeDecorator;
import com.mraof.minestuck.world.lands.gen.ChunkProviderLands;
import com.mraof.minestuck.world.lands.structure.blocks.StructureBlockRegistry;

import net.minecraft.block.BlockChorusFlower;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

public class LandAspectData extends TerrainLandAspect 
{
	static Vec3d skyColor = new Vec3d(0.0D, 0.4D, 0.2D);
	
	@Override
	public void registerBlocks(StructureBlockRegistry registry)
	{
		registry.setBlockState("surface", Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.CYAN));
		registry.setBlockState("upper", Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.CYAN));
		registry.setBlockState("ground", Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.BLACK)); //this should be blackstone
		registry.setBlockState("ocean", MinestuckBlocks.blockEnder.getDefaultState());
		registry.setBlockState("structure_primary", Blocks.END_BRICKS.getDefaultState());
		registry.setBlockState("structure_primary_decorative", Blocks.PURPUR_PILLAR.getDefaultState().withProperty(BlockRotatedPillar.AXIS, Axis.Y));
		registry.setBlockState("structure_primary_stairs", Blocks.PURPUR_STAIRS.getDefaultState());
		registry.setBlockState("structure_secondary", Blocks.PURPUR_BLOCK.getDefaultState());
		registry.setBlockState("structure_secondary_stairs", Blocks.STONE_BRICK_STAIRS.getDefaultState());
		registry.setBlockState("fall_fluid", MinestuckBlocks.blockEnder.getDefaultState());
		registry.setBlockState("structure_planks", Blocks.BRICK_BLOCK.getDefaultState());
		registry.setBlockState("structure_planks_slab", Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.BRICK));
		registry.setBlockState("village_path", MinestuckBlocks.coarseEndStone.getDefaultState());
		registry.setBlockState("village_fence", Blocks.NETHER_BRICK_FENCE.getDefaultState());
		registry.setBlockState("structure_wool_1", Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.GREEN));
		registry.setBlockState("structure_wool_3", Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.PURPLE));
	}
	
	@Override
	public String getPrimaryName()
	{
		return "data";
	}
	
	@Override
	public String[] getNames()
	{
		return new String[] {"data", "hardware", "computers"};
	}
	
	@Override
	public List<ILandDecorator> getDecorators()
	{
		ArrayList<ILandDecorator> list = new ArrayList<ILandDecorator>();
		list.add(new WorldgenTreeDecorator(2, new WorldGenEndTree(false), BiomeMinestuck.mediumRough));
		list.add(new WorldgenTreeDecorator(3, new WorldGenEndTree(false), BiomeMinestuck.mediumNormal));
		
		list.add(new UndergroundDecoratorVein(MinestuckBlocks.ironOreEndStone.getDefaultState(), 20, 9, 64));
		list.add(new UndergroundDecoratorVein(MinestuckBlocks.redstoneOreEndStone.getDefaultState(), 10, 8, 32));
		list.add(new UndergroundDecoratorVein(MinestuckBlocks.goldOreSandstone.getDefaultState(), 8, 8, 27));
		list.add(new UndergroundDecoratorVein(Blocks.QUARTZ_ORE.getDefaultState(), 8, 5, 21));
		list.add(new UndergroundDecoratorVein(MinestuckBlocks.oreUranium.getDefaultState(), 5, 3, 15));
		
		return list;
	}
	
	//public 
	
	@Override
	public int getDayCycleMode()
	{
		return 0;
	}
	
	@Override
	public Vec3d getFogColor() 
	{
		return skyColor;
	}
	
	@Override
	public float getTemperature()
	{
		return 1.2F;
	}
	
	@Override
	public float getRainfall()
	{
		return 0.0F;
	}
	
	@Override
	public EnumConsort getConsortType()
	{
		return EnumConsort.NAKAGATOR;
	}
}
