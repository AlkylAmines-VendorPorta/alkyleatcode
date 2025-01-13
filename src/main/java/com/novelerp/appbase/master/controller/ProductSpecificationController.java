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
public class ProductSpecificationController {
	
	@RequestMapping(value="/productSpecificationView")
	public ModelAndView productSpecificationView(){
		ModelAndView view = new ModelAndView();
		view.setViewName("productSpecificationForm");
		return view;
	}
}
