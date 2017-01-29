package org.areillan.net.packet.context;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.net.packet.Context;

/**
 * The StringPacket packet context.
 * @author Emperor
 */
public class StringContext implements Context {

	/**
	 * The player reference.
	 */
	private Player player;

	/**
	 * The StringPacket string.
	 */
	private String string;

	/**
	 * The interface id.
	 */
	private int interfaceId;

	/**
	 * The line id.
	 */
	private int lineId;

	/**
	 * Constructs a new {@code StringContext} {@code Object}.
	 * @param player The player.
	 * @param string The string to send.
	 * @param interfaceId The interface id.
	 * @param lineId The child id.
	 */
	public StringContext(Player player, String string, int interfaceId, int lineId) {
		this.player = player;
		this.string = string;
		this.interfaceId = interfaceId;
		this.lineId = lineId;
	}

	@Override
	public Player getPlayer() {
		if (player != null && player.isArtificial() && (Player)((AIPlayer)player).getControler().getAttribute("aip_became") == player) {
			return ((AIPlayer)player).getControler();
		}
		return player;
	}

	/**
	 * Get the StringPacket string.
	 * @return The string.
	 */
	public String getString() {
		return string;
	}

	/**
	 * Get the interface id.
	 * @return The interface id.
	 */
	public int getInterfaceId() {
		return interfaceId;
	}

	/**
	 * Gets the line id.
	 * @return The line id.
	 */
	public int getLineId() {
		return lineId;
	}
}
