package com.abide.coding.challenge.handler;

import java.util.Map;

import com.abide.coding.challenge.model.Prescriber;

public class PrescriberCacheBuilder implements Handler<Prescriber> {

	private Map<String, Prescriber> cache;

	public PrescriberCacheBuilder(Map<String, Prescriber> cache) {
		super();
		this.cache = cache;
	}

	@Override
	public void handle(Prescriber prescriber) {
		cache.put(prescriber.getKey(), prescriber);
	}

}
