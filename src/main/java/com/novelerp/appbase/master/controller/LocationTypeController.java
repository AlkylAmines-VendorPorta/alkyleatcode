package com.novelerp.appbase.master.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.LocationTypeDto;
import com.novelerp.appbase.master.service.LocationTypeService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.ResponseDto;

/**
 * @author Aman
 *
 */
@Controller
public class LocationTypeController {

	@Autowired
	
	 @Qualifier("jwtUserContext") private ContextService contextService;

	@Autowired
	private LocationTypeService locationTypeService;

	@RequestMapping(value = "/locationType", method = RequestMethod.GET)
	public ModelAndView LocationTypeView() {
		ModelAndView modelAndView = new ModelAndView("locationType");
		return modelAndView;
	}

	/*
	 * @RequestMapping(value = "/getLocationType", method = RequestMethod.POST)
	 * public List<LocationTypeDto> getLocationType() {
	 * System.out.println("..LocationTypeController-getLocationType()"); return
	 * locationTypeService.getLocationTypeList(); }
	 */

	@RequestMapping(value = "/getlocationtypeList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<LocationTypeDto> getLocationTypeList() {
		// Set<RoleDto> role=contextService.getRoles();
		BPartnerDto bPartnerDto = contextService.getPartner();
		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());
		List<LocationTypeDto> locationList = locationTypeService.findDtos("getLocationTypeByPartner", params);

		return locationList;
	}

	@RequestMapping(value = "/getlocationById/{LocationId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody LocationTypeDto getLocationById(@PathVariable("LocationId") Long LocationId) {

		LocationTypeDto locationtype = locationTypeService.findDto(LocationId);
		return locationtype;
	}

	@RequestMapping(value = "/addNewLocation", method = RequestMethod.POST)
	public @ResponseBody LocationTypeDto addLocationType(@ModelAttribute("LocationType") LocationTypeDto locationTypeDto) {
		BPartnerDto bPartnerDto = contextService.getPartner();
		locationTypeDto.setPartner(bPartnerDto);
		locationTypeDto.setIsActive("Y");
		LocationTypeDto location = new LocationTypeDto();
		if (locationTypeDto.getLocationTypeId() == null) {
			location = locationTypeService.save(locationTypeDto);
		} else {
			location = locationTypeService.updateDto(locationTypeDto);
		}
		return location;
	}

	@RequestMapping(value = "/deleteLocationType/{locationId}", method = RequestMethod.POST)
	public @ResponseBody ResponseDto deleteLocationType(@PathVariable("locationId") Long locationId) {
		ResponseDto response = null;
		try{
		boolean isDeleted = false;
		if (locationId != null) {
			isDeleted = locationTypeService.deleteLocationType(locationId);
		}
		if (isDeleted) {
			response = new ResponseDto(false, "LocationType Deleted...!");
		} else {
			response = new ResponseDto(true, "Cannot Delete LocationType...!");
		}
		}
		catch(Exception e){
			response = new ResponseDto(true, "LocationType is Already in Use...!");
		}
		return response;
	}

	@RequestMapping(value = "/getLocatList/{index}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody Map<String, Object> getLocatList(@PathVariable("index") Long id) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<LocationTypeDto> referenceList = locationTypeService.getLocationType(id);
		map.put("DATA", referenceList);
		return map;
	}

}
