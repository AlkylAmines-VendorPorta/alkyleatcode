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

import com.novelerp.appcontext.dto.BPartnerGroupDto;
import com.novelerp.appcontext.service.BPartnerGroupService;

/** 
 * @author Aman
 *
 */
@Controller
public class BusinessPartnerGroupController {
	private static final Logger log=LoggerFactory.getLogger(BusinessPartnerGroupController.class);
	@Autowired
	private BPartnerGroupService businessPartnerGroupService;

	@RequestMapping(value = "/BusinessPartnerGroup", method = RequestMethod.GET)
	public ModelAndView businessPartnerGroupView() {
		ModelAndView modelAndView = new ModelAndView("businessPartnerGroup");
		return modelAndView;
	}
	
	@RequestMapping(value = "/getBusinessPartnerGroupList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<BPartnerGroupDto> getBusinessPartnerGroupList() {
		
		List<BPartnerGroupDto> businessPartnerGrouplist=businessPartnerGroupService.getBusinessPartnerGroupList();
		return businessPartnerGrouplist;
	}
	
	@RequestMapping(value = "/getBusinessPartnerGroup/{businessPartnerGroupId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody BPartnerGroupDto getBusinessPartnerGroup(@PathVariable("businessPartnerGroupId") Long id) {
		
		BPartnerGroupDto businessPartnerGroup=businessPartnerGroupService.getBusinessPartnerGroup(id);
		return businessPartnerGroup;
	}

}




