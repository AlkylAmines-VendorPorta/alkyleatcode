package com.novelerp.alkyl.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelerp.alkyl.component.BPartnerComponent;
import com.novelerp.alkyl.dto.ASNReadDto;
import com.novelerp.alkyl.dto.AdvancePaymentDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.PaymentReadDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.service.AdvancePaymentService;
import com.novelerp.alkyl.service.AdvanceShipmentNoticeService;
import com.novelerp.alkyl.validator.AdvancePaymentValidator;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.entity.User;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CommonDto;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.PriceBidDto;

@Controller
@RequestMapping("/rest")
public class AdvancePaymentController {
	
	@Autowired
	private AdvancePaymentService advanceService;
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	
	@Autowired 
	private AdvancePaymentValidator paymentValidator;
	
	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	private BPartnerComponent partnerComponent;
	
	@Autowired
	private BPartnerService partnerService;
	
	@PostMapping(value = "/saveadvancePayment")
	public @ResponseBody CustomResponseDto saveadvancePayment(@RequestBody List<AdvancePaymentDto> advance) {
		
		try {
			
			Errors errors = new Errors();
			CustomResponseDto respN = new CustomResponseDto();
			paymentValidator.validatePayment(advance, errors);
		
			if(errors.getErrorCount()>0){
				return new CustomResponseDto(false, errors.getErrorString());
			}
			
			
			advance=advanceService.saveadvancepayment(advance);
			advanceService.sendMailforApproval(advance);
			//advanceService.sendProcessedDatatoSAP(advance);
			
			CustomResponseDto resp =new CustomResponseDto(true, "Mail Submitted SuccessFully");
			return resp;
					
				}catch(Exception ex){
					ex.printStackTrace();
					return new CustomResponseDto(false, ex.getMessage());
					//return new CustomResponseDto(false, "Error While Submitting");
				}
				
				
		
			}
			
			

	@PostMapping(value = "/getAlladvancePayment")
	public @ResponseBody CustomResponseDto getAlladvancePayment() {
		
		List<AdvancePaymentDto> allpayList = null;
		String role =contextService.getDefaultRole().getValue();
		BPartnerDto partner = contextService.getPartner();
		
		CustomResponseDto resp =new CustomResponseDto();
		Map<String, Object> params = new HashMap<String, Object>(); 
		List<String> param = new ArrayList<>();
		if(role.equals(AppBaseConstant.ROLE_VENDOR_ADMIN)){
			param.add(AppBaseConstant.EARLY_PAYMENT_STATUS_IN_PROGRESS);
			param.add(AppBaseConstant.EARLY_PAYMENT_STATUS_APPROVED);
			param.add(AppBaseConstant.EARLY_PAYMENT_STATUS_REJECTED);
			params.put("status", param);
			params.put("partnerId", partner.getbPartnerId());
			 allpayList=advanceService.findDtos("getAllpaymentbyPartner",params);
		
		}else {
			param.add(AppBaseConstant.EARLY_PAYMENT_STATUS_IN_PROGRESS);
			params.put("status", param);
			allpayList=advanceService.findDtos("getAllpaymentdetails",params);
		  //  params.put("status", AppBaseConstant.EARLY_PAYMENT_STATUS_IN_PROGRESS);
		}
		
		
		resp.addObject("getVendorPayListforApproval", allpayList);
		
		return resp;
		
		
	}
	
	
	@PostMapping(value = "/approveAdvancePayment")
	public @ResponseBody CustomResponseDto approveAdvancePayment(@RequestBody List<AdvancePaymentDto> advance) {
		
		try{
			
			
			Errors errors = new Errors();
			CustomResponseDto resp = new CustomResponseDto();
			paymentValidator.validateApprovalPayment(advance, errors);
			if (errors.getErrorCount() > 0) {
				resp.setSuccess(false);
				resp.setMessage(errors.getErrorString());
				return resp;

			}else {
			
			int result=0;
			
			CustomResponseDto mailDto=null;
			
			mailDto=advanceService.sendApprovedMailtoVendor(advance);
			
			if(mailDto.getMessage().equals("Mail Sucessfully Sent")) { 
			for(AdvancePaymentDto newadvance:advance) {
				Map<String, Object> whereCls = AbstractContextServiceImpl.getParamMap("advancePaymentId", newadvance.getAdvancePaymentId());
				Map<String, Object> params = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.EARLY_PAYMENT_STATUS_APPROVED);
				User approvedBy= new User();
				approvedBy.setUserId(contextService.getUser().getUserId());
				params.put("approvedBy", approvedBy);
				params.put("approvedDate", new Date());
				result = advanceService.updateByJpql(params, whereCls);	
			}
		
			if(result>0){
				
				
				resp.setSuccess(true);
				resp.setMessage("Payment Approved");
				resp.addObject("status", AppBaseConstant.EARLY_PAYMENT_STATUS_APPROVED);
				return resp;
			}else{
				return new CustomResponseDto(false, "Error while approving Payment.");
			}
			}else {
				return new CustomResponseDto(false, "Error while sending mail.");
			}
			}
		}catch (Exception e) {
			return new CustomResponseDto(false, e.getMessage());
		}
		
		
	}
	
	
	@PostMapping(value = "/approveAdvancePaymentold/{advancePaymentId}")
	public @ResponseBody CustomResponseDto approveAdvancePaymentold(@PathVariable ("advancePaymentId") Long advancePaymentId) {
		
		try{
			Map<String, Object> whereCls = AbstractContextServiceImpl.getParamMap("advancePaymentId", advancePaymentId);
			Map<String, Object> params = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.EARLY_PAYMENT_STATUS_APPROVED);

			User approvedBy= new User();
			approvedBy.setUserId(contextService.getUser().getUserId());
			params.put("approvedBy", approvedBy);
			params.put("approvedDate", new Date());
//			params.put("interestRate", interestRate);
			int result = advanceService.updateByJpql(params, whereCls);
			if(result>0){
				
				List<AdvancePaymentDto> payment=advanceService.findDtos("getpaymentbyadvancePaymentId",AbstractContextServiceImpl.getParamMap("advancePaymentId", advancePaymentId));
			
				advanceService.sendApprovedMailtoVendor(payment);
				
				CustomResponseDto resp =new CustomResponseDto(true, "Payment Approved");
				resp.addObject("status", AppBaseConstant.EARLY_PAYMENT_STATUS_APPROVED);
				return resp;
			}else{
				return new CustomResponseDto(false, "Error while approving Payment.");
			}
		}catch (Exception e) {
			return new CustomResponseDto(false, e.getMessage());
		}
		
		
	}
	
	
	@PostMapping(value = "/rejectAdvancePayment")
	public @ResponseBody CustomResponseDto rejectAdvancePayment(@RequestBody List<AdvancePaymentDto> advance) {
		
		
		int result=0;
		for(AdvancePaymentDto newadvance:advance) {
			Map<String, Object> propertyValueMap = AbstractContextServiceImpl.getParamMap("status",
					AppBaseConstant.EARLY_PAYMENT_STATUS_REJECTED);
//			propertyValueMap.put("rejectReason", rejectReason);
			result = advanceService.updateByJpql(propertyValueMap,
					AbstractContextServiceImpl.getParamMap("advancePaymentId", newadvance.getAdvancePaymentId()));
		}
		
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "Payment Rejected");
			resp.addObject("status", AppBaseConstant.EARLY_PAYMENT_STATUS_REJECTED);
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Rejecting Payment");
		}
		
	}
		
	
//	@PostMapping(value = "/rejectAdvancePayment/{advancePaymentId}/{rejectReason}")
//	public @ResponseBody CustomResponseDto rejectAdvancePayment(@PathVariable ("advancePaymentId") Long advancePaymentId, @PathVariable ("rejectReason") String rejectReason) {
//		Map<String, Object> propertyValueMap = AbstractContextServiceImpl.getParamMap("status",
//				AppBaseConstant.EARLY_PAYMENT_STATUS_REJECTED);
//		propertyValueMap.put("rejectReason", rejectReason);
//		int result = advanceService.updateByJpql(propertyValueMap,
//				AbstractContextServiceImpl.getParamMap("advancePaymentId", advancePaymentId));
//		if (result > 0) {
//			CustomResponseDto resp = new CustomResponseDto(true, "Payment Rejected");
//			resp.addObject("status", AppBaseConstant.EARLY_PAYMENT_STATUS_REJECTED);
//			return resp;
//		} else {
//			return new CustomResponseDto(false, "Error While Rejecting Payment");
//		}
//		
//	}

	
	@PostMapping(value = "/getEarlyPaymentReport")
	public @ResponseBody CustomResponseDto getEarlyPaymentReport(@RequestBody PaymentReadDto dto) {

		CustomResponseDto resp = new CustomResponseDto();
		List<AdvancePaymentDto> paymentreportlist = advanceService.getPaymentReportbyFilter(dto);
		
		resp.addObject("paymentreportlist", paymentreportlist);
		
		return resp;
		
	}
	
	@RequestMapping(value = { "/getPaymentStatusList" }, method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto getASNStatusList() {
		CustomResponseDto resp = new CustomResponseDto();
		Map<String, String> paymentStatusList = refListService.getReferenceListMap(CoreReferenceConstants.EARLY_PAYMENT_STATUS);
		
		resp.addObject("paymentStatusList", paymentStatusList);
		return resp;
	}
	
	
	
	@PostMapping(value = "/getAdvancePaymentDetailsforVendor")
	public @ResponseBody CustomResponseDto getAdvancePaymentDetailsforVendor() {

		CustomResponseDto resp = new CustomResponseDto();
		
		String role = null;
		
		Map<String, String> purchaseOrderStatusList = refListService
				.getReferenceListMap(CoreReferenceConstants.PO_STATUS);
									// Create a trust manager that does not validate certificate chains
					TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
						public java.security.cert.X509Certificate[] getAcceptedIssuers() {
							return null;
						}

						public void checkClientTrusted(X509Certificate[] certs, String authType) {
						}

						public void checkServerTrusted(X509Certificate[] certs, String authType) {
						}
					} };

					// Install the all-trusting trust manager
					SSLContext sc = null;
					try {
						sc = SSLContext.getInstance("SSL");
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						sc.init(null, trustAllCerts, new java.security.SecureRandom());
					} catch (KeyManagementException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

					// Create all-trusting host name verifier
					HostnameVerifier allHostsValid = new HostnameVerifier() {
						public boolean verify(String hostname, SSLSession session) {
							return true;
						}
					};

					// Install the all-trusting host verifier
					HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
					
					
					Long partnerId=partnerComponent.getLoggedInUser().getPartner().getbPartnerId();
					
					BPartnerDto partner = partnerService.findDto("getPartnerByPartnerId",
							AbstractContextServiceImpl.getParamMap("partnerId", partnerId));
					
					if (contextService != null && contextService.getDefaultRole() != null
							&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())) {
						role = contextService.getDefaultRole().getValue();
					}
					
					String vendorSapCode=partner.getVendorSapCode();
					JSONArray newjsonlist=new JSONArray();
					
					String url="https://172.18.2.36:44300/sap/bc/yweb03_WS_55?sap-client=100&vendor=" + vendorSapCode;
					//String url="https://172.18.2.29:44300/sap/bc/yweb03_WS_55?sap-client=100&vendor=" + vendorSapCode;
				
					System.out.println(url);

					URLConnection request = null;
					
					try {
						URL u = new URL(url);
						request = u.openConnection();
						request.connect();
						BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
						StringBuilder sb = new StringBuilder();
						String line;
						while ((line = br.readLine()) != null) {
							sb.append(line + "\n");
						}
						System.out.println(sb);
						br.close();

						//JSONObject obj = new JSONObject(sb.toString());
						JSONArray headerArray=new JSONArray(sb.toString());
						//System.out.println(headerArray);
						
						 ObjectMapper objJson = new ObjectMapper();
						 objJson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
							
						 AdvancePaymentDto[] dto = objJson.readValue(headerArray.toString(), AdvancePaymentDto[].class);
						 
						 List<AdvancePaymentDto> advancePaymentList= new ArrayList<AdvancePaymentDto>();
						 
						 for(AdvancePaymentDto dtos:dto){
							 
//							 if(!dtos.getMessage().equals("")){     
//								 advancePaymentList.add(dtos);     
//							    }
							 
							advancePaymentList.add(dtos);
			          	   
			             }
						 
						 
						 
						 resp.addObject("advancePaymentList", advancePaymentList);
						 resp.addObject("role", role);
						

					} catch (MalformedURLException ex) {
						ex.printStackTrace();
					} catch (IOException ex) {
						ex.printStackTrace();
					} finally {
						if (request != null) {
							try {
								((HttpURLConnection) request).disconnect();
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}

					}

				

			

		
		return resp;

	}

	

}
