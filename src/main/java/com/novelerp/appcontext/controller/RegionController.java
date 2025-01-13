package com.novelerp.appcontext.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RegionDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.RegionService;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/**
 * 
 * @author Varsha Patil
 *
 */
@Controller
@RequestMapping("/rest")
public class RegionController {
    @Autowired
	private RegionService regionService;
    @Autowired
    private BPartnerService partnerService;
    @Autowired
    @Qualifier("jwtUserContext") 
    private ContextService contextService;
    
    @RequestMapping(value = "/getStateByCountry/{countryId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getStateByCountry(@PathVariable ("countryId") Long countryId){
		
		Map<String, Object> params =  AbstractServiceImpl.getParamMap("countryId", countryId);
 		List<RegionDto> regions = regionService.findDtos("getRegionBycountry", params);
      	CustomResponseDto response =  new CustomResponseDto("regions",regions);
      	return response;
	}
    @RequestMapping(value = "/getStateByCode/{partnerId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getStateByCode(@PathVariable("partnerId") Long partnerId){
    	CustomResponseDto response=new CustomResponseDto();
    	RoleDto role=contextService.getDefaultRole();
    	if(AppBaseConstant.ROLE_PARTNER_USER.equals(role.getValue()) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue())||AppBaseConstant.ROLE_VENDOR_ADMIN.equals(role.getValue())){
    		BPartnerDto partner=partnerService.findDto(partnerId);
    		if(null!=partner && "Y".equals(partner.getIsGstApplicable()) && null!=partner.getGstinNo()){
				String stateCode=partner.getGstinNo().substring(0,2);
				Map<String,Object> param=AbstractServiceImpl.getParamMap("gstStateCode",stateCode);
				List<RegionDto> regions=regionService.findDtos("getRegionByGstCode",param);
				response.addObject("regions",regions);
			}  
    	}
      	return response;
	}
    
    @RequestMapping(value = "/getState", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getState(){
		
		//Map<String, Object> params =  AbstractServiceImpl.getParamMap("countryId", countryId);
 		List<RegionDto> stateList = regionService.findDtos("getRegionQuery", null);
      	CustomResponseDto response =  new CustomResponseDto("stateList",stateList);
      	return response;
	}
}
