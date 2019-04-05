package com.mraof.minestuck.world.lands.structure.village;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.mraof.minestuck.Minestuck;
import com.mraof.minestuck.entity.EntityFrog;
import com.mraof.minestuck.entity.consort.EnumConsort;
import com.mraof.minestuck.entity.consort.EnumConsort.MerchantType;
import com.mraof.minestuck.entity.item.EntityShopPoster;
import com.mraof.minestuck.item.MinestuckItems;
import com.mraof.minestuck.world.MinestuckDimensionHandler;
import com.mraof.minestuck.world.lands.gen.ChunkProviderLands;
import com.mraof.minestuck.world.lands.structure.blocks.StructureBlockUtil;
import com.mraof.minestuck.world.storage.loot.MinestuckLoot;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

public class ConsortVillageTurtle
{
	public static class TurtleCenter extends ConsortVillageCenter.VillageCenter
	{
		public TurtleCenter()
		{
			super();
		}
		
		public TurtleCenter(List<ConsortVillageComponents.PieceWeight> pieceWeightList, int x, int z, Random rand)
		{
			super(pieceWeightList);
			this.setCoordBaseMode(EnumFacing.Plane.HORIZONTAL.random(rand));
			
			this.boundingBox = new StructureBoundingBox(x, 60, z, x + 8 - 1, 70, z + 8 - 1);
		}
		
		@Override
		public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand)
		{
			ConsortVillageComponents.generateAndAddRoadPiece((ConsortVillageCenter.VillageCenter) componentIn, listIn, rand, boundingBox.minX + 3, boundingBox.minY, boundingBox.maxZ + 1, EnumFacing.SOUTH);
			ConsortVillageComponents.generateAndAddRoadPiece((ConsortVillageCenter.VillageCenter) componentIn, listIn, rand, boundingBox.minX - 1, boundingBox.minY, boundingBox.minZ + 3, EnumFacing.WEST);
			ConsortVillageComponents.generateAndAddRoadPiece((ConsortVillageCenter.VillageCenter) componentIn, listIn, rand, boundingBox.minX + 3, boundingBox.minY, boundingBox.minZ - 1, EnumFacing.NORTH);
			ConsortVillageComponents.generateAndAddRoadPiece((ConsortVillageCenter.VillageCenter) componentIn, listIn, rand, boundingBox.maxX + 1, boundingBox.minY, boundingBox.minZ + 3, EnumFacing.EAST);
		}
		
		@Override
		public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
		{
			if(worldIn.isRemote) return true;
			if (this.averageGroundLvl < 0)
			{
				this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);
				
				if (this.averageGroundLvl < 0)
				{
					return true;
				}
				
				this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.minY - 1, 0);
			}
			
			ChunkProviderLands provider = (ChunkProviderLands) worldIn.provider.createChunkGenerator();
			String terrain = MinestuckDimensionHandler.getAspects(worldIn.provider.getDimension()).aspectTerrain.getPrimaryName();
			String[] pieces = new String[] {"well", "park"};
			String piece = pieces[randomIn.nextInt(pieces.length)];
			Template template = worldIn.getSaveHandler().getStructureTemplateManager().getTemplate(worldIn.getMinecraftServer(), new ResourceLocation(Minestuck.MOD_ID, "village/turtle/"+terrain+"/"+piece));
			if(template == null) template = worldIn.getSaveHandler().getStructureTemplateManager().getTemplate(worldIn.getMinecraftServer(), new ResourceLocation(Minestuck.MOD_ID, "village/turtle/default/"+piece));
			
			PlacementSettings settings = new PlacementSettings().setBoundingBox(structureBoundingBoxIn).setRotation(getTemplateRotation());
			BlockPos pos = new BlockPos(this.getXWithOffset(0, 0), this.getYWithOffset(0), this.getZWithOffset(0, 0));
			template.addBlocksToWorld(worldIn, pos, settings);
			
			System.out.println(piece);
			System.out.println(pos);
			Map<BlockPos, String> datablocks = template.getDataBlocks(pos, settings);
			
			return true;
		}
		
	}
	
	public static class LoweredShellHouse1 extends ConsortVillageComponents.ConsortVillagePiece
	{
		public LoweredShellHouse1()
		{
			spawns = new boolean[2];
		}
		
		public LoweredShellHouse1(ConsortVillageCenter.VillageCenter start, Random rand, StructureBoundingBox boundingBox, EnumFacing facing)
		{
			this();
			this.setCoordBaseMode(facing);
			this.boundingBox = boundingBox;
		}
		
		public static LoweredShellHouse1 createPiece(ConsortVillageCenter.VillageCenter start, List<StructureComponent> componentList, Random rand, int x, int y, int z, EnumFacing facing)
		{
			StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, -2, 0, 8, 5, 9, facing);
			return StructureComponent.findIntersecting(componentList, structureboundingbox) == null ? new LoweredShellHouse1(start, rand, structureboundingbox, facing) : null;
		}
		
		@Override
		public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
		{
			if(worldIn.isRemote) return true;
			if (this.averageGroundLvl < 0)
			{
				this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);
				
				if (this.averageGroundLvl < 0)
				{
					return true;
				}
				
				this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.minY - 1, 0);
			}
			
			ChunkProviderLands provider = (ChunkProviderLands) worldIn.provider.createChunkGenerator();
			String terrain = MinestuckDimensionHandler.getAspects(worldIn.provider.getDimension()).aspectTerrain.getPrimaryName();
			Template template = worldIn.getSaveHandler().getStructureTemplateManager().getTemplate(worldIn.getMinecraftServer(), new ResourceLocation(Minestuck.MOD_ID, "village/turtle/"+terrain+"/house"));
			if(template == null) template = worldIn.getSaveHandler().getStructureTemplateManager().getTemplate(worldIn.getMinecraftServer(), new ResourceLocation(Minestuck.MOD_ID, "village/turtle/default/house"));
			
			PlacementSettings settings = new PlacementSettings().setBoundingBox(structureBoundingBoxIn).setRotation(getTemplateRotation());
			BlockPos pos = new BlockPos(this.getXWithOffset(0, 0), this.getYWithOffset(0)-2, this.getZWithOffset(0, 0));
			template.addBlocksToWorld(worldIn, pos, settings);
			
			System.out.println("house");
			System.out.println(pos);
			Map<BlockPos, String> datablocks = template.getDataBlocks(pos, settings);
			for (Entry<BlockPos, String> entry : datablocks.entrySet())
            {
                BlockPos blockpos = entry.getKey();
                if ("civilian".equals(entry.getValue()))
                {
                    worldIn.setBlockToAir(blockpos);
                    if(!spawns[0])
        				spawns[0] = spawnConsort(blockpos, structureBoundingBoxIn, worldIn, EnumConsort.MerchantType.NONE, 48);
        			if(!spawns[1])
        				spawns[1] = spawnConsort(blockpos, structureBoundingBoxIn, worldIn, EnumConsort.MerchantType.NONE, 48);
                }
            }
			
			return true;
		}
	}
	
	public static class TurtleMarketBuilding1 extends ConsortVillageComponents.ConsortVillagePiece
	{
		public TurtleMarketBuilding1()
		{
			spawns = new boolean[2];
		}
		
		public TurtleMarketBuilding1(ConsortVillageCenter.VillageCenter start, Random rand, StructureBoundingBox boundingBox, EnumFacing facing)
		{
			this();
			setCoordBaseMode(facing);
			this.boundingBox = boundingBox;
		}
		
		public static TurtleMarketBuilding1 createPiece(ConsortVillageCenter.VillageCenter start, List<StructureComponent> componentList, Random rand, int x, int y, int z, EnumFacing facing)
		{
			StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, 6, 6, 8, facing);
			return StructureComponent.findIntersecting(componentList, structureboundingbox) == null ? new TurtleMarketBuilding1(start, rand, structureboundingbox, facing) : null;
		}
		
		@Override
		public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
		{
			if(worldIn.isRemote) return true;
			if (this.averageGroundLvl < 0)
			{
				this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);
				
				if (this.averageGroundLvl < 0)
				{
					return true;
				}
				
				this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.minY - 1, 0);
			}
			
			ChunkProviderLands provider = (ChunkProviderLands) worldIn.provider.createChunkGenerator();
			String terrain = MinestuckDimensionHandler.getAspects(worldIn.provider.getDimension()).aspectTerrain.getPrimaryName();
			Template template = worldIn.getSaveHandler().getStructureTemplateManager().getTemplate(worldIn.getMinecraftServer(), new ResourceLocation(Minestuck.MOD_ID, "village/turtle/"+terrain+"/shop"));
			if(template == null) template = worldIn.getSaveHandler().getStructureTemplateManager().getTemplate(worldIn.getMinecraftServer(), new ResourceLocation(Minestuck.MOD_ID, "village/turtle/default/shop"));
			
			PlacementSettings settings = new PlacementSettings().setBoundingBox(structureBoundingBoxIn).setRotation(getTemplateRotation());
			BlockPos pos = new BlockPos(this.getXWithOffset(0, 0), this.getYWithOffset(0)-2, this.getZWithOffset(0, 0));
			template.addBlocksToWorld(worldIn, pos, settings);
			EnumConsort.MerchantType profession = EnumConsort.getRandomMerchant(randomIn);
			
			System.out.println(profession.toString().toLowerCase() + " store");
			System.out.println(pos);
			Map<BlockPos, String> datablocks = template.getDataBlocks(pos, settings);
			for (Entry<BlockPos, String> entry : datablocks.entrySet())
            {
                BlockPos blockpos = entry.getKey();
                if ("consort_shop_sign".equals(entry.getValue()))
                {
                    worldIn.setBlockToAir(blockpos);
                    int meta = EntityShopPoster.getMetaFromProfession(profession);
                    worldIn.spawnEntity(new EntityShopPoster(worldIn, blockpos, this.getCoordBaseMode().rotateYCCW(), new ItemStack(MinestuckItems.shopPoster, 1, meta), meta));
                }
                else if("shopkeeper".equals(entry.getValue()))
                {
                	worldIn.setBlockToAir(blockpos);
                	spawnConsort(blockpos, structureBoundingBoxIn, worldIn, profession, 1);
                }
            }
			
			return true;
		}
	}
	
	public static class TurtleTemple1 extends ConsortVillageComponents.ConsortVillagePiece
	{
		public TurtleTemple1()
		{
			spawns = new boolean[3];
		}
		
		public TurtleTemple1(ConsortVillageCenter.VillageCenter start, Random rand, StructureBoundingBox boundingBox, EnumFacing facing)
		{
			this();
			this.setCoordBaseMode(facing);
			this.boundingBox = boundingBox;
		}
		
		public static TurtleTemple1 createPiece(ConsortVillageCenter.VillageCenter start, List<StructureComponent> componentList, Random rand, int x, int y, int z, EnumFacing facing)
		{
			StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, -1, 0, 11, 6, 14, facing);
			return StructureComponent.findIntersecting(componentList, structureboundingbox) == null ? new TurtleTemple1(start, rand, structureboundingbox, facing) : null;
		}
		
		@Override
		public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn)
		{
			if(worldIn.isRemote) return true;
			if (this.averageGroundLvl < 0)
			{
				this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);
				
				if (this.averageGroundLvl < 0)
				{
					return true;
				}
				
				this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.minY - 1, 0);
			}
			
			ChunkProviderLands provider = (ChunkProviderLands) worldIn.provider.createChunkGenerator();
			String terrain = MinestuckDimensionHandler.getAspects(worldIn.provider.getDimension()).aspectTerrain.getPrimaryName();
			Template template = worldIn.getSaveHandler().getStructureTemplateManager().getTemplate(worldIn.getMinecraftServer(), new ResourceLocation(Minestuck.MOD_ID, "village/turtle/"+terrain+"/temple"));
			if(template == null) template = worldIn.getSaveHandler().getStructureTemplateManager().getTemplate(worldIn.getMinecraftServer(), new ResourceLocation(Minestuck.MOD_ID, "village/turtle/default/temple"));
			
			PlacementSettings settings = new PlacementSettings().setBoundingBox(structureBoundingBoxIn).setRotation(getTemplateRotation());
			BlockPos pos = new BlockPos(this.getXWithOffset(0, 0), this.getYWithOffset(0)-1, this.getZWithOffset(0, 0));
			template.addBlocksToWorld(worldIn, pos, settings);
			
			System.out.println("temple");
			System.out.println(pos);
			Map<BlockPos, String> datablocks = template.getDataBlocks(pos, settings);
			for (Entry<BlockPos, String> entry : datablocks.entrySet())
            {
                BlockPos blockpos = entry.getKey();
                if ("frog".equals(entry.getValue()))
                {
                	worldIn.setBlockToAir(blockpos);
                	int chance = new Random().nextInt(100);
                	if(chance == 1) worldIn.spawnEntity(new EntityFrog(worldIn));
                }
                
            }
			
			return true;
		}
	}

	public static class TurtleProfessionalBuilding extends ConsortVillageComponents.ConsortVillagePiece
	{
		
		public TurtleProfessionalBuilding()
		{
			spawns = new boolean[1];
		}
		
		public TurtleProfessionalBuilding(ConsortVillageCenter.VillageCenter start, Random rand, StructureBoundingBox boundingBox, EnumFacing facing)
		{
			this();
			this.setCoordBaseMode(facing);
			this.boundingBox = boundingBox;
		}
		
		public static TurtleProfessionalBuilding createPiece(ConsortVillageCenter.VillageCenter start, List<StructureComponent> componentList, Random rand, int x, int y, int z, EnumFacing facing)
		{
			StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, -1, 0, 11, 6, 14, facing);
			return StructureComponent.findIntersecting(componentList, structureboundingbox) == null ? new TurtleProfessionalBuilding(start, rand, structureboundingbox, facing) : null;
		}
		
		@Override
		public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) 
		{
			if(worldIn.isRemote) return true;
			if (this.averageGroundLvl < 0)
			{
				this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);
				
				if (this.averageGroundLvl < 0)
				{
					return true;
				}
				
				this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.minY - 1, 0);
			}
			
			ChunkProviderLands provider = (ChunkProviderLands) worldIn.provider.createChunkGenerator();
			String terrain = MinestuckDimensionHandler.getAspects(worldIn.provider.getDimension()).aspectTerrain.getPrimaryName();
			String[] buildings = new String[] {"inn","smith","library", "farm"};
			String building = buildings[randomIn.nextInt(buildings.length)];
			Template template = worldIn.getSaveHandler().getStructureTemplateManager().getTemplate(worldIn.getMinecraftServer(), new ResourceLocation(Minestuck.MOD_ID, "village/turtle/"+terrain+"/"+building));
			if(template == null) template = worldIn.getSaveHandler().getStructureTemplateManager().getTemplate(worldIn.getMinecraftServer(), new ResourceLocation(Minestuck.MOD_ID, "village/turtle/default/"+building));
			
			PlacementSettings settings = new PlacementSettings().setBoundingBox(structureBoundingBoxIn).setRotation(getTemplateRotation());
			BlockPos pos = new BlockPos(this.getXWithOffset(0, 0), this.getYWithOffset(0)-1, this.getZWithOffset(0, 0));
			template.addBlocksToWorld(worldIn, pos, settings);
			
			System.out.println(building);
			System.out.println(pos);
			Map<BlockPos, String> datablocks = template.getDataBlocks(pos, settings);
			for (Entry<BlockPos, String> entry : datablocks.entrySet())
            {
                BlockPos blockpos = entry.getKey();
                if ("loot".equals(entry.getValue()))
                {
                	//TODO custom loot
                	worldIn.setBlockState(blockpos, Blocks.CHEST.getDefaultState().withProperty(Blocks.CHEST.FACING, getCoordBaseMode().getOpposite()));
                }
                else if("innkeeper".equals(entry.getValue()))
                {
                	worldIn.setBlockToAir(blockpos);
                	//TODO innkeeper profession
                	spawnConsort(blockpos, structureBoundingBoxIn, worldIn, MerchantType.INNKEEPER, 1);
                }
                else if("farmland".equals(entry.getValue()))
                {
                	worldIn.setBlockState(blockpos, Blocks.FARMLAND.getDefaultState());
                	
                	Block[] crops = new Block[] {Blocks.WHEAT,Blocks.CARROTS,Blocks.BEETROOTS,Blocks.POTATOES};
                	IBlockState state = crops[randomIn.nextInt(crops.length)].getDefaultState();
                	/*
                	 * TODO random crop age
                	if(state.getBlock() instanceof BlockCrops)
                		state = state.withProperty(((BlockCrops)state.getBlock()).AGE, randomIn.nextInt(((BlockCrops)state.getBlock()).getMaxAge()-1));
                		*/
                	worldIn.setBlockState(blockpos.up(), state);
                }
                
            }
			
			return true;
		}
		
	}
	public static class TurtleStorage extends ConsortVillageComponents.ConsortVillagePiece
	{
		
		public TurtleStorage()
		{
			spawns = new boolean[1];
		}
		
		public TurtleStorage(ConsortVillageCenter.VillageCenter start, Random rand, StructureBoundingBox boundingBox, EnumFacing facing)
		{
			this();
			this.setCoordBaseMode(facing);
			this.boundingBox = boundingBox;
		}
		
		public static TurtleStorage createPiece(ConsortVillageCenter.VillageCenter start, List<StructureComponent> componentList, Random rand, int x, int y, int z, EnumFacing facing)
		{
			StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, -1, 0, 11, 6, 14, facing);
			return StructureComponent.findIntersecting(componentList, structureboundingbox) == null ? new TurtleStorage(start, rand, structureboundingbox, facing) : null;
		}
		
		@Override
		public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) 
		{
			if(worldIn.isRemote) return true;
			if (this.averageGroundLvl < 0)
			{
				this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);
				
				if (this.averageGroundLvl < 0)
				{
					return true;
				}
				
				this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.minY - 1, 0);
			}
			
			ChunkProviderLands provider = (ChunkProviderLands) worldIn.provider.createChunkGenerator();
			String terrain = MinestuckDimensionHandler.getAspects(worldIn.provider.getDimension()).aspectTerrain.getPrimaryName();
			Template template = worldIn.getSaveHandler().getStructureTemplateManager().getTemplate(worldIn.getMinecraftServer(), new ResourceLocation(Minestuck.MOD_ID, "village/turtle/"+terrain+"/storage"));
			if(template == null) template = worldIn.getSaveHandler().getStructureTemplateManager().getTemplate(worldIn.getMinecraftServer(), new ResourceLocation(Minestuck.MOD_ID, "village/turtle/default/storage"));
			
			PlacementSettings settings = new PlacementSettings().setBoundingBox(structureBoundingBoxIn).setRotation(getTemplateRotation());
			BlockPos pos = new BlockPos(this.getXWithOffset(0, 0), this.getYWithOffset(0)-1, this.getZWithOffset(0, 0));
			template.addBlocksToWorld(worldIn, pos, settings);
			
			System.out.println("storage");
			System.out.println(pos);
			Map<BlockPos, String> datablocks = template.getDataBlocks(pos, settings);
			for (Entry<BlockPos, String> entry : datablocks.entrySet())
            {
                BlockPos blockpos = entry.getKey();
                if ("gourd".equals(entry.getValue()))
                {
                	Block[] gourds = new Block[] {Blocks.PUMPKIN, Blocks.MELON_BLOCK};
                	IBlockState state = gourds[randomIn.nextInt(gourds.length)].getDefaultState();
                	if(state.getBlock() instanceof BlockHorizontal) state = state.withProperty(BlockHorizontal.FACING, EnumFacing.HORIZONTALS[randomIn.nextInt(EnumFacing.HORIZONTALS.length)]);
                	worldIn.setBlockState(blockpos, state);
                }
                else if("loot".equals(entry.getValue()))
                {
                	//TODO storage loot
                	StructureBlockUtil.placeLootChest(blockpos, worldIn, structureBoundingBoxIn, getCoordBaseMode().getOpposite(), MinestuckLoot.BASIC_MEDIUM_CHEST, randomIn);
                }
                
            }
			
			return true;
		}
		
	}
	
}
