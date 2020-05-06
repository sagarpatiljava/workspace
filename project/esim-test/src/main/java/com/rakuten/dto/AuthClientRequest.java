package com.rakuten.dto;

public class AuthClientRequest {

	public AuthClientRequest(String transactionId, String authenticateServerResponse) {
		super();
		this.transactionId = transactionId;
		this.authenticateServerResponse = authenticateServerResponse;
	}

	private String transactionId;
	private String authenticateServerResponse;
	
	public String getTransactionID() {
		return transactionId;
	}
	
	public String getAuthenticateServerResponse() {
		return authenticateServerResponse;
	}
	
	public void setAuthenticateServerResponse(String authenticateServerResponse) {
		this.authenticateServerResponse = authenticateServerResponse;
	}
	
	public void setTransactionID(String transactionID) {
		this.transactionId = transactionID;
	}

	@Override
	public String toString() {
		return "AuthClientRequest [transactionId=" + transactionId + ", authenticateServerResponse="
				+ authenticateServerResponse + "]";
	}
	
	
}
