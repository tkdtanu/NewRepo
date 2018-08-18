package com.tkd.empmgmt.exception;

public class UserCreationException extends EmployeeMgmtException {

	private static final long serialVersionUID = 7544956145432438168L;

	public UserCreationException() {
		super("Sorry!!! We are facing some problem while creating the user");
	}
	
	public UserCreationException(String message) {
		super(message);
	}

	public UserCreationException(String message, Integer errorCode) {
		super(message, errorCode);
	}

}
