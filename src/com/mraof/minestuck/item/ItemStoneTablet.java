package com.mraof.minestuck.item;

import java.util.List;

import javax.annotation.Nullable;

import com.mraof.minestuck.Minestuck;
import com.mraof.minestuck.client.gui.GuiHandler;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemStoneTablet extends Item
{
	public ItemStoneTablet()
	{
		setCreativeTab(TabMinestuck.instance);
		setMaxStackSize(1);
	}
	
	/**
     * Called when the equipped item is right clicked.
     */
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
    	ItemStack itemstack = playerIn.getHeldItem(handIn);
    	BlockPos pos = playerIn.getPosition();
		playerIn.openGui(Minestuck.instance, GuiHandler.GuiId.STONE_TABLET.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
    	NBTTagCompound nbt = stack.getTagCompound();
        if (nbt != null && nbt.hasKey("text") && nbt.getString("text") != "")
            tooltip.add(TextFormatting.GRAY + I18n.translateToLocal("item.stoneSlab.carved"));
    }
    
}
