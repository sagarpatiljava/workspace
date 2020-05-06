package com.rakuten.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class InitAuthResponse {

	private Header header;
	
	@JsonInclude(Include.NON_NULL)
	private String transactionId;
	@JsonInclude(Include.NON_NULL)
	private String serverSigned1;
	@JsonInclude(Include.NON_NULL)
	private String serverSignature1;
	@JsonInclude(Include.NON_NULL)
	private String euiccCiPKIdToBeUsed;
	@JsonInclude(Include.NON_NULL)
	private String serverCertificate;
	
	
	public InitAuthResponse(String transactionId, 
							String serverSigned1,
							String serverSignature1,
							String euiccCiPKIdToBeUsed, 
							String serverCertificate,
							Header header) {
		super();
		this.transactionId = transactionId;
		this.serverSigned1 = serverSigned1;
		this.serverSignature1 = serverSignature1;
		this.euiccCiPKIdToBeUsed = euiccCiPKIdToBeUsed;
		this.serverCertificate = serverCertificate;
		this.setHeader(header);
	}
	
	
	public InitAuthResponse(Header header) {
		super();
		this.header = header;
	}


	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getEuiccCiPKIdToBeUsed() {
		return euiccCiPKIdToBeUsed;
	}
	public void setEuiccCiPKIdToBeUsed(String euiccCiPKIdToBeUsed) {
		this.euiccCiPKIdToBeUsed = euiccCiPKIdToBeUsed;
	}
	public String getServerCertificate() {
		return serverCertificate;
	}
	public void setServerCertificate(String serverCertificate) {
		this.serverCertificate = serverCertificate;
	}
	public String getServerSigned1() {
		return serverSigned1;
	}
	public void setServerSigned1(String serverSigned1) {
		this.serverSigned1 = serverSigned1;
	}
	public String getServerSignature1() {
		return serverSignature1;
	}
	public void setServerSignature1(String serverSignature1) {
		this.serverSignature1 = serverSignature1;
	}
	
	
	
	@Override
	public String toString() {
		return "InitAuthResponse [transactionId= " + transactionId + ",serverSigned1=" + serverSigned1 + ", serverSignature1=" + serverSignature1 + ",euiccCiPKIdToBeUsed=" + euiccCiPKIdToBeUsed +",serverCertificate=" + serverCertificate +"]";
	}
	/*public String toString() {
		return "InitAuthResponse [transactionId=" + transactionId + ", euiccCiPKIdToBeUsed=" + euiccCiPKIdToBeUsed
				+ ", serverCertificate=" + serverCertificate + ", serverSigned1=" + serverSigned1
			+ ", serverSignature1=" + serverSignature1 + "]";
	}*/
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	
	
}
