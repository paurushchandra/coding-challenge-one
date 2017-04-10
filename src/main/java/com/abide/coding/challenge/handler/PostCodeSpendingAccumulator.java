package com.abide.coding.challenge.handler;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.abide.coding.challenge.model.DataStore;
import com.abide.coding.challenge.model.Prescriber;
import com.abide.coding.challenge.model.Prescription;

public class PostCodeSpendingAccumulator implements Handler<Prescription> {

	private DataStore dataStore;
	private Map<String, Prescriber> prescriberCache;

	public PostCodeSpendingAccumulator(DataStore dataStore, Map<String, Prescriber> prescriberCache) {
		super();
		this.dataStore = dataStore;
		this.prescriberCache = prescriberCache;
	}

	@Override
	public void handle(Prescription input) {
		Prescriber prescriber = prescriberCache.get(input.getPrescriberKey());
		if (prescriber == null) {
			return;
		}
		AtomicInteger total = dataStore.getHighestSpenderPostCode().putIfAbsent(prescriber.getPostCode(),
				new AtomicInteger(0));
		if (total == null) {
			total = dataStore.getHighestSpenderPostCode().get(prescriber.getPostCode());
		}
		total.getAndAdd(input.getActualCost().intValue());
	}

}
