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
import java.util.ArrayList;
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
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelerp.alkyl.component.BPartnerComponent;
import com.novelerp.alkyl.dto.AdvancePaymentDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.dto.SearchPObySAPDto;
import com.novelerp.alkyl.service.AdvancePaymentService;
import com.novelerp.alkyl.service.AdvanceShipmentNoticeService;
import com.novelerp.alkyl.service.PurchaseOrderService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;

@Controller
@RequestMapping("/rest")
public class UserDashboardController {
	

	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	
	@Autowired
	private ReferenceListService refListService;

	@Autowired
	private PurchaseOrderService poService;
	
	@Autowired
	private BPartnerComponent partnerComponent;
	
	@Autowired
	private AdvanceShipmentNoticeService asnService;
	
	@Autowired
	private BPartnerService partnerService;
	
	@Autowired
	private AdvancePaymentService advanceService;
	
	@RequestMapping(value = "/userDashboardDetails", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto userDashboardDetails() {
		CustomResponseDto resp = new CustomResponseDto();
		Long partnerId=partnerComponent.getLoggedInUser().getPartner().getbPartnerId();
		Long totalPoCount= poService.getPobyPartnerIdNew(partnerId);
		/*Long acceptedPoCount=poService.getAcceptedPobyPartnerIdNew(partnerComponent.getLoggedInUser().getPartner().getbPartnerId());
		Long rejectedPoCount=poService.getRejectedPobyPartnerIdNew(partnerComponent.getLoggedInUser().getPartner().getbPartnerId());*/
		Long releasedPoCount=poService.getReleasedPobyPartnerIdNew(partnerComponent.getLoggedInUser().getPartner().getbPartnerId());
		
		Long openPoCount=poService.getOpenPoCountbyPartnerId(partnerId);	
		Long openPoAsnCount=asnService.getOpenAsnCountbyPartnerId(partnerId);
		Long pendingPoBillBookingCount=asnService.getPendingPoBillBookingCountbyPartnerId(partnerId);
		
		resp.addObject("totalPoCount", totalPoCount);
		/*resp.addObject("acceptedPoCount", acceptedPoCount);
		resp.addObject("rejectedPoCount", rejectedPoCount);*/
		resp.addObject("releasedPoCount", releasedPoCount);
		
		resp.addObject("openPoCount", openPoCount);
		resp.addObject("openPoAsnCount", openPoAsnCount);
		resp.addObject("pendingPoBillBookingCount", pendingPoBillBookingCount);
		return resp;
	}
	
	
	@PostMapping(value = "/getPOforVendor")
	public @ResponseBody CustomResponseDto getPOforVendor() {
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
					
					 String url="https://172.18.2.36:44300/sap/bc/yweb03_ws_22?sap-client=100&VENDOR=" + vendorSapCode;
				//	String url="https://172.18.2.28:44300/sap/bc/yweb03_ws_22?sap-client=100&VENDOR=" + vendorSapCode;
				//	String url = "https://172.18.2.33:44300/sap/bc/yweb03_ws_22?sap-client=110&VENDOR=" + vendorSapCode;
					
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
							
						 PurchaseOrderDto[] dto = objJson.readValue(headerArray.toString(), PurchaseOrderDto[].class);
						 
						 List<PurchaseOrderDto> searchedPOList= new ArrayList<PurchaseOrderDto>();
						 for(PurchaseOrderDto dtos:dto){
							if(dtos.getReleaseStatus().equals("REL")) {
								dtos.setStatus("Released");
							}else if(dtos.getReleaseStatus().equals("ACPT")) {
								dtos.setStatus("Accepted");
							}else if(dtos.getReleaseStatus().equals("REJ")) {
								dtos.setStatus("Rejected");
							}else {
								dtos.setStatus("");
							}
							 searchedPOList.add(dtos);
			          	   
			             }
						 
						 
						
						 JSONObject tot_obj = headerArray.getJSONObject(headerArray.length()-1);
						 String totalOpenPoCount=tot_obj.getString("serialNo");
//						 String vendorPortal=tot_obj.getString("vendorPortal");
						 resp.addObject("totalOpenPoCount", totalOpenPoCount);
						 resp.addObject("purchaseOrderStatusList", purchaseOrderStatusList);
//						 resp.addObject("vendorPortal", vendorPortal);
						 resp.addObject("VendorPoList", searchedPOList);
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
