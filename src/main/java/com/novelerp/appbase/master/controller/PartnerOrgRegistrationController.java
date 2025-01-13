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

import com.novelerp.appbase.master.dto.PartnerOrgRegistrationDto;
import com.novelerp.appbase.master.service.PartnerOrgRegistrationService;
/**
 * @author Aman
 *
 */
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appbase.validator.PartnerOrgRegistrationValidator;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;
@Controller
public class PartnerOrgRegistrationController {
	
	@Autowired
	private PartnerOrgRegistrationService partnerOrgRegistrationService;
	
	@Autowired
	private ReferenceListService refListService;
	@Autowired
	private PartnerOrgRegistrationValidator regValidator;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
		
	@RequestMapping(value = "/getOrgRegistration/{partnerOrgId}", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getPartnerOrgRegistration(@PathVariable ("partnerOrgId") Long partnerOrgId){
		Map<String, String> registrationTypes = refListService.getReferenceListMap(CoreReferenceConstants.EXEMP_REG_TYPE);
		Map<String, String> validityTypes = refListService.getReferenceListMap(CoreReferenceConstants.VALIDITY_TYPE);
		Map<String, String> natureCode = refListService.getReferenceListMap(CoreReferenceConstants.NATURE_OF_ACTIVITY);
		Map<String, String> categoryCode = refListService.getReferenceListMap(CoreReferenceConstants.ENTERPRISE_CATEGORY);
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("partnerOrgId", partnerOrgId);
 		List<PartnerOrgRegistrationDto> partnerOrgRegistrations = partnerOrgRegistrationService.findDtos("getPartnerOrgRegQuery", params);
		CustomResponseDto response = new CustomResponseDto("partnerOrgRegistrations", partnerOrgRegistrations);
		response.addObject("registrationTypes", registrationTypes);
		response.addObject("validityTypes", validityTypes);
		response.addObject("natureCode", natureCode);
		response.addObject("categoryCode", categoryCode);
		return response;
	}
	
	@RequestMapping(value="/saveOrgRegistration", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PartnerOrgRegistrationDto save(@ModelAttribute ("partnerOrgRegistration") PartnerOrgRegistrationDto dto){
	    RoleDto role=contextService.getDefaultRole();
	    Errors errors =  new Errors();
		ResponseDto response=new ResponseDto();
		
		if(role==null || role.getValue()==null){
			dto.setResponse(new ResponseDto(true,"Session Timeout...Please Login"));
			return dto;
		}
		if(role.getValue().equals(ContextConstant.USER_TYPE_PATNER_USER) || role.getValue().equals(ContextConstant.USER_TYPE_VENDOR_ADMIN)|| AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue())){
			regValidator.validate(dto,errors);
			if(!CommonUtil.isCollectionEmpty(errors.getErrorList())){
				response.setHasError(true);
				response.setErrors(errors.getErrorList());
				dto.setResponse(response);
				return dto;
			}
		}
		return partnerOrgRegistrationService.saveOrgRegistration(dto);
	}
	
	@RequestMapping(value="/deletePartnerOrgRegistration/{partnerOrgRegistrationId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto delete(@PathVariable("partnerOrgRegistrationId") Long partnerOrgRegistrationId){
		boolean deleted =  partnerOrgRegistrationService.deleteById(partnerOrgRegistrationId);
		if(deleted){
			return new ResponseDto(false, "Record deleted");
		}
		return new ResponseDto(true, "Problem in deleting record");
	}
	@RequestMapping(value="/getPartnerOrgByRegType/{registrationType}/{partnerOrgId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto gegtFactoriesByType(@PathVariable("registrationType") String registrationType,@PathVariable("partnerOrgId") Long partnerOrgId){
		
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("registrationType", registrationType);
		params.put("partnerOrgId", partnerOrgId);
		List<PartnerOrgRegistrationDto> partnerOrgRegistrations = partnerOrgRegistrationService.findDtos("getPartnerOrgRegByType", params);
		CustomResponseDto response = new CustomResponseDto("partnerOrgRegistrations", partnerOrgRegistrations);
		return response;
		
	}

}
