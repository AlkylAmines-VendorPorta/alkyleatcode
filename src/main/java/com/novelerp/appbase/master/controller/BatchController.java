package com.novelerp.appbase.master.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Vivek Birdi
 *
 */

@Controller
public class BatchController {
	
	@RequestMapping(value="/batchView", method = RequestMethod.GET)
	public ModelAndView batchView(){	
		ModelAndView view =  new ModelAndView();
		view.setViewName("batchForm");
		return view;
	}
	
}
