package com.novelerp.eat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.util.ContextConstant;

@Controller
public class RfqProposalSubmissionController {
	
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;

	
	@RequestMapping(value= {"/rfqProposalSubmission"},method =RequestMethod.GET)
	public ModelAndView tenderSubmission(){
		ModelAndView model=new ModelAndView("rfqProposalSubmission");
		/*String fileSizeForWT = appPropertyUtil.getProperty("tender.file.size");*/
		String fileSizeForWT =sysConfiguratorService.getPropertyConfigurator("tender.file.size");

		/*String fileSizeForPT = appPropertyUtil.getProperty("partner.file.size");*/
		String fileSizeForPT =sysConfiguratorService.getPropertyConfigurator("partner.file.size");

		model.addObject("documentType",ContextConstant.DOCUMENT_REQUEST_FOR_PROPOSAL);
		model.addObject("fileSizeForWT", fileSizeForWT);
		model.addObject("fileSizeForPT", fileSizeForPT);
		return model;
	}
}
