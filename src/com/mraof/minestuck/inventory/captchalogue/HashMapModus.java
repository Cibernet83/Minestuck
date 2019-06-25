package com.mraof.minestuck.inventory.captchalogue;

import com.mraof.minestuck.MinestuckConfig;
import com.mraof.minestuck.client.gui.captchalouge.HashmapGuiHandler;
import com.mraof.minestuck.client.gui.captchalouge.SylladexGuiHandler;
import com.mraof.minestuck.item.MinestuckItems;
import com.mraof.minestuck.network.CaptchaDeckPacket;
import com.mraof.minestuck.network.MinestuckPacketHandler;
import com.mraof.minestuck.alchemy.AlchemyRecipes;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.LogicalSide;

import java.util.Iterator;

public class HashMapModus extends Modus
{
	
	protected NonNullList<ItemStack> list;
	public boolean ejectByChat = true;
	
	@OnlyIn(Dist.CLIENT)
	protected boolean changed;
	@OnlyIn(Dist.CLIENT)
	protected NonNullList<ItemStack> items;
	@OnlyIn(Dist.CLIENT)
	protected SylladexGuiHandler gui;
	
	public HashMapModus(LogicalSide side)
	{
		super(side);
	}
	
	@Override
	public ResourceLocation getRegistryName()
	{
		return CaptchaDeckHandler.HASH_MAP;
	}
	
	@Override
	public void initModus(EntityPlayerMP player, NonNullList<ItemStack> prev, int size)
	{
		list = NonNullList.create();
		if(prev != null)
		{
			list.addAll(prev);
		}
		while(list.size() < size)
			list.add(ItemStack.EMPTY);
		
		if(side == LogicalSide.CLIENT)
		{
			items = NonNullList.create();
			changed = true;
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		int size = nbt.getInt("size");
		ejectByChat = nbt.getBoolean("ejectByChat");
		list = NonNullList.create();
		
		for(int i = 0; i < size; i++)
			if(nbt.hasKey("item"+i))
				list.add(ItemStack.read(nbt.getCompound("item"+i)));
			else list.add(ItemStack.EMPTY);
		if(side == LogicalSide.CLIENT)
		{
			if(items == null)
				items = NonNullList.create();
			changed = true;
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{
		nbt.setInt("size", list.size());
		nbt.setBoolean("ejectByChat", ejectByChat);
		Iterator<ItemStack> iterator = list.iterator();
		for(int i = 0; i < list.size(); i++)
		{
			ItemStack stack = iterator.next();
			if(!stack.isEmpty())
				nbt.setTag("item"+i, stack.write(new NBTTagCompound()));
		}
		return nbt;
	}
	
	@Override
	public boolean putItemStack(EntityPlayerMP player, ItemStack item)
	{
		if(list.size() == 0 || item.isEmpty())
			return false;
		
		//TODO use registry names when 1.13 comes out
		
		String unloc = item.getTranslationKey();
		unloc = unloc.substring(unloc.indexOf('.')+1, unloc.length());

		if(unloc.indexOf('.') != -1)
		{
			String adj = unloc.substring(unloc.indexOf('.')+1, unloc.length());
			unloc = adj + " " + unloc.substring(0, unloc.indexOf('.'));
		}
		
		int index = ((item.hasDisplayName()) ? item.getDisplayName() : unloc).hashCode() % list.size();
				
		if(index < 0)
			index += list.size();
		
		if(!list.get(index).isEmpty())
		{
			ItemStack otherItem = list.get(index);
			if(otherItem.getItem() == item.getItem() && ItemStack.areItemStackTagsEqual(otherItem, item)
					&& otherItem.getCount() + item.getCount() <= otherItem.getMaxStackSize())
			{
				otherItem.grow(item.getCount());
				return true;
			} else CaptchaDeckHandler.launchItem(player, list.get(index));
		}
		
		list.set(index, item);
		
		if(ejectByChat && MinestuckConfig.hashmapChatModusSetting != 2 || MinestuckConfig.hashmapChatModusSetting == 1)
			player.sendMessage(new TextComponentTranslation("message.hash_map", item.getTextComponent(), getSize(), index));
		
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
		list.addAll(items);
	}
	
	@Override
	public boolean increaseSize(EntityPlayerMP player)
	{
		if(MinestuckConfig.modusMaxSize > 0 && list.size() >= MinestuckConfig.modusMaxSize)
			return false;
		
		list.add(ItemStack.EMPTY);
		return true;
	}
	
	@Override
	public ItemStack getItem(EntityPlayerMP player, int id, boolean asCard)
	{
		if(id == CaptchaDeckHandler.EMPTY_CARD)
		{
			return ItemStack.EMPTY;	//Empty card is retrieved the same way as a normal item
		}
		
		if(list.isEmpty())
			return ItemStack.EMPTY;
		
		if(id == CaptchaDeckHandler.EMPTY_SYLLADEX)
		{
			for(int i = 0; i < list.size(); i++)
				if(!list.get(i).isEmpty())
				{
					CaptchaDeckHandler.launchAnyItem(player, list.get(i));
					list.set(i, ItemStack.EMPTY);
				}
			return ItemStack.EMPTY;
		}
		
		id = id % list.size();
		
		ItemStack item = list.get(id);
		
		if(asCard)
		{
			list.remove(id);
			if(item.isEmpty())
				return new ItemStack(MinestuckItems.CAPTCHA_CARD);
			else return AlchemyRecipes.createCard(item, false);
		} else
		{
			list.set(id, ItemStack.EMPTY);
			return item;
		}
	}
	
	@Override
	public boolean canSwitchFrom(Modus modus)
	{
		return false;
	}
	
	@Override
	public int getSize()
	{
		return list.size();
	}
	
	@Override
	public void setValue(EntityPlayerMP player, byte type, int value)
	{
		ejectByChat = value > 0;
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public SylladexGuiHandler getGuiHandler()
	{
		if(gui == null)
			gui = new HashmapGuiHandler(this);
		return gui;
	}
	
	public void onChatMessage(EntityPlayerMP player, String str)
	{
		if(!ejectByChat && MinestuckConfig.hashmapChatModusSetting != 1 || MinestuckConfig.hashmapChatModusSetting == 2)
			return;
		
		boolean isPrevLetter = false;
		StringBuilder number = new StringBuilder();
		for(int i = 0; i < str.length(); i++)
		{
			char c = str.charAt(i);
			if(Character.isLetter(c))
				isPrevLetter = true;
			else if(Character.isDigit(c) || (number.length() == 0 && c == '-'))
			{
				if(!isPrevLetter)
					number.append(c);
				continue;
			} else
			{
				isPrevLetter = false;
				
				if(number.length() > 0)
					handleNumber(player, number.toString());
			}
			
			number = new StringBuilder();
		}
		
		if(number.length() > 0)
			handleNumber(player, number.toString());
		
		CaptchaDeckPacket packet = CaptchaDeckPacket.data(CaptchaDeckHandler.writeToNBT(this));
		MinestuckPacketHandler.sendToPlayer(packet, player);
		
	}
	
	private void handleNumber(EntityPlayerMP player, String str)
	{
		int i;
		
		try
		{
			i = Integer.parseInt(str);
		} catch(NumberFormatException e) {return;}
		
		int index = i % getSize();
		if(index < 0)
			index += getSize();
		
		ItemStack stack = getItem(player, index, false);
		if(stack == null)
			return;
		
		if(player.inventory.getCurrentItem().isEmpty())
			player.inventory.setInventorySlotContents(player.inventory.currentItem, stack);
		else CaptchaDeckHandler.launchAnyItem(player, stack);
		
		player.sendMessage(new TextComponentTranslation("message.hash_map", i, getSize(), index, stack.getTextComponent()));
	}
	
}