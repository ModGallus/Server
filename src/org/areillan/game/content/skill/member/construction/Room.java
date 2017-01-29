package org.areillan.game.content.skill.member.construction;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.object.GameObject;
import org.areillan.game.node.object.ObjectBuilder;
import org.areillan.game.world.map.BuildRegionChunk;
import org.areillan.game.world.map.Direction;
import org.areillan.game.world.map.Location;
import org.areillan.game.world.map.Region;
import org.areillan.game.world.map.RegionChunk;
import org.areillan.game.world.map.RegionManager;

/**
 * Represents a room.
 * @author Emperor
 *
 */
public final class Room {
	
	/**
	 * The room properties.
	 */
	private final RoomProperties properties;

	/**
	 * The region chunk.
	 */
	private RegionChunk chunk;
		
	/**
	 * The hotspots.
	 */
	private Hotspot[] hotspots;
	
	/**
	 * The current rotation of the room.
	 */
	private Direction rotation = Direction.NORTH;
	
	/**
	 * Constructs a new {@code Room} {@code Object}.
	 * @param properties The room properties.
	 */
	public Room(RoomProperties properties) {
		this.properties = properties;
	}

	/**
	 * Creates a new room.
	 * @param player The player.
	 * @param properties The room properties.
	 * @return The room.
	 */
	public static Room create(Player player, RoomProperties properties) {
		Room room = new Room(properties);
		room.configure(player.getHouseManager().getStyle());
		return room;
	}
	
	/**
	 * Configures the room.
	 */
	public void configure(HousingStyle style) {
		this.hotspots = new Hotspot[properties.getHotspots().length];
		for (int i = 0; i < hotspots.length; i++) {
			hotspots[i] = properties.getHotspots()[i].copy();
		}
		decorate(style);
	}

	/**
	 * Redecorates the room.
	 * @param style The house style.
	 */
	public void decorate(HousingStyle style) {
		Region region = RegionManager.forId(style.getRegionId());
		Region.load(region, true);
		chunk = region.getPlanes()[style.getPlane()].getRegionChunk(properties.getChunkX(), properties.getChunkY());
	}
	
	/**
	 * Loads all the decorations.
	 * @param chunk The chunk used in the dynamic region.
	 * @param buildingMode If building mode is enabled.
	 * @param style The housing style (for windows)
	 */
	public void loadDecorations(BuildRegionChunk chunk, boolean buildingMode, HousingStyle style) {
		for (int i = 0; i < hotspots.length; i++) {
			Hotspot spot = hotspots[i];
			int x = spot.getChunkX();
			int y = spot.getChunkY();
			int index = chunk.getIndex(x, y, spot.getHotspot().getObjectId());
			GameObject[][] objects = chunk.getObjects(index);
			GameObject object = objects[x][y];
			if (object != null) {
				//TODO: Add objects using LandscapeParser
				if (object.getId() == BuildHotspot.WINDOW.getObjectId()) {
					chunk.add(object.transform(style.getWindowStyle().getObjectId(), object.getRotation(), object.getType()));
				}
				if (spot.getDecorationIndex() > -1) {
					//chunk.remove(object);
					int id = spot.getHotspot().getDecorations()[spot.getDecorationIndex()].getObjectId();
					//chunk.add(object.transform(id, object.getRotation(), chunk.getCurrentBase().transform(x, y, 0)));
					ObjectBuilder.replace(object, object.transform(id, object.getRotation(), chunk.getCurrentBase().transform(x, y, 0)));
				}
			}
		}
		if (!buildingMode) {
			for (int i = 0; i < BuildRegionChunk.ARRAY_SIZE; i++) {
				for (int x = 0; x < 8; x++) {
					for (int y = 0; y < 8; y++) {
						GameObject object = chunk.get(x, y, i);
						if (object != null && object.getDefinition().hasAction("Build")) {
							chunk.remove(object);
						}
					}
				}
			}
		}
		if (rotation != Direction.NORTH && chunk.getRotation() == 0) {
			this.hotspots = chunk.rotateHotspots(hotspots, rotation);
		}
	}

	/**
	 * Gets the exit directions.
	 * @return The directions at which you can exit the room (0=east, 1=south, 2=west, 3=north).
	 */
	public boolean[] getExits(Direction rotation) {
		boolean[] exits = { isExit(7, 3), isExit(3, 0), isExit(0, 3), isExit(3, 7) };
		if (rotation != Direction.NORTH && chunk.getRotation() == 0) {
			for (int i = 0; i < RoomBuilder.DIRECTIONS.length; i++) {
				if (rotation == RoomBuilder.DIRECTIONS[i]) {
					break;
				}
				boolean b = exits[0];
				for (int j = 0; j < exits.length - 1; j++) {
					exits[j] = exits[j + 1];
				}
				exits[exits.length - 1] = b;
			}
		}
		return exits;
	}
	
	/**
	 * Gets a hotspot by buildhotspot type and decoration index
	 * @param buildHotspot
	 * @param decorationIndex
	 * @pparam l - location of hotspot
	 * @return
	 */
	public Hotspot getHotspot(BuildHotspot buildHotspot, int decorationIndex, Location l) {
		for (int i = 0; i < getHotspots().length; i++) {
			Hotspot h = getHotspots()[i];
			if (h.getHotspot() == buildHotspot && h.getDecorationIndex() == decorationIndex) {
				if (buildHotspot.getType() == BuildHotspotType.INDIVIDUAL && 
						((h.getChunkX() != l.getChunkOffsetX() || h.getChunkY() != l.getChunkOffsetY()))) 
					continue;
				return h;
			}
		}
		return null;
	}
		
	/**
	 * Gets the exit directions
	 * @return
	 */
	public boolean[] getExits() {
		return getExits(rotation);
	}
	
	/**
	 * Checks if the object on the given chunk coordinates is a door.
	 * @param chunkX The x location in the chunk.
	 * @param chunkY The y location in the chunk.
	 * @return {@code True} if so.
	 */
	private boolean isExit(int chunkX, int chunkY) {
		for (GameObject object : chunk.getObjects(chunkX, chunkY)) {
			if (object != null && (object.getId() == 15313 || object.getId() == 15314)) { //object.getType() == 0 && object.getDefinition().hasAction("Build")
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the chunk.
	 * @return The chunk.
	 */
	public RegionChunk getChunk() {
		return chunk;
	}

	/**
	 * Sets the chunk.
	 * @param chunk The chunk to set.
	 */
	public void setChunk(RegionChunk chunk) {
		this.chunk = chunk;
	}

	/**
	 * Gets the hotspots.
	 * @return The hotspots.
	 */
	public Hotspot[] getHotspots() {
		return hotspots;
	}

	/**
	 * Sets the hotspots.
	 * @param hotspots The hotspots to set.
	 */
	public void setHotspots(Hotspot[] hotspots) {
		this.hotspots = hotspots;
	}
	
	/**
	 * Sets the decoration index for a group of object ids
	 * @param index
	 * @param objectId
	 */
	public void setAllDecorationIndex(int index, int objectId) {
		for (int i = 0; i < hotspots.length; i++) {
			Hotspot h = hotspots[i];
			if (h.getHotspot().getObjectId() == objectId) {
				h.setDecorationIndex(index);
			}
		}
	}

	/**
	 * Gets the properties.
	 * @return The properties.
	 */
	public RoomProperties getProperties() {
		return properties;
	}

	/**
	 * Sets the room rotation.
	 * @param rotation The rotation.
	 */
	public void setRotation(Direction rotation) {
		this.rotation = rotation;
	}

	/**
	 * Gets the rotation.
	 * @return The rotation.
	 */
	public Direction getRotation() {
		return rotation;
	}
	
}