package com.spring.template.errorhandling.validator;

public class ValidatorResult {

	private boolean valid;
	
	private String validationText;
	
	public boolean isValid() {
		return valid;
	}
	
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	public String getValidationText() {
		return validationText;
	}
	
	public void setValidationText(String validationText) {
		this.validationText = validationText;
	}
}
