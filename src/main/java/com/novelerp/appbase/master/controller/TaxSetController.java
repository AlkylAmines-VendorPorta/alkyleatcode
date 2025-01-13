package com.novelerp.appbase.master.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Vivek Birdi
 *
 */
@Controller
public class TaxSetController {

	@RequestMapping(value="/taxtSetView")
	public ModelAndView taxSetView(){
		ModelAndView view =  new ModelAndView();
		view.setViewName("taxSetForm");
		return view;
	}
}
