package com.mraof.minestuck.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.mraof.minestuck.editmode.ServerEditHandler;
import com.sun.org.apache.xpath.internal.operations.Bool;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListOpsEntry;
import net.minecraft.world.GameType;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.common.ForgeConfigSpec.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber
public class MinestuckConfig
{
    private static final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec config;
    //private static final ForgeConfigSpec.Builder server_builder = new ForgeConfigSpec.Builder();
    //private static final ForgeConfigSpec server_config;

    //Client Side Configs
    @OnlyIn(Dist.CLIENT)
    public static int clientOverworldEditRange;
    @OnlyIn(Dist.CLIENT)
    public static int clientLandEditRange;
    @OnlyIn(Dist.CLIENT)
    public static int clientCardCost;
    @OnlyIn(Dist.CLIENT)
    public static int clientAlchemiterStacks;
    @OnlyIn(Dist.CLIENT)
    public static byte clientTreeAutobalance;
    @OnlyIn(Dist.CLIENT)
    public static byte clientHashmapChat;
    @OnlyIn(Dist.CLIENT)
    public static byte echeladderAnimation;
    @OnlyIn(Dist.CLIENT)
    public static boolean clientGiveItems;
    @OnlyIn(Dist.CLIENT)
    public static boolean clientDisableGristWidget;
    @OnlyIn(Dist.CLIENT)
    public static boolean clientHardMode;
    @OnlyIn(Dist.CLIENT)
    public static boolean oldItemModels;
    @OnlyIn(Dist.CLIENT)
    public static boolean loginColorSelector;
    @OnlyIn(Dist.CLIENT)
    public static boolean dataCheckerAccess;
    @OnlyIn(Dist.CLIENT)
    public static boolean alchemyIcons;
    @OnlyIn(Dist.CLIENT)
    public static boolean preEntryEcheladder;

    //Ores
    public static BooleanValue generateUraniumOre;
    public static BooleanValue generateCruxiteOre;
    public static BooleanValue vanillaOreDrop;
    public static IntValue oreMultiplier;

    //Connections
    public static BooleanValue privateComputers;
    public static BooleanValue globalSession;
    public static BooleanValue allowSecondaryConnections;
    public static BooleanValue skaianetCheck;
    /**
     * 0: Make the player's new server player his/her old server player's server player
     * 1: The player that lost his/her server player will have an idle main connection until someone without a client player connects to him/her.
     * (Will try to put a better explanation somewhere else later)
     */
    public static IntValue escapeFailureMode;

    //Entry
    public static BooleanValue entryCrater;
    public static BooleanValue adaptEntryBlockHeight;
    public static BooleanValue needComputer;
    public static BooleanValue stopSecondEntry;
    public static IntValue artifactRange;

    //Edit Mode
    public static BooleanValue giveItems;
    public static BooleanValue gristRefund;;
    public static BooleanValue deployCard;
    public static BooleanValue portableMachines;
    public static BooleanValue deployGristWidget;
    public static IntValue overworldEditRange;
    public static IntValue landEditRange;
    public static boolean[] deployConfigurations = new boolean[2];

    //Machines
    public static BooleanValue cruxtruderIntake;
    public static BooleanValue disableGristWidget;
    public static IntValue alchemiterMaxStacks;

    //Mechanics
    public static BooleanValue aspectEffects;
    public static BooleanValue playerSelectedTitle;
    public static BooleanValue showGristChanges;
    public static BooleanValue echeladderProgress;
    public static IntValue preEntryRungLimit;


    //Sylladex & Captchalogue Cards
    public static BooleanValue dropItemsInCards;
    public static IntValue initialModusSize;
    public static IntValue modusMaxSize;
    public static IntValue cardCost;
    public static String[] defaultModusTypes;
    public static ConfigValue<String[]> cfgDefaultModusTypes;
    public static byte treeModusSetting;
    public static ConfigValue<String> cfgTreeModusSetting;
    public static byte hashmapChatModusSetting;
    public static ConfigValue<String> cfgHashmapChatModusSetting;
    public static byte sylladexDropMode;
    public static ConfigValue<String> cfgSylladexDropMode;

    //Misc
    public static BooleanValue hardMode;
    public static boolean forceMaxSize = true;
    public static BooleanValue useUUID;
    public static BooleanValue canBreakGates;
    public static BooleanValue disableGiclops;
    public static byte dataCheckerPermission;
    public static ConfigValue<String> cfgDataCheckerPermission;
    public static DimensionType[] forbiddenDimensionsTpz = new DimensionType[0];
    public static ConfigValue<Integer[]> cfgForbiddenDimensionsTpz;

    //Secret configuration options
    public static boolean secretConfig = false;
    public static boolean disableCruxite = false;
    public static boolean disableUranium = false;
    public static int cruxiteVeinsPerChunk = 10;
    public static int uraniumVeinsPerChunk = 10;
    public static int baseCruxiteVeinSize = 6;
    public static int baseUraniumVeinSize = 3;
    public static int bonusCruxiteVeinSize = 3;
    public static int bonusUraniumVeinSize = 3;
    public static int cruxiteStratumMin = 0;
    public static int uraniumStratumMin = 0;
    public static int cruxiteStratumMax = 60;
    public static int uraniumStratumMax = 30;

    static
    {
        builder.comment("Ores");
        generateCruxiteOre = builder.comment("If cruxite ore should be generated in the overworld.")
                .define("ores.generateCruxiteOre", true);
        generateUraniumOre = builder.comment("If uranium ore should be generated in the overworld.")
                .define("ores.generateUraniumOre", true);
        vanillaOreDrop = builder.comment("If this is true, the custom vanilla ores will drop the standard vanilla ores when mined, instead of the custom type.")
                .define("ores.vanillaOreDrop", false);
        oreMultiplier = builder.comment("Multiplies the cost for the 'contents' of an ore. Set to 0 to disable alchemizing ores.")
                .defineInRange("ores.oreMultiplier", 1, 0, 1000);

        builder.comment("Connections");
        privateComputers = builder.comment("True if computers should only be able to be used by the owner.")
                .define("connections.privateComputers", true);
        globalSession = builder.comment("Whenether all connetions should be put into a single session or not.")
                .define("connections.globalSession", false);
        allowSecondaryConnections = builder.comment("Set this to true to allow so-called 'secondary connections' to be created.")
                .define("connections.secondaryConnections", true);

        builder.comment("Entry");
        adaptEntryBlockHeight = builder.comment("Adapt the transferred height to make the top non-air block to be placed at y:128. Makes entry take slightly longer.")
                .define("entry.adaptEntryBlockHeight", true);
        needComputer = builder.comment("If this is true, players need to have a computer nearby to Enter")
                .define("entry.needComputer", false);
        stopSecondEntry = builder.comment("If this is true, players may only use an artifact once, even if they end up in the overworld again.")
                .define("entry.stopSecondEntry", true);
        artifactRange = builder.comment("Radius of the land brought into the medium.")
                .defineInRange("entry.artifactRange", 30, 0, 1000);

        builder.comment("Edit Mode");
        giveItems = builder.comment("Setting this to true replaces editmode with the old Give Items button.")
                .define("editMode.giveItems", false);
        gristRefund = builder.comment("Enable this and players will get a (full) grist refund from breaking blocks in edit mode.")
                .define("editMode.gristRefund", false);
        deployCard = builder.comment("Determines if a card with a captcha card punched on it should be added to the deploy list or not.")
                .define("editMode.deployCard", false);
        portableMachines = builder.comment("Determines if the small portable machines should be included in the deploy list.")
                .define("editMode.portableMachines", false);
        //deployGristWidget = builder.comment("Determines if the GristWidget 12000 should be included in the deploy list.").define("editMode.deployGristWidget", false);
        overworldEditRange = builder.comment("A number that determines how far away from the computer an editmode player may be before entry.")
                .defineInRange("editMode.overworldEditRange", 15, 0, 1000);
        landEditRange = builder.comment("A number that determines how far away from the center of the brought land that an editmode player may be after entry.")
                .defineInRange("editMode.landEditRange", 30, 0, 1000);

        builder.comment("Machines");
        cruxtruderIntake = builder.comment("If enabled, the regular cruxtruder will require raw cruxite to function, which is inserted through the pipe.")
                .define("machines.cruxtruderIntake", false);
        disableGristWidget = builder.comment("If you want to disable grist widgets because of exploits caused by other mods, you can do it here.")
                .define("machines.disableGristWidget", false);
        alchemiterMaxStacks = builder.comment("The number of stacks that can be alchemized at the same time with the alchemiter.")
                .defineInRange("machines.alchemiterMaxStacks", 16, 0, 999);

        builder.comment("Mechanics");
        aspectEffects = builder.comment("If this is true, players will gain certain potion effects once they reach a certain rung based on their aspect.")
                .define("mechanics.aspectEffects", true);
        playerSelectedTitle = builder.comment("Enable this to let players select their own title. They will however not be able to select the Lord or Muse as class.")
                .define("mechanics.playerSelectedTitle", false);
        showGristChanges = builder.comment("If this is true, grist change messages will appear")
                .define("mechanics.showGristChanges", true);
        showGristChanges = builder.comment("If this is true, players will be able to see their progress towards the next rung. This is server side and will only be active in multiplayer if the server/Lan host has it activated.")
                .define("mechanics.showGristChanges", false);
        preEntryRungLimit = builder.comment("The highest rung you can get before entering medium. Note that the first rung is indexed as 0, the second as 1 and so on.")
                .defineInRange("machines.preEntryRungLimit", 6, 0, 49);

        builder.comment("Sylladex & Captchalogue Cards");
        dropItemsInCards = builder.comment("When sylladexes are droppable, this option determines if items should be dropped inside of cards or items and cards as different stacks.")
                .define("sylladex.dropItemsInCards", true);
        initialModusSize = builder.comment("When a player is assigned a modus for the first time, that modus will have the size of this value.")
                .defineInRange("sylladex.initialModusSize", 5, 0, 1000);
        modusMaxSize = builder.comment("The max size on a modus. Ignored if the value is 0.")
                .defineInRange("sylladex.modusMaxSize", 0, 0, 1000);
        cardCost = builder.comment("An integer that determines how much a captchalogue card costs to alchemize")
                .defineInRange("sylladex.cardCost", 1, 0, Integer.MAX_VALUE);
        cfgDefaultModusTypes = builder.comment("An array with the possible modus types to be assigned. Written with mod-id and modus name, for example \"minestuck:queue_stack\" or \"minestuck:hashmap\"")
                .define("sylladex.defaultModusTypes", new String[]{"minestuck:stack","minestuck:queue"});
        cfgTreeModusSetting = builder.comment("This determines if auto-balance should be forced. 'both' if the player should choose, 'on' if forced at on, and 'off' if forced at off.")
                .defineInList("sylladex.treeModusSetting", "both", new ArrayList<String>(){{add("both"); add("on"); add("off");}});
        cfgHashmapChatModusSetting = builder.comment("This determines if hashmap chat ejection should be forced. 'both' if the player should choose, 'on' if forced at on, and 'off' if forced at off.")
                .defineInList("sylladex.forceEjectByChat", "both", new ArrayList<String>(){{add("both"); add("on"); add("off");}});
        cfgSylladexDropMode = builder.comment("Determines which items from the modus that are dropped on death. \\\"items\\\": Only the items are dropped. \\\"cardsAndItems\\\": Both items and cards are dropped. (So that you have at most initialModusSize amount of cards) \\\"all\\\": Everything is dropped, even the modus.")
                .defineInList("sylladex.cardsAndItems", "both", new ArrayList<String>(){{add("items"); add("cardsAndItems"); add("all");}});

        builder.comment("Misc.");
        hardMode = builder.define("misc.hardMode", false);
        useUUID = builder.comment("If this is set to true, minestuck will use uuids to refer to players in the saved data. On false it will instead use the old method based on usernames.")
                .define("misc.uuidIdentification", true);
        canBreakGates = builder.comment("Lets gates be destroyed by explosions. Turning this off will make gates use the same explosion resistance as bedrock.")
                .define("misc.canBreakGates", true);
        disableGiclops = builder.comment("Right now, the giclops pathfinding is currently causing huge amounts of lag due to their size. This option is a short-term solution that will disable giclops spawning and remove all existing giclopes.")
                .define("misc.disableGiclops", true);
        cfgDataCheckerPermission = builder.comment("Determines who's allowed to access the data checker. \"none\": No one is allowed. \"ops\": only those with a command permission of level 2 or more may access the data ckecker. (for single player, that would be if cheats are turned on) \"gamemode\": Only players with the creative " +
                "or spectator gamemode may view the data checker. \"opsAndGamemode\": Combination of \"ops\" and \"gamemode\". \"anyone\": No access restrictions are used.")
                .defineInList("misc.dataCheckerPermission", "", new ArrayList<String>(){{add("none"); add("ops"); add("opsAndGamemode"); add("anyone");}});
        cfgForbiddenDimensionsTpz = builder.comment("A list of dimension id's that you cannot travel to or from using transportalizers.")
                .define("misc.forbiddenDimensionsTpz", new Integer[]{});

        config = builder.build();
        System.out.println("Configs Built");
    }

    public static void loadConfig()
    {
        String path = FMLPaths.CONFIGDIR.get().resolve("Minestuck.toml").toString();
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();

    }

    public static void initConfigVariables()
    {
        deployConfigurations = new boolean[]
                {
                        deployCard.get(),
                        portableMachines.get(),
                        //deployGristWidget.get()
                };
        defaultModusTypes = cfgDefaultModusTypes.get();
        treeModusSetting = (byte) (cfgTreeModusSetting.get().equals("both") ? 0 : cfgTreeModusSetting.get().equals("on") ? 1 : 2);
        hashmapChatModusSetting = (byte) (cfgHashmapChatModusSetting.get().equals("both") ? 0 : cfgTreeModusSetting.get().equals("on") ? 1 : 2);
        sylladexDropMode = (byte) (cfgSylladexDropMode.get().equals("items") ? 0 : cfgTreeModusSetting.get().equals("cardsAndItems") ? 1 : 2);
        dataCheckerPermission = (byte) (cfgDataCheckerPermission.get().equals("none") ? 0 : cfgDataCheckerPermission.get().equals("ops") ? 1 :  cfgDataCheckerPermission.get().equals("gamemode") ? 2 :  cfgDataCheckerPermission.get().equals("opsAndGamemode") ? 3 : 4);

        Integer[] fdt = cfgForbiddenDimensionsTpz.get();
        forbiddenDimensionsTpz = new DimensionType[fdt.length];
        for(int i = 0; i < forbiddenDimensionsTpz.length; i++)
            forbiddenDimensionsTpz[i] = DimensionType.getById(fdt[i]);
    }

    public static boolean getDataCheckerPermissionFor(EntityPlayerMP player)
    {
        if((dataCheckerPermission & 3) != 0)
        {
            if((dataCheckerPermission & 1) != 0)
            {
                MinecraftServer server = player.getServer();
                if (server.getPlayerList().canSendCommands(player.getGameProfile()))
                {
                    UserListOpsEntry userlistopsentry = server.getPlayerList().getOppedPlayers().getEntry(player.getGameProfile());
                    if((userlistopsentry != null ? userlistopsentry.getPermissionLevel() : server.getOpPermissionLevel()) >= 2)
                        return true;
                }
            }
            if((dataCheckerPermission & 2) != 0)
            {
                GameType gameType = player.interactionManager.getGameType();
                if(ServerEditHandler.getData(player) != null)
                    gameType = ServerEditHandler.getData(player).getDecoy().gameType;
                if(!gameType.isSurvivalOrAdventure())
                    return true;
            }
            return false;
        } else return dataCheckerPermission != 0;
    }


}
