package org.areillan.net.packet.in;

import org.areillan.game.node.entity.player.Player;
import org.areillan.net.packet.IncomingPacket;
import org.areillan.net.packet.IoBuffer;

/**
 * Handles an incoming ping packet.
 * @author Emperor
 */
public final class PingPacketHandler extends IncomingPacket {

	@Override
	public void decode(Player player, int opcode, IoBuffer buffer) {
		
	}

}