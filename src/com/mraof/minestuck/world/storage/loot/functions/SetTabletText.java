package com.mraof.minestuck.world.storage.loot.functions;

import java.util.Random;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.mraof.minestuck.Minestuck;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;

public class SetTabletText extends LootFunction
{
	private static String[] pool = new String[] 
	{
		"shining",
		"determined",
		"hidden",
		"idea",
		"groceries",
		"inspirational",
		"game",
	};
	
	protected SetTabletText(LootCondition[] conditionsIn) {super(conditionsIn);}

	@Override
	public ItemStack apply(ItemStack stack, Random rand, LootContext context) 
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString("text", I18n.translateToLocal("tablet."+pool[rand.nextInt(pool.length)]));
		stack.setTagCompound(nbt);
		return stack;
	}
	
	public static class Serializer extends LootFunction.Serializer<SetTabletText>
	{

		public Serializer() {super(new ResourceLocation(Minestuck.MOD_ID,"set_tablet_text"), SetTabletText.class);}

		@Override
		public void serialize(JsonObject object, SetTabletText functionClazz,
				JsonSerializationContext serializationContext) {}

		@Override
		public SetTabletText deserialize(JsonObject object, JsonDeserializationContext deserializationContext,
				LootCondition[] conditionsIn) {return new SetTabletText(conditionsIn);}
		
	}
}
