package org.areillan.net.packet.out;

import org.areillan.net.packet.IoBuffer;
import org.areillan.net.packet.OutgoingPacket;
import org.areillan.net.packet.context.WindowsPaneContext;

/**
 * Handles the windows pane outgoing packet.
 * @author Emperor
 */
public final class WindowsPane implements OutgoingPacket<WindowsPaneContext> {

	@Override
	public void send(WindowsPaneContext context) {
		IoBuffer buffer = new IoBuffer(145);
		buffer.putLEShortA(context.getWindowId());
		buffer.putS(context.getType());
		buffer.putLEShortA(context.getPlayer().getInterfaceManager().getPacketCount(1));
		context.getPlayer().getDetails().getSession().write(buffer);
	}

}