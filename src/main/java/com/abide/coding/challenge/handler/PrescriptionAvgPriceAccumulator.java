package com.abide.coding.challenge.handler;

import java.util.concurrent.ConcurrentHashMap;

import com.abide.coding.challenge.model.DataStore;
import com.abide.coding.challenge.model.DataStore.PrescriptionCountPriceHolder;
import com.abide.coding.challenge.model.Prescription;
import com.abide.coding.challenge.predicate.Predicate;
import com.abide.coding.challenge.service.RegionQueryService;

public class PrescriptionAvgPriceAccumulator implements Handler<Prescription> {

	private DataStore dataStore;
	private Predicate<Prescription> condition;
	private RegionQueryService regionQueryService;

	public PrescriptionAvgPriceAccumulator(DataStore dataStore, Predicate<Prescription> condition,
			RegionQueryService regionQueryService) {
		super();
		this.dataStore = dataStore;
		this.condition = condition;
		this.regionQueryService = regionQueryService;
	}

	@Override
	public void handle(Prescription input) {
		if (condition.accept(input)) {
			ConcurrentHashMap<String, PrescriptionCountPriceHolder> regionPrescriptionAvgPrice = dataStore
					.getRegionPrescriptionAvgPrice();
			String region = regionQueryService.getRegion(input);
			PrescriptionCountPriceHolder holder = regionPrescriptionAvgPrice.putIfAbsent(region,
					new PrescriptionCountPriceHolder());
			if (holder == null) {
				holder = regionPrescriptionAvgPrice.get(region);
			}
			holder.getPrescriptionCount().incrementAndGet();
			holder.getPrescriptionPrice().addAndGet(input.getActualCost().intValue());
		}
	}

}
