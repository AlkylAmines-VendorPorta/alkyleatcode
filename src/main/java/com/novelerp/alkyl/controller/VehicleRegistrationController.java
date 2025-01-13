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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelerp.alkyl.component.GetGSTINDetails;
import com.novelerp.alkyl.component.VehicleRegistrationComponent;
import com.novelerp.alkyl.dao.VehicleRegistrationDao;
import com.novelerp.alkyl.dto.SAPSalesOrderDto;
import com.novelerp.alkyl.dto.VehicleRegistrationDto;
import com.novelerp.alkyl.service.CustomerService;
import com.novelerp.alkyl.service.VehicleRegistrationService;
import com.novelerp.alkyl.validator.VehicleRegistrationValidator;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appbase.master.service.AttachmentService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.User;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.CoreReferenceConstants;

@Controller
@RestController
@RequestMapping("/rest")
public class VehicleRegistrationController {
	
	@Autowired
	private ReferenceListService refListService;
	@Autowired
	private UserService userService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private VehicleRegistrationService vehicleRegistrationService;
	@Autowired
	private VehicleRegistrationValidator VehicleRegistrationValidator;
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	@Autowired
	private AttachmentService attService;
	@Autowired
	private VehicleRegistrationComponent vehicleRegistrationComponent;
	
	@Autowired
	@Qualifier(value=AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
	private MediaService mediaService;
	
	@Autowired
	private GetGSTINDetails getGSTNDEtails;
	
	@Autowired
	private VehicleRegistrationDao vehicleRegistrationDao;
	
	@PostMapping(value = "/getVehicleRegDropDown")
	public @ResponseBody CustomResponseDto getOnLOadMaster() {
		Map<String,String> plantList =refListService.getReferenceListMap(CoreReferenceConstants.PlANT);
		List<UserDto> internalUserList=userService.findDtos("getAllInternalUser", null);
       /* List<CustomerDto> soldToParty=customerService.findDtos("queryToGetCustomerByType",AbstractContextServiceImpl.getParamMap("customerType", "STP"));
        List<CustomerDto> shipToParty=customerService.findDtos("queryToGetCustomerByType", AbstractContextServiceImpl.getParamMap("customerType", "SHTP"));
        List<CustomerDto> transporter=customerService.findDtos("queryToGetCustomerByType", AbstractContextServiceImpl.getParamMap("customerType", "TRP"));
        Map<String,String> vehicleType =refListService.getReferenceListMap(CoreReferenceConstants.VEHICLE_TYPE);
       */ CustomResponseDto resp = new CustomResponseDto();
        resp.addObject("plantList",plantList);
        resp.addObject("internalUserList",internalUserList);
       /* resp.addObject("soldToParty",soldToParty);
        resp.addObject("shipToParty",shipToParty);
        resp.addObject("transporter",transporter);
        resp.addObject("vehicleType",vehicleType);*/
        return resp;
	}
	
	@RequestMapping(value={"/saveVehicleRegistration"},method=RequestMethod.POST,produces="application/json")
	public @ResponseBody CustomResponseDto saveVehicleRegistration(@RequestBody VehicleRegistrationDto vehicleRegistrationDto) {
		Errors errors = new Errors();
		CustomResponseDto resp = new CustomResponseDto();
		VehicleRegistrationValidator.validateVehicleRegistration(vehicleRegistrationDto, errors);
		if (errors.getErrorCount() > 0) {
			resp.setSuccess(false);
			resp.setMessage(errors.getErrorString());
			return resp;
		} else {
			
			
			String poNo=vehicleRegistrationDto.getPoNo();
			String saleorderno=vehicleRegistrationDto.getSaleOrderNo();
			
			if(saleorderno!=null) {
			
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
			//String url="http://103.231.11.54:8000/sap/bc/zgate_web?saleOrderNo="+saleOrder+"";
			
			//String url="https://172.18.2.33:44300/sap/bc/zgate_web?sap-client=110&SALEORDERNO="+saleOrder;
	        String url="https://172.18.2.36:44300/sap/bc/zgate_web?sap-client=100&SALEORDERNO="+saleorderno;
	     //   String url="https://172.18.2.29:44300/sap/bc/zgate_web?sap-client=100&SALEORDERNO="+saleorderno;
			//String url="http://103.231.11.54:8000/sap/bc/zgate_web?saleOrderNo="+saleOrder+"";
			URLConnection request=null;     
			try {
				 boolean shipToStatus=false;
				 boolean soldToStatus =false;
		            URL u = new URL(url);
		             request =  u.openConnection();

		            request.connect();


		            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		            StringBuilder sb = new StringBuilder();
		            String line;
		            while ((line = br.readLine()) != null) {
		                sb.append(line + "\n");
		            }
		            br.close();
		             ObjectMapper objJson = new ObjectMapper();
	                VehicleRegistrationDto dto = objJson.readValue(sb.toString(), VehicleRegistrationDto.class);
//	                if(null!=dto.getShiptogstin()){
//	               shipToStatus= getGSTNDEtails.getAuth(dto.getShiptogstin());
//	                }
//	                if(null!=dto.getSoldtogstin()){
//	                 soldToStatus= getGSTNDEtails.getAuth(dto.getSoldtogstin());
//	                     }
//	                if(!shipToStatus && !soldToStatus){
//	                	resp.setMessage("GSTIN Not Active");
//	                	resp.setSuccess(false);
//	                	return resp;
//	                }
//	                if(!shipToStatus){
//	                	resp.setMessage("Ship To Party GSTIN Not Active");
//	                	resp.setSuccess(false);
//	                	return resp;
//	                }
//	                if(!soldToStatus){
//	                	resp.setMessage("Bill To Party GSTIN Not Active");
//	                	resp.setSuccess(false);
//	                	return resp;
//	                 }
	                
	            	Map<String, Object> params = new HashMap<String, Object>();
	        		//	List<VehicleRegistrationDto> dtoList=new ArrayList();
	        			params.put("saleorderno", saleorderno);
	        			List<VehicleRegistrationDto> dtoList=vehicleRegistrationService.findDtos("queryToGetVehicleRegistrationreqno", params);
	                
	        			if(dtoList.size()>0) {
	        				String reqNo=dtoList.get(0).getRequestNo();
	        			
	        				resp.setSuccess(false);
	        				resp.setMessage("Request No is Already Generated and Request No is:-"+reqNo);
	        			    return resp;
	        				}
	        			else {
	        				try {
	        					
	        					vehicleRegistrationService.saveVehicleRegistration(vehicleRegistrationDto);
	        				
	        					
	        				}catch (Exception e) {
	        					resp.setSuccess(false);
	        					resp.setMessage(e.getMessage());
	        					return resp;
	        				}
	        				resp.setSuccess(true);
	        				resp.setMessage("Vehicle Registered Suceessfully Request No:-"+vehicleRegistrationDto.getRequestNo());
	        			    return resp;
	        	         }
			
	                
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
			}else {
				try {
					
					vehicleRegistrationService.saveVehicleRegistration(vehicleRegistrationDto);
				
					
				}catch (Exception e) {
					resp.setSuccess(false);
					resp.setMessage(e.getMessage());
					return resp;
				}
				resp.setSuccess(true);
				resp.setMessage("Vehicle Registered Suceessfully Request No:-"+vehicleRegistrationDto.getRequestNo());
			    return resp;
	         }
				
			

			
			
			
			
			
			
			
//			Map<String, Object> params = new HashMap<String, Object>();
//		//	List<VehicleRegistrationDto> dtoList=new ArrayList();
//			params.put("saleorderno", saleorderno);
//			List<VehicleRegistrationDto> dtoList=vehicleRegistrationService.findDtos("queryToGetVehicleRegistrationreqno", params);
//			if(saleorderno!=null) {
//				params.put("saleorderno", saleorderno);
//				dtoList=vehicleRegistrationService.findDtos("queryToGetVehicleRegistrationreqno", params);
//			}
//			else {
//				params.put("poNo", poNo);
//				dtoList=vehicleRegistrationService.findDtos("queryToGetVehicleRegistrationreqnobypo", params);
//			}
			
			
//			if(dtoList.size()>0) {
//			String reqNo=dtoList.get(0).getRequestNo();
//		
//			resp.setSuccess(false);
//			resp.setMessage("Request No is Already Generated and Request No is:-"+reqNo);
//		    return resp;
//			}
//			else {
//			try {
//				
//				vehicleRegistrationService.saveVehicleRegistration(vehicleRegistrationDto);
//			
//				
//			}catch (Exception e) {
//				resp.setSuccess(false);
//				resp.setMessage(e.getMessage());
//				return resp;
//			}
//			resp.setSuccess(true);
//			resp.setMessage("Vehicle Registered Suceessfully Request No:-"+vehicleRegistrationDto.getRequestNo());
//		    return resp;
//         }

		}
		return null;
	}
	

	@PostMapping(value = "/getVehicleRegistration")
	public @ResponseBody CustomResponseDto getVehicleRegistration() {
		CustomResponseDto resp = new CustomResponseDto();
		Map<String, Object> params = new HashMap<String, Object>();
		List<VehicleRegistrationDto> dtoList=vehicleRegistrationService.findDtos("queryToGetVehicleRegistration", params);
		resp.addObject("registrationList",dtoList);
		return resp;
		
		
//		CustomResponseDto resp = new CustomResponseDto();
//		Map<String, Object> params = new HashMap<String, Object>();
//		List<VehicleRegistrationDto> olddtoList=vehicleRegistrationService.findDtos("queryToGetVehicleRegistration", params);	
//		    vehicleRegistrationService.getVehicleRegistration(olddtoList);	
//			List<VehicleRegistrationDto> updateddtoList=vehicleRegistrationService.findDtos("queryToGetVehicleRegistration", params);
//			resp.addObject("registrationList",updateddtoList);
//			return resp;
	
		
	}
	
	@PostMapping(value="/getVehicleREGByID/{id}")
	public @ResponseBody CustomResponseDto getVehicleREGByID(@PathVariable("id") Long regID){
		/*byte[] driverBytes = null;byte[] docBytes = null;*/
		CustomResponseDto resp = new CustomResponseDto();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("vehicleRegistationId", regID);
		VehicleRegistrationDto dto=vehicleRegistrationService.findDto("getVehicleRegById", params);
		
		String saleOrder=dto.getSaleOrderNo();
		
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
		//String url="http://103.231.11.54:8000/sap/bc/zgate_web?saleOrderNo="+saleOrder+"";
		
	//	String url="https://172.18.2.33:44300/sap/bc/zgate_web?sap-client=110&SALEORDERNO="+saleOrder;
     String url="https://172.18.2.36:44300/sap/bc/zgate_web?sap-client=100&SALEORDERNO="+saleOrder;
   //  String url="https://172.18.2.29:44300/sap/bc/zgate_web?sap-client=100&SALEORDERNO="+saleOrder;
    // String url="https://172.18.2.28:44300/sap/bc/zgate_web?sap-client=100&SALEORDERNO="+saleOrder;
		URLConnection request=null;     
		try {
			 boolean shipToStatus=false;
			 boolean soldToStatus =false;
	            URL u = new URL(url);
	             request =  u.openConnection();

	            request.connect();


	            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
	            StringBuilder sb = new StringBuilder();
	            String line;
	            while ((line = br.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	            br.close();
	             ObjectMapper objJson = new ObjectMapper();
             VehicleRegistrationDto vrdto = objJson.readValue(sb.toString(), VehicleRegistrationDto.class);
             resp.addObject("vehiclesapDetails",vrdto);
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

		
		/*if(driverPic!=0 && docPic!=0){
		AttachmentDto driverattch = attService.findDto(driverPic);
		AttachmentDto docattch = attService.findDto(docPic);
		driverBytes=mediaService.getBISFromAttachment(driverattch);
		docBytes=mediaService.getBISFromAttachment(docattch);
		}*/
		 resp.addObject("vehicleDetails",dto);
	//	 resp.addObject("vehiclesapDetails",vrdto);
		 
			return resp;
	}
	
	@RequestMapping(value={"/reportVehicle"},method=RequestMethod.POST,produces="application/json")
	public @ResponseBody CustomResponseDto reportVehicle(@RequestBody VehicleRegistrationDto vehicleRegistrationDto) throws IOException {
	/*	Errors errors = new Errors();
		VehicleRegistrationValidator.validateVehicleRegistration(vehicleRegistrationDto, errors);
		if (errors.getErrorCount() > 0) {
			CustomResponseDto customResponseDto = new  CustomResponseDto();
			customResponseDto.setSuccess(false);
			customResponseDto.setMessage(errors.getErrorString());
			return customResponseDto;
		} else {*/
				Map<String, Object> param = AbstractContextServiceImpl.getParamMap("vehicleRegistrationNo", vehicleRegistrationDto.getVehicleRegistrationNo());
				param.put("licenseNo", vehicleRegistrationDto.getLicenseNo());
				param.put("tripLSP", vehicleRegistrationDto.getTripLSP());
				param.put("driverName", vehicleRegistrationDto.getDriverName());
				param.put("expDate", vehicleRegistrationDto.getExpDate());
				param.put("phoneNo", vehicleRegistrationDto.getPhoneNo());
				param.put("driverPicString", vehicleRegistrationDto.getDriverPicString());
				param.put("docPicString", vehicleRegistrationDto.getDocPicString());
				User reportedby= new User();
				reportedby.setUserId(contextService.getUser().getUserId());
				param.put("reportedby", reportedby);
				param.put("reporteddate", new Date());
				param.put("status",AppBaseConstant.STATUSREPOTED);
				
				int result = vehicleRegistrationService.updateByJpql(param,
						AbstractContextServiceImpl.getParamMap("vehicleRegistationId", vehicleRegistrationDto.getVehicleRegistationId()));
				if (result > 0) {
					CustomResponseDto resp = new CustomResponseDto(true, "Vehicle Reported");
					return resp;
				} else {
					return new CustomResponseDto(false, "Error While vehicle Report");
				}
		/*}*/
	}
	@PostMapping(value="/vehicleGateIN/{id}")
	public @ResponseBody CustomResponseDto vehicleGateIN(@PathVariable("id") Long regID){
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",AppBaseConstant.STATUSVEHICLEGATEIN);
		User gateInby= new User();
		gateInby.setUserId(contextService.getUser().getUserId());
		param.put("gateInby", gateInby);
		param.put("gateIndate", new Date());
		int result = vehicleRegistrationService.updateByJpql(param,
				AbstractContextServiceImpl.getParamMap("vehicleRegistationId", regID));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "Vehicle GATE IN");
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While GATE IN ");
		}
	}
	@PostMapping(value="/vehicleGateOUT/{id}")
	public @ResponseBody CustomResponseDto vehicleGateOUT(@PathVariable("id") Long regID) throws ParseException{
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",AppBaseConstant.STATUSVEHICLEGATEOUT);
		//Map<String, Object> param = AbstractContextServiceImpl.getParamMap("gateOutdate",new Date());
		param.put("gateOutdate", new Date());
		User gateOutby= new User();
		gateOutby.setUserId(contextService.getUser().getUserId());
		param.put("gateOutby", gateOutby);
		int result = vehicleRegistrationService.updateByJpql(param,
				AbstractContextServiceImpl.getParamMap("vehicleRegistationId", regID));
		if (result > 0) {
			CustomResponseDto isInvoiceSendinSAP = null;
			
			isInvoiceSendinSAP=vehicleRegistrationComponent.sendInvoiceToSAP(regID);
			
			if(isInvoiceSendinSAP.getMessage().equals("MAIL SENT SUCCESSFULLY")) {
			 CustomResponseDto resp = new CustomResponseDto(true, "Vehicle GATE OUT & MAIL SENT SUCCESSFULLY in SAP");
		     return resp;
			}else {
				CustomResponseDto resp = new CustomResponseDto(false, "Vehicle GATE OUT & Mail Sending Failed in SAP");
			     return resp;
			}
//			CustomResponseDto isInvoiceGenerated = null;
//			isInvoiceGenerated=vehicleRegistrationComponent.sendInvoiceAndGateOutDetailsToSAP(regID);
//			//vehicleRegistrationComponent.sendInvoiceAndGateOutDetailsToSAP(regID);
//			
//			if(isInvoiceGenerated.getMessage().equals("invoice found successfully  ..")) {
//				Map<String, Object> params = AbstractContextServiceImpl.getParamMap("status",AppBaseConstant.STATUSVEHICLEGATEOUT);
//				int result1 = vehicleRegistrationService.updateByJpql(params,
//						AbstractContextServiceImpl.getParamMap("vehicleRegistationId", regID));
//			    CustomResponseDto resp = new CustomResponseDto(true, "Vehicle GATE OUT ");
//			     return resp;
//			}else {
//				return new CustomResponseDto(false, "invoice not found ,PLS CHECK ..");
//			}
			
		} else {
			return new CustomResponseDto(false, "Error While GATE OUT ");
		}
	}
	
	@PostMapping(value="/vehicleGateOUTSTO/{id}/{invoiceNo}")
	public @ResponseBody CustomResponseDto vehicleGateOUTSTO(@PathVariable("id") Long regID,@PathVariable("invoiceNo") String invoiceNo){
	
//		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",AppBaseConstant.STATUSVEHICLEGATEOUT);
//		param.put("gateOutdate", new Date());
//		User gateOutby= new User();
//		gateOutby.setUserId(contextService.getUser().getUserId());
//		param.put("gateOutby", gateOutby);
//		int result = vehicleRegistrationService.updateByJpql(param,
//				AbstractContextServiceImpl.getParamMap("vehicleRegistationId", regID));
		
		
		CustomResponseDto isInvoiceGenerated = null;
		isInvoiceGenerated=vehicleRegistrationComponent.checkInvoiceFromSAP(regID,invoiceNo);
		
		if(isInvoiceGenerated.getMessage().equals("invoice Matches")) {
			Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",AppBaseConstant.STATUSVEHICLEGATEOUT);
			param.put("gateOutdate", new Date());
			User gateOutby= new User();
			gateOutby.setUserId(contextService.getUser().getUserId());
			param.put("gateOutby", gateOutby);
			int result = vehicleRegistrationService.updateByJpql(param,
					AbstractContextServiceImpl.getParamMap("vehicleRegistationId", regID));
			CustomResponseDto resp = new CustomResponseDto(true, "Vehicle GATE OUT ");
			return resp;
			
		} else {
			
			return new CustomResponseDto(false, "invoice not found ,PLS CHECK ..");
			
		}
//		if (result > 0) {
//			vehicleRegistrationComponent.checkInvoiceFromSAP(regID,invoiceNo);
//			CustomResponseDto resp = new CustomResponseDto(true, "Vehicle GATE OUT ");
//			return resp;
//		} 
//		else {
//			return new CustomResponseDto(false, "Error While GATE OUT ");
//		}
		
	}
	
	@RequestMapping(value={"/generateVehicleInvoice"},method=RequestMethod.POST,produces="application/json")
	public @ResponseBody CustomResponseDto generateVehicleInvoice(@RequestBody VehicleRegistrationDto vehicleRegistrationDto) {
		AttachmentDto dto = new AttachmentDto();
		dto.setName(vehicleRegistrationDto.getInvoiceNo()+".pdf");
		dto.setFileName(vehicleRegistrationDto.getInvoiceNo()+".pdf");
		dto.setPath(vehicleRegistrationDto.getDestination());
		dto =attService.save(dto);
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("invoiceNo", vehicleRegistrationDto.getInvoiceNo());
		param.put("invoiceDate",vehicleRegistrationDto.getInvoiceDate());
		Attachment attach = new Attachment();
		attach.setAttachmentId(dto.getAttachmentId());
		param.put("docPic", attach);
		int result = vehicleRegistrationService.updateByJpql(param,
				AbstractContextServiceImpl.getParamMap("vehicleRegistationId", vehicleRegistrationDto.getVehicleRegistationId()));
		if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "invoice generated");
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While invoice generation");
		}
	}
	@PostMapping(value="/getSalesOrderDetails/{id}")
	public @ResponseBody VehicleRegistrationDto getSalesOrderDetails(@PathVariable("id") String saleOrder){
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
		//String url="http://103.231.11.54:8000/sap/bc/zgate_web?saleOrderNo="+saleOrder+"";
		
	//	String url="https://172.18.2.33:44300/sap/bc/zgate_web?sap-client=110&SALEORDERNO="+saleOrder;
        String url="https://172.18.2.36:44300/sap/bc/zgate_web?sap-client=100&SALEORDERNO="+saleOrder;
     //   String url="https://172.18.2.29:44300/sap/bc/zgate_web?sap-client=100&SALEORDERNO="+saleOrder;
		URLConnection request=null;     
		try {
			 boolean shipToStatus=false;
			 boolean soldToStatus =false;
	            URL u = new URL(url);
	             request =  u.openConnection();

	            request.connect();


	            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
	            StringBuilder sb = new StringBuilder();
	            String line;
	            while ((line = br.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	            br.close();
	             ObjectMapper objJson = new ObjectMapper();
                VehicleRegistrationDto dto = objJson.readValue(sb.toString(), VehicleRegistrationDto.class);
                if(null!=dto.getShiptogstin()){
               shipToStatus= getGSTNDEtails.getAuth(dto.getShiptogstin());
                }
                if(null!=dto.getSoldtogstin()){
                 soldToStatus= getGSTNDEtails.getAuth(dto.getSoldtogstin());
                     }
                if(!shipToStatus && !soldToStatus){
                //	dto.setMessage("Both Party GSTIN Not Active");
                	dto.setMessage("GSTIN Not Active");
                	dto.setSuccess(false);
                	return dto;
                }
                if(!shipToStatus){
                	dto.setMessage("Ship To Party GSTIN Not Active");
                	dto.setSuccess(false);
                	return dto;
                }
                if(!soldToStatus){
                	dto.setMessage("Bill To Party GSTIN Not Active");
                	dto.setSuccess(false);
                	return dto;
                 }
             //   dto.setMessage("Both Party GSTIN Active");
                dto.setMessage("GSTIN Active");
                dto.setSuccess(true);
            	return dto;
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
	
	@PostMapping(value = "/getSTOVehicleSAP/{pono}")
	public @ResponseBody VehicleRegistrationDto getSTOVehicleSAP(@PathVariable("pono") String pono) {
		CustomResponseDto resp = new CustomResponseDto();
		
		Map<String, Object> params = new HashMap<String, Object>();
		List<String> param = new ArrayList<>();
		
		params.put("pono", pono);
		
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
					
				//	String url="https://172.18.2.28:44300/sap/bc/yweb03_ws_35?sap-client=100&pono=" + pono;
				//	String url="https://172.18.2.33:44300/sap/bc/yweb03_ws_35?sap-client=110&pono=" + pono;
				//	String url="https://172.18.2.36:44300/sap/bc/yweb03_ws_35?sap-client=100&pono=" + pono;
				String url=	"https://172.18.2.36:44300/sap/bc/yweb03_ws_41?sap-client=100&pono="+ pono;
				//	 String url="https://172.18.2.36:44300/sap/bc/yweb03_ws_21?sap-client=100&pono=" + pono;
				//	 String url="https://172.18.2.33:44300/sap/bc/yweb03_ws_21?sap-client=110&pono=" + pono;
				//	String url="https://172.18.2.28:44300/sap/bc/yweb03_ws_21?sap-client=100&pono="+pono;
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
						
						 ObjectMapper objJson = new ObjectMapper();

						 
						 JSONArray headerarray=new JSONArray(sb.toString());
						 JSONObject item = headerarray.getJSONObject(0);
						 objJson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
						 VehicleRegistrationDto dto = objJson.readValue(item.toString(), VehicleRegistrationDto.class);
						
			             
						 return dto;
					
					      

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
		

		
		return null;

	}

	
	@PostMapping(value="/getInvoiceDetails/{id}/{regId}")
	public @ResponseBody String getInvoiceDetails(@PathVariable("id") String saleOrder,@PathVariable("regId") Long regId){
	//	String url="http://103.231.11.54:8000/sap/bc/yweb03_ws_02?saleorderno="+saleOrder+"";
	//	String url="https://172.18.2.33:44300/sap/bc/yweb03_ws_02?sap-client=110&SALEORDERNO="+saleOrder;
		String url="https://172.18.2.36:44300/sap/bc/yweb03_ws_02?sap-client=100&SALEORDERNO="+saleOrder;
	//	String url="https://172.18.2.29:44300/sap/bc/yweb03_ws_02?sap-client=100&SALEORDERNO="+saleOrder;
	//	String url="https://172.18.2.28:44300/sap/bc/yweb03_ws_02?sap-client=100&SALEORDERNO="+saleOrder;
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
	            br.close();
               /* ObjectMapper objJson = new ObjectMapper();
                VehicleRegistrationDto dto = objJson.readValue(sb.toString(), VehicleRegistrationDto.class);
                vehicleRegistrationComponent.saveInvoiceMailDetail(dto,regId);*/
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
	@PostMapping(value="/getSalesOrderList")
	public @ResponseBody List<SAPSalesOrderDto> getSalesOrderList(@RequestBody SAPSalesOrderDto saledto){
	//public @ResponseBody List<SAPSalesOrderDto> getSalesOrderList(@PathVariable("fdate") String fromDate,@PathVariable("tdate") String toDAte,@PathVariable("plant") String plant){
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
      
        String url="";
        String fromDate=saledto.getFdate();
        String toDAte=saledto.getTdate();
        String plant=saledto.getPlant();
        if(plant==null && toDAte!=null) {
        // url="https://172.18.2.33:44300/sap/bc/yweb03_ws_10?sap-client=110&fdate1="+fromDate+"&tdate1="+toDAte;
        	 url="https://172.18.2.36:44300/sap/bc/yweb03_ws_10?sap-client=100&fdate1="+fromDate+"&tdate1="+toDAte;
        }else if(plant==null && toDAte==null) {
        //	url="https://172.18.2.33:44300/sap/bc/yweb03_ws_10?sap-client=110&fdate1="+fromDate;
        	 url="https://172.18.2.36:44300/sap/bc/yweb03_ws_10?sap-client=100&fdate1="+fromDate;
        }
        else {
         //url="https://172.18.2.33:44300/sap/bc/yweb03_ws_10?sap-client=110&fdate1="+fromDate+"&tdate1="+toDAte+"&plant="+plant;
           url="https://172.18.2.36:44300/sap/bc/yweb03_ws_10?sap-client=100&fdate1="+fromDate+"&tdate1="+toDAte+"&plant="+plant;
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
	            br.close();
                ObjectMapper objJson = new ObjectMapper();
                SAPSalesOrderDto[] dto = objJson.readValue(sb.toString(), SAPSalesOrderDto[].class);
                
               List<SAPSalesOrderDto> orderList= new ArrayList<SAPSalesOrderDto>();
               for(SAPSalesOrderDto dtos:dto){
            	   orderList.add(dtos);
               }
               orderList=vehicleRegistrationComponent.getReqNoFromSalesOrder(orderList);
	            return orderList;


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
	@PostMapping(value="/createVehicleRegistartion/{id}")
	public @ResponseBody CustomResponseDto createVehicleRegistartion(@PathVariable("id") String saleOrder){
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
		//String url="http://103.231.11.54:8000/sap/bc/zgate_web?saleOrderNo="+saleOrder+"";
		
		//String url="https://172.18.2.33:44300/sap/bc/zgate_web?sap-client=110&SALEORDERNO="+saleOrder;
        String url="https://172.18.2.36:44300/sap/bc/zgate_web?sap-client=100&SALEORDERNO="+saleOrder;
      //  String url="https://172.18.2.28:44300/sap/bc/zgate_web?sap-client=100&SALEORDERNO="+saleOrder;
		//String url="http://103.231.11.54:8000/sap/bc/zgate_web?saleOrderNo="+saleOrder+"";
		URLConnection request=null;     
		try {
			 boolean shipToStatus=false;
			 boolean soldToStatus =false;
	            URL u = new URL(url);
	             request =  u.openConnection();

	            request.connect();


	            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
	            StringBuilder sb = new StringBuilder();
	            String line;
	            while ((line = br.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	            br.close();
	             ObjectMapper objJson = new ObjectMapper();
                VehicleRegistrationDto dto = objJson.readValue(sb.toString(), VehicleRegistrationDto.class);
//                if(null!=dto.getShiptogstin()){
//               shipToStatus= getGSTNDEtails.getAuth(dto.getShiptogstin());
//                }
//                if(null!=dto.getSoldtogstin()){
//                 soldToStatus= getGSTNDEtails.getAuth(dto.getSoldtogstin());
//                     }
//                if(!shipToStatus && !soldToStatus){
//                	//resp.setMessage("Both Party GSTIN Not Active");
//                	resp.setMessage("GSTIN Not Active");
//                	resp.setSuccess(false);
//                	return resp;
//                }
//                if(!shipToStatus){
//                	resp.setMessage("Ship To Party GSTIN Not Active");
//                	resp.setSuccess(false);
//                	return resp;
//                }
//                if(!soldToStatus){
//                	resp.setMessage("Bill To Party GSTIN Not Active");
//                	resp.setSuccess(false);
//                	return resp;
//                 }
                
                try {
                	Map<String, Object> params = new HashMap<String, Object>();
            		params.put("username", dto.getMeCode().getUserName());
            		UserDto meUser=userService.findDto("getUserBYUserName", params);
            		if(null!=meUser){
            			dto.setMeCode(null);
            			UserDto user = new UserDto();
            			user.setUserId(meUser.getUserId());
            			dto.setMeCode(user);
            			vehicleRegistrationService.saveVehicleRegistration(dto);
            		}else{
            			dto.setMeCode(null);
            			vehicleRegistrationService.saveVehicleRegistration(dto);
            		}
    				
    			}catch (Exception e) {
    				resp.setSuccess(false);
    				resp.setMessage(e.getMessage());
    				return resp;
    			}
    			resp.setSuccess(true);
    			resp.setData(dto.getRequestNo());
    			resp.setMessage("Vehicle Registered Suceessfully Request No:-"+dto.getRequestNo());
    		   return resp;
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
	
	
//	@PostMapping(value = "/getASNReport")
//	public @ResponseBody CustomResponseDto getASNReport(@RequestBody ASNReadDto dto) {

//		CustomResponseDto resp = new CustomResponseDto();
//		List<AdvanceShipmentNoticeDto> asnreportlist = asnService.getASNReportbyFilter(dto);

//		resp.addObject("AsnReportList", asnreportlist);
		
//		return resp;
//		// return new CustomResponseDto("AsnReportList", asnreport);
//	}
	
	
	@PostMapping(value = "/getOutwardReport")
	public @ResponseBody CustomResponseDto getOutwardReport(@RequestBody OutwardReadDto dto ) {
		CustomResponseDto resp = new CustomResponseDto();
		List<VehicleRegistrationDto> outwardReportList=vehicleRegistrationService.getOutwardReportbyFilter(dto);
		
	resp.addObject("outwardReportList", outwardReportList);
		
		return resp;
		
	}
	
	@PostMapping(value = "/cancelVehicleRegistration/{id}/{remarks}")
	public @ResponseBody CustomResponseDto cancelASN(@PathVariable("id") Long regID,
			@PathVariable("remarks") String remarks) {
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",
				AppBaseConstant.STATUSVEHICLECANCEL);
		param.put("remarks", remarks);
		int result = vehicleRegistrationService.updateByJpql(param,
		AbstractContextServiceImpl.getParamMap("vehicleRegistationId", regID));
	if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "Request Canceled.");
			resp.addObject("status", AppBaseConstant.STATUSVEHICLECANCEL);
			return resp;
		} else {
			return new CustomResponseDto(false, "Error While Canceling ASN");
		}

	}
	
	@PostMapping(value = "/revokeCancellation/{id}")
	public @ResponseBody CustomResponseDto cancelASN(@PathVariable("id") Long regID) {
		Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",
				AppBaseConstant.STATUSCREATED);
		int result = vehicleRegistrationService.updateByJpql(param,
		AbstractContextServiceImpl.getParamMap("vehicleRegistationId", regID));
	if (result > 0) {
			CustomResponseDto resp = new CustomResponseDto(true, "Request Revoked.");
			resp.addObject("status", AppBaseConstant.STATUSCREATED);
			return resp;
		} else {
			return new CustomResponseDto(false, "Request cancellation is revoked");
		}

	}
	
//	@RequestMapping(value={"/NicerGlobeVehicleStatusUpdate/{vehicleno}/{requestno}/{status}/{datetime}"},method=RequestMethod.GET)
	@GetMapping(value = "/NicerGlobeVehicleStatusUpdate/{vehicleRegistrationNo}/{requestNo}/{status}/{datetime}/**")
	public String NicerGlobeVehicleStatusUpdate(@PathVariable("vehicleRegistrationNo") String vehicleRegistrationNo,@PathVariable("requestNo") String requestNo,@PathVariable("status") String status,@PathVariable("datetime") String datetime,HttpServletRequest request) throws ParseException {

		String Response="";
		
		 final String path =request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
		 final String bestMatchingPattern =request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();

		    String arguments = new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, path);

		//    String moduleName;
		    if (null != arguments && !arguments.isEmpty()) {
		    	datetime = datetime + '/' + arguments;
		    } else {
		    	datetime = datetime;
		    }
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("vehicleRegistrationNo", vehicleRegistrationNo);
		params.put("requestNo", requestNo);
		
		List<VehicleRegistrationDto> dtoList=vehicleRegistrationService.findDtos("queryToGetVehicleRegistrationnicergloabe", params);
		
		Map<String, Object> param = new HashMap<String, Object>();
		if (dtoList.size() > 0) {
		    if(dtoList!=null) {
		    	if(status.equals("VGI")) {
		    		param.put("status", status);
		    		String dateString = datetime;
		    		SimpleDateFormat changeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		    	    java.util.Date temp = changeFormat.parse(dateString);
		    	     
		    		param.put("gateIndate",temp);
		    	}else if(status.equals("VGO")) {
		    		param.put("status", status);
		    		String dateString = datetime;
		    		SimpleDateFormat changeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		    	    java.util.Date temp = changeFormat.parse(dateString);
		    	
		    		param.put("gateOutdate",temp);
		    		
		    	}else if(status.equals("DESTINATIONGATEIN")) {
		    		param.put("status", status);
		    		String dateString = datetime;
		    		SimpleDateFormat changeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		    	    java.util.Date temp = changeFormat.parse(dateString);
		    	
		    		param.put("destinationgateindate",temp);
		    		
		    	}else {
		    		param.put("status", status);
		    		String dateString = datetime;
		    		SimpleDateFormat changeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		    	    java.util.Date temp = changeFormat.parse(dateString);
		    	
		    		param.put("destinationgateoutdate",temp);
		    		
		    	}
		    	
		    	
		    	Long regID=dtoList.get(0).getVehicleRegistationId();
		    	
		    	int result = vehicleRegistrationService.updateByJpql(param,
		    			AbstractContextServiceImpl.getParamMap("vehicleRegistationId", regID));
		    	
		    	 Response="OK";
		    	
	
		     }
		}
		else {
			 Response="Failed";
			
		}
		
		return Response;
		}
}
