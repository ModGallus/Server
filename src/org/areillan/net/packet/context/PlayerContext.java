package org.areillan.net.packet.context;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.net.packet.Context;

/**
 * The default packet context.
 * @author Emperor
 */
public final class PlayerContext implements Context {

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * Constructs a new {@code PlayerContext} {@code Object}.
	 * @param player The player.
	 */
	public PlayerContext(Player player) {
		this.player = player;
	}

	@Override
	public Player getPlayer() {
		if (player != null && player.isArtificial() && (Player)((AIPlayer)player).getControler().getAttribute("aip_became") == player) {
			return ((AIPlayer)player).getControler();
		}
		return player;
	}

}