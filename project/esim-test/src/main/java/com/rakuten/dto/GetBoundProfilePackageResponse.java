package com.rakuten.dto;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


public class GetBoundProfilePackageResponse {

	
	private Header header;

	@JsonInclude(Include.NON_NULL)
	private String transactionId;
	@JsonInclude(Include.NON_NULL)
	private String boundProfilePackage;

	
	public GetBoundProfilePackageResponse(String txId,
										  String bPP,
										  Header header) {
		super();
		this.transactionId = txId; 
		this.boundProfilePackage = bPP;
		this.setHeader(header);
		// TODO Auto-generated constructor stub
	}
	
	public GetBoundProfilePackageResponse(Header header) {
		super();
		this.header = header;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getBoundProfilePackage() {
		return boundProfilePackage;
	}

	public void setBoundProfilePackage(String boundProfilePackage) {
		this.boundProfilePackage = boundProfilePackage;
	}

	@Override
	public String toString() {
		return "GetBoundProfilePackageResponse [header=" + header + ", transactionId=" + transactionId
				+ ", boundProfilePackage=" + boundProfilePackage + "]";
	}


}
