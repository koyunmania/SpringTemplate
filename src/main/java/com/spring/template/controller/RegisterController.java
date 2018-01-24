package com.spring.template.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.template.model.User;
import com.spring.template.service.UserService;

@Controller
public class RegisterController {
	@Autowired
	private UserService userService; 
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView; 
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(@Valid User user) {
		ModelAndView modelAndView = new ModelAndView();
		userService.saveUser(user);
		modelAndView.addObject("username", user.getUsername());
		return modelAndView; 
	}
}
