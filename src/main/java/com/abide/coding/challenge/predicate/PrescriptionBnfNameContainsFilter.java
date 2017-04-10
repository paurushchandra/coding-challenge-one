package com.abide.coding.challenge.predicate;

import com.abide.coding.challenge.model.Prescription;

public class PrescriptionBnfNameContainsFilter implements Predicate<Prescription> {

	private String nameContains;
	private String excludeName;

	public PrescriptionBnfNameContainsFilter(String nameContains, String excludeName) {
		super();
		this.nameContains = nameContains;
		this.excludeName = excludeName;
	}

	@Override
	public boolean accept(Prescription input) {
		return input.getBnfName().contains(nameContains) && !input.getBnfName().contains(excludeName);
	}

}
