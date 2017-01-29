package org.areillan.net.packet;

import java.util.List;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;

/**
 * Represents an incoming packet.
 * @author Emperor
 */
public class IncomingPacket {

	/**
	 * Decodes the incoming packet.
	 * @param player The player.
	 * @param opcode The opcode.
	 * @param buffer The buffer.
	 * @return The new buffer to send in response.
	 */
	public void decode(Player player, int opcode, IoBuffer buffer) {
		// If we have a legion they can just copy us.
		// If we do a move command the legion will follow us instead.
		/*List<AIPlayer> legion = player.getAttribute("aip_legion");
		if (legion != null && !legion.isEmpty()) {
			for (AIPlayer aip : legion) {
				if (aip != null && aip.isArtificial())
					decode(aip, opcode, buffer);
			}
		}*/
	}
	
	public Player useAIP(final Player player) {
		final Player aip = player == null ? null : (Player)player.getAttribute("aip_became");
		return aip != null ? aip : player;
	}

}