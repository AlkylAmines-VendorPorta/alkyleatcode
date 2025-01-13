package com.novelerp.appbase.master.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.dto.FinancialYearDto;
import com.novelerp.appbase.master.dto.PartnerFinancialAttachmentDto;
import com.novelerp.appbase.master.dto.PartnerFinancialAttachmentsRespDto;
import com.novelerp.appbase.master.service.FinancialYearService;
import com.novelerp.appbase.master.service.PartnerFinancialDetailsService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.util.CoreReferenceConstants;

/**
 * 
 * @author varsha
 *
 */
@Controller
@RequestMapping("/rest")
public class PartnerFinancialDetailsController {
    @Autowired
	private PartnerFinancialDetailsService partnerFinancialDetailsService;
    @Autowired
    private ReferenceListService referenceListService;
    @Autowired
    private FinancialYearService financialYearService;
    
	@RequestMapping(value="/getPartnerFinancialDetails/{bpartnerId}",method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPartnerFinacialDocs(@PathVariable("bpartnerId") Long partnerId)
	{
		Map<String, Object> params =  AbstractServiceImpl.getParamMap("partnerId", partnerId);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		Date lastYear=cal.getTime();
		cal.add(Calendar.YEAR,-1);
		Date lastTwoYear=cal.getTime();
		params.put("lastYear", lastYear);
		params.put("lastTwoYear", lastTwoYear);
		params.put("finacialType", AppBaseConstant.TURNOVER_DETAILS);
		List<PartnerFinancialAttachmentDto> financialAttachments=partnerFinancialDetailsService.findDtos("getPartnerFinancialDetailsQuery", params);
		Map<String, String> financialType=referenceListService.getReferenceListMap(CoreReferenceConstants.FINANCIAL_TYPE);
		List<FinancialYearDto> years=financialYearService.getYears();
		CustomResponseDto response =  new CustomResponseDto("financialAttachments",financialAttachments);
		response.addObject("financialType", financialType);
		response.addObject("years", years);
	    return response;
	}
	@RequestMapping(value="/savePartnerFinancialDetails",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PartnerFinancialAttachmentDto> saveFinancialDetails(@ModelAttribute("partnerFinancialAttachments") PartnerFinancialAttachmentsRespDto dtos)
	{
		List<PartnerFinancialAttachmentDto> financialAttchments=dtos.getFinancialAttachments();
		return partnerFinancialDetailsService.processFinancialAttachmentRequest(financialAttchments);
	}

}
