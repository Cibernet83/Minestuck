package com.mraof.minestuck.event;

import com.mraof.minestuck.inventory.captchalouge.Modus;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class DropSylladexEvent extends Event
{
	EntityPlayer player;
	Modus modus;
	NonNullList<ItemStack> droppedItems;

	public DropSylladexEvent(EntityPlayer player, Modus modus, NonNullList<ItemStack> droppedItems)
	{
		this.player = player;
		this.modus = modus;
		this.droppedItems = droppedItems;
	}

	public EntityPlayer getPlayer() {
		return player;
	}

	public Modus getModus() {
		return modus;
	}

	public NonNullList<ItemStack> getDroppedItems() {
		return droppedItems;
	}
}
