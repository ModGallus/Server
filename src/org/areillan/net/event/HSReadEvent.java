package org.areillan.net.event;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.areillan.net.Constants;
import org.areillan.net.IoReadEvent;
import org.areillan.net.IoSession;
import org.areillan.net.lobby.WorldList;
import org.areillan.net.registry.AccountRegister;
import org.areillan.tools.RandomFunction;

/**
 * Handles handshake read events.
 * @author Emperor
 */
public final class HSReadEvent extends IoReadEvent {

	// debug
	static Map<String, Integer> count = new HashMap<>();

	/**
	 * Constructs a new {@code HSReadEvent}.
	 * @param session The session.
	 * @param buffer The buffer.
	 */
	public HSReadEvent(IoSession session, ByteBuffer buffer) {
		super(session, buffer);
	}

	@Override
	public void read(IoSession session, ByteBuffer buffer) {
		Integer amount = count.get(session.getAddress());
		if (amount == null) {
			amount = 0;
		}
		count.put(session.getAddress(), amount + 1);
		int opcode = buffer.get() & 0xFF;
		switch (opcode) {
		case 14:
			session.setNameHash(buffer.get() & 0xFF);
			session.setServerKey(RandomFunction.RANDOM.nextLong());
			session.write(true);
			break;
		case 15:
			int revision = buffer.getInt();
			if (revision != Constants.REVISION) {
				session.disconnect();
				break;
			}
			session.write(false);
			break;
		case 147:
		case 186:
		case 36:
			AccountRegister.read(session, opcode, buffer);
			break;
		case 255: // World list
			int updateStamp = buffer.getInt();
			WorldList.sendUpdate(session, updateStamp);
			break;
		default:
			session.disconnect();
			break;
		}
	}

}