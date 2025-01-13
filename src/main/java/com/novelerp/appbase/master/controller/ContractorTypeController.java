package com.novelerp.appbase.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.ContractorTypeDto;
import com.novelerp.appbase.master.service.ContractorTypeService;

/** 
 * @author Aman
 *
 */
@Controller
public class ContractorTypeController {
	@Autowired
	private ContractorTypeService contractorTypeService;
	
	@RequestMapping(value = "/ContractorType", method = RequestMethod.GET)
	public ModelAndView contractorTypeView() {
		ModelAndView modelAndView = new ModelAndView("contractorType");
		return modelAndView;
	}
	
	@RequestMapping(value = "/getContractorTypeList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<ContractorTypeDto> getContractorTypeList() {
		
		List<ContractorTypeDto> contractorTypelist=contractorTypeService.getContractorTypeList();
		return contractorTypelist;
	}
	
	@RequestMapping(value = "/getContractorType/{Id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody ContractorTypeDto getContractorType(@PathVariable("Id") Long id) {
		
		ContractorTypeDto contractorType=contractorTypeService.getContractorType(id);
		return contractorType;
	}

}


