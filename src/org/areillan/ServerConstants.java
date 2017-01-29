package org.areillan;

import org.areillan.game.world.map.Location;
import org.areillan.tools.RandomFunction;

/**
 * A class holding constants of the server.
 * @author Emperor
 * @author Vexia
 * 
 */
public final class ServerConstants {
	
	/**
	 * The cache path.
	 */
	public static final String CACHE_PATH = "data/cache/";

	/**
	 * The store path.
	 */
	public static final String STORE_PATH = "data/store/";
	
	/**
	 * The player account path.
	 */
	public static final String PLAYER_SAVE_PATH = "data/players/";

	/**
	 * The maximum amount of players.
	 */
	public static final int MAX_PLAYERS = (1 << 11) - 1;

	/**
	 * The maximum amount of NPCs.
	 */
	public static final int MAX_NPCS = (1 << 15) - 1;
	
	/**
	 * The start location for a fresh account.
	 */
	//public static final Location START_LOCATION = Location.create(3094, 3107, 0);
	public static final Location START_LOCATION = Location.create(3087, 3489, 0);
	
	/**
	 * The main home teleport location.
	 */
	public static final Location HOME_LOCATION = Location.create(3087, 3489, 0);
	
	/**
	 * The teleport destinations.
	 */
	public static final Object[][] TELEPORT_DESTINATIONS = { { Location.create(3293, 3184, 0), "Al Kharid", "al kharid", "alkharid", "kharid" }, { Location.create(3222, 3217, 0), "Lumbridge", "lumbridge", "lumby" }, { Location.create(3110, 3168, 0), "Wizard Tower", "wizard tower", "wizards tower", "tower", "wizards" }, { Location.create(3083, 3249, 0), "Draynor", "draynor", "draynor village" }, { Location.create(3019, 3244, 0), "Port Sarim", "port", "port sarim", "sarim" }, { Location.create(2956, 3209, 0), "Rimmington", "rimmington" }, { Location.create(2965, 3380, 0), "Falador", "falador", "fally" }, { Location.create(2895, 3436, 0), "Taverly", "taverly" }, { Location.create(3080, 3423, 0), "Barbarian Village", "barbarian village", "barb" }, { Location.create(3213, 3428, 0), "Varrock", "varrock" }, { Location.create(3164, 3485, 0), "Grand Exchange", "grand exchange", "ge" }, { Location.create(2917, 3175, 0), "Karamja", "karamja" }, { Location.create(2450, 5165, 0), "Tzhaar", "tzhaar" }, { Location.create(2795, 3177, 0), "Brimhaven", "brimhaven", "brim" }, { Location.create(2849, 2961, 0), "Shilo Village", "shilo village", "shilo" }, { Location.create(2605, 3093, 0), "Yanille", "yanille" }, { Location.create(2663, 3305, 0), "Ardougne", "ardougne", "ardy" }, { Location.create(2450, 3422, 0), "Gnome Stronghold", "gnome stronghold", "gnome" }, { Location.create(2730, 3485, 0), "Camelot", "camelot", "seers village", "cammy", "seers" }, { Location.create(2805, 3435, 0), "Catherby", "catherby" }, { Location.create(2659, 3657, 0), "Rellekka", "rellekka" }, { Location.create(2890, 3676, 0), "Trollheim", "trollheim" }, { Location.create(2914, 3746, 0), "Godwars", "gwd", "godwars", "god wars" }, { Location.create(3272, 3687, 0), "Clan Wars", "clan wars", "clw" }, { Location.create(3090, 3957, 0), "Mage Arena", "mage", "magearena", "mage arena", "arena" }, { Location.create(3069, 10257, 0), "King Black Dragon", "king black dragon", "kbd" }, { Location.create(3359, 3416, 0), "Digsite", "digsite" }, { Location.create(3488, 3489, 0), "Canifis", "canifis" }, { Location.create(3428, 3526, 0), "Slayer tower", "slayer tower", "slayer" }, { Location.create(3233, 2913, 0), "Pyramid", "pyramid" }, { Location.create(3419, 2917, 0), "Nardah", "nardah" }, { Location.create(3482, 3090, 0), "Uzer", "uzer" }, { Location.create(3358, 2970, 0), "Pollnivneach", "pollnivneach", "poln" }, { Location.create(3305, 2788, 0), "Sophanem", "sophanem" }, { Location.create(2898, 3544, 0), "Burthorpe", "burthorpe" }, { Location.create(3088, 3491, 0), "Edge", "edge", "edgeville" }, { Location.create(3169, 3034, 0), "Bedabin", "bedabin" } };
	
	/**
	 * The teleport destinations, intended for Grandpa Jack.
	 */
	public static final Object[][] TELEPORT_DESTINATIONS_DONATOR = { { Location.create(2914, 3746, 0), "godwars", "gwd", "god wars"}, { Location.create(2659, 2649, 0), "pc", "pest control", "pest" }, { Location.create(3293, 3184, 0), "al kharid", "alkharid", "kharid" }, { Location.create(3222, 3217, 0), "lumbridge", "lumby" }, { Location.create(3110, 3168, 0), "wizard tower", "wizards tower", "tower", "wizards" }, { Location.create(3083, 3249, 0), "draynor", "draynor village" }, { Location.create(3019, 3244, 0), "port sarim", "sarim" }, { Location.create(2956, 3209, 0), "rimmington" }, { Location.create(2965, 3380, 0), "fally", "falador" }, { Location.create(2895, 3436, 0), "taverly" }, { Location.create(3080, 3423, 0), "barbarian village", "barb" }, { Location.create(3213, 3428, 0), "varrock" }, { Location.create(3164, 3485, 0), "grand exchange", "ge" }, { Location.create(2917, 3175, 0), "karamja" }, { Location.create(2450, 5165, 0), "tzhaar" }, { Location.create(2795, 3177, 0), "brimhaven" }, { Location.create(2849, 2961, 0), "shilo village", "shilo" }, { Location.create(2605, 3093, 0), "yanille" }, { Location.create(2663, 3305, 0), "ardougne", "ardy" }, { Location.create(2450, 3422, 0), "gnome stronghold", "gnome" }, { Location.create(2730, 3485, 0), "camelot", "cammy", "seers" }, { Location.create(2805, 3435, 0), "catherby" }, { Location.create(2659, 3657, 0), "rellekka" }, { Location.create(2890, 3676, 0), "trollheim" },  { Location.create(3180, 3684, 0), "bounty hunter", "bh" }, { Location.create(3272, 3687, 0), "clan wars", "clw" }, { Location.create(3090, 3957, 0), "mage arena", "mage", "magearena", "arena" }, { Location.create(3359, 3416, 0), "digsite" }, { Location.create(3488, 3489, 0), "canifis" }, { Location.create(3428, 3526, 0), "slayer tower", "slayer" }, { Location.create(3233, 2913, 0), "pyramid" }, { Location.create(3419, 2917, 0), "nardah" }, { Location.create(3482, 3090, 0), "uzer" }, { Location.create(3358, 2970, 0), "pollnivneach", "poln" }, { Location.create(3305, 2788, 0), "sophanem" }, { Location.create(2898, 3544, 0), "burthorpe", "burthorpe" }, { Location.create(3088, 3491, 0), "edge", "edgeville" }, { Location.create(3169, 3034, 0), "bedabin" }, { Location.create(3565, 3311, 0), "barrows" } };
	
	/**
	 * The telport destinations for bosses
	 */
	public static final Object[][] TELEPORT_BOSSES = { { Location.create(3172, 3799, 0), "Callisto", "calliso" }, {Location.create(3111, 5529, 0), "Bork", "bork"}, {Location.create(2968, 4384, 0), "Corporeal Beast", "corp", "corporeal beast" }, {Location.create(3307, 3916, 0), "Chaos Elemental", "chaos elemental", "chaos ele" }, {Location.create(2977, 3692, 0), "Crazy Archaeologoist", "crazy archaeologoist", "ca" }, {Location.create(1912, 4367, 0), "Dagganoth Kings", "dagganoth kings", "dag kings" }, {Location.create(1763, 5183, 0), "Giant Mole", "giant mole" }, {Location.create(2914, 3746, 0), "Godwars", "gdw", "godwars" }, {Location.create(3503, 9494, 0), "Kalphite Queen", "kq", "kalphite queen" }, { Location.create(3069, 10257, 0), "King Black Dragon", "king black dragon", "kbd" }, {Location.create(3696, 5798, 0), "Kraken", "kraken" }, {Location.create(2574, 5734, 0), "Tormented Demons", "tormented demons", "td" }, {Location.create(3371, 3746, 0), "Venenatis", "venenatis" } };
	
	/**
	 * The teleport destinations for minigames
	 */
	public static final Object[][] TELEPORT_MINIGAMES = { { Location.create(3565, 3289, 0), "Barrows", "barrows", }, { Location.create(2442, 3089, 0), "Castle Wars", "castle wars", "cwars" }, { Location.create(3187, 3691, 0), "Bounty Hunter", "bounty hunter", "bh" }, { Location.create(3269, 3687, 0), "Clan Wars", "cw", "clanwars", "clwars" }, { Location.create(3314, 3235, 0), "Duel Arena", "duel arena", "da" }, { Location.create(2439, 5171, 0), "Jad", "jad" }, { Location.create(2659, 2649, 0), "Pest Control", "pc", "pest control", "pest" }, { Location.create(2400, 5178, 0), "Fight Pits", "fight pits", "pits" }, { Location.create(2878, 3546, 0), "Warriors Guild", "warriors guild", "wguild" }, {Location.create(2809, 3192, 0), "Agility Arena", "agilitya", "aa"} };
	
	/**
	 * The teleport destinations for monsters
	 */
	public static final Object[][] TELEPORT_MONSTERS = { { Location.create(3293, 3180, 0), "Al-Kharid Warriors", "akw", "al kharid warriors" }, { Location.create(3239, 3606, 0), "Armoured Zombies", "az", "armoured zombies" }, { Location.create(2674, 3710, 0), "Brine Rate Cave", "brc", "brine rat cave" }, { Location.create(2573, 9499, 0), "Chaos Druid Warriors", "chaos druid warriors" }, { Location.create(3161, 4275, 0), "Cockroach Soldier", "cockroach soldier" }, { Location.create(3258, 3261, 0), "Cows", "cows" }, { Location.create(2524, 3740, 0), "Dagannoths", "dags", "dagannoths" }, {Location.create(3176, 2987, 0), "Desert Bandits", "desert bandits", "bandits" }, { Location.create(2956, 3852, 0), "Ice Warriors", "ice warriors" }, { Location.create(3202, 3859, 0), "Lava Dragons", "lava dragons" }, {Location.create(2672, 3712, 0), "Rock Crabs", "crabs", "rock crabs" }, { Location.create(2326, 3803, 0), "Yaks", "yaks" }, {Location.create(2450, 5165, 0),  "TzHaars", "tzhaars" }, { Location.create(2492, 10147, 0), "Wallasalki" } };
	
	/**{ Location.create(3414, 3560, 2), "abby" }
	 * The string of donation messages displayed on an interface.
	 */
	public static final String[] MESSAGES = new String[] {"Donations on Aincrad are different than those elsewhere.", "Here we use a perk system.", "There are many different type of perks that can be bought to", "speed up efficiency, but nothing game breaking. By doing this", "we provide players with ways to support Aincrad, in a manner" , "that doesn't ruin the economy or provide substantial advantages.", "If you would like to check out our perks please visit", "www.aincrad.co." };
	/**
	 * The teleport destination for skills
	 */
	//public static final Object[][] TELEPORT_SKILLS = { { } };
	/**
	 * Constructs a new {@Code ServerConstants} {@Code Object}
	 */
	private ServerConstants() {
		/*
		 * empty.
		 */
	}
	
}
