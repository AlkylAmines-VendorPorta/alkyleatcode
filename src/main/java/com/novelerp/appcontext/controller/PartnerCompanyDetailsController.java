package com.novelerp.appcontext.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.dto.ResponseDto;
import com.novelerp.appcontext.dto.PartnerCompanyDetailsDto;
import com.novelerp.appcontext.service.PartnerCompanyDetailsService;
/**
 * @author Aman
 *
 */
@Controller
public class PartnerCompanyDetailsController {
	
	@Autowired
	private PartnerCompanyDetailsService partnerCompanyDetailsService;
	
	@RequestMapping(value = "/getCompanyDetails", method=RequestMethod.GET)
	public @ResponseBody PartnerCompanyDetailsDto getPartnerCompanyDetails(@RequestBody Long partnerId){
		return partnerCompanyDetailsService.getPartnerCompanyDetails(partnerId);
	}
	
	@RequestMapping(value="/saveCompanyDetails", method=RequestMethod.POST, produces="application/json" ,consumes="application/json")
	public @ResponseBody ResponseDto savePartnerCompanyDetails(@RequestBody PartnerCompanyDetailsDto dto){
		System.out.println("PartnerCompanyDetailsController-savePartnerCompanyDetails().."+dto.getCompanyName());
		ResponseDto response=new ResponseDto();
		
		/*return partnerCompanyDetailsService.updatePartnerCompanyDetails(dto);*/
		return response;
	}
	

}
