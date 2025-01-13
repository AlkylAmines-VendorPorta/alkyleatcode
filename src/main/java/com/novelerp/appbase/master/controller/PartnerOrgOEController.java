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

import com.novelerp.appbase.master.dto.PartnerOrgOEDto;
import com.novelerp.appbase.master.service.PartnerOrgOEService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appbase.validator.PartnerOrgOEValidator;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;
/**
 * @author Aman
 *
 */
@Controller
public class PartnerOrgOEController {
	
	@Autowired
	private PartnerOrgOEService partnerOrgOEService;
	@Autowired
	private ReferenceListService refListService;
	@Autowired
	private PartnerOrgOEValidator partnerOrgOEValidator;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@RequestMapping(value = "/getOrgOtherDetails/{partnerOrgId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody  CustomResponseDto getPartnerOrgOE(@PathVariable ("partnerOrgId") Long partnerOrgId){
		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("partnerOrgId", partnerOrgId);
		List<PartnerOrgOEDto> partnerOrgOEs =  partnerOrgOEService.findDtos("getOrgOEDetailQuery", params);
		Map<String, String> oeTypes = refListService.getReferenceListMap(CoreReferenceConstants.OE_TYPES);
		Map<String, String> validityTypes = refListService.getReferenceListMap(CoreReferenceConstants.VALIDITY_TYPE);
		CustomResponseDto response = new CustomResponseDto("partnerOrgOEs", partnerOrgOEs);
		response.addObject("oeTypes", oeTypes);
		response.addObject("validityTypes",validityTypes);
		return  response;
	}
	
	/*@RequestMapping(value="/saveOrgOtherDetails", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody List<PartnerOrgOEDto> savePartnerOrgOE(@ModelAttribute ("partnerOrgOE") ListOEResponseDto dtos){
		List<PartnerOrgOEDto> oeLists= dtos.getOeList();
		return partnerOrgOEService.processRequest(oeLists);
	}*/
	@RequestMapping(value="/saveOrgOtherDetails", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody PartnerOrgOEDto savePartnerOrgOE(@ModelAttribute("partnerOrgOE") PartnerOrgOEDto dto){
		RoleDto role=contextService.getDefaultRole();
		if(role==null || role.getValue()==null)
		{   dto.setResponse(new ResponseDto(true,"Session Timeout...Please Login"));
			return dto;
		}
		if(role.getValue().equals(ContextConstant.USER_TYPE_PATNER_USER) || role.getValue().equals(ContextConstant.USER_TYPE_VENDOR_ADMIN) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue()))
		{
			Errors errors=new Errors();
			partnerOrgOEValidator.validate(dto, errors);
			if(!CommonUtil.isCollectionEmpty(errors.getErrorList())){
			dto.setResponse(new ResponseDto(true,"Check Following Issues:",errors.getErrorList()));
			return dto;
			}		
		}
		return partnerOrgOEService.savePartnerOrgOE(dto,role);
	}
	@RequestMapping(value="/deleteOrgOE/{partnerOrgOEId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto delete(@PathVariable Long partnerOrgOEId){
		boolean deleted =  partnerOrgOEService.deleteById(partnerOrgOEId);
		if(deleted){
			return new ResponseDto(false, "Record Deleted");
		}
		return new ResponseDto(true, "Problem in deleting record");
	}
}
