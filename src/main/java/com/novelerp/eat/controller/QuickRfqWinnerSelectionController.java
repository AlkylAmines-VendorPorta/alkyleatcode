package com.novelerp.eat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appcontext.util.ContextConstant;

@Controller
public class QuickRfqWinnerSelectionController {
		
		@RequestMapping(value = "/quickRfqWinnerSelection", method = RequestMethod.GET)
		public ModelAndView tenderWinnerSelection() {
			ModelAndView model = new ModelAndView("quickRfqWinnerSelection");
			model.addObject("documentType",ContextConstant.DOCUMENT_QUICK_REQUEST_FOR_PROPOSAL);
			model.addObject("type", "winnerSelection");
			return model;
		}

}
