package org.areillan.net.packet.in;

import org.areillan.game.node.entity.player.Player;
import org.areillan.net.packet.IncomingPacket;
import org.areillan.net.packet.IoBuffer;

/**
 * Handles music-related incoming packets.
 * @author Emperor
 */
public final class MusicPacketHandler extends IncomingPacket {

	@Override
	public void decode(Player p, int opcode, IoBuffer buffer) {
		Player player = useAIP(p);
		int musicId = buffer.getLEShortA();
		if (player.getMusicPlayer().isLooping()) {
			player.getMusicPlayer().replay();
			return;
		}
		if (player.getMusicPlayer().getCurrentMusicId() == musicId) {
			player.getMusicPlayer().setPlaying(false);
		}
	}

}