package com.novelerp.appbase.master.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.dto.PartnerOrgBISDto;
import com.novelerp.appbase.master.service.PartnerOrgBISService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appbase.validator.PartnerOrgBISValidator;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;
/**
 * @author Aman
 *
 */
@Controller
public class PartnerOrgBISController {
	
	@Autowired
	private PartnerOrgBISService partnerOrgBISService;
	@Autowired
	private PartnerOrgBISValidator partnerOrgBISValidator;
	@Autowired
	
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@RequestMapping(value = "/getOrgBISDetails/{orgId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPartnerOrgBIS(@PathVariable("orgId") Long partnerOrgId){
		Map<String, Object> params =  AbstractServiceImpl.getParamMap("partnerOrgId", partnerOrgId);
 		List<PartnerOrgBISDto> orgBIS = partnerOrgBISService.findDtos("getOrgBISQuery", params);
		CustomResponseDto response =new CustomResponseDto();
		response.addObject("orgBIS", orgBIS);
		return response;
	}
	
	@RequestMapping(value="/saveOrgBISDetails", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody PartnerOrgBISDto savePartnerOrgBIS(@ModelAttribute("partnerOrgBIS") PartnerOrgBISDto dto){
		RoleDto role=contextService.getDefaultRole();
		if(role==null || role.getValue()==null)
		{   dto.setResponse(new ResponseDto(true, "Session Timeout...Please Login "));
			return dto;
		}
		if(role.getValue().equals(ContextConstant.USER_TYPE_PATNER_USER) || role.getValue().equals(ContextConstant.USER_TYPE_VENDOR_ADMIN) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue()))
		{
		  Errors errors=new Errors();
		  partnerOrgBISValidator.validate(dto, errors);
		  if(!CommonUtil.isCollectionEmpty(errors.getErrorList())){
			  ResponseDto response=new ResponseDto();
			  response.setHasError(true);
			  response.setMessage("Check Following Issues: ");
			  response.setErrors(errors.getErrorList());
			  dto.setResponse(response);
			  return dto;
		  }
		}
		return partnerOrgBISService.saveOrgBIS(dto,role);	
	}
	
	@RequestMapping(value="/deleteBIS/{bisId}", method= RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto delete(@PathVariable("bisId") Long bisId){		
		boolean deleted = partnerOrgBISService.deleteById(bisId);
		if(deleted){
			return new ResponseDto(false, "Record Deleted");
		}
		return new ResponseDto(true,"Problem in deleting record");
	}
	

}
