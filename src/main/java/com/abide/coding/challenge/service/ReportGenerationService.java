package com.abide.coding.challenge.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.abide.coding.challenge.model.DataStore;
import com.abide.coding.challenge.model.DataStore.PrescriptionCountPriceHolder;

public class ReportGenerationService {

	private static final String NEW_LINE = "\n";
	private static final String TAB = "\t";
	private DataStore dataStore;

	public ReportGenerationService(DataStore dataStore) {
		super();
		this.dataStore = dataStore;
	}

	public String generateReport() {
		StringBuilder sb = new StringBuilder(NEW_LINE).append(" ---------- Start of Report -------------")
				.append(NEW_LINE);
		sb.append(NEW_LINE).append("Que 1.").append(TAB).append("Count of London Practioners").append(TAB)
				.append(dataStore.getLocSpecPrescribers().size()).append(NEW_LINE);
		sb.append(NEW_LINE).append("Que 2.").append(TAB).append("Peppermint oil average cost").append(TAB)
				.append((dataStore.getPrescriptionTotActCost().get() / dataStore.getPrescriptionTotItems().get()))
				.append(NEW_LINE);
		sb.append(NEW_LINE).append("Que 3.").append(TAB).append("Top 5 spending by passcode").append(NEW_LINE);
		List<HighestSpendHolder> holders = new ArrayList<HighestSpendHolder>();
		for (Entry<String, AtomicInteger> entry : dataStore.getHighestSpenderPostCode().entrySet()) {
			holders.add(this.new HighestSpendHolder(entry.getKey(), entry.getValue().get()));
		}
		Collections.sort(holders);
		for (int i = 1; i <= 5; i++) {
			sb.append(holders.get(i).toString()).append(NEW_LINE);
		}
		sb.append(NEW_LINE).append("Que 4a&b.").append(TAB)
				.append("Flucloxacillin avg price by region and national mean").append(NEW_LINE);
		Map<String, PrescriptionCountPriceHolder> regionsData = dataStore.getRegionPrescriptionAvgPrice();

		List<RegionAvgPriceHolder> regionAvgPriceHolders = new ArrayList<RegionAvgPriceHolder>();
		double totalAvg = 0;
		for (Entry<String, PrescriptionCountPriceHolder> regionData : regionsData.entrySet()) {
			PrescriptionCountPriceHolder holder = regionData.getValue();
			RegionAvgPriceHolder regionAvgPriceHolder = this.new RegionAvgPriceHolder(regionData.getKey(),
					holder.getPrescriptionPrice().get() / holder.getPrescriptionCount().get());
			regionAvgPriceHolders.add(regionAvgPriceHolder);
			totalAvg += regionAvgPriceHolder.avgPrice;
		}

		double nationalMean = totalAvg / regionAvgPriceHolders.size();
		for (RegionAvgPriceHolder holder : regionAvgPriceHolders) {
			holder.nationalMean = nationalMean;
			holder.deviation = nationalMean - holder.avgPrice;
			sb.append(holder.toString()).append(NEW_LINE);
		}

		sb.append(NEW_LINE).append("Que 5.").append(TAB).append("Most commonly prescribed drugs by region")
				.append(NEW_LINE);
		for (Entry<String, ConcurrentHashMap<String, AtomicInteger>> regionPrescription : dataStore
				.getRegionPrescription().entrySet()) {
			String region = regionPrescription.getKey();
			ConcurrentHashMap<String, AtomicInteger> prescriptions = regionPrescription.getValue();
			String mostPrescribedDrug = null;
			int maxPrescribedCount = 0;
			for (Entry<String, AtomicInteger> prescription : prescriptions.entrySet()) {
				if (prescription.getValue().get() > maxPrescribedCount) {
					maxPrescribedCount = prescription.getValue().get();
					mostPrescribedDrug = prescription.getKey();
				}
			}
			sb.append("[ region=").append(region).append(" , mostPrescribedDrug=").append(mostPrescribedDrug)
					.append(" , count=").append(maxPrescribedCount).append(NEW_LINE);
		}

		return sb.append("----------- End of Report ------------").toString();
	}

	class HighestSpendHolder implements Comparable<HighestSpendHolder> {
		String postCode;
		Integer cost;

		public HighestSpendHolder(String postCode, Integer cost) {
			super();
			this.postCode = postCode;
			this.cost = cost;
		}

		@Override
		public int compareTo(HighestSpendHolder o) {
			return o.cost.compareTo(this.cost);
		}

		@Override
		public String toString() {
			return "HighestSpendHolder [postCode=" + postCode + ", cost=" + cost + "]";
		}

	}

	class RegionAvgPriceHolder {
		String region;
		double avgPrice;
		double nationalMean;
		double deviation;

		public RegionAvgPriceHolder(String region, double avgPrice) {
			super();
			this.region = region;
			this.avgPrice = avgPrice;
		}

		@Override
		public String toString() {
			return "RegionAvgPriceHolder [region=" + region + ", avgPrice=" + avgPrice + ", nationalMean="
					+ nationalMean + ", deviation=" + deviation + "]";
		}

	}

}
