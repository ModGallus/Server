package org.areillan.net.producer;

import java.nio.ByteBuffer;

import org.areillan.net.EventProducer;
import org.areillan.net.IoReadEvent;
import org.areillan.net.IoSession;
import org.areillan.net.IoWriteEvent;
import org.areillan.net.event.JS5ReadEvent;
import org.areillan.net.event.JS5WriteEvent;

/**
 * Produces JS-5 I/O events.
 * @author Tyler
 * @author Emperor
 */
public class JS5EventProducer implements EventProducer {

	@Override
	public IoReadEvent produceReader(IoSession session, ByteBuffer buffer) {
		return new JS5ReadEvent(session, buffer);
	}

	@Override
	public IoWriteEvent produceWriter(IoSession session, Object context) {
		return new JS5WriteEvent(session, context);
	}

}
