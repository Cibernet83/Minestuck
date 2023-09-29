package com.mraof.minestuck.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class ItemCruxiteFood extends Item implements ICruxiteArtifact
{
	private final EnumAction action;
	private final CruxiteArtifactTeleporter teleporter;

	public ItemCruxiteFood(EnumAction action)
	{
		this.setCreativeTab(TabMinestuck.instance);
		this.action = action;
		setUnlocalizedName("cruxiteArtifact");
		this.maxStackSize = 1;
		setHasSubtypes(true);

		teleporter = new CruxiteArtifactTeleporter();
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
	{
		if (isEntryArtifact())
			super.getSubItems(tab, items);
	}

	@Override
	public boolean isEntryArtifact()
	{
		return teleporter != null;
	}

	@Override
	public CruxiteArtifactTeleporter getTeleporter()
	{
		return teleporter;
	}

	@Override
	public ItemStack getStack(int colorIndex) {
		return new ItemStack(this, 1, colorIndex+1);
	}

	public EnumAction getItemUseAction(ItemStack stack)
	{
		return action;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
	{
		stack.shrink(1);
		if(entityLiving instanceof EntityPlayer)
			teleporter.onArtifactActivated((EntityPlayer) entityLiving);

		return stack;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		playerIn.setActiveHand(handIn);
		return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}
}