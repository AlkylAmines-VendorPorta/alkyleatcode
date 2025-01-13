package com.novelerp.appbase.master.controller;

import java.util.HashMap;
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

import com.novelerp.appbase.master.dto.PartnerOrgRDAECDto;
import com.novelerp.appbase.master.service.PartnerOrgRDAECService;
import com.novelerp.appbase.master.service.PartnerOrgService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.util.CoreReferenceConstants;
/**
 * @author Aman
 *
 */
@Controller
public class PartnerOrgRDAECController {
	
	@Autowired
	private PartnerOrgRDAECService partnerOrgRDAECService;
	
	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@Autowired
	private PartnerOrgService partnerOrgService;
	@RequestMapping(value = "/getOrgRDAEC/{partnerOrgId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getPartnerOrgRDAEC(@PathVariable ("partnerOrgId") Long partnerOrgId){
	
		Map<String, Object> params =  AbstractContextServiceImpl.getParamMap("partnerOrgId", partnerOrgId); 
		List<PartnerOrgRDAECDto> partnerOrgRDAECs =  partnerOrgRDAECService.findDtos("getOrgRDAECQuery", params);
		Map<String, String> developingRegions = refListService.getReferenceListMap(CoreReferenceConstants.DEVELOPING_REGIONS);
		CustomResponseDto response = new CustomResponseDto("partnerOrgRDAECs",partnerOrgRDAECs);
		response.addObject("developingRegions", developingRegions);
		return response;
	}
	
	@RequestMapping(value="/saveOrgRDAEC", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)	
	public @ResponseBody PartnerOrgRDAECDto savePartnerOrgRDAEC(@ModelAttribute ("partnerOrgRDAEC") PartnerOrgRDAECDto dto){
		RoleDto role=contextService.getDefaultRole();
		if(role==null || role.getValue()==null)
		{   dto.setResponse(new ResponseDto(true, "Session Timeout...Please Login "));
			return dto;
		}
		if(role.getValue().equals(ContextConstant.USER_TYPE_PATNER_USER) || role.getValue().equals(ContextConstant.USER_TYPE_VENDOR_ADMIN) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue()))
		{
			dto.setIsEEApproved(null);
			dto.setIsCEApproved(null);
			dto.setEeComment(null);
			dto.setCeComment(null);
			if(dto.getPartnerOrg()!=null && dto.getPartnerOrg().getPartnerOrgId()!=null)
			{
			  Map<String, Object> map=AbstractServiceImpl.getParamMap("isEEApproved", null);
			  map.put("isCEApproved", null);
			  map.put("eeComment", null);
			  map.put("ceComment", null);
			  partnerOrgService.updateByJpql(map, "partnerOrgId", dto.getPartnerOrg().getPartnerOrgId());
			}
		}
		if(dto!=null && dto.getPartnerOrgRDAECId()!=null && (role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)||role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)))
		{
			Map<String, Object> map=new HashMap<>();
			if (dto.getIsEEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_EXECUTIVE_ENGINEER)) {
			  map.put("eeComment", dto.getEeComment());
			  map.put("isEEApproved", dto.getIsEEApproved());
			}else if (dto.getIsCEApproved()!=null && role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)) {
				  map.put("ceComment", dto.getCeComment());
				  map.put("isCEApproved", dto.getIsCEApproved());
			}
			int result= partnerOrgRDAECService.updateByJpql(map, "partnerOrgRDAECId", dto.getPartnerOrgRDAECId());
			if(result>0)
			{
				dto.setResponse(new ResponseDto(false, "Record Updated"));
				return dto;
			}else{
				dto.setResponse(new ResponseDto(true, "Error in Updated"));
				return dto;
			}
		}
		if(dto.getPartnerOrgRDAECId() == null){
			return partnerOrgRDAECService.save(dto);
		}
		return partnerOrgRDAECService.updateDto(dto);
	}
	

}
