package org.areillan.net.packet.out;

import org.areillan.net.packet.IoBuffer;
import org.areillan.net.packet.OutgoingPacket;
import org.areillan.net.packet.context.InterfaceContext;

/**
 * The interface outgoing packet.
 * @author Emperor
 */
public final class Interface implements OutgoingPacket<InterfaceContext> {

	@Override
	public void send(InterfaceContext context) {
		IoBuffer buffer = new IoBuffer(155);
		buffer.put(context.isWalkable() ? 1 : 0);
		buffer.putIntB(context.getWindowId() << 16 | context.getComponentId()).putShortA(context.getPlayer().getInterfaceManager().getPacketCount(1)).putShort(context.getInterfaceId());
		context.getPlayer().getDetails().getSession().write(buffer);
	}

}