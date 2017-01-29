package org.areillan.net.packet.in;

import org.areillan.game.node.entity.player.Player;
import org.areillan.net.packet.IncomingPacket;
import org.areillan.net.packet.IoBuffer;

/**
 * Handles an incoming camera movement changed packet.
 * @author Emperor
 */
public final class CameraMovementPacket extends IncomingPacket {

	@Override
	public void decode(Player player, int opcode, IoBuffer buffer) {
		buffer.getShortA();
		buffer.getLEShort();
	}

}