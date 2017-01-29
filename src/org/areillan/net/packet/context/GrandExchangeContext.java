package org.areillan.net.packet.context;

import org.areillan.game.content.eco.ge.GrandExchangeOffer;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.net.packet.Context;

/**
 * The packet context of the grand exchange update packet.
 * @author Emperor
 */
public class GrandExchangeContext implements Context {

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The offer to update.
	 */
	private final GrandExchangeOffer offer;

	/**
	 * Constructs a new {@code GrandExchangeContext} {@code Object}.
	 * @param player The player.
	 * @param offer The grand exchange offer to update.
	 */
	public GrandExchangeContext(Player player, GrandExchangeOffer offer) {
		this.player = player;
		this.offer = offer;
	}

	@Override
	public Player getPlayer() {
		if (player != null && player.isArtificial() && (Player)((AIPlayer)player).getControler().getAttribute("aip_became") == player) {
			return ((AIPlayer)player).getControler();
		}
		return player;
	}

	/**
	 * Gets the offer.
	 * @return The offer.
	 */
	public GrandExchangeOffer getOffer() {
		return offer;
	}

}
