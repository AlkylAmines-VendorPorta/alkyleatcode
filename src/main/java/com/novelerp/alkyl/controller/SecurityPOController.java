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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Base64;
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
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.icu.text.SimpleDateFormat;
import com.novelerp.alkyl.component.SecurityPOHeaderComponent;
import com.novelerp.alkyl.dao.AdvanceShipmentNoticeLineDao;
import com.novelerp.alkyl.dao.SecurityPOHeaderDao;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.SecurityPOHeaderDto;
import com.novelerp.alkyl.service.AdvanceShipmentNoticeService;

import com.novelerp.alkyl.service.SecurityPOHeaderService;
import com.novelerp.alkyl.service.SecurityPOLineItemService;
import com.novelerp.alkyl.validator.ASNValidator;
import com.novelerp.alkyl.validator.GRNValidator;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.entity.User;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.DateUtil;

@Controller
@RequestMapping("/rest")
public class SecurityPOController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	
	@Autowired
	private SecurityPOHeaderService securityPOHeaderService;
	
	@Autowired
	private SecurityPOHeaderComponent securityPOHeaderComponent;
	
	@Autowired
	private SecurityPOLineItemService securityPOLineItemService;
	
	
	@Autowired
	private AdvanceShipmentNoticeService asnService;
	
	@Autowired
	private SecurityPOHeaderDao securityPOHeaderDao;
	
	@Autowired 
	private AdvanceShipmentNoticeLineDao advanceShipmentNoticeLineDao;
	
	@Autowired
	private GRNValidator validator;
	
	@Autowired
	private ASNValidator asnvalidator;
	
	@PostMapping(value = "/saveSecurityHeaderDetails")
	public @ResponseBody CustomResponseDto saveSecurityHeaderDetails(
			@RequestBody SecurityPOHeaderDto securityPOHeaderDto) {
		CustomResponseDto resp = new CustomResponseDto();
		try {
			securityPOHeaderDto = securityPOHeaderService.save(securityPOHeaderDto);
//			if (securityPOHeaderDto != null && securityPOHeaderDto.getResponse() != null
//					&& !securityPOHeaderDto.getResponse().isHasError()) {
//				CustomResponseDto response = new CustomResponseDto(true,
//						"ASN Marked as Reported" + '\n' + " ASN No is :" + securityPOHeaderDto.getAsnNumber());
//				response.addObject("status", AppBaseConstant.ASN_STATUS_REPORTED);
//				return response;
//			}

		} catch (Exception e) {
			e.printStackTrace();
			return new CustomResponseDto(false, e.getMessage());

		}
	//	return new CustomResponseDto(false, "Error While marking ASN as Reported");
		return null;
	}
	
	
	@PostMapping(value = "/saveCommercialHeaderDetailsinASN")
	public @ResponseBody CustomResponseDto saveCommercialHeaderDetailsinASN(@RequestBody AdvanceShipmentNoticeDto asn) {
		CustomResponseDto resp = new CustomResponseDto();
		try {
			
			
			asn=asnService.saveasn(asn);
			
			
			if (asn != null && asn.getResponse() != null && !asn.getResponse().isHasError()) {
				CustomResponseDto response = new CustomResponseDto(true,
						"ASN Marked as Reported" + '\n' + " ASN No is :" + asn.getAdvanceShipmentNoticeNo());
				response.addObject("status", AppBaseConstant.ASN_STATUS_REPORTED);
				return response;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new CustomResponseDto(false, e.getMessage());

		}
		//return new CustomResponseDto(false, "Error While Updating Data");
//		return null;
		return new CustomResponseDto(false, "Error While marking ASN as Reported");
	}
	
	
	
	@PostMapping(value = "/saveASNwithoutPO")
	public @ResponseBody CustomResponseDto saveASNwithoutPO(@RequestBody AdvanceShipmentNoticeDto asn) {
		CustomResponseDto resp = new CustomResponseDto();
		
		String outbounddeliveryNo=asn.getDeliveryNoteNo();
		
		AdvanceShipmentNoticeDto asnDto = asnService.findDto("getAsnbyOutbounddeliveryNo",AbstractContextServiceImpl.getParamMap("outbounddeliveryNo",outbounddeliveryNo));
		
//		if(asnDto==null) {
		
		try {
			
//			if("".equals(asn.getPlant())) {
//				CustomResponseDto response = new CustomResponseDto(true,
//						"Please Select Plant");
//				
//				return response;
//				
//			}
			
			
			Errors errors = new Errors();
			CustomResponseDto respN = new CustomResponseDto();
			asnvalidator.validateWithoutpoAsnDetails(asn, errors);
			if (errors.getErrorCount() > 0) {
				respN.setSuccess(false);
				respN.setMessage(errors.getErrorString());
				return respN;
			}else {
			
			asn=asnService.saveasnwithoutpo(asn);
			
			}
			if (asn != null && asn.getResponse() != null && !asn.getResponse().isHasError()) {
				CustomResponseDto response = new CustomResponseDto(true,
						"ASN Marked as Reported" + '\n' + " ASN No is :" + asn.getAdvanceShipmentNoticeNo());
				response.addObject("status", AppBaseConstant.ASN_STATUS_REPORTED);
				
				return response;
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			return new CustomResponseDto(false, e.getMessage());

		}
		//return new CustomResponseDto(false, "Error While Updating Data");
//		return null;
		return new CustomResponseDto(false, "Error While marking ASN as Reported");
//		}
//		else {
//			return new CustomResponseDto(false, "Already ASN is generated against this outboundDelivery");
//		}
	}
	
	@PostMapping(value = "/saveASNDirectFor103")
	public @ResponseBody CustomResponseDto saveASNDirectFor103(@RequestBody AdvanceShipmentNoticeDto asn) {
		CustomResponseDto resp = new CustomResponseDto();
		try {
			
			
			asn=asnService.saveasnDirectFor103(asn);
			
			
			if (asn != null && asn.getResponse() != null && !asn.getResponse().isHasError()) {
				CustomResponseDto response = new CustomResponseDto(true," ASN No is :" + asn.getAdvanceShipmentNoticeNo());
				response.addObject("status", AppBaseConstant.ASN_STATUS_GATE_IN);
				return response;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new CustomResponseDto(false, e.getMessage());

		}
		//return new CustomResponseDto(false, "Error While Updating Data");
//		return null;
		return new CustomResponseDto(false, "Error While marking ASN as Gate IN");
	}
	
	

	@PostMapping(value = "/getAsnbyheaderId/{asnHeaderId}")
	public @ResponseBody CustomResponseDto getAsnbyheaderId(@PathVariable("asnHeaderId") Long asnHeaderId) {
		CustomResponseDto resp = null;

		AdvanceShipmentNoticeDto Dto = asnService.findDto("getAsnbyheaderId",AbstractContextServiceImpl.getParamMap("asnHeaderId",asnHeaderId));
		resp = new CustomResponseDto("asnList", Dto);
		return resp;
	}
	
	
	@PostMapping(value = "/getASNforCommercial")
	public @ResponseBody CustomResponseDto getASNforCommercial() {
		CustomResponseDto resp = null;
		Map<String, Object> params = new HashMap<String, Object>();
		List<SecurityPOHeaderDto> asnList = new ArrayList<>();
		List<String> param = new ArrayList<>();
		if (contextService != null && contextService.getDefaultRole() != null
				&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())) {
			String role = contextService.getDefaultRole().getValue();

//			if (role.equals(AppBaseConstant.ROLE_STORE_ADMIN)) {
				param.add(AppBaseConstant.ASN_STATUS_REPORTED);
				param.add(AppBaseConstant.ASN_STATUS_GATE_IN);
				param.add(AppBaseConstant.ASN_STATUS_103_IN_Progress);
//			}
		}
		params.put("status", param);
		asnList = securityPOHeaderService.findDtos("getASNBYStatusforCommercial", params);
		resp = new CustomResponseDto("asnList", asnList);
		return resp;
	}
	
	@PostMapping(value = "/getInSecurityStatusUpdate/{advanceShipmentNoticeId}")
	public @ResponseBody CustomResponseDto getInSecurityStatusUpdate(@PathVariable("advanceShipmentNoticeId") Long advanceShipmentNoticeId) throws ParseException {
		
//		SecurityPOHeaderDto Dto = securityPOHeaderService.findDto("getASNByASNIdforCommercial",
//				AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId",advanceShipmentNoticeId));
		
		User gateinBy= new User();
		gateinBy.setUserId(contextService.getUser().getUserId());

		Map<String , Object> parameter = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.ASN_STATUS_GATE_IN);
		parameter.put("gateinBy", gateinBy);
		parameter.put("gateInDate", new Date());
		int Asnresult = asnService.updateByJpql(parameter,AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", advanceShipmentNoticeId));
		

		if (Asnresult > 0) {
			
			
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
			
//		//	AdvanceShipmentNoticeLineDto Dto = asnlineService.findDto("printASNByASNNOforSecurity",AbstractContextServiceImpl.getParamMap("asnId", asnId));
//			
//		//	List<AdvanceShipmentNoticeLineDto> asnLineList = asnLineComponent.getASNLineForASNPrint(asnId);
			
			List<AdvanceShipmentNoticeLineDto> asnLineList = advanceShipmentNoticeLineDao.printASNByASNNOforSecurityNew(advanceShipmentNoticeId);
			 
			
	       String pono=asnLineList.get(0).getAdvanceshipmentnotice().getPo().getPurchaseOrderNumber();
		
			
	         ObjectMapper mapper = new ObjectMapper();
	     
	         Map<String, Object> jsonlist = new HashMap<String, Object>();

	         
	         JSONArray newjsonlist=new JSONArray();
	        
	         for(AdvanceShipmentNoticeLineDto newasnList:asnLineList) {      	 
	         jsonlist.put("pono", newasnList.getAdvanceshipmentnotice().getPo().getPurchaseOrderNumber());
	         jsonlist.put("advanceShipmentNoticeNo", newasnList.getAdvanceshipmentnotice().getAdvanceShipmentNoticeNo());
	         
	         String str = DateUtil.getDateString(newasnList.getAdvanceshipmentnotice().getGateInDate(), DateUtil.DEFAULT_DATE_FORMAT );
	         String Gateindate=str. replace("-", "");
	         
	         jsonlist.put("gateInDate", Gateindate);
	         
	         
	         String invoice = DateUtil.getDateString(newasnList.getAdvanceshipmentnotice().getInvoiceDate(), DateUtil.DEFAULT_DATE_FORMAT );
	         String Invoicedate=invoice. replace("-", "");
	         jsonlist.put("invoiceDate", Invoicedate);
	         
//	         jsonlist.put("gateInDate", DateUtil.getDateString(newasnList.getAdvanceshipmentnotice().getGateInDate(), DateUtil.DEFAULT_DATE_FORMAT ));
	         jsonlist.put("invoiceNo", newasnList.getAdvanceshipmentnotice().getInvoiceNo());
//	         jsonlist.put("invoiceDate", DateUtil.getDateString(newasnList.getAdvanceshipmentnotice().getInvoiceDate(), DateUtil.DEFAULT_DATE_FORMAT ));
	         jsonlist.put("vehicalNo", newasnList.getAdvanceshipmentnotice().getVehicalNo());
	         jsonlist.put("deliveryQuantity", newasnList.getDeliveryQuantity());
	         jsonlist.put("plant", newasnList.getPoLine().getPlant());
	         jsonlist.put("lrNumber",newasnList.getAdvanceshipmentnotice().getLrNumber());
	         jsonlist.put("netWeight",newasnList.getAdvanceshipmentnotice().getNetWeight());
	         jsonlist.put("transporterNo",newasnList.getAdvanceshipmentnotice().getTransporterNo());
//	         jsonlist.put("uom", newasnList.getPoLine().getUom());
//	         jsonlist.put("name", newasnList.getPoLine().getName());
	         jsonlist.put("value", newasnList.getPoLine().getCode());
	         jsonlist.put("lineitemNumber", newasnList.getPoLine().getLineItemNumber());
	         newjsonlist.put(jsonlist);
	         
	         }

				try {

				//	String url ="https://172.18.2.29:44300/sap/bc/yweb03_WS_56?sap-client=100&PO="+pono+"&JSON="+newjsonlist;
					
					String url ="https://172.18.2.36:44300/sap/bc/yweb03_WS_56?sap-client=100&PO="+pono+"&JSON="+newjsonlist;
					System.out.println(url);
					ResponseDto resp = new ResponseDto();
					URLConnection request = null;
					try {
					//	URL u = new URL( URLEncoder.encode(url, "UTF-8"));
						URL u = new URL(url.replace(" ","%20"));
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
					
					}
					catch (MalformedURLException ex) {
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
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			
		CustomResponseDto response = new CustomResponseDto(true,"Gate In SuccessFully");
		response.addObject("status", AppBaseConstant.ASN_STATUS_GATE_IN);
		return response;
		}
		
//
//		if (Asnresult > 0) {
//		CustomResponseDto response = new CustomResponseDto(true,"Gate In SuccessFully");
//		response.addObject("status", AppBaseConstant.ASN_STATUS_GATE_IN);
//		return response;
//		}
		
           return null;

	}
	
	@PostMapping(value = "/getInSecurityStatusUpdate103/{asnHeaderId}")
	public @ResponseBody int getInSecurityStatusUpdate103(@PathVariable("asnHeaderId") Long asnHeaderId) {
		
//		SecurityPOHeaderDto Dto = securityPOHeaderService.findDto("getASNByASNIdforCommercial",
//				AbstractContextServiceImpl.getParamMap("asnHeaderId",asnHeaderId));

	//	Dto.setStatus(AppBaseConstant.ASN_STATUS_Checked);
		Map<String , Object> param = AbstractContextServiceImpl.getParamMap("status",AppBaseConstant.ASN_STATUS_103_IN_Progress);
		int result = securityPOHeaderService.updateByJpql(param,AbstractContextServiceImpl.getParamMap("asnHeaderId", asnHeaderId));

//		if (result > 0) {
////			CustomResponseDto response = new CustomResponseDto(true,"");
////			return response;
//			}
	           return result ;
//		if (result > 0) {
//		CustomResponseDto response = new CustomResponseDto(true,"Gate In SuccessFully");
//		return response;
//		}
	//	Dto.setStatus(AppBaseConstant.ASN_STATUS_103_IN_Progress);
	//	securityPOHeaderService.updateDto(Dto);
	//	CustomResponseDto response = new CustomResponseDto(true,"Gate In SuccessFully");
		


	}
	
	@PostMapping(value = "/printSecurityGateInForm/{asnId}")
	public @ResponseBody CustomResponseDto printSecurityGateInForm(@PathVariable("asnId") Long asnId) {
		
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
		
	//	AdvanceShipmentNoticeLineDto Dto = asnlineService.findDto("printASNByASNNOforSecurity",AbstractContextServiceImpl.getParamMap("asnId", asnId));
		
	//	List<AdvanceShipmentNoticeLineDto> asnLineList = asnLineComponent.getASNLineForASNPrint(asnId);
		
		List<AdvanceShipmentNoticeLineDto> asnLineList = advanceShipmentNoticeLineDao.printASNByASNNOforSecurityNew(asnId);
		 

	
        String pono=asnLineList.get(0).getAdvanceshipmentnotice().getPo().getPurchaseOrderNumber();
        		//Dto.getPo().getPurchaseOrderNumber();
        
         ObjectMapper mapper = new ObjectMapper();
       //  List<String> jsonlist = new ArrayList<>();
         Map<String, Object> jsonlist = new HashMap<String, Object>();

         
         JSONArray newjsonlist=new JSONArray();
        
         for(AdvanceShipmentNoticeLineDto newasnList:asnLineList) {      	 
         jsonlist.put("pono", newasnList.getAdvanceshipmentnotice().getPo().getPurchaseOrderNumber());
         jsonlist.put("advanceShipmentNoticeNo", newasnList.getAdvanceshipmentnotice().getAdvanceShipmentNoticeNo());
         jsonlist.put("gateInDate", newasnList.getAdvanceshipmentnotice().getGateInDate());
         jsonlist.put("invoiceNo", newasnList.getAdvanceshipmentnotice().getInvoiceNo());
         jsonlist.put("invoiceDate", newasnList.getAdvanceshipmentnotice().getInvoiceDate());
         jsonlist.put("vehicalNo", newasnList.getAdvanceshipmentnotice().getVehicalNo());
         jsonlist.put("deliveryQuantity", newasnList.getDeliveryQuantity());
         jsonlist.put("plant", newasnList.getPoLine().getPlant());
         jsonlist.put("uom", newasnList.getPoLine().getUom());
//         jsonlist.put("name", newasnList.getPoLine().getName());
         jsonlist.put("value", newasnList.getPoLine().getCode());
         jsonlist.put("lineitemNumber", newasnList.getPoLine().getLineItemNumber());
         newjsonlist.put(jsonlist);
        // newjsonlist.add(jsonlist);
         
         }

			try {
//				String jsonnewlist = new String(newjsonlist.toString());
//			String json = mapper.writeValueAsString(jsonnewlist);
//			System.out.println(json);
				String url ="https://172.18.2.36:44300/sap/bc/yweb03_ws_24?sap-client=100&PO="+pono+"&JSON="+newjsonlist;
				System.out.println(url);
				ResponseDto resp = new ResponseDto();
				URLConnection request = null;
				try {
				//	URL u = new URL( URLEncoder.encode(url, "UTF-8"));
					URL u = new URL(url.replace(" ","%20"));
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
				
				}
				catch (MalformedURLException ex) {
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
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		return null;
	}
	
//	@PostMapping(value = "/printSecurityGateInForm/{asnId}")
//	public @ResponseBody CustomResponseDto printSecurityGateInForm(@PathVariable("asnId") Long asnId) {
//		
//		// Create a trust manager that does not validate certificate chains
//		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
//			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//				return null;
//			}
//
//			public void checkClientTrusted(X509Certificate[] certs, String authType) {
//			}
//
//			public void checkServerTrusted(X509Certificate[] certs, String authType) {
//			}
//		} };
//
//		// Install the all-trusting trust manager
//		SSLContext sc = null;
//		try {
//			sc = SSLContext.getInstance("SSL");
//		} catch (NoSuchAlgorithmException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		try {
//			sc.init(null, trustAllCerts, new java.security.SecureRandom());
//		} catch (KeyManagementException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//
//		// Create all-trusting host name verifier
//		HostnameVerifier allHostsValid = new HostnameVerifier() {
//			public boolean verify(String hostname, SSLSession session) {
//				return true;
//			}
//		};
//
//		// Install the all-trusting host verifier
//		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
//		
//	//	AdvanceShipmentNoticeLineDto Dto = asnlineService.findDto("printASNByASNNOforSecurity",AbstractContextServiceImpl.getParamMap("asnId", asnId));
//		
//	//	List<AdvanceShipmentNoticeLineDto> asnLineList = asnLineComponent.getASNLineForASNPrint(asnId);
//		
//		List<AdvanceShipmentNoticeLineDto> asnLineList = advanceShipmentNoticeLineDao.printASNByASNNOforSecurityNew(asnId);
//		 
//
//	
//        String pono=asnLineList.get(0).getAdvanceshipmentnotice().getPo().getPurchaseOrderNumber();
//        		//Dto.getPo().getPurchaseOrderNumber();
//        
//         ObjectMapper mapper = new ObjectMapper();
//       //  List<String> jsonlist = new ArrayList<>();
//         Map<String, Object> jsonlist = new HashMap<String, Object>();
//
//         
//         JSONArray newjsonlist=new JSONArray();
//        
//         for(AdvanceShipmentNoticeLineDto newasnList:asnLineList) {      	 
//         jsonlist.put("pono", newasnList.getAdvanceshipmentnotice().getPo().getPurchaseOrderNumber());
//         jsonlist.put("advanceShipmentNoticeNo", newasnList.getAdvanceshipmentnotice().getAdvanceShipmentNoticeNo());
//         jsonlist.put("gateInDate", newasnList.getAdvanceshipmentnotice().getGateInDate());
//         jsonlist.put("invoiceNo", newasnList.getAdvanceshipmentnotice().getInvoiceNo());
//         
//         DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
//         String strDate = dateFormat.format(newasnList.getAdvanceshipmentnotice().getInvoiceDate());  
//         jsonlist.put("invoiceDate", strDate);
//         jsonlist.put("vehicalNo", newasnList.getAdvanceshipmentnotice().getVehicalNo());
//         jsonlist.put("deliveryQuantity", newasnList.getDeliveryQuantity());
//         jsonlist.put("plant", newasnList.getPoLine().getPlant());
//         jsonlist.put("uom", newasnList.getPoLine().getUom());
////         jsonlist.put("name", newasnList.getPoLine().getName());
//         jsonlist.put("value", newasnList.getPoLine().getCode());
//         jsonlist.put("lineitemNumber", newasnList.getPoLine().getLineItemNumber());
//         newjsonlist.put(jsonlist);
//        // newjsonlist.add(jsonlist);
//         
//         }
//
//			try {
////				String jsonnewlist = new String(newjsonlist.toString());
////			String json = mapper.writeValueAsString(jsonnewlist);
////			System.out.println(json);
//				String url ="https://172.18.2.36:44300/sap/bc/yweb03_ws_24?sap-client=100&PO="+pono+"&JSON="+newjsonlist;
//			//	String url ="https://172.18.2.33:44300/sap/bc/yweb03_ws_24?sap-client=110&po="+pono+"&asnid="+asnId;
//			//	String url ="https://172.18.2.36:44300/sap/bc/yweb03_ws_24?sap-client=100&po="+pono+"&asnid="+asnId;
//				System.out.println(url);
//				ResponseDto resp = new ResponseDto();
//				URLConnection request = null;
//				try {
//				//	URL u = new URL( URLEncoder.encode(url, "UTF-8"));
//					URL u = new URL(url.replace(" ","%20"));
//					request = u.openConnection();
//					request.connect();
//					BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
//					StringBuilder sb = new StringBuilder();
//					String line;
//					while ((line = br.readLine()) != null) {
//						sb.append(line + "\n");
//					}
//					System.out.println(sb);
//					br.close();
//				
//				}
//				catch (MalformedURLException ex) {
//					ex.printStackTrace();
//				} catch (IOException ex) {
//					ex.printStackTrace();
//				} finally {
//					if (request != null) {
//						try {
//							((HttpURLConnection) request).disconnect();
//						} catch (Exception ex) {
//							ex.printStackTrace();
//						}
//					}
//
//				}
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		
//		
//		
//		
//		return null;
//	}
//	
	
	
	@PostMapping(value = "/reportSecurityStatusUpdate/{advanceShipmentNoticeId}")
	public @ResponseBody CustomResponseDto reportSecurityStatusUpdate(@PathVariable("advanceShipmentNoticeId") Long advanceShipmentNoticeId) {

	// SecurityPOHeaderDto Dto = securityPOHeaderService.findDto("getASNByASNIdforCommercial",
	// AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId",advanceShipmentNoticeId));

	User reportedBy= new User();
	reportedBy.setUserId(contextService.getUser().getUserId());
	// Map<String , Object> param = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.ASN_STATUS_GATE_IN);
	// param.put("gateinBy", gateinBy);
	// param.put("gateinDate", new Date());
	// int headerresult = securityPOHeaderService.updateByJpql(param,AbstractContextServiceImpl.getParamMap("asnHeaderId", asnHeaderId));

	Map<String , Object> parameter = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.ASN_STATUS_REPORTED);
	parameter.put("reportedBy", reportedBy);
	int Asnresult = asnService.updateByJpql(parameter,AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", advanceShipmentNoticeId));


	if (Asnresult > 0) {
	CustomResponseDto response = new CustomResponseDto(true,"Reported SuccessFully");
	return response;
	}

	           return null;

	}
	
	
	@RequestMapping(value="/unloadingFormJson/{asnId}" , method = RequestMethod.GET)
	 public @ResponseBody String unloadingFormJson(@PathVariable("asnId") Long asnId) throws Exception {
		
	
		
		List<AdvanceShipmentNoticeLineDto> asnLineList = advanceShipmentNoticeLineDao.printASNByASNNOforSecurityNew(asnId);
		 

		
        String pono=asnLineList.get(0).getAdvanceshipmentnotice().getPo().getPurchaseOrderNumber();
        		//Dto.getPo().getPurchaseOrderNumber();
        
         ObjectMapper mapper = new ObjectMapper();
       //  List<String> jsonlist = new ArrayList<>();
         Map<String, Object> jsonlist = new HashMap<String, Object>();

         
         JSONArray newjsonlist=new JSONArray();
        
         for(AdvanceShipmentNoticeLineDto newasnList:asnLineList) {      	 
         jsonlist.put("pono", newasnList.getAdvanceshipmentnotice().getPo().getPurchaseOrderNumber());
         jsonlist.put("advanceShipmentNoticeNo", newasnList.getAdvanceshipmentnotice().getAdvanceShipmentNoticeNo());
         jsonlist.put("gateInDate", newasnList.getAdvanceshipmentnotice().getGateInDate());
         jsonlist.put("invoiceNo", newasnList.getAdvanceshipmentnotice().getInvoiceNo()); 
         String pattern = "dd-MM-yyyy";
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
         String date = simpleDateFormat.format(newasnList.getAdvanceshipmentnotice().getInvoiceDate());
         jsonlist.put("invoiceDate", date);
         jsonlist.put("vehicalNo", newasnList.getAdvanceshipmentnotice().getVehicalNo());
         jsonlist.put("deliveryQuantity", newasnList.getDeliveryQuantity());
         jsonlist.put("plant", newasnList.getPoLine().getPlant());
         jsonlist.put("uom", newasnList.getPoLine().getUom());
//         jsonlist.put("name", newasnList.getPoLine().getName());
         jsonlist.put("value", newasnList.getPoLine().getCode());
         jsonlist.put("lineitemNumber", newasnList.getPoLine().getLineItemNumber());
         newjsonlist.put(jsonlist);
         
         }
		try {
			String json = CommonUtil.getObjectJson(jsonlist);
		//	boolean result = mediaService.writeByteArrayTo(json.getBytes(), AppBaseConstant.FTP_GRN_TO_PROCESS_PATH, asnDto.getAdvanceShipmentNoticeNo()+".txt");
		//	if(!result){
		//		throw new RuntimeException("Error while writing file");
		//	}
			return json;
		} catch (Exception e) {
			throw e;
		}
         }
	
	
	@PostMapping(value = "/printSecurityGateInFormWithoutPO/{asnId}")
	public @ResponseBody CustomResponseDto printSecurityGateInFormWithoutPO(@PathVariable("asnId") Long asnId) {
		
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
		
	//	AdvanceShipmentNoticeLineDto Dto = asnlineService.findDto("printASNByASNNOforSecurity",AbstractContextServiceImpl.getParamMap("asnId", asnId));
		
	//	List<AdvanceShipmentNoticeLineDto> asnLineList = asnLineComponent.getASNLineForASNPrint(asnId);
		
		List<AdvanceShipmentNoticeLineDto> asnLineList = advanceShipmentNoticeLineDao.printASNByASNNOforSecurityNewWithoutPO(asnId);
		 

	
       // String pono=asnLineList.get(0).getAdvanceshipmentnotice().getPo().getPurchaseOrderNumber();
        		//Dto.getPo().getPurchaseOrderNumber();
        
         ObjectMapper mapper = new ObjectMapper();
       //  List<String> jsonlist = new ArrayList<>();
         Map<String, Object> jsonlist = new HashMap<String, Object>();

         
         JSONArray newjsonlist=new JSONArray();
        
         for(AdvanceShipmentNoticeLineDto newasnList:asnLineList) {      	 
        // jsonlist.put("pono", newasnList.getAdvanceshipmentnotice().getPo().getPurchaseOrderNumber());
         jsonlist.put("advanceShipmentNoticeNo", newasnList.getAdvanceshipmentnotice().getAdvanceShipmentNoticeNo());
         jsonlist.put("gateInDate", newasnList.getAdvanceshipmentnotice().getGateInDate());
         jsonlist.put("invoiceNo", newasnList.getAdvanceshipmentnotice().getInvoiceNo());
         jsonlist.put("invoiceDate", newasnList.getAdvanceshipmentnotice().getInvoiceDate());
         jsonlist.put("vehicalNo", newasnList.getAdvanceshipmentnotice().getVehicalNo());
         jsonlist.put("deliveryQuantity", newasnList.getDeliveryQuantity());
         jsonlist.put("plant", newasnList.getAdvanceshipmentnotice().getPlant());
         jsonlist.put("uom", newasnList.getUom());
//         jsonlist.put("name", newasnList.getPoLine().getName());
       //  jsonlist.put("value", newasnList.getName());
         jsonlist.put("material", newasnList.getName());
         jsonlist.put("lineitemNumber", newasnList.getLineItemNo());
         newjsonlist.put(jsonlist);
        // newjsonlist.add(jsonlist);
         
         }

			try {
//				String jsonnewlist = new String(newjsonlist.toString());
//			String json = mapper.writeValueAsString(jsonnewlist);
//			System.out.println(json);
				String url ="https://172.18.2.36:44300/sap/bc/yweb03_ws_24?sap-client=100&PO=&JSON="+newjsonlist;
				//String url ="https://172.18.2.29:44300/sap/bc/yweb03_ws_24?sap-client=100&PO=&JSON="+newjsonlist;
				System.out.println(url);
				ResponseDto resp = new ResponseDto();
				URLConnection request = null;
				try {
				//	URL u = new URL( URLEncoder.encode(url, "UTF-8"));
					URL u = new URL(url.replace(" ","%20"));
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
				
				}
				catch (MalformedURLException ex) {
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
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		return null;
	}
	
	
//	@RequestMapping(value="/unloadingASNWithoutPOFormJson/{asnId}" , method = RequestMethod.GET)
//	 public @ResponseBody String unloadingASNWithoutPOFormJson(@PathVariable("asnId") Long asnId) throws Exception {
//		
//		//List<AdvanceShipmentNoticeLineDto> asnLineList = advanceShipmentNoticeLineDao.printASNByASNNOforSecurityNew(asnId);
//		List<AdvanceShipmentNoticeLineDto> asnLineList = advanceShipmentNoticeLineDao.printASNByASNNOforSecurityNewWithoutPO(asnId);
//
//		
//      // String pono=asnLineList.get(0).getAdvanceshipmentnotice().getPo().getPurchaseOrderNumber();
//       		//Dto.getPo().getPurchaseOrderNumber();
//
//        Map<String, Object> jsonlist = new HashMap<String, Object>();
//
//        
//        JSONArray newjsonlist=new JSONArray();
//       
//        for(AdvanceShipmentNoticeLineDto newasnList:asnLineList) {      	 
//       // jsonlist.put("pono", newasnList.getAdvanceshipmentnotice().getPo().getPurchaseOrderNumber());
//        jsonlist.put("advanceShipmentNoticeNo", newasnList.getAdvanceshipmentnotice().getAdvanceShipmentNoticeNo());
//        jsonlist.put("gateInDate", newasnList.getAdvanceshipmentnotice().getGateInDate());
//        jsonlist.put("invoiceNo", newasnList.getAdvanceshipmentnotice().getInvoiceNo()); 
//        String pattern = "dd-MM-yyyy";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//        String date = simpleDateFormat.format(newasnList.getAdvanceshipmentnotice().getInvoiceDate());
//        jsonlist.put("invoiceDate", date);
//        jsonlist.put("vehicalNo", newasnList.getAdvanceshipmentnotice().getVehicalNo());
//        jsonlist.put("deliveryQuantity", newasnList.getDeliveryQuantity());
//        jsonlist.put("plant", newasnList.getAdvanceshipmentnotice().getPlant());
//        jsonlist.put("uom", newasnList.getUom());
////        jsonlist.put("name", newasnList.getPoLine().getName());
//      //  jsonlist.put("value", newasnList.getPoLine().getCode());
//        jsonlist.put("material", newasnList.getName());
//        jsonlist.put("lineitemNumber", newasnList.getLineItemNo());
//        newjsonlist.put(jsonlist);
//        
//        }
//		try {
//			String json = CommonUtil.getObjectJson(jsonlist);
//
//			return json;
//		} catch (Exception e) {
//			throw e;
//		}
//        }

}
