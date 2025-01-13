package com.novelerp.appbase.master.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * 
 * @author vivek.birdi
 *
 */
@Controller
public class PaymentTermController {

	@RequestMapping(value="/paymentTermView", method=RequestMethod.GET)
	public ModelAndView paymentTermView(){
		ModelAndView view =  new ModelAndView();
		view.setViewName("paymentTermForm");
		return view;
	}
}
