package com.mraof.minestuck.world.lands.structure.blocks;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import com.mraof.minestuck.world.lands.gen.ChunkProviderLands;

import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedSpawnerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class StructureBlockUtil
{
	
	public Template getStructureTemplate(World worldIn, ResourceLocation templateLoc)
	{
		TemplateManager manager = worldIn.getSaveHandler().getStructureTemplateManager();
		Template template = manager.getTemplate(worldIn.getMinecraftServer(), templateLoc);
		try 
		{
			InputStream stream = Minecraft.getMinecraft().getResourceManager().getResource(templateLoc).getInputStream();
			NBTTagCompound nbt = CompressedStreamTools.readCompressed(stream);
			NBTTagList list = (NBTTagList) nbt.getTagList("palette", 10);
			NBTTagList list2 = new NBTTagList();
			
			for(int i = 0; i < list.tagCount(); i++)
			{
				String name = list.getCompoundTagAt(i).getString("Name");
				IBlockState state = ((ChunkProviderLands) worldIn.provider.createChunkGenerator()).blockRegistry.getBlockState(name);
				if(state != null) 
					list2.appendTag(NBTUtil.writeBlockState(new NBTTagCompound(), state));
				else list2.appendTag(list.getCompoundTagAt(i));
			}
			
			template.writeToNBT(nbt);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return template;
	}
	
	public static boolean placeSpawner(BlockPos pos, World world, StructureBoundingBox bb, String entityName)
	{
		WeightedSpawnerEntity entity = new WeightedSpawnerEntity();
		entity.getNbt().setString("id", entityName);
		entity.getNbt().setBoolean("spawned", true);
		return placeSpawner(pos, world, bb, entity);
	}
	
	public static boolean placeSpawner(BlockPos pos, World world, StructureBoundingBox bb, WeightedSpawnerEntity entity)
	{
		if(bb.isVecInside(pos))
		{
			world.setBlockState(pos, Blocks.MOB_SPAWNER.getDefaultState(), 2);
			
			TileEntity te = world.getTileEntity(pos);
			if(te instanceof TileEntityMobSpawner)
			{
				TileEntityMobSpawner spawner = (TileEntityMobSpawner) te;
				spawner.getSpawnerBaseLogic().setNextSpawnData(entity);
				
				return true;
			}
		}
		return false;
	}
	
	public static boolean placeLootChest(BlockPos pos, World world, StructureBoundingBox bb, EnumFacing facing, ResourceLocation lootTable, Random rand)
	{
		if(bb == null || bb.isVecInside(pos))
		{
			world.setBlockState(pos, Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, facing), 2);
			
			TileEntity te = world.getTileEntity(pos);
			if(te instanceof TileEntityChest)
			{
				TileEntityChest chest = (TileEntityChest) te;
				chest.setLootTable(lootTable, rand.nextLong());
			}
		}
		return false;
	}
}