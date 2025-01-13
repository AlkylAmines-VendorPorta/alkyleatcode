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

import com.novelerp.appbase.master.converter.PartnerOrgExperienceConverter;
import com.novelerp.appbase.master.dto.PartnerOrgExperienceDto;
import com.novelerp.appbase.master.service.PartnerOrgExperienceService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appbase.validator.PartnerOrgExperienceValidator;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
/**
 * @author Aman
 *
 */
@Controller
public class PartnerOrgExperienceController {

	@Autowired
	private PartnerOrgExperienceService partnerOrgExperienceService;
	@Autowired
	private PartnerOrgExperienceConverter orgExperienceConverter;
    @Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
    @Autowired
    private PartnerOrgExperienceValidator expValidator;
	@RequestMapping(value = "/getOrgExperience/{partnerOrgId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPartnerOrgExperience(@PathVariable("partnerOrgId") Long partnerOrgId){
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("partnerOrgId", partnerOrgId);
		List<PartnerOrgExperienceDto> partnerOrgExperiences = partnerOrgExperienceService.findDtos("getOrgExperienceQuery", params, orgExperienceConverter);
		CustomResponseDto response =  new CustomResponseDto();
		response.addObject("partnerOrgExperiences", partnerOrgExperiences);
		return response;
	}
	
	@RequestMapping(value="/saveOrgExperience", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody PartnerOrgExperienceDto savePartnerOrgExperience(@ModelAttribute ("partnerOrgExperience") PartnerOrgExperienceDto dto){
		RoleDto role=contextService.getDefaultRole();
		if(role==null || role.getValue()==null){
			dto.setResponse(new ResponseDto(true,"Session Time Out"));
		}
		if(ContextConstant.USER_TYPE_PATNER_USER.equals(role.getValue()) || role.getValue().equals(ContextConstant.USER_TYPE_VENDOR_ADMIN) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue()))
		{
			Errors errors=new Errors();
			expValidator.validate(dto, errors);
			if(!CommonUtil.isListEmpty(errors.getErrorList())){
				dto.setResponse(new ResponseDto(true,"Please check following issues:",errors.getErrorList()));
				return dto;
			}
		}
		return partnerOrgExperienceService.saveOrgExp(dto);	
	}
}
