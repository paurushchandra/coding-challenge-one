package com.abide.coding.challenge.handler;

import java.util.concurrent.BlockingQueue;

import com.abide.coding.challenge.model.Prescription;

public class PrescriptionQueueSender implements Handler<Prescription> {

	private BlockingQueue<Prescription> queue;

	public PrescriptionQueueSender(BlockingQueue<Prescription> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void handle(Prescription input) {
		try {
			queue.put(input);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
