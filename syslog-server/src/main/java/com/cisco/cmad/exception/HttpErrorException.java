package com.cisco.cmad.exception;

public class HttpErrorException  extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7743173530656482952L;
	
	private long errorCode;
	private String message;

	public HttpErrorException(String message,long errorCode){
		super("CODE"+errorCode+ ": "+message);
		this.message=message;
		this.errorCode=errorCode;
	}

	public long getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
