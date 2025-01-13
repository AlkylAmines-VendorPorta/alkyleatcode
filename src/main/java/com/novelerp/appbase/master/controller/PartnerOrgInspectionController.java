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

import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.dto.PartnerOrgInspectionDto;
import com.novelerp.appbase.master.service.PartnerOrgInspectionService;
import com.novelerp.appbase.master.service.PartnerOrgService;
import com.novelerp.appbase.validator.PartnerOrgInspectionValidator;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;

/**
 * 
 * @author Varsha Patil
 *
 */
@Controller
public class PartnerOrgInspectionController {
	
	@Autowired
	private PartnerOrgInspectionService partnerOrgInspectionService;
	
	@Autowired
	private PartnerOrgInspectionValidator partnerOrgInspectionValidator;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private PartnerOrgService partnerOrgService;
	
	@RequestMapping(value = "/getOrgInspection/{partnerOrgId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getOrgInspection(@PathVariable ("partnerOrgId") Long partnerOrgId){
		Map<String, Object> params =  AbstractServiceImpl.getParamMap("partnerOrgId", partnerOrgId);
 		List<PartnerOrgInspectionDto> orgInspection = partnerOrgInspectionService.findDtos("getPartnerOrgInspection", params);
 		PartnerOrgDto partnerOrg=partnerOrgService.findDto(partnerOrgId);
 		CustomResponseDto response =  new CustomResponseDto("orgInspection",orgInspection);
 		response.addObject("partnerOrg", partnerOrg);
 		return response;
	}
	@RequestMapping(value = "/saveOrgInspection", method=RequestMethod.POST)
	public @ResponseBody PartnerOrgInspectionDto saveOrgInspection(@ModelAttribute ("partnerOrgInspection") PartnerOrgInspectionDto dto){
		Errors errors=new Errors();		
		partnerOrgInspectionValidator.validate(dto, errors);
		if(errors.getErrorCount()>0){
			ResponseDto response=new ResponseDto();
			response.setHasError(true);
			response.setErrors(errors.getErrorList());
			dto.setResponse(response);
			return dto;
		}
		
		RoleDto role=contextService.getDefaultRole();
		if(role==null || role.getValue()==null){
			dto.setResponse(new ResponseDto(true, "Session Timeout"));
			return dto;
		}
		
		if(dto!=null && dto.getOrgInspectionId()!=null && (role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)||role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)))
		{
			Map<String, Object> map=new HashMap<>();
			if (dto.getIsEEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)) {
			  map.put("otherItemsManufactured", dto.getOtherItemsManufactured());
			  map.put("eeComment", dto.getEeComment());
			  map.put("isEEApproved", dto.getIsEEApproved());
			}else if (dto.getIsCEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)) {
				  map.put("ceComment", dto.getCeComment());
				  map.put("isCEApproved", dto.getIsCEApproved());
			}
			int result= partnerOrgInspectionService.updateByJpql(map, "orgInspectionId", dto.getOrgInspectionId());
			if(result>0)
			{
				dto.setResponse(new ResponseDto(false, "Record Updated"));				
			}else{
				dto.setResponse(new ResponseDto(true, "Error in Updated"));
			}
		}
		if(dto.getOrgInspectionId()==null){
			dto= partnerOrgInspectionService.save(dto);
		}
		/*dto= partnerOrgInspectionService.updateDto(dto);*/
		
		/*if(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER.equals(roleName) && AppBaseConstant.CLARIFICATION_STATUS.equals(status)){
			UserDto user=userService.getUserByPartnerId(dto.getPartner().getbPartnerId());
			String email=user==null?"":user.getEmail()==null?"":user.getEmail();
		     String sub="Physical Verification Approval";
		     String message="<p>Hi</p><br><p>Your Factory's physical verification has not been approved. <p>";
		     bPartnerService.sendEmailToPartner(email, sub, message);
		}*/
		return dto;
	}
}
