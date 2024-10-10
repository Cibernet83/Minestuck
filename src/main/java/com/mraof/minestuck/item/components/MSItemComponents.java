package com.mraof.minestuck.item.components;

import com.mraof.minestuck.Minestuck;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MSItemComponents
{
	public static final DeferredRegister.DataComponents REGISTRY = DeferredRegister.createDataComponents(Minestuck.MOD_ID);
	
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<EncodedItemComponent>> ENCODED_ITEM = REGISTRY.register("encoded_item", () ->
			new DataComponentType.Builder<EncodedItemComponent>()
					.persistent(EncodedItemComponent.CODEC)
					.networkSynchronized(EncodedItemComponent.STREAM_CODEC)
					.build());
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<StoneTabletTextComponent>> STONE_TABLET_TEXT = REGISTRY.register("stone_tablet_text", () ->
			new DataComponentType.Builder<StoneTabletTextComponent>()
					.persistent(StoneTabletTextComponent.CODEC)
					.networkSynchronized(StoneTabletTextComponent.STREAM_CODEC)
					.build());
}
