package com.abide.coding.challenge.predicate;

import com.abide.coding.challenge.model.Prescriber;

public class PrescriberLocationFilter implements Predicate<Prescriber> {

	private String location;

	public PrescriberLocationFilter(String location) {
		super();
		this.location = location;
	}

	@Override
	public boolean accept(Prescriber input) {
		return location.equalsIgnoreCase(input.getCity());
	}

}
