package com.mraof.minestuck.event;

import com.mraof.minestuck.network.skaianet.SburbConnection;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.List;

public class GetDeployListEvent extends Event
{
	EntityPlayer player;
	SburbConnection connection;
	List<ItemStack> deployList;

	public GetDeployListEvent(EntityPlayer player, SburbConnection connection, List<ItemStack> deployList)
	{
		this.player = player;
		this.connection = connection;
		this.deployList = deployList;
	}

	public EntityPlayer getPlayer() {
		return player;
	}

	public SburbConnection getConnection() {
		return connection;
	}

	public List<ItemStack> getDeployList() {
		return deployList;
	}
}
