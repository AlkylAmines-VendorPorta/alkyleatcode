package com.novelerp.alkyl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.alkyl.component.BPartnerComponent;
import com.novelerp.alkyl.component.VendorProfileHistoryComponent;
import com.novelerp.alkyl.validator.VendorRegistrationValidator;
import com.novelerp.appbase.master.dto.PartnerCompanyAddressDto;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.UserDetailsService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;

@Controller
@RequestMapping("/rest")
public class CompanyContactController {
	
	@Autowired
	VendorRegistrationValidator validator;

	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private VendorProfileHistoryComponent vendorProfileComponent;
	
	@Autowired
	private BPartnerService partnerService;
	
	@Autowired
	private BPartnerComponent bPartnerComponent;
	
	@PostMapping(value="/getCompanyContactDetails/{partnerId}")
	public @ResponseBody CustomResponseDto getCompanyContactDetails(@PathVariable("partnerId") Long partnerId){
		CustomResponseDto resp = new CustomResponseDto();
		Map<String, String> salutationList = refListService.getReferenceListMap(CoreReferenceConstants.SALUTATION); 
		List<UserDetailsDto> contactDetailsList = userDetailService.findDtos("getUserDetailsQueryByBP", AbstractContextServiceImpl.getParamMap("bPartnerId", partnerId)); 
		resp.addObject("contactDetailsList", contactDetailsList);
		resp.addObject("salutationList", salutationList);
		return resp;
	}
	
	@PostMapping(value="/saveCompanyContactDetails")
	public @ResponseBody CustomResponseDto saveCompanyContactDetails(@RequestBody UserDetailsDto dto){
		CustomResponseDto resp = new CustomResponseDto(); 
		dto.setUserDetailType(AppBaseConstant.COMPANY_USER);
		Errors errors = new Errors();
		validator.validateCompanyContact(dto, errors);
		if(errors.getErrorCount()>0){
			dto.setResponse(new ResponseDto(true, errors.getErrorString()));
			resp.addObject("contactDetails", dto);
			return resp;
		}
		if(null!=dto.getUserDetailsId()){
			try{
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("userDetailsId", dto.getUserDetailsId());
				UserDetailsDto oldDto=userDetailService.findDto("getUserDetailsWithID", param);
				vendorProfileComponent.createCompanyConHistory(dto,oldDto);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		dto=userDetailService.saveCompanyContact(dto);
		// Update partial status of vendor
		BPartnerDto oldDto = partnerService.findDto("getQueryForPartnerDetail",
				AbstractContextServiceImpl.getParamMap("partnerId", dto.getPartner().getbPartnerId()));
		bPartnerComponent.setVendorPartialdetails(oldDto);
		partnerService.updateDto(oldDto);
		
		resp.addObject("contactDetails", dto);
		return resp;
	}
	
	@PostMapping(value="/deleteCompanyContactDetails/{userDetailId}")
	public @ResponseBody CustomResponseDto saveCompanyContactDetails(@PathVariable("userDetailId") Long userDetailsId){
		int result = userDetailService.updateByJpql(AbstractContextServiceImpl.getParamMap("isActive", "N"), 
				AbstractContextServiceImpl.getParamMap("userDetailsId", userDetailsId));
		UserDetailsDto userDetail = new UserDetailsDto();
		userDetail.setUserDetailsId(userDetailsId);
		if (result>0) {
			userDetail.setResponse(new ResponseDto(false, "Record deleted"));
		}else{
			userDetail.setResponse(new ResponseDto(true, "Problem in deleting record"));
		}
		return new CustomResponseDto("userDetail",userDetail);
	}
	
}
