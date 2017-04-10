package com.abide.coding.challenge.service;

import java.util.Map;

import com.abide.coding.challenge.model.Prescriber;
import com.abide.coding.challenge.model.Prescription;
import com.abide.coding.challenge.model.Region;

public class RegionQueryService {

	private static final String UNKNOWN_REGION = "UNKNOWN";
	private Map<String, Region> regionCache;
	private Map<String, Prescriber> prescriberCache;

	public RegionQueryService(Map<String, Region> regionCache, Map<String, Prescriber> prescriberCache) {
		super();
		this.regionCache = regionCache;
		this.prescriberCache = prescriberCache;
	}

	public String getRegion(Prescription prescription) {
		Prescriber prescriber = prescriberCache.get(prescription.getPrescriberKey());
		if (prescriber == null) {
			return UNKNOWN_REGION;
		}
		Region region = regionCache.get(prescriber.getCity().toUpperCase());
		if (region != null) {
			return region.getRegion();
		}
		return UNKNOWN_REGION;
	}

}
