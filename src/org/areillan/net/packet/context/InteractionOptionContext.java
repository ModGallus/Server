package org.areillan.net.packet.context;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.net.packet.Context;

/**
 * The context implementation used for the InteractionOption outgoing packet.
 * @author Emperor
 */
public final class InteractionOptionContext implements Context {

	/**
	 * The player reference.
	 */
	private final Player player;

	/**
	 * The index.
	 */
	private final int index;

	/**
	 * The name.
	 */
	private final String name;

	/**
	 * Constructs a new {@code InteractionOptionContext} {@code Object}.
	 * @param player The player.
	 * @param index The index.
	 * @param name The option name.
	 */
	public InteractionOptionContext(Player player, int index, String name) {
		this.player = player;
		this.index = index;
		this.name = name;
	}

	@Override
	public Player getPlayer() {
		if (player != null && player.isArtificial() && (Player)((AIPlayer)player).getControler().getAttribute("aip_became") == player) {
			return ((AIPlayer)player).getControler();
		}
		return player;
	}

	/**
	 * Gets the index.
	 * @return The index.
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Gets the name.
	 * @return The name.
	 */
	public String getName() {
		return name;
	}

}