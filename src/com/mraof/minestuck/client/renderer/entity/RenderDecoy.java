package com.mraof.minestuck.client.renderer.entity;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerArrow;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;

import com.mraof.minestuck.entity.EntityDecoy;

public class RenderDecoy extends RenderLivingBase<EntityDecoy>
{

	private static final ModelPlayer MODEL = new ModelPlayer(0F, false);
	private static final ModelPlayer MODEL_SLIM = new ModelPlayer(0F, true);

	public RenderDecoy(RenderManager manager)
	{
		super(manager, MODEL, 0F);
		this.addLayer(new LayerBipedArmor(this));
		this.addLayer(new LayerHeldItem(this));
		this.addLayer(new LayerArrow(this));
	}

	@Override
	public void doRender(EntityDecoy entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		mainModel = (entity.uuid != null) && (entity.world.getPlayerEntityByUUID(entity.uuid) instanceof AbstractClientPlayer &&
				((AbstractClientPlayer) entity.world.getPlayerEntityByUUID(entity.uuid)).getSkinType().equals("slim")) ? MODEL_SLIM : MODEL;
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityDecoy entity)
	{
		return entity.getLocationSkin();
	}
}