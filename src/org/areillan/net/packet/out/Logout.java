package org.areillan.net.packet.out;

import org.areillan.net.packet.IoBuffer;
import org.areillan.net.packet.OutgoingPacket;
import org.areillan.net.packet.context.PlayerContext;

/**
 * The outgoing logout packet.
 * @author Emperor
 */
public class Logout implements OutgoingPacket<PlayerContext> {

	@Override
	public void send(PlayerContext context) {
		IoBuffer buffer = new IoBuffer(86);
		context.getPlayer().getDetails().getSession().write(buffer);
	}
}