package org.areillan.net.packet.out;

import org.areillan.net.packet.IoBuffer;
import org.areillan.net.packet.OutgoingPacket;
import org.areillan.net.packet.context.PlayerContext;

/**
 * Handles the removal of the minimap flag.
 * @author Emperor
 */
public final class ClearMinimapFlag implements OutgoingPacket<PlayerContext> {

	@Override
	public void send(PlayerContext context) {
		context.getPlayer().getDetails().getSession().write(new IoBuffer(153));
	}

}