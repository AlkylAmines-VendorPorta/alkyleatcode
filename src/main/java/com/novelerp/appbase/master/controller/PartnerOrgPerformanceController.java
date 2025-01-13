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

import com.novelerp.appbase.master.dto.MaterialDto;
import com.novelerp.appbase.master.dto.PartnerOrgPerformanceDto;
import com.novelerp.appbase.master.dto.UOMDto;
import com.novelerp.appbase.master.service.MaterialService;
import com.novelerp.appbase.master.service.PartnerOrgPerformanceService;
import com.novelerp.appbase.master.service.UOMService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appbase.validator.PartnerOrgPerformanceValidator;
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
public class PartnerOrgPerformanceController {
	

	@Autowired
	private PartnerOrgPerformanceService partnerOrgPerformanceService;
    @Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@Autowired
	private PartnerOrgPerformanceValidator partnerOrgPerformanceValidator;
	@Autowired
	private UOMService uomService;
	@Autowired
	private MaterialService materialService;
	@RequestMapping(value = "/getOrgPerformance/{partnerOrgId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getPartnerOrgPerformance(@PathVariable ("partnerOrgId") Long partnerOrgId){
		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("partnerOrgId", partnerOrgId);
		List<PartnerOrgPerformanceDto> partnerOrgPerformances = partnerOrgPerformanceService.findDtos("getOrgPerformanceQuery",params);
		List<MaterialDto> materialList =  materialService.findDtos("getMaterialsForOrgPrfm",params);
		CustomResponseDto response=  new CustomResponseDto("partnerOrgPerformances", partnerOrgPerformances);
		List<UOMDto> uomlist=uomService.getUomList();
		response.addObject("uom",uomlist );
		response.addObject("materials", materialList);
		return response;
	}
	
	@RequestMapping(value="/savePartnerOrgPerformance", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PartnerOrgPerformanceDto savePartnerOrgPerformance(@ModelAttribute ("partnerOrgPerformance") PartnerOrgPerformanceDto dto){
		RoleDto role=contextService.getDefaultRole();
		if(role==null || role.getValue()==null)
		{   dto.setResponse(new ResponseDto(true, "Session Timeout...Please Login "));
			return dto;
		}
		if(role.getValue().equals(ContextConstant.USER_TYPE_PATNER_USER) || role.getValue().equals(ContextConstant.USER_TYPE_VENDOR_ADMIN) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue()))
		{
			Errors errors=new Errors();
			partnerOrgPerformanceValidator.validate(dto, errors);
			if(!CommonUtil.isCollectionEmpty(errors.getErrorList())){
				dto.setResponse(new ResponseDto(true, "Please check following issue: ", errors.getErrorList()));
				return dto;
			}
		}
		return partnerOrgPerformanceService.saveOrgPerformance(dto, role);
	}
	
	@RequestMapping(value="/deleteOrgPerformance/{partnerOrgPerformanceId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto delete(@PathVariable Long partnerOrgPerformanceId){
		boolean deleted =  partnerOrgPerformanceService.deleteById(partnerOrgPerformanceId);
		if(deleted){
			return new ResponseDto(false, "Record Deleted");
		}
		return new ResponseDto(true, "Problem in deleting record");
	}


}
