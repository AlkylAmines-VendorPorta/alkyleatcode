package com.novelerp.appcontext.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.dto.PaymentTypeDto;
import com.novelerp.appbase.master.service.PartnerOrgService;
import com.novelerp.appbase.master.service.PaymentTypeService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.AppPropertyUtil;
import com.novelerp.eat.dto.PaymentDetailDto;
import com.novelerp.eat.dto.PaymentGatewayResponseDto;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.service.PaymentDetailService;
import com.novelerp.eat.service.PaymentGatewayResponseService;
import com.novelerp.eat.service.PaymentGatewayService;
import com.novelerp.eat.service.TAHDRDetailService;
import com.novelerp.eat.validator.TahdrPurchaseValidator;

@Controller
public class PaymentGatewayController {

	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	@Autowired
	private PaymentDetailService paymentDetailService;

	private static Logger log = LoggerFactory.getLogger(PaymentGatewayController.class);
	@Autowired
	PaymentGatewayService interpoleGatewayService;
	@Autowired
	private TAHDRDetailService tahdrDetailService;
	@Autowired
	private PaymentTypeService paymentTypeService;
	@Autowired
	private PaymentGatewayResponseService paymentGatewayResponseService;
	@Autowired
	private PartnerOrgService partnerOrgService;
	@Autowired
	private AppPropertyUtil propertyUtil;
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;
	
	@Autowired
	private TahdrPurchaseValidator tahdrPurchaseValidator;


	@RequestMapping(value = "/payOnline", method = RequestMethod.POST)
	public @ResponseBody String payOnline(@ModelAttribute("paymentDetail") PaymentDetailDto paymentDetail) {
		log.info("Payment processing started.");
		UserDto userDto = contextService.getUser();
		BPartnerDto bPartner = contextService.getPartner();
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("paymentTypeId",
				paymentDetail.getPaymentType().getPaymentTypeId());
		PaymentTypeDto paymentTypeDto = paymentTypeService.findDto("getPaymentTypeById", param);
		paymentDetail.setPaymentType(paymentTypeDto);
		if(paymentDetail.getVendorTypePayment()!=null && paymentDetail.getVendorTypePayment().equals(AppBaseConstant.MANUFACTURER_PAYMENT) 
				 &&  paymentTypeDto!=null && paymentTypeDto.getCode().equals(AppBaseConstant.RENEWAL_FEE) && paymentDetail.getPartnerOrg()!=null)
				{
					PartnerOrgDto partnerOrg=partnerOrgService.findDto(paymentDetail.getPartnerOrg().getPartnerOrgId());
					paymentDetail.setPartnerOrg(partnerOrg);
				}
		/*paymentDetail.setPaymentGatewayStatus(AppBaseConstant.PAYMENT_PENDING_STATUS);*/
		paymentDetail = paymentDetailService.savePartnerOrgOnlinePaymentDetail(paymentDetail);
		String msg = interpoleGatewayService.getRegistrationPaymentData(userDto, bPartner, paymentDetail.getDocNo());
		if (CommonUtil.isStringEmpty(msg)) {
			return "Try again";
		}
		/*String billDeskUrl = propertyUtil.getProperty("onlinepayment.billdesk.url");*/
		String billDeskUrl =sysConfiguratorService.getPropertyConfigurator("onlinepayment.billdesk.url");

		/*String systemid = propertyUtil.getProperty("onlinepayment.billdesk.systemid");*/
		String systemid =sysConfiguratorService.getPropertyConfigurator("onlinepayment.billdesk.systemid");

		return "<html><body><form  action='"+billDeskUrl+"' method='post'> <input type=hidden name= 'systemid' value='"+systemid+"'> <input type=hidden name='msg' value='"
				+ msg
				+ "'> <input type=submit name=submit value='Process Payment' id = 'loinForm'	> </form></body><script>debugger;document.getElementById('loinForm').click();</script></html>";
	}

	@RequestMapping(value = "/onlineEmdPayment", method = RequestMethod.POST)
	public @ResponseBody String saveEmdPaymentDetail(@ModelAttribute("paymentDetail") PaymentDetailDto paymentDetail) {

		TAHDRDetailDto td = tahdrDetailService.findDto("QueryForTAHDRDetailByTahdrId",
				AbstractContextServiceImpl.getParamMap("tahdrId", paymentDetail.getTahdr().getTahdrId()));

		UserDto userDto = contextService.getUser();
		BPartnerDto bPartner = contextService.getPartner();

		paymentDetail = paymentDetailService.emdPayment(paymentDetail);
		paymentDetail = paymentDetailService.saveOnlineEmdPaymentDetail(paymentDetail);
		String msg = interpoleGatewayService.getEmdPaymentData(userDto, td, bPartner, paymentDetail.getDocNo());

		if (CommonUtil.isStringEmpty(msg)) {
			return "Try again";
		}
		String billDeskUrl =sysConfiguratorService.getPropertyConfigurator("onlinepayment.billdesk.url");
        String systemid =sysConfiguratorService.getPropertyConfigurator("onlinepayment.billdesk.systemid");


		return "<html><body><form  action='"+billDeskUrl+"' method='post'> <input type=hidden name= 'systemid' value='"+systemid+"'> <input type=hidden name='msg' value='"
				+ msg
				+ "'> <input type=submit name=submit value='Process Payment' id = 'loinForm'	> </form></body><script>debugger;document.getElementById('loinForm').click();</script></html>";
	}

	@RequestMapping(value = "/paymentResponse", method = RequestMethod.GET)
	public String paymentResponse(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("requestID", request.getParameter("request_id"));
		return "paymentgateway";
	}

	@RequestMapping(value = "/onlineTenderFeePayment", method = RequestMethod.POST)
	public @ResponseBody String saveTenderFeesPaymentDetail(@ModelAttribute("paymentDetail") PaymentDetailDto payment) {

		TAHDRDetailDto td = tahdrDetailService.findDto("QueryForTAHDRDetailByTahdrId",
				AbstractContextServiceImpl.getParamMap("tahdrId", payment.getTahdr().getTahdrId()));

		UserDto userDto = contextService.getUser();
		BPartnerDto bPartner = contextService.getPartner();
		PaymentDetailDto paymentDetail = new PaymentDetailDto();
		/*BidderDto bidderDto = new BidderDto();*/
		try {
			
			paymentDetail = paymentDetailService.purchaseTahdrDoc(payment);
			if(payment.getPartnerOrg()==null || payment.getPartnerOrg().getPartnerOrgId()==null){
				paymentDetail.setPartnerOrg(null);
			}else{
				paymentDetail.setPartnerOrg(payment.getPartnerOrg());
			}
			
			Errors errors =  new Errors();
			tahdrPurchaseValidator.validate(paymentDetail, errors);
			if(errors.getErrorCount()>0){
				return "<html><body style='text-align:center;'>"
						+ "<b>Purchase is not allowed. No item in the tender exist in selected factory.</b>"
						+ "</body></html>;";
			}
			
			paymentDetail=paymentDetailService.save(paymentDetail);
			/*bidderDto = bidderService.createBidder(paymentDetail);*/
			if (payment.getPaymentMode().equals("OP")) {
				long paymentDetailId = paymentDetail.getPaymentDetailId();
				long requestID = paymentDetailId + 1200000000;
				Map<String, Object> map = new HashMap<>();
				map.put("docNo", requestID);
				paymentDetailService.updateByJpql(map, "paymentDetailId", paymentDetailId);
				paymentDetail.setDocNo(requestID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String msg = interpoleGatewayService.getTahdrFeesPaymentData(userDto, td, bPartner,
				paymentDetail);

		if (CommonUtil.isStringEmpty(msg)) {
			return "Try again";
		}
		String billDeskUrl =sysConfiguratorService.getPropertyConfigurator("onlinepayment.billdesk.url");
        String systemid =sysConfiguratorService.getPropertyConfigurator("onlinepayment.billdesk.systemid");


		return "<html><body><form  action='"+billDeskUrl+"' method='post'> <input type=hidden name= 'systemid' value='"+systemid+"'> <input type=hidden name='msg' value='"
				+ msg
				+ "'> <input type=submit name=submit value='Process Payment' id = 'loinForm'	> </form></body><script>debugger;document.getElementById('loinForm').click();</script></html>";
	}

	@RequestMapping(value = "/paymentResponseStatus", method = RequestMethod.GET)
	public @ResponseBody CustomResponseDto paymentResponseStatus(@RequestParam("doc_no")String docNo) {
		/*String docNo = request.getParameter("doc_no");*/
		CustomResponseDto customResponseDto = new CustomResponseDto();
		PaymentGatewayResponseDto gatewayResponse = paymentGatewayResponseService.getPaymentGatewayResponseByDocNo(docNo);
		PaymentDetailDto paymentDetailDto = paymentDetailService.getPaymentDetailsByDocNO(docNo);
		if (gatewayResponse != null) {
			long paymentDetailId = paymentDetailDto.getPaymentDetailId();
			String status = gatewayResponse.getStatus();
			paymentDetailService.updatePaymentStatus(paymentDetailId, status);
		}
		customResponseDto.addObject("paymentGatewayResponseDto", gatewayResponse);
		customResponseDto.addObject("paymentDetailDto", paymentDetailDto);
		return customResponseDto;
	}

}
