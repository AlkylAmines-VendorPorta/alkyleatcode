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
public class ProductCategoryController {

	@RequestMapping(value="/productCategoryView")
	public ModelAndView productCategoryView(){
		ModelAndView view =  new ModelAndView();
		view.setViewName("productCategoryForm");
		return view;
	}
}
