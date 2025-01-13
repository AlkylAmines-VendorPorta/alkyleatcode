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

import com.novelerp.appbase.master.dto.PartnerItemManufacturerDto;
import com.novelerp.appbase.master.service.PartnerItemManufacturerService;
import com.novelerp.appcontext.dto.CountryDto;
import com.novelerp.appcontext.dto.DistrictDto;
import com.novelerp.appcontext.dto.RegionDto;
import com.novelerp.appcontext.service.CountryService;
import com.novelerp.appcontext.service.DistrictService;
import com.novelerp.appcontext.service.RegionService;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;

/** 
 * @author Aman Sahu
 *
 */
@Controller
public class PartnerItemManufacturerController {
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private RegionService regionService;
	
	@Autowired
	private DistrictService districtService;
		
	@Autowired
	private PartnerItemManufacturerService partnerItemManufacturerService;
		
	@RequestMapping(value = "/getPartnerItemManufacturer/{partnerId}", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getPartnerItemManufacturer(@PathVariable ("partnerId") Long partnerId){
		List<PartnerItemManufacturerDto> partnerItemManufacturer = partnerItemManufacturerService.getPartnerItemManufacturer(partnerId);
		Map<String, Object> param= new HashMap<>();
 		List<CountryDto> countries= countryService.findDtos("getCountryQuery", param);
 		List<RegionDto> regions = regionService.findDtos("getRegionQuery", param);
 		List<DistrictDto> districts =  districtService.findDtos("getDistrictQuery", param);

		CustomResponseDto response  = new CustomResponseDto("partnerItemManufacturer", partnerItemManufacturer);
 		response.addObject("countries", countries);
 		response.addObject("regions", regions);
 		response.addObject("districts",districts);
		return response;

		
	}
	
	@RequestMapping(value="/savePartnerItemManufacturer", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PartnerItemManufacturerDto savePartnerItemManufacturer(@ModelAttribute ("partnerManufacturer") PartnerItemManufacturerDto dto){
	   return partnerItemManufacturerService.saveItemManufacture(dto);	
	}
	
	@RequestMapping( value ="/deletePartnerItemManufacturer/{partnerItemManufacturerId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)	
	public @ResponseBody ResponseDto delete (@PathVariable("partnerItemManufacturerId") Long partnerItemManufacturerId){
		boolean deleted = partnerItemManufacturerService.deleteById(partnerItemManufacturerId);
		if(deleted){
			return new ResponseDto(false, "Record Deleted");
		}
		return new ResponseDto(true, "Problem in deleting record;");
	}

}
