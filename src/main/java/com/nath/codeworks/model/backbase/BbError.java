package com.nath.codeworks.model.backbase;

/**
 * @author ghtvnath
 * 
 * Format in which error will be responded to API clients
 */
public class BbError {
	
	private final String errorCode;
	private final String errorMessage;
	
	public BbError(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	

}
