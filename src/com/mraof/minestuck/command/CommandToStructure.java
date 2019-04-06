package com.mraof.minestuck.command;

import java.util.List;

import com.mraof.minestuck.world.WorldProviderLands;

import joptsimple.ArgumentAcceptingOptionSpec;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

/**
 * Created by kirderf1 for debugging purposes
 */
public class CommandToStructure extends CommandBase
{
	@Override
	public String getName()
	{
		return "tpStruct";
	}
	
	@Override
	public String getUsage(ICommandSender sender)
	{
		return "commands.tpStruct.usage";
	}
	
	@Override
	public int getRequiredPermissionLevel()
	{
		return 2;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		if(args.length < 1)
			throw new WrongUsageException(this.getUsage(sender));
		
		EntityPlayerMP playerMP = CommandBase.getCommandSenderAsPlayer(sender);
		
		if(!playerMP.getEntityData().hasKey(EntityPlayer.PERSISTED_NBT_TAG))
			playerMP.getEntityData().setTag(EntityPlayer.PERSISTED_NBT_TAG, new NBTTagCompound());
		if(!playerMP.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).hasKey("commandVisitedStructures"))
			playerMP.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).setTag("commandVisitedStructures", new NBTTagList());
		
		if(playerMP.world.provider instanceof WorldProviderLands)
		{
			BlockPos location = ((WorldProviderLands) playerMP.world.provider).findAndMarkNextStructure(playerMP, args[0], playerMP.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG).getTagList("commandVisitedStructures", 4));
			if(location != null)
				playerMP.setPositionAndUpdate(location.getX(), location.getY(), location.getZ());
			else if(WorldProviderLands.getStructureNamesList().contains(args[0].toLowerCase()))
				throw new CommandException("Could not find a" + (args[0].toLowerCase().charAt(0) == 'a' ? "n " : " ") + args[0].toLowerCase() + " structure, try to generate some chunks by walking around.");
			else	throw new CommandException(args[0].toLowerCase()  + " is either an invalid structure or currently unsupported by this command.");
				
			
		}
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) 
	{
		if(args.length == 1)
			return WorldProviderLands.getStructureNamesList();
		else
		return super.getTabCompletions(server, sender, args, targetPos);
	}
}
