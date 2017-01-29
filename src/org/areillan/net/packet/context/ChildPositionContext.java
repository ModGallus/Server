package org.areillan.net.packet.context;

import java.awt.Point;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.net.packet.Context;

/**
 * A context implementation used for the interface child repositioning packet.
 * @author Emperor
 */
public final class ChildPositionContext implements Context {

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The interface id.
	 */
	private final int interfaceId;

	/**
	 * The child id.
	 */
	private final int childId;

	/**
	 * The new position of the child.
	 */
	private final Point position;

	/**
	 * Constructs a new {@code ChildPositionContext} {@code Object}.
	 * @param player The player.
	 * @param interfaceId The interface id.
	 * @param childId The id of the child to reposition.
	 * @param positionX The new x-position.
	 * @param positionY The new y-position.
	 */
	public ChildPositionContext(Player player, int interfaceId, int childId, int positionX, int positionY) {
		this.player = player;
		this.interfaceId = interfaceId;
		this.childId = childId;
		this.position = new Point(positionX, positionY);
	}

	@Override
	public Player getPlayer() {
		if (player != null && player.isArtificial() && (Player)((AIPlayer)player).getControler().getAttribute("aip_became") == player) {
			return ((AIPlayer)player).getControler();
		}
		return player;
	}

	/**
	 * Gets the interfaceId.
	 * @return The interfaceId.
	 */
	public int getInterfaceId() {
		return interfaceId;
	}

	/**
	 * Gets the childId.
	 * @return The childId.
	 */
	public int getChildId() {
		return childId;
	}

	/**
	 * Gets the position.
	 * @return The position.
	 */
	public Point getPosition() {
		return position;
	}

}