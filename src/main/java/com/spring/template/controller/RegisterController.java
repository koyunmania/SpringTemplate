package com.spring.template.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;

import com.spring.template.model.Role;
import com.spring.template.model.RoleName;
import com.spring.template.model.User;
import com.spring.template.service.RoleService;
import com.spring.template.service.UserService;

@Controller
public class RegisterController {
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private RoleService roleService; 
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView; 
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExisting = userService.findUserByUsername(user.getUsername()); 
		if(userExisting != null) {
			logger.warn("__WARN: RegisterController.register(User user): User already exists!");
			modelAndView.addObject("username", "REGISTRATION ERROR!!!");
		} else {
			user.setEmail(user.getUsername());
			String roleUser = RoleName.User.getRole();
			Set<Role> roles= new HashSet<Role>();
			roles.add(roleService.findRoleByName(roleUser));
			user.setRoles(roles);
			userService.saveUser(user);
			modelAndView.addObject("username", user.getUsername());
		}
		return modelAndView; 
	}
}
