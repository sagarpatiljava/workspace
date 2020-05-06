package com.rakuten.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class FunctionExecutionStatus {

	private String status;
	@JsonInclude(Include.NON_NULL)
	
	private Map<String ,String> statusCodeData;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Map<String, String> getStatusCodeData() {
		return statusCodeData;
	}
	public void setStatusCodeData(Map<String, String> statusCodeData) {
		this.statusCodeData = statusCodeData;
	}
	@Override
	public String toString() {
		return "FunctionExecutionStatus [status=" + status + ", statusCodeData=" + statusCodeData + "]";
	}
	public FunctionExecutionStatus(String status, Map<String, String> statusCodeData) {
		super();
		this.status = status;
		this.statusCodeData = statusCodeData;
	}
	public FunctionExecutionStatus(String status) {
		super();
		this.status = status;
	}
	
	
	
	
}
