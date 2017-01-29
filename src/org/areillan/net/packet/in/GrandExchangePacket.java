package org.areillan.net.packet.in;

import org.areillan.game.node.entity.player.Player;
import org.areillan.net.packet.IncomingPacket;
import org.areillan.net.packet.IoBuffer;

/**
 * Represents the <b>Incoming</b> packet of the grand exchange.
 * @author Emperor
 */
public class GrandExchangePacket extends IncomingPacket {

	@Override
	public void decode(Player p, int opcode, IoBuffer buffer) {
		final Player player = useAIP(p);
		int itemId = buffer.getShort();
		player.getGrandExchange().constructBuy(itemId);
		player.getInterfaceManager().closeChatbox();
	}

}
