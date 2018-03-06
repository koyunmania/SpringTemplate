package com.spring.template.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.template.model.User;
import com.spring.template.service.UserService;

@RestController
public class UserServiceRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceRestController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/userapi/deleteuser", method = RequestMethod.DELETE)
	public void deleteUser(@RequestParam(value = "username") String username) {
		User user = null;
		try {
			user = userService.findUserByUsername(username);
		} catch (Exception e) {
			logger.error("__ERROR: UserServiceRestController.deleteUser(String email): User could not be found!", e);
			return;
		} 
		try {
			userService.deleteUser(user);
		} catch (Exception e) {
			logger.error("__ERROR: UserServiceRestController.deleteUser(String email): User could not be deleted!", e);
		}
	}

	@RequestMapping(value = "/userapi/getuser", method = RequestMethod.GET)
	public User getUser(@RequestParam(value = "username") String username) {
		User user = null;
		try {
			user = userService.findUserByUsername(username);
		} catch (Exception e) {
			logger.error("__ERROR: UserServiceRestController.deleteUser(String email): User could not be found!", e);
		}
		return user;
	}
	
	@RequestMapping(value = "/userapi/saveuser", 
			method = RequestMethod.POST,
			consumes = "application/json",
			produces = "application/json")
	@ResponseBody
	public RestResult saveUser(@RequestBody User user) {
		RestResult result = new RestResult();
		try {
			userService.saveUser(user);
			result.setStatus(true);
			result.setMessage("User saved successfully...");
			result.setData(user);
		} catch (Exception e) {
			logger.error("__ERROR: UserServiceRestController.saveUser(User user): User could not be saved!", e);
			result.setStatus(false);
			result.setMessage("User could not be saved!");
			result.setData(user);
		}
		return result;
	}
}
