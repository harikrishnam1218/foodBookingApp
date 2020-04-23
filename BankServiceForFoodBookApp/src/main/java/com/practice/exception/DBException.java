package com.practice.exception;

public class DBException extends Exception {

	private static final long serialVersionUID = -3250526797396148102L;
	
	private String messgae;
	
	private Exception ex;

	public Exception getEx() {
		return ex;
	}

	public void setEx(Exception ex) {
		this.ex = ex;
	}

	public String getMessgae() {
		return messgae;
	}

	public void setMessgae(String messgae) {
		this.messgae = messgae;
	}

	public DBException(String messgae) {
		super();
		this.messgae = messgae;
	}

	public DBException() {
		super();
	}

	public DBException(String messgae, Exception ex) {
		super();
		this.messgae = messgae;
		this.ex = ex;
	}
	
}
