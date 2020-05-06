package com.rakuten.dto;

public class Header {
	private FunctionExecutionStatus functionExecutionStatus;
	
	
	
	public Header(FunctionExecutionStatus functionExecutionStatus) {
		super();
		this.functionExecutionStatus = functionExecutionStatus;
	}
	public FunctionExecutionStatus getFunctionExecutionStatus() {
		return functionExecutionStatus;
	}
	public void setFunctionExecutionStatus(FunctionExecutionStatus functionExecutionStatus) {
		this.functionExecutionStatus = functionExecutionStatus;
	
	}
}
