package com.spring.template.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.template.model.Role;
import com.spring.template.model.RoleName;
import com.spring.template.model.User;
import com.spring.template.service.RoleService;
import com.spring.template.service.ServiceResult;
import com.spring.template.service.UserService;

@Controller
public class RegisterController {
	
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
	public ModelAndView register(@Valid User user, BindingResult result) throws ConstraintViolationException {
		ModelAndView modelAndView = new ModelAndView();

		if(result.hasFieldErrors("username")) {
			modelAndView.addObject("message", "Username not valid!");
			modelAndView.addObject("registerable", false);
		} else if(result.hasFieldErrors("password")) { 
			modelAndView.addObject("message", "Password not valid!");
			modelAndView.addObject("registerable", false);

		} else {
			user.setEmail(user.getUsername());
			RoleName roleUser = RoleName.User;
			Set<Role> roles= new HashSet<Role>();
			roles.add(roleService.findRoleByName(roleUser));
			user.setRoles(roles);
			ServiceResult serviceResult = userService.saveUser(user); 
			if(serviceResult.isStatus()) {
				modelAndView.addObject("message", serviceResult.getMessage());
				modelAndView.addObject("registerable", true);
			} else {
				modelAndView.addObject("message", serviceResult.getMessage());
				modelAndView.addObject("registerable", false);
				
			}
		}
		return modelAndView; 
	}
}
