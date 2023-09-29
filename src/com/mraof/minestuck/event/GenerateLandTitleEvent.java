package com.mraof.minestuck.event;

import com.mraof.minestuck.network.skaianet.SburbConnection;
import com.mraof.minestuck.world.lands.title.TitleLandAspect;

public class GenerateLandTitleEvent extends GenerateLandAspectEvent
{
	private TitleLandAspect title;

	public GenerateLandTitleEvent(SburbConnection connection)
	{
		super(connection);
	}

	public void setLandTitle(TitleLandAspect title)
	{
		this.title = title;
	}

	public TitleLandAspect getLandTitle()
	{
		return title;
	}
}
