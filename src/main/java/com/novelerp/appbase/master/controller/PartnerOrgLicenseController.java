package com.novelerp.appbase.master.controller;

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

import com.novelerp.appbase.master.converter.PartnerOrgLicenseConverter;
import com.novelerp.appbase.master.dto.PartnerOrgLicenseDto;
import com.novelerp.appbase.master.service.PartnerOrgLicenseService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;
/**
 * @author Vivek Birdi
 *
 */
@Controller
public class PartnerOrgLicenseController {
	
	@Autowired
	private PartnerOrgLicenseService partnerCompanyLicenseService;
	
	@Autowired
	private PartnerOrgLicenseConverter orgLicenseConverter;

	@Autowired
	private ReferenceListService refListService;
	
	@RequestMapping(value = "/getOrgLicense/{partnerOrgId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody CustomResponseDto getPartnerOrgLicense(@PathVariable("partnerOrgId") Long partnerOrgId){
		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("partnerOrgId", partnerOrgId);
		List<PartnerOrgLicenseDto> orgLincenses = partnerCompanyLicenseService.findDtos("getPartnerOrgLicenseQuery", params,orgLicenseConverter);
		Map<String, String> licenseTypes = refListService.getReferenceListMap(CoreReferenceConstants.FACTORY_LICENSE_TYPE);
		CustomResponseDto response = new CustomResponseDto("partnerOrgLincenses",orgLincenses);
		response.addObject("licenseTypes", licenseTypes);
		return  response;
	}
	
	@RequestMapping(value="/saveOrgLicense", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody PartnerOrgLicenseDto save(@ModelAttribute("partnerOrgLicense") PartnerOrgLicenseDto dto){
		if(dto.getPartnerOrgLicenceId() == null){
			return partnerCompanyLicenseService.save(dto);
		}
		return partnerCompanyLicenseService.updateDto(dto);
	}
	
	@RequestMapping(value="/deleteOrgLincense/{orgLincenseId}", method= RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto delete(@PathVariable("orgLincenseId") Long orgLicenseId){
		boolean deleted = partnerCompanyLicenseService.deleteById(orgLicenseId);
		if(deleted){
			return new ResponseDto(false, "Record Deleted");
		}
		return new ResponseDto(true,"Problem in deleting record");
	}
}
