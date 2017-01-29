package org.areillan.net.packet.context;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.game.world.map.Location;
import org.areillan.net.packet.Context;

/**
 * Packet context used for location based packets.
 * @author Emperor
 */
public final class LocationContext implements Context {

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The location.
	 */
	private final Location location;

	/**
	 * If the location update is flagged as a teleport.
	 */
	private final boolean teleport;

	/**
	 * Constructs a new {@code LocationContext} {@code Object}.
	 * @param player The player.
	 * @param location The location.
	 * @param teleport If the location update is flagged as a teleport.
	 */
	public LocationContext(Player player, Location location, boolean teleport) {
		this.player = player;
		this.location = location;
		this.teleport = teleport;
	}

	@Override
	public Player getPlayer() {
		if (player != null && player.isArtificial() && (Player)((AIPlayer)player).getControler().getAttribute("aip_became") == player) {
			return ((AIPlayer)player).getControler();
		}
		return player;
	}

	/**
	 * Gets the location.
	 * @return The location.
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Gets the teleport.
	 * @return The teleport.
	 */
	public boolean isTeleport() {
		return teleport;
	}

}