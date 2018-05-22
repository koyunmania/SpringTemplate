package com.spring.template.errorhandling.exceptions;

import com.spring.template.service.ServiceResult;

public abstract class ExceptionHandling {
	public static ServiceResult returnResponse(Exception ex) {
		ServiceResult result = new ServiceResult ();
		result.setMessage(ex.getMessage());
		result.setStatus(false);
		return result;
	};
}
