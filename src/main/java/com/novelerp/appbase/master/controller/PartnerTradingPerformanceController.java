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
 * 
 * @author Varsha Patil
 *
 */
@Controller
public class PartnerTradingPerformanceController {
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
	
	@RequestMapping(value = "/getTraderPerformance/{partnerId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CustomResponseDto getTraderPerformance(@PathVariable ("partnerId") Long partnerId){
		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("partnerId", partnerId);
		List<PartnerOrgPerformanceDto> partnerOrgPerformances = partnerOrgPerformanceService.findDtos("getTraderPerformanceQuery",params);
		List<MaterialDto> materialList =  materialService.findDtos("getMaterialsForTraderPrfm", params);
		CustomResponseDto response=  new CustomResponseDto("partnerOrgPerformances", partnerOrgPerformances);
		List<UOMDto> uomlist=uomService.getUomList();
		response.addObject("uom",uomlist);
		response.addObject("materials",materialList);
		return response;
	}
	
	@RequestMapping(value="/saveTraderPerformance", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PartnerOrgPerformanceDto saveTraderPerformance(@ModelAttribute ("mOrgPerformance") PartnerOrgPerformanceDto dto){
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
		return partnerOrgPerformanceService.saveTraderPerformance(dto, role);
	}
	
	@RequestMapping(value="/deleteTraderPerformance/{performanceId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto deleteTraderPerformance(@PathVariable("performanceId") Long performanceId){
		boolean deleted =  partnerOrgPerformanceService.deleteById(performanceId);
		if(deleted){
			return new ResponseDto(false, "Record Deleted");
		}
		return new ResponseDto(true, "Problem in deleting record");
	}

}
