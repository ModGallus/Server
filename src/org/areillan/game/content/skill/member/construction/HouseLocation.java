package org.areillan.game.content.skill.member.construction;

import org.areillan.game.world.map.Location;


/**
 * The house locations.
 * @author Emperor
 *
 */
public enum HouseLocation {
	
	NOWHERE(-1, null),
	
	RIMMINGTON(15478, Location.create(2953, 3224, 0)),
	
	TAVERLY(15477, Location.create(2893, 3465, 0)),
	
	POLLNIVNEACH(15479, Location.create(3340, 3003, 0)),
	
	RELLEKKA(15480, Location.create(2670, 3631, 0)),
	
	BRIMHAVEN(15481, Location.create(2757, 3178, 0)),
	
	YANILLE(15482, Location.create(2544, 3096, 0));
	
	/**
	 * The portal object id for this location.
	 */
	private final int portalId;
	
	/**
	 * The exit location.
	 */
	private final Location exitLocation;
	
	/**
	 * Constructs a new {@code HouseLocation} {@code Object}
	 * @param portalId The portal id.
	 * @param exitLocation The exit location.
	 */
	private HouseLocation(int portalId, Location exitLocation) {
		this.portalId = portalId;
		this.exitLocation = exitLocation;
	}

	/**
	 * Gets the portalId.
	 * @return the portalId
	 */
	public int getPortalId() {
		return portalId;
	}

	/**
	 * Gets the exitLocation.
	 * @return the exitLocation
	 */
	public Location getExitLocation() {
		return exitLocation;
	}
}