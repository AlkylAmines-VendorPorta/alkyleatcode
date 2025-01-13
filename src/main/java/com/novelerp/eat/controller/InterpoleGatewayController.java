package com.novelerp.eat.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.service.PartnerOrgService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.dto.PaymentGatewayResponseDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.PaymentDetailService;
import com.novelerp.eat.service.PaymentGatewayResponseService;
import com.novelerp.eat.service.PaymentGatewayService;
import com.novelerp.eat.service.SMSService;

@Controller
public class InterpoleGatewayController {

	private static Logger log = LoggerFactory.getLogger(InterpoleGatewayController.class);

	@Autowired
	private PaymentGatewayService gatewayService;
	@Autowired
	private PaymentGatewayResponseService paymentGatewayResponseService;
	@Autowired
	private PaymentDetailService paymentDetailService;
	@Autowired
	private BidderService bidderService;
	@Autowired
	private PartnerOrgService partnerOrgService;
	@Autowired
	private SMSService smsService;
	
	@CrossOrigin
	@RequestMapping(value = "/interpoleResponse", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String gatewayResponse(HttpServletRequest request) {

		System.out.println("In interpole response");
		String message = request.getParameter("msg");
		System.out.println(message);
		PaymentGatewayResponseDto response = gatewayService.process(message);
		if (!response.getResponse().isHasError()) {
			    paymentGatewayResponseService.save(response);
			    Map<String, Object> param=AbstractServiceImpl.getParamMap("docNo", response.getDocNo());
				PaymentDetailDto payment=paymentDetailService.findDto("getPaymentDetailsByDocNumber", param);
				if(payment!=null){
					payment.setResponse(response.getResponse());
					payment.setPaymentGatewayStatus(response.getStatus());
					payment =paymentDetailService.updateOnlinePaymentStatus(payment);
					log.info(response.getStatus() +"#"+ payment.getPaymentType().getCode());
					if(payment.getPaymentType().getCode().equalsIgnoreCase(AppBaseConstant.TENDER_PURCHASE_FEE) 
							&& response.getStatus().equalsIgnoreCase(AppBaseConstant.INTERPOL_RESP_SUCCESS)){
						log.info("creating Bidder");
						bidderService.createBidderForOP(payment);
						
						Map<String, String> params = new HashMap<String, String>();
						params.put(AppBaseConstant.SMS_PARAMETER_1, (payment.getAmount().add(payment.getGst())).toString());
						params.put(AppBaseConstant.SMS_PARAMETER_2, "Tender%20Fee");
						params.put(AppBaseConstant.SMS_PARAMETER_3, payment.getTahdr().getTahdrCode());
						smsService.sendSMS(payment.getCreatedBy().getUserDetails().getMobileNo(), AppBaseConstant.SMS_TEMPLATE_TENDER_FEE , params);
					}
					if(response.getStatus().equalsIgnoreCase(AppBaseConstant.INTERPOL_RESP_SUCCESS) && payment.getPaymentType().getCode().equalsIgnoreCase(AppBaseConstant.EMD) ) {
						Map<String, String> params = new HashMap<String, String>();
						params.put(AppBaseConstant.SMS_PARAMETER_1, (payment.getAmount().add(payment.getGst())).toString());
						params.put(AppBaseConstant.SMS_PARAMETER_2, AppBaseConstant.EMD.replace(" " , "%20"));
						params.put(AppBaseConstant.SMS_PARAMETER_3, payment.getTahdr().getTahdrCode());
						smsService.sendSMS(payment.getCreatedBy().getUserDetails().getMobileNo(), AppBaseConstant.SMS_TEMPLATE_TENDER_FEE , params);
					}
					
					if(payment.getPaymentType().getCode().equalsIgnoreCase(AppBaseConstant.RENEWAL_FEE) &&
					response.getStatus()!=null && response.getStatus().equalsIgnoreCase(AppBaseConstant.PAYMENT_SUCCESS_STATUS))
					{
						partnerOrgService.updateOrgForRenewalPayment(payment.getPartnerOrg());
						Map<String, String> params = new HashMap<String, String>();
						params.put(AppBaseConstant.SMS_PARAMETER_1, (payment.getAmount().add(payment.getGst())).toString());
						params.put(AppBaseConstant.SMS_PARAMETER_2, "Renewal%20Fee");
						smsService.sendSMS(payment.getCreatedBy().getUserDetails().getMobileNo(), AppBaseConstant.SMS_TEMPLATE_PAYEMNT_RECEIVED , params);
					}
					if(response.getStatus().equalsIgnoreCase(AppBaseConstant.INTERPOL_RESP_SUCCESS) && payment.getPaymentType().getCode().equalsIgnoreCase(AppBaseConstant.REGISTRATION_FEE) ) {
						Map<String, String> params = new HashMap<String, String>();
						params.put(AppBaseConstant.SMS_PARAMETER_1, (payment.getAmount().add(payment.getGst())).toString());
						params.put(AppBaseConstant.SMS_PARAMETER_2, "Registration%20Fee");
						smsService.sendSMS(payment.getCreatedBy().getUserDetails().getMobileNo(), AppBaseConstant.SMS_TEMPLATE_PAYEMNT_RECEIVED , params);
					}
					
					
				}
			return response.getResponse().getMessage();
		}
		return response.getResponse().getMessage();
	}
}
