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
public class ProductController {

	@RequestMapping(value="/productView", method = RequestMethod.GET)
	public ModelAndView productView(){
		ModelAndView view = new ModelAndView();
		view.setViewName("productForm");
		return view;
	}
}
