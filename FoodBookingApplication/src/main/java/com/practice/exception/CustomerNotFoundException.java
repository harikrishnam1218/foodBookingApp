package com.practice.exception;

public class CustomerNotFoundException extends Exception {
	
	private static final long serialVersionUID = -1635139686449341936L;
	
	private String messgae;

	public String getMessgae() {
		return messgae;
	}  

	public void setMessgae(String messgae) {
		this.messgae = messgae;
	}

	public CustomerNotFoundException(String messgae) {
		super();
		this.messgae = messgae;
	}

	public CustomerNotFoundException() {
		super();
	}
	
}
