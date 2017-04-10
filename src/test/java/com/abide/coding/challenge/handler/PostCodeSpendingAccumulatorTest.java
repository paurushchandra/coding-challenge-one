package com.abide.coding.challenge.handler;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.abide.coding.challenge.model.DataStore;
import com.abide.coding.challenge.model.Prescriber;
import com.abide.coding.challenge.model.Prescription;

import junit.framework.Assert;

public class PostCodeSpendingAccumulatorTest {

	@Test
	public void test_accumulation() {
		input.setActualCost(10d);
		input.setPrescriberKey("ABC");
		Prescriber prescriber = new Prescriber();
		prescriber.setPostCode("TS24 7PW");
		prescriberCache.put("ABC", prescriber);
		handler.handle(input);
		Assert.assertEquals(dataStore.getHighestSpenderPostCode().get("TS24 7PW").get(), 10);
		input.setActualCost(30d);
		handler.handle(input);
		Assert.assertEquals(dataStore.getHighestSpenderPostCode().get("TS24 7PW").get(), 40);
	}

	@Before
	public void setup() {
		dataStore = new DataStore();
		prescriberCache = new HashMap<String, Prescriber>();
		handler = new PostCodeSpendingAccumulator(dataStore, prescriberCache);
		input = new Prescription();
	}

	PostCodeSpendingAccumulator handler;
	Map<String, Prescriber> prescriberCache;
	DataStore dataStore;
	Prescription input;

}
