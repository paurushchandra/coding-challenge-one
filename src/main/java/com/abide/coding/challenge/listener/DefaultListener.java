package com.abide.coding.challenge.listener;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.Logger;

public class DefaultListener<T> implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(DefaultListener.class);
	private BlockingQueue<T> queue;
	private AtomicBoolean abortListener;
	private MessageListener<T> messagelistener;

	public DefaultListener(BlockingQueue<T> queue, MessageListener<T> messageListener) {
		super();
		this.queue = queue;
		this.abortListener = new AtomicBoolean(false);
		this.messagelistener = messageListener;
	}

	private void listen() {
		LOGGER.info("starting listener");
		while (!abortListener.get()) {
			try {
				T message = queue.take();
				messagelistener.onMessage(message);
			} catch (InterruptedException e) {
				abortListener.set(Boolean.TRUE);
			}
		}
		LOGGER.info("shutting down listener");
	}

	public void stop() {
		abortListener.set(Boolean.TRUE);
		Thread.currentThread().interrupt();
	}

	@Override
	public void run() {
		listen();
	}

}
