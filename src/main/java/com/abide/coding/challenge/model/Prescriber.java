package com.abide.coding.challenge.model;

public class Prescriber {

	public Prescriber() {
		super();
	}

	private int period;
	private String key;
	private String name;
	private String address1;
	private String address2;
	private String address3;
	private String address4;
	private String postCode;

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getAddress4() {
		return address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCity() {
		if (!this.address4.isEmpty()) {
			return this.address4;
		}
		return this.address3;
	}

	@Override
	public String toString() {
		return "Prescriber [key=" + key + ", name=" + name + ", address1=" + address1 + ", address2=" + address2
				+ ", address3=" + address3 + ", address4=" + address4 + ", postCode=" + postCode + "]";
	}

}
