package com.practice.exception;

public class BankRunTimeException extends RuntimeException{
	
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
	public BankRunTimeException(String message, Exception ex) {
		super();
		this.message = message;
		this.ex = ex;
	}
	public BankRunTimeException(String message) {
		super();
		this.message = message;
	}
	public BankRunTimeException() {
		super();
	}
	
	
}
