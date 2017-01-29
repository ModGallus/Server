package org.areillan.net.packet.context;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.game.system.communication.ClanRepository;
import org.areillan.net.packet.Context;

/**
 * The packet context for clan-related outgoing packets.
 * @author Emperor
 */
public final class ClanContext implements Context {

	/**
	 * The player
	 */
	private final Player player;

	/**
	 * The clan instance.
	 */
	private final ClanRepository clan;

	/**
	 * If the player is leaving the clan.
	 */
	private final boolean leave;

	/**
	 * Constructs a new {@code ClanContext} {@code Object}.
	 * @param player the player.
	 * @param clan the clan.
	 * @param leave If the player is leaving the clan.
	 */
	public ClanContext(Player player, ClanRepository clan, boolean leave) {
		this.player = player;
		this.clan = clan;
		this.leave = leave;
	}

	/**
	 * Gets the clan.
	 * @return The clan.
	 */
	public ClanRepository getClan() {
		return clan;
	}

	/**
	 * Gets the leave.
	 * @return The leave.
	 */
	public boolean isLeave() {
		return leave;
	}

	@Override
	public Player getPlayer() {
		if (player != null && player.isArtificial() && (Player)((AIPlayer)player).getControler().getAttribute("aip_became") == player) {
			return ((AIPlayer)player).getControler();
		}
		return player;
	}
}