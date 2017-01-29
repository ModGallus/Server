package org.areillan.net.producer;

import java.nio.ByteBuffer;

import org.areillan.net.EventProducer;
import org.areillan.net.IoReadEvent;
import org.areillan.net.IoSession;
import org.areillan.net.IoWriteEvent;
import org.areillan.net.event.LoginReadEvent;
import org.areillan.net.event.LoginWriteEvent;

/**
 * Produces login I/O events.
 * @author Emperor
 */
public final class LoginEventProducer implements EventProducer {

	@Override
	public IoReadEvent produceReader(IoSession session, ByteBuffer buffer) {
		return new LoginReadEvent(session, buffer);
	}

	@Override
	public IoWriteEvent produceWriter(IoSession session, Object context) {
		return new LoginWriteEvent(session, context);
	}

}