package org.areillan.net.packet.context;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.net.packet.Context;

/**
 * Represents the windows pane packet context.
 * @author Emperor
 */
public final class WindowsPaneContext implements Context {

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The window id.
	 */
	private final int windowId;

	/**
	 * The type.
	 */
	private final int type;

	/**
	 * Constructs a new {@code WindowsPaneContext} object.
	 * @param player The player.
	 * @param windowId The window id.
	 * @param type The type.
	 */
	public WindowsPaneContext(Player player, int windowId, int type) {
		this.player = player;
		this.windowId = windowId;
		this.type = type;
	}

	@Override
	public Player getPlayer() {
		if (player != null && player.isArtificial() && (Player)((AIPlayer)player).getControler().getAttribute("aip_became") == player) {
			return ((AIPlayer)player).getControler();
		}
		return player;
	}

	/**
	 * Gets the windowId.
	 * @return The windowId.
	 */
	public int getWindowId() {
		return windowId;
	}

	/**
	 * Gets the type.
	 * @return The type.
	 */
	public int getType() {
		return type;
	}

}
