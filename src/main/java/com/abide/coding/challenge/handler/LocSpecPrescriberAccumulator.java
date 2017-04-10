package com.abide.coding.challenge.handler;

import com.abide.coding.challenge.model.DataStore;
import com.abide.coding.challenge.model.Prescriber;
import com.abide.coding.challenge.predicate.Predicate;

public class LocSpecPrescriberAccumulator implements Handler<Prescriber> {

	private DataStore dataStore;
	private Predicate<Prescriber> condition;

	public LocSpecPrescriberAccumulator(DataStore dataStore, Predicate<Prescriber> condition) {
		super();
		this.dataStore = dataStore;
		this.condition = condition;
	}

	@Override
	public void handle(Prescriber input) {
		if (condition.accept(input)) {
			dataStore.getLocSpecPrescribers().putIfAbsent(input.getKey(), input);
		}
	}

}
