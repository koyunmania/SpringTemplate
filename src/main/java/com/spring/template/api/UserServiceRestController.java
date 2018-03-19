package com.spring.template.api;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.template.model.User;
import com.spring.template.service.ServiceResult;
import com.spring.template.service.UserService;

@RestController
public class UserServiceRestController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/userapi/deleteuser", 
			method = RequestMethod.DELETE,
			produces = "application/json")
	@ResponseBody
	@Transactional
	public ServiceResult deleteUser(@RequestParam(value = "username") String username) {
		User user = new User();
		user.setUsername(username);
		ServiceResult serviceResult = userService.deleteUser(user);
		return serviceResult;
	}

	@RequestMapping(value = "/userapi/getuser", 
			method = RequestMethod.GET,
			produces = "application/json")
	@ResponseBody
	public ServiceResult getUser(@RequestParam(value = "username") String username) {
		ServiceResult serviceResult = userService.findUserByUsername(username);
		return serviceResult;
	}
	
	@RequestMapping(value = "/userapi/saveuser", 
			method = RequestMethod.POST,
			consumes = "application/json",
			produces = "application/json")
	@ResponseBody
	@Transactional
	public ServiceResult saveUser(@RequestBody User user) {
		ServiceResult serviceResult = userService.saveUser(user);
		return serviceResult;
	}

}
