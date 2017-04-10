package com.abide.coding.challenge.model;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DataStore {

	private ConcurrentHashMap<String, Prescriber> locSpecPrescribers; // Que1
	private AtomicInteger prescriptionTotItems; // Que2
	private AtomicInteger prescriptionTotActCost; // Que2
	private ConcurrentHashMap<String, AtomicInteger> highestSpenderPostCode; // Que3
	private ConcurrentHashMap<String, PrescriptionCountPriceHolder> regionPrescriptionAvgPrice; // Que4.a&b
	private ConcurrentHashMap<String, ConcurrentHashMap<String, AtomicInteger>> regionPrescription; // Que5

	public DataStore() {
		super();
		this.locSpecPrescribers = new ConcurrentHashMap<String, Prescriber>();
		this.prescriptionTotItems = new AtomicInteger(0);
		this.prescriptionTotActCost = new AtomicInteger(0);
		this.highestSpenderPostCode = new ConcurrentHashMap<String, AtomicInteger>();
		this.regionPrescriptionAvgPrice = new ConcurrentHashMap<String, PrescriptionCountPriceHolder>();
		this.regionPrescription = new ConcurrentHashMap<String, ConcurrentHashMap<String, AtomicInteger>>();
	}

	public ConcurrentHashMap<String, Prescriber> getLocSpecPrescribers() {
		return locSpecPrescribers;
	}

	public AtomicInteger getPrescriptionTotItems() {
		return prescriptionTotItems;
	}

	public AtomicInteger getPrescriptionTotActCost() {
		return prescriptionTotActCost;
	}

	public ConcurrentHashMap<String, AtomicInteger> getHighestSpenderPostCode() {
		return highestSpenderPostCode;
	}

	public ConcurrentHashMap<String, PrescriptionCountPriceHolder> getRegionPrescriptionAvgPrice() {
		return regionPrescriptionAvgPrice;
	}

	public ConcurrentHashMap<String, ConcurrentHashMap<String, AtomicInteger>> getRegionPrescription() {
		return regionPrescription;
	}

	public static class PrescriptionCountPriceHolder {

		private AtomicInteger prescriptionCount;
		private AtomicInteger prescriptionPrice;

		public PrescriptionCountPriceHolder() {
			super();
			this.prescriptionCount = new AtomicInteger(0);
			this.prescriptionPrice = new AtomicInteger(0);
		}

		public AtomicInteger getPrescriptionCount() {
			return prescriptionCount;
		}

		public AtomicInteger getPrescriptionPrice() {
			return prescriptionPrice;
		}

	}

}
