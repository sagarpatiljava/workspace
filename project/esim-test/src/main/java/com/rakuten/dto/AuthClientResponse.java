package com.rakuten.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class AuthClientResponse {

	private Header header;
	
	@JsonInclude(Include.NON_NULL)
	private String transactionId;
	@JsonInclude(Include.NON_NULL)
	private String profileMetadata;
	@JsonInclude(Include.NON_NULL)
	private String smdpSigned2;
	@JsonInclude(Include.NON_NULL)
	private String smdpSignature2;
	@JsonInclude(Include.NON_NULL)
	private String smdpCertificate;

	
	
	public AuthClientResponse(String transactionId, 
								String profileMetadata,
								String smdpSigned2,
								String smdpSignature2, 
								String smdpCertificate,
								Header header) {
		super();
		this.transactionId = transactionId;
		this.profileMetadata = profileMetadata;
		this.smdpSigned2 = smdpSigned2;
		this.smdpSignature2 = smdpSignature2;
		this.smdpCertificate = smdpCertificate;
		this.setHeader(header);
	}
	
	public AuthClientResponse(Header header) {
		super();
		this.header = header;
	}
	
	//@Override
	//public String toString() {
	//	return "AuthClientResponse [header=" + header + ", transactionId=" + transactionId + ", profileMetadata="
	//			+ profileMetadata + ", smdpSigned2=" + smdpSigned2 + ", smdpSignature2=" + smdpSignature2
	//			+ ", smdpCertificate=" + smdpCertificate + "]";
	//}

	@Override
	public String toString() {
		return "AuthClientResponse [transactionId=" + transactionId + ", profileMetadata="
				+ profileMetadata + ", smdpSigned2=" + smdpSigned2 + ", smdpSignature2=" + smdpSignature2
				+ ", smdpCertificate=" + smdpCertificate + "]";
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

	public String getProfileMetadata() {
		return profileMetadata;
	}

	public void setProfileMetadata(String profileMetadata) {
		this.profileMetadata = profileMetadata;
	}

	public String getSmdpSigned2() {
		return smdpSigned2;
	}

	public void setSmdpSigned2(String smdpSigned2) {
		this.smdpSigned2 = smdpSigned2;
	}

	public String getSmdpSignature2() {
		return smdpSignature2;
	}

	public void setSmdpSignature2(String smdpSignature2) {
		this.smdpSignature2 = smdpSignature2;
	}

	public String getSmdpCertificate() {
		return smdpCertificate;
	}

	public void setSmdpCertificate(String smdpCertificate) {
		this.smdpCertificate = smdpCertificate;
	}

}
