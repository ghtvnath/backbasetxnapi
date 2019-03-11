package com.nath.codeworks.exception;

/**
 * @author ghtvnath
 * 
 * Custom Exception for Backbase Transactions API application
 */
public class BackBaseRestException extends Exception {

	private static final long serialVersionUID = 5236137056119085730L;

	private final ErrorCode errorCode;

	public BackBaseRestException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

}
