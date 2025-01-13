package com.novelerp.appbase.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.dto.PartnerOrgFinancialDto;
import com.novelerp.appbase.master.dto.ResponseDto;
import com.novelerp.appbase.master.service.PartnerOrgFinancialService;
/**
 * @author Aman
 *
 */
@Controller
public class PartnerOrgFinancialController {
	
	@Autowired
	private PartnerOrgFinancialService partnerCompanyAddressService;
	
	@RequestMapping(value = "/getOrgFinancial", method=RequestMethod.GET)
	public @ResponseBody PartnerOrgFinancialDto getPartnerOrgFinancial(@RequestBody Long partnerId){
		return partnerCompanyAddressService.getPartnerOrgFinancial(partnerId);
	}
	
	@RequestMapping(value="/saveOrgFinancial", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody ResponseDto savePartnerOrgFinancial(@RequestBody PartnerOrgFinancialDto dto){
		return partnerCompanyAddressService.updatePartnerOrgFinancial(dto);
	}
	

}
