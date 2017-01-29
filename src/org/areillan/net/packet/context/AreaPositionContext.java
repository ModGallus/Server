package org.areillan.net.packet.context;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.game.world.map.Location;
import org.areillan.net.packet.Context;

/**
 * Handles the area position update packet context.
 * @author Emperor
 */
public final class AreaPositionContext implements Context {

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The location.
	 */
	private final Location location;

	/**
	 * The offset x-coordinate.
	 */
	private final int offsetX;

	/**
	 * The offset y-coordinate.
	 */
	private final int offsetY;

	/**
	 * Constructs a new {@code AreaPositionContext} {@code Object}.
	 * @param player The player.
	 * @param location The location.
	 * @param offsetX The offset x-coordinate.
	 * @param offsetY The offset y-coordinate.
	 */
	public AreaPositionContext(Player player, Location location, int offsetX, int offsetY) {
		this.player = player;
		this.location = location;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
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
	 * Gets the offsetX.
	 * @return The offsetX.
	 */
	public int getOffsetX() {
		return offsetX;
	}

	/**
	 * Gets the offsetY.
	 * @return The offsetY.
	 */
	public int getOffsetY() {
		return offsetY;
	}

}