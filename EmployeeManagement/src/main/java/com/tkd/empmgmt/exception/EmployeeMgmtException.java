package com.tkd.empmgmt.exception;

public class EmployeeMgmtException extends Exception {

	private static final long serialVersionUID = 3656219158050212819L;
	
	private String message;
	
	private Integer errorCode;

	public EmployeeMgmtException() {
		super();
	}

	public EmployeeMgmtException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public EmployeeMgmtException(String message, Integer errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}

	public EmployeeMgmtException(Integer errorCode) {
		super();
		this.errorCode = errorCode;
	}

}
