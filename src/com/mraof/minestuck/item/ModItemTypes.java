package com.mraof.minestuck.item;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.ToolType;

import java.util.function.Supplier;

public class ModItemTypes
{
	public static final IItemTier EMERALD_TIER = new ModItemTier(3, 1220, 8.0F, 3.0F, 12, () -> Ingredient.fromItems(Items.EMERALD));
	public static final IItemTier URANIUM_TIER = new ModItemTier(2, 512, 5.0F, 4.0F, 5, () -> Ingredient.fromItems(MinestuckItems.RAW_URANIUM));
	public static final IItemTier POGO_TIER = new ModItemTier(1, 400, 2.0F, 2.0F, 8, () -> Ingredient.fromItems(Items.SLIME_BALL));
	public static final IItemTier BOOK_TIER = new ModItemTier(0, 250, 1.0F, 2.0F, 15, () -> Ingredient.fromItems(Items.BOOK));
	public static final IItemTier REGI_TIER = new ModItemTier(3, 812, 8.0F, 3.0F, 10, () -> Ingredient.EMPTY);
	public static final IItemTier RUBY_TIER = new ModItemTier(3, 2000, 10.0F, 4.0F, 16, () -> Ingredient.EMPTY);
	public static final IItemTier ZILLYHOO_TIER = new ModItemTier(4, 3000, 15.0F, 5.0F, 25, () -> Ingredient.EMPTY);
	public static final IItemTier SBAHJ_TIER = new ModItemTier(0, 59, 1.0F, -1.0F, 5, () -> Ingredient.EMPTY);
	public static final IItemTier CACTUS_TIER = new ModItemTier(0, 104, 2.0F, 1.0F, 10, () -> Ingredient.fromItems(Blocks.CACTUS));
	public static final IItemTier MEAT_TIER = new ModItemTier(0, 175, 1.0F, 0.0F, 5, () -> Ingredient.EMPTY);
	public static final IItemTier CANDY_TIER = new ModItemTier(0, 450, 2.5F, 0.0F, 15, () -> Ingredient.EMPTY);
	public static final IItemTier HORRORTERROR_TIER = new ModItemTier(3, 1600, 4.0F, 4.0F, 15, () -> Ingredient.EMPTY);
	
	public static final IArmorMaterial PRISMARINE_ARMOR = new ModArmorMaterial("minestuck:prismarine", 20, new int[]{3, 7, 6, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, () -> Ingredient.fromItems(Items.PRISMARINE_SHARD));
	
	public static final ToolType SICKLE_TOOL = ToolType.get("sickle");
	
	private static class ModItemTier implements IItemTier
	{
		private final int harvestLevel;
		private final int maxUses;
		private final float efficiency;
		private final float attackDamage;
		private final int enchantability;
		private final LazyLoadBase<Ingredient> repairMaterial;
		
		public ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial)
		{
			this.harvestLevel = harvestLevel;
			this.maxUses = maxUses;
			this.efficiency = efficiency;
			this.attackDamage = attackDamage;
			this.enchantability = enchantability;
			this.repairMaterial = new LazyLoadBase<>(repairMaterial);
		}
		
		@Override
		public int getHarvestLevel()
		{
			return harvestLevel;
		}
		
		@Override
		public int getMaxUses()
		{
			return maxUses;
		}
		
		@Override
		public float getEfficiency()
		{
			return efficiency;
		}
		
		@Override
		public float getAttackDamage()
		{
			return attackDamage;
		}
		
		@Override
		public int getEnchantability()
		{
			return enchantability;
		}
		
		@Override
		public Ingredient getRepairMaterial()
		{
			return repairMaterial.getValue();
		}
	}
	
	private static class ModArmorMaterial implements IArmorMaterial
	{
		private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
		
		private final String name;
		private final int maxDamageFactor;
		private final int[] damageReductionAmountArray;
		private final int enchantability;
		private final SoundEvent soundEvent;
		private final float toughness;
		private final LazyLoadBase<Ingredient> repairMaterial;
		
		public ModArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, Supplier<Ingredient> repairMaterial)
		{
			this.name = name;
			this.maxDamageFactor = maxDamageFactor;
			this.damageReductionAmountArray = damageReductionAmountArray;
			this.enchantability = enchantability;
			this.soundEvent = soundEvent;
			this.toughness = toughness;
			this.repairMaterial = new LazyLoadBase<>(repairMaterial);
		}
		
		@Override
		public String getName()
		{
			return name;
		}
		
		@Override
		public int getDurability(EntityEquipmentSlot slotIn)
		{
			return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
		}
		
		@Override
		public int getDamageReductionAmount(EntityEquipmentSlot slotIn)
		{
			return this.damageReductionAmountArray[slotIn.getIndex()];
		}
		
		@Override
		public int getEnchantability()
		{
			return enchantability;
		}
		
		@Override
		public SoundEvent getSoundEvent()
		{
			return soundEvent;
		}
		
		@Override
		public float getToughness()
		{
			return toughness;
		}
		
		@Override
		public Ingredient getRepairMaterial()
		{
			return repairMaterial.getValue();
		}
	}
}