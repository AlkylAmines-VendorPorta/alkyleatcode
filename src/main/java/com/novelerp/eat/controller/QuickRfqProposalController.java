package com.novelerp.eat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appcontext.util.ContextConstant;

@Controller
public class QuickRfqProposalController {
	
	@RequestMapping(value= "/quickRfqProposal", method=RequestMethod.GET)
	public ModelAndView quickRfqPreparation(){
		ModelAndView model= new ModelAndView("quickRfqProposal");
		model.addObject("documentType",ContextConstant.DOCUMENT_QUICK_REQUEST_FOR_PROPOSAL);
		return model;
	}

}
