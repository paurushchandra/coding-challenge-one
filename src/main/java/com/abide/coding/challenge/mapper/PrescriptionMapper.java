package com.abide.coding.challenge.mapper;

import com.abide.coding.challenge.model.Prescription;

public class PrescriptionMapper implements Mapper<String, Prescription> {

	@Override
	public Prescription map(String line) {
		String[] lineArr = line.split(",");
		if (lineArr.length < 9) {
			throw new IllegalArgumentException("can not populate object. Missing fields");
		}

		Prescription prescription = new Prescription();
		prescription.setSha(lineArr[0].trim());
		prescription.setPct(lineArr[1].trim());
		prescription.setPrescriberKey(lineArr[2].trim());
		prescription.setBnfCode(lineArr[3].trim());
		prescription.setBnfName(lineArr[4].trim());
		prescription.setItems(Integer.parseInt(lineArr[5].trim()));
		prescription.setNic(Double.parseDouble(lineArr[6].trim()));
		prescription.setActualCost(Double.parseDouble(lineArr[7].trim()));
		prescription.setPeriod(Integer.parseInt(lineArr[8].trim()));

		return prescription;
	}

}
