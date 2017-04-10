package com.abide.coding.challenge.handler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.abide.coding.challenge.model.DataStore;
import com.abide.coding.challenge.model.Prescription;
import com.abide.coding.challenge.predicate.Predicate;
import com.abide.coding.challenge.service.RegionQueryService;

import junit.framework.Assert;

public class PrescriptionAvgPriceAccumulatorTest {

	@Test
	public void accumulation() {
		Mockito.when(condition.accept(Mockito.any(Prescription.class))).thenReturn(Boolean.TRUE);
		Mockito.when(regionQueryService.getRegion(Mockito.any(Prescription.class))).thenReturn("North East");
		input.setActualCost(10d);
		handler.handle(input);
		DataStore.PrescriptionCountPriceHolder holder = dataStore.getRegionPrescriptionAvgPrice().get("North East");
		Assert.assertEquals(1, holder.getPrescriptionCount().get());
		Assert.assertEquals(10, holder.getPrescriptionPrice().get());

		input.setActualCost(20d);
		handler.handle(input);
		Assert.assertEquals(2, holder.getPrescriptionCount().get());
		Assert.assertEquals(30, holder.getPrescriptionPrice().get());
	}

	@Before
	public void setup() {
		dataStore = new DataStore();
		condition = Mockito.mock(Predicate.class);
		input = new Prescription();
		regionQueryService = Mockito.mock(RegionQueryService.class);
		handler = new PrescriptionAvgPriceAccumulator(dataStore, condition, regionQueryService);
	}

	private DataStore dataStore;
	private Predicate<Prescription> condition;
	private Prescription input;
	RegionQueryService regionQueryService;
	PrescriptionAvgPriceAccumulator handler;

}
