package org.areillan.net.producer;

import java.nio.ByteBuffer;

import org.areillan.net.EventProducer;
import org.areillan.net.IoReadEvent;
import org.areillan.net.IoSession;
import org.areillan.net.IoWriteEvent;
import org.areillan.net.event.RegistryReadEvent;
import org.areillan.net.event.RegistryWriteEvent;

/**
 * Handles world server registry.
 * @author Emperor
 */
public final class RegistryEventProducer implements EventProducer {

	@Override
	public IoReadEvent produceReader(IoSession session, ByteBuffer buffer) {
		return new RegistryReadEvent(session, buffer);
	}

	@Override
	public IoWriteEvent produceWriter(IoSession session, Object context) {
		return new RegistryWriteEvent(session, context);
	}

}