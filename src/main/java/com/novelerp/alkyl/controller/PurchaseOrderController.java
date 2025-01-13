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
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelerp.alkyl.component.POComponent;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.ConditionMasterDto;
import com.novelerp.alkyl.dto.POReadDto;
import com.novelerp.alkyl.dto.PoLineConditionDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.dto.PurchaseOrderLineDto;
import com.novelerp.alkyl.dto.SearchPObySAPDto;
import com.novelerp.alkyl.service.AdvanceShipmentNoticeService;
import com.novelerp.alkyl.service.PurchaseOrderService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.SystemConfiguratorDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;

@RestController
@RequestMapping("/rest")
public class PurchaseOrderController {

	@Autowired
	private ReferenceListService refListService;

	@Autowired
	private UserService userService;

	@Autowired
	private BPartnerService partnerService;

	@Autowired
	private PurchaseOrderService poService;

//	@Autowired
//	private PurchaseOrderLineService poLineService;

	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;

	@Autowired
	private POComponent poComponent;
	
//	@Autowired
//	private POLineComponent poLineComponent;

	@Autowired
	private AdvanceShipmentNoticeService asnService;
	
	@Autowired
	private SystemConfiguratorService sysConfigService;

	@PostMapping("/sapPOCreation")
	public @ResponseBody ResponseDto createVendor(@RequestBody PurchaseOrderDto poDto) {
		if (poDto == null) {
			return new ResponseDto(true, "Empty Data");
		}
		UserDto requestedBy = userService.findDto("getUserBYUserName",
				AbstractContextServiceImpl.getParamMap("username", poDto.getRequestedBy()));

		if (requestedBy == null) {
			return new ResponseDto(true, "Invalid Requested By");
		}
		if (CommonUtil.isStringEmpty(poDto.getVendorCode())) {
			return new ResponseDto(true, "Invalid Vendor SAP Code");
		}
		BPartnerDto partner = partnerService.findDto("getPartnerByVendorSAPCode",
				AbstractContextServiceImpl.getParamMap("vendorCode", poDto.getVendorCode()));
		if (partner == null) {
			return new ResponseDto(true, "Invalid Vendor SAP Code");
		}
		poDto.setPartner(partner);
		poDto.setCreatedBy(requestedBy);
		poDto.setUpdatedBy(requestedBy);
		poDto = poService.save(poDto);
		return poDto.getResponse();
	}

//	@Scheduled(cron = "0 0/10 * * * ?")
	@PostMapping("/fetchPO")
	public @ResponseBody ResponseDto fetchPO() {
		System.out.println("Into Scheduler");
		Errors errors = poComponent.fetchPOFromFTP();
		if (errors.getErrorCount() == 0) {
			return new ResponseDto(false, "Fetch Complete");
		} else {
			return new ResponseDto(true, errors.getErrorStringWithCode());
		}

	}

	@PostMapping("/getPOJSON")
	public @ResponseBody PurchaseOrderDto createVendor() {

		PurchaseOrderDto poDto = new PurchaseOrderDto();
		PurchaseOrderLineDto poLine = new PurchaseOrderLineDto();
		PurchaseOrderLineDto serviceLine = new PurchaseOrderLineDto();
		PoLineConditionDto poLineCondition = new PoLineConditionDto();
		ConditionMasterDto conditionMaster = new ConditionMasterDto();

		List<PurchaseOrderLineDto> poLineList = new ArrayList<>();
		List<PurchaseOrderLineDto> serviceList = new ArrayList<>();
		poLineCondition.setCondition(conditionMaster);

		poLine.addPOLineCondition(poLineCondition);

		serviceList.add(serviceLine);

		poLine.setServiceList(serviceList);

		poLineList.add(poLine);

		poDto.setPoLineList(poLineList);

		return poDto;
	}

	@PostMapping(value = "/getPurchaseOrder")
	public @ResponseBody CustomResponseDto getPurchaseOrder() {
		List<PurchaseOrderDto> poList = null;
		if (contextService != null && contextService.getDefaultRole() != null
				&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())) {
			String role = contextService.getDefaultRole().getValue();
			BPartnerDto partner = contextService.getPartner();
			if (role.equals(AppBaseConstant.ROLE_VENDOR_ADMIN)) {
				poList = poService.findDtos("getPurchaseOrderbyPartner",
						AbstractContextServiceImpl.getParamMap("partnerId", partner.getbPartnerId()));
			} else if (role.equals(AppBaseConstant.ROLE_PARTNER_ADMIN)) {
				poList = poService.findDtos("getPurchaseOrder", null);
			}
		}

		return new CustomResponseDto("poList", poList);
	}

	@PostMapping(value = "/getPurchaseOrderAndPartner")
	public @ResponseBody CustomResponseDto getPurchaseOrderAndPartner() {
		CustomResponseDto resp = new CustomResponseDto();
		Map<String, String> purchaseOrderStatusList = refListService
				.getReferenceListMap(CoreReferenceConstants.PO_STATUS);
		List<PurchaseOrderDto> poList = null;
		if (contextService != null && contextService.getDefaultRole() != null
				&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())) {
			String role = contextService.getDefaultRole().getValue();
			BPartnerDto partner = contextService.getPartner();
			if (role.equals(AppBaseConstant.ROLE_VENDOR_ADMIN)) {
				poList = poService.findDtos("getPurchaseOrderbyPartner",
						AbstractContextServiceImpl.getParamMap("partnerId", partner.getbPartnerId()));
			} else if (role.equals(AppBaseConstant.ROLE_PARTNER_ADMIN)) {
				poList = poService.findDtos("getPurchaseOrder", null);
			}

			resp.addObject("poList", poList);
			resp.addObject("partner", partner);
			resp.addObject("user", contextService.getUser());
			resp.addObject("role", role);
			resp.addObject("purchaseOrderStatusList", purchaseOrderStatusList);
		}

		return resp;
	}

	@PostMapping(value = "/acceptPO/{poId}")
	public @ResponseBody CustomResponseDto acceptPO(@PathVariable("poId") Long poId) {
		int result = poService.updateByJpql(
				AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.PO_STATUS_ACCEPTED),
				AbstractContextServiceImpl.getParamMap("purchaseOrderId", poId));
		CustomResponseDto resp = null;
		if (result > 0) {
			/* old code PurchaseOrderDto po = poService.findDto(poId); */
			/* my code */ PurchaseOrderDto po = poService.findDto("getPurchaseOrderbyPoId",
					AbstractContextServiceImpl.getParamMap("purchaseOrderId", poId));
			try {
				/* my code */poComponent.sendMailOnAcceptPo(po);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp = new CustomResponseDto(true, "Purchase Order Accepted");
			resp.addObject("purchaseOrder", po);
			return resp;
		} else {
			return new CustomResponseDto(false, "Error Occured Accepting Purchase Order");
		}

	}

	@PostMapping(value = "/rejectPO/{poId}/{remark}")
	public @ResponseBody CustomResponseDto rejectPO(@PathVariable("poId") Long poId,
			@PathVariable("remark") String remark) {
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",
				AppBaseConstant.PO_STATUS_REJECTED);
		param.put("remark", remark);
		int result = poService.updateByJpql(param, AbstractContextServiceImpl.getParamMap("purchaseOrderId", poId));
		CustomResponseDto resp = null;
		if (result > 0) {
			PurchaseOrderDto po = poService.findDto(poId);
			resp = new CustomResponseDto(true, "Purchase Order Rejected");
			resp.addObject("purchaseOrder", po);
			return resp;
		} else {
			return new CustomResponseDto(false, "Error Occured Rejecting Purchase Order");
		}

	}

	@PostMapping(value = "/getPurchaseOrderByStatus/status")
	public @ResponseBody CustomResponseDto getPurchaseOrderByStatus(@PathVariable("status") String status) {
		CustomResponseDto resp = new CustomResponseDto();
		Map<String, String> purchaseOrderStatusList = refListService
				.getReferenceListMap(CoreReferenceConstants.PO_STATUS);
		List<PurchaseOrderDto> poList = null;
		if (contextService != null && contextService.getDefaultRole() != null
				&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())) {
			String role = contextService.getDefaultRole().getValue();
			BPartnerDto partner = contextService.getPartner();
			Map<String, Object> param = AbstractContextServiceImpl.getParamMap("partnerId", partner.getbPartnerId());
			if ("OPEN".equals(status)) {
				param.put("status", AppBaseConstant.PO_STATUS_CLOSED);
				poList = poService.findDtos("geOpentPurchaseOrder", param);
			} else {

				param.put("status", status);
				poList = poService.findDtos("getPurchaseOrderbyStatus", param);
			}
			resp.addObject("poList", poList);
			resp.addObject("partner", partner);
			resp.addObject("user", contextService.getUser());
			resp.addObject("role", role);
			resp.addObject("purchaseOrderStatusList", purchaseOrderStatusList);
		}

		return resp;
	}
	/*
	 * @PostMapping(value = "/getPOforGateEntry") public @ResponseBody
	 * CustomResponseDto getPOforGateEntry(@RequestBody POReadDto dto) { String role
	 * =null; CustomResponseDto resp = new CustomResponseDto();
	 * List<PurchaseOrderDto> poList= poService.getPoByFilter(dto); if(poList!=null)
	 * { resp = new CustomResponseDto(true,"PO Exist in List"); }
	 * resp.addObject("poList",poList); return resp; }
	 */

	@PostMapping(value = "/getPOforGateEntry/{pono}/{doctype}")
	
	public @ResponseBody CustomResponseDto getPOforGateEntry(@PathVariable("pono") String pono,@PathVariable("doctype") String doctype) {
		CustomResponseDto resp = new CustomResponseDto();

		Map<String, Object> params = new HashMap<String, Object>();
		

		params.put("pono", pono);

		if (contextService != null && contextService.getDefaultRole() != null
				&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())) {

//			param.add(AppBaseConstant.ASN_STATUS_IN_TRANSIT);
//			param.add(AppBaseConstant.ASN_STATUS_105);
//			param.add(AppBaseConstant.ASN_STATUS_REPORTED);
//			param.add(AppBaseConstant.ASN_STATUS_GATE_IN);
//			param.add(AppBaseConstant.ASN_STATUS_CLOSED);
//			param.add(AppBaseConstant.ASN_STATUS_103_IN_Progress);
//			param.add(AppBaseConstant.ASN_STATUS_103_Posted);
//
//			params.put("status", param);
//			List<AdvanceShipmentNoticeDto> gateentryAsnList = asnService.findDtos("getASNBYStatusForGateEntry", params);
//
//			if (gateentryAsnList.size() > 0) {
//				if (gateentryAsnList != null) {
//					resp.addObject("gateentryAsnList", gateentryAsnList);
//				//	resp.setMessage("ASN Exist");
//				//	resp.setSuccess(true);
//
//				}
//				}
//				else {
//						resp.setMessage("Vendor Registered on Portal,Please Send Him ASN Reminder");
//						resp.setSuccess(false);
//				}
		//	}	
				
		//	}

//			else {
//				PurchaseOrderDto purchaseOrderDto = poService.findDto("getPObyPONumber",
//						AbstractContextServiceImpl.getParamMap("poNumber", pono));
//						if (purchaseOrderDto != null) {
//							
//					BPartnerDto partner = partnerService.findDto("getPartnerByVendorSAPCodeForPO",
//							AbstractContextServiceImpl.getParamMap("vendorCode", purchaseOrderDto.getVendorCode()));                         
//                           
//					if (partner == null) {
//					//	resp.setMessage("Vendor Not Registered...Please Create ASN");
//						//resp.setSuccess(false);
//					} else {
//						 String vendorPortaltable=purchaseOrderDto.getVendorPortal();
//						 String Pstyp=purchaseOrderDto.getPstyp();
//						 
//						 if(vendorPortaltable.equals("Y"))							 
//		                  {
//							 if(Pstyp.equals("9")) {
//
//								 
//							 }
//							 else {
//						        resp.setMessage("Vendor is Registered on Portal,Please Send Him ASN Reminder");
//							    resp.setSuccess(false);
//							 }
//		                  }
//					}
//
//				}

			/*	else {*/
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
				
				
					String url="https://172.18.2.36:44300/sap/bc/yweb03_ws_21?sap-client=100&pono="+pono+"&doctyp="+doctype;
				//	String url="https://172.18.2.29:44300/sap/bc/yweb03_ws_21?sap-client=100&pono="+pono+"&doctyp="+doctype;
				
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

						JSONObject obj = new JSONObject(sb.toString());
						System.out.println(obj);
						String Message = obj.getString("message");
						
						if (Message.equals("PO not yet released")) {

							resp.setMessage("PO not yet released");
							resp.setSuccess(false);

						} else if (Message.equals("PO NOT FOUND,PLS CHECK PO NO..")) {
							resp.setMessage("PO NOT FOUND,PLS CHECK PO NO..");
							resp.setSuccess(false);
						}
						

						else {
							// JSONObject headerArray = (JSONObject) obj.getJSONArray("HEADER").get(0);
							// JSONArray headerArray=obj.getJSONArray("HEADER");
							
							String role = null;
							if (contextService != null && contextService.getDefaultRole() != null
									&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())) {
								role = contextService.getDefaultRole().getValue();
							}
							
							JSONObject headerArray = obj.getJSONObject("HEADER");
							
							
							
//							if(role.equals(AppBaseConstant.ROLE_VENDOR_ADMIN)) {
								ObjectMapper mapper = new ObjectMapper();
								mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
								mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

								PurchaseOrderDto[] dto = mapper.readValue(headerArray.toString(), PurchaseOrderDto[].class);
								boolean isPOCreated = false;
								
								if(role.equals(AppBaseConstant.ROLE_VENDOR_ADMIN)) {
								
								for (PurchaseOrderDto dtos : dto) {
								//	dtos.setStatus("REL");
									isPOCreated = poComponent.createPOFromSAP(dtos);
								}
								}else {
									
									for (PurchaseOrderDto dtos : dto) {
										dtos.setStatus("ACPT");
									//	dtos.setDoctyp(doctype);
										isPOCreated = poComponent.createPOFromSAP(dtos);
									}
									
								}

//								if (isPOCreated) {
//
//									resp.setMessage("PO " + pono + " Uploaded Successfully");
//									// resp.setMessage("PO Created Successfully");
//									resp.setSuccess(true);
//								}


								
//														}
//							
//							else {
//								
//								ObjectMapper mapper = new ObjectMapper();
//								mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
//								String vendorPortal=headerArray.getString("vendorPortal");
//								PurchaseOrderDto[] dto = mapper.readValue(headerArray.toString(), PurchaseOrderDto[].class);
//								boolean isPOCreated = false;
//								for (PurchaseOrderDto dtos : dto) {
//									dtos.setStatus("ACPT");
//									isPOCreated = poComponent.createPOFromSAP(dtos);
//								}
								
								
								
								
//								String vendorPortal=headerArray.getString("vendorPortal");
//								String pstyp=headerArray.getString("pstyp");
//								if (vendorPortal.equals("Y")) {
//									if(pstyp.equals("9")) {
//									resp.setMessage("Vendor Registered on Portal,Kindly Ask Vendor To Create SSN");
//									resp.setSuccess(false);
//									}
//									else {
//									resp.setMessage("Vendor Registered on Portal,Please Send Him ASN Reminder");
//									resp.setSuccess(false);
//									}

//								if (isPOCreated) {
//
//									resp.setMessage("PO " + pono + " Uploaded Successfully");
//									// resp.setMessage("PO Created Successfully");
//									resp.setSuccess(true);
//								}

					//		}
							
								
							//}
						}
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

/*				}*/

		//	}

		}
		return resp;

	}	


	@PostMapping(value = "/getPOforUser/{pono}/{doctype}")
	public @ResponseBody CustomResponseDto getPOforUser(@PathVariable("pono") String pono,@PathVariable("doctype") String doctype) {
		CustomResponseDto resp = new CustomResponseDto();
		
		Map<String, Object> params = new HashMap<String, Object>();
		List<String> param = new ArrayList<>();
		
		params.put("pono", pono);
		
	
//		if(doctype.equals("PO")) {
//
//		params.put("pono", pono);
//		}
//		else {
//			
//			PurchaseOrderDto purchaseOrderDto = poService.findDto("getPObyoutdeliveryNo",AbstractContextServiceImpl.getParamMap("poNumber", pono));
//			if(purchaseOrderDto!=null) {
//			pono= purchaseOrderDto.getPurchaseOrderNumber();
//			params.put("pono", pono);
//			pono= purchaseOrderDto.getOutboundDeliveryNo();
//			}else {
//				params.put("pono", pono);
//			}
//		}
		
		
		/*asnstatus*/
		
		param.add(AppBaseConstant.ASN_STATUS_IN_TRANSIT);
		param.add(AppBaseConstant.ASN_STATUS_105);
		param.add(AppBaseConstant.ASN_STATUS_REPORTED);
		param.add(AppBaseConstant.ASN_STATUS_GATE_IN);
		param.add(AppBaseConstant.ASN_STATUS_CLOSED);
		param.add(AppBaseConstant.ASN_STATUS_103_IN_Progress);
		param.add(AppBaseConstant.ASN_STATUS_103_Posted);
		param.add(AppBaseConstant.ASN_STATUS_101);
		param.add(AppBaseConstant.ASN_STATUS_CANCELED);
		
		/*servicesheetstatus*/
		
		param.add(AppBaseConstant.SERVICE_SHEET_STATUS_IN_PROGRESS);
		param.add(AppBaseConstant.SERVICE_SHEET_STATUS_APPROVED);
		param.add(AppBaseConstant.SERVICE_SHEET_STATUS_REJECTED);
		param.add(AppBaseConstant.SERVICE_SHEET_STATUS_DRAFTED);
		param.add(AppBaseConstant.SERVICE_SHEET_STATUS_APPROVED_2);
		
		
		params.put("status", param);
	
		
		List<AdvanceShipmentNoticeDto> gateentryAsnList = asnService.findDtos("getASNBYStatusForGateEntry", params);

		if (gateentryAsnList.size() > 0) {
			if (gateentryAsnList != null) {
				resp.addObject("gateentryAsnList", gateentryAsnList);
			//	resp.setMessage("ASN Exist");
			//	resp.setSuccess(true);

			}
			}
		else {
		
		PurchaseOrderDto purchaseOrderDto = poService.findDto("getPObyPONumber",AbstractContextServiceImpl.getParamMap("poNumber", pono));
		if (purchaseOrderDto != null) {
					
			BPartnerDto partner = partnerService.findDto("getPartnerByVendorSAPCodeForPO",
					AbstractContextServiceImpl.getParamMap("vendorCode", purchaseOrderDto.getVendorCode()));                         
                   
			if (partner == null) {
			//	resp.setMessage("Vendor Not Registered...Please Create ASN");
				//resp.setSuccess(false);
			} else {
				 String vendorPortaltable=purchaseOrderDto.getVendorPortal();
				 String Pstyp=purchaseOrderDto.getPstyp();
				 
				 if(vendorPortaltable.equals("Y"))							 
                  {
					 if(Pstyp.equals("9")) {

						 
					 }
					 else {
				        resp.setMessage("Vendor is Registered on Portal,Please Send Him ASN Reminder");
					    resp.setSuccess(false);
					 }
                  }
			}

		}

		}
//		params.put("status", param);
//		List<AdvanceShipmentNoticeDto> gateentryAsnList = asnService.findDtos("getASNBYStatusForGateEntry", params);
//
//		if (gateentryAsnList.size() > 0) {
//			if (gateentryAsnList != null) {
//				resp.addObject("gateentryAsnList", gateentryAsnList);
//			//	resp.setMessage("ASN Exist");
//			//	resp.setSuccess(true);
//
//			}
//			}
//			else {
//					resp.setMessage("Vendor Registered on Portal,Please Send Him ASN Reminder");
//					resp.setSuccess(false);
//			}

		
		
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
					

					String url="https://172.18.2.36:44300/sap/bc/yweb03_ws_21?sap-client=100&pono="+pono+"&doctyp="+doctype;
				//	String url="https://172.18.2.29:44300/sap/bc/yweb03_ws_21?sap-client=100&pono="+pono+"&doctyp="+doctype;
			
					
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
						JSONObject obj = new JSONObject(sb.toString());
						
					
						
					String Message = obj.getString("message");
						
						if (Message.equals("PO not yet released")) {

							resp.setMessage("PO not yet released");
							resp.setSuccess(false);

						} else if (Message.equals("PO NOT FOUND,PLS CHECK PO NO..")) {
							resp.setMessage("PO NOT FOUND,PLS CHECK PO NO..");
							resp.setSuccess(false);
						}
						else {
							JSONObject headerArray = obj.getJSONObject("HEADER");
							System.out.println(obj);
							System.out.println(headerArray);
							
							 ObjectMapper objJson = new ObjectMapper();
							 
							 objJson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
							 PurchaseOrderDto dto = objJson.readValue(headerArray.toString(), PurchaseOrderDto.class);
							 List<PurchaseOrderDto> searchedPOList= new ArrayList<PurchaseOrderDto>();
							 dto.setStatus("ACPT");
							 searchedPOList.add(dto);
							 
							 resp.addObject("purchaseOrderList", searchedPOList);
//							resp.setMessage("PO IS RELEASED");
//							resp.setSuccess(true);
						}
					

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

	
	
	
	
	
	
	
	// resp.addObject("poList",purchaseOrderDto);

	@PostMapping(value = "/getPOByFilter")
	public @ResponseBody CustomResponseDto getPOByFilter(@RequestBody POReadDto dto) {
		String role = null;
		CustomResponseDto resp = new CustomResponseDto();
		Map<String, String> purchaseOrderStatusList = refListService
				.getReferenceListMap(CoreReferenceConstants.PO_STATUS);
		BPartnerDto partner = contextService.getPartner();
		if (contextService != null && contextService.getDefaultRole() != null
				&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())) {
			role = contextService.getDefaultRole().getValue();
		}

		if (role.equals(AppBaseConstant.ROLE_VENDOR_ADMIN)) {
			dto.setIsVendorFilter(true);
			dto.setbPartnerId(partner.getbPartnerId());
		}

		List<PurchaseOrderDto> poList = poService.getPoByFilter(dto);

		/*
		 * if(dto.getIsPONoFilter()){ Map<String, Object> param = new HashMap<String,
		 * Object>(); param.put("from",dto.getPoNoFrom() );
		 * param.put("to",dto.getPoNoTo() );
		 * poList=poService.findDtos("getPurchaseOrderbyPoNoFilter", param);
		 * 
		 * } if(dto.getIsPODateFilter()){ Map<String, Object> param = new
		 * HashMap<String, Object>(); param.put("from",dto.getPoDateFrom() );
		 * param.put("to",dto.getPoDateTo() );
		 * poList=poService.findDtos("getPurchaseOrderbyPoDateFilter", param);
		 * 
		 * } if(dto.getIsVendorFilter()){ Map<String, Object> param = new
		 * HashMap<String, Object>(); param.put("from",dto.getVendorCodeFrom() );
		 * param.put("to",dto.getVendorCodeTo() );
		 * poList=poService.findDtos("getPurchaseOrderbyVendorFilter", param);
		 * 
		 * } if(dto.getIsEmpFilter()){ Map<String, Object> param = new HashMap<String,
		 * Object>(); param.put("id",dto.getEmpCode() );
		 * poList=poService.findDtos("getPurchaseOrderbyEmployeeFilter", param);
		 * 
		 * }
		 */

		resp.addObject("poList", poList);
		resp.addObject("partner", partner);
		resp.addObject("user", contextService.getUser());
		resp.addObject("role", role);
		resp.addObject("purchaseOrderStatusList", purchaseOrderStatusList);
		return resp;
	}

	/*
	 * @PostMapping(value = "/getPurchaseOrderByPONO/pono") public @ResponseBody
	 * CustomResponseDto getPurchaseOrderByPONO(@PathVariable("pono") String pono){
	 * CustomResponseDto resp = new CustomResponseDto(); PurchaseOrderDto
	 * poDto=poService.findDto("",
	 * AbstractContextServiceImpl.getParamMap("purchaseOrderNumber", pono));
	 * if(null==poDto){ String
	 * url="http://103.231.11.54:8000/sap/bc/yweb03_ws_02?saleorderno="+pono+"";
	 * URLConnection request=null; try { URL u = new URL(url); request =
	 * u.openConnection();
	 * 
	 * request.connect();
	 * 
	 * 
	 * BufferedReader br = new BufferedReader(new
	 * InputStreamReader(request.getInputStream())); StringBuilder sb = new
	 * StringBuilder(); String line; while ((line = br.readLine()) != null) {
	 * sb.append(line + "\n"); } br.close(); ObjectMapper objJson = new
	 * ObjectMapper(); PurchaseOrderDto dto = objJson.readValue(sb.toString(),
	 * PurchaseOrderDto.class); if(poDto == null){ errors.addError(fileName,
	 * "Empty file"); continue; }
	 * if(CommonUtil.isStringEmpty(poDto.getVendorCode())){
	 * errors.addError(fileName, "PO Number - "+poDto.getPurchaseOrderNumber()+", "
	 * +"Vendor Code cannot be empty null."); continue; } BPartnerDto partner =
	 * partnerService.findDto("getPartnerByVendorSAPCodeForPO",
	 * AbstractContextServiceImpl.getParamMap("vendorCode", poDto.getVendorCode()));
	 * 
	 * if(partner==null){ errors.addError(fileName,
	 * "Vendor with SAP vendor code "+poDto.getVendorCode()+" not found.");
	 * continue; } poDto.setPartner(partner); UserDto vendorUser =
	 * userService.findDto("getUserByPartnerId",
	 * AbstractContextServiceImpl.getParamMap("bPartnerId",
	 * partner.getbPartnerId())); List<UserDetailsDto>
	 * userDetail=userDetailsService.findDtos("getVendorUserForPOActivity",
	 * AbstractContextServiceImpl.getParamMap("partnerId",
	 * partner.getbPartnerId())); if(userDetail==null){ errors.addError(fileName,
	 * "No User Found With PO relatice activity."); continue; } UserDto requestedBy
	 * = userService.findDto("getUserBYUserName",AbstractContextServiceImpl.
	 * getParamMap("username", poDto.getRequestedBy()));
	 * 
	 * if(requestedBy==null){ errors.addError(fileName,
	 * "Employee with employee code "+poDto.getRequestedBy()+" not found.");
	 * continue; }
	 * 
	 * // poDto.setCreatedBy(requestedBy); // poDto.setUpdatedBy(requestedBy);
	 * poDto.setReqby(requestedBy);
	 * 
	 * poDto = poService.save(poDto); return sb.toString();
	 * 
	 * 
	 * } catch (MalformedURLException ex) { ex.printStackTrace(); } catch
	 * (IOException ex) { ex.printStackTrace(); }finally { if (request != null) {
	 * try { ((HttpURLConnection) request).disconnect(); } catch (Exception ex) {
	 * ex.printStackTrace(); } } } } return resp;
	 * 
	 * }
	 */
	

	
	@PostMapping(value = "/searchPObySAP/{pono}/{pono1}")
	public @ResponseBody CustomResponseDto searchPObySAP(@PathVariable("pono") String pono,@PathVariable("pono1") String pono1) {
		
		
		
//		Map<String, Object> resp=SearchPO(pono);
		
		
		 TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
             public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                 return null;
             }
             public void checkClientTrusted(X509Certificate[] certs, String authType) {
             }
             public void checkServerTrusted(X509Certificate[] certs, String authType) {
             }
         }
     };
     

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
		

	//String url="https://172.18.2.33:44300/sap/bc/yweb03_ws_22?sap-client=110&pono=3100000001&pono1=3100000030";
     String url="https://172.18.2.33:44300/sap/bc/yweb03_ws_22?sap-client=110&pono="+pono+"&pono1="+pono1;

		System.out.println(url);
//		List<String> list = new ArrayList<>();
		
		CustomResponseDto response = new CustomResponseDto();
		
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
			 String obj = sb.toString();
			 ObjectMapper objJson = new ObjectMapper();
			
			 SearchPObySAPDto[] dto = objJson.readValue(obj.toString(), SearchPObySAPDto[].class);
			 List<SearchPObySAPDto> searchedPOList= new ArrayList<SearchPObySAPDto>();
			 for(SearchPObySAPDto dtos:dto){
				 searchedPOList.add(dtos);
          	   
             }
			 response.addObject("searchedPOList", searchedPOList);



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
		
		
		return response;
	}
	
	
	@PostMapping(value="/getPObyPONo/{poNumber}")
	public @ResponseBody CustomResponseDto getPObyPONo(@PathVariable("poNumber") String poNumber){
		List<PurchaseOrderDto> poList = poService.findDtos("getPObyPONumber", AbstractContextServiceImpl.getParamMap("poNumber", poNumber));
	//	List<PurchaseOrderLineDto> serviceLineList = poLineService.findDtos("getServicesByPOId", AbstractContextServiceImpl.getParamMap("poNo", poNo));
		//Map<String, String> costList = refListService.getReferenceListMap(CoreReferenceConstants.COST_CENTER);
		
		
	
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("value", AppBaseConstant.SSNVERSION);
		SystemConfiguratorDto sysConfig=sysConfigService.findDto("getSysConfiguratorByType",params);
		
	
		CustomResponseDto resp = new CustomResponseDto("poList", poList);
		resp.addObject("SSNVersion", sysConfig.getName());
		return resp;
	}
	
	
	@PostMapping(value = "/searchPObyVendor/{pono}")
	public @ResponseBody CustomResponseDto searchPObyVendor(@PathVariable("pono") String pono) {
		
		
		
//		Map<String, Object> resp=SearchPO(pono);
		
		
		 TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
             public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                 return null;
             }
             public void checkClientTrusted(X509Certificate[] certs, String authType) {
             }
             public void checkServerTrusted(X509Certificate[] certs, String authType) {
             }
         }
     };
     

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
		
     String username="abap";
     String password="Abaper@123";
	//String url="https://172.18.2.33:44300/sap/bc/yweb03_ws_22?sap-client=110&pono=3100000001&pono1=3100000030";
     String url="https://172.18.2.33:44300/sap/bc/ztest_user?sap-client=110&po="+pono; 
     // snippet ends
		System.out.println(url);
//		List<String> list = new ArrayList<>();
		
		CustomResponseDto response = new CustomResponseDto();
		
		URLConnection request = null;
		try {
			URL u = new URL(url);
			request = u.openConnection();
		     request.setRequestProperty("Accept", "application/json");
		     // snippet begins
		     request.setRequestProperty("Authorization",
		       "Basic " + Base64.getEncoder().encodeToString(
		         (username + ":" + password).getBytes()
		       )
		     );
		     
			request.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			System.out.println(sb);
			br.close();
			 ObjectMapper objJson = new ObjectMapper();
			
			 SearchPObySAPDto[] dto = objJson.readValue(sb.toString(), SearchPObySAPDto[].class);
			 List<SearchPObySAPDto> searchedPOList= new ArrayList<SearchPObySAPDto>();
			 for(SearchPObySAPDto dtos:dto){
				 searchedPOList.add(dtos);
          	   
             }
			 response.addObject("searchedPOList", searchedPOList);



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
		
		
		return response;
	}
	
	
	@GetMapping(value = "/POSAPStatus/{pono}")
	public String POSAPStatus(@PathVariable("pono") String pono) {
		

		String status=null;

	
 		List<PurchaseOrderDto> poList = poService.findDtos("getPObyPONumber", AbstractContextServiceImpl.getParamMap("poNumber", pono));
		
		if (poList.size() > 0) {
		    if(poList!=null) {
		    	
		          status=poList.get(0).getStatus();
		          if(status.equals("")) {
		        	  status="Status is Null";
		        	 
		        	 
		          }else {
		        	  status=poList.get(0).getStatus();
		          }
	
		     }
		}
		else {
			status="PO Not Found";
		}
		
		return status;
		}
	
	
	
	
	
	
	
	



}
