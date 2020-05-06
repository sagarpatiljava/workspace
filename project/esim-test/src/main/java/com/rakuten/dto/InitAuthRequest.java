package com.rakuten.dto;

public class InitAuthRequest {

	private String euiccChallenge;
	private String euiccInfo1;
	private String smdpAddress;
	public String getEuiccChallenge() {
		return euiccChallenge;
	}
	public void setEuiccChallenge(String euiccChallenge) {
		this.euiccChallenge = euiccChallenge;
	}
	public String getEuiccInfo1() {
		return euiccInfo1;
	}
	public void setEuiccInfo1(String euiccInfo1) {
		this.euiccInfo1 = euiccInfo1;
	}
	public String getSmdpAddress() {
		return smdpAddress;
	}
	public void setSmdpAddress(String smdpAddress) {
		this.smdpAddress = smdpAddress;
	}
	@Override
	public String toString() {
		return "InitAuthRequest [euiccChallenge=" + euiccChallenge + ", euiccInfo1=" + euiccInfo1 + ", smdpAddress="
				+ smdpAddress + "]";
	}
	
	
}
