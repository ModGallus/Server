package org.areillan.game.content.skill.member.construction;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a building hotspot.
 * @author Emperor
 * @author Mod Ethan
 *
 */
public enum BuildHotspot {

	/**
	 * Low level garden hotspots.
	 */
	CENTREPIECE_1(15361, BuildHotspotType.INDIVIDUAL,Decoration.PORTAL, Decoration.ROCK, Decoration.POND, Decoration.IMP_STATUE, Decoration.DUNGEON_ENTRANCE),
	BIG_TREE_1(15362, BuildHotspotType.INDIVIDUAL, Decoration.BIG_DEAD_TREE, Decoration.BIG_TREE, Decoration.BIG_OAK_TREE, Decoration.BIG_WILLOW_TREE, Decoration.BIG_MAPLE_TREE, Decoration.BIG_YEW_TREE, Decoration.BIG_MAGIC_TREE),
	TREE_1(15363, BuildHotspotType.INDIVIDUAL, Decoration.DEAD_TREE, Decoration.TREE, Decoration.OAK_TREE, Decoration.WILLOW_TREE, Decoration.MAPLE_TREE, Decoration.YEW_TREE, Decoration.MAGIC_TREE),
	BIG_PLANT_1(15364, BuildHotspotType.INDIVIDUAL, Decoration.FERN, Decoration.BUSH, Decoration.TALL_PLANT),
	BIG_PLANT_2(15365, BuildHotspotType.INDIVIDUAL, Decoration.SHORT_PLANT, Decoration.LARGE_LEAF_PLANT, Decoration.HUGE_PLANT),
	SMALL_PLANT_1(15366, BuildHotspotType.INDIVIDUAL, Decoration.PLANT, Decoration.SMALL_FERN, Decoration.FERN_SP),
	SMALL_PLANT_2(15367, BuildHotspotType.INDIVIDUAL, Decoration.DOCK_LEAF, Decoration.THISTLE, Decoration.REEDS),
	
	/**
	 * Formal Garden hotspots
	 */
	CENTREPIECE_2(15368, BuildHotspotType.INDIVIDUAL, Decoration.PORTAL, Decoration.GAZEBO, Decoration.DUNGEON_ENTRANCE, Decoration.SMALL_FOUNTAIN, Decoration.LARGE_FOUNTAIN, Decoration.POSH_FOUNTAIN),
	
	/**
	 * Low level Parlor hotspots.
	 */
	CHAIRS_1(15410, BuildHotspotType.INDIVIDUAL, Decoration.CRUDE_CHAIR,Decoration.WOODEN_CHAIR, Decoration.ROCKING_CHAIR, Decoration.OAK_CHAIR, Decoration.OAK_ARMCHAIR, Decoration.TEAK_ARMCHAIR, Decoration.MAHOGANY_ARMCHAIR),
	CHAIRS_2(15411, BuildHotspotType.INDIVIDUAL, Decoration.CRUDE_CHAIR, Decoration.WOODEN_CHAIR,Decoration.ROCKING_CHAIR, Decoration.OAK_CHAIR, Decoration.OAK_ARMCHAIR, Decoration.TEAK_ARMCHAIR, Decoration.MAHOGANY_ARMCHAIR), 
	CHAIRS_3(15412, BuildHotspotType.INDIVIDUAL, Decoration.CRUDE_CHAIR, Decoration.WOODEN_CHAIR,Decoration.ROCKING_CHAIR, Decoration.OAK_CHAIR, Decoration.OAK_ARMCHAIR, Decoration.TEAK_ARMCHAIR, Decoration.MAHOGANY_ARMCHAIR),
	FIREPLACE(15418, BuildHotspotType.INDIVIDUAL, Decoration.CLAY_FIREPLACE, Decoration.STONE_FIREPLACE,Decoration.MARBLE_FIREPLACE), 
	FIREPLACE2(15267, BuildHotspotType.INDIVIDUAL, Decoration.CLAY_FIREPLACE, Decoration.STONE_FIREPLACE,Decoration.MARBLE_FIREPLACE), 
	CURTAINS(15419, BuildHotspotType.INDIVIDUAL, Decoration.TORN_CURTAINS, Decoration.CURTAINS, Decoration.OPULENT_CURTAINS), 
	BOOKCASE(15416,  BuildHotspotType.INDIVIDUAL, Decoration.WOODEN_BOOKCASE, Decoration.OAK_BOOKCASE, Decoration.MAHOGANY_BOOKCASE),
	RUG(15415, BuildHotspotType.LINKED, Decoration.BROWN_RUG_CORNER, Decoration.RED_RUG_CORNER, Decoration.OPULENT_RUG_CORNER),
	RUG2(15414, BuildHotspotType.LINKED, Decoration.BROWN_RUG_END, Decoration.RED_RUG_END, Decoration.OPULENT_RUG_END),
	RUG3(15413, BuildHotspotType.LINKED, Decoration.BROWN_RUG_CENTER, Decoration.RED_RUG_CENTER, Decoration.OPULENT_RUG_CENTER),
	
	/**
	 * Low level Kitchen hotspots.
	 */
	LARDER(15403, BuildHotspotType.INDIVIDUAL, Decoration.WOODEN_LARDER, Decoration.OAK_LARDER,Decoration.TEAK_LARDER), 
	SINK(15404, BuildHotspotType.INDIVIDUAL, Decoration.PUMP_AND_DRAIN, Decoration.PUMP_AND_TUB, Decoration.SINK), 
	KITCHEN_TABLE(15405, BuildHotspotType.INDIVIDUAL, Decoration.KITCHEN_WOODEN_TABLE,Decoration.KITCHEN_OAK_TABLE, Decoration.KITCHEN_TEAK_TABLE), 
	CAT_BLANKET(15402, BuildHotspotType.INDIVIDUAL, Decoration.CAT_BLANKET, Decoration.CAT_BASKET,Decoration.CAST_BASKET_CUSHIONED), 
	STOVE(15398, BuildHotspotType.INDIVIDUAL, Decoration.BASIC_FIREPIT, Decoration.FIREPIT_WITH_HOOK, Decoration.FIREPIT_WITH_POT, Decoration.SMALL_OVEN, Decoration.LARGE_OVEN, Decoration.BASIC_RANGE, Decoration.FANCY_RANGE), 
	SHELVES(15400, BuildHotspotType.INDIVIDUAL, Decoration.WOODEN_SHELVES_1,Decoration.WOODEN_SHELVES_2, Decoration.WOODEN_SHELVES_3,Decoration.OAK_SHELVES_1, Decoration.OAK_SHELVES_2, Decoration.TEAK_SHELVES_1, Decoration.TEAK_SHELVES_2), 
	SHELVES_2(15399, BuildHotspotType.INDIVIDUAL, Decoration.WOODEN_SHELVES_1,Decoration.WOODEN_SHELVES_2, Decoration.WOODEN_SHELVES_3,Decoration.OAK_SHELVES_1, Decoration.OAK_SHELVES_2,Decoration.TEAK_SHELVES_1, Decoration.TEAK_SHELVES_2), 
	BARRELS(15401, BuildHotspotType.INDIVIDUAL, Decoration.BASIC_BEER_BARREL, Decoration.CIDER_BARREL,Decoration.ASGARNIAN_ALE_BARREL, Decoration.GREENMANS_ALE_BARREL,Decoration.DRAGON_BITTER_BARREL, Decoration.CHEFS_DELIGHT_BARREL),
	
	/**
	 * Low-level Dining hotspots.
	 */
	FIREPLACE_DINING(15301, BuildHotspotType.INDIVIDUAL, Decoration.CLAY_FIREPLACE, Decoration.STONE_FIREPLACE, Decoration.MARBLE_FIREPLACE), 
	DINING_TABLE(15298, BuildHotspotType.INDIVIDUAL, Decoration.DINING_TABLE_WOOD, Decoration.DINING_TABLE_OAK, Decoration.DINING_TABLE_CARVED_OAK, Decoration.DINING_TABLE_TEAK,Decoration.DINING_TABLE_CARVED_TEAK, Decoration.DINING_TABLE_MAHOGANY,Decoration.DINING_TABLE_OPULENT), 
	DINING_CURTAINS(15302, BuildHotspotType.INDIVIDUAL, Decoration.TORN_CURTAINS, Decoration.CURTAINS, Decoration.OPULENT_CURTAINS), 
	DINING_BENCH_1(15300, BuildHotspotType.RECURSIVE, Decoration.BENCH_WOODEN, Decoration.BENCH_OAK, Decoration.BENCH_CARVED_OAK, Decoration.BENCH_TEAK, Decoration.BENCH_CARVED_TEAK, Decoration.BENCH_MAHOGANY, Decoration.BENCH_GILDED), 
	DINING_BENCH_2(15299, BuildHotspotType.RECURSIVE, Decoration.BENCH_WOODEN, Decoration.BENCH_OAK, Decoration.BENCH_CARVED_OAK, Decoration.BENCH_TEAK, Decoration.BENCH_CARVED_TEAK, Decoration.BENCH_MAHOGANY,Decoration.BENCH_GILDED), 
	ROPE_BELL_PULL(15304, BuildHotspotType.INDIVIDUAL, Decoration.ROPE_PULL, Decoration.BELL_PULL, Decoration.FANCY_BELL_PULL),
	WALL_DECORATION(15303, BuildHotspotType.INDIVIDUAL, Decoration.OAK_DECORATION, Decoration.TEAK_DECORATION, Decoration.GILDED_DECORATION), 
	
	/**
	 * Low-level Work shop hotspots.
	 */
	REPAIR(15448, BuildHotspotType.INDIVIDUAL, Decoration.REPAIR_BENCH, Decoration.WHETSTONE, Decoration.ARMOUR_STAND), 
	WORKBENCH(15439, BuildHotspotType.INDIVIDUAL, Decoration.WORKBENCH_WOODEN, Decoration.WORKBENCH_OAK,Decoration.WORKBENCH_STEEL_FRAME, Decoration.WORKBENCH_WITH_VICE,Decoration.WORKBENCH_WITH_LATHE), 
	CRAFTING(15441, BuildHotspotType.INDIVIDUAL, Decoration.CRAFTING_TABLE_1, Decoration.CRAFTING_TABLE_2,Decoration.CRAFTING_TABLE_3, Decoration.CRAFTING_TABLE_4), 
	TOOL1(15443, BuildHotspotType.INDIVIDUAL, Decoration.TOOL_STORE_1, Decoration.TOOL_STORE_2,	Decoration.TOOL_STORE_3, Decoration.TOOL_STORE_4,	Decoration.TOOL_STORE_5), 
	TOOL2(15444, BuildHotspotType.INDIVIDUAL, Decoration.TOOL_STORE_1, Decoration.TOOL_STORE_2,Decoration.TOOL_STORE_3, Decoration.TOOL_STORE_4,Decoration.TOOL_STORE_5), 
	TOOL3(15445, BuildHotspotType.INDIVIDUAL, Decoration.TOOL_STORE_1, Decoration.TOOL_STORE_2,Decoration.TOOL_STORE_3, Decoration.TOOL_STORE_4,Decoration.TOOL_STORE_5),
	TOOL4(15446, BuildHotspotType.INDIVIDUAL, Decoration.TOOL_STORE_1, Decoration.TOOL_STORE_2,Decoration.TOOL_STORE_3, Decoration.TOOL_STORE_4,Decoration.TOOL_STORE_5), 
	TOOL5(15447, BuildHotspotType.INDIVIDUAL, Decoration.TOOL_STORE_1, Decoration.TOOL_STORE_2,Decoration.TOOL_STORE_3, Decoration.TOOL_STORE_4,Decoration.TOOL_STORE_5), 
	HERALDRY(15450, BuildHotspotType.INDIVIDUAL, Decoration.PLUMING_STAND, Decoration.SHIELD_EASEL,Decoration.BANNER_EASEL),
	
	/**
	 * Bedroom hotspots.
	 */
	BED(15260, BuildHotspotType.INDIVIDUAL, Decoration.WOODEN_BED, Decoration.OAK_BED, Decoration.LARGE_OAK_BED, Decoration.TEAK_BED, Decoration.LARGE_TEAK_BED, Decoration.FOUR_POSTER, Decoration.GILDED_FOUR_POSTER),
	CLOCK(15268, BuildHotspotType.INDIVIDUAL, Decoration.OAK_CLOCK, Decoration.TEAK_CLOCK, Decoration.GILDED_CLOCK),
	DRESSER(15262, BuildHotspotType.INDIVIDUAL, Decoration.SHAVING_STAND, Decoration.OAK_SHAVING_STAND, Decoration.OAK_DRESSER, Decoration.TEAK_DRESSER, Decoration.FANCY_TEAK_DRESSER, Decoration.MAHOGANY_DRESSER, Decoration.GILDED_DRESSER),
	DRAWERS(15261, BuildHotspotType.INDIVIDUAL, Decoration.SHOE_BOX, Decoration.OAK_DRAWERS, Decoration.OAK_WARDROBE, Decoration.TEAK_DRAWERS, Decoration.TEAK_WARDROBE, Decoration.MAHOGANY_WARDROBE, Decoration.GILDED_WARDROBE),
	
	/**
	 * Formal garden hotspots.
	 */
	FENCING(15369, BuildHotspotType.RECURSIVE, Decoration.BOUNDARY_STONES, Decoration.WOODEN_FENCE, Decoration.STONE_WALL, Decoration.IRON_RAILINGS, Decoration.PICKET_FENCE, Decoration.GARDEN_FENCE, Decoration.MARBLE_WALL),
	SMALL_PLANT1(15375, BuildHotspotType.INDIVIDUAL, Decoration.ROSEMARY, Decoration.DAFFODILS, Decoration.BLUEBALLZ),
	BIG_PLANT1(15373, BuildHotspotType.INDIVIDUAL, Decoration.SUNFLOWER, Decoration.MARIGOLDS, Decoration.ROSES),
	SMALL_PLANT2(15376, BuildHotspotType.INDIVIDUAL, Decoration.ROSEMARY, Decoration.DAFFODILS, Decoration.BLUEBALLZ),
	BIG_PLANT2(15374, BuildHotspotType.INDIVIDUAL, Decoration.SUNFLOWER, Decoration.MARIGOLDS, Decoration.ROSES),
	HEDGE_1(15370, BuildHotspotType.LINKED, Decoration.THORNY_HEDGE, Decoration.NICE_HEDGE, Decoration.SMALL_BOX_HEDGE, Decoration.TOPIARY_HEDGE, Decoration.FANCY_HEDGE, Decoration.TALL_FANCY_HEDGE, Decoration.TALL_BOX_HEDGE),
	HEDGE_2(15371, BuildHotspotType.LINKED, Decoration.THORNY_HEDGE, Decoration.NICE_HEDGE, Decoration.SMALL_BOX_HEDGE, Decoration.TOPIARY_HEDGE, Decoration.FANCY_HEDGE, Decoration.TALL_FANCY_HEDGE, Decoration.TALL_BOX_HEDGE),
	HEDGE_3(15372, BuildHotspotType.LINKED, Decoration.THORNY_HEDGE, Decoration.NICE_HEDGE, Decoration.SMALL_BOX_HEDGE, Decoration.TOPIARY_HEDGE, Decoration.FANCY_HEDGE, Decoration.TALL_FANCY_HEDGE, Decoration.TALL_BOX_HEDGE),
	
	/**
	 * Skill hall hotspots.
	 */
	ARMOUR_SPACE(15385, BuildHotspotType.INDIVIDUAL, Decoration.MITHRIL_ARMOUR, Decoration.ADAMANT_ARMOUR, Decoration.RUNE_ARMOUR),
	ARMOUR_SPACE2(15384, BuildHotspotType.INDIVIDUAL, Decoration.MITHRIL_ARMOUR, Decoration.ADAMANT_ARMOUR, Decoration.RUNE_ARMOUR),
	HEAD_TROPHY(15382, BuildHotspotType.INDIVIDUAL, Decoration.CRAWLING_HAND, Decoration.COCKATRICE_HEAD, Decoration.BASILISK_HEAD, Decoration.KURASK_HEAD, Decoration.ABYSSAL_DEMON_HEAD, Decoration.KBD_HEAD, Decoration.KQ_HEAD),
	RUNE_CASE(15386, BuildHotspotType.INDIVIDUAL, Decoration.RUNE_CASE1, Decoration.RUNE_CASE2),
	FISHING_TROPHY(15383, BuildHotspotType.INDIVIDUAL, Decoration.MOUNTED_BASS, Decoration.MOUNTED_SWORDFISH, Decoration.MOUNTED_SHARK),
	
	/**
	 * Games room hotspots.
	 */
	RANGING_GAME(15346, BuildHotspotType.INDIVIDUAL, Decoration.HOOP_AND_STICK, Decoration.DARTBOARD, Decoration.ARCHERY_TARGET),
	ATTACK_STONE(15344, BuildHotspotType.INDIVIDUAL, Decoration.CLAY_STONE, Decoration.LIMESTONE_STONE, Decoration.MARBLE_STONE),
	PRIZE_CHEST(15343, BuildHotspotType.INDIVIDUAL, Decoration.OAK_CHEST, Decoration.TEAK_CHEST, Decoration.MAHOGANY_CHEST),
	ELEMENTAL_BALANCE(15345, BuildHotspotType.INDIVIDUAL, Decoration.BALANCE_1, Decoration.BALANCE_2, Decoration.BALANCE_3),
	GAME_SPACE(15342, BuildHotspotType.INDIVIDUAL, Decoration.JESTER, Decoration.TREASURE_HUNT, Decoration.HANGMAN),
	
	/**
	 * Portal room hotspots.
	 */
	TELEPORT_FOCUS(15409, BuildHotspotType.INDIVIDUAL, Decoration.TELEPORT_FOCUS, Decoration.GREATER_TELEPORT_FOCUS, Decoration.SCRYING_POOL),
	PORTAL1(15406, BuildHotspotType.INDIVIDUAL, Decoration.TEAK_PORTAL, Decoration.MAHOGANY_PORTAL, Decoration.MARBLE_PORTAL),
	PORTAL2(15407, BuildHotspotType.INDIVIDUAL, Decoration.TEAK_PORTAL, Decoration.MAHOGANY_PORTAL, Decoration.MARBLE_PORTAL),
	PORTAL3(15408, BuildHotspotType.INDIVIDUAL, Decoration.TEAK_PORTAL, Decoration.MAHOGANY_PORTAL, Decoration.MARBLE_PORTAL),
	
	/**
	 * Quest Hall hotspots.
	 */
	GUILD_TROPHY(15394, BuildHotspotType.INDIVIDUAL, Decoration.ANTIDRAGON_SHIELD, Decoration.AMULET_OF_GLORY, Decoration.CAPE_OF_LEGENDS),
	PORTRAIT(15392, BuildHotspotType.INDIVIDUAL, Decoration.KING_ARTHUR, Decoration.ELENA, Decoration.GIANT_DWARF, Decoration.MISCELLANIANS),
	LANDSCAPE(15393, BuildHotspotType.INDIVIDUAL, Decoration.LUMBRIDGE, Decoration.THE_DESERT, Decoration.MORYTANIA, Decoration.KARAMJA, Decoration.ISAFDAR),
	SWORD(15395, BuildHotspotType.INDIVIDUAL, Decoration.SILVERLIGHT, Decoration.EXCALIBUR, Decoration.DARKLIGHT),
	MAP(15396, BuildHotspotType.INDIVIDUAL, Decoration.SMALL_MAP, Decoration.MEDIUM_MAP, Decoration.LARGE_MAP),
	BOOKCASE2(15397, BuildHotspotType.INDIVIDUAL, Decoration.WOODEN_BOOKCASE, Decoration.OAK_BOOKCASE, Decoration.MAHOGANY_BOOKCASE),
	
	/**
	 * Combat room hotspots.
	 */
	WALL_DECORATION2(15297, BuildHotspotType.INDIVIDUAL, Decoration.OAK_DECORATION, Decoration.TEAK_DECORATION, Decoration.GILDED_DECORATION), 
	STORAGE_SPACE(15296, BuildHotspotType.INDIVIDUAL, Decoration.GLOVE_RACK, Decoration.WEAPONS_RACK, Decoration.EXTRA_WEAPONS_RACK), 

	/**
	 * Study hotspots.
	 */
	GLOBE(15421, BuildHotspotType.INDIVIDUAL, Decoration.GLOBE, Decoration.ORNAMENTAL_GLOBE, Decoration.LUNAR_GLOBE, Decoration.CELESTIAL_GLOBE, Decoration.ARMILLARY_SPHERE, Decoration.SMALL_ORREY, Decoration.LARGE_ORREY), 
	LECTERN(15420, BuildHotspotType.INDIVIDUAL, Decoration.OAK_LECTERN, Decoration.EAGLE_LECTERN, Decoration.DEMON_LECTERN, Decoration.TEAK_EAGLE_LECTERN, Decoration.TEAK_DEMON_LECTERN, Decoration.MAHOGANY_EAGLE_LECTERN, Decoration.MAHOGANY_DEMON_LECTERN), 
	CRYSTAL_BALL(15422, BuildHotspotType.INDIVIDUAL, Decoration.CRYSTAL_BALL, Decoration.ELEMENTAL_SPHERE, Decoration.CRYSTAL_OF_POWER), 
	BOOKCASE3(15425, BuildHotspotType.INDIVIDUAL, Decoration.WOODEN_BOOKCASE, Decoration.OAK_BOOKCASE, Decoration.MAHOGANY_BOOKCASE),
	WALL_CHART(15423, BuildHotspotType.INDIVIDUAL, Decoration.ALCHEMICAL_CHART, Decoration.ASTRONOMICAL_CHART, Decoration.INFERNAL_CHART),
	TELESCOPE(15424, BuildHotspotType.INDIVIDUAL, Decoration.TELESCOPE1, Decoration.TELESCOPE2, Decoration.TELESCOPE3),
	
	/**
	 * Chapel hotspots.
	 */
	ALTAR(15270, BuildHotspotType.INDIVIDUAL, Decoration.OAK_ALTAR, Decoration.TEAK_ALTAR, Decoration.CLOTH_ALTAR, Decoration.MAHOGANY_ALTAR, Decoration.LIMESTONE_ALTAR, Decoration.MARBLE_ALTAR, Decoration.GILDED_ALTAR), 
	STATUE(15275, BuildHotspotType.INDIVIDUAL, Decoration.SMALL_STATUE, Decoration.MEDIUM_STATUE, Decoration.LARGE_STATUE), 
	MUSICAL(15276, BuildHotspotType.INDIVIDUAL, Decoration.WINDCHIMES, Decoration.BELLS, Decoration.ORGAN), 
	ICON(15269, BuildHotspotType.INDIVIDUAL, Decoration.SARADOMIN_SYMBOL, Decoration.ZAMORAK_SYMBOL, Decoration.GUTHIX_SYMBOL, Decoration.SARADOMIN_ICON, Decoration.ZAMORAK_ICON, Decoration.GUTHIX_ICON, Decoration.ICON_OF_BOB), 
	BURNERS(15271, BuildHotspotType.RECURSIVE, Decoration.STEEL_TORCHES, Decoration.WOODEN_TORCHES, Decoration.STEEL_CANDLESTICKS, Decoration.GOLD_CANDLESTICKS, Decoration.INCENSE_BURNERS, Decoration.MAHOGANY_BURNERS, Decoration.MARBLE_BURNERS), 
	CHAPEL_WINDOW(13730, BuildHotspotType.RECURSIVE, Decoration.SHUTTERED_WINDOW, Decoration.DECORATIVE_WINDOW, Decoration.STAINED_GLASS), 
	
	
	/**
	 * Stairways.
	 */
	STAIRWAYS(15380, BuildHotspotType.INDIVIDUAL, Decoration.OAK_STAIRCASE, Decoration.TEAK_STAIRCASE, Decoration.SPIRAL_STAIRCASE, Decoration.MARBLE_STAIRCASE, Decoration.MARBLE_SPIRAL),
	STAIRWAYS2(15390, BuildHotspotType.INDIVIDUAL, Decoration.OAK_STAIRCASE, Decoration.TEAK_STAIRCASE, Decoration.SPIRAL_STAIRCASE, Decoration.MARBLE_STAIRCASE, Decoration.MARBLE_SPIRAL),
	
	
	/**
	 * Window hotspots.
	 */
	WINDOW(13830, BuildHotspotType.INDIVIDUAL, Decoration.BASIC_WOOD_WINDOW, Decoration.BASIC_STONE_WINDOW, Decoration.WHITEWASHED_STONE_WINDOW, Decoration.FREMENNIK_WINDOW, Decoration.TROPICAL_WOOD_WINDOW, Decoration.FANCY_STONE_WINDOW),
	;

	/**
	 * The object id.
	 */
	private final int objectId;
	
	/**
	 * The decorations to build on this hotspot.
	 */
	private final Decoration[] decorations;
	
	/**
	 * The hotspot type (-1=individual, 0=recursive, 1=linkedHotspots)
	 */
	private final BuildHotspotType type;
	
	/**
	 * The linked hotspots
	 */
	private static List<BuildHotspot[]> linkedHotspots = new ArrayList<BuildHotspot[]>();
	
	/**
	 * The linked hotspots
	 */
	static {
		linkedHotspots.add(new BuildHotspot[]{RUG, RUG2, RUG3});
	}

	
	/**
	 * Constructs a new {@code BuildHotspot} {@code Object}.
	 * @param objectId The object id.
	 * @param decorations The decoration.
	 */
	private BuildHotspot(int objectId, BuildHotspotType type, Decoration... decorations) {
		this.objectId = objectId;
		this.type = type;
		this.decorations = decorations;
		
	}

	/**
	 * Gets the building hotspot for the given object id.
	 * @param id The object id.
	 * @return The building hotspot.
	 */
	public static BuildHotspot forId(int id) {
		for (BuildHotspot spot : values()) {
			if (spot.getObjectId() == id) {
				return spot;
			}
		}
		return null;
	}
	
	/**
	 * Gets the linked hotspots (if any)
	 * @param b - the buildhotspot to find linked hotspots
	 * @return BuildHotspot[] or null
	 */
	public static BuildHotspot[] getLinkedHotspots(BuildHotspot b) {
		for (BuildHotspot[] list : linkedHotspots) {
			for (BuildHotspot bh : list) {
				if (bh == b) {
					return list;
				}
			}
		}	
		return null;
	}

	/**
	 * Gets the decoration index.
	 * @param deco The decoration.
	 * @return The index.
	 */
	public int getDecorationIndex(Decoration deco) {
		for (int i = 0; i < decorations.length; i++) {
			if (decorations[i] == deco) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Gets the objectId.
	 * @return The objectId.
	 */
	public int getObjectId() {
		return objectId;
	}
	
	/**
	 * Gets the type
	 * @return type
	 */
	public BuildHotspotType getType() {
		return type;
	}

	/**
	 * Gets the decorations.
	 * @return The decorations.
	 */
	public Decoration[] getDecorations() {
		return decorations;
	}

}