package com.abide.coding.challenge.predicate;

import org.junit.Assert;
import org.junit.Test;

import com.abide.coding.challenge.model.Prescription;

public class PrescriptionBnfNameContainsFilterTest {

	String nameContains = "Flucloxacillin";
	String excludeName = "Co-Fluampicil";
	PrescriptionBnfNameContainsFilter condition = new PrescriptionBnfNameContainsFilter(nameContains, excludeName);

	@Test
	public void test_matches_both() {
		Prescription prescription = new Prescription();
		prescription.setBnfName("Flucloxacillin Sodium");
		Assert.assertTrue(condition.accept(prescription));
	}

	@Test
	public void test_matches_first_clause_only() {
		Prescription prescription = new Prescription();
		prescription.setBnfName("Co-Fluampicil(Flucloxacillin/Ampicillin)");
		Assert.assertFalse(condition.accept(prescription));
	}

	@Test
	public void test_complete_mismatch() {
		Prescription prescription = new Prescription();
		prescription.setBnfName("Oxytetracycline");
		Assert.assertFalse(condition.accept(prescription));
	}

}
