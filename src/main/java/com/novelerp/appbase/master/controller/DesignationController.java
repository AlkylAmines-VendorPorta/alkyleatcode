package com.novelerp.appbase.master.controller;

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

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.DesignationDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.DesignationService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.ResponseDto;

/**
 * 
 * @author Ankita Tirodkar
 *
 */

@Controller
public class DesignationController {

	@Autowired
	private DesignationService designationService;
	@Autowired
	
	@Qualifier("jwtUserContext") private ContextService contextService;
	
	@RequestMapping(value = {"/designation"}, method = RequestMethod.GET)
	public ModelAndView designationView() {
		return new ModelAndView("designation");
	}
	
	@RequestMapping(value = "/getDesignationList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<DesignationDto> getDesignationList() {
		BPartnerDto bPartnerDto =contextService.getPartner();
		RoleDto role=contextService.getDefaultRole();
		List<DesignationDto> designationtList =null;
		if(role!=null){
			if(role.getValue().equalsIgnoreCase(AppBaseConstant.ROLE_SYS_ADMIN)){
				designationtList = designationService.findAll();
			}else{
				Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("BPartnerId", bPartnerDto.getbPartnerId());
				designationtList = designationService.findDtos("getDesignationListByPartner", params);
			}	
		}
		return designationtList;
	}
		
	@RequestMapping(value = "/getDesignation/{designationId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody DesignationDto getDesignation(@PathVariable("designationId") Long id) {
		
		DesignationDto designationDto=designationService.findDto(id);
		return designationDto;
	}
	/*@RequestMapping(value = "/addNewDesignation", method=RequestMethod.POST)
	public @ResponseBody DesignationDto  addNewDesignation(@ModelAttribute("Designation") DesignationDto designationtDto){
		BPartnerDto bPartnerDto =contextService.getPartner();
		designationtDto.setPartner(bPartnerDto);
		designationtDto.setIsActive("Y");
		DesignationDto resdto = designationService.save(designationtDto);
		return resdto;
	}
	@RequestMapping(value = "/updateDesigantion", method=RequestMethod.POST)
	public @ResponseBody DesignationDto  updateDesigantion(@ModelAttribute("Designation") DesignationDto designationtDto){
		DesignationDto dto =designationService.findDto(designationtDto.getDesignationId());
		BPartnerDto bPartnerDto =contextService.getPartner();
		dto.setPartner(bPartnerDto);
		dto.setName(designationtDto.getName());
		dto.setCode(designationtDto.getCode());
		dto.setDescription(designationtDto.getDescription());
		DesignationDto resdto = designationService.updateDto(dto);
		return resdto;
	}*/
	
	@RequestMapping(value="/saveDesgination" , method= RequestMethod.POST , produces="application/json")
	public @ResponseBody DesignationDto saveDesgination(@ModelAttribute("Designation") DesignationDto designationDto){
		DesignationDto designation= new DesignationDto();
		BPartnerDto bPartnerDto= contextService.getPartner();
		designationDto.setPartner(bPartnerDto);
		designationDto.setIsActive("Y");
		if(designationDto.getDesignationId()==null){
			designation=designationService.save(designationDto);
		}
		else{
			designation=designationService.updateDto(designationDto);
		}
		return designation;
		
	}
	
	/*
	@RequestMapping(value = "/deleteDesignation", method=RequestMethod.POST)
	public @ResponseBody DesignationDto  deleteDesignation(@ModelAttribute("Designation") DesignationDto designationtDto){
		DesignationDto dto =designationService.findDto(designationtDto.getDesignationId());
		dto.setIsActive("N");
		DesignationDto resdto = designationService.updateDto(dto);
		
		return resdto;
		
		
	}*/
	@RequestMapping(value = "/deleteDesignation/{Id}", method=RequestMethod.POST , produces = "application/json")
	public @ResponseBody ResponseDto  deleteDesignation(@PathVariable("Id") Long id){
		ResponseDto response=null;
		try{
		boolean isDeleted=false;
		if(id>0){
			isDeleted=designationService.deleteDesignation(id);
		}
		 if(isDeleted){
	    	   response= new ResponseDto(false, "Designation Deleted...!");
	       }
	       else{
	    	   response=new ResponseDto(true,"Cannot Delete Designation...!");
	       }
		}
		catch(Exception e){
			 response=new ResponseDto(true,"Designation is Already in Use...!");
		}
		return response;
	}

	
}
