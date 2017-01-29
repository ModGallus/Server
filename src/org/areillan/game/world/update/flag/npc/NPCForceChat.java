package org.areillan.game.world.update.flag.npc;

import org.areillan.game.world.update.flag.UpdateFlag;
import org.areillan.net.packet.IoBuffer;

/**
 * The NPC force chat update flag.
 * @author Emperor
 *
 */
public final class NPCForceChat extends UpdateFlag<String> {

	/**
	 * Constructs a new {@code NPCForceChat} {@code Object}.
	 * @param context The chat message.
	 */
	public NPCForceChat(String context) {
		super(context);
	}

	@Override
	public void write(IoBuffer buffer) {
		buffer.putString(context);
	}

	@Override
	public int data() {
		return maskData();
	}

	@Override
	public int ordinal() {
		return 6;
	}

	/**
	 * Gets the mask data.
	 * @return The mask data.
	 */
	public static int maskData() {
		return 0x20;
	}
}