package com.abide.coding.challenge.handler;

import org.junit.Before;
import org.junit.Test;

import com.abide.coding.challenge.model.DataStore;
import com.abide.coding.challenge.model.Prescription;
import com.abide.coding.challenge.predicate.Predicate;
import com.abide.coding.challenge.predicate.PrescriptionBnfNameFilter;

import junit.framework.Assert;

public class PrescriptionActAvgCostAccumulatorTest {

	@Test
	public void test_accumulation() {
		input.setBnfName("some other oil");
		input.setItems(1);
		input.setActualCost(2d);
		handler.handle(input);
		Assert.assertEquals(0, dataStore.getPrescriptionTotItems().get());
		Assert.assertEquals(0, dataStore.getPrescriptionTotActCost().get());

		input.setBnfName("peppermint oil");
		input.setItems(1);
		input.setActualCost(2d);
		handler.handle(input);
		Assert.assertEquals(1, dataStore.getPrescriptionTotItems().get());
		Assert.assertEquals(2, dataStore.getPrescriptionTotActCost().get());

		input.setItems(9);
		input.setActualCost(18d);
		handler.handle(input);
		Assert.assertEquals(10, dataStore.getPrescriptionTotItems().get());
		Assert.assertEquals(20, dataStore.getPrescriptionTotActCost().get());
	}

	@Before
	public void setup() {
		dataStore = new DataStore();
		condition = new PrescriptionBnfNameFilter("peppermint oil");
		input = new Prescription();
		handler = new PrescriptionActAvgCostAccumulator(dataStore, condition);
	}

	private DataStore dataStore;
	private Predicate<Prescription> condition;
	private Prescription input;
	PrescriptionActAvgCostAccumulator handler;
}
