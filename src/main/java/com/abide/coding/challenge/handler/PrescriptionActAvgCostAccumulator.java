package com.abide.coding.challenge.handler;

import com.abide.coding.challenge.model.DataStore;
import com.abide.coding.challenge.model.Prescription;
import com.abide.coding.challenge.predicate.Predicate;

public class PrescriptionActAvgCostAccumulator implements Handler<Prescription> {

	private DataStore dataStore;
	private Predicate<Prescription> condition;

	public PrescriptionActAvgCostAccumulator(DataStore dataStore, Predicate<Prescription> condition) {
		super();
		this.dataStore = dataStore;
		this.condition = condition;
	}

	@Override
	public void handle(Prescription input) {
		if (condition.accept(input)) {
			dataStore.getPrescriptionTotItems().addAndGet(input.getItems());
			dataStore.getPrescriptionTotActCost().addAndGet(input.getActualCost().intValue());
		}
	}

}
