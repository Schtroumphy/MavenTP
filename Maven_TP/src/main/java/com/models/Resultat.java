package com.models;

public class Resultat {

	boolean result;
	String message;
	public Resultat(boolean result, String message) {
		super();
		this.result = result;
		this.message = message;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
