package org.areillan.net.packet.context;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.net.packet.Context;

/**
 * The <b>Integer</b> context.
 * @author 'Vexia
 */
public class IntegerContext implements Context {

	/**
	 * The player instance.
	 */
	private Player player;

	/**
	 * The integer value.
	 */
	private int integer;

	/**
	 * Constructs a new {@code IntegerContext.java} {@code Object}.
	 * @param player the player.
	 * @param integer the integer.
	 */
	public IntegerContext(Player player, int integer) {
		this.player = player;
		this.setInteger(integer);
	}

	@Override
	public Player getPlayer() {
		if (player != null && player.isArtificial() && (Player)((AIPlayer)player).getControler().getAttribute("aip_became") == player) {
			return ((AIPlayer)player).getControler();
		}
		return player;
	}

	/**
	 * @return the integer.
	 */
	public int getInteger() {
		return integer;
	}

	/**
	 * @param integer the integer to set.
	 */
	public void setInteger(int integer) {
		this.integer = integer;
	}

}
