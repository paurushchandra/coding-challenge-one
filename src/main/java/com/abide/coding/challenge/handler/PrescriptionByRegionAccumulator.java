package com.abide.coding.challenge.handler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.abide.coding.challenge.model.DataStore;
import com.abide.coding.challenge.model.Prescription;
import com.abide.coding.challenge.service.RegionQueryService;

public class PrescriptionByRegionAccumulator implements Handler<Prescription> {

	private DataStore dataStore;
	private RegionQueryService regionQueryService;

	public PrescriptionByRegionAccumulator(DataStore dataStore, RegionQueryService regionQueryService) {
		super();
		this.dataStore = dataStore;
		this.regionQueryService = regionQueryService;
	}

	@Override
	public void handle(Prescription input) {
		ConcurrentHashMap<String, ConcurrentHashMap<String, AtomicInteger>> regionPrescription = dataStore
				.getRegionPrescription();
		String region = regionQueryService.getRegion(input);
		ConcurrentHashMap<String, AtomicInteger> regionPrescriptionCountMap = regionPrescription.putIfAbsent(region,
				new ConcurrentHashMap<String, AtomicInteger>());
		if (regionPrescriptionCountMap == null) {
			regionPrescriptionCountMap = regionPrescription.get(region);
		}
		AtomicInteger count = regionPrescriptionCountMap.putIfAbsent(input.getBnfName(), new AtomicInteger(0));
		if (count == null) {
			count = regionPrescriptionCountMap.get(input.getBnfName());
		}
		count.incrementAndGet();
	}

}
