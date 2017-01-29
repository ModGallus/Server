package org.areillan.net.packet.context;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.net.packet.Context;

/**
 * The skill context.
 * @author Emperor
 */
public final class SkillContext implements Context {

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The skill id.
	 */
	private final int skillId;

	/**
	 * Constructs a new {@code SkillContext} {@code Object}.
	 * @param player The player.
	 * @param skillId The skill id.
	 */
	public SkillContext(Player player, int skillId) {
		this.player = player;
		this.skillId = skillId;
	}

	@Override
	public Player getPlayer() {
		if (player != null && player.isArtificial() && (Player)((AIPlayer)player).getControler().getAttribute("aip_became") == player) {
			return ((AIPlayer)player).getControler();
		}
		return player;
	}

	/**
	 * Gets the skillId.
	 * @return The skillId.
	 */
	public int getSkillId() {
		return skillId;
	}

}