package com.abide.coding.challenge.handler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.abide.coding.challenge.model.DataStore;
import com.abide.coding.challenge.model.Prescription;
import com.abide.coding.challenge.service.RegionQueryService;

import junit.framework.Assert;

public class PrescriptionByRegionAccumulatorTest {

	@Test
	public void test_accumulation() {
		Mockito.when(regionQueryService.getRegion(Mockito.any(Prescription.class))).thenReturn("North East");
		input.setBnfName("peppermint oil");
		handler.handle(input);
		handler.handle(input);
		input.setBnfName("flucloxacillin");
		handler.handle(input);
		Mockito.when(regionQueryService.getRegion(Mockito.any(Prescription.class))).thenReturn("London");
		input.setBnfName("peppermint oil");
		handler.handle(input);

		ConcurrentHashMap<String, ConcurrentHashMap<String, AtomicInteger>> regionPrescription = dataStore
				.getRegionPrescription();
		Assert.assertEquals(2, regionPrescription.get("North East").get("peppermint oil").get());
		Assert.assertEquals(1, regionPrescription.get("North East").get("flucloxacillin").get());
		Assert.assertEquals(1, regionPrescription.get("London").get("peppermint oil").get());

	}

	@Before
	public void setup() {
		dataStore = new DataStore();
		input = new Prescription();
		regionQueryService = Mockito.mock(RegionQueryService.class);
		handler = new PrescriptionByRegionAccumulator(dataStore, regionQueryService);
	}

	DataStore dataStore;
	Prescription input;
	RegionQueryService regionQueryService;
	PrescriptionByRegionAccumulator handler;

}
