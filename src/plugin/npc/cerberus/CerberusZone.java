package plugin.npc.cerberus;

import java.util.ArrayList;
import java.util.List;

import org.areillan.game.node.entity.Entity;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.world.map.Location;
import org.areillan.game.world.map.Region;
import org.areillan.game.world.map.RegionManager;
import org.areillan.game.world.map.zone.MapZone;
import org.areillan.game.world.map.zone.ZoneRestriction;

/**
 * Handles the cerberus zone.
 * @author Vexia
 *
 */
public class CerberusZone extends MapZone {
	
	/**
	 * The list of players in the boss room.
	 */
	private final List<Player> players = new ArrayList<>();
	
	/**
	 * The cerberus npc.
	 */
	private final CerberusNPC cerberus;
	
	/**
	 * The region id.
	 */
	private final int regionId;
	
	/**
	 * The region object.
	 */
	private final Region region;

	/**
	 * Constructs a new @{Code CerberusZone} object.
	 */
	public CerberusZone(int regionId, CerberusNPC cerberus) {
		super("cerberus zone", true, ZoneRestriction.FIRES, ZoneRestriction.CANNON, ZoneRestriction.RANDOM_EVENTS);
		this.regionId = regionId;
		this.cerberus = cerberus;
		this.region = RegionManager.getRegionCache().get(regionId);
	}
	
	@Override
	public boolean enter(Entity e) {
		if (e instanceof Player) {
			players.add((Player) e);
		}
		return super.enter(e);
	}
	
	@Override
	public boolean leave(Entity e, boolean logout) {
		if (e instanceof Player) {
			players.remove((Player) e);
		}
		return super.leave(e, logout);
	}
	
	@Override
	public void locationUpdate(Entity e, Location last) {
		
	}

	@Override
	public void configure() {
		registerRegion(regionId);
		cerberus.init();
	}
	
	/**
	 * Gets the list of players in the room.
	 * @return The list array.
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * Gets the base location.
	 * @return
	 */
	public Location getBase() {
		return region.getBaseLocation();
	}
	
	/**
	 * Gets the region id.
	 * @return The region id.
	 */
	public int getRegionId() {
		return regionId;
	}
}
