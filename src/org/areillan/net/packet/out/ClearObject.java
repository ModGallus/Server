package org.areillan.net.packet.out;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.object.GameObject;
import org.areillan.game.world.map.Location;
import org.areillan.net.packet.IoBuffer;
import org.areillan.net.packet.OutgoingPacket;
import org.areillan.net.packet.context.BuildObjectContext;

/**
 * The clear game object outgoing packet.
 * @author Emperor
 */
public final class ClearObject implements OutgoingPacket<BuildObjectContext> {

	/**
	 * Writes the packet.
	 * @param buffer The buffer.
	 * @param objects The objects.
	 */
	public static IoBuffer write(IoBuffer buffer, GameObject object) {
		Location l = object.getLocation();
		buffer.put(195) // Opcode
				.putC((object.getType() << 2) + (object.getRotation() & 3)).put((l.getChunkOffsetX() << 4) | (l.getChunkOffsetY() & 0x7));
		return buffer;
	}

	@Override
	public void send(BuildObjectContext context) {
		Player player = context.getPlayer();
		GameObject o = context.getGameObject();
		IoBuffer buffer = write(UpdateAreaPosition.getBuffer(player, o.getLocation().getChunkBase()), o);
		player.getSession().write(buffer);

	}
}