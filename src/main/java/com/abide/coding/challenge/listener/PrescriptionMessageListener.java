package com.abide.coding.challenge.listener;

import java.util.concurrent.atomic.AtomicInteger;

import com.abide.coding.challenge.handler.Handler;
import com.abide.coding.challenge.model.Prescription;

public class PrescriptionMessageListener implements MessageListener<Prescription> {

	private Handler<Prescription> handler;
	private AtomicInteger prescriptionMessageCounter;

	public PrescriptionMessageListener(Handler<Prescription> handler, AtomicInteger prescriptionMessageCounter) {
		super();
		this.handler = handler;
		this.prescriptionMessageCounter = prescriptionMessageCounter;
	}

	@Override
	public void onMessage(Prescription message) {
		handler.handle(message);
		prescriptionMessageCounter.incrementAndGet();
	}

}
