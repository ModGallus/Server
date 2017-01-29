package org.areillan.net.packet.in;

import org.areillan.game.node.entity.player.Player;
import org.areillan.net.packet.IncomingPacket;
import org.areillan.net.packet.IoBuffer;

/**
 * Handles an incoming client focus changed packet.
 * @author Emperor
 */
public final class ClientFocusPacket extends IncomingPacket {

	@Override
	public void decode(Player player, int opcode, IoBuffer buffer) {
		if (player != null) {
			player.getMonitor().setClientFocus(buffer.get() == 1);
		}
	}

}