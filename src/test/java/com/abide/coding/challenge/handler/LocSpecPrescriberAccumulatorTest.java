package com.abide.coding.challenge.handler;

import org.junit.Before;
import org.junit.Test;

import com.abide.coding.challenge.model.DataStore;
import com.abide.coding.challenge.model.Prescriber;
import com.abide.coding.challenge.predicate.Predicate;
import com.abide.coding.challenge.predicate.PrescriberLocationFilter;

import junit.framework.Assert;

public class LocSpecPrescriberAccumulatorTest {

	@Test
	public void test_accumulation() {
		input.setAddress4("london");
		input.setKey("ABC");
		handler.handle(input);
		Assert.assertTrue(dataStore.getLocSpecPrescribers().containsKey("ABC"));
	}

	@Test
	public void test_accumulation_negative() {
		input.setAddress4("manchaster");
		input.setKey("ABC");
		handler.handle(input);
		Assert.assertFalse(dataStore.getLocSpecPrescribers().containsKey("ABC"));
	}

	@Before
	public void setup() {
		dataStore = new DataStore();
		condition = new PrescriberLocationFilter("london");
		handler = new LocSpecPrescriberAccumulator(dataStore, condition);
		input = new Prescriber();
	}

	LocSpecPrescriberAccumulator handler;
	DataStore dataStore;
	Predicate<Prescriber> condition;
	Prescriber input;

}
