package org.areillan.net.producer;

import java.nio.ByteBuffer;

import org.areillan.net.EventProducer;
import org.areillan.net.IoReadEvent;
import org.areillan.net.IoSession;
import org.areillan.net.IoWriteEvent;
import org.areillan.net.event.MSReadEvent;
import org.areillan.net.event.MSWriteEvent;

/**
 * Handles Management server events.
 * @author Emperor
 */
public final class MSEventProducer implements EventProducer {

	@Override
	public IoReadEvent produceReader(IoSession session, ByteBuffer buffer) {
		return new MSReadEvent(session, buffer);
	}

	@Override
	public IoWriteEvent produceWriter(IoSession session, Object context) {
		return new MSWriteEvent(session, context);
	}

}