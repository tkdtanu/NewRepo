package com.tkd.empmgmt.exception;

import java.util.Date;

public class ErrorResponse {
	private String message;
	private Date timestamp;
	private String description;
	private int status = 400;

	public ErrorResponse(String message) {
		this.message = message;
		this.timestamp = new Date();
	}
	public ErrorResponse(String message, String description) {
		this.message = message;
		this.timestamp = new Date();
		this.description = description;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ErrorResponse [message=" + message + ", timestamp=" + timestamp + ", description=" + description
				+ ", status=" + status + "]";
	}
}
