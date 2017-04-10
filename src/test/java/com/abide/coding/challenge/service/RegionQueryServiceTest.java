package com.abide.coding.challenge.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.abide.coding.challenge.model.Prescriber;
import com.abide.coding.challenge.model.Prescription;
import com.abide.coding.challenge.model.Region;

import junit.framework.Assert;

public class RegionQueryServiceTest {

	Map<String, Region> regionCache = new HashMap<String, Region>();
	Map<String, Prescriber> prescriberCache = new HashMap<String, Prescriber>();
	RegionQueryService service = new RegionQueryService(regionCache, prescriberCache);
	Prescription prescription = new Prescription();

	@Test
	public void test_region_mapping_found() {
		prescription.setPrescriberKey("ABC");
		Assert.assertEquals("NORTH EAST", service.getRegion(prescription));
	}

	@Test
	public void test_region_mapping_not_found() {
		prescription.setPrescriberKey("XYZ");
		Assert.assertEquals("UNKNOWN", service.getRegion(prescription));
	}

	@Before
	public void setup() {
		Region region = new Region();
		region.setPlace("CLEVELAND");
		region.setRegion("NORTH EAST");
		regionCache.put(region.getPlace(), region);
		Prescriber prescriber = new Prescriber();
		prescriber.setKey("ABC");
		prescriber.setAddress4("CLEVELAND");
		prescriberCache.put("ABC", prescriber);
	}

}
