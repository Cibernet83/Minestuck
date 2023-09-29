package com.mraof.minestuck.event;

import com.mraof.minestuck.network.skaianet.SburbConnection;
import net.minecraftforge.fml.common.eventhandler.Event;

public abstract class GenerateLandAspectEvent extends Event
{
	private SburbConnection connection;

	public GenerateLandAspectEvent(SburbConnection connection)
	{
		this.connection = connection;
	}

	public SburbConnection getConnection()
	{
		return connection;
	}
}
