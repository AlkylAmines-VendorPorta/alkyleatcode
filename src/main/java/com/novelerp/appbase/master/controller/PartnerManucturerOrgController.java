package com.novelerp.appbase.master.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.service.PartnerOrgService;
import com.novelerp.appcontext.dto.CountryDto;
import com.novelerp.appcontext.dto.DistrictDto;
import com.novelerp.appcontext.dto.RegionDto;
import com.novelerp.appcontext.service.CountryService;
import com.novelerp.appcontext.service.DistrictService;
import com.novelerp.appcontext.service.RegionService;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/**
 * 
 * @author Varsha Patil
 *
 */
@Controller
public class PartnerManucturerOrgController {
	@Autowired
	private PartnerOrgService partnerOrgService;
		
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private RegionService regionService;
	
	@Autowired
	private DistrictService districtService;
	
	@RequestMapping(value = "/getManufacturerOrg/{partnerManufacturerId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPartnerOrg(@PathVariable ("partnerManufacturerId") Long partnerManufacturerId){
		
		Map<String, Object> params =  AbstractServiceImpl.getParamMap("partnerManufacturerId", partnerManufacturerId);
		List<PartnerOrgDto> orgs = partnerOrgService.findDtos("getPartnerManufacturerOrgsQuery", params);
		Map<String, Object> param= new HashMap<>();
 		List<CountryDto> countries= countryService.findDtos("getCountryQuery", param);
 		List<RegionDto> regions = regionService.findDtos("getRegionQuery", param);
 		List<DistrictDto> districts =  districtService.findDtos("getDistrictQuery", param);

 		CustomResponseDto response =  new CustomResponseDto("orgs",orgs);
 		response.addObject("countries", countries);
 		response.addObject("regions", regions);
 		response.addObject("districts",districts); 		
		return response;
	}
	
	@RequestMapping(value="/saveManucturerOrgDetails", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody PartnerOrgDto saveManucturerOrgDetails(@ModelAttribute("partnerManufacturerOrg") PartnerOrgDto dto){
	   return partnerOrgService.saveManufactureOrg(dto);	
	}
	
	@RequestMapping(value="/deleteManufacturerOrg/{manufacturerOrgId}", method= RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto delete(@PathVariable("manufacturerOrgId") Long manufacturerOrgId){		
		boolean deleted = partnerOrgService.deleteById(manufacturerOrgId);
		if(deleted){
			return new ResponseDto(false, "Record Deleted");
		}
		return new ResponseDto(true,"Problem in deleting record");
	}

}
