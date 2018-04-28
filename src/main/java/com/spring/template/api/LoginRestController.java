package com.spring.template.api;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.template.service.ServiceResult;

@RestController
public class LoginRestController {

	@RequestMapping(value="/api/login",
			method=RequestMethod.GET,
			produces = "application/json")
	@ResponseBody
	public ServiceResult login() {
		ServiceResult result = new ServiceResult();
		result.setMessage("Please login with /api/authuser");
		result.setData(false);
		return result;
	}
}
