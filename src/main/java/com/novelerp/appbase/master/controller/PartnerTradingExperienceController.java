package com.novelerp.appbase.master.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.dto.PartnerOrgExperienceDto;
import com.novelerp.appbase.master.service.PartnerOrgExperienceService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;

/**
 * 
 * @author Varsha Patil
 *
 */
@Controller
public class PartnerTradingExperienceController {

	@Autowired
	private PartnerOrgExperienceService partnerOrgExperienceService;
	
	
	@RequestMapping(value = "/getTraderExperience/{partnerId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getTraderExperience(@PathVariable("partnerId") Long partnerId){
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("partnerId", partnerId);
		List<PartnerOrgExperienceDto> partnerOrgExperiences = partnerOrgExperienceService.findDtos("getTradingExperienceQuery", params);
		CustomResponseDto response =  new CustomResponseDto();
		response.addObject("partnerOrgExperiences", partnerOrgExperiences);
		return response;
	}
	
	@RequestMapping(value="/saveManufactureOrgExperience", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody PartnerOrgExperienceDto saveManufactureOrgExperience(@ModelAttribute ("mOrgExperience") PartnerOrgExperienceDto dto){
	    return partnerOrgExperienceService.saveOrgExp(dto);
	}
}
