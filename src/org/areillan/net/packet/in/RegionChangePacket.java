package org.areillan.net.packet.in;

import org.areillan.game.node.entity.player.Player;
import org.areillan.net.packet.IncomingPacket;
import org.areillan.net.packet.IoBuffer;

/**
 * Packet received when a player's region has changed.
 * @author Emperor
 * @author 'Vexia
 */
public class RegionChangePacket extends IncomingPacket {

	@Override
	public void decode(Player player, int opcode, IoBuffer buffer) {
		//TODO: no data is sen't so not sure what to do.
	}

}
