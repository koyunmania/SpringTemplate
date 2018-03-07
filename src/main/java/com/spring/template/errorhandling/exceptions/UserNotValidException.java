package com.spring.template.errorhandling.exceptions;

public class UserNotValidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2218549957589238417L;
	
	private String errorMessage;
	private String viewName;
	
	public UserNotValidException(String errorMessage , String viewName) {
		this.setErrorMessage(errorMessage);
		this.setViewName(viewName);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

}
