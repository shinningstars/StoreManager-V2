package com.storemanager.payload;

public class ResponseDetails {
	private String message;

	public ResponseDetails(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
