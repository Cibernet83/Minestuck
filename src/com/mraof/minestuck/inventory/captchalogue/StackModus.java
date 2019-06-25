package com.mraof.minestuck.inventory.captchalogue;

import com.mraof.minestuck.MinestuckConfig;
import com.mraof.minestuck.client.gui.captchalouge.StackGuiHandler;
import com.mraof.minestuck.client.gui.captchalouge.SylladexGuiHandler;
import com.mraof.minestuck.item.MinestuckItems;
import com.mraof.minestuck.alchemy.AlchemyRecipes;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.LogicalSide;

import java.util.Iterator;
import java.util.LinkedList;

public class StackModus extends Modus
{
	
	protected int size;
	protected LinkedList<ItemStack> list;
	
	@OnlyIn(Dist.CLIENT)
	protected boolean changed;
	@OnlyIn(Dist.CLIENT)
	protected NonNullList<ItemStack> items;
	@OnlyIn(Dist.CLIENT)
	protected SylladexGuiHandler gui;
	
	public StackModus(LogicalSide side)
	{
		super(side);
	}
	
	@Override
	public ResourceLocation getRegistryName()
	{
		return CaptchaDeckHandler.STACK;
	}
	
	@Override
	public void initModus(EntityPlayerMP player, NonNullList<ItemStack> prev, int size)
	{
		this.size = size;
		list = new LinkedList<>();
		if(prev != null)
		{
			for(ItemStack stack : prev)
				if(!stack.isEmpty())
					list.add(stack);
		}
		
		if(player.world.isRemote)
		{
			items = NonNullList.create();
			changed = prev != null;
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		size = nbt.getInt("size");
		list = new LinkedList<>();
		
		for(int i = 0; i < size; i++)
			if(nbt.hasKey("item"+i))
				list.add(ItemStack.read(nbt.getCompound("item"+i)));
			else break;
		if(side == LogicalSide.CLIENT)
		{
			items = NonNullList.create();
			changed = true;
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{
		nbt.setInt("size", size);
		Iterator<ItemStack> iter = list.iterator();
		for(int i = 0; i < list.size(); i++)
		{
			ItemStack stack = iter.next();
			nbt.setTag("item"+i, stack.write(new NBTTagCompound()));
		}
		return nbt;
	}
	
	@Override
	public boolean putItemStack(EntityPlayerMP player, ItemStack item)
	{
		if(size == 0 || item.isEmpty())
			return false;
		
		ItemStack firstItem = list.size() > 0 ? list.getFirst() : ItemStack.EMPTY;
		if(firstItem.getItem() == item.getItem() && ItemStack.areItemStackTagsEqual(firstItem, item)
				&& firstItem.getCount() + item.getCount() <= firstItem.getMaxStackSize())
			firstItem.grow(item.getCount());
		else if(list.size() < size)
			list.addFirst(item);
		else
		{
			list.addFirst(item);
			CaptchaDeckHandler.launchItem(player, list.removeLast());
		}
		
		return true;
	}
	
	@Override
	public NonNullList<ItemStack> getItems()
	{
		if(side == LogicalSide.SERVER)	//Used only when replacing the modus
		{
			NonNullList<ItemStack> items = NonNullList.create();
			fillList(items);
			return items;
		}
		
		if(changed)
		{
			fillList(items);
		}
		return items;
	}
	
	protected void fillList(NonNullList<ItemStack> items)
	{
		items.clear();
		Iterator<ItemStack> iter = list.iterator();
		for(int i = 0; i < size; i++)
			if(iter.hasNext())
				items.add(iter.next());
			else items.add(ItemStack.EMPTY);
	}
	
	@Override
	public boolean increaseSize(EntityPlayerMP player)
	{
		if(MinestuckConfig.modusMaxSize > 0 && size >= MinestuckConfig.modusMaxSize)
			return false;
		
		size++;
		
		return true;
	}

	@Override
	public ItemStack getItem(EntityPlayerMP player, int id, boolean asCard)
	{
		if(id == CaptchaDeckHandler.EMPTY_CARD)
		{
			if(list.size() < size)
			{
				size--;
				return new ItemStack(MinestuckItems.CAPTCHA_CARD);
			} else return ItemStack.EMPTY;
		}
		
		if(list.isEmpty())
			return ItemStack.EMPTY;
		
		if(id == CaptchaDeckHandler.EMPTY_SYLLADEX)
		{
			for(ItemStack item : list)
				CaptchaDeckHandler.launchAnyItem(player, item);
			list.clear();
			return ItemStack.EMPTY;
		}
		
		if(asCard)
		{
			size--;
			return AlchemyRecipes.createCard(list.removeFirst(), false);
		}
		else return list.removeFirst();
	}

	@Override
	public boolean canSwitchFrom(Modus modus)
	{
		return modus instanceof StackModus;
	}
	
	@Override
	public int getSize()
	{
		return size;
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public SylladexGuiHandler getGuiHandler()
	{
		if(gui == null)
			gui = new StackGuiHandler(this);
		return gui;
	}
	
}