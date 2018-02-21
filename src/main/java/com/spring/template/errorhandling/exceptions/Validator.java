package com.spring.template.errorhandling.exceptions;

public class Validator {
	private boolean valid;
	private String validationText;
	
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public boolean getValid() {
		return valid;
	}
	public String getValidationString() {
		return validationText;
	}
	public void setValidationString(String validationString) {
		this.validationText = validationString;
	}
}
