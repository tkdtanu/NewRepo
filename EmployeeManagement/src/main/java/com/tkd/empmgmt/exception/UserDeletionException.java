package com.tkd.empmgmt.exception;

public class UserDeletionException extends EmployeeMgmtException {

	private static final long serialVersionUID = 63899521312214504L;
	private static final String Message = "Sorry!!! We are facing some problem while deleting the user"; 
	public UserDeletionException() {
		super(Message);
	}

	public UserDeletionException(String message) {
		super(message);
	}

	public UserDeletionException(String message, Integer errorCode) {
		super(message, errorCode);
	}

	public UserDeletionException(Integer errorCode) {
		super(Message,errorCode);
	}
	

}
