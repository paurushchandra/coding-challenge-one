package com.abide.coding.challenge.mapper;

import com.abide.coding.challenge.model.Prescriber;

public class PrescriberMapper implements Mapper<String, Prescriber> {

	@Override
	public Prescriber map(String line) {
		String[] lineArr = line.split(",");
		if (lineArr.length < 8) {
			throw new IllegalArgumentException("can not populate object. Missing fields");
		}
		Prescriber prescriber = new Prescriber();
		prescriber.setPeriod(Integer.parseInt(lineArr[0].trim()));
		prescriber.setKey(lineArr[1].trim());
		prescriber.setName(lineArr[2].trim());
		prescriber.setAddress1(lineArr[3].trim());
		prescriber.setAddress2(lineArr[4].trim());
		prescriber.setAddress3(lineArr[5].trim());
		prescriber.setAddress4(lineArr[6].trim());
		prescriber.setPostCode(lineArr[7].trim());
		return prescriber;
	}

}
