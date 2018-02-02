package com.spring.template.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.template.model.User;
import com.spring.template.service.UserService;

@RestController
public class UserServiceRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceRestController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public void deleteUser(@RequestParam(value = "username") String username) {
		try {
			User user = userService.findUserByUsername(username);
			try {
				userService.deleteUser(user);
			} catch (Exception e) {
				logger.error("__ERROR: UserServiceRestController.deleteUser(String email): User could not be deleted!", e);
			}
		} catch (Exception e) {
			logger.error("__ERROR: UserServiceRestController.deleteUser(String email): User could not be found!", e);
		} 
		
	}
}
