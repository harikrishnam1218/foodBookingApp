package com.practice.exception;

public class BatchJobException extends RuntimeException{

	private String message;
	
	private Exception ex;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Exception getEx() {
		return ex;
	}

	public void setEx(Exception ex) {
		this.ex = ex;
	}

	public BatchJobException(String message, Exception ex) {
		super();
		this.message = message;
		this.ex = ex;
	}

	public BatchJobException(String message) {
		super();
		this.message = message;
	}

	public BatchJobException() {
		super();
	}
	
}
