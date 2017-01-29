package org.areillan.net.packet.out;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.item.Item;
import org.areillan.game.world.map.Location;
import org.areillan.net.packet.IoBuffer;
import org.areillan.net.packet.OutgoingPacket;
import org.areillan.net.packet.context.BuildItemContext;

/**
 * Represents the outgoing packet of constructing a ground item.
 * @author Emperor
 */
public final class ConstructGroundItem implements OutgoingPacket<BuildItemContext> {

	/**
	 * Writes the packet.
	 * @param buffer The buffer.
	 * @param item The item.
	 */
	public static IoBuffer write(IoBuffer buffer, Item item) {
		Location l = item.getLocation();
		buffer.put(33);
		buffer.putLEShort(item.getId()).put((l.getChunkOffsetX() << 4) | (l.getChunkOffsetY() & 0x7)).putShortA(item.getAmount());
		return buffer;
	}

	@Override
	public void send(BuildItemContext context) {
		Player player = context.getPlayer();
		Item item = context.getItem();
		IoBuffer buffer = write(UpdateAreaPosition.getBuffer(player, item.getLocation().getChunkBase()), item);
		player.getDetails().getSession().write(buffer);
	}
}
