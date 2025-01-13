package com.novelerp.appbase.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.dto.PartnerOrgPaymentDto;
import com.novelerp.appbase.master.dto.ResponseDto;
import com.novelerp.appbase.master.service.PartnerOrgPaymentService;
/**
 * @author Aman
 *
 */
@Controller
public class PartnerOrgPaymentController {

	@Autowired
	private PartnerOrgPaymentService partnerCompanyAddressService;
	
	@RequestMapping(value = "/getOrgPayment", method=RequestMethod.GET)
	public @ResponseBody PartnerOrgPaymentDto getPartnerOrgPayment(@RequestBody Long partnerId){
		return partnerCompanyAddressService.getPartnerOrgPayment(partnerId);
	}
	
	@RequestMapping(value="/saveOrgPayment", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody ResponseDto savePartnerOrgPayment(@RequestBody PartnerOrgPaymentDto dto){
		return partnerCompanyAddressService.updatePartnerOrgPayment(dto);
	}
}
