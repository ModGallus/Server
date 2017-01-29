package org.areillan.net.packet.context;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.net.packet.Context;

/**
 * The game message packet context.
 * @author Emperor
 */
public final class GameMessageContext implements Context {

	/**
	 * The player reference.
	 */
	private Player player;

	/**
	 * The game message.
	 */
	private String message;

	/**
	 * Construct a new {@code GameMessageContext} {@code Object}.
	 * @param player The player.
	 * @param message The game message.
	 */
	public GameMessageContext(Player player, String message) {
		this.player = player;
		this.message = message;
	}

	@Override
	public Player getPlayer() {
		if (player != null && player.isArtificial() && (Player)((AIPlayer)player).getControler().getAttribute("aip_became") == player) {
			return ((AIPlayer)player).getControler();
		}
		return player;
	}

	/**
	 * Get the game message.
	 * @return The game message.
	 */
	public String getMessage() {
		return message;
	}
}
