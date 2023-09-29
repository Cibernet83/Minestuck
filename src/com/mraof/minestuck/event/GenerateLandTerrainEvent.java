package com.mraof.minestuck.event;

import com.mraof.minestuck.network.skaianet.SburbConnection;
import com.mraof.minestuck.world.lands.terrain.TerrainLandAspect;

public class GenerateLandTerrainEvent extends GenerateLandAspectEvent
{
	private TerrainLandAspect terrain;

	public GenerateLandTerrainEvent(SburbConnection connection)
	{
		super(connection);
	}

	public void setLandTerrain(TerrainLandAspect terrain)
	{
		this.terrain = terrain;
	}

	public TerrainLandAspect getLandTerrain()
	{
		return terrain;
	}
}
