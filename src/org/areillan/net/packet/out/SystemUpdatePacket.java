package org.areillan.net.packet.out;

import org.areillan.net.packet.IoBuffer;
import org.areillan.net.packet.OutgoingPacket;
import org.areillan.net.packet.context.SystemUpdateContext;

/**
 * Handles the system update packet.
 * @author 'Vexia
 */
public class SystemUpdatePacket implements OutgoingPacket<SystemUpdateContext> {

	@Override
	public void send(final SystemUpdateContext context) {
		IoBuffer buffer = new IoBuffer(85).putShort(context.getTime());
		context.getPlayer().getDetails().getSession().write(buffer);
	}

}
