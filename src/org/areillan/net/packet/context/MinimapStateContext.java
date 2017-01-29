package org.areillan.net.packet.context;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.net.packet.Context;

/**
 * Represents the context used for the Minimap State packet.
 * @author Emperor
 */
public class MinimapStateContext implements Context {

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The minimap state to set.
	 */
	private final int state;

	/**
	 * Constructs a new {@code MinimapStateContext} {@code Object}.
	 * @param player The player.
	 * @param state The minimap state to set.
	 */
	public MinimapStateContext(Player player, int state) {
		this.player = player;
		this.state = state;
	}

	@Override
	public Player getPlayer() {
		if (player != null && player.isArtificial() && (Player)((AIPlayer)player).getControler().getAttribute("aip_became") == player) {
			return ((AIPlayer)player).getControler();
		}
		return player;
	}

	/**
	 * Gets the state.
	 * @return The state.
	 */
	public int getState() {
		return state;
	}

}