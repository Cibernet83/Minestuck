package com.mraof.minestuck.world.storage.loot.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import com.mraof.minestuck.Minestuck;
import com.mraof.minestuck.network.skaianet.SburbConnection;
import com.mraof.minestuck.network.skaianet.SburbHandler;
import com.mraof.minestuck.network.skaianet.SkaianetHandler;
import com.mraof.minestuck.util.EnumAspect;
import com.mraof.minestuck.util.EnumClass;
import com.mraof.minestuck.util.IdentifierHandler.PlayerIdentifier;
import com.mraof.minestuck.util.MinestuckPlayerData;
import com.mraof.minestuck.world.MinestuckDimensionHandler;
import com.mraof.minestuck.world.lands.ILandAspect;
import com.mraof.minestuck.world.lands.LandAspectRegistry;
import com.mraof.minestuck.world.lands.LandAspectRegistry.AspectCombination;
import com.mraof.minestuck.world.lands.gen.ChunkProviderLands;
import com.mraof.minestuck.world.lands.terrain.TerrainLandAspect;
import com.mraof.minestuck.world.lands.title.TitleLandAspect;

import static net.minecraft.client.resources.I18n.format;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;

public class SetTabletText extends LootFunction
{	
	private HashMap<String, String[][]> pools = new HashMap() {{
		put("general", new String[][] 
		{
			{"shining"},
			{"determined"},
			{"hidden"},
			{"idea"},
			{"groceries"},
			{"inspirational"},
			{"game"},
		});
		put("sand_red", new String[][]
		{
			
		});
	}};
	
	
	protected SetTabletText(LootCondition[] conditionsIn) {super(conditionsIn);}

	@Override
	public ItemStack apply(ItemStack stack, Random rand, LootContext context) 
	{
		WorldServer world = context.getWorld();
		ArrayList<String[]> pool = new ArrayList<>();
		
		
		AspectCombination aspects = MinestuckDimensionHandler.getAspects(world.provider.getDimension());
		String landTitle = aspects.aspectTitle.getPrimaryName();
		String landTerrain = aspects.aspectTerrain.getPrimaryName();
		
		//pool.addAll(Arrays.asList(pools.get("general")));
		if(pools.containsKey(landTerrain)) pool.addAll(Arrays.asList(pools.get(landTerrain)));
		if(pools.containsKey(landTitle)) pool.addAll(Arrays.asList(pools.get(landTitle)));
		
		String[] item = pool.get(rand.nextInt(pool.size()));
		
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString("text", formatText(world, item));
		stack.setTagCompound(nbt);
		return stack;
	}
	
	public static String formatText(WorldServer worldIn, String... args)
	{
		int dim = worldIn.provider.getDimension();
		SburbConnection c = SburbHandler.getConnectionForDimension(dim);
		PlayerIdentifier player = c.getClientIdentifier();
		String consortName = MinestuckDimensionHandler.getAspects(dim).aspectTerrain.getConsortType().getName();
		String[] format = new String[args.length-1];
		for(int i = 1; i <= format.length; i++)
		{
			switch(args[i])
			{
			case "playerName": format[i-1] = player.getUsername(); break;
			case "playerClasspect": format[i-1] = MinestuckPlayerData.getTitle(player).getTitleName(); break;
			case "playerClass": format[i-1] = MinestuckPlayerData.getTitle(player).getHeroClass().getDisplayName(); break;
			case "playerAspect": format[i-1] = MinestuckPlayerData.getTitle(player).getHeroAspect().getDisplayName(); break;
			
			case "landName": format[i-1] = MinestuckDimensionHandler.getLandName(worldIn); break;
			case "landTitle": format[i-1] = I18n.translateToLocal(MinestuckDimensionHandler.getLandTitleName(worldIn)); break;
			case "landTerrain": format[i-1] = I18n.translateToLocal(MinestuckDimensionHandler.getLandTerrainName(worldIn)); break;
			
			case "denizen": format[i-1] = I18n.translateToLocal("denizen." + MinestuckPlayerData.getData(player).title.getHeroAspect().toString() + ".name"); break;
			case "consort": format[i-1] = consortName; break;
			case "consortSound": format[i-1] = I18n.translateToLocal("consort.sound.minestuck."+consortName); break;
			case "consortSound2": format[i-1] = I18n.translateToLocal("consort.sound2.minestuck."+consortName); break;
			default: format[i-1] = args[i];  break;
			
			}
			
		}
		return I18n.translateToLocal(String.format(I18n.translateToLocal("tablet."+args[0]), format));
	}
	
	
	public static class Serializer extends LootFunction.Serializer<SetTabletText>
	{

		public Serializer() {super(new ResourceLocation(Minestuck.MOD_ID,"set_tablet_text"), SetTabletText.class);}

		@Override
		public void serialize(JsonObject json, SetTabletText value,
				JsonSerializationContext serializationContext) {}

		@Override
		public SetTabletText deserialize(JsonObject json, JsonDeserializationContext context,
				LootCondition[] conditionsIn) {return new SetTabletText(conditionsIn);}
		
		private static ILandAspect getAspect(String aspectName)
		{
			if(aspectName == "") return null;
			ILandAspect aspect = LandAspectRegistry.fromNameTerrain(aspectName);
			if(aspect == null)
				aspect = LandAspectRegistry.fromNameTitle(aspectName);
			if(aspect == null)
				throw new JsonSyntaxException("\"" + aspectName + "\" is not a valid land aspect.");
			return aspect;
		}
	}
}
