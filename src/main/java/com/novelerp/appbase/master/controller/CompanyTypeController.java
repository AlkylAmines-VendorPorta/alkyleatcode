package com.novelerp.appbase.master.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.CompanyTypeDto;
import com.novelerp.appbase.master.service.CompanyTypeService;
/**
 * 
 * @author Aman
 *
 */
@Controller
public class CompanyTypeController {
	private static final Logger log=LoggerFactory.getLogger(CompanyTypeController.class);
	@Autowired
	private CompanyTypeService companyTypeService;
	
	@RequestMapping(value = "/CompanyType", method = RequestMethod.GET)
	public ModelAndView companyTypeView() {
		ModelAndView modelAndView = new ModelAndView("companyType");
		return modelAndView;
	}
	
	@RequestMapping(value = "/getCompanyTypeList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<CompanyTypeDto> getCompanyTypeList() {
		
		List<CompanyTypeDto> companyTypelist=companyTypeService.getCompanyTypeList();
		return companyTypelist;
	}
	
	@RequestMapping(value = "/getCompanyType/{companyTypeId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody CompanyTypeDto getCompanyType(@PathVariable("companyTypeId") Long id) {
		
		CompanyTypeDto companyType=companyTypeService.getCompanyType(id);
		return companyType;
	}
}
