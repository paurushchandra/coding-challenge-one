package com.abide.coding.challenge.model;

public class Prescription {

	private String sha;
	private String pct;
	private String prescriberKey;
	private String bnfCode;
	private String bnfName;
	private int items;
	private Double nic;
	private Double actualCost;
	private int period;

	public String getSha() {
		return sha;
	}

	public void setSha(String sha) {
		this.sha = sha;
	}

	public String getPct() {
		return pct;
	}

	public void setPct(String pct) {
		this.pct = pct;
	}

	public String getPrescriberKey() {
		return prescriberKey;
	}

	public void setPrescriberKey(String prescriberKey) {
		this.prescriberKey = prescriberKey;
	}

	public String getBnfCode() {
		return bnfCode;
	}

	public void setBnfCode(String bnfCode) {
		this.bnfCode = bnfCode;
	}

	public String getBnfName() {
		return bnfName;
	}

	public void setBnfName(String bnfName) {
		this.bnfName = bnfName;
	}

	public int getItems() {
		return items;
	}

	public void setItems(int items) {
		this.items = items;
	}

	public Double getNic() {
		return nic;
	}

	public void setNic(Double nic) {
		this.nic = nic;
	}

	public Double getActualCost() {
		return actualCost;
	}

	public void setActualCost(Double actualCost) {
		this.actualCost = actualCost;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	@Override
	public String toString() {
		return "Prescription [sha=" + sha + ", pct=" + pct + ", prescriberKey=" + prescriberKey + ", bnfCode=" + bnfCode
				+ ", bnfName=" + bnfName + ", items=" + items + ", nic=" + nic + ", actualCost=" + actualCost
				+ ", period=" + period + "]";
	}

}
