package com.novelerp.appbase.master.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.CustomResponseDto;
import com.novelerp.appbase.master.dto.GtpParameterTypeDto;
import com.novelerp.appbase.master.service.GtpParameterTypeService;
import com.novelerp.appbase.validator.GtpParameterTypeValidator;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.RoleAccessMasterService;
import com.novelerp.core.dto.Errors;

/** 
 * @author Aman
 *
 */
@Controller
public class GtpParameterTypeController {
	@Autowired
	private GtpParameterTypeService gtpParameterTypeService;
	
	@Autowired
	private GtpParameterTypeValidator gtpParameterTypeValidator;
	
	@Autowired
	private RoleAccessMasterService roleAccessMasterService;
	
	@Autowired
	
	@Qualifier("jwtUserContext") private ContextService contextService;
	
	@RequestMapping(value = "/GtpParameterType", method = RequestMethod.GET)
	public ModelAndView GtpParameterTypeView() {
		ModelAndView mv=new ModelAndView("gtpParameterType");
		
	Set<RoleDto> role=contextService.getRoles();
			
			if(role!=null)
			{
				mv.addObject("access", roleAccessMasterService.getAccessByRoleId(role.iterator().next().getRoleId(),23l));
			}
		return  mv;
	}
	
	@RequestMapping(value = "/getGtpParameterTypeList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody List<GtpParameterTypeDto> getGtpParameterTypeList() {
		
		List<GtpParameterTypeDto> gtpParameterTypeDtoList = gtpParameterTypeService.getGtpParameterTypeList();
		
		return gtpParameterTypeDtoList;
	}
	
	@RequestMapping(value = "/getGtpParameterType/{id}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody GtpParameterTypeDto getGtpParameterType(@PathVariable("id") Long id) {
		
		GtpParameterTypeDto gtpParameterTypeDto=gtpParameterTypeService.getGtpParameterType(id);
		return gtpParameterTypeDto;
	}
	@RequestMapping(value="/saveGtpParameterType", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody CustomResponseDto save(@ModelAttribute("GtpParameterType") GtpParameterTypeDto dto){
		Errors errors =  new Errors();
		gtpParameterTypeValidator.validate(dto, errors);
		if(errors.getErrorCount()>0){
			return new CustomResponseDto(errors.getErrorList(), "Request has validation errors, look for error List",false);
		}
		else
		return gtpParameterTypeService.saveGtpParameterType(dto);
	}
	
	@RequestMapping(value = "/editGtpParameterType", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto edit(@ModelAttribute("GtpParameterType") GtpParameterTypeDto dto) {
		Errors errors =  new Errors();
		gtpParameterTypeValidator.validate(dto, errors);
		if(errors.getErrorCount()>0){
			return new CustomResponseDto(errors.getErrorList(), "Request has validation errors, look for error List",false);
		}
		else
		return gtpParameterTypeService.editGtpParameterType(dto);
	}
	
	@RequestMapping(value = "/deleteGtpParameterType/{gtpParameterTypeId}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto delete(@PathVariable("gtpParameterTypeId") Long id) {
		
			boolean deleted = gtpParameterTypeService.deleteById(id);
			if(!deleted){
				return new CustomResponseDto(false,"GTP PARAMETER TYPE  IS IN USE");
			}
			return	new CustomResponseDto(true,"Deleted Successfully");
			
	}
}

