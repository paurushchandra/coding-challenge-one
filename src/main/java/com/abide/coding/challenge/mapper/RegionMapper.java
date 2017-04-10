package com.abide.coding.challenge.mapper;

import com.abide.coding.challenge.model.Region;

public class RegionMapper implements Mapper<String, Region> {

	@Override
	public Region map(String line) {
		String[] lineArr = line.split(",");
		Region region = new Region();
		region.setPlace(lineArr[0].trim().toUpperCase());
		region.setRegion(lineArr[1].trim().toUpperCase());
		return region;
	}

}
