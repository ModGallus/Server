package org.areillan.net.packet.context;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.net.packet.Context;

/**
 * Represents the set walk-to option context.
 * @author Emperor
 */
public final class WalkOptionContext implements Context {

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The option.
	 */
	private final String option;

	/**
	 * Constructs a new {@code WalkOptionContext} {@code Object}.
	 * @param player The player.
	 * @param option The option name.
	 */
	public WalkOptionContext(Player player, String option) {
		this.player = player;
		this.option = option;
	}

	@Override
	public Player getPlayer() {
		if (player != null && player.isArtificial() && (Player)((AIPlayer)player).getControler().getAttribute("aip_became") == player) {
			return ((AIPlayer)player).getControler();
		}
		return player;
	}

	/**
	 * Gets the option.
	 * @return The option.
	 */
	public String getOption() {
		return option;
	}

}