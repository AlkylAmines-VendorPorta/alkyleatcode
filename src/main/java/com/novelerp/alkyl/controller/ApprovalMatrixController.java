package com.novelerp.alkyl.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novelerp.alkyl.dao.VendorProfileHistoryDao;
import com.novelerp.alkyl.dto.ApprovalMatrixDto;
import com.novelerp.alkyl.dto.KYCDto;
import com.novelerp.alkyl.service.ApprovalMatrixService;
import com.novelerp.alkyl.service.KYCService;
import com.novelerp.alkyl.service.VendorProfileHistoryService;
import com.novelerp.appbase.master.dto.MailTemplateDto;
import com.novelerp.appbase.master.service.MailTemplateService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.BPartnerService;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;

@Controller
@RequestMapping("/rest")

public class ApprovalMatrixController {

	@Autowired
	private ApprovalMatrixService approvalService;
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	
	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	private BPartnerService partnerService;
	
	@Autowired
	private KYCService kycService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private MailTemplateService mailTemplateService;
	@Autowired
	private VendorProfileHistoryDao vendorProfileHistoryDao;
	
	@Autowired
	private VendorProfileHistoryService vendorHistoryService;

	
		
	@RequestMapping(value = { "/vendorApprovalDetails/{partnerId}" }, method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto getVendorApprovalDetails(@PathVariable("partnerId")Long partnerId) {
		CustomResponseDto resp = new CustomResponseDto();
		Map<String, String> titleList = refListService.getReferenceListMap(CoreReferenceConstants.SALUTATION);
		Map<String, String> vendorClassificationList = refListService
				.getReferenceListMap(CoreReferenceConstants.VENDOR_CLASSIFICATION);
//		Map<String, String> reconAccountList = refListService.getReferenceListMap(CoreReferenceConstants.RECON_ACCOUNT);
		Map<String, String> vendorSchemaGroupList = refListService
				.getReferenceListMap(CoreReferenceConstants.VENDOR_SCHEMA_GROUP);
		Map<String, String> vendorPurchaseGroupList = refListService
				.getReferenceListMap(CoreReferenceConstants.VENDOR_PURCHASE_GROUP);
//		Map<String, String> incoTermsList = refListService
//				.getReferenceListMap(CoreReferenceConstants.INCO_TERMS);
//		Map<String, String> paymentTermsList = refListService
//				.getReferenceListMap(CoreReferenceConstants.PAYMENT_TERMS);
		
//		 // Create a trust manager that does not validate certificate chains
//        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
//                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                    return null;
//                }
//                public void checkClientTrusted(X509Certificate[] certs, String authType) {
//                }
//                public void checkServerTrusted(X509Certificate[] certs, String authType) {
//                }
//            }
//        };
// 
//        // Install the all-trusting trust manager
//        SSLContext sc = null;
//		try {
//			sc = SSLContext.getInstance("SSL");
//		} catch (NoSuchAlgorithmException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//        try {
//			sc.init(null, trustAllCerts, new java.security.SecureRandom());
//		} catch (KeyManagementException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
// 
//        // Create all-trusting host name verifier
//        HostnameVerifier allHostsValid = new HostnameVerifier() {
//            public boolean verify(String hostname, SSLSession session) {
//                return true;
//            }
//        };
// 
//        // Install the all-trusting host verifier
//        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
//        
//        String paymenturl="https://172.18.2.36:44300/sap/bc/yweb03_ws_18?sap-client=100";
//		
//		//String paymenturl="https://172.18.2.28:44300/sap/bc/yweb03_ws_18?sap-client=100";
//      //  String paymenturl="https://172.18.2.28:44300/sap/bc/yweb03_ws_18?sap-client=100";
//		
//				List<String> cntList = new ArrayList<>();
//				URLConnection request=null;   
//				try {
//			            URL u = new URL(paymenturl);
//			            request =  u.openConnection();
//
//			            request.connect();
//
//
//			            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
//			            StringBuilder sb = new StringBuilder();
//			            String line;
//			            while ((line = br.readLine()) != null) {
//			                sb.append(line + "\n");
//			            }
//			            System.out.println(sb);
//			            br.close();
//			            String[] dropdownelement = sb.toString().replaceAll("\"", "").split(",");
//			            cntList = Arrays.asList(dropdownelement);
//			            
//			        } catch (MalformedURLException ex) {
//			            ex.printStackTrace();
//			        } catch (IOException ex) {
//			            ex.printStackTrace();
//			        }finally {
//			            if (request != null) {
//			                try {
//			                	((HttpURLConnection) request).disconnect();
//			                } catch (Exception ex) {
//			                    ex.printStackTrace();
//			                }
//			            }
//			        }
//				
//				Map<String, String> paymentTermsList = new HashMap<>();
//				for(String temp : cntList) {
//					paymentTermsList.put(temp.split("-")[0].trim(), temp.trim());
//				}
				
			//	String reconaccounturl="https://172.18.2.28:44300//sap/bc/yweb03_ws_27?sap-client=100";
			//	String reconaccounturl="https://172.18.2.36:44300//sap/bc/yweb03_ws_27?sap-client=100";
				
				//String reconaccounturl="https://172.18.2.33:44300//sap/bc/yweb03_ws_27?sap-client=110";
				//String reconaccounturl="https://172.18.2.28:44300//sap/bc/yweb03_ws_27?sap-client=100";
//					List<String> reconList = new ArrayList<>();
//					URLConnection reconrequest=null;   
//					try {
//				            URL u = new URL(reconaccounturl);
//				            reconrequest =  u.openConnection();
//
//				            reconrequest.connect();
//
//
//				            BufferedReader br = new BufferedReader(new InputStreamReader(reconrequest.getInputStream()));
//				            StringBuilder sb = new StringBuilder();
//				            String line;
//				            while ((line = br.readLine()) != null) {
//				                sb.append(line + "\n");
//				            }
//				            System.out.println(sb);
//				            br.close();
//				            String[] dropdownelement = sb.toString().replaceAll("\"", "").split(";");
//				            reconList = Arrays.asList(dropdownelement);
//				            
//				        } catch (MalformedURLException ex) {
//				            ex.printStackTrace();
//				        } catch (IOException ex) {
//				            ex.printStackTrace();
//				        }finally {
//				            if (request != null) {
//				                try {
//				                	((HttpURLConnection) reconrequest).disconnect();
//				                } catch (Exception ex) {
//				                    ex.printStackTrace();
//				                }
//				            }
//				        }
//					
//					Map<String, String> reconAccountList = new HashMap<>();
//					for(String temp : reconList) {
//						reconAccountList.put(temp.split("-")[0].trim(), temp.trim());
//					}
					
//				//	String incotermturl="https://172.18.2.33:44300//sap/bc/yweb03_ws_28?sap-client=110";
//				//	String incotermturl="https://172.18.2.28:44300//sap/bc/yweb03_ws_28?sap-client=100";
//					String incotermturl="https://172.18.2.36:44300//sap/bc/yweb03_ws_28?sap-client=100";
//						List<String> incoTermList = new ArrayList<>();
//						URLConnection incoTermrequest=null;   
//						try {
//					            URL u = new URL(incotermturl);
//					            incoTermrequest =  u.openConnection();
//
//					            incoTermrequest.connect();
//
//
//					            BufferedReader br = new BufferedReader(new InputStreamReader(incoTermrequest.getInputStream()));
//					            StringBuilder sb = new StringBuilder();
//					            String line;
//					            while ((line = br.readLine()) != null) {
//					                sb.append(line + "\n");
//					            }
//					            System.out.println(sb);
//					            br.close();
//					          //  String[] dropdownelement = sb.toString().replaceAll("\"", "").split(",");
//					            String[] dropdownelement = sb.toString().replaceAll("\"", "").split(";");
//					            incoTermList = Arrays.asList(dropdownelement);
//					            
//					        } catch (MalformedURLException ex) {
//					            ex.printStackTrace();
//					        } catch (IOException ex) {
//					            ex.printStackTrace();
//					        }finally {
//					            if (request != null) {
//					                try {
//					                	((HttpURLConnection) incoTermrequest).disconnect();
//					                } catch (Exception ex) {
//					                    ex.printStackTrace();
//					                }
//					            }
//					        }
//						
//						Map<String, String> incoTermsList = new HashMap<>();
//						for(String temp : incoTermList) {
//							incoTermsList.put(temp.split("-")[0].trim(), temp.trim());
//						}


		
		KYCDto kycDto = kycService.findDto("getKYCDetailsByPartnerId",AbstractContextServiceImpl.getParamMap("partnerId", partnerId));
		ApprovalMatrixDto approvalDto= approvalService.findDto("getApprovalMatrix",AbstractContextServiceImpl.getParamMap("partnerId", partnerId));
		
		resp.addObject("isMsme", kycDto.getIsMSME());
		resp.addObject("msmeType", kycDto.getMsmeType());
		resp.addObject("vendorApproval", approvalDto);
		resp.addObject("titleList", titleList);
		resp.addObject("vendorClassificationList", vendorClassificationList);
	//	resp.addObject("reconAccountList", reconAccountList);
		resp.addObject("vendorSchemaGroupList", vendorSchemaGroupList);
		resp.addObject("vendorPurchaseGroupList", vendorPurchaseGroupList);
	//	resp.addObject("incoTermsList", incoTermsList);
	//	resp.addObject("paymentTermsList", paymentTermsList);
		return resp;
	}

	@RequestMapping(value = { "/saveVendorApprovalDetails" }, method = RequestMethod.POST)
	public @ResponseBody CustomResponseDto saveVendorApprovalDetails(@RequestBody ApprovalMatrixDto dto) {
		CustomResponseDto resp = new CustomResponseDto();
		try{
//			UserDto user=userService.findDto("getQueryForUserByPartnerId", AbstractContextServiceImpl.getParamMap("bPartnerId", dto.getPartner().getbPartnerId()));
			UserDto user=userService.getUserByPartnerId(dto.getPartner().getbPartnerId());
			System.out.println(user.getUserId());
			dto = approvalService.approveVendorWithUserDto(dto, user);
			
			if(dto.getResponse().getMessage().equals("Vendor Approved.") ){
				 approvalService.updateStatusAfterVendorApproval(dto);
		     }
			
		//	System.out.println("approveVendorWithUserDto" +dto);
			/*if(!dto.getResponse().isHasError()){
				MailTemplateDto mailTemplate=mailTemplateService.getMailTemplateFromTemplateType(AppBaseConstant.VENDOR_APPROVE_TEMPLATE);
				userService.sendMailOnVendorApprovalAlkyl(user,mailTemplate,dto.getPartner().getName());
			}*/
			//boolean activeStatus = vendorProfileDaoImpl.updateIsActiveStatus();
			
			/*HashMap<String, Object> params = new HashMap<>();
			params.put("bPartnerId", dto.getPartner().getbPartnerId());
			params.put("bPartnerId",  10065);
		
			vendorHistoryService.updateByJpql(AbstractContextServiceImpl.getParamMap("isActive", AppBaseConstant.REJECTED_STATUS), 
					AbstractContextServiceImpl.getParamMap("bPartnerId", dto.getPartner().getbPartnerId()));*/
			vendorProfileHistoryDao.updateIsActiveStatus(dto.getPartner().getbPartnerId());
			}catch(Exception e){
			dto.setResponse(new ResponseDto(true,e.getMessage()));
			System.out.println("saveVendorApprovalDetails catch block" +e.getMessage());
			System.out.println("saveVendorApprovalDetails catch block" +dto);
		}
		resp.addObject("approvalMatrix", dto);
		return resp;
	}
	@PostMapping(value="/saveVendorRejectionDetails")
	public @ResponseBody CustomResponseDto setVendorStatus(@RequestBody ApprovalMatrixDto dto){
		CustomResponseDto resp = new CustomResponseDto();
		if(null==dto.getApprovalMatrixId()){
			dto = approvalService.save(dto);
		}else{
			dto = approvalService.updateDto(dto);
		}
		
		int responsestatus=partnerService.updateByJpql(AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.DOCUMENT_STATUS_REJECTED)
				, AbstractContextServiceImpl.getParamMap("bPartnerId", dto.getPartner().getbPartnerId()));
		
		if(responsestatus>0 ){
			dto.setResponse(new ResponseDto(false,"Vendor Rejected"));
		}else{
			dto.setResponse(new ResponseDto(true,"Vendor Rejection Failed "));
		}
		resp.addObject("approvalMatrix", dto);
		return resp;
	}
	
	/*@Scheduled(cron="0 0/30 * * * ?")*/
	@PostMapping(value="/fetchVendorData")
	public @ResponseBody CustomResponseDto fetchVendorData(){
		CustomResponseDto resp = new CustomResponseDto();
		try {
			System.out.println("Into Fetch Vendor");
			Errors errors=approvalService.fetchVendorData();
			if(errors.getErrorCount()==0){
				resp.setSuccess(true);
				resp.setMessage("Vendors fetched");
			}else{
				resp.setSuccess(false);
				resp.setMessage(errors.getErrorStringWithCode());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resp.setSuccess(false);
			resp.setMessage("Something Went Wrong");
		}
		return resp;
	}
	
	
	/*//@RequestMapping(value={"/fetchVendorApprovalDetails/{vendorSapcode}" , "/fetchVendorApprovalDetails"}, method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	//@RequestMapping(value={"/fetchVendorApprovalDetails/{vendorSapcode}"}, method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json")
	@PostMapping(value="/fetchVendorApprovalDetails/{vendorSapcode}") 
	//public @ResponseBody String getVendorDetails(@PathVariable("vendorSapcode") String vendorSapcode){
		public @ResponseBody String getVendorDetails(@PathVariable(value="vendorSapcode",required = false) String vendorSapcode){
		System.out.println("Fetch Vendor Approval Details");
		String url;
		if(vendorSapcode != null){
		 url="http://103.231.11.54:8000/sap/bc/yweb03_ws_05?sap-client=009&vcode="+vendorSapcode;
		 System.out.println("not null");
		}
		else{
			System.out.println("inside else");
			url="http://103.231.11.54:8000/sap/bc/yweb03_ws_05?sap-client=009";
			 System.out.println("null");
			System.out.println(url);
		}
		 URLConnection request=null;   
		try {
	            URL u = new URL(url);
	            request =  u.openConnection();

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
                VehicleRegistrationDto dto = objJson.readValue(sb.toString(), VehicleRegistrationDto.class);
                vehicleRegistrationComponent.saveInvoiceMailDetail(dto,regId);
	            return sb.toString();


	        } catch (MalformedURLException ex) {
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }finally {
	            if (request != null) {
	                try {
	                	((HttpURLConnection) request).disconnect();
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }
	        }
	        return null;
	}*/
	
	
	
	@PostMapping(value="/fetchVendorApprovalDetails/{vendorSapcode}") 
		public @ResponseBody String getVendorDetails(@PathVariable(value="vendorSapcode") String vendorSapcode){
		
//		String url="http://103.231.11.54:8000/sap/bc/yweb03_ws_05?sap-client=009&vcode="+vendorSapcode;
//	String url="	https://172.18.2.28:44300//sap/bc/yweb03_ws_05?sap-client=100&vcode=600027";
		 // Create a trust manager that does not validate certificate chains
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
        
	String url="https://172.18.2.28:44300//sap/bc/yweb03_ws_05?sap-client=100&vcode="+vendorSapcode;
//	String url="https://172.18.2.36:44300//sap/bc/yweb03_ws_05?sap-client=100&vcode="+vendorSapcode;
		
		 URLConnection request=null;   
		try {
	            URL u = new URL(url);
	            request =  u.openConnection();

	            request.connect();


	            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
	            StringBuilder sb = new StringBuilder();
	            String line;
	            while ((line = br.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	            System.out.println(sb);
	            br.close();
                
	            return sb.toString();


	        } catch (MalformedURLException ex) {
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }finally {
	            if (request != null) {
	                try {
	                	((HttpURLConnection) request).disconnect();
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }
	        }
	        return null;
	}
	
	@PostMapping(value="/vendorApprovalReconAccountDetails")
	public @ResponseBody CustomResponseDto vendorApprovalReconAccountDetails(){
		
		CustomResponseDto resp = new CustomResponseDto();
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
    
//	String reconaccounturl="https://172.18.2.28:44300//sap/bc/yweb03_ws_27?sap-client=100";
	String reconaccounturl="https://172.18.2.36:44300//sap/bc/yweb03_ws_27?sap-client=100";
	
	//String reconaccounturl="https://172.18.2.33:44300//sap/bc/yweb03_ws_27?sap-client=110";
	//String reconaccounturl="https://172.18.2.28:44300//sap/bc/yweb03_ws_27?sap-client=100";
		List<String> reconList = new ArrayList<>();
		URLConnection reconrequest=null;   
		try {
	            URL u = new URL(reconaccounturl);
	            reconrequest =  u.openConnection();

	            reconrequest.connect();


	            BufferedReader br = new BufferedReader(new InputStreamReader(reconrequest.getInputStream()));
	            StringBuilder sb = new StringBuilder();
	            String line;
	            while ((line = br.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	            System.out.println(sb);
	            br.close();
	            String[] dropdownelement = sb.toString().replaceAll("\"", "").split(";");
	            reconList = Arrays.asList(dropdownelement);
	            
	        } catch (MalformedURLException ex) {
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }finally {
	            if (reconrequest != null) {
	                try {
	                	((HttpURLConnection) reconrequest).disconnect();
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }
	        }
		
		Map<String, String> reconAccountList = new HashMap<>();
		for(String temp : reconList) {
			reconAccountList.put(temp.split("-")[0].trim(), temp.trim());
		}
		
		
		String role =contextService.getDefaultRole().getValue();
		resp.addObject("role",role);
		resp.addObject("reconAccountList", reconAccountList);
           return resp;

	}
	
	
	
	@PostMapping(value="/vendorApprovalPaymentTermsDetails")
	public @ResponseBody CustomResponseDto vendorApprovalPaymentTermsDetails(){
		CustomResponseDto resp = new CustomResponseDto();
		 // Create a trust manager that does not validate certificate chains
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
        
        String paymenturl="https://172.18.2.36:44300/sap/bc/yweb03_ws_18?sap-client=100";
		
		//String paymenturl="https://172.18.2.28:44300/sap/bc/yweb03_ws_18?sap-client=100";
      //  String paymenturl="https://172.18.2.28:44300/sap/bc/yweb03_ws_18?sap-client=100";
		
				List<String> cntList = new ArrayList<>();
				URLConnection request=null;   
				try {
			            URL u = new URL(paymenturl);
			            request =  u.openConnection();

			            request.connect();


			            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			            StringBuilder sb = new StringBuilder();
			            String line;
			            while ((line = br.readLine()) != null) {
			                sb.append(line + "\n");
			            }
			            System.out.println(sb);
			            br.close();
			            String[] dropdownelement = sb.toString().replaceAll("\"", "").split(",");
			            cntList = Arrays.asList(dropdownelement);
			            
			        } catch (MalformedURLException ex) {
			            ex.printStackTrace();
			        } catch (IOException ex) {
			            ex.printStackTrace();
			        }finally {
			            if (request != null) {
			                try {
			                	((HttpURLConnection) request).disconnect();
			                } catch (Exception ex) {
			                    ex.printStackTrace();
			                }
			            }
			        }
				
				Map<String, String> paymentTermsList = new HashMap<>();
				for(String temp : cntList) {
					paymentTermsList.put(temp.split("-")[0].trim(), temp.trim());
				}

		
		
				resp.addObject("paymentTermsList", paymentTermsList);
                return resp;

	}
	
	
	
	@PostMapping(value="/vendorApprovalincoTermsListDetails")
	public @ResponseBody CustomResponseDto vendorApprovalincoTermsListDetails(){
		CustomResponseDto resp = new CustomResponseDto();
//		String incotermturl="https://172.18.2.33:44300//sap/bc/yweb03_ws_28?sap-client=110";
		//	String incotermturl="https://172.18.2.28:44300//sap/bc/yweb03_ws_28?sap-client=100";
			String incotermturl="https://172.18.2.36:44300//sap/bc/yweb03_ws_28?sap-client=100";
				List<String> incoTermList = new ArrayList<>();
				URLConnection incoTermrequest=null;   
				try {
			            URL u = new URL(incotermturl);
			            incoTermrequest =  u.openConnection();

			            incoTermrequest.connect();


			            BufferedReader br = new BufferedReader(new InputStreamReader(incoTermrequest.getInputStream()));
			            StringBuilder sb = new StringBuilder();
			            String line;
			            while ((line = br.readLine()) != null) {
			                sb.append(line + "\n");
			            }
			            System.out.println(sb);
			            br.close();
			          //  String[] dropdownelement = sb.toString().replaceAll("\"", "").split(",");
			            String[] dropdownelement = sb.toString().replaceAll("\"", "").split(";");
			            incoTermList = Arrays.asList(dropdownelement);
			            
			        } catch (MalformedURLException ex) {
			            ex.printStackTrace();
			        } catch (IOException ex) {
			            ex.printStackTrace();
			        }finally {
			            if (incoTermrequest != null) {
			                try {
			                	((HttpURLConnection) incoTermrequest).disconnect();
			                } catch (Exception ex) {
			                    ex.printStackTrace();
			                }
			            }
			        }
				
				Map<String, String> incoTermsList = new HashMap<>();
				for(String temp : incoTermList) {
					incoTermsList.put(temp.split("-")[0].trim(), temp.trim());
				}

		
		
				resp.addObject("incoTermsList", incoTermsList);
                return resp;

	}

	
}
