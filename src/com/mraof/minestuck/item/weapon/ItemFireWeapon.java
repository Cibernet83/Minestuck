package com.mraof.minestuck.item.weapon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;

/**
 * Created by mraof on 2017 January 18 at 6:24 PM.
 */
public class ItemFireWeapon extends ItemWeapon
{
	private final int duration;
	
	public ItemFireWeapon(IItemTier tier, int attackDamageIn, float attackSpeedIn, float efficiency, int duration, Properties builder)
	{
		super(tier, attackDamageIn, attackSpeedIn, efficiency, builder);
		this.duration = duration;
	}

	@Override
	public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase player)
	{
		target.setFire(duration);
		return super.hitEntity(itemStack, target, player);
	}
}