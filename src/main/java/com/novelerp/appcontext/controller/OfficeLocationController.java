package com.novelerp.appcontext.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.OfficeLocationDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.OfficeLocationService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.ResponseDto;

@Controller
public class OfficeLocationController {

	private final Logger log = LoggerFactory.getLogger(OfficeLocationController.class);
	
	@Autowired
	private OfficeLocationService officeLocationService;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@RequestMapping(value = "/getOfficeLocation/{locationtype}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<OfficeLocationDto> getOfficeLocation(@PathVariable("locationtype") String locationtype) {
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("locationtype", locationtype);
		List<OfficeLocationDto> officeLocation = officeLocationService.findDtos("getOfficeLocationByType", params);
		return officeLocation;
	}
	
	@RequestMapping(value = "/getofficeTypeListTab/{locationId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<OfficeLocationDto> getofficeTypeListTab(@PathVariable("locationId") long locationId) {
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("locationId", locationId);
		List<OfficeLocationDto> officeLocation = officeLocationService.findDtos("getOfficeTypeByLocationID", params);
		return officeLocation;
	}
	
	@RequestMapping(value = "/addNewOfficeType", method=RequestMethod.POST)
	public @ResponseBody OfficeLocationDto  addNewReferenceList(@ModelAttribute("OfficeLocation") OfficeLocationDto OfficeLocation){
		
		BPartnerDto bPartnerDto =contextService.getPartner();
		OfficeLocation.setPartner(bPartnerDto);
		OfficeLocation.setIsActive("Y");
		OfficeLocationDto OfficeLocationDtoList=new OfficeLocationDto();
		if(OfficeLocation.getOfficeLocationId()==null){
			OfficeLocationDtoList=officeLocationService.save(OfficeLocation);
		}
		else{
			OfficeLocationDtoList=officeLocationService.updateDto(OfficeLocation);
		}
		return OfficeLocationDtoList;
		
	}
	@RequestMapping(value = "/getofficeTypeById/{officeLocationId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody OfficeLocationDto getofficeTypeById(@PathVariable("officeLocationId") Long officeLocationId) {
		
		OfficeLocationDto reference = officeLocationService.findDto(officeLocationId);
		return reference;
	}
	@RequestMapping(value = "/deleteOfficeType/{officeTypeId}", method=RequestMethod.POST)
	public @ResponseBody ResponseDto deleteReference(@PathVariable("officeTypeId") Long officeTypeId){
	
		
		 ResponseDto response=null;
		 try{
	       boolean isDeleted=false;
	       if(officeTypeId>0){
	    	   isDeleted= officeLocationService.deleteReferenceList(officeTypeId);
	       }
	       if(isDeleted){
	    	   response= new ResponseDto(false, "Office Type List Deleted...!");
	       }
	       else{
	    	   response=new ResponseDto(true,"Cannot Delete Office Type...!");
	       }
		 }
		 catch(Exception e){
			 response = new ResponseDto(true,"Office Type is Already in Use...!");
		 }
			return response;
	    
	
		
	}
	
}
