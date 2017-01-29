package org.areillan.game.content.skill.member.construction;


/**
 * Represents the room properties.
 * @author Emperor
 * >ORDINAL BOUND<
 */
public enum RoomProperties {

	/**
	 * The parlour.
	 */
	PARLOUR(1000, 1, 0, 0, 7, new Hotspot(BuildHotspot.BOOKCASE, 0, 1),
			new Hotspot(BuildHotspot.WINDOW, 0, 2),
			new Hotspot(BuildHotspot.CURTAINS, 0, 2),
			new Hotspot(BuildHotspot.CURTAINS, 0, 5),
			new Hotspot(BuildHotspot.WINDOW, 0, 5),
			new Hotspot(BuildHotspot.CURTAINS, 2, 0),
			new Hotspot(BuildHotspot.WINDOW, 2, 0),
			new Hotspot(BuildHotspot.RUG, 2, 2),
			new Hotspot(BuildHotspot.RUG2, 2, 3),
			new Hotspot(BuildHotspot.RUG2, 2, 4),
			new Hotspot(BuildHotspot.CHAIRS_1, 2, 4),
			new Hotspot(BuildHotspot.RUG, 2, 5),
			new Hotspot(BuildHotspot.CURTAINS, 2, 7),
			new Hotspot(BuildHotspot.WINDOW, 2, 7),
			new Hotspot(BuildHotspot.RUG2, 3, 2),
			new Hotspot(BuildHotspot.RUG3, 3, 3),
			new Hotspot(BuildHotspot.RUG3, 3, 4),
			new Hotspot(BuildHotspot.RUG2, 3, 5),
			new Hotspot(BuildHotspot.FIREPLACE, 3, 7),
			new Hotspot(BuildHotspot.RUG2, 4, 2),
			new Hotspot(BuildHotspot.RUG3, 4, 3),
			new Hotspot(BuildHotspot.CHAIRS_3, 4, 3),
			new Hotspot(BuildHotspot.RUG3, 4, 4),
			new Hotspot(BuildHotspot.RUG2, 4, 5),
			new Hotspot(BuildHotspot.CURTAINS, 5, 0),
			new Hotspot(BuildHotspot.WINDOW, 5, 0),
			new Hotspot(BuildHotspot.RUG, 5, 2),
			new Hotspot(BuildHotspot.RUG2, 5, 3),
			new Hotspot(BuildHotspot.RUG2, 5, 4),
			new Hotspot(BuildHotspot.CHAIRS_2, 5, 4),
			new Hotspot(BuildHotspot.RUG, 5, 5),
			new Hotspot(BuildHotspot.CURTAINS, 5, 7),
			new Hotspot(BuildHotspot.WINDOW, 5, 7),
			new Hotspot(BuildHotspot.BOOKCASE, 7, 1),
			new Hotspot(BuildHotspot.CURTAINS, 7, 2),
			new Hotspot(BuildHotspot.WINDOW, 7, 2),
			new Hotspot(BuildHotspot.WINDOW, 7, 5),
			new Hotspot(BuildHotspot.CURTAINS, 7, 5)),
			

	/**
	 * The garden. (centrepiece has to be first!)
	 */
	GARDEN(1000, 1, 0, 0, 1, new Hotspot(BuildHotspot.CENTREPIECE_1, 3, 3), 
			new Hotspot(BuildHotspot.BIG_PLANT_2, 0, 0), 
			new Hotspot(BuildHotspot.BIG_TREE_1, 1, 5), 
			new Hotspot(BuildHotspot.SMALL_PLANT_1, 3, 1), 
			new Hotspot(BuildHotspot.SMALL_PLANT_2, 4, 5), 
			new Hotspot(BuildHotspot.BIG_PLANT_1, 6, 0), 
			new Hotspot(BuildHotspot.TREE_1, 6, 6)),
	
	/**
	 * Woman's place.
	 */
	KITCHEN(5000, 5, 0, 2, 7, new Hotspot(BuildHotspot.CAT_BLANKET, 0, 0),
			new Hotspot(BuildHotspot.WINDOW, 0, 2),
			new Hotspot(BuildHotspot.WINDOW, 0, 5),
			new Hotspot(BuildHotspot.BARRELS, 0, 6),
			new Hotspot(BuildHotspot.SHELVES, 1, 7),
			new Hotspot(BuildHotspot.WINDOW, 2, 0),
			new Hotspot(BuildHotspot.WINDOW, 2, 7),
			new Hotspot(BuildHotspot.KITCHEN_TABLE, 3, 3),
			new Hotspot(BuildHotspot.STOVE, 3, 7),
			new Hotspot(BuildHotspot.WINDOW, 5, 0),
			new Hotspot(BuildHotspot.WINDOW, 5, 7),
			new Hotspot(BuildHotspot.LARDER, 6, 0),
			new Hotspot(BuildHotspot.SHELVES, 6, 7),
			new Hotspot(BuildHotspot.WINDOW, 7, 2),
			new Hotspot(BuildHotspot.SINK, 7, 3),
			new Hotspot(BuildHotspot.WINDOW, 7, 5),
			new Hotspot(BuildHotspot.SHELVES_2, 7, 6)),
			
	/**
	* Dining room.
	*/
	DINING_ROOM(5000, 10, 0, 4, 7, new Hotspot(BuildHotspot.FIREPLACE_DINING, 3, 7),
			new Hotspot(BuildHotspot.WINDOW, 0, 2),
			new Hotspot(BuildHotspot.WINDOW, 0, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 0),
			new Hotspot(BuildHotspot.WINDOW, 5, 0),
			new Hotspot(BuildHotspot.WINDOW, 7, 2),
			new Hotspot(BuildHotspot.WINDOW, 7, 5),
			new Hotspot(BuildHotspot.CURTAINS, 0, 2),
			new Hotspot(BuildHotspot.CURTAINS, 0, 5),
			new Hotspot(BuildHotspot.CURTAINS, 2, 0),
			new Hotspot(BuildHotspot.CURTAINS, 5, 0),
			new Hotspot(BuildHotspot.CURTAINS, 7, 2),
			new Hotspot(BuildHotspot.CURTAINS, 7, 5),
			new Hotspot(BuildHotspot.WALL_DECORATION, 2, 7),
			new Hotspot(BuildHotspot.WALL_DECORATION, 5, 7),
			new Hotspot(BuildHotspot.DINING_BENCH_2, 2, 5),
			new Hotspot(BuildHotspot.DINING_BENCH_2, 3, 5),
			new Hotspot(BuildHotspot.DINING_BENCH_2, 4, 5),
			new Hotspot(BuildHotspot.DINING_BENCH_2, 5, 5),
			new Hotspot(BuildHotspot.DINING_BENCH_1, 2, 2),
			new Hotspot(BuildHotspot.DINING_BENCH_1, 3, 2),
			new Hotspot(BuildHotspot.DINING_BENCH_1, 4, 2),
			new Hotspot(BuildHotspot.DINING_BENCH_1, 5, 2),
			new Hotspot(BuildHotspot.ROPE_BELL_PULL, 0, 0),
			new Hotspot(BuildHotspot.DINING_TABLE, 2, 3)),
			
	/**
	* Workshop.
	*/
	WORKSHOP(10000, 15, 0, 0, 5, new Hotspot(BuildHotspot.WORKBENCH, 3, 4),
			new Hotspot(BuildHotspot.WINDOW, 0, 2),
			new Hotspot(BuildHotspot.WINDOW, 0, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 0),
			new Hotspot(BuildHotspot.WINDOW, 5, 0),
			new Hotspot(BuildHotspot.WINDOW, 7, 2),
			new Hotspot(BuildHotspot.WINDOW, 7, 5),
			new Hotspot(BuildHotspot.WINDOW, 2, 7),
			new Hotspot(BuildHotspot.WINDOW, 5, 7),
			new Hotspot(BuildHotspot.REPAIR, 7, 3),
			new Hotspot(BuildHotspot.HERALDRY, 7, 6),
			new Hotspot(BuildHotspot.CRAFTING, 0, 3),
			new Hotspot(BuildHotspot.WORKBENCH, 3, 4),
			new Hotspot(BuildHotspot.TOOL4, 7, 1),
			new Hotspot(BuildHotspot.TOOL2, 6, 0),
			new Hotspot(BuildHotspot.TOOL1, 1, 0),
			new Hotspot(BuildHotspot.TOOL3, 0, 1),
			new Hotspot(BuildHotspot.TOOL5, 0, 6)),
			
	/**
	* Bedroom.
	*/
	BEDROOM(10000, 20, 0, 6, 7, new Hotspot(BuildHotspot.BED, 3, 6), 
			new Hotspot(BuildHotspot.FIREPLACE2, 7, 3),
			new Hotspot(BuildHotspot.CLOCK, 7, 0),
			new Hotspot(BuildHotspot.DRESSER, 0, 7),
			new Hotspot(BuildHotspot.DRAWERS, 6, 7)),
	
	/**
	* Skill hall room.
	*/
	SKILL_HALL(15000, 25, 0, 1, 6, new Hotspot(BuildHotspot.STAIRWAYS, 3, 3),
			 new Hotspot(BuildHotspot.ARMOUR_SPACE, 2, 3),
			 new Hotspot(BuildHotspot.ARMOUR_SPACE2, 5, 3),
			 new Hotspot(BuildHotspot.HEAD_TROPHY, 6, 7),
			 new Hotspot(BuildHotspot.RUNE_CASE, 0, 6),
			 new Hotspot(BuildHotspot.FISHING_TROPHY, 1, 7)),
	
	/**
	* Games room.
	*/
	GAMES_ROOM(25000, 30, 0, 5, 4, new Hotspot(BuildHotspot.RANGING_GAME, 1, 0),
			new Hotspot(BuildHotspot.ATTACK_STONE, 2, 4),//TODO: Needs to spawn an NPC also
			new Hotspot(BuildHotspot.PRIZE_CHEST, 3, 7),
			new Hotspot(BuildHotspot.ELEMENTAL_BALANCE, 5, 4),
			new Hotspot(BuildHotspot.GAME_SPACE, 6, 0)),
	
	/**
	* Combat room.
	* TODO: Combat ring
	*/
	COMBAT_ROOM(25000, 32, 0, 3, 4, new Hotspot(BuildHotspot.STORAGE_SPACE, 3, 7),
			new Hotspot(BuildHotspot.WALL_DECORATION2, 1, 7),
			new Hotspot(BuildHotspot.WALL_DECORATION2, 6, 7)),
	
	/**
	* Quest trophy hall.
	*/
	QUEST_HALL(25000, 35, 0, 5, 6, new Hotspot(BuildHotspot.STAIRWAYS2, 3, 3),
			new Hotspot(BuildHotspot.MAP, 7, 1),
			new Hotspot(BuildHotspot.SWORD, 7, 6),
			new Hotspot(BuildHotspot.LANDSCAPE, 6, 7),
			new Hotspot(BuildHotspot.PORTRAIT, 1, 7),
			new Hotspot(BuildHotspot.GUILD_TROPHY, 0, 6),
			new Hotspot(BuildHotspot.BOOKCASE2, 0, 1)),
	
	/**
	* Study.
	*/
	STUDY(50000, 40, 0, 4, 5, new Hotspot(BuildHotspot.GLOBE, 1, 4),
			new Hotspot(BuildHotspot.LECTERN, 2, 2),
			new Hotspot(BuildHotspot.CRYSTAL_BALL, 5, 2),
			new Hotspot(BuildHotspot.BOOKCASE3, 4, 7),
			new Hotspot(BuildHotspot.BOOKCASE3, 3, 7),
			new Hotspot(BuildHotspot.WALL_CHART, 1, 7),
			new Hotspot(BuildHotspot.WALL_CHART, 6, 7),
			new Hotspot(BuildHotspot.WALL_CHART, 7, 1),
			new Hotspot(BuildHotspot.WALL_CHART, 0, 1),
			new Hotspot(BuildHotspot.TELESCOPE, 5, 7)),
	
	/**
	* Costume room.
	*/
	COSTUME_ROOM(50000, 42, 0, 2, 1, new Hotspot(null, 3, 4)),
	
	/**
	* Chapel room.
	*/
	CHAPEL(50000, 45, 0, 2, 5, new Hotspot(BuildHotspot.ALTAR, 3, 5),
			new Hotspot(BuildHotspot.STATUE, 7, 0),
			new Hotspot(BuildHotspot.STATUE, 0, 0),
			new Hotspot(BuildHotspot.ICON, 3, 7),
			new Hotspot(BuildHotspot.MUSICAL, 7, 3),
			new Hotspot(BuildHotspot.BURNERS, 1, 5),
			new Hotspot(BuildHotspot.BURNERS, 6, 5),
			new Hotspot(BuildHotspot.CHAPEL_WINDOW, 0, 2),
			new Hotspot(BuildHotspot.CHAPEL_WINDOW, 0, 5),
			new Hotspot(BuildHotspot.CHAPEL_WINDOW, 0, 2),
			new Hotspot(BuildHotspot.CHAPEL_WINDOW, 2, 7),
			new Hotspot(BuildHotspot.CHAPEL_WINDOW, 5, 7),
			new Hotspot(BuildHotspot.CHAPEL_WINDOW, 7, 5),
			new Hotspot(BuildHotspot.CHAPEL_WINDOW, 7, 2)),
	
	/**
	* Portal chamber.
	*/
	PORTAL_CHAMBER(100000, 50, 0, 1, 4, new Hotspot(BuildHotspot.TELEPORT_FOCUS, 3, 3),
			new Hotspot(BuildHotspot.PORTAL1, 0, 3),
			new Hotspot(BuildHotspot.PORTAL2, 3, 7),
			new Hotspot(BuildHotspot.PORTAL3, 7, 3)),
	
	/**
	* The formal garden.
	* TODO: Hedges..
	*/
	FORMAL_GARDEN(75000, 55, 0, 2, 1, new Hotspot(BuildHotspot.CENTREPIECE_2, 3, 3),
			new Hotspot(BuildHotspot.FENCING, 0, 0),
			new Hotspot(BuildHotspot.FENCING, 1, 0),
			new Hotspot(BuildHotspot.FENCING, 2, 0),
			new Hotspot(BuildHotspot.FENCING, 5, 0),
			new Hotspot(BuildHotspot.FENCING, 6, 0),
			new Hotspot(BuildHotspot.FENCING, 7, 0),
			new Hotspot(BuildHotspot.FENCING, 7, 1),
			new Hotspot(BuildHotspot.FENCING, 7, 2),
			new Hotspot(BuildHotspot.FENCING, 7, 5),
			new Hotspot(BuildHotspot.FENCING, 7, 6),
			new Hotspot(BuildHotspot.FENCING, 7, 7),
			new Hotspot(BuildHotspot.FENCING, 6, 7),
			new Hotspot(BuildHotspot.FENCING, 5, 7),
			new Hotspot(BuildHotspot.FENCING, 2, 7),
			new Hotspot(BuildHotspot.FENCING, 1, 7),
			new Hotspot(BuildHotspot.FENCING, 0, 7),
			new Hotspot(BuildHotspot.FENCING, 0, 6),
			new Hotspot(BuildHotspot.FENCING, 0, 5),
			new Hotspot(BuildHotspot.FENCING, 0, 2),
			new Hotspot(BuildHotspot.FENCING, 0, 1),
			new Hotspot(BuildHotspot.SMALL_PLANT2, 2, 1),
			new Hotspot(BuildHotspot.SMALL_PLANT2, 1, 2),
			new Hotspot(BuildHotspot.SMALL_PLANT2, 5, 6),
			new Hotspot(BuildHotspot.SMALL_PLANT2, 6, 5),
			new Hotspot(BuildHotspot.BIG_PLANT2, 1, 1),
			new Hotspot(BuildHotspot.BIG_PLANT2, 6, 6),
			new Hotspot(BuildHotspot.SMALL_PLANT1, 5, 1),
			new Hotspot(BuildHotspot.SMALL_PLANT1, 6, 2),
			new Hotspot(BuildHotspot.SMALL_PLANT1, 1, 5),
			new Hotspot(BuildHotspot.SMALL_PLANT1, 2, 6),
			new Hotspot(BuildHotspot.BIG_PLANT1, 1, 6),
			new Hotspot(BuildHotspot.BIG_PLANT1, 6, 1)),
	
	/**
	* Throne room.
	* TODO: The floorpiece should come down when the lever is pulled.
	*/
	//THRONE_ROOM(150000, 60, 0, 6, 5, new Hotspot(null, 3, 3)),
	
	/**
	* Oubilette (dungeon).
	*/
	//OUBILETTE(150000, 65, 0, 6, 3, new Hotspot(null, 3, 4)),
	
	/**
	* Dungeon corridor.
	*/
	//DUNGEON_CORRIDOR(7500, 70, 0, 4, 3, new Hotspot(null, 3, 4)),
	
	/**
	* Dungeon junction.
	*/
	//DUNGEON_JUNCTION(7500, 70, 0, 0, 3, new Hotspot(null, 3, 4)),
	
	/**
	* Dungeon stairs.
	*/
	//DUNGEON_STAIRS(7500, 70, 0, 2, 3, new Hotspot(null, 3, 4)),
	
	/**
	* Treasure room.
	*/
	//TREASURE_ROOM(250000, 75, 0, 7, 4, new Hotspot(null, 3, 4)),
	
	;

	/**
	 * The amount this room costs.
	 */
	private final int cost;

	/**
	 * The level required.
	 */
	private final int level;

	/**
	 * The chunk plane.
	 */
	private final int z;

	/**
	 * The chunk x-offset.
	 */
	private final int chunkX;

	/**
	 * The chunk y-offset.
	 */
	private final int chunkY;

	/**
	 * The hotspots in this room.
	 */
	private final Hotspot[] hotspots;

	/**
	 * Constructs a new {@code RoomProperties} {@code Object}.
	 * @param z The chunk plane.
	 * @param chunkX The chunk x-offset.
	 * @param chunkY The chunk y-offset.
	 * @param regionId The region id to copy from.
	 * @param hotspots The hotspots.
	 */
	private RoomProperties(int cost, int level, int z, int chunkX, int chunkY, Hotspot...hotspots) {
		this.cost = cost;
		this.level = level;
		this.z = z;
		this.chunkX = chunkX;
		this.chunkY = chunkY;
		this.hotspots = hotspots;
	}

	/**
	 * Gets the z.
	 * @return The z.
	 */
	public int getZ() {
		return z;
	}

	/**
	 * Gets the chunkX.
	 * @return The chunkX.
	 */
	public int getChunkX() {
		return chunkX;
	}

	/**
	 * Gets the chunkY.
	 * @return The chunkY.
	 */
	public int getChunkY() {
		return chunkY;
	}

	/**  
	 * Gets the hotspots.
	 * @return The hotspots.
	 */
	public Hotspot[] getHotspots() {
		return hotspots;
	}

	/**
	 * Gets the cost.
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Gets the level.
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
}