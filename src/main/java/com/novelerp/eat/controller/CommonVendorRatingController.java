package com.novelerp.eat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonVendorRatingController {
	
	@RequestMapping(value = { "/commonVendorRating" }, method = RequestMethod.GET)
	public ModelAndView rating() {
		ModelAndView modelAndView = new ModelAndView("commonVendorRating");
		return modelAndView;
	}

}
