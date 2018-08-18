package com.tkd.empmgmt.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.tkd.empmgmt.exception.EmployeeMgmtException;
import com.tkd.empmgmt.exception.ErrorResponse;

@RestControllerAdvice
public class EmployeeMgmtGlobalExceptionHandler {
	
	@ExceptionHandler(value=EmployeeMgmtException.class)
	public ResponseEntity<ErrorResponse> handleUserCreationException(EmployeeMgmtException e, WebRequest request) {
		ErrorResponse error = new ErrorResponse(e.getMessage(), request.getDescription(false));
		if(e.getErrorCode() !=null) {
			return new ResponseEntity<>(error, HttpStatus.valueOf(e.getErrorCode()));
		}
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
