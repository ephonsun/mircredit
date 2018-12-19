package com.banger.mobile.webservice.domain.loan;

public class PadErrorResult {
	private String operationName;
	
	private String errorValue;

	private String errorType;
	
	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public String getErrorValue() {
		return errorValue;
	}

	public void setErrorValue(String errorValue) {
		this.errorValue = errorValue;
	}
	
	
	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public PadErrorResult(String getErrorType , String operationName,String errorValue){
		this.setErrorType(getErrorType);
		this.setOperationName(operationName);
		this.setErrorValue(errorValue);
	}
}
