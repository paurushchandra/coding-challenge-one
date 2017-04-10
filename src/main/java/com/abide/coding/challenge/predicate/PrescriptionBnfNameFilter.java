package com.abide.coding.challenge.predicate;

import com.abide.coding.challenge.model.Prescription;

public class PrescriptionBnfNameFilter implements Predicate<Prescription> {

	private String bnfName;

	public PrescriptionBnfNameFilter(String bnfName) {
		super();
		this.bnfName = bnfName;
	}

	@Override
	public boolean accept(Prescription input) {
		return bnfName.equalsIgnoreCase(input.getBnfName());
	}

}
