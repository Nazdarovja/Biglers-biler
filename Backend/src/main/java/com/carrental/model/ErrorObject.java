package com.carrental.model;

public class ErrorObject {
	private int code;
	private String errorMessage;
	private String errorAdditional;
	
	public ErrorObject() {
		
	}
	
	public ErrorObject (int code, String errorMessage, String errorAdditional) {
		this.code = code;
		this.errorMessage = errorMessage;
		this.errorAdditional = errorAdditional;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorAdditional() {
		return errorAdditional;
	}
	public void setErrorAdditional(String errorAdditional) {
		this.errorAdditional = errorAdditional;
	}
	
	
	
}
