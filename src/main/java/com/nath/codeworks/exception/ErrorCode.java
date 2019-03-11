package com.nath.codeworks.exception;

public enum ErrorCode {

	AUTHENTICATION(0, "AUTHENTICATION"),
	SYSTEM(1, "SYSTEM_ERROR"), 
	CONNECTIVITY(2, "CONNECTIVITY_ERROR"),
	NO_DATA(3, "EMPTY_DATA");

	private final int code;
	private final String description;

	private ErrorCode(int code, String description) {
	    this.code = code;
	    this.description = description;
	  }

	public String getDescription() {
		return description;
	}

	public int getCode() {
		return code;
	}

	@Override
	public String toString() {
		return String.format("%d : %s", code, description);
	}

}
