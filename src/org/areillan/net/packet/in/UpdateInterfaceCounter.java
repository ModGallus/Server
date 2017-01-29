package org.areillan.net.packet.in;

import org.areillan.game.node.entity.player.Player;
import org.areillan.net.packet.IncomingPacket;
import org.areillan.net.packet.IoBuffer;

/**
 * Handles the update interface packet counter packet.
 * @author Emperor
 *
 */
public final class UpdateInterfaceCounter extends IncomingPacket {

	@Override
	public void decode(Player p, int opcode, IoBuffer buffer) {
		Player player = useAIP(p);
		int count = buffer.getShort() - player.getInterfaceManager().getPacketCount(0);
		player.getInterfaceManager().getPacketCount(count);
	}

}