package com.novelerp.appcontext.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appcontext.dto.DistrictDto;
import com.novelerp.appcontext.service.DistrictService;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/**
 * 
 * @author Varsha Patil
 *
 */
@Controller
@RequestMapping("/rest")
public class DistrictController {
		@Autowired
		private DistrictService districtService;
	   
	    @RequestMapping(value = "/getDistrictByState/{stateId}", method=RequestMethod.POST)
		public @ResponseBody CustomResponseDto getPartnerCompanyAddress(@PathVariable ("stateId") Long stateId){
			Map<String, Object> params =  AbstractServiceImpl.getParamMap("stateId", stateId);
			List<DistrictDto> districts =  districtService.findDtos("getDistrictByState", params);
	      	CustomResponseDto response =  new CustomResponseDto("districts",districts);
	      	return response;
		}
	    
	    @RequestMapping(value = "/getDistrict", method=RequestMethod.POST)
		public @ResponseBody CustomResponseDto getDistrict(){
			List<DistrictDto> districts =  districtService.findDtos("getDistrictQuery", null);
	      	CustomResponseDto response =  new CustomResponseDto("districts",districts);
	      	return response;
		}
}
