package com.abide.coding.challenge.mapper;

import org.junit.Test;

import com.abide.coding.challenge.model.Prescriber;

import junit.framework.Assert;

public class PrescriberMapperTest {

	@Test
	public void test_mapper() {
		String line = "201202,A81001,THE DENSHAM SURGERY                     ,THE HEALTH CENTRE        ,LAWSON STREET            ,STOCKTON                 ,CLEVELAND                ,TS18 1HU";
		PrescriberMapper mapper = new PrescriberMapper();
		Prescriber prescriber = mapper.map(line);
		Assert.assertEquals(201202, prescriber.getPeriod());
		Assert.assertEquals("A81001", prescriber.getKey());
		Assert.assertEquals("THE DENSHAM SURGERY", prescriber.getName());
		Assert.assertEquals("THE HEALTH CENTRE", prescriber.getAddress1());
		Assert.assertEquals("LAWSON STREET", prescriber.getAddress2());
		Assert.assertEquals("STOCKTON", prescriber.getAddress3());
		Assert.assertEquals("CLEVELAND", prescriber.getAddress4());
		Assert.assertEquals("TS18 1HU", prescriber.getPostCode());
		Assert.assertEquals("CLEVELAND", prescriber.getCity());
	}

	@Test
	public void test_mapper_addLine4_missing() {
		String line = "201202,A81001,THE DENSHAM SURGERY                     ,THE HEALTH CENTRE        ,LAWSON STREET            ,CLEVELAND                 ,                         ,TS18 1HU";
		PrescriberMapper mapper = new PrescriberMapper();
		Prescriber prescriber = mapper.map(line);
		Assert.assertEquals(201202, prescriber.getPeriod());
		Assert.assertEquals("A81001", prescriber.getKey());
		Assert.assertEquals("THE DENSHAM SURGERY", prescriber.getName());
		Assert.assertEquals("THE HEALTH CENTRE", prescriber.getAddress1());
		Assert.assertEquals("LAWSON STREET", prescriber.getAddress2());
		Assert.assertEquals("CLEVELAND", prescriber.getAddress3());
		Assert.assertEquals("", prescriber.getAddress4());
		Assert.assertEquals("TS18 1HU", prescriber.getPostCode());
		Assert.assertEquals("CLEVELAND", prescriber.getCity());
	}

}
