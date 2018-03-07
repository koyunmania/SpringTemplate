package com.spring.template.errorhandling.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.template.model.Role;
import com.spring.template.model.User;
import com.spring.template.service.UserService;
import com.spring.template.errorhandling.validator.ValidatorResult;

@Component
public class Validator {

	private static final Logger logger = LoggerFactory.getLogger(Validator.class);
	
	private static UserService userService;
	
	@Autowired
	public Validator(UserService userService) {
		Validator.userService = userService;
	}
	
	public static ValidatorResult validate(User user) {
		ValidatorResult result = new ValidatorResult(); 
		if(userService.findUserByUsername(user.getUsername()).getData() != null) {
			logger.warn("__WARN: UserValidator.validate(): User already exists!");
			result.setValidationText("REGISTRATION ERROR: User " + user.getUsername() + " already exists!");
			result.setValid(false);
		} else {
			result.setValidationText("Congrats " + user.getUsername() + " you are registered!");
			result.setValid(true);
		}
		return result;
	}
}
