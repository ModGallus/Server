package org.areillan.net.producer;

import java.nio.ByteBuffer;

import org.areillan.net.EventProducer;
import org.areillan.net.IoReadEvent;
import org.areillan.net.IoSession;
import org.areillan.net.IoWriteEvent;
import org.areillan.net.event.HSReadEvent;
import org.areillan.net.event.HSWriteEvent;

/**
 * Produces I/O events for the handshake protocol.
 * @author Emperor
 */
public final class HSEventProducer implements EventProducer {

	@Override
	public IoReadEvent produceReader(IoSession session, ByteBuffer buffer) {
		return new HSReadEvent(session, buffer);
	}

	@Override
	public IoWriteEvent produceWriter(IoSession session, Object context) {
		return new HSWriteEvent(session, context);
	}

}