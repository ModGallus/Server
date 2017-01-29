package org.areillan.net.packet.out;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.object.GameObject;
import org.areillan.game.world.map.Location;
import org.areillan.game.world.update.flag.context.Animation;
import org.areillan.net.packet.IoBuffer;
import org.areillan.net.packet.OutgoingPacket;
import org.areillan.net.packet.context.AnimateObjectContext;

/**
 * Represents the packet used to animate an object.
 * @author Vexia
 * @date 10/11/2013
 */
public class AnimateObjectPacket implements OutgoingPacket<AnimateObjectContext> {

	/**
	 * Writes the packet.
	 * @param buffer The buffer.
	 * @param item The item.
	 */
	public static IoBuffer write(IoBuffer buffer, Animation animation) {
		GameObject object = animation.getObject();
		Location l = object.getLocation();
		buffer.put(20);
		buffer.putS((l.getChunkOffsetX() << 4) | (l.getChunkOffsetY() & 0x7));
		buffer.putS((object.getType() << 2) + (object.getRotation() & 0x3));
		buffer.putLEShort(animation.getId());
		return buffer;
	}

	@Override
	public void send(AnimateObjectContext context) {
		Player player = context.getPlayer();
		GameObject object = context.getAnimation().getObject();
		IoBuffer buffer = write(UpdateAreaPosition.getBuffer(player, object.getLocation().getChunkBase()), context.getAnimation());
		player.getSession().write(buffer);
	}
}
