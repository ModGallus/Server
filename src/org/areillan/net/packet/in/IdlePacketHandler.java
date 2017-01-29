package org.areillan.net.packet.in;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.info.Rights;
import org.areillan.net.packet.IncomingPacket;
import org.areillan.net.packet.IoBuffer;

/**
 * Handles the idle packet handler.
 * @author Emperor
 */
public final class IdlePacketHandler extends IncomingPacket {

	@Override
	public void decode(Player player, int opcode, IoBuffer buffer) {
		if (player.getDetails().getRights() != Rights.ADMINISTRATOR) {
			player.getPacketDispatch().sendLogout();
		}
	}

}