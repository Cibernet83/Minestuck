package com.mraof.minestuck.alchemy;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;

public class DummySmeltingRecipe extends ShapelessRecipes
{
	public DummySmeltingRecipe(ItemStack input, ItemStack output)
	{
		super("", output, NonNullList.from(Ingredient.EMPTY, Ingredient.fromStacks(input)));
	}
}
