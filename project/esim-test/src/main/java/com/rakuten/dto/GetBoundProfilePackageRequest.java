package com.rakuten.dto;

public class GetBoundProfilePackageRequest {
	
	private String transactionID;
	private String prepareDownloadResponse;
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public String getPrepareDownloadResponse() {
		return prepareDownloadResponse;
	}
	public void setPrepareDownloadResponse(String prepareDownloadResponse) {
		this.prepareDownloadResponse = prepareDownloadResponse;
	}
	@Override
	public String toString() {
		return "GetBoundProfilePackageRequest [transactionID=" + transactionID + ", prepareDownloadResponse="
				+ prepareDownloadResponse + "]";
	}

}
