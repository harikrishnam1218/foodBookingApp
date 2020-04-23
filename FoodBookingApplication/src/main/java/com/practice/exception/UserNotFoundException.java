package com.practice.exception;

public class UserNotFoundException extends Exception {
	
	private String messgae;

	public String getMessgae() {
		return messgae;
	}  

	public void setMessgae(String messgae) {
		this.messgae = messgae;
	}

	public UserNotFoundException(String messgae) {
		super();
		this.messgae = messgae;
	}

	public UserNotFoundException() {
		super();
	}
	
}
