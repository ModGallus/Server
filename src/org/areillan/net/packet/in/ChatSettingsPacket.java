package org.areillan.net.packet.in;

import org.areillan.game.node.entity.player.Player;
import org.areillan.net.amsc.MSPacketRepository;
import org.areillan.net.amsc.WorldCommunicator;
import org.areillan.net.packet.IncomingPacket;
import org.areillan.net.packet.IoBuffer;

/**
 * Handles an incoming chat settings update packet.
 * @author Emperor
 */
public final class ChatSettingsPacket extends IncomingPacket {

	@Override
	public void decode(Player player, int opcode, IoBuffer buffer) {
		int publicSetting = buffer.get();
		int privateSetting = buffer.get();
		int tradeSetting = buffer.get();
		if (WorldCommunicator.isEnabled()) {
			MSPacketRepository.sendChatSetting(player, publicSetting, privateSetting, tradeSetting);
		}
		player.getSettings().updateChatSettings(publicSetting, privateSetting, tradeSetting);
	}

}