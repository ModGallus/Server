package org.areillan.net.packet.out;

import org.areillan.net.packet.IoBuffer;
import org.areillan.net.packet.OutgoingPacket;
import org.areillan.net.packet.context.InterfaceConfigContext;

/**
 * The outgoing interface configuration packet.
 * @author Emperor
 */
public class InterfaceConfig implements OutgoingPacket<InterfaceConfigContext> {

	@Override
	public void send(InterfaceConfigContext context) {
		IoBuffer buffer = new IoBuffer(21);
		buffer.putC(context.isHidden() ? 1 : 0);
		buffer.putShort(context.getPlayer().getInterfaceManager().getPacketCount(1));
		buffer.putLEInt(context.getInterfaceId() << 16 | context.getChildId());
		context.getPlayer().getSession().write(buffer);
	}
}
