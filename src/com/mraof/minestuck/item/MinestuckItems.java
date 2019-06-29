package com.mraof.minestuck.item;

import com.mraof.minestuck.Minestuck;
import com.mraof.minestuck.entity.item.EntityCrewPoster;
import com.mraof.minestuck.entity.item.EntityMetalBoat;
import com.mraof.minestuck.entity.item.EntitySbahjPoster;
import com.mraof.minestuck.entity.item.EntityShopPoster;
import com.mraof.minestuck.item.block.*;
import com.mraof.minestuck.item.weapon.*;
import com.mraof.minestuck.util.MinestuckSoundHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import static com.mraof.minestuck.block.MinestuckBlocks.*;

import java.util.Arrays;

/**
 * This class contains all non-ItemBlock items that minestuck adds,
 * and is responsible for initializing and registering these.
 */
public class MinestuckItems
{
	
	//hammers
	public static Item CLAW_HAMMER;
	public static Item SLEDGE_HAMMER;
	public static Item BLACKSMITH_HAMMER;
	public static Item POGO_HAMMER;
	public static Item TELESCOPIC_SASSACRUSHER;
	public static Item REGI_HAMMER;
	public static Item FEAR_NO_ANVIL;
	public static Item MELT_MASHER;
	public static Item Q_E_HAMMER_AXE;
	public static Item D_D_E_HAMMER_AXE;
	public static Item ZILLYHOO_HAMMER;
	public static Item POPAMATIC_VRILLYHOO;
	public static Item SCARLET_ZILLYHOO;
	public static Item MWRTHWL;
	//blades
	public static Item SORD;
	public static Item CACTUS_CUTLASS;
	public static Item STEAK_SWORD;
	public static Item BEEF_SWORD;
	public static Item IRRADIATED_STEAK_SWORD;
	public static Item KATANA;
	public static Item UNBREAKABLE_KATANA;
	public static Item FIRE_POKER;
	public static Item HOT_HANDLE;
	public static Item CALEDSCRATCH;
	public static Item CALEDFWLCH;
	public static Item ROYAL_DERINGER;
	public static Item CLAYMORE;
	public static Item ZILLYWAIR_CUTLASS;
	public static Item REGISWORD;
	public static Item SCARLET_RIBBITAR;
	public static Item DOGG_MACHETE;
	public static Item COBALT_SABRE;
	public static Item QUAMTUM_SABRE;
	public static Item SHATTER_BEACON;
	//axes
	public static Item BATLEACKS;
	public static Item COPSE_CRUSHER;
	public static Item BATTLEAXE;
	public static Item BLACKSMITH_BANE;
	public static Item SCRAXE;
	public static Item Q_P_HAMMER_AXE;
	public static Item RUBY_CROAK;
	public static Item HEPHAESTUS_LUMBER;
	public static Item Q_F_HAMMER_AXE;
	//Dice
	public static Item DICE;
	public static Item FLUORITE_OCTET;
	//misc weapons
	public static Item CAT_CLAWS_DRAWN, CAT_CLAWS_SHEATHED;
	//sickles
	public static Item SICKLE;
	public static Item HOMES_SMELL_YA_LATER;
	public static Item FUDGE_SICKLE;
	public static Item REGI_SICKLE;
	public static Item CLAW_SICKLE;
	public static Item CLAW_OF_NRUBYIGLITH;
	public static Item CANDY_SICKLE;
	//clubs
	public static Item DEUCE_CLUB;
	public static Item NIGHT_CLUB;
	public static Item POGO_CLUB;
	public static Item METAL_BAT;
	public static Item SPIKED_CLUB;
	//canes
	public static Item CANE;
	public static Item IRON_CANE;
	public static Item SPEAR_CANE;
	public static Item PARADISES_PORTABELLO;
	public static Item REGI_CANE;
	public static Item DRAGON_CANE;
	public static Item POGO_CANE;
	//Spoons/forks
	public static Item WOODEN_SPOON;
	public static Item SILVER_SPOON;
	public static Item CROCKER_SPOON, CROCKER_FORK;
	public static Item SKAIA_FORK;
	public static Item FORK;
	public static Item SPORK;
	public static Item GOLDEN_SPORK;
	//Material tools
	public static Item EMERALD_SWORD;
	public static Item EMERALD_AXE;
	public static Item EMERALD_PICKAXE;
	public static Item EMERALD_SHOVEL;
	public static Item EMERALD_HOE;
	//Armor
	public static Item PRISMARINE_HELMET;
	public static Item PRISMARINE_CHESTPLATE;
	public static Item PRISMARINE_LEGGINGS;
	public static Item PRISMARINE_BOOTS;
	
	//Core Items
	public static Item BOONDOLLARS;
	public static Item RAW_CRUXITE;
	public static Item RAW_URANIUM;
	public static Item ENERGY_CORE;
	public static Item CRUXITE_APPLE;
	public static Item CRUXITE_POTION;
	public static Item CLIENT_DISK, SERVER_DISK;
	public static Item CAPTCHA_CARD;
	public static Item STACK_MODUS_CARD, QUEUE_MODUS_CARD, QUEUESTACK_MODUS_CARD, TREE_MODUS_CARD, HASHMAP_MODUS_CARD, SET_MODUS_CARD;
	public static Item SHUNT;
	
	//Food
	public static Item BUG_ON_A_STICK;
	public static Item CHOCOLATE_BEETLE;
	public static Item CONE_OF_FLIES;
	public static Item GRASSHOPPER;
	public static Item JAR_OF_BUGS;
	public static Item ONION;
	public static Item SALAD;
	public static Item DESERT_FRUIT;
	public static Item ROCK_COOKIE;
	public static Item FUNGAL_SPORE;
	public static Item SPOREO;
	public static Item MOREL_MUSHROOM;
	public static Item FRENCH_FRY;
	public static Item STRAWBERRY_CHUNK;
	
	public static Item CANDY_CORN;
	public static Item BUILD_GUSHERS;
	public static Item AMBER_GUMMY_WORM, CAULK_PRETZEL, CHALK_CANDY_CIGARETTE, IODINE_LICORICE, SHALE_PEEP, TAR_LICORICE;
	public static Item COBALT_GUM, MARBLE_JAWBREAKER, MERCURY_SIXLETS, QUARTZ_JELLY_BEAN, SULFUR_CANDY_APPLE;
	public static Item AMETHYST_HARD_CANDY, GARNET_TWIX, RUBY_LOLLIPOP, RUST_GUMMY_EYE;
	public static Item DIAMOND_MINT, GOLD_CANDY_RIBBON, URANIUM_GUMMY_BEAR;
	public static Item ARTIFACT_WARHEAD, ZILLIUM_SKITTLES;
	public static Item TAB;
	public static Item FAYGO, FAYGO_CANDY_APPLE, FAYGO_COLA, FAYGO_COTTON_CANDY, FAYGO_CREME, FAYGO_GRAPE;
	public static Item FAYGO_MOON_MIST, FAYGO_PEACH, FAYGO_REDPOP;
	public static Item IRRADIATED_STEAK;
	public static Item SURPRISE_EMBRYO;
	public static Item UNKNOWABLE_EGG;
	
	//Other Land Items
	public static Item GOLDEN_GRASSHOPPER;
	public static Item BUG_NET;
	public static Item FROG;
	public static Item CARVING_TOOL;
	public static Item CRUMPLY_HAT;
	public static Item STONE_EYEBALLS;
	public static Item STONE_SLAB;
	public static Item SHOP_POSTER;
	
	//Other
	//public static Item minestuckBucket = new ItemMinestuckBucket();	//TODO Sort out fluids and the bucket
	public static Item OBSIDIAN_BUCKET;
	public static Item CAPTCHAROID_CAMERA;
	public static Item GRIMOIRE;
	public static Item LONG_FORGOTTEN_WARHORN;
	public static Item RAZOR_BLADE;
	public static Item UP_STICK;
	public static Item IRON_BOAT, GOLD_BOAT;
	public static Item THRESH_DVD;
	public static Item GAMEBRO_MAGAZINE;
	public static Item GAMEGRL_MAGAZINE;
	public static Item CREW_POSTER;
	public static Item SBAHJ_POSTER;
	public static Item FAKE_ARMS;
	//Music disks
	public static Item RECORD_EMISSARY_OF_DANCE;
	public static Item RECORD_DANCE_STAB;
	public static Item RECORD_RETRO_BATTLE;
	
	public static void registerItems(IForgeRegistry<Item> registry)
	{
		registerItemBlock(registry, BLACK_CHESS_DIRT, ModItemGroup.MAIN);
		registerItemBlock(registry, WHITE_CHESS_DIRT, ModItemGroup.MAIN);
		registerItemBlock(registry, DARK_GRAY_CHESS_DIRT, ModItemGroup.MAIN);
		registerItemBlock(registry, LIGHT_GRAY_CHESS_DIRT, ModItemGroup.MAIN);
		registerItemBlock(registry, SKAIA_PORTAL, ModItemGroup.MAIN);
		
		registerItemBlock(registry, CRUXITE_ORE_STONE, ModItemGroup.LANDS);
		registerItemBlock(registry, CRUXITE_ORE_NETHERRACK, ModItemGroup.LANDS);
		registerItemBlock(registry, CRUXITE_ORE_COBBLESTONE, ModItemGroup.LANDS);
		registerItemBlock(registry, CRUXITE_ORE_SANDSTONE, ModItemGroup.LANDS);
		registerItemBlock(registry, CRUXITE_ORE_RED_SANDSTONE, ModItemGroup.LANDS);
		registerItemBlock(registry, CRUXITE_ORE_END_STONE, ModItemGroup.LANDS);
		registerItemBlock(registry, CRUXITE_ORE_PINK_STONE, ModItemGroup.LANDS);
		registerItemBlock(registry, URANIUM_ORE_STONE, ModItemGroup.LANDS);
		registerItemBlock(registry, URANIUM_ORE_NETHERRACK, ModItemGroup.LANDS);
		registerItemBlock(registry, URANIUM_ORE_COBBLESTONE, ModItemGroup.LANDS);
		registerItemBlock(registry, URANIUM_ORE_SANDSTONE, ModItemGroup.LANDS);
		registerItemBlock(registry, URANIUM_ORE_RED_SANDSTONE, ModItemGroup.LANDS);
		registerItemBlock(registry, URANIUM_ORE_END_STONE, ModItemGroup.LANDS);
		registerItemBlock(registry, COAL_ORE_NETHERRACK, ModItemGroup.LANDS);
		registerItemBlock(registry, COAL_ORE_PINK_STONE, ModItemGroup.LANDS);
		registerItemBlock(registry, IRON_ORE_END_STONE, ModItemGroup.LANDS);
		registerItemBlock(registry, IRON_ORE_SANDSTONE, ModItemGroup.LANDS);
		registerItemBlock(registry, IRON_ORE_SANDSTONE_RED, ModItemGroup.LANDS);
		registerItemBlock(registry, GOLD_ORE_SANDSTONE, ModItemGroup.LANDS);
		registerItemBlock(registry, GOLD_ORE_SANDSTONE_RED, ModItemGroup.LANDS);
		registerItemBlock(registry, GOLD_ORE_PINK_STONE, ModItemGroup.LANDS);
		registerItemBlock(registry, REDSTONE_ORE_END_STONE, ModItemGroup.LANDS);
		registerItemBlock(registry, QUARTZ_ORE_STONE, ModItemGroup.LANDS);
		registerItemBlock(registry, LAPIS_ORE_PINK_STONE, ModItemGroup.LANDS);
		registerItemBlock(registry, DIAMOND_ORE_PINK_STONE, ModItemGroup.LANDS);
		
		registerItemBlock(registry, CRUXITE_BLOCK, ModItemGroup.MAIN);
		registerItemBlock(registry, URANIUM_BLOCK, ModItemGroup.MAIN);
		registerItemBlock(registry, GENERIC_OBJECT, ModItemGroup.MAIN);
		
		registerItemBlock(registry, BLUE_DIRT, ModItemGroup.LANDS);
		registerItemBlock(registry, THOUGHT_DIRT, ModItemGroup.LANDS);
		registerItemBlock(registry, COARSE_STONE, ModItemGroup.LANDS);
		registerItemBlock(registry, CHISELED_COARSE_STONE, ModItemGroup.LANDS);
		registerItemBlock(registry, SHADE_BRICKS, ModItemGroup.LANDS);
		registerItemBlock(registry, SMOOTH_SHADE_STONE, ModItemGroup.LANDS);
		registerItemBlock(registry, FROST_BRICKS, ModItemGroup.LANDS);
		registerItemBlock(registry, FROST_TILE, ModItemGroup.LANDS);
		registerItemBlock(registry, CHISELED_FROST_BRICKS, ModItemGroup.LANDS);
		registerItemBlock(registry, CAST_IRON, ModItemGroup.LANDS);
		registerItemBlock(registry, CHISELED_CAST_IRON, ModItemGroup.LANDS);
		registerItemBlock(registry, MYCELIUM_BRICKS, ModItemGroup.LANDS);
		registerItemBlock(registry, BLACK_STONE, ModItemGroup.LANDS);
		registerItemBlock(registry, FLOWERY_MOSS_STONE, ModItemGroup.LANDS);
		registerItemBlock(registry, FLOWERY_MOSS_BRICKS, ModItemGroup.LANDS);
		registerItemBlock(registry, COARSE_END_STONE, ModItemGroup.LANDS);
		registerItemBlock(registry, END_GRASS, ModItemGroup.LANDS);
		registerItemBlock(registry, CHALK, ModItemGroup.LANDS);
		registerItemBlock(registry, CHALK_BRICKS, ModItemGroup.LANDS);
		registerItemBlock(registry, CHISELED_CHALK_BRICKS, ModItemGroup.LANDS);
		registerItemBlock(registry, POLISHED_CHALK, ModItemGroup.LANDS);
		registerItemBlock(registry, PINK_STONE, ModItemGroup.LANDS);
		registerItemBlock(registry, PINK_STONE_BRICKS, ModItemGroup.LANDS);
		registerItemBlock(registry, CHISELED_PINK_STONE_BRICKS, ModItemGroup.LANDS);
		registerItemBlock(registry, CRACKED_PINK_STONE_BRICKS, ModItemGroup.LANDS);
		registerItemBlock(registry, MOSSY_PINK_STONE_BRICKS, ModItemGroup.LANDS);
		registerItemBlock(registry, POLISHED_PINK_STONE, ModItemGroup.LANDS);
		registerItemBlock(registry, DENSE_CLOUD, ModItemGroup.LANDS);
		registerItemBlock(registry, BRIGHT_DENSE_CLOUD, ModItemGroup.LANDS);
		registerItemBlock(registry, SUGAR_CUBE, ModItemGroup.LANDS);
		
		registerItemBlock(registry, GLOWING_LOG, ModItemGroup.LANDS);
		registerItemBlock(registry, FROST_LOG, ModItemGroup.LANDS);
		registerItemBlock(registry, RAINBOW_LOG, ModItemGroup.LANDS);
		registerItemBlock(registry, END_LOG, ModItemGroup.LANDS);
		registerItemBlock(registry, VINE_LOG, ModItemGroup.LANDS);
		registerItemBlock(registry, FLOWERY_VINE_LOG, ModItemGroup.LANDS);
		registerItemBlock(registry, DEAD_LOG, ModItemGroup.LANDS);
		registerItemBlock(registry, PETRIFIED_LOG, ModItemGroup.LANDS);
		registerItemBlock(registry, GLOWING_WOOD, ModItemGroup.LANDS);
		registerItemBlock(registry, FROST_WOOD, ModItemGroup.LANDS);
		registerItemBlock(registry, RAINBOW_WOOD, ModItemGroup.LANDS);
		registerItemBlock(registry, END_WOOD, ModItemGroup.LANDS);
		registerItemBlock(registry, VINE_WOOD, ModItemGroup.LANDS);
		registerItemBlock(registry, FLOWERY_VINE_WOOD, ModItemGroup.LANDS);
		registerItemBlock(registry, DEAD_WOOD, ModItemGroup.LANDS);
		registerItemBlock(registry, PETRIFIED_WOOD, ModItemGroup.LANDS);
		registerItemBlock(registry, GLOWING_PLANKS, ModItemGroup.LANDS);
		registerItemBlock(registry, FROST_PLANKS, ModItemGroup.LANDS);
		registerItemBlock(registry, RAINBOW_PLANKS, ModItemGroup.LANDS);
		registerItemBlock(registry, END_PLANKS, ModItemGroup.LANDS);
		registerItemBlock(registry, DEAD_PLANKS, ModItemGroup.LANDS);
		registerItemBlock(registry, TREATED_PLANKS, ModItemGroup.LANDS);
		registerItemBlock(registry, FROST_LEAVES, ModItemGroup.LANDS);
		registerItemBlock(registry, RAINBOW_LEAVES, ModItemGroup.LANDS);
		registerItemBlock(registry, END_LEAVES, ModItemGroup.LANDS);
		registerItemBlock(registry, RAINBOW_SAPLING, ModItemGroup.LANDS);
		registerItemBlock(registry, END_SAPLING, ModItemGroup.LANDS);
		
		registerItemBlock(registry, BLOOD_ASPECT_LOG, ModItemGroup.LANDS);
		registerItemBlock(registry, BREATH_ASPECT_LOG, ModItemGroup.LANDS);
		registerItemBlock(registry, DOOM_ASPECT_LOG, ModItemGroup.LANDS);
		registerItemBlock(registry, HEART_ASPECT_LOG, ModItemGroup.LANDS);
		registerItemBlock(registry, HOPE_ASPECT_LOG, ModItemGroup.LANDS);
		registerItemBlock(registry, LIFE_ASPECT_LOG, ModItemGroup.LANDS);
		registerItemBlock(registry, LIGHT_ASPECT_LOG, ModItemGroup.LANDS);
		registerItemBlock(registry, MIND_ASPECT_LOG, ModItemGroup.LANDS);
		registerItemBlock(registry, RAGE_ASPECT_LOG, ModItemGroup.LANDS);
		registerItemBlock(registry, SPACE_ASPECT_LOG, ModItemGroup.LANDS);
		registerItemBlock(registry, TIME_ASPECT_LOG, ModItemGroup.LANDS);
		registerItemBlock(registry, VOID_ASPECT_LOG, ModItemGroup.LANDS);
		registerItemBlock(registry, BLOOD_ASPECT_PLANKS, ModItemGroup.LANDS);
		registerItemBlock(registry, BREATH_ASPECT_PLANKS, ModItemGroup.LANDS);
		registerItemBlock(registry, DOOM_ASPECT_PLANKS, ModItemGroup.LANDS);
		registerItemBlock(registry, HEART_ASPECT_PLANKS, ModItemGroup.LANDS);
		registerItemBlock(registry, HOPE_ASPECT_PLANKS, ModItemGroup.LANDS);
		registerItemBlock(registry, LIFE_ASPECT_PLANKS, ModItemGroup.LANDS);
		registerItemBlock(registry, LIGHT_ASPECT_PLANKS, ModItemGroup.LANDS);
		registerItemBlock(registry, MIND_ASPECT_PLANKS, ModItemGroup.LANDS);
		registerItemBlock(registry, RAGE_ASPECT_PLANKS, ModItemGroup.LANDS);
		registerItemBlock(registry, SPACE_ASPECT_PLANKS, ModItemGroup.LANDS);
		registerItemBlock(registry, TIME_ASPECT_PLANKS, ModItemGroup.LANDS);
		registerItemBlock(registry, VOID_ASPECT_PLANKS, ModItemGroup.LANDS);
		registerItemBlock(registry, BLOOD_ASPECT_LEAVES, ModItemGroup.LANDS);
		registerItemBlock(registry, BREATH_ASPECT_LEAVES, ModItemGroup.LANDS);
		registerItemBlock(registry, DOOM_ASPECT_LEAVES, ModItemGroup.LANDS);
		registerItemBlock(registry, HEART_ASPECT_LEAVES, ModItemGroup.LANDS);
		registerItemBlock(registry, HOPE_ASPECT_LEAVES, ModItemGroup.LANDS);
		registerItemBlock(registry, LIFE_ASPECT_LEAVES, ModItemGroup.LANDS);
		registerItemBlock(registry, LIGHT_ASPECT_LEAVES, ModItemGroup.LANDS);
		registerItemBlock(registry, MIND_ASPECT_LEAVES, ModItemGroup.LANDS);
		registerItemBlock(registry, RAGE_ASPECT_LEAVES, ModItemGroup.LANDS);
		registerItemBlock(registry, SPACE_ASPECT_LEAVES, ModItemGroup.LANDS);
		registerItemBlock(registry, TIME_ASPECT_LEAVES, ModItemGroup.LANDS);
		registerItemBlock(registry, VOID_ASPECT_LEAVES, ModItemGroup.LANDS);
		registerItemBlock(registry, BLOOD_ASPECT_SAPLING, ModItemGroup.LANDS);
		registerItemBlock(registry, BREATH_ASPECT_SAPLING, ModItemGroup.LANDS);
		registerItemBlock(registry, DOOM_ASPECT_SAPLING, ModItemGroup.LANDS);
		registerItemBlock(registry, HEART_ASPECT_SAPLING, ModItemGroup.LANDS);
		registerItemBlock(registry, HOPE_ASPECT_SAPLING, ModItemGroup.LANDS);
		registerItemBlock(registry, LIFE_ASPECT_SAPLING, ModItemGroup.LANDS);
		registerItemBlock(registry, LIGHT_ASPECT_SAPLING, ModItemGroup.LANDS);
		registerItemBlock(registry, MIND_ASPECT_SAPLING, ModItemGroup.LANDS);
		registerItemBlock(registry, RAGE_ASPECT_SAPLING, ModItemGroup.LANDS);
		registerItemBlock(registry, SPACE_ASPECT_SAPLING, ModItemGroup.LANDS);
		registerItemBlock(registry, TIME_ASPECT_SAPLING, ModItemGroup.LANDS);
		registerItemBlock(registry, VOID_ASPECT_SAPLING, ModItemGroup.LANDS);
		
		registerItemBlock(registry, GLOWING_MUSHROOM, ModItemGroup.LANDS);
		registerItemBlock(registry, DESERT_BUSH, ModItemGroup.LANDS);
		registerItemBlock(registry, BLOOMING_CACTUS, ModItemGroup.LANDS);
		registerItemBlock(registry, PETRIFIED_GRASS, ModItemGroup.LANDS);
		registerItemBlock(registry, PETRIFIED_POPPY, ModItemGroup.LANDS);
		registerItemBlock(registry, STRAWBERRY, ModItemGroup.LANDS);
		
		registerItemBlock(registry, LAYERED_SAND, ModItemGroup.LANDS);
		registerItemBlock(registry, LAYERED_RED_SAND, ModItemGroup.LANDS);
		registerItemBlock(registry, GLOWY_GOOP, ModItemGroup.LANDS);
		registerItemBlock(registry, COAGULATED_BLOOD, ModItemGroup.LANDS);
		registerItemBlock(registry, VEIN, ModItemGroup.LANDS);
		registerItemBlock(registry, VEIN_CORNER, ModItemGroup.LANDS);
		registerItemBlock(registry, VEIN_CORNER_INVERTED, ModItemGroup.LANDS);
		
		registerItemBlock(registry, COARSE_STONE_STAIRS, ModItemGroup.LANDS);
		registerItemBlock(registry, SHADE_BRICK_STAIRS, ModItemGroup.LANDS);
		registerItemBlock(registry, FROST_BRICK_STAIRS, ModItemGroup.LANDS);
		registerItemBlock(registry, CAST_IRON_STAIRS, ModItemGroup.LANDS);
		registerItemBlock(registry, MYCELIUM_BRICK_STAIRS, ModItemGroup.LANDS);
		registerItemBlock(registry, CHALK_STAIRS, ModItemGroup.LANDS);
		registerItemBlock(registry, CHALK_BRICK_STAIRS, ModItemGroup.LANDS);
		registerItemBlock(registry, PINK_STONE_BRICK_STAIRS, ModItemGroup.LANDS);
		registerItemBlock(registry, RAINBOW_PLANKS_STAIRS, ModItemGroup.LANDS);
		registerItemBlock(registry, END_PLANKS_STAIRS, ModItemGroup.LANDS);
		registerItemBlock(registry, DEAD_PLANKS_STAIRS, ModItemGroup.LANDS);
		registerItemBlock(registry, TREATED_PLANKS_STAIRS, ModItemGroup.LANDS);
		registerItemBlock(registry, CHALK_SLAB, ModItemGroup.LANDS);
		registerItemBlock(registry, CHALK_BRICK_SLAB, ModItemGroup.LANDS);
		registerItemBlock(registry, PINK_STONE_BRICK_SLAB, ModItemGroup.LANDS);
		registerItemBlock(registry, RAINBOW_PLANKS_SLAB, ModItemGroup.LANDS);
		registerItemBlock(registry, END_PLANKS_SLAB, ModItemGroup.LANDS);
		registerItemBlock(registry, DEAD_PLANKS_SLAB, ModItemGroup.LANDS);
		registerItemBlock(registry, TREATED_PLANKS_SLAB, ModItemGroup.LANDS);
		
		registry.register(new ItemCruxtruder(CRUXTRUDER.getMainBlock(), new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("cruxtruder"));
		registerItemBlock(registry, CRUXTRUDER_LID, ModItemGroup.MAIN);
		registry.register(new ItemTotemLathe(TOTEM_LATHE.getMainBlock(), new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("totem_lathe"));
		registry.register(new ItemAlchemiter(ALCHEMITER.getMainBlock(), new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("alchemiter"));
		registry.register(new ItemPunchDesignix(PUNCH_DESIGNIX.getMainBlock(), new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("punch_designix"));
		registerItemBlock(registry, new ItemMiniCruxtruder(MINI_CRUXTRUDER, new Item.Properties().group(ModItemGroup.MAIN)));
		registerItemBlock(registry, MINI_TOTEM_LATHE, ModItemGroup.MAIN);
		registerItemBlock(registry, MINI_ALCHEMITER, ModItemGroup.MAIN);
		registerItemBlock(registry, MINI_PUNCH_DESIGNIX, ModItemGroup.MAIN);
		registerItemBlock(registry, new ItemBlock(holopad));
		registerItemBlock(registry, new ItemJumperBlock(jumperBlockExtension[0]));
		
		registerItemBlock(registry, COMPUTER_OFF, ModItemGroup.MAIN);
		registerItemBlock(registry, LAPTOP_OFF, ModItemGroup.MAIN);
		registerItemBlock(registry, CROCKERTOP_OFF, ModItemGroup.MAIN);
		registerItemBlock(registry, HUBTOP_OFF, ModItemGroup.MAIN);
		registerItemBlock(registry, LUNCHTOP_OFF, ModItemGroup.MAIN);
		registerItemBlock(registry, new ItemTransportalizer(TRANSPORTALIZER, new Item.Properties().group(ModItemGroup.MAIN)));
		registerItemBlock(registry, GRIST_WIDGET, ModItemGroup.MAIN);
		registerItemBlock(registry, URANIUM_COOKER, ModItemGroup.MAIN);
		
		registerItemBlock(registry, new ItemDowel(CRUXITE_DOWEL, new Item.Properties().group(ModItemGroup.MAIN)));
		
		registerItemBlock(registry, GOLD_SEEDS, ModItemGroup.MAIN);
		registerItemBlock(registry, WOODEN_CACTUS, ModItemGroup.MAIN);
		
		registerItemBlock(registry, new ItemBlock(APPLE_CAKE, new Item.Properties().group(ModItemGroup.MAIN).maxStackSize(1)));
		registerItemBlock(registry, new ItemBlock(BLUE_CAKE, new Item.Properties().group(ModItemGroup.MAIN).maxStackSize(1)));
		registerItemBlock(registry, new ItemBlock(COLD_CAKE, new Item.Properties().group(ModItemGroup.MAIN).maxStackSize(1)));
		registerItemBlock(registry, new ItemBlock(RED_CAKE, new Item.Properties().group(ModItemGroup.MAIN).maxStackSize(1)));
		registerItemBlock(registry, new ItemBlock(HOT_CAKE, new Item.Properties().group(ModItemGroup.MAIN).maxStackSize(1)));
		registerItemBlock(registry, new ItemBlock(REVERSE_CAKE, new Item.Properties().group(ModItemGroup.MAIN).maxStackSize(1)));
		registerItemBlock(registry, new ItemBlock(FUCHSIA_CAKE, new Item.Properties().group(ModItemGroup.MAIN).maxStackSize(1)));
		
		registerItemBlock(registry, PRIMED_TNT, ModItemGroup.MAIN);
		registerItemBlock(registry, UNSTABLE_TNT, ModItemGroup.MAIN);
		registerItemBlock(registry, INSTANT_TNT, ModItemGroup.MAIN);
		registerItemBlock(registry, WOODEN_EXPLOSIVE_BUTTON, ModItemGroup.MAIN);
		registerItemBlock(registry, STONE_EXPLOSIVE_BUTTON, ModItemGroup.MAIN);
		
		registerItemBlock(registry, BLENDER, ModItemGroup.MAIN);
		registerItemBlock(registry, CHESSBOARD, ModItemGroup.MAIN);
		registerItemBlock(registry, MINI_FROG_STATUE, ModItemGroup.MAIN);
		registerItemBlock(registry, GLOWYSTONE_WIRE, ModItemGroup.MAIN);
		
		//hammers
		registry.register(CLAW_HAMMER = new ItemWeapon(ItemTier.IRON, 2, -2.4F, 1.0F, new Item.Properties().defaultMaxDamage(131).addToolType(ToolType.PICKAXE, 0).group(ModItemGroup.WEAPONS)).setRegistryName("claw_hammer"));
		registry.register(SLEDGE_HAMMER = new ItemWeapon(ItemTier.IRON, 4, -2.8F, 4.0F, new Item.Properties().addToolType(ToolType.PICKAXE, 2).group(ModItemGroup.WEAPONS)).setRegistryName("sledge_hammer"));
		registry.register(BLACKSMITH_HAMMER = new ItemWeapon(ItemTier.IRON, 5, -2.8F, 3.5F, new Item.Properties().defaultMaxDamage(450).addToolType(ToolType.PICKAXE, 2).group(ModItemGroup.WEAPONS)).setRegistryName("blacksmith_hammer"));
		registry.register(POGO_HAMMER = new ItemPogoWeapon(ModItemTypes.POGO_TIER, 5, -2.8F, 2.0F, 0.7, new Item.Properties().addToolType(ToolType.PICKAXE, 1).group(ModItemGroup.WEAPONS)).setRegistryName("pogo_hammer"));
		registry.register(TELESCOPIC_SASSACRUSHER = new ItemWeapon(ModItemTypes.BOOK_TIER, 8, -2.9F, 5.0F, new Item.Properties().defaultMaxDamage(1024).addToolType(ToolType.PICKAXE, 2).group(ModItemGroup.WEAPONS)).setRegistryName("telescopic_sassacrusher"));
		registry.register(REGI_HAMMER = new ItemWeapon(ModItemTypes.REGI_TIER, 3, -2.4F, 8.0F, new Item.Properties().addToolType(ToolType.PICKAXE, 2).group(ModItemGroup.WEAPONS)).setRegistryName("regi_hammer"));
		registry.register(FEAR_NO_ANVIL = new ItemPotionWeapon(ModItemTypes.RUBY_TIER, 6, -2.8F, 7.0F, new PotionEffect(MobEffects.SLOWNESS, 100, 3), new Item.Properties().addToolType(ToolType.PICKAXE, 3).group(ModItemGroup.WEAPONS)).setRegistryName("fear_no_anvil"));
		registry.register(MELT_MASHER = new ItemFireWeapon(ModItemTypes.RUBY_TIER, 6, -2.8F, 12.0F, 25, new Item.Properties().defaultMaxDamage(1413).addToolType(ToolType.PICKAXE, 4).group(ModItemGroup.WEAPONS)).setRegistryName("melt_masher"));
		registry.register(Q_E_HAMMER_AXE = new ItemPogoFarmine(ModItemTypes.RUBY_TIER, 5, -2.8F, 9.0F, Integer.MAX_VALUE, 200, 0.7, new Item.Properties().defaultMaxDamage(6114).addToolType(ToolType.PICKAXE, 3).addToolType(ToolType.SHOVEL, 1).addToolType(ToolType.AXE, 2).group(ModItemGroup.WEAPONS)).setRegistryName("estrogen_empowered_everything_eradicator"));
		registry.register(D_D_E_HAMMER_AXE = new ItemSbahjEEEE(ModItemTypes.RUBY_TIER, 5, -2.8F, 9.1F, 0.2, new Item.Properties().defaultMaxDamage(6114).group(ModItemGroup.WEAPONS)).setRegistryName("eeeeeeeeeeee"));
		registry.register(ZILLYHOO_HAMMER = new ItemWeapon(ModItemTypes.ZILLYHOO_TIER, 6, -2.8F, 15.0F, new Item.Properties().addToolType(ToolType.PICKAXE, 4).group(ModItemGroup.WEAPONS)).setRegistryName("zillyhoo_hammer"));
		registry.register(POPAMATIC_VRILLYHOO = new ItemRandomWeapon(ModItemTypes.ZILLYHOO_TIER, 3, -2.8F, 15.0F, new Item.Properties().addToolType(ToolType.PICKAXE, 4).group(ModItemGroup.WEAPONS)).setRegistryName("popamatic_vrillyhoo"));
		registry.register(SCARLET_ZILLYHOO = new ItemFireWeapon(ModItemTypes.RUBY_TIER, 6, -2.8F, 4.0F, 50, new Item.Properties().addToolType(ToolType.PICKAXE, 3)).setRegistryName("scarlet_zillyhoo"));
		registry.register(MWRTHWL = new ItemWeapon(ModItemTypes.RUBY_TIER, 6, -2.8F, 4.0F, new Item.Properties().addToolType(ToolType.PICKAXE, 3)).setRegistryName("mwrthwl"));
		
		//blades
		registry.register(SORD = new ItemSord(ModItemTypes.SBAHJ_TIER, 3, -2.4F, 1.0F, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("sord"));
		registry.register(CACTUS_CUTLASS = new ItemWeapon(ModItemTypes.CACTUS_TIER, 3, -2.4F, 15.0F, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("cactaceae_cutlass"));	//The sword harvestTool is only used against webs, hence the high efficiency.
		registry.register(STEAK_SWORD = new ItemConsumableWeapon(ModItemTypes.MEAT_TIER, 4, -2.4F, 5.0F, 8, 1F, new Item.Properties().defaultMaxDamage(250).group(ModItemGroup.WEAPONS)).setRegistryName("steak_sword"));
		registry.register(BEEF_SWORD = new ItemConsumableWeapon(ModItemTypes.MEAT_TIER, 2, -2.4F, 5.0F, 3, 0.8F, 75, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("beef_sword"));
		registry.register(IRRADIATED_STEAK_SWORD = new ItemConsumableWeapon(ModItemTypes.MEAT_TIER, 2, -2.4F, 5.0F, 4, 0.4F, 25, new Item.Properties().defaultMaxDamage(150).group(ModItemGroup.WEAPONS)).setPotionEffect(new PotionEffect(MobEffects.WITHER, 100, 1), 0.9F).setRegistryName("irradiated_steak_sword"));
		registry.register(KATANA = new ItemWeapon(ItemTier.IRON, 3, -2.4F, 15.0F, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("katana"));
		registry.register(UNBREAKABLE_KATANA = new ItemWeapon(ModItemTypes.RUBY_TIER, 3, -2.4F, 15.0F, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("unbreakable_katana"));	//Not actually unbreakable
		registry.register(FIRE_POKER = new ItemFireWeapon(ItemTier.IRON, 4, -2.4F, 15.0F,  30, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("fire_poker"));
		registry.register(HOT_HANDLE = new ItemFireWeapon(ItemTier.IRON, 3, -2.4F, 15.0F, 10, new Item.Properties().defaultMaxDamage(350).group(ModItemGroup.WEAPONS)).setRegistryName("too_hot_to_handle"));
		registry.register(CALEDSCRATCH = new ItemWeapon(ModItemTypes.RUBY_TIER, 2, -2.4F, 15.0F, new Item.Properties().defaultMaxDamage(1561).group(ModItemGroup.WEAPONS)).setRegistryName("caledscratch"));
		registry.register(CALEDFWLCH = new ItemWeapon(ModItemTypes.RUBY_TIER, 2, -2.4F, 15.0F, new Item.Properties().defaultMaxDamage(1025).group(ModItemGroup.WEAPONS)).setRegistryName("caledfwlch"));
		registry.register(ROYAL_DERINGER = new ItemWeapon(ModItemTypes.RUBY_TIER, 3, -2.4F, 15.0F, new Item.Properties().defaultMaxDamage(1561).group(ModItemGroup.WEAPONS)).setRegistryName("royal_deringer"));
		registry.register(CLAYMORE = new ItemWeapon(ItemTier.IRON, 5, -2.6F, 15.0F, new Item.Properties().defaultMaxDamage(600).group(ModItemGroup.WEAPONS)).setRegistryName("claymore"));
		registry.register(ZILLYWAIR_CUTLASS = new ItemWeapon(ModItemTypes.ZILLYHOO_TIER, 3, -2.4F, 15.0F, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("cutlass_of_zillywair"));
		registry.register(REGISWORD = new ItemWeapon(ModItemTypes.REGI_TIER, 3, -2.4F, 15.0F, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("regisword"));
		registry.register(SCARLET_RIBBITAR = new ItemWeapon(ModItemTypes.RUBY_TIER, 3, -2.4F, 15.0F, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("scarlet_ribbitar"));
		registry.register(DOGG_MACHETE = new ItemWeapon(ModItemTypes.RUBY_TIER, 1, -2.4F, 15.0F, new Item.Properties().defaultMaxDamage(1000).group(ModItemGroup.WEAPONS)).setRegistryName("dogg_machete"));
		registry.register(COBALT_SABRE = new ItemFireWeapon(ItemTier.GOLD, 7, -2.4F, 15.0F, 30, new Item.Properties().defaultMaxDamage(300).group(ModItemGroup.WEAPONS)).setRegistryName("cobalt_sabre"));
		registry.register(QUAMTUM_SABRE = new ItemPotionWeapon(ModItemTypes.URANIUM_TIER, 4, -2.4F, 15.0F, new PotionEffect(MobEffects.WITHER, 100, 1), new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("quantum_sabre"));
		registry.register(SHATTER_BEACON = new ItemWeapon(ModItemTypes.RUBY_TIER, 6, -2.4F, 15.0F, new Item.Properties().defaultMaxDamage(1850).group(ModItemGroup.WEAPONS)).setRegistryName("shatter_beacon"));
		
		//axes
		registry.register(BATLEACKS = new ItemSord(ModItemTypes.SBAHJ_TIER, 5, -3.5F, 1.0F, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("batleacks"));
		registry.register(COPSE_CRUSHER = new ItemFarmine(ItemTier.STONE, 5, -3.0F, 6.0F, Integer.MAX_VALUE, 20, new Item.Properties().defaultMaxDamage(400).addToolType(ToolType.AXE, 2).group(ModItemGroup.WEAPONS)).setRegistryName("copse_crusher"));
		registry.register(BATTLEAXE = new ItemWeapon(ItemTier.IRON, 8, -3.0F, 3.0F, new Item.Properties().defaultMaxDamage(600).group(ModItemGroup.WEAPONS).addToolType(ToolType.AXE, 2)).setRegistryName("battleaxe"));
		registry.register(BLACKSMITH_BANE = new ItemWeapon(ItemTier.STONE, 8, -3.0F, 6.0F, new Item.Properties().defaultMaxDamage(413).group(ModItemGroup.WEAPONS).addToolType(ToolType.AXE, 2)).setRegistryName("blacksmith_bane"));
		registry.register(SCRAXE = new ItemWeapon(ItemTier.IRON, 8, -3.0F, 7.0F, new Item.Properties().defaultMaxDamage(500).group(ModItemGroup.WEAPONS).addToolType(ToolType.AXE, 2)).setRegistryName("scraxe"));
		registry.register(Q_P_HAMMER_AXE = new ItemPogoFarmine(ModItemTypes.POGO_TIER, 6, -3.0F, 2.0F, Integer.MAX_VALUE, 50, 0.6, new Item.Properties().defaultMaxDamage(800).group(ModItemGroup.WEAPONS).addToolType(ToolType.AXE, 2).addToolType(ToolType.PICKAXE, 1)).setRegistryName("piston_powered_pogo_axehammer"));
		registry.register(RUBY_CROAK = new ItemWeapon(ModItemTypes.RUBY_TIER, 7, -3.0F, 8.0F, new Item.Properties().group(ModItemGroup.WEAPONS).addToolType(ToolType.AXE, 3)).setRegistryName("ruby_croak"));
		registry.register(HEPHAESTUS_LUMBER = new ItemFireWeapon(ModItemTypes.RUBY_TIER, 7, -3.0F, 9.0F, 30, new Item.Properties().defaultMaxDamage(3000).group(ModItemGroup.WEAPONS).addToolType(ToolType.AXE, 3)).setRegistryName("hephaestus_lumberjack"));
		registry.register(Q_F_HAMMER_AXE = new ItemPogoFarmine(ModItemTypes.URANIUM_TIER, 7, -3.0F, 5.0F, Integer.MAX_VALUE, 100, 0.7, new Item.Properties().defaultMaxDamage(2048).group(ModItemGroup.WEAPONS).addToolType(ToolType.PICKAXE, 2).addToolType(ToolType.AXE, 3)).setRegistryName("fission_focused_fault_feller"));
		
		//Dice
		registry.register(DICE = new ItemWeapon(ItemTier.STONE, 5, -3.0F, 1.0F, new Item.Properties().defaultMaxDamage(51).group(ModItemGroup.WEAPONS)).setRegistryName("dice"));
		registry.register(FLUORITE_OCTET = new ItemWeapon(ItemTier.DIAMOND, 12, -3.0F, 1.0F, new Item.Properties().defaultMaxDamage(67).group(ModItemGroup.WEAPONS)).setRegistryName("fluorite_octet"));
		//misc weapons
		registry.register(CAT_CLAWS_DRAWN  = new ItemDualWeapon(ItemTier.IRON, 2, -1.5F, 10.0F, new Item.Properties().defaultMaxDamage(500).group(ModItemGroup.WEAPONS)).setRegistryName("cat_claws_drawn"));
		registry.register(CAT_CLAWS_SHEATHED  = new ItemDualWeapon(ItemTier.IRON, -1, -1.0F, 10.0F, (ItemDualWeapon) CAT_CLAWS_DRAWN, new Item.Properties().defaultMaxDamage(500)).setRegistryName("cat_claws_sheathed"));
		//sickles
		registry.register(SICKLE = new ItemWeapon(ItemTier.IRON, 2, -2.4F, 1.5F, new Item.Properties().group(ModItemGroup.WEAPONS).addToolType(ModItemTypes.SICKLE_TOOL, 0)).setRegistryName("sickle"));
		registry.register(HOMES_SMELL_YA_LATER = new ItemWeapon(ItemTier.IRON, 3, -2.4F, 3.0F, new Item.Properties().defaultMaxDamage(400).group(ModItemGroup.WEAPONS).addToolType(ModItemTypes.SICKLE_TOOL, 0)).setRegistryName("homes_smell_ya_later"));
		registry.register(FUDGE_SICKLE = new ItemConsumableWeapon(ModItemTypes.CANDY_TIER, 5, -2.4F, 1.0F, 7, 0.6F, new Item.Properties().group(ModItemGroup.WEAPONS).addToolType(ModItemTypes.SICKLE_TOOL, 0)).setRegistryName("fudgesickle"));
		registry.register(REGI_SICKLE = new ItemWeapon(ModItemTypes.REGI_TIER, 3, -2.4F, 4.0F, new Item.Properties().group(ModItemGroup.WEAPONS).addToolType(ModItemTypes.SICKLE_TOOL, 0)).setRegistryName("regisickle"));
		registry.register(CLAW_SICKLE = new ItemWeapon(ModItemTypes.RUBY_TIER, 3, -2.4F, 4.0F, new Item.Properties().group(ModItemGroup.WEAPONS).addToolType(ModItemTypes.SICKLE_TOOL, 0)).setRegistryName("claw_sickle"));
		registry.register(CLAW_OF_NRUBYIGLITH = new ItemHorrorterrorWeapon(ModItemTypes.HORRORTERROR_TIER, 5, -2.4F, 4.0F, new Item.Properties().group(ModItemGroup.WEAPONS).addToolType(ModItemTypes.SICKLE_TOOL, 0)).setRegistryName("claw_of_nrubyiglith"));
		registry.register(CANDY_SICKLE = new ItemCandyWeapon(ModItemTypes.CANDY_TIER, 6, -2.4F, 2.5F, new Item.Properties().defaultMaxDamage(96).group(ModItemGroup.WEAPONS).addToolType(ModItemTypes.SICKLE_TOOL, 0)).setRegistryName("candy_sickle"));
		
		//clubs
		registry.register(DEUCE_CLUB = new ItemWeapon(ItemTier.WOOD, 3, -2.2F, 2.0F, new Item.Properties().defaultMaxDamage(1024).group(ModItemGroup.WEAPONS)).setRegistryName("deuce_club"));
		registry.register(NIGHT_CLUB = new ItemWeapon(ModItemTypes.REGI_TIER, 1, -2.2F, 2.0F, new Item.Properties().defaultMaxDamage(600).group(ModItemGroup.WEAPONS)).setRegistryName("night_club"));
		registry.register(POGO_CLUB = new ItemPogoWeapon(ModItemTypes.POGO_TIER, 2, -2.2F, 2.0F, 0.5, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("pogo_club"));
		registry.register(METAL_BAT = new ItemWeapon(ItemTier.IRON, 3, -2.2F, 2.0F, new Item.Properties().defaultMaxDamage(750).group(ModItemGroup.WEAPONS)).setRegistryName("metal_bat"));
		registry.register(SPIKED_CLUB = new ItemWeapon(ItemTier.WOOD, 5, -2.2F, 2.0F, new Item.Properties().defaultMaxDamage(500).group(ModItemGroup.WEAPONS)).setRegistryName("spiked_club"));
		
		//canes
		registry.register(CANE = new ItemWeapon(ItemTier.WOOD, 2, -2.0F, 1.0F, new Item.Properties().defaultMaxDamage(100).group(ModItemGroup.WEAPONS)).setRegistryName("cane"));
		registry.register(IRON_CANE = new ItemWeapon(ItemTier.IRON, 2, -2.0F, 1.0F, new Item.Properties().defaultMaxDamage(450).group(ModItemGroup.WEAPONS)).setRegistryName("iron_cane"));
		registry.register(SPEAR_CANE = new ItemWeapon(ItemTier.IRON, 3, -2.0F, 1.0F, new Item.Properties().defaultMaxDamage(300).group(ModItemGroup.WEAPONS)).setRegistryName("spear_cane"));
		registry.register(PARADISES_PORTABELLO = new ItemWeapon(ModItemTypes.CANDY_TIER, 3, -2.0F, 1.0F, new Item.Properties().defaultMaxDamage(175).group(ModItemGroup.WEAPONS)).setRegistryName("paradises_portabello"));
		registry.register(REGI_CANE = new ItemWeapon(ModItemTypes.REGI_TIER, 3, -2.0F, 1.0F, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("regi_cane"));
		registry.register(DRAGON_CANE = new ItemWeapon(ModItemTypes.RUBY_TIER, 3, -2.0F, 1.0F, new Item.Properties().defaultMaxDamage(300).group(ModItemGroup.WEAPONS)).setRegistryName("dragon_cane"));
		registry.register(POGO_CANE = new ItemPogoWeapon(ModItemTypes.POGO_TIER, 2, -2.0F, 1.0F, 0.6, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("pogo_cane"));
		//Spoons/forks
		registry.register(WOODEN_SPOON = new ItemWeapon(ItemTier.WOOD, 2, -2.2F, 1.0F, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("wooden_spoon"));
		registry.register(SILVER_SPOON = new ItemWeapon(ItemTier.IRON, 1, -2.2F, 1.0F, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("silver_spoon"));
		registry.register(CROCKER_SPOON = new ItemDualWeapon(ModItemTypes.RUBY_TIER, 0, -2.2F, 1.0F, new Item.Properties().defaultMaxDamage(512).group(ModItemGroup.WEAPONS)).setRegistryName("crocker_spoon"));
		registry.register(CROCKER_FORK = new ItemDualWeapon(ModItemTypes.RUBY_TIER, 2, -2.6F, 1.0F, (ItemDualWeapon) CROCKER_SPOON, new Item.Properties().defaultMaxDamage(512)).setRegistryName("crocker_fork"));
		registry.register(SKAIA_FORK = new ItemWeapon(ModItemTypes.REGI_TIER, 5, -2.2F, 1.0F, new Item.Properties().defaultMaxDamage(2048).group(ModItemGroup.WEAPONS)).setRegistryName("skaia_fork"));
		registry.register(FORK = new ItemWeapon(ItemTier.STONE, 3, -2.2F, 1.0F, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("fork"));
		registry.register(SPORK = new ItemWeapon(ItemTier.STONE, 4, -2.3F, 1.0F, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("spork"));
		registry.register(GOLDEN_SPORK = new ItemWeapon(ItemTier.GOLD, 5, -2.3F, 1.0F, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("golden_spork"));

		registry.register(EMERALD_SWORD = new ItemSword(ModItemTypes.EMERALD_TIER, 3, -2.4F, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("emerald_sword"));
		registry.register(EMERALD_AXE = new ItemModAxe(ModItemTypes.EMERALD_TIER, 5, -3.0F, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("emerald_axe"));
		registry.register(EMERALD_PICKAXE = new ItemModPickaxe(ModItemTypes.EMERALD_TIER, 1 , -2.8F, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("emerald_pickaxe"));
		registry.register(EMERALD_SHOVEL = new ItemSpade(ModItemTypes.EMERALD_TIER, 1.5F, -3.0F, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("emerald_shovel"));
		registry.register(EMERALD_HOE = new ItemHoe(ModItemTypes.EMERALD_TIER, 0.0F, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("emerald_hoe"));
		
		//armor
		registry.register(PRISMARINE_HELMET = new ItemArmor(ModItemTypes.PRISMARINE_ARMOR, EntityEquipmentSlot.HEAD, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("prismarine_helmet"));
		registry.register(PRISMARINE_CHESTPLATE = new ItemArmor(ModItemTypes.PRISMARINE_ARMOR, EntityEquipmentSlot.CHEST, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("prismarine_chestplate"));
		registry.register(PRISMARINE_LEGGINGS = new ItemArmor(ModItemTypes.PRISMARINE_ARMOR, EntityEquipmentSlot.LEGS, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("prismarine_leggings"));
		registry.register(PRISMARINE_BOOTS = new ItemArmor(ModItemTypes.PRISMARINE_ARMOR, EntityEquipmentSlot.FEET, new Item.Properties().group(ModItemGroup.WEAPONS)).setRegistryName("prismarine_boots"));
		
		
		registry.register(BOONDOLLARS = new ItemBoondollars(new Item.Properties().maxStackSize(1).group(ModItemGroup.MAIN)).setRegistryName("boondollars"));
		registry.register(RAW_CRUXITE = new Item(new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("raw_cruxite"));
		registry.register(RAW_URANIUM = new Item(new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("raw_uranium"));
		registry.register(ENERGY_CORE = new Item(new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("energy_core"));
		registry.register(CRUXITE_APPLE = new ItemCruxiteApple(new Item.Properties().maxStackSize(1).group(ModItemGroup.MAIN)).setRegistryName("cruxite_apple"));
		registry.register(CRUXITE_POTION = new ItemCruxitePotion(new Item.Properties().maxStackSize(1).group(ModItemGroup.MAIN)).setRegistryName("cruxite_potion"));
		registry.register(CLIENT_DISK = new Item(new Item.Properties().maxStackSize(1).group(ModItemGroup.MAIN)).setRegistryName("client_disk"));
		registry.register(SERVER_DISK = new Item(new Item.Properties().maxStackSize(1).group(ModItemGroup.MAIN)).setRegistryName("server_disk"));
		registry.register(CAPTCHA_CARD = new ItemCaptchaCard(new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("captcha_card"));
		registry.register(STACK_MODUS_CARD = new Item(new Item.Properties().maxStackSize(1).group(ModItemGroup.MAIN)).setRegistryName("stack_modus_card"));
		registry.register(QUEUE_MODUS_CARD = new Item(new Item.Properties().maxStackSize(1).group(ModItemGroup.MAIN)).setRegistryName("queue_modus_card"));
		registry.register(QUEUESTACK_MODUS_CARD = new Item(new Item.Properties().maxStackSize(1).group(ModItemGroup.MAIN)).setRegistryName("queuestack_modus_card"));
		registry.register(TREE_MODUS_CARD = new Item(new Item.Properties().maxStackSize(1).group(ModItemGroup.MAIN)).setRegistryName("tree_modus_card"));
		registry.register(HASHMAP_MODUS_CARD = new Item(new Item.Properties().maxStackSize(1).group(ModItemGroup.MAIN)).setRegistryName("hashmap_modus_card"));
		registry.register(SET_MODUS_CARD = new Item(new Item.Properties().maxStackSize(1).group(ModItemGroup.MAIN)).setRegistryName("set_modus_card"));
		registry.register(SHUNT = new ItemShunt(new Item.Properties().maxStackSize(1).group(ModItemGroup.MAIN)).setRegistryName("shunt"));
		
		//food
		registry.register(BUG_ON_A_STICK = new ItemFood(1, 0.1F, false, new Item.Properties().group(ModItemGroup.LANDS)).setRegistryName("bug_on_a_stick"));
		registry.register(CHOCOLATE_BEETLE = new ItemFood(3, 0.4F, false, new Item.Properties().group(ModItemGroup.LANDS)).setRegistryName("chocolate_beetle"));
		registry.register(CONE_OF_FLIES = new ItemFood(2, 0.1F, false, new Item.Properties().group(ModItemGroup.LANDS)).setRegistryName("cone_of_flies"));
		registry.register(GRASSHOPPER = new ItemFood(4, 0.5F, false, new Item.Properties().group(ModItemGroup.LANDS)).setRegistryName("grasshopper"));
		registry.register(JAR_OF_BUGS = new ItemFood(3, 0.2F, false, new Item.Properties().group(ModItemGroup.LANDS)).setRegistryName("jar_of_bugs"));
		registry.register(ONION = new ItemFood(2, 0.2F, false, new Item.Properties().group(ModItemGroup.LANDS)).setRegistryName("onion"));
		registry.register(SALAD = new ItemSoup(1, new Item.Properties().group(ModItemGroup.LANDS)).setRegistryName("salad"));
		registry.register(DESERT_FRUIT = new ItemFood(1, 0.1F, false, new Item.Properties().group(ModItemGroup.LANDS)).setRegistryName("desert_fruit"));
		registry.register(ROCK_COOKIE = new Item(new Item.Properties().group(ModItemGroup.LANDS)).setRegistryName("rock_cookie"));	//Not actually food, but let's pretend it is
		registry.register(FUNGAL_SPORE = new ItemFood(1, 0.2F, false, new Item.Properties().group(ModItemGroup.LANDS)).setPotionEffect(new PotionEffect(MobEffects.POISON, 60, 3), 0.7F).setRegistryName("fungal_spore"));
		registry.register(SPOREO = new ItemFood(3, 0.4F, false, new Item.Properties().group(ModItemGroup.LANDS)).setRegistryName("sporeo"));
		registry.register(MOREL_MUSHROOM = new ItemFood(3, 0.9F, false, new Item.Properties().group(ModItemGroup.LANDS)).setRegistryName("morel_mushroom"));
		registry.register(FRENCH_FRY = new ItemFood(1, 0.1F, false, new Item.Properties().group(ModItemGroup.LANDS)).setRegistryName("french_fry"));
		registry.register(STRAWBERRY_CHUNK = new ItemSeedFood(4, 0.5F, STRAWBERRY_STEM, new Item.Properties().group(ModItemGroup.LANDS)).setRegistryName("strawberry_chunk"));
		
		registry.register(CANDY_CORN = new ItemFood(2, 0.3F, false, new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("candy_corn"));
		registry.register(BUILD_GUSHERS = new ItemFood(2, 0.0F, false, new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("build_gushers"));
		registry.register(AMBER_GUMMY_WORM = new ItemFood(2, 0.1F, false, new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("amber_gummy_worm"));
		registry.register(CAULK_PRETZEL = new ItemFood(2, 0.1F, false, new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("caulk_pretzel"));
		registry.register(CHALK_CANDY_CIGARETTE = new ItemFood(2, 0.1F, false, new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("chalk_candy_cigarette"));
		registry.register(IODINE_LICORICE = new ItemFood(2, 0.1F, false, new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("iodine_licorice"));
		registry.register(SHALE_PEEP = new ItemFood(2, 0.1F, false, new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("shale_peep"));
		registry.register(TAR_LICORICE = new ItemFood(2, 0.1F, false, new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("tar_licorice"));
		registry.register(COBALT_GUM = new ItemFood(2, 0.2F, false, new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("cobalt_gum"));
		registry.register(MARBLE_JAWBREAKER = new ItemFood(2, 0.2F, false, new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("marble_jawbreaker"));
		registry.register(MERCURY_SIXLETS = new ItemFood(2, 0.2F, false, new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("mercury_sixlets"));
		registry.register(QUARTZ_JELLY_BEAN = new ItemFood(2, 0.2F, false, new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("quartz_jelly_bean"));
		registry.register(SULFUR_CANDY_APPLE = new ItemFood(2, 0.2F, false, new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("sulfur_candy_apple"));
		registry.register(AMETHYST_HARD_CANDY = new ItemFood(2, 0.3F, false, new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("amethyst_hard_candy"));
		registry.register(GARNET_TWIX = new ItemFood(2, 0.3F, false, new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("garnet_twix"));
		registry.register(RUBY_LOLLIPOP = new ItemFood(2, 0.3F, false, new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("ruby_lollipop"));
		registry.register(RUST_GUMMY_EYE = new ItemFood(2, 0.3F, false, new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("rust_gummy_eye"));
		registry.register(DIAMOND_MINT = new ItemFood(2, 0.4F, false, new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("diamond_mint"));
		registry.register(GOLD_CANDY_RIBBON = new ItemFood(2, 0.4F, false, new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("gold_candy_ribbon"));
		registry.register(URANIUM_GUMMY_BEAR = new ItemFood(2, 0.4F, false, new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("uranium_gummy_bear"));
		registry.register(ARTIFACT_WARHEAD = new ItemFood(2, 0.5F, false, new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("artifact_warhead"));
		registry.register(ZILLIUM_SKITTLES = new ItemFood(2, 0.6F, false, new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("zillium_skittles"));
		registry.register(TAB = new ItemBeverage(1, 0.0F, false, new Item.Properties().maxStackSize(16).group(ModItemGroup.MAIN)).setAlwaysEdible().setRegistryName("tab"));
		registry.register(FAYGO = new ItemBeverage(1, 0.0F, false, new Item.Properties().maxStackSize(16).group(ModItemGroup.MAIN)).setAlwaysEdible().setRegistryName("faygo"));
		registry.register(FAYGO_CANDY_APPLE = new ItemBeverage(1, 0.0F, false, new Item.Properties().maxStackSize(16).group(ModItemGroup.MAIN)).setAlwaysEdible().setRegistryName("faygo_candy_apple"));
		registry.register(FAYGO_COLA = new ItemBeverage(1, 0.0F, false, new Item.Properties().maxStackSize(16).group(ModItemGroup.MAIN)).setAlwaysEdible().setRegistryName("faygo_cola"));
		registry.register(FAYGO_COTTON_CANDY = new ItemBeverage(1, 0.0F, false, new Item.Properties().maxStackSize(16).group(ModItemGroup.MAIN)).setAlwaysEdible().setRegistryName("faygo_cotton_candy"));
		registry.register(FAYGO_CREME = new ItemBeverage(1, 0.0F, false, new Item.Properties().maxStackSize(16).group(ModItemGroup.MAIN)).setAlwaysEdible().setRegistryName("faygo_creme"));
		registry.register(FAYGO_GRAPE = new ItemBeverage(1, 0.0F, false, new Item.Properties().maxStackSize(16).group(ModItemGroup.MAIN)).setAlwaysEdible().setRegistryName("faygo_grape"));
		registry.register(FAYGO_MOON_MIST = new ItemBeverage(1, 0.0F, false, new Item.Properties().maxStackSize(16).group(ModItemGroup.MAIN)).setAlwaysEdible().setRegistryName("faygo_moon_mist"));
		registry.register(FAYGO_PEACH = new ItemBeverage(1, 0.0F, false, new Item.Properties().maxStackSize(16).group(ModItemGroup.MAIN)).setAlwaysEdible().setRegistryName("faygo_peach"));
		registry.register(FAYGO_REDPOP = new ItemBeverage(1, 0.0F, false, new Item.Properties().maxStackSize(16).group(ModItemGroup.MAIN)).setAlwaysEdible().setRegistryName("faygo_redpop"));
		registry.register(IRRADIATED_STEAK = new ItemFood(4, 0.4F, true, new Item.Properties().group(ModItemGroup.MAIN)).setPotionEffect(new PotionEffect(MobEffects.WITHER, 100, 1), 0.9F).setRegistryName("irradiated_steak"));
		registry.register(SURPRISE_EMBRYO = new ItemSurpriseEmbryo(3, 0.2F, false, new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("surprise_embryo"));
		registry.register(UNKNOWABLE_EGG = new ItemUnknowableEgg(3, 0.3F, false, new Item.Properties().maxStackSize(16).group(ModItemGroup.MAIN)).setRegistryName("unknowable_egg"));
		
		registry.register(GOLDEN_GRASSHOPPER = new Item(new Item.Properties().group(ModItemGroup.LANDS)).setRegistryName("golden_grasshopper"));
		registry.register(BUG_NET = new ItemBugNet(new Item.Properties().defaultMaxDamage(64).group(ModItemGroup.LANDS)).setRegistryName("bug_net"));
		registry.register(FROG = new ItemFrog(new Item.Properties().maxStackSize(1).group(ModItemGroup.LANDS)).setRegistryName("frog"));
		registry.register(CARVING_TOOL = new Item(new Item.Properties().maxStackSize(1).group(ModItemGroup.LANDS)).setRegistryName("carving_tool"));
		registry.register(CRUMPLY_HAT = new Item(new Item.Properties().maxStackSize(1).group(ModItemGroup.LANDS)).setRegistryName("crumply_hat"));
		registry.register(STONE_EYEBALLS = new Item(new Item.Properties().group(ModItemGroup.LANDS)).setRegistryName("stone_eyeballs"));
		registry.register(STONE_SLAB = new Item(new Item.Properties().group(ModItemGroup.LANDS)).setRegistryName("stone_slab"));
		//registry.register(SHOP_POSTER = new ItemHanging((world, pos, facing, stack) -> new EntityShopPoster(world, pos, facing, stack, 0), new Item.Properties().maxStackSize(1).group(ModItemGroup.LANDS)).setRegistryName("shop_poster"));
		
		//registry.register(minestuckBucket.setRegistryName("minestuck_bucket"));
		registry.register(OBSIDIAN_BUCKET = new ItemObsidianBucket(new Item.Properties().maxStackSize(1).containerItem(Items.BUCKET).group(ModItemGroup.MAIN)).setRegistryName("obsidian_bucket"));
		registry.register(CAPTCHAROID_CAMERA = new ItemCaptcharoidCamera(new Item.Properties().defaultMaxDamage(64).group(ModItemGroup.MAIN)).setRegistryName("captcharoid_camera"));
		registry.register(GRIMOIRE = new ItemGrimoire(new Item.Properties().maxStackSize(1).group(ModItemGroup.MAIN)).setRegistryName("grimoire"));
		registry.register(LONG_FORGOTTEN_WARHORN = new ItemLongForgottenWarhorn(new Item.Properties().defaultMaxDamage(100)).setRegistryName("long_forgotten_warhorn"));
		registry.register(RAZOR_BLADE = new ItemRazorBlade(new Item.Properties().group(ModItemGroup.MAIN)).setRegistryName("razor_blade"));
		registry.register(UP_STICK = new Item(new Item.Properties().group(ModItemGroup.MAIN).maxStackSize(1)).setRegistryName("uranium_powered_stick"));
		registry.register(IRON_BOAT = new ItemCustomBoat((stack, world, x, y, z) -> new EntityMetalBoat(world, x, y, z, 0), new Item.Properties().group(ModItemGroup.MAIN).maxStackSize(1)).setRegistryName("iron_boat"));
		registry.register(GOLD_BOAT = new ItemCustomBoat((stack, world, x, y, z) -> new EntityMetalBoat(world, x, y, z, 1), new Item.Properties().group(ModItemGroup.MAIN).maxStackSize(1)).setRegistryName("gold_boat"));
		registry.register(THRESH_DVD = new Item(new Item.Properties().maxStackSize(1).group(ModItemGroup.MAIN)).setRegistryName("thresh_dvd"));
		registry.register(GAMEBRO_MAGAZINE = new Item(new Item.Properties().maxStackSize(1).group(ModItemGroup.MAIN)).setRegistryName("gamebro_magazine"));
		registry.register(GAMEGRL_MAGAZINE = new Item(new Item.Properties().maxStackSize(1).group(ModItemGroup.MAIN)).setRegistryName("gamegrl_magazine"));
		registry.register(CREW_POSTER = new ItemHanging((world, pos, facing, stack) -> new EntityCrewPoster(world, pos, facing), new Item.Properties().maxStackSize(1).group(ModItemGroup.MAIN)).setRegistryName("crew_poster"));
		registry.register(SBAHJ_POSTER = new ItemHanging((world, pos, facing, stack) -> new EntitySbahjPoster(world, pos, facing), new Item.Properties().maxStackSize(1).group(ModItemGroup.MAIN)).setRegistryName("sbahj_poster"));
		//registry.register(FAKE_ARMS = new Item(new Item.Properties().maxStackSize(1)).setRegistryName("fake_arms"));
		
		//Music disks
		registry.register(RECORD_EMISSARY_OF_DANCE = new ItemModRecord(13, MinestuckSoundHandler.soundEmissaryOfDance, new Item.Properties().maxStackSize(1).group(ModItemGroup.MAIN)).setRegistryName("record_emissary"));
		registry.register(RECORD_DANCE_STAB = new ItemModRecord(13, MinestuckSoundHandler.soundDanceStabDance, new Item.Properties().maxStackSize(1).group(ModItemGroup.MAIN)).setRegistryName("record_dance_stab"));
		registry.register(RECORD_RETRO_BATTLE = new ItemModRecord(13, MinestuckSoundHandler.soundRetroBattleTheme, new Item.Properties().maxStackSize(1).group(ModItemGroup.MAIN)).setRegistryName("record_retro_battle"));

		/*((ItemMinestuckBucket) minestuckBucket).addBlock(blockOil.getDefaultState());
		((ItemMinestuckBucket) minestuckBucket).addBlock(blockBlood.getDefaultState());
		((ItemMinestuckBucket) minestuckBucket).addBlock(blockBrainJuice.getDefaultState());
		((ItemMinestuckBucket) minestuckBucket).addBlock(blockWatercolors.getDefaultState());
		((ItemMinestuckBucket) minestuckBucket).addBlock(blockEnder.getDefaultState());
		((ItemMinestuckBucket) minestuckBucket).addBlock(blockLightWater.getDefaultState());*/

		/*for(Block block : liquidGrists)
		{
			minestuckBucket.addBlock(block.getDefaultState());
		}*/
		
		ItemWeapon.addToolMaterial(ToolType.PICKAXE, Arrays.asList(Material.IRON, Material.ANVIL, Material.ROCK));
		ItemWeapon.addToolMaterial(ToolType.AXE, Arrays.asList(Material.WOOD, Material.PLANTS, Material.VINE));
		ItemWeapon.addToolMaterial(ToolType.SHOVEL, Arrays.asList(Material.SNOW, Material.CRAFTED_SNOW, Material.CLAY, Material.GRASS, Material.GROUND, Material.SAND));
		//ItemWeapon.addToolMaterial("sword", Arrays.asList(Material.WEB));
		ItemWeapon.addToolMaterial(ModItemTypes.SICKLE_TOOL, Arrays.asList(Material.WEB, Material.LEAVES, Material.PLANTS, Material.VINE));
	}
	
	private static Item registerItemBlock(IForgeRegistry<Item> registry, Block block)
	{
		return registerItemBlock(registry, new ItemBlock(block, new Item.Properties()));
	}
	
	private static Item registerItemBlock(IForgeRegistry<Item> registry, Block block, ItemGroup group)
	{
		return registerItemBlock(registry, new ItemBlock(block, new Item.Properties().group(group)));
	}
	
	private static Item registerItemBlock(IForgeRegistry<Item> registry, ItemBlock item)
	{
		if(item.getBlock().getRegistryName() == null)
			throw new IllegalArgumentException(String.format("The provided itemblock %s has a block without a registry name!", item.getBlock()));
		registry.register(item.setRegistryName(item.getBlock().getRegistryName()));
		return item;
	}
}