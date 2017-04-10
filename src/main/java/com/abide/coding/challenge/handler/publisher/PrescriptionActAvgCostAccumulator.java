package com.abide.coding.challenge.handler.publisher;

import com.abide.coding.challenge.handler.Handler;
import com.abide.coding.challenge.model.DataStore;
import com.abide.coding.challenge.model.Prescription;
import com.abide.coding.challenge.predicate.Predicate;

public class PrescriptionActAvgCostAccumulator implements Handler<Prescription>, Publisher {

	private DataStore dataStore;
	private Predicate<Prescription> condition;
	private int prescriptionTotItems;
	private int prescriptionTotActCost;

	public PrescriptionActAvgCostAccumulator(DataStore dataStore, Predicate<Prescription> condition) {
		super();
		this.dataStore = dataStore;
		this.condition = condition;
		this.prescriptionTotItems = 0;
		this.prescriptionTotActCost = 0;
	}

	@Override
	public void handle(Prescription input) {
		if (condition.accept(input)) {
			prescriptionTotItems += input.getItems();
			prescriptionTotActCost += input.getActualCost().intValue();
		}
	}

	@Override
	public void publish() {
		dataStore.getPrescriptionTotItems().addAndGet(prescriptionTotItems);
		dataStore.getPrescriptionTotActCost().addAndGet(prescriptionTotActCost);
	}

}