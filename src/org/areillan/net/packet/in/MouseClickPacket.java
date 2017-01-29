package org.areillan.net.packet.in;

import org.areillan.game.node.entity.player.Player;
import org.areillan.net.packet.IncomingPacket;
import org.areillan.net.packet.IoBuffer;

/**
 * Handles incoming mouse click packets.
 * @author Emperor
 */
public final class MouseClickPacket extends IncomingPacket {

	@Override
	public void decode(Player p, int opcode, IoBuffer buffer) {
		Player player = useAIP(p);
		int data = buffer.getLEShortA();
		int positioning = buffer.getIntB();
		boolean rightClick = ((data >> 15) & 0x1) == 1;
		int delay = data & 0x7FF;
		int x = positioning >> 16;
		int y = positioning & 0xFFFF;
		if (player == null) {
			return;
		}
		player.getMonitor().handleMouseClick(x, y, delay, rightClick);
	}

}