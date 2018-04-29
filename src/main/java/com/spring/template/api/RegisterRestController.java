package com.spring.template.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.template.controller.RegisterController;
import com.spring.template.model.User;
import com.spring.template.service.ServiceResult;

@RestController
public class RegisterRestController {
	
	@Autowired
	RegisterController registerController;
	
	@RequestMapping(value="/api/register",
			method=RequestMethod.POST,
			consumes="application/json",
			produces="application/json")
	@ResponseBody
	public ServiceResult register(@RequestBody User user) {
		ServiceResult result = new ServiceResult();
		result = registerController.register(user);
		return result;
	}
}
