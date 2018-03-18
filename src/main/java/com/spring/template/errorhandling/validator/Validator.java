package com.spring.template.errorhandling.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.template.model.User;
import com.spring.template.service.UserService;

@Component
public class Validator {

	private static UserService userService;
	
	@Autowired
	public Validator(UserService userService) {
		Validator.userService = userService;
	}
	
	public static User validateUserExistance(User user) {
		User result = (User) userService.findUserByUsername(user.getUsername()).getData();
		return result;
	}
}
