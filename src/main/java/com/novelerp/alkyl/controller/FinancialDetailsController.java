package com.novelerp.alkyl.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.alkyl.component.FinancialDetailsComponent;
import com.novelerp.alkyl.dto.FinancialDetailsDto;
import com.novelerp.alkyl.validator.VendorRegistrationValidator;
import com.novelerp.appbase.master.dto.FinancialYearDto;
import com.novelerp.appbase.master.dto.PartnerFinancialAttachmentDto;
import com.novelerp.appbase.master.service.FinancialYearService;
import com.novelerp.appbase.master.service.PartnerFinancialDetailsService;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;

@Controller
@RequestMapping("/rest")
public class FinancialDetailsController {
	
	@Autowired
	private FinancialDetailsComponent fdComponent;
	
	@Autowired
	private PartnerFinancialDetailsService partnerFinancialDetailsService;
    
    @Autowired
    private FinancialYearService financialYearService;
	
    @Autowired
    private VendorRegistrationValidator validator;
    
	@PostMapping(value="/saveFinancialDetails")
	public @ResponseBody CustomResponseDto saveFinancialDetails(@RequestBody FinancialDetailsDto dto){
		CustomResponseDto resp = new CustomResponseDto();
		Errors errors = new Errors();
		//validator.validateFinancialDetails(dto, errors);
		if(errors.getErrorCount()>0){
			dto.setResponse(new ResponseDto(true, errors.getErrorString()));
			resp.addObject("financialDetails", dto);
			return resp;
		}
		resp.addObject("financialDetails", fdComponent.saveFinancialDetails(dto));
		return resp;
	}
	
//	@PostMapping("/getFinancialDetails/{partnerId}")
//	public @ResponseBody CustomResponseDto getFinancialDetails(@PathVariable("partnerId") Long partnerId){
//		Map<String, Object> params =  AbstractServiceImpl.getParamMap("partnerId", partnerId);
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.YEAR, -1);
//		Date lastYear=cal.getTime();
//		cal.add(Calendar.YEAR,-1);
//		Date lastTwoYear=cal.getTime();
//		params.put("lastYear", lastYear);
//		params.put("lastTwoYear", lastTwoYear);
//		params.put("finacialType", AppBaseConstant.TURNOVER_DETAILS);
//		List<PartnerFinancialAttachmentDto> financialAttachments=partnerFinancialDetailsService.findDtos("getFinancialDetailsQuery", params);
//		List<FinancialYearDto> years=financialYearService.getYears();
//		PartnerFinancialAttachmentDto years=partnerFinancialDetailsService.findDto("getFinancialYear", params);
//		CustomResponseDto response =  new CustomResponseDto("financialAttachments",fdComponent.setFinancialDetailsDtoFromList(financialAttachments));
//		response.addObject("years", years);
//		
//	    return response;
//	}
//}
@PostMapping("/getFinancialDetails/{partnerId}")
public @ResponseBody CustomResponseDto getFinancialDetails(@PathVariable("partnerId") Long partnerId){
	Map<String, Object> params =  AbstractServiceImpl.getParamMap("partnerId", partnerId);
	List<PartnerFinancialAttachmentDto> financialAttachments=partnerFinancialDetailsService.findDtos("getFinancialDetailsQuery", params);
	CustomResponseDto response =  new CustomResponseDto("financialAttachments",fdComponent.setFinancialDetailsDtoFromList(financialAttachments));
    return response;
}
}
