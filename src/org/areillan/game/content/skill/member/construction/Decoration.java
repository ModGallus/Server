package org.areillan.game.content.skill.member.construction;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.item.Item;
import org.areillan.game.world.map.Location;

/**
 * Represents the decorations.
 * @author Emperor
 *
 */
public enum Decoration {

	/**
	 * Garden centrepiece decorations.
	 */
	PORTAL(13405, 8168, 1, 100.0, new Item(2351, 10)),
	ROCK(13406, 8169, 5, 100.0, new Item(3420, 5)),
	POND(13407, 8170, 10, 100.0, new Item(1761, 10)),
	IMP_STATUE(13408, 8171, 15, 150.0, new Item(3420, 5), new Item(1761, 5)),
	DUNGEON_ENTRANCE(13409, 8172, 70, 500.0, new Item(8786)),

	/**
	 * Garden big tree decorations.
	 */
	BIG_DEAD_TREE(13411, 8173, 5, 31.0, new Item(8417)),
	BIG_TREE(13412, 8174, 10, 44.0, new Item(8419)),
	BIG_OAK_TREE(13413, 8175, 15, 70.0, new Item(8421)),
	BIG_WILLOW_TREE(13414, 8176, 30, 100.0, new Item(8423)),
	BIG_MAPLE_TREE(13415, 8177, 45, 122.0, new Item(8425)),
	BIG_YEW_TREE(13416, 8178, 60, 141.0, new Item(8427)),
	BIG_MAGIC_TREE(13417, 8179, 75, 223.0, new Item(8429)),
	
	/**
	 * Garden tree decorations.
	 */
	DEAD_TREE(13418, 8173, 5, 31.0, new Item(8417)),
	TREE(13419, 8174, 10, 44.0, new Item(8419)),
	OAK_TREE(13420, 8175, 15, 70.0, new Item(8421)),
	WILLOW_TREE(13421, 8176, 30, 100.0, new Item(8423)),
	MAPLE_TREE(13422, 8177, 45, 122.0, new Item(8425)),
	YEW_TREE(13423, 8178, 60, 141.0, new Item(8427)),
	MAGIC_TREE(13424, 8179, 75, 223.0, new Item(8429)), 
	
	/**
	 * Garden big plant 1 decorations.
	 */
	FERN(13425, 8186, 1, 31.0, new Item(8431)),
	BUSH(13426, 8187, 6, 70.0, new Item(8433)),
	TALL_PLANT(13427, 8188, 12, 100.0, new Item(8435)),
	
	/**
	 * Garden big plant 2 decorations.
	 */
	SHORT_PLANT(13428, 8189, 1, 31.0, new Item(8431)),
	LARGE_LEAF_PLANT(13429, 8190, 6, 70.0, new Item(8433)),
	HUGE_PLANT(13430, 8191, 12, 100.0, new Item(8435)), 

	/**
	 * Garden small plant 1 decorations.
	 */
	PLANT(13431, 8180, 1, 31.0, new Item(8431)), 
	SMALL_FERN(13432, 8181, 6, 70.0, new Item(8433)), 
	FERN_SP(13433, 8182, 12, 100.0, new Item(8435)), 

	/**
	 * Garden small plant 2 decorations.
	 */
	DOCK_LEAF(13434, 8183, 1, 31.0, new Item(8431)), 
	THISTLE(13435, 8184, 6, 70.0, new Item(8433)), 
	REEDS(13436, 8185, 12, 100.0, new Item(8435)), 
	/**
	 * Parlour chair spot
	 */
	CRUDE_CHAIR(13581, 8309, 1, 66.0, new Item(960, 2)),
	WOODEN_CHAIR(13582, 8310, 8, 96.0, new Item(960, 3)),
	ROCKING_CHAIR(13583, 8311, 14, 96.0, new Item(960, 3)),
	OAK_CHAIR(13584, 8312, 19, 120.0, new Item(8778, 2)),
	OAK_ARMCHAIR(13585, 8313, 26, 180.0, new Item(8778, 3)),
	TEAK_ARMCHAIR(13586, 8314, 35, 180.0, new Item(8780, 2)),
	MAHOGANY_ARMCHAIR(13587, 8315, 50, 280.0, new Item(8782, 2)),

	/**
	 * Rugs rugs rugs
	 */
	BROWN_RUG_CORNER(13588, 8316, 2, 30.0, new Item(8790, 2)),
	RED_RUG_CORNER(13591, 8317, 13, 60.0, new Item(8790, 4)),
	OPULENT_RUG_CORNER(13594, 8318, 65, 360.0, new Item[]{ new Item(8790, 4), new Item(4692, 1)}),
	
	BROWN_RUG_END(13589, 8316, 2, 30.0, new Item(8790, 2)),
	RED_RUG_END(13592, 8317, 13, 60.0, new Item(8790, 4)),
	OPULENT_RUG_END(13595, 8318, 65, 360.0, new Item[]{ new Item(8790, 4), new Item(4692, 1)}),
	
	BROWN_RUG_CENTER(13590, 8316, 2, 30.0, new Item(8790, 2)),
	RED_RUG_CENTER(13593, 8317, 13, 60.0, new Item(8790, 4)),
	OPULENT_RUG_CENTER(13596, 8318, 65, 360.0, new Item[]{ new Item(8790, 4), new Item(4692, 1)}),

	/**
	 * Parlour fireplaces
	 */
	CLAY_FIREPLACE(13609, 8325, 3, 30.0, new Item(1761, 3)),
	STONE_FIREPLACE(13611, 8326, 33, 40.0, new Item(3420, 2)),
	MARBLE_FIREPLACE(13613, 8327, 63, 500.0, new Item(8786, 1)),

	/**
	 * Parlour curtain spot
	 */
	TORN_CURTAINS(13603, 8322, 2, 132.0, new Item[]{ new Item(960, 3), new Item(8790, 3)}),
	CURTAINS(13604, 8323, 18, 225.0, new Item[]{ new Item(8778, 3), new Item(8790, 3)}),
	OPULENT_CURTAINS(13605, 8324, 40, 315.0, new Item[]{ new Item(8780, 3), new Item(8790, 3)}),

	/**
	 * Parlour bookcases
	 */
	WOODEN_BOOKCASE(13597, 8319, 4, 132.0, new Item(960, 4)),
	OAK_BOOKCASE(13598, 8320, 29, 225.0, new Item(8778, 3)),
	MAHOGANY_BOOKCASE(13599, 8321, 40, 315.0, new Item(8782, 3)),

	
	/**
	 * Kitchen Beer Barrels
	 * TODO: These also require cooking levels!
	 * Basic: 1, Cider: 14, Asgarnian: 24, Greenman's: 29, D.Bitter: 39, Chef's: 54
	 *
	 */
	BASIC_BEER_BARREL(13568, 8239, 7, 87.0, new Item(960, 3)),
	CIDER_BARREL(13569, 8240, 12, 91.0, new Item[]{ new Item(960, 3), new Item(5763, 8)}),
	ASGARNIAN_ALE_BARREL(13570, 8241, 18, 184.0, new Item[]{ new Item(8778, 3), new Item(1905, 8)}),
	GREENMANS_ALE_BARREL(13571, 8242, 26, 184.0, new Item[]{ new Item(8778, 3), new Item(1909, 8)}),
	DRAGON_BITTER_BARREL(13572, 8243, 36, 224.0, new Item[]{ new Item(8778, 3), new Item(1911, 8), new Item(2353, 2)}),
	CHEFS_DELIGHT_BARREL(13573, 8244, 48, 224.0, new Item[]{ new Item(8778, 3), new Item(5755, 8), new Item(2353, 2)}),
	
	
	/**
	 * Kitchen Tables!
	 */
	KITCHEN_WOODEN_TABLE(13577, 8246, 12, 87.0, new Item(960, 3)),
	KITCHEN_OAK_TABLE(13578, 8247, 32, 180.0, new Item(8778, 3)),
	KITCHEN_TEAK_TABLE(13579, 8248, 52, 270.0, new Item(8780, 3)),
	
	
	/**
	 * Kitchen Stoves
	 */
	BASIC_FIREPIT(13528, 8216, 5, 40.0, new Item[]{ new Item(1761, 2), new Item(2353, 1)}),
	FIREPIT_WITH_HOOK(13529, 8217, 11, 60.0, new Item[]{ new Item(1761, 2), new Item(2353, 2)}),
	FIREPIT_WITH_POT(13531, 8218, 17, 80.0, new Item[]{ new Item(1761, 2), new Item(2353, 3)}),
	SMALL_OVEN(13533, 8219, 24, 80.0, new Item(2353, 4)),
	LARGE_OVEN(13536, 8220, 29, 100.0, new Item(2353, 5)),
	BASIC_RANGE(13539, 8221, 34, 120.0, new Item(2353, 6)),
	FANCY_RANGE(13542, 8222, 42, 160.0, new Item(2353, 8)),
	
	/**
	 * Kitchen larders
	 */
	WOODEN_LARDER(13565, 8233, 9, 228.0, new Item(960, 8)),
	OAK_LARDER(13566, 8234, 33, 480.0, new Item(8778, 8)),
	TEAK_LARDER(13567, 8235, 43, 750.0, new Item[]{ new Item(8780, 8), new Item(8790, 2)}),
	
	
	/**
	 * Kitchen shelves
	 */
	WOODEN_SHELVES_1(13545, 8223, 6, 87.0, new Item(960, 3)),
	WOODEN_SHELVES_2(13546, 8224, 12, 147.0, new Item[]{ new Item(960, 3), new Item(1761, 6)}),
	WOODEN_SHELVES_3(13547, 8225, 23, 147.0, new Item[]{ new Item(960, 3), new Item(1761, 6)}),
	OAK_SHELVES_1(13548, 8226, 34, 240.0, new Item[]{ new Item(8778, 3), new Item(1761, 6)}),
	OAK_SHELVES_2(13549, 8227, 45, 240.0, new Item[]{ new Item(8778, 3), new Item(1761, 6)}),
	TEAK_SHELVES_1(13550, 8228, 56, 330.0, new Item[]{ new Item(8780, 3), new Item(1761, 6)}),
	TEAK_SHELVES_2(13551, 8229, 67, 930.0, new Item[]{ new Item(8780, 3), new Item(1761, 6), new Item(4692, 2)}),
	
	/**
	 * Kitchen sinks
	 */
	PUMP_AND_DRAIN(13559, 8230, 7, 100.0, new Item(2353, 5)),
	PUMP_AND_TUB(13561, 8231, 27, 200.0, new Item(2353, 10)),
	SINK(13563, 8232, 47, 300.0, new Item(2353, 15)),
	
	
	/**
	 * Kitchen cat baskets/blankets
	 */
	CAT_BLANKET(13574, 8236, 5, 15.0, new Item(8790, 1)),
	CAT_BASKET(13575, 8237, 19, 58.0, new Item(960, 2)),
	CAST_BASKET_CUSHIONED(13576, 8238, 33, 58.0, new Item[]{ new Item(960, 2), new Item(1737, 2)}),
	
	
	/**
	 * Dining room tables
	 */
	DINING_TABLE_WOOD(13293, 8246, 10, 115.0, new Item(960, 4)),
	DINING_TABLE_OAK(13294, 8247, 22, 240.0, new Item(8778, 4)),
	DINING_TABLE_CARVED_OAK(13295, 8247, 31, 360.0, new Item(8778, 6)),
	DINING_TABLE_TEAK(13296, 8248, 38, 360.0, new Item(8780, 4)),
	DINING_TABLE_CARVED_TEAK(13297, 8248, 45, 600.0, new Item[]{ new Item(8780, 6), new Item(8790, 4)}),
	DINING_TABLE_MAHOGANY(13298, 8120, 52, 840.0, new Item(8782, 6)),
	DINING_TABLE_OPULENT(13299, 8121, 72, 3100.0, new Item[]{ new Item(8782, 6), new Item(8790, 4), 
			new Item(4692, 4), new Item(8786, 2)}),
		

	/**
	 * Dining room benches
	 */
	BENCH_WOODEN(13300, 8108, 10, 115.0, new Item(960, 4)),
	BENCH_OAK(13301, 8109, 22, 240.0, new Item(8778, 4)),
	BENCH_CARVED_OAK(13302, 8110, 31, 240.0, new Item(8778, 4)),
	BENCH_TEAK(13303, 8111, 38, 360.0, new Item(8780, 4)),
	BENCH_CARVED_TEAK(13304, 8112, 44, 360.0, new Item(8780, 4)),
	BENCH_MAHOGANY(13305, 8113, 52, 560.0, new Item(8782, 6)),
	BENCH_GILDED(13306, 8114, 61, 1760.0, new Item[]{ new Item(8782, 4), new Item(4692, 4)}),	
	
	/**
	 * Dining room bell-pulls
	 */
	ROPE_PULL(13307, 8099, 5, 15.0, new Item[]{ new Item(954, 1), new Item(8778, 1)}),
	BELL_PULL(13308, 8100, 19, 58.0, new Item[]{ new Item(8780, 1), new Item(8790, 2)}),
	FANCY_BELL_PULL(13309, 8101, 33, 58.0, new Item[]{ new Item(8780, 1), new Item(8790, 2), new Item(4692, 1)}),
	
	/**
	 * Workshop workbench
	 */
	WORKBENCH_WOODEN(13704, 8375, 17, 145.0, new Item(960, 1)),
	WORKBENCH_OAK(13705, 8376, 32, 300.0, new Item(8778, 5)),
	WORKBENCH_STEEL_FRAME(13706, 8377, 46, 440.0, new Item[]{ new Item(8778, 6), new Item(2353, 4)}),
	WORKBENCH_WITH_VICE(13707, 8378, 62, 750.0, new Item[]{ new Item(8377, 1), new Item(8778, 2), new Item(2353, 1)}),
	WORKBENCH_WITH_LATHE(13708, 8379, 77, 1000.0, new Item[]{ new Item(8376, 1), new Item(8778, 2), new Item(2353, 1)}),
	
	/**
	 * Workshop repair benches/stands
	 */
	REPAIR_BENCH(13713, 8389, 15, 120.0, new Item(8778, 2)),
	WHETSTONE(13714, 8390, 35, 260.0, new Item[]{ new Item(8778, 4), new Item(3420, 1)}),
	ARMOUR_STAND(13715, 8391, 55, 500.0, new Item[]{ new Item(8778, 8), new Item(3420, 1)}),
	
	/**
	 * Workshop easels
	 */
	PLUMING_STAND(13716, 8392, 16, 120.0, new Item(8778, 2)),
	SHIELD_EASEL(13717, 8393, 41, 240.0, new Item(8778, 4)),
	BANNER_EASEL(13718, 8394, 66, 510.0, new Item[]{ new Item(8778, 8), new Item(8790, 2)}),
	
	/**
	 * Workshop crafting tables
	 * 	TODO: These are upgradable hotspots, therefore crafting table 3 would require
	 * 	crafting table 2 to be already built in that spot.
	 */
	CRAFTING_TABLE_1(13709, 8380, 16, 50.0, new Item(8778, 4)),
	CRAFTING_TABLE_2(13710, 8381, 25, 100.0, new Item(1775, 1)),
	CRAFTING_TABLE_3(13711, 8382, 34, 175.0, new Item(1775, 2)),
	CRAFTING_TABLE_4(13712, 8383, 42, 240.0, new Item(8778, 2)),
	
	/**
	 * Workshop tool stores
	 * 	These are also upgradable just like the tables above.
	 */
	TOOL_STORE_1(13699, 8384, 15, 120.0, new Item(8778, 2)),
	TOOL_STORE_2(13700, 8385, 25, 120.0, new Item(8778, 2)),
	TOOL_STORE_3(13701, 8386, 35, 120.0, new Item(8778, 2)),
	TOOL_STORE_4(13702, 8387, 44, 120.0, new Item(8778, 2)),
	TOOL_STORE_5(13703, 8388, 55, 120.0, new Item(8778, 2)),

	
	/**
	 * Wall-mounted decorations
	 */
	OAK_DECORATION(13606, 8102, 16, 120.0, new Item[]{ new Item(8778, 2)}),
	TEAK_DECORATION(13606, 8103, 36, 180.0, new Item[]{ new Item(8780, 2)}),
	GILDED_DECORATION(13607, 8104, 56, 1020.0, new Item[]{ new Item(8782, 3), new Item(4692, 2)}),
	
	/**
	 * Staircases.
	 */
	OAK_STAIRCASE(13497, 8249, 27, 680.0, new Item[]{ new Item(8778, 10), new Item(2353, 4)}),
	TEAK_STAIRCASE(13499, 8252, 48, 980.0, new Item[]{ new Item(8780, 10), new Item(2353, 4)}),
	SPIRAL_STAIRCASE(13503, 8258, 67, 1040.0, new Item[]{ new Item(8780, 10), new Item(3420, 7)}),
	MARBLE_STAIRCASE(13501, 8257, 82, 3200.0, new Item[]{ new Item(8782, 5), new Item(8786, 5)}),
	MARBLE_SPIRAL(13505, 8259, 97, 4400.0, new Item[]{ new Item(8780, 10), new Item(8786, 7)}),
	
	/**
	 * Portal room decorations.
	 */
	TEAK_PORTAL(13636, 8328, 50, 270.0, new Item[]{ new Item(8780, 3)}),
	MAHOGANY_PORTAL(13637, 8329, 65, 420.0, new Item[]{ new Item(8782, 3)}),
	MARBLE_PORTAL(13638, 8330, 80, 1500.0, new Item[]{ new Item(8786, 3)}),
	TELEPORT_FOCUS(13640, 8331, 50, 40, new Item[]{ new Item(3420, 2)}),
	GREATER_TELEPORT_FOCUS(13641, 8332, 65, 500.0, new Item[]{ new Item(8786, 1)}),
	SCRYING_POOL(13639, 8333, 80, 2000.0, new Item[]{ new Item(8786, 4)}),
	
	/**
	 * Skill hall decorations.
	 */
	MITHRIL_ARMOUR(13491, 8270, 28, 135.0, new Item[]{ new Item(8778, 2), new Item(1159, 1), new Item(1121, 1), new Item(1085, 1)}),
	ADAMANT_ARMOUR(13492, 8271, 28, 150.0, new Item[]{ new Item(8778, 2), new Item(1161, 1), new Item(1123, 1), new Item(1091, 1)}),
	RUNE_ARMOUR(13493, 8272, 28, 165.0, new Item[]{ new Item(8778, 2),new Item(1163, 1), new Item(1127, 1), new Item(1093, 1)}),
	CRAWLING_HAND(13481, 8260, 38, 211.0, new Item[]{ new Item(8780, 2), new Item(8260, 1)}),
	COCKATRICE_HEAD(13482, 8261, 38, 224.0, new Item[]{ new Item(8780, 2), new Item(8261, 1)}),
	BASILISK_HEAD(13483, 8262, 38, 243.0, new Item[]{ new Item(8780, 2), new Item(8262, 1)}),
	KURASK_HEAD(13484, 8263, 58, 357.0, new Item[]{ new Item(8782, 2), new Item(8263, 1)}),
	ABYSSAL_DEMON_HEAD(13485, 8264, 58, 389.0, new Item[]{ new Item(8782, 2), new Item(8264, 1)}),
	KBD_HEAD(13486, 8265, 78, 1103.0, new Item[]{ new Item(8782, 2), new Item(8265, 1)}),	
	KQ_HEAD(13487, 8266, 78, 1103.0, new Item[]{ new Item(8782, 2), new Item(8266, 1)}),	
	MOUNTED_BASS(13488, 8267, 36, 151.0, new Item[]{ new Item(8778, 2), new Item(7989, 1)}),
	MOUNTED_SWORDFISH(13489, 8268, 56, 230.0, new Item[]{ new Item(8780, 2), new Item(7991, 1)}),
	MOUNTED_SHARK(13490, 8269, 76, 350.0, new Item[]{ new Item(8782, 2), new Item(7993, 1)}),
	RUNE_CASE1(13507, 8095, 41, 190.0, new Item[]{ new Item(8780, 2), new Item(1775, 2), new Item(554, 1), new Item(556, 1), new Item(557, 1), new Item(555, 1)}),
	RUNE_CASE2(13508, 8095, 41, 212.0, new Item[]{ new Item(8780, 2), new Item(1775, 2), new Item(559, 1), new Item(564, 1), new Item(562, 1), new Item(561, 1)}),
			
	
	/**
	 * Games room decorations.
	 */
	CLAY_STONE(13392, 8153, 39, 100.0, new Item(1761, 10)),
	LIMESTONE_STONE(13393, 8154, 59, 200.0, new Item(3420, 10)),
	MARBLE_STONE(13394, 8155, 79, 2000.0, new Item(8786, 4)),
	HOOP_AND_STICK(13398, 8162, 30, 120.0, new Item(8778, 2)),
	DARTBOARD(13400, 8163, 54, 290.0, new Item[] { new Item(8780, 3), new Item(2353, 1)}),
	ARCHERY_TARGET(13402, 8164, 81, 600.0, new Item[] { new Item(8780, 6), new Item(2353, 3)}),
	BALANCE_1(13395, 8156, 37, 176.0, new Item[] { new Item(554, 500), new Item(556, 500), new Item(557, 500), new Item(555, 500)}),
	BALANCE_2(13396, 8157, 57, 252.0, new Item[] { new Item(554, 1000), new Item(556, 1000), new Item(557, 1000), new Item(555, 1000)}),
	BALANCE_3(13397, 8158, 77, 356.0, new Item[] { new Item(554, 2000), new Item(556, 2000), new Item(557, 2000), new Item(555, 2000)}),
	OAK_CHEST(13385, 8165, 34, 240.0, new Item(8778, 4)),
	TEAK_CHEST(13387, 8166, 44, 660.0, new Item[] { new Item(8780, 4), new Item(4692, 1)}),
	MAHOGANY_CHEST(13389, 8167, 54, 860.0, new Item[] { new Item(8782, 4), new Item(4692, 1)}),
	JESTER(13390, 8159, 39, 360.0, new Item(8780, 4)),
	TREASURE_HUNT(13379, 8160, 49, 800.0, new Item[] { new Item(8780, 8), new Item(2353, 4)}),
	HANGMAN(13404, 8161, 59, 1200.0, new Item[] { new Item(8780, 12), new Item(2353, 6)}),	
	
	
	/**
	* Combat room decorations.
	*/
	BOXING_RING(13129, 8023, 32, 570.0, new Item[]{ new Item(8778, 6), new Item(8790, 4)}),
	FENCING_RING(13133, 8024, 41, 570.0, new Item[]{ new Item(8770, 8), new Item(7991, 6)}),
	COMBAT_RING(13137, 8025, 51, 630.0, new Item[]{ new Item(8780, 6), new Item(7991, 6)}),
	RANGING_PEDESTALS(13147, 8026, 71, 720.0, new Item(8780, 8)),
	BALANCE_BEAM(13142, 8027, 81, 1000.0, new Item[]{ new Item(8780, 10), new Item(2353, 5)}),
	GLOVE_RACK(13381, 8028, 34, 120.0, new Item(8778, 2)),
	WEAPONS_RACK(13382, 8029, 44, 180.0, new Item(8780, 2)),
	EXTRA_WEAPONS_RACK(13383, 8030, 54, 440.0, new Item[] { new Item(8780, 4), new Item(2353, 4)}), 
	
	
	/**
	 * Formal garden decorations
	 */
	GAZEBO(13477, 8192, 65, 1200, new Item[] { new Item(8782, 8), new Item(2353, 4)}),
	SMALL_FOUNTAIN(13478, 8193, 71, 500, new Item[] { new Item(8786, 1)}),
	LARGE_FOUNTAIN(13479, 8194, 75, 1000, new Item[] { new Item(8786, 2)}),
	POSH_FOUNTAIN(13480, 8195, 81, 1500, new Item[] { new Item(8786, 3)}),
	SUNFLOWER(13443, 8213, 66, 70, new Item[] { new Item(8457, 1)}),
	MARIGOLDS(13444, 8214, 71, 100, new Item[] { new Item(8459, 1)}),
	ROSES(13445, 8215, 76, 122, new Item[] { new Item(8461, 1)}),
	ROSEMARY(13440, 8210, 66, 70, new Item[] { new Item(8451, 1)}),
	DAFFODILS(13441, 8211, 71, 100, new Item[] { new Item(8453, 1)}),
	BLUEBALLZ(13439, 8212, 76, 122, new Item[] { new Item(8455, 1)}),
	THORNY_HEDGE(13456, 8203, 56, 70, new Item[] { new Item(8437, 1)}),
	NICE_HEDGE(13459, 8204, 60, 100, new Item[] { new Item(8439, 1)}),
	SMALL_BOX_HEDGE(13462, 8205, 64, 122, new Item[] { new Item(8441, 1)}),
	TOPIARY_HEDGE(13465, 8206, 68, 141, new Item[] { new Item(8443, 1)}),
	FANCY_HEDGE(13468, 8207, 72, 158, new Item[] { new Item(8445, 1)}),
	TALL_FANCY_HEDGE(13471, 8208, 76, 223, new Item[] { new Item(8447, 1)}),
	TALL_BOX_HEDGE(13474, 8209, 80, 316, new Item[] { new Item(8449, 1)}),
	BOUNDARY_STONES(13449, 8196, 55, 100, new Item[] { new Item(1761, 10)}),
	WOODEN_FENCE(13450, 8197, 59, 280, new Item[] { new Item(960, 10)}),
	STONE_WALL(13451, 8198, 63, 200, new Item[] { new Item(3420, 10)}),
	IRON_RAILINGS(13452, 8199, 67, 220, new Item[] { new Item(2351, 10), new Item(3420, 6)}),
	PICKET_FENCE(13453, 8200, 71, 640, new Item[] { new Item(8778, 10), new Item(2353, 2)}),
	GARDEN_FENCE(13454, 8201, 75, 940, new Item[] { new Item(8780, 10), new Item(2353, 2)}),
	MARBLE_WALL(13455, 8202, 79, 4000, new Item[] { new Item(8786, 10)}),
	
	
	/**
	 * Bedroom decorations.
	 */
	WOODEN_BED(13148, 8031, 20, 117, new Item[] { new Item(960, 3), new Item(8790, 2)}),
	OAK_BED(13149, 8032, 30, 210, new Item[] { new Item(8778, 3), new Item(8790, 2)}),
	LARGE_OAK_BED(13150, 8033, 34, 330, new Item[] { new Item(8778, 5), new Item(8790, 2)}),
	TEAK_BED(13151, 8034, 40, 300, new Item[] { new Item(8780, 3), new Item(8790, 2)}),
	LARGE_TEAK_BED(13152, 8035, 45, 480, new Item[] { new Item(8780, 5), new Item(8790, 2)}),
	FOUR_POSTER(13153, 8036, 53, 450, new Item[] { new Item(8782, 3), new Item(8790, 2)}),
	GILDED_FOUR_POSTER(13154, 8037, 60, 1330, new Item[] { new Item(8782, 5), new Item(8790, 2), new Item(4692, 2)}),
	OAK_CLOCK(13169, 8052, 25, 142, new Item[] { new Item(8778, 2), new Item(8792, 1)}),
	TEAK_CLOCK(13170, 8053, 55, 202, new Item[] { new Item(8780, 2), new Item(8792, 1)}),
	GILDED_CLOCK(13171, 8054, 85, 602, new Item[] { new Item(8782, 2), new Item(8792, 1), new Item(4692, 1)}),
	SHAVING_STAND(13162, 8045, 21, 30, new Item[] { new Item(960, 1), new Item(1775, 1)}),
	OAK_SHAVING_STAND(13163, 8046, 29, 61, new Item[] { new Item(8778, 1), new Item(1775, 1)}),
	OAK_DRESSER(13164, 8047, 37, 121, new Item[] { new Item(8778, 2), new Item(1775, 1)}),
	TEAK_DRESSER(13165, 8048, 46, 181, new Item[] { new Item(8780, 2), new Item(1775, 1)}),
	FANCY_TEAK_DRESSER(13166, 8049, 56, 182, new Item[] { new Item(8780, 2), new Item(1775, 2)}),
	MAHOGANY_DRESSER(13167, 8050, 64, 281, new Item[] { new Item(8782, 2), new Item(1775, 1)}),
	GILDED_DRESSER(13168, 8051, 74, 582, new Item[] { new Item(8782, 2), new Item(1775, 2), new Item(4692, 1)}),
	SHOE_BOX(13155, 8038, 20, 58, new Item[] { new Item(960, 2)}),
	OAK_DRAWERS(13156, 8039, 27, 120, new Item[] { new Item(8778, 2)}),
	OAK_WARDROBE(13157, 8040, 39, 180, new Item[] { new Item(8778, 3)}),
	TEAK_DRAWERS(13158, 8041, 51, 180, new Item[] { new Item(8780, 2)}),
	TEAK_WARDROBE(13159, 8042, 63, 270, new Item[] { new Item(8780, 3)}),
	MAHOGANY_WARDROBE(13160, 8043, 75, 420, new Item[] { new Item(8782, 2)}),
	GILDED_WARDROBE(13161, 8044, 87, 720, new Item[] { new Item(8782, 2), new Item(4692, 1)}),
	
	
	/**
	 * Quest hall decorations.
	 */
	ANTIDRAGON_SHIELD(13522, 8282, 47, 280, new Item[] { new Item(8780, 3), new Item(1540, 1)}),
	AMULET_OF_GLORY(13523, 8283, 47, 290, new Item[] { new Item(8780, 3), new Item(1704, 1)}),
	CAPE_OF_LEGENDS(13524, 8284, 47, 300, new Item[] { new Item(8780, 3), new Item(1052, 1)}),
	KING_ARTHUR(13510, 8285, 35, 211, new Item[] { new Item(8780, 3), new Item(7995, 1)}),
	ELENA(13511, 8286, 35, 211, new Item[] { new Item(8780, 3), new Item(7996, 1)}),
	GIANT_DWARF(13512, 8287, 35, 211, new Item[] { new Item(8780, 3), new Item(7997, 1)}),
	MISCELLANIANS(13513, 8288, 35, 311, new Item[] { new Item(8780, 3), new Item(7998, 1)}),
	LUMBRIDGE(13517, 8289, 44, 314, new Item[] { new Item(8780, 3), new Item(8002, 1)}),
	THE_DESERT(13514, 8290, 44, 314, new Item[] { new Item(8780, 3), new Item(7999, 1)}),
	MORYTANIA(13518, 8291, 44, 314, new Item[] { new Item(8780, 3), new Item(8003, 1)}),
	KARAMJA(13516, 8292, 65, 464, new Item[] { new Item(8782, 3), new Item(8001, 1)}),
	ISAFDAR(13515, 8293, 65, 464, new Item[] { new Item(8782, 3), new Item(8000, 1)}),
	SILVERLIGHT(13519, 8279, 42, 187, new Item[] { new Item(8780, 3), new Item(2402, 1)}),
	EXCALIBUR(13521, 8280, 42, 194, new Item[] { new Item(8780, 3), new Item(35, 1)}),
	DARKLIGHT(13520, 8281, 42, 202, new Item[] { new Item(8780, 3), new Item(6746, 1)}),
	SMALL_MAP(13525, 8294, 38, 211, new Item[] { new Item(8780, 3), new Item(8004, 1)}),
	MEDIUM_MAP(13526, 8295, 58, 451, new Item[] { new Item(8782, 3), new Item(8005, 1)}),
	LARGE_MAP(13527, 8296, 78, 591, new Item[] { new Item(8782, 4), new Item(8006, 1)}),
	
	
	/**
	 * Study decorations.
	 */
	GLOBE(13649, 8341, 41, 180, new Item[] { new Item(8778, 3)}),
	ORNAMENTAL_GLOBE(13650, 8342, 50, 270, new Item[] { new Item(8780, 3)}),	
	LUNAR_GLOBE(13651, 8343, 59, 570, new Item[] { new Item(8780, 3), new Item(4692, 1)}),	
	CELESTIAL_GLOBE(13652, 8344, 68, 570, new Item[] { new Item(8780, 3), new Item(4692, 1)}),	
	ARMILLARY_SPHERE(13653, 8345, 77, 960, new Item[] { new Item(8782, 2), new Item(4692, 2), new Item(2353, 4)}),	
	SMALL_ORREY(13654, 8346, 86, 1320, new Item[] { new Item(8782, 3), new Item(4692, 3)}),	
	LARGE_ORREY(13655, 8347, 95, 1420, new Item[] { new Item(8782, 3), new Item(4692, 5)}),	
	OAK_LECTERN(13642, 8334, 40, 60, new Item[] { new Item(8778, 1)}),	
	EAGLE_LECTERN(13643, 8335, 47, 120, new Item[] { new Item(8778, 2)}),	
	DEMON_LECTERN(13644, 8336, 47, 120, new Item[] { new Item(8778, 2)}),	
	TEAK_EAGLE_LECTERN(13645, 8337, 57, 180, new Item[] { new Item(8780, 2)}),	
	TEAK_DEMON_LECTERN(13646, 8338, 57, 180, new Item[] { new Item(8780, 2)}),	
	MAHOGANY_EAGLE_LECTERN(13647, 8339, 67, 580, new Item[] { new Item(8782, 2), new Item(4692, 1)}),	
	MAHOGANY_DEMON_LECTERN(13648, 8340, 67, 580, new Item[] { new Item(8782, 2), new Item(4692, 1)}),	
	CRYSTAL_BALL(13659, 8351, 42, 280, new Item[] { new Item(8780, 3), new Item(567, 1)}),	
	ELEMENTAL_SPHERE(13660, 8352, 54, 580, new Item[] { new Item(8780, 3), new Item(567, 1), new Item(4692, 1)}),
	CRYSTAL_OF_POWER(13661, 8353, 66, 890, new Item[] { new Item(8782, 2), new Item(567, 1), new Item(4692, 2)}),
	ALCHEMICAL_CHART(13662, 8354, 43, 30, new Item[] { new Item(8790, 2)}),
	ASTRONOMICAL_CHART(13663, 8355, 63, 45, new Item[] { new Item(8790, 3)}),
	INFERNAL_CHART(13664, 8356, 83, 60, new Item[] { new Item(8790, 4)}),
	TELESCOPE1(13656, 8348, 44, 121, new Item[] { new Item(8778, 2), new Item(1775, 1)}),
	TELESCOPE2(13657, 8349, 64, 181, new Item[] { new Item(8780, 2), new Item(1775, 1)}),
	TELESCOPE3(13658, 8350, 84, 580, new Item[] { new Item(8782, 2), new Item(1775, 1)}),
	
	
	/**
	 * Chapel decorations.
	 */
	OAK_ALTAR(13179, 8062, 45, 240, new Item[] { new Item(8778, 4)}),	
	TEAK_ALTAR(13182, 8063, 50, 360, new Item[] { new Item(8780, 4)}),	
	CLOTH_ALTAR(13185, 8064, 56, 390, new Item[] { new Item(8780, 4), new Item(8790, 2)}),	
	MAHOGANY_ALTAR(13188, 8065, 60, 590, new Item[] { new Item(8782, 4), new Item(8790, 2)}),	
	LIMESTONE_ALTAR(13191, 8066, 64, 910, new Item[] { new Item(8782, 6), new Item(8790, 2), new Item(3420, 2)}),	
	MARBLE_ALTAR(13194, 8067, 70, 1030, new Item[] { new Item(8786, 2), new Item(8790, 2)}),	
	GILDED_ALTAR(13197, 8068, 75, 2230, new Item[] { new Item(8786, 2), new Item(8790, 2), new Item(4692, 4)}),	
	SMALL_STATUE(13271, 8082, 49, 40, new Item[] { new Item(3420, 2)}),	
	MEDIUM_STATUE(13272, 8083, 69, 500, new Item[] { new Item(8786, 1)}),	
	LARGE_STATUE(13282, 8084, 89, 1500, new Item[] { new Item(8786, 3)}),	
	WINDCHIMES(13214, 8079, 49, 323, new Item[] { new Item(8778, 4), new Item(2353, 4)}),	
	BELLS(13215, 8080, 58, 480, new Item[] { new Item(8780, 4), new Item(2353, 6)}),	
	ORGAN(13216, 8081, 69, 680, new Item[] { new Item(8782, 4), new Item(2353, 6)}),	
	SARADOMIN_SYMBOL(13172, 8055, 48, 120, new Item[] { new Item(8778, 2)}),	
	ZAMORAK_SYMBOL(13173, 8056, 48, 120, new Item[] { new Item(8778, 2)}),
	GUTHIX_SYMBOL(13174, 8057, 48, 120, new Item[] { new Item(8778, 2)}),
	SARADOMIN_ICON(13175, 8058, 59, 960, new Item[] { new Item(8780, 4), new Item(4692, 2)}),	
	ZAMORAK_ICON(13176, 8059, 59, 960, new Item[] { new Item(8780, 4), new Item(4692, 2)}),	
	GUTHIX_ICON(13177, 8060, 59, 960, new Item[] { new Item(8780, 4), new Item(4692, 2)}),	
	ICON_OF_BOB(13178, 8061, 71, 1160, new Item[] { new Item(8782, 4), new Item(4692, 2)}),	
	STEEL_TORCHES(13202, 8070, 45, 80, new Item[] { new Item(2353, 2)}),	
	WOODEN_TORCHES(13200, 8069, 49, 58, new Item[] { new Item(960, 2)}),	
	STEEL_CANDLESTICKS(13204, 8071, 53, 124, new Item[] { new Item(2353, 6), new Item(36, 6)}),	
	GOLD_CANDLESTICKS(13206, 8072, 57, 46, new Item[] { new Item(2357, 6), new Item(36, 6)}),	
	INCENSE_BURNERS(13208, 8073, 61, 280, new Item[] { new Item(8778, 4), new Item(2353, 2)}),	
	MAHOGANY_BURNERS(13210, 8074, 65, 600, new Item[] { new Item(8782, 4), new Item(2353, 2)}),	
	MARBLE_BURNERS(13212, 8075, 69, 1600, new Item[] { new Item(8790, 2), new Item(2353, 2)}),	
	SHUTTERED_WINDOW(13253, 8076, 49, 228, new Item[] { new Item(960, 8)}),	
	DECORATIVE_WINDOW(13257, 8077, 69, 200, new Item[] { new Item(1775, 8)}),	
	STAINED_GLASS(13258, 8078, 89, 400, new Item[] { new Item(1775, 16)}),	
	
	
	/**
	 * Style related decoration.
	 */
	BASIC_WOOD_WINDOW(13099, -1, 1, 0.0), 
	BASIC_STONE_WINDOW(13091, -1, 1, 0.0),
	WHITEWASHED_STONE_WINDOW(13099, -1, 1, 0.0), 
	FREMENNIK_WINDOW(13099, -1, 1, 0.0), 
	TROPICAL_WOOD_WINDOW(13099, -1, 1, 0.0), 
	FANCY_STONE_WINDOW(13099, -1, 1, 0.0),
	
	;
/*
item=Gazebo, id=8192, option=[null, null, null, null, drop]
item=Small fountain, id=8193, option=[null, null, null, null, drop]
item=Large fountain, id=8194, option=[null, null, null, null, drop]
item=Posh fountain, id=8195, option=[null, null, null, null, drop]
item=Boundary stones, id=8196, option=[null, null, null, null, drop]
item=Wooden fence, id=8197, option=[null, null, null, null, drop]
item=Stone wall, id=8198, option=[null, null, null, null, drop]
item=Iron railings, id=8199, option=[null, null, null, null, drop]
item=Picket fence, id=8200, option=[null, null, null, null, drop]
item=Garden fence, id=8201, option=[null, null, null, null, drop]
item=Marble wall, id=8202, option=[null, null, null, null, drop]
item=Thorny hedge, id=8203, option=[null, null, null, null, drop]
item=Nice hedge, id=8204, option=[null, null, null, null, drop]
item=Small box hedge, id=8205, option=[null, null, null, null, drop]
item=Topiary hedge, id=8206, option=[null, null, null, null, drop]
item=Fancy hedge, id=8207, option=[null, null, null, null, drop]
item=Tall fancy hedge, id=8208, option=[null, null, null, null, drop]
item=Tall box hedge, id=8209, option=[null, null, null, null, drop]
item=Rosemary, id=8210, option=[null, null, null, null, drop]
item=Daffodils, id=8211, option=[null, null, null, null, drop]
item=Bluebells, id=8212, option=[null, null, null, null, drop]
item=Sunflower, id=8213, option=[null, null, null, null, drop]
item=Marigolds, id=8214, option=[null, null, null, null, drop]
item=Roses, id=8215, option=[null, null, null, null, drop]
item=Firepit, id=8216, option=[null, null, null, null, drop]
item=Firepit with hook, id=8217, option=[null, null, null, null, drop]
item=Firepit with pot, id=8218, option=[null, null, null, null, drop]
item=Small oven, id=8219, option=[null, null, null, null, drop]
item=Large oven, id=8220, option=[null, null, null, null, drop]
item=Steel range, id=8221, option=[null, null, null, null, drop]
item=Fancy range, id=8222, option=[null, null, null, null, drop]
item=Wooden shelves 1, id=8223, option=[null, null, null, null, drop]
item=Wooden shelves 2, id=8224, option=[null, null, null, null, drop]
item=Wooden shelves 3, id=8225, option=[null, null, null, null, drop]
item=Oak shelves 1, id=8226, option=[null, null, null, null, drop]
item=Oak shelves 2, id=8227, option=[null, null, null, null, drop]
item=Teak shelves 1, id=8228, option=[null, null, null, null, drop]
item=Teak shelves 2, id=8229, option=[null, null, null, null, drop]
item=Pump and drain, id=8230, option=[null, null, null, null, drop]
item=Pump and tub, id=8231, option=[null, null, null, null, drop]
item=Sink, id=8232, option=[null, null, null, null, drop]
item=Wooden larder, id=8233, option=[null, null, null, null, drop]
item=Oak larder, id=8234, option=[null, null, null, null, drop]
item=Teak larder, id=8235, option=[null, null, null, null, drop]
item=Cat blanket, id=8236, option=[null, null, null, null, drop]
item=Cat basket, id=8237, option=[null, null, null, null, drop]
item=Cushioned basket, id=8238, option=[null, null, null, null, drop]
item=Beer barrel, id=8239, option=[null, null, null, null, drop]
item=Cider barrel, id=8240, option=[null, null, null, null, drop]
item=Asgarnian ale, id=8241, option=[null, null, null, null, drop]
item=Greenman's ale, id=8242, option=[null, null, null, null, drop]
item=Dragon bitter, id=8243, option=[null, null, null, null, drop]
item=Chef's delight, id=8244, option=[null, null, null, null, drop]
item=Blank poh entity, id=8245, option=[null, null, null, null, drop]
item=Wood kitchen table, id=8246, option=[null, null, null, null, drop]
item=Oak kitchen table, id=8247, option=[null, null, null, null, drop]
item=Teak kitchen table, id=8248, option=[null, null, null, null, drop]
item=Oak staircase, id=8249, option=[null, null, null, null, drop]
item=Oak staircase, id=8250, option=[null, null, null, null, drop]
item=Oak staircase, id=8251, option=[null, null, null, null, drop]
item=Teak staircase, id=8252, option=[null, null, null, null, drop]
item=Teak staircase, id=8253, option=[null, null, null, null, drop]
item=Teak staircase, id=8254, option=[null, null, null, null, drop]
item=Marble staircase, id=8255, option=[null, null, null, null, drop]
item=Marble staircase, id=8256, option=[null, null, null, null, drop]
item=Marble staircase, id=8257, option=[null, null, null, null, drop]
item=Spiral staircase, id=8258, option=[null, null, null, null, drop]
item=Marble spiral, id=8259, option=[null, null, null, null, drop]
item=Crawling hand, id=8260, option=[null, null, null, null, drop]
item=Cockatrice head, id=8261, option=[null, null, null, null, drop]
item=Basilisk head, id=8262, option=[null, null, null, null, drop]
item=Kurask head, id=8263, option=[null, null, null, null, drop]
item=Abyssal head, id=8264, option=[null, null, null, null, drop]
item=Kbd heads, id=8265, option=[null, null, null, null, drop]
item=Kq head, id=8266, option=[null, null, null, null, drop]
item=Mounted bass, id=8267, option=[null, null, null, null, drop]
item=Mounted swordfish, id=8268, option=[null, null, null, null, drop]
item=Mounted shark, id=8269, option=[null, null, null, null, drop]
item=Mithril armour, id=8270, option=[null, null, null, null, drop]
item=Adamantite armour, id=8271, option=[null, null, null, null, drop]
item=Runite armour, id=8272, option=[null, null, null, null, drop]
item=Cw armour 1, id=8273, option=[null, null, null, null, drop]
item=Cw armour 2, id=8274, option=[null, null, null, null, drop]
item=Cw armour 3, id=8275, option=[null, null, null, null, drop]
item=Rune case 1, id=8276, option=[null, null, null, null, drop]
item=Rune case 2, id=8277, option=[null, null, null, null, drop]
item=Rune case 3, id=8278, option=[null, null, null, null, drop]
item=Silverlight, id=8279, option=[null, null, null, null, drop]
item=Excalibur, id=8280, option=[null, null, null, null, drop]
item=Darklight, id=8281, option=[null, null, null, null, drop]
item=Anti-dragon shield, id=8282, option=[null, null, null, null, drop]
item=Amulet of glory, id=8283, option=[null, null, null, null, drop]
item=Cape of legends, id=8284, option=[null, null, null, null, drop]
item=King arthur, id=8285, option=[null, null, null, null, drop]
item=Elena, id=8286, option=[null, null, null, null, drop]
item=Giant dwarf, id=8287, option=[null, null, null, null, drop]
item=Miscellanians, id=8288, option=[null, null, null, null, drop]
item=Lumbridge, id=8289, option=[null, null, null, null, drop]
item=The desert, id=8290, option=[null, null, null, null, drop]
item=Morytania, id=8291, option=[null, null, null, null, drop]
item=Karamja, id=8292, option=[null, null, null, null, drop]
item=Isafdar, id=8293, option=[null, null, null, null, drop]
item=Small map, id=8294, option=[null, null, null, null, drop]
item=Medium map, id=8295, option=[null, null, null, null, drop]
item=Large map, id=8296, option=[null, null, null, null, drop]
item=Oak cage, id=8297, option=[null, null, null, null, drop]
item=Oak and steel cage, id=8298, option=[null, null, null, null, drop]
item=Steel cage, id=8299, option=[null, null, null, null, drop]
item=Spiked cage, id=8300, option=[null, null, null, null, drop]
item=Bone cage, id=8301, option=[null, null, null, null, drop]
item=Spikes, id=8302, option=[null, null, null, null, drop]
item=Tentacle pool, id=8303, option=[null, null, null, null, drop]
item=Flame pit, id=8304, option=[null, null, null, null, drop]
item=Rocnar, id=8305, option=[null, null, null, null, drop]
item=Oak ladder, id=8306, option=[null, null, null, null, drop]
item=Teak ladder, id=8307, option=[null, null, null, null, drop]
item=Mahogany ladder, id=8308, option=[null, null, null, null, drop]
item=Crude wooden chair, id=8309, option=[null, null, null, null, drop]
item=Wooden chair, id=8310, option=[null, null, null, null, drop]
item=Rocking chair, id=8311, option=[null, null, null, null, drop]
item=Oak chair, id=8312, option=[null, null, null, null, drop]
item=Oak armchair, id=8313, option=[null, null, null, null, drop]
item=Teak armchair, id=8314, option=[null, null, null, null, drop]
item=Mahogany armchair, id=8315, option=[null, null, null, null, drop]
item=Brown rug, id=8316, option=[null, null, null, null, drop]
item=Rug, id=8317, option=[null, null, null, null, drop]
item=Opulent rug, id=8318, option=[null, null, null, null, drop]
item=Wooden bookcase, id=8319, option=[null, null, null, null, drop]
item=Oak bookcase, id=8320, option=[null, null, null, null, drop]
item=Mahogany b'kcase, id=8321, option=[null, null, null, null, drop]
item=Torn curtains, id=8322, option=[null, null, null, null, drop]
item=Curtains, id=8323, option=[null, null, null, null, drop]
item=Opulent curtains, id=8324, option=[null, null, null, null, drop]
item=Clay fireplace, id=8325, option=[null, null, null, null, drop]
item=Stone fireplace, id=8326, option=[null, null, null, null, drop]
item=Marble fireplace, id=8327, option=[null, null, null, null, drop]
item=Teak portal, id=8328, option=[null, null, null, null, drop]
item=Mahogany portal, id=8329, option=[null, null, null, null, drop]
item=Marble portal, id=8330, option=[null, null, null, null, drop]
item=Teleport focus, id=8331, option=[null, null, null, null, drop]
item=Greater focus, id=8332, option=[null, null, null, null, drop]
item=Scrying pool, id=8333, option=[null, null, null, null, drop]
item=Oak lectern, id=8334, option=[null, null, null, null, drop]
item=Eagle lectern, id=8335, option=[null, null, null, null, drop]
item=Demon lectern, id=8336, option=[null, null, null, null, drop]
item=Teak eagle lectern, id=8337, option=[null, null, null, null, drop]
item=Teak demon lectern, id=8338, option=[null, null, null, null, drop]
item=Mahogany eagle, id=8339, option=[null, null, null, null, drop]
item=Mahogany demon, id=8340, option=[null, null, null, null, drop]
item=Globe, id=8341, option=[null, null, null, null, drop]
item=Ornamental globe, id=8342, option=[null, null, null, null, drop]
item=Lunar globe, id=8343, option=[null, null, null, null, drop]
item=Celestial globe, id=8344, option=[null, null, null, null, drop]
item=Armillary sphere, id=8345, option=[null, null, null, null, drop]
item=Small orrery, id=8346, option=[null, null, null, null, drop]
item=Large orrery, id=8347, option=[null, null, null, null, drop]
item=Wooden telescope, id=8348, option=[null, null, null, null, drop]
item=Teak telescope, id=8349, option=[null, null, null, null, drop]
item=Mahogany 'scope, id=8350, option=[null, null, null, null, drop]
item=Crystal ball, id=8351, option=[null, null, null, null, drop]
item=Elemental sphere, id=8352, option=[null, null, null, null, drop]
item=Crystal of power, id=8353, option=[null, null, null, null, drop]
item=Alchemical chart, id=8354, option=[null, null, null, null, drop]
item=Astronomical chart, id=8355, option=[null, null, null, null, drop]
item=Infernal chart, id=8356, option=[null, null, null, null, drop]
item=Oak throne, id=8357, option=[null, null, null, null, drop]
item=Teak throne, id=8358, option=[null, null, null, null, drop]
item=Mahogany throne, id=8359, option=[null, null, null, null, drop]
item=Gilded throne, id=8360, option=[null, null, null, null, drop]
item=Skeleton throne, id=8361, option=[null, null, null, null, drop]
item=Crystal throne, id=8362, option=[null, null, null, null, drop]
item=Demonic throne, id=8363, option=[null, null, null, null, drop]
item=Oak lever, id=8364, option=[null, null, null, null, drop]
item=Teak lever, id=8365, option=[null, null, null, null, drop]
item=Mahogany lever, id=8366, option=[null, null, null, null, drop]
item=Trapdoor, id=8367, option=[null, null, null, null, drop]
item=Trapdoor, id=8368, option=[null, null, null, null, drop]
item=Trapdoor, id=8369, option=[null, null, null, null, drop]
item=Floor decoration, id=8370, option=[null, null, null, null, drop]
item=Steel cage, id=8371, option=[null, null, null, null, drop]
item=Trapdoor, id=8372, option=[null, null, null, null, drop]
item=Lesser magic cage, id=8373, option=[null, null, null, null, drop]
item=Greater magic cage, id=8374, option=[null, null, null, null, drop]
item=Wooden workbench, id=8375, option=[null, null, null, null, drop]
item=Oak workbench, id=8376, option=[null, null, null, null, drop]
item=Steel framed bench, id=8377, option=[null, null, null, null, drop]
item=Bench with vice, id=8378, option=[null, null, null, null, drop]
item=Bench with lathe, id=8379, option=[null, null, null, null, drop]
item=Crafting table 1, id=8380, option=[null, null, null, null, drop]
item=Crafting table 2, id=8381, option=[null, null, null, null, drop]
item=Crafting table 3, id=8382, option=[null, null, null, null, drop]
item=Crafting table 4, id=8383, option=[null, null, null, null, drop]
item=Tool store 1, id=8384, option=[null, null, null, null, drop]
item=Tool store 2, id=8385, option=[null, null, null, null, drop]
item=Tool store 3, id=8386, option=[null, null, null, null, drop]
item=Tool store 4, id=8387, option=[null, null, null, null, drop]
item=Tool store 5, id=8388, option=[null, null, null, null, drop]
item=Repair bench, id=8389, option=[null, null, null, null, drop]
item=Whetstone, id=8390, option=[null, null, null, null, drop]
item=Armour stand, id=8391, option=[null, null, null, null, drop]
item=Pluming stand, id=8392, option=[null, null, null, null, drop]
item=Shield easel, id=8393, option=[null, null, null, null, drop]
item=Banner easel, id=8394, option=[null, null, null, null, drop]
item=Parlour, id=8395, option=[null, null, null, null, drop]
item=Kitchen, id=8396, option=[null, null, null, null, drop]
item=Dining room, id=8397, option=[null, null, null, null, drop]
item=Bedroom, id=8398, option=[null, null, null, null, drop]
item=Games room, id=8399, option=[null, null, null, null, drop]
item=Combat room, id=8400, option=[null, null, null, null, drop]
item=Hall, id=8401, option=[null, null, null, null, drop]
item=Hall, id=8402, option=[null, null, null, null, drop]
item=Hall, id=8403, option=[null, null, null, null, drop]
item=Hall, id=8404, option=[null, null, null, null, drop]
item=Chapel, id=8405, option=[null, null, null, null, drop]
item=Workshop, id=8406, option=[null, null, null, null, drop]
item=Study, id=8407, option=[null, null, null, null, drop]
item=Portal chamber, id=8408, option=[null, null, null, null, drop]
item=Throne room, id=8409, option=[null, null, null, null, drop]
item=Oubliette, id=8410, option=[null, null, null, null, drop]
item=Dungeon corridor, id=8411, option=[null, null, null, null, drop]
item=Dungeon cross, id=8412, option=[null, null, null, null, drop]
item=Dungeon stairs, id=8413, option=[null, null, null, null, drop]
item=Treasure room, id=8414, option=[null, null, null, null, drop]
item=Garden, id=8415, option=[null, null, null, null, drop]
item=Formal garden, id=8416, option=[null, null, null, null, drop]
 */
	/**
	 * The object id.
	 */
	private final int objectId;
	
	/**
	 * The item id for the interface.
	 */
	private final int interfaceItem;

	/**
	 * The level requirement.
	 */
	private final int level;
	
	/**
	 * The experience gained for building this decoration.
	 */
	private final double experience;
	
	/**
	 * The item required.
	 */
	private final Item[] items;
	
	/**
	 * The tools required.
	 */
	private final int[] tools;
	
	/**
	 * Constructs a new {@code Portal} {@code Object}.
	 * @param objectId The object id.
	 * @param interfaceItem The item id for the building interface.
	 * @param level The level required.
	 * @param experience The experience gained.
	 * @param items The items required.
	 */
	private Decoration(int objectId, int interfaceItem, int level, double experience, Item... items) {
		this(objectId, interfaceItem, level, experience, new int[] { 2347, 8794 }, items);
	}
	
	/**
	 * Constructs a new {@code Portal} {@code Object}.
	 * @param objectId The object id.
	 * @param interfaceItem The item id for the building interface.
	 * @param level The level required.
	 * @param experience The experience gained.
	 * @param items The items required.
	 */
	private Decoration(int objectId, int interfaceItem, int level, double experience, int[] tools, Item... items) {
		this.objectId = objectId;
		this.interfaceItem = interfaceItem;
		this.level = level;
		this.experience = experience;
		this.tools = tools;
		this.items = items;
	}

	/**
	 * Gets the decoration on the given location.
	 * @param player The player.
	 * @param l The location.
	 * @return The decoration.
	 */
	public static Decoration getDecoration(Player player, Location l) {
		Room room = player.getHouseManager().getRooms()[l.getZ()][l.getLocalX() >> 3][l.getLocalY() >> 3];
		for (Hotspot h : room.getHotspots()) {
			if (h.getChunkX() == l.getChunkOffsetX() && h.getChunkY() == l.getChunkOffsetY()) {
				if (h.getDecorationIndex() != -1)
					return h.getHotspot().getDecorations()[h.getDecorationIndex()];
			}
		}
		return null;
	}
	
	/**
	 * Gets the objectId.
	 * @return The objectId.
	 */
	public int getObjectId() {
		return objectId;
	}

	/**
	 * Gets the level.
	 * @return The level.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gets the experience.
	 * @return The experience.
	 */
	public double getExperience() {
		return experience;
	}

	/**
	 * Gets the items.
	 * @return The items.
	 */
	public Item[] getItems() {
		return items;
	}

	/**
	 * Gets the tools.
	 * @return The tools.
	 */
	public int[] getTools() {
		return tools;
	}

	/**
	 * Gets the interfaceItem.
	 * @return the interfaceItem
	 */
	public int getInterfaceItem() {
		return interfaceItem;
	}
}