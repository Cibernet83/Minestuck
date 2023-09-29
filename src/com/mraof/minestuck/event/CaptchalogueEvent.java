package com.mraof.minestuck.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class CaptchalogueEvent extends Event
{
	EntityPlayer player;
	ItemStack stack;
	ItemStack originalStack;

	public CaptchalogueEvent(EntityPlayer player, ItemStack stack)
	{
		this.player = player;
		this.stack = stack;
		this.originalStack = stack;
	}

	public EntityPlayer getPlayer() {
		return player;
	}

	public ItemStack getStack() {
		return stack;
	}

	public ItemStack getOriginalStack() {
		return originalStack;
	}

	public void setStack(ItemStack stack) {
		this.stack = stack;
	}

	public static class Inventory extends CaptchalogueEvent
	{

		public Inventory(EntityPlayer player, ItemStack stack) {
			super(player, stack);
		}
	}
}
