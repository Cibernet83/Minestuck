package com.mraof.minestuck.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mraof.minestuck.world.MinestuckDimensionHandler;
import com.mraof.minestuck.world.lands.LandAspects;
import com.mraof.minestuck.world.lands.gen.ChunkProviderLands;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandCheckLand
{
	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
		dispatcher.register(Commands.literal("checkland").executes(context -> execute(context.getSource())));
	}
	
	private static int execute(CommandSource source) throws CommandSyntaxException
	{
		EntityPlayerMP player = source.asPlayer();
		
		if(MinestuckDimensionHandler.isLandDimension(player.dimension))
		{
			LandAspects aspects = MinestuckDimensionHandler.getAspects(player.dimension);
			ChunkProviderLands chunkProvider = (ChunkProviderLands) player.world.dimension.createChunkGenerator();	//TODO Change name storage so that we don't have to go through the chunk generator
			ITextComponent aspect1 = new TextComponentTranslation("land."+aspects.aspectTerrain.getNames()[chunkProvider.nameIndex1]);
			ITextComponent aspect2 = new TextComponentTranslation("land."+aspects.aspectTitle.getNames()[chunkProvider.nameIndex2]);
			ITextComponent toSend;
			if(chunkProvider.nameOrder)
				toSend = new TextComponentTranslation("land.message.check", aspect1, aspect2);
			else toSend = new TextComponentTranslation("land.message.check", aspect2, aspect1);
			source.sendFeedback(toSend, false);
		}
		else
		{
			source.sendFeedback(new TextComponentTranslation("land.message.checkFail"), false);
		}
		return 1;
	}
}