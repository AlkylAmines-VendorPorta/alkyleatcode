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

import com.novelerp.appbase.master.dto.PartnerOrgCertificationDto;
import com.novelerp.appbase.master.service.PartnerOrgCertificationService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appbase.validator.PartnerOrgCertificationValidator;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
/**
 * @author Aman/ Vivek Birdi
 *
 */
@Controller
public class PartnerOrgCertificationController {
	
	@Autowired
	private PartnerOrgCertificationService partnerOrgCertificationService;
	@Autowired
	
     @Qualifier("jwtUserContext") private ContextService contextService;
	@Autowired
	private PartnerOrgCertificationValidator partnerOrgCertificationValidator;
	
	@RequestMapping(value = "/getOrgCertificate/{partnerOrgId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getPartnerOrgCertification(@PathVariable ("partnerOrgId") Long partnerOrgId){
		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("partnerOrgId", partnerOrgId);
		List<PartnerOrgCertificationDto> partnerOrgCertifications = partnerOrgCertificationService.findDtos("getOrgCertificateQuery", params);
		CustomResponseDto response = new CustomResponseDto("partnerOrgCertifications",partnerOrgCertifications);
		return response;
	}
	
	@RequestMapping(value="/saveOrgCertificate", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PartnerOrgCertificationDto savePartnerOrgCertification(@ModelAttribute("partnerOrgCertification") PartnerOrgCertificationDto dto){
	    RoleDto role=contextService.getDefaultRole();
		if(role==null || role.getValue()==null)
		{   dto.setResponse(new ResponseDto(true, "Session Timeout...Please Login "));
			return dto;
		}
		if(role.getValue().equals(ContextConstant.USER_TYPE_PATNER_USER) || role.getValue().equals(ContextConstant.USER_TYPE_VENDOR_ADMIN) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue()))
		{
		  Errors errors=new Errors();
		  partnerOrgCertificationValidator.validate(dto, errors);
		  if(!CommonUtil.isCollectionEmpty(errors.getErrorList())){
			  ResponseDto response=new ResponseDto();
			  response.setHasError(true);
			  response.setMessage("Check Following Issues: ");
			  response.setErrors(errors.getErrorList());
			  dto.setResponse(response);
			  return dto;
		  }
		}
		return partnerOrgCertificationService.saveOrgCert(dto,role);	
	}
}
