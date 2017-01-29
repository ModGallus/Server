package org.areillan.net.packet.in;

import org.areillan.game.node.entity.player.Player;
import org.areillan.net.packet.IncomingPacket;
import org.areillan.net.packet.IoBuffer;

/**
 * Handles the display update packet.
 * @author Emperor
 *
 */
public class DisplayUpdatePacket extends IncomingPacket {

	@Override
	public void decode(Player p, int opcode, IoBuffer buffer) {
		Player player = useAIP(p);
		int windowMode = buffer.get(); //Window mode
		int screenWidth = buffer.getShort();
		int screenHeight = buffer.getShort();
		int displayMode = buffer.get(); //Display mode
		player.getSession().getClientInfo().setScreenWidth(screenWidth);
		player.getSession().getClientInfo().setScreenHeight(screenHeight);
		player.getSession().getClientInfo().setDisplayMode(displayMode);
		player.getInterfaceManager().switchWindowMode(windowMode);
	}

}