package org.areillan.net.packet.out;

import org.areillan.game.node.entity.player.link.audio.Audio;
import org.areillan.net.packet.IoBuffer;
import org.areillan.net.packet.OutgoingPacket;
import org.areillan.net.packet.context.DefaultContext;

/**
 * Sends an audio packet.
 * @author Vexia
 */
public class AudioPacket implements OutgoingPacket<DefaultContext> {

	//208 music effect
	//4 music
	//172 sound effect
	@Override
	public void send(DefaultContext context) {
		final Audio audio = (Audio) context.getObjects()[0];
		IoBuffer buffer = new IoBuffer(172);
		buffer.putShort(audio.getId());
		buffer.put((byte) audio.getVolume());
		buffer.putShort(audio.getDelay());
		context.getPlayer().getSession().write(buffer);
	}

}
