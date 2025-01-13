package com.novelerp.eat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appcontext.util.ContextConstant;

@Controller
public class RfqController {

	
	@RequestMapping(value= "/rfqPreparation", method=RequestMethod.GET)
	public ModelAndView rfqPreparation(){
		ModelAndView model= new ModelAndView("rfqPreparation");
		String dataUrl="tenderPreparationData";
		String dataForTypeCode="getTAHDRByTypeCode";
		model.addObject("documentType",ContextConstant.DOCUMENT_REQUEST_FOR_PROPOSAL);
		model.addObject("dataUrl", dataUrl);
		model.addObject("dataForTypeCode", dataForTypeCode);
		return model;
	}
	
}
