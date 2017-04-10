package com.abide.coding.challenge.handler.publisher;

import java.util.HashMap;
import java.util.Map;

import com.abide.coding.challenge.handler.Handler;
import com.abide.coding.challenge.model.DataStore;
import com.abide.coding.challenge.model.Prescriber;
import com.abide.coding.challenge.model.Prescription;

public class PostCodeSpendingAccumulator implements Handler<Prescription>, Publisher {

	private DataStore dataStore;
	private Map<String, Prescriber> prescriberCache;
	private Map<String, Integer> highestSpenderByPostCode;

	public PostCodeSpendingAccumulator(DataStore dataStore, Map<String, Prescriber> prescriberCache) {
		super();
		this.dataStore = dataStore;
		this.prescriberCache = prescriberCache;
		this.highestSpenderByPostCode = new HashMap<String, Integer>();
	}

	@Override
	public void handle(Prescription input) {
		Prescriber prescriber = prescriberCache.get(input.getPrescriberKey());
		if (prescriber == null) {
			return;
		}
		int total = highestSpenderByPostCode.get(prescriber.getPostCode());
		highestSpenderByPostCode.put(prescriber.getPostCode(), total + input.getActualCost().intValue());
	}

	@Override
	public void publish() {
		// dataStore.getHighestSpenderPostCode().putAll(highestSpenderByPostCode);
	}

}
