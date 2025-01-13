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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.service.PartnerOrgService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appbase.validator.PartnerOrgValidator;
import com.novelerp.appcontext.dto.CountryDto;
import com.novelerp.appcontext.dto.DistrictDto;
import com.novelerp.appcontext.dto.RegionDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.CountryService;
import com.novelerp.appcontext.service.DistrictService;
import com.novelerp.appcontext.service.RegionService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.service.PaymentDetailService;

/**
 * @author Vivek Birdi
 *
 */
@Controller

public class PartnerOrgController {
	
	@Autowired
	private PartnerOrgService partnerOrgService;
		
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private RegionService regionService;
	
	@Autowired
	private DistrictService districtService;
	@Autowired
	private PaymentDetailService paymentDetailService;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private PartnerOrgValidator orgValidator;
	@RequestMapping(value = "/getOrgDetails/{partnerId}", method=RequestMethod.POST)
	public @ResponseBody CustomResponseDto getPartnerOrg(@PathVariable ("partnerId") Long partnerId){
		
		
		Map<String, Object> params =  AbstractServiceImpl.getParamMap("partnerId", partnerId);
		params.put("paymentStatus", AppBaseConstant.PAYMENT_SUCCESS_STATUS);
		List<PartnerOrgDto> orgs = partnerOrgService.getPartnerOrgs(partnerId);
		Map<String, Object> param= new HashMap<>();
 		List<CountryDto> countries= countryService.findDtos("getCountryQuery", param);
 		List<RegionDto> regions = regionService.findDtos("getRegionQuery", param);
 		List<DistrictDto> districts =  districtService.findDtos("getDistrictQuery", param);

 		List<PaymentDetailDto> paymentDetail=paymentDetailService.findDtos("getPaymentsForOrg", params);
 		CustomResponseDto response =  new CustomResponseDto("orgs",orgs);
 		response.addObject("countries", countries);
 		response.addObject("regions", regions);
 		response.addObject("districts",districts);
 		response.addObject("paymentDetail",paymentDetail);
 		
		return response;
	}
	
	@RequestMapping(value="/saveOrgDetails", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody PartnerOrgDto savePartnerOrg(@RequestBody PartnerOrgDto dto){
		
		Errors errors =  new Errors();
		ResponseDto response=new ResponseDto();
		RoleDto role=contextService.getDefaultRole();
		if(role==null || role.getValue()==null){
			dto.setResponse(new ResponseDto(true,"Session Timeout...Please Login"));
			return dto;
		}
		orgValidator.validate(dto, errors);
		if(errors.getErrorCount()>0){
			response.setHasError(true);
			response.setErrors(errors.getErrorList());
			dto.setResponse(response);
			return dto;
		}
		if(role.getValue().equals(ContextConstant.USER_TYPE_CHIEF_ENGINEER)){
		if( dto.getPaymentDetail()!=null && dto.getPaymentDetail().getPaymentDetailId()!=null){
		Map<String, Object> map=AbstractServiceImpl.getParamMap("paymentDetailId", dto.getPaymentDetail().getPaymentDetailId());
		PaymentDetailDto payment=paymentDetailService.findDto("getPaymentById",map);
				if (payment != null) {
					dto.setPaymentDetail(payment);
				}
		}
		if(dto.getPartnerOrgId()!=null){
		  PartnerOrgDto org=partnerOrgService.findDto(dto.getPartnerOrgId());
		  dto.setValidTo(org.getValidTo());
		  dto.setIsRenewed(org.getIsRenewed());
		}
		}
		dto=partnerOrgService.savePartnerOrg(dto);
		paymentDetailService.updatePaymentDetail(dto.getPartnerOrgId(), dto.getPaymentDetail().getPaymentDetailId());
		return dto;
	}
	
	
	@RequestMapping(value="/deleteOrg/{orgId}/{paymentId}", method= RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto delete(@PathVariable("orgId") Long orgId,@PathVariable("paymentId") Long paymentId){		
		return partnerOrgService.deleteOrg(orgId, paymentId);
	}
	
}
