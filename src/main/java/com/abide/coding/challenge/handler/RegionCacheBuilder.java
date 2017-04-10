package com.abide.coding.challenge.handler;

import java.util.Map;

import com.abide.coding.challenge.model.Region;

public class RegionCacheBuilder implements Handler<Region> {

	private Map<String, Region> cache;

	public RegionCacheBuilder(Map<String, Region> cache) {
		super();
		this.cache = cache;
	}

	@Override
	public void handle(Region input) {
		cache.put(input.getPlace(), input);
	}

}
