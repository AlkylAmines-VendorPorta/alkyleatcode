package com.novelerp.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	
	@RequestMapping(value= {"/home"},method =RequestMethod.GET)
	public ModelAndView welcome(HttpServletRequest request){
		request.getSession().invalidate();
		return new ModelAndView("home");
	}
	

}
