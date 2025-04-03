package com.cms.app.exception;


import org.springframework.http.HttpStatus;

public class ErrorMessage {
	
	private HttpStatus errorCode;
	private String message;
	
	public ErrorMessage(HttpStatus errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

	public HttpStatus getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
