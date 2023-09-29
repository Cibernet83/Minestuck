package com.mraof.minestuck.item;

import com.mraof.minestuck.alchemy.GristSet;
import com.mraof.minestuck.util.ColorCollector;
import net.minecraft.item.ItemStack;

public interface ICruxiteArtifact
{
	boolean isEntryArtifact();
	CruxiteArtifactTeleporter getTeleporter();

	default int getCruxiteColor(ItemStack stack)
	{
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("ColorID") && stack.getTagCompound().getInteger("ColorID") < ColorCollector.getColorSize() && stack.getTagCompound().getInteger("ColorID") >= 0)
			return ColorCollector.getColor(stack.getTagCompound().getInteger("ColorID"));
		else return 0x99D9EA;
	}

	ItemStack getStack(int colorIndex);
}
