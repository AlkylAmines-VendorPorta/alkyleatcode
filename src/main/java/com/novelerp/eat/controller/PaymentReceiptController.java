package com.novelerp.eat.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novelerp.appbase.master.dto.PartnerCompanyAddressDto;
import com.novelerp.appbase.master.service.PartnerCompanyAddressService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.UserDetailsService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.service.PaymentReceiptService;

/**
 * 
 * @author Ankita
 *
 */
@Controller
public class PaymentReceiptController {

	@Autowired
	private PaymentReceiptService paymentReceiptService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private PartnerCompanyAddressService partnerCompanyAddressService;
	
	/*view and load payment receipt*/
	@RequestMapping(value = "/invoice", method = RequestMethod.GET)
	public ModelAndView tenderPurchaseView(@RequestParam("id") Long id,@RequestParam("paymentType") String paymentType) {
		ModelAndView model=new ModelAndView("invoice");
		model.addObject("id",id);	
		model.addObject("paymentType",paymentType);
		model.addObject("receiptUrl","getPaymentReceiptFromPaymentType/"+id+"/"+paymentType);
		return  model;
	}
	/*view and load payment receipt*/
	
	@RequestMapping(value = "/getPaymentReceiptFromPaymentType/{id}/{paymentType}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody CustomResponseDto getPaymentDetailForReceipt(@PathVariable("id") Long id,@PathVariable("paymentType") String paymentType) {
		CustomResponseDto response=new CustomResponseDto();
		PaymentDetailDto paymentDetail=paymentReceiptService.getPaymentDetailsByPaymentType(id, paymentType);
		UserDetailsDto userDetails = paymentDetail.getCreatedBy()==null?null:paymentDetail.getCreatedBy().getUserDetails();
		BPartnerDto partnerDto=paymentDetail.getPartner();
		if(userDetails!=null){
		Map<String, Object> param=AbstractServiceImpl.getParamMap("userDetailsId",userDetails.getUserDetailsId());
		UserDetailsDto userDetailsData=userDetailsService.findDto("getUserDetailsForPaymentReceipt", param);
		response.addObject("userDetailsData", userDetailsData);
		}
		Map<String, Object> map=AbstractServiceImpl.getParamMap("role",ContextConstant.USER_TYPE_SYSUSER);
		String today=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		
		UserDto internalUser=userService.findDto("getUserByRoleCode", map);
		PartnerCompanyAddressDto companyAddressDto=null;
		if(partnerDto!=null){
			companyAddressDto=partnerCompanyAddressService.findDto("getAddressQuery", AbstractServiceImpl.getParamMap("partnerId",partnerDto.getbPartnerId()));
		}
		
		response.addObject("paymentDetail", paymentDetail);
		response.addObject("internalUser", internalUser);
		
		response.addObject("companyAddressDto", companyAddressDto);
		response.addObject("today", today);
		return response;
	}
}
