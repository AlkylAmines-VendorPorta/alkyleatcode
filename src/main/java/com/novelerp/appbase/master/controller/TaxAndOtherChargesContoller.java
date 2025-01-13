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
public class TaxAndOtherChargesContoller {

	@RequestMapping(value="/taxAndOtherChargesView")
	public ModelAndView taxAndOtherChargesView(){
		ModelAndView view = new ModelAndView();
		view.setViewName("taxAndOtherChargesForm");
		return view;
	}
}
