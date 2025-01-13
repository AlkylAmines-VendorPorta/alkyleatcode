package com.novelerp.eat.controller;

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

import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.dto.PaymentTypeDto;
import com.novelerp.appbase.master.service.PartnerOrgService;
import com.novelerp.appbase.master.service.PaymentTypeService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.service.PaymentDetailService;
import com.novelerp.eat.validator.EMDPaymentValidator;
import com.novelerp.eat.validator.PaymentDetailValidator;

/**
 * 
 * @author varsha
 *
 */
@Controller
public class PaymentDetailController {
	@Autowired
	private PaymentDetailService paymentDetailService;
    @Autowired
	private PaymentTypeService paymentTypeService;
	@Autowired
	private BPartnerService partnerService;
	@Autowired
	private PartnerOrgService partnerOrgService;
	@Autowired
	private PaymentDetailValidator paymentDetailValidator;
	@Autowired
	private EMDPaymentValidator emdPaymentValidator;
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@RequestMapping(value = "/getPaymentDetails/{partnerId}", method = RequestMethod.POST)
	public @ResponseBody CustomResponseDto getOrgPaymentDetails(@PathVariable("partnerId") Long partnerId) {

		Map<String,Object> param=AbstractServiceImpl.getParamMap("partnerId", partnerId);
		List<PaymentDetailDto> paymentDetails = paymentDetailService.getPaymenDetails(partnerId);
		List<PaymentTypeDto> paymentType = paymentTypeService.getPaymentTypeForPartner();
		List<PartnerOrgDto> partnerOrg = partnerOrgService.findDtos("getOrgWithValidDate", param);
		BPartnerDto partner = partnerService.findDto(partnerId);
		CustomResponseDto response = new CustomResponseDto("paymentDetails", paymentDetails);
		response.addObject("paymentType", paymentType);
		response.addObject("partner", partner);
		response.addObject("partnerOrg", partnerOrg);
		return response;
	}

	@RequestMapping(value = "/saveOrgPaymentDetails", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody PaymentDetailDto savePartnerOrgPaymentDetail(@ModelAttribute("paymentDetail") PaymentDetailDto dto) {
	
		RoleDto role=contextService.getDefaultRole();
		if(role==null || role.getValue()==null){
			dto.setResponse(new ResponseDto(true, "Session Time Out..Please login"));
			return dto;
		}
		Map<String, Object> param = AbstractServiceImpl.getParamMap("paymentTypeId", dto.getPaymentType().getPaymentTypeId());
		PaymentTypeDto paymentType=paymentTypeService.findDto("getPaymentTypeById", param);
		dto.setPaymentType(paymentType);
		if(ContextConstant.USER_TYPE_PATNER_USER.equals(role.getValue()) || ContextConstant.USER_TYPE_VENDOR_ADMIN.equals(role.getValue()) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue())){
			Errors errors=new Errors();
			BPartnerDto partner=partnerService.findDto(dto.getPartner().getbPartnerId());
			dto.setPartner(partner);
			paymentDetailValidator.checkPaymentType(dto, errors);
			if(!CommonUtil.isCollectionEmpty(errors.getErrorList())){
				dto.setResponse(new ResponseDto(true, "Please Check Following Issues: ", errors.getErrorList()));
				return dto;
			}
		}
		
		if(dto.getVendorTypePayment()!=null && dto.getVendorTypePayment().equals(AppBaseConstant.MANUFACTURER_PAYMENT) 
		 &&  paymentType!=null && paymentType.getCode().equals(AppBaseConstant.RENEWAL_FEE) && dto.getPartnerOrg()!=null)
		{
			PartnerOrgDto partnerOrg=partnerOrgService.findDto(dto.getPartnerOrg().getPartnerOrgId());
			dto.setPartnerOrg(partnerOrg);
		}
        return paymentDetailService.savePaymentDetail(dto);
	}

	@RequestMapping(value = "/saveEmdPaymentDetail", method = RequestMethod.POST)
	public @ResponseBody PaymentDetailDto saveEmdPaymentDetail(@ModelAttribute("paymentDetail") PaymentDetailDto paymentDetail) {
		paymentDetail = paymentDetailService.emdPayment(paymentDetail);
		Errors errors =  new Errors();
		ResponseDto response=new ResponseDto();
		emdPaymentValidator.validate(paymentDetail, errors);
		if(errors.getErrorCount()>0){
			response.setHasError(true);
			response.setErrors(errors.getErrorList());
			paymentDetail.setResponse(response);
		}else{
		paymentDetail=paymentDetailService.purchaseTahdrDoc(paymentDetail,AppBaseConstant.EMD);
		paymentDetail = paymentDetailService.save(paymentDetail);
		}
		return paymentDetail;
	}

	@RequestMapping(value = "/deletePaymentDetail/{paymentId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto delete(@PathVariable("paymentId") Long paymentId) {
		boolean deleted = paymentDetailService.deleteById(paymentId);
		if (deleted) {
			return new ResponseDto(false, "Record Deleted");
		}
		return new ResponseDto(true, "Problem in deleting record;");
	}

	@RequestMapping(value = "/testPagination", method = RequestMethod.GET)
	public void TestPagination() {
		List<PaymentDetailDto> list = paymentDetailService.findDtos(null, null, 1, 10, "paymentDetailId");
		List<PaymentDetailDto> list2 = paymentDetailService.findDtos(null, null, 2, 10, "paymentDetailId");
		System.out.println("Total count "+paymentDetailService.getRecordCount());
		for(PaymentDetailDto detailDto :list){
			System.out.println(detailDto.getPaymentDetailId());
		}
		for(PaymentDetailDto detailDto :list2){
			System.out.println(detailDto.getPaymentDetailId());
		}
	}
	@RequestMapping(value = "/validateOnlinePayment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PaymentDetailDto delete(@ModelAttribute()PaymentDetailDto dto) {
		RoleDto role=contextService.getDefaultRole();
		Map<String, Object> param = AbstractServiceImpl.getParamMap("paymentTypeId", dto.getPaymentType().getPaymentTypeId());
		PaymentTypeDto paymentType=paymentTypeService.findDto("getPaymentTypeById", param);
		dto.setPaymentType(paymentType);
		if(ContextConstant.USER_TYPE_PATNER_USER.equals(role.getValue()) || ContextConstant.USER_TYPE_VENDOR_ADMIN.equals(role.getValue()) || AppBaseConstant.ROLE_INFRA_ADMIN.equals(role.getValue())){
			Errors errors=new Errors();
			BPartnerDto partner=partnerService.findDto(dto.getPartner().getbPartnerId());
			dto.setPartner(partner);
			paymentDetailValidator.checkPaymentType(dto, errors);
			if(!CommonUtil.isCollectionEmpty(errors.getErrorList())){
				dto.setResponse(new ResponseDto(true, "Please Check Following Issues: ", errors.getErrorList()));
				return dto;
			}else{
				dto.setResponse(new ResponseDto(false, "Validate Successfully !"));
			}
		}else{
		   dto.setResponse(new ResponseDto(true, "Invalid Role Found"));
		}
		return dto;
	}
}
