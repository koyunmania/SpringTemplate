package com.spring.template.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminpageController {
	@RequestMapping(value="/admin", method = RequestMethod.GET)
	public ModelAndView adminpage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}
}
