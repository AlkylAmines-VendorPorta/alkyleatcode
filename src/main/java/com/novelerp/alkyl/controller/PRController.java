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
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelerp.alkyl.component.PRComponent;
import com.novelerp.alkyl.dto.AdvancePaymentDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.POReadDto;
import com.novelerp.alkyl.dto.PRAttachmentDto;
import com.novelerp.alkyl.dto.PRDto;
import com.novelerp.alkyl.dto.PRLineDto;
import com.novelerp.alkyl.dto.PRLineFilterDto;
import com.novelerp.alkyl.dto.PRLineFromSapDto;
import com.novelerp.alkyl.dto.PRReadDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.dto.ThirdPartyPRApproverDto;
import com.novelerp.alkyl.entity.PRAttachment;
import com.novelerp.alkyl.service.PRAttachmentService;
import com.novelerp.alkyl.service.PRLineService;
import com.novelerp.alkyl.service.PRService;
import com.novelerp.alkyl.validator.PRValidator;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.UserService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;

@Controller
@RequestMapping("/rest")
public class PRController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PRComponent prComponent;
	
	@Autowired
	private PRService prService;
	
	@Autowired
	private PRValidator prValidator;
	
	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PRLineService prLineService;
	
	@Autowired
	private PRAttachmentService prAttachmentService;
	
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	
	@PostMapping(value="/getPRJson")
	public @ResponseBody PRDto getPRJson(){
		PRDto pr = new PRDto();
		UserDto reqB = new UserDto();
		
		UserDto tcApprover = new UserDto();
		pr.setTcApprover(tcApprover);
		
		UserDto buyer = new UserDto();
		pr.setBuyer(buyer);
		
		UserDto approvedBy = new UserDto();
		pr.setApprovedBy(approvedBy);
		
		pr.setRequestedBy(reqB);
		PRLineDto prLine = new PRLineDto();
		UserDto desiredVendor = new UserDto();
		UserDto fixedVendor = new UserDto();
		prLine.setDesiredVendor(desiredVendor);
		prLine.setFixedVendor(fixedVendor);
		PRLineDto prServiceLine = new PRLineDto();
		List<PRLineDto> prServiceLines= new ArrayList<>();
		prServiceLines.add(prServiceLine);
		prLine.setPrServiceLines(prServiceLines);
		List<PRLineDto> prLines= new ArrayList<>();
		prLines.add(prLine);
		prLine.setDeliverDate(new Date());
		prLine.setRequiredDate(new Date());
		pr.setPrLines(prLines);
		return pr;
	}
	
	@PostMapping(value="/sapPRCreation")
	public @ResponseBody CustomResponseDto sapPRCreation(@RequestBody PRDto prDto){
		CustomResponseDto resp = new CustomResponseDto();
		Errors errors = new Errors();
		prComponent.savePR(prDto, errors, "");
		return resp;
	}
	
	@Scheduled(cron="0 0/10 * * * ?")
	@PostMapping(value="/fetchPR")
	public @ResponseBody CustomResponseDto fetchPR(){
		
		Errors errors = prComponent.fetchPRFromFTP();
		if(errors.getErrorCount()>0){
			CustomResponseDto resp = new CustomResponseDto(false,errors.getErrorStringWithCode());
			return resp;
		}
		
		return new CustomResponseDto(true, "Fetch Complete");
	}
	
	@PostMapping(value="/fetchPRfromWebservicetodisplay/{prNo}")
	public @ResponseBody CustomResponseDto fetchPRfromWebservicetodisplay(@PathVariable("prNo") String prNo){
		
           CustomResponseDto resp = new CustomResponseDto();
		
		Map<String, Object> params = new HashMap<String, Object>();
		List<String> param = new ArrayList<>();
			
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
		
					String url="https://172.18.2.36:44300/sap/bc/yweb03_WS_50?sap-client=100&PR_NO="+prNo;
				//	String url="https://172.18.2.29:44300/sap/bc/yweb03_WS_50?sap-client=100&PR_NO="+prNo;
					
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
						
					
						
				//	String Message = obj.getString("message");
						
//						if (Message.equals("PO not yet released")) {
//
//							resp.setMessage("PO not yet released");
//							resp.setSuccess(false);
//
//						} else if (Message.equals("PO NOT FOUND,PLS CHECK PO NO..")) {
//							resp.setMessage("PO NOT FOUND,PLS CHECK PO NO..");
//							resp.setSuccess(false);
//						}
//						else {
						//	JSONObject headerArray = obj.getJSONObject("HEADER");
							System.out.println(obj);
						//	System.out.println(headerArray);
							
							 ObjectMapper objJson = new ObjectMapper();
							 
							 objJson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
							 PRDto dto = objJson.readValue(obj.toString(), PRDto.class);
							 dto.setStatus(AppBaseConstant.PR_STATUS_CREATED);
							// PurchaseOrderDto dto = objJson.readValue(headerArray.toString(), PurchaseOrderDto.class);
							 List<PRDto> prList= new ArrayList<PRDto>();
							
							 prList.add(dto);
							 
							 resp.addObject("prList", prList);

//						}
					

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
	
@PostMapping(value = "/savePRfromWebservice/{prNo}")
	
	public @ResponseBody CustomResponseDto savePRfromWebservice(@PathVariable("prNo") String prNo) {
		CustomResponseDto resp = new CustomResponseDto();

		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("prNo", prNo);
		
		PRDto prdto=prService.findDto("getPlainPRByPRNumber", AbstractContextServiceImpl.getParamMap("prNo", prNo));
		if(prdto!=null){
			return new CustomResponseDto(false,"PR already exist");
		}

		if (contextService != null && contextService.getDefaultRole() != null
				&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())) {

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

				
				//	String url="https://172.18.2.29:44300/sap/bc/yweb03_WS_50?sap-client=100&PR_NO="+prNo;
					String url="https://172.18.2.36:44300/sap/bc/yweb03_WS_50?sap-client=100&PR_NO="+prNo;
				
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
//						String Message = obj.getString("message");
//						
//						if (Message.equals("PO not yet released")) {
//
//							resp.setMessage("PO not yet released");
//							resp.setSuccess(false);
//
//						} else if (Message.equals("PO NOT FOUND,PLS CHECK PO NO..")) {
//							resp.setMessage("PO NOT FOUND,PLS CHECK PO NO..");
//							resp.setSuccess(false);
//						}
						

//						else {
							// JSONObject headerArray = (JSONObject) obj.getJSONArray("HEADER").get(0);
							// JSONArray headerArray=obj.getJSONArray("HEADER");
							
							String role = null;
							if (contextService != null && contextService.getDefaultRole() != null
									&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())) {
								role = contextService.getDefaultRole().getValue();
							}
							
							//JSONObject headerArray = obj.getJSONObject("HEADER");
							
								ObjectMapper mapper = new ObjectMapper();
								mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
								mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

								 PRDto[] dto = mapper.readValue(obj.toString(), PRDto[].class);
								// PRDto[] dto = mapper.readValue(headerArray.toString(), PRDto[].class);
								 //PurchaseOrderDto[] dto = mapper.readValue(headerArray.toString(), PurchaseOrderDto[].class);
								boolean isPRCreated = false;								
								for (PRDto dtos : dto) {
								//	dtos.setStatus("REL");
									isPRCreated = prComponent.createPRFromSAP(dtos);
								}
								
								if(isPRCreated) {
									resp.setMessage("PR Fetched SucessFully");
									resp.setSuccess(true);
								}else {
									resp.setMessage("PR Fetched Failed");
									resp.setSuccess(false);
								}
								
									
								
						//}
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

	
	@PostMapping(value="/getPR")
	public @ResponseBody CustomResponseDto getPR(){
		CustomResponseDto resp = null;List<PRDto> prList = null;List<PRLineDto> prLineList=null; String role =null;BPartnerDto partner = null;
		Map<String,String> plantList=null;
		Map<String, String> prStatusList = refListService.getReferenceListMap(CoreReferenceConstants.PR_STATUS);
	
		
		if(contextService!=null && contextService.getDefaultRole()!=null 
				&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())){
			role =contextService.getDefaultRole().getValue();
			partner = contextService.getPartner();
		}
		if(!AppBaseConstant.ROLE_PURCHASE_MANAGER_ADMIN.equals(role)){
			if(!AppBaseConstant.ROLE_BUYER_ADMIN.equals(role)){
		prList=prComponent.getPR(role,contextService.getUserDetails());
			}
		}
		
		if(AppBaseConstant.ROLE_PURCHASE_MANAGER_ADMIN.equals(role)){
			prLineList=prComponent.getPRLine(role, contextService.getUserDetails());
			plantList =refListService.getReferenceListMap(CoreReferenceConstants.PlANT);
		}
		if(AppBaseConstant.ROLE_BUYER_ADMIN.equals(role)){
//			prLineList=prComponent.findPRLineForBuyer(contextService.getUser().getUserId());
			prLineList=null;
			plantList =refListService.getReferenceListMap(CoreReferenceConstants.PlANT);
		}
		
		List<UserDto> buyerList=userService.findDtos("getUserListByRole", 
				AbstractContextServiceImpl.getParamMap("value", AppBaseConstant.ROLE_BUYER_ADMIN));
		
		List<UserDto> technicalList=userService.findDtos("getUserListByRole", 
				AbstractContextServiceImpl.getParamMap("value", AppBaseConstant.ROLE_TECHNICAL_ADMIN));
		
		Map<String, String> priorityList = refListService.getReferenceListMap(CoreReferenceConstants.PRIORITY);
		
		resp=new CustomResponseDto("prList", prList);
		resp.addObject("prStatusList", prStatusList);
		resp.addObject("role", role);
		resp.addObject("partner", partner);
		resp.addObject("buyerList", buyerList);
		resp.addObject("technicalList", technicalList);
		resp.addObject("priorityList", priorityList);
		resp.addObject("prLineList", prLineList);
		resp.addObject("plantList",plantList);
		
		return resp;
	}
	
	@PostMapping(value="/updatePRSubmit")
	public @ResponseBody CustomResponseDto updatePRSubmit(@RequestBody PRDto prDto){
		CustomResponseDto respDto = null;
		try {
			Errors errors = new Errors();
			prValidator.validateSubmit(prDto, errors);
			
			if(errors.getErrorCount()>0){
				respDto=new CustomResponseDto(false, errors.getErrorString());
				return respDto;
			}
			
			prDto=prService.updatePRSubmit(prDto);
			if(prDto.getResponse().isHasError()){
				return new CustomResponseDto(false,"Failed to Update Record");
			}
			respDto=new CustomResponseDto(true,"PR Released");
			respDto.addObject("prDto", prDto);
		} catch (Exception e) {
			log.info("ERROR",e);
			return new CustomResponseDto(false,e.getMessage());
		}
		return respDto;
	}
	@PostMapping(value="/updatePRSave")
	public @ResponseBody CustomResponseDto updatePRSave(@RequestBody PRDto prDto){
		CustomResponseDto respDto = null;
		try {
			Errors errors = new Errors();
			prValidator.validateSubmit(prDto, errors);
			
			if(errors.getErrorCount()>0){
				respDto=new CustomResponseDto(false, errors.getErrorString());
				return respDto;
			}
			
			prDto=prService.updatePRSave(prDto);
			if(prDto.getResponse().isHasError()){
				return new CustomResponseDto(false,"Failed to Update Record");
			}
			respDto=new CustomResponseDto(true,"PR Saved");
			respDto.addObject("prDto", prDto);
		} catch (Exception e) {
			log.info("ERROR",e);
			return new CustomResponseDto(false,e.getMessage());
		}
		return respDto;
	}
	
	@PostMapping(value="/updatePRApprove")
	public @ResponseBody CustomResponseDto updatePRApprove(@RequestBody PRDto prDto){
		CustomResponseDto respDto = null;
		try {
			Errors errors = new Errors();
			prValidator.validateApprove(prDto, errors);
			
			if(errors.getErrorCount()>0){
				respDto=new CustomResponseDto(false, errors.getErrorString());
				return respDto;
			}
			prDto.setApprovedBy(contextService.getUser());
			prDto=prService.updatePRApprove(prDto);
			if(prDto.getResponse().isHasError()){
				return new CustomResponseDto(false,"Failed to Update Record");
			}
			respDto=new CustomResponseDto(true,"Approved");
			respDto.addObject("prDto", prDto);			
		} catch (Exception e) {
			log.info("ERROR",e);
			return new CustomResponseDto(false,e.getMessage());
		}
		return respDto;
	}
	
	@PostMapping(value="/updatePRReject/{prId}/{remarks}")
	public @ResponseBody CustomResponseDto updatePRReject(@PathVariable("prId") Long prId,@PathVariable("remarks") String remarks){
		ResponseDto respDto = null;
		try {
			Errors errors = new Errors();
			prValidator.validateReject(prId,contextService.getDefaultRole().getValue(), errors);
			
			if(errors.getErrorCount()>0){
				return new CustomResponseDto(false, errors.getErrorString());
			}
			
			respDto=prService.updatePRReject(prId,remarks);
			if(respDto.isHasError()){
				return new CustomResponseDto(false,"Failed to Update Record");
			}
			return new CustomResponseDto(AppBaseConstant.PR_STATUS_REJECTED,"Rejected",true);
		} catch (Exception e) {
			log.info("ERROR",e);
			return new CustomResponseDto(false,e.getMessage());
		}
	}
	
	@PostMapping(value="/updatePRBuyerAssign")
	public @ResponseBody CustomResponseDto updatePRBuyerAssign(@RequestBody PRDto prDto){
		CustomResponseDto respDto = null;
		try {
			Errors errors = new Errors();
			prValidator.validateBuyerAssign(prDto, errors);
			
			if(errors.getErrorCount()>0){
				return respDto=new CustomResponseDto(false, errors.getErrorString());
			}
			
			prDto=prService.updatePRBuyerAssign(prDto);
			if(prDto.getResponse().isHasError()){
				return new CustomResponseDto(false,"Failed to Update Record");
			}
			respDto=new CustomResponseDto(true,"Buyer Assigned");
			respDto.addObject("prDto", prDto);
		}
		catch (Exception e) {
			log.info("ERROR",e);
			return new CustomResponseDto(false,e.getMessage());
		}
		return respDto;
	}
	
	
	@PostMapping(value = "/getPRLinebyPrId/{prId}")
	public @ResponseBody CustomResponseDto getPRLinebyPrId(@PathVariable("prId") Long prId){
		CustomResponseDto resp = new CustomResponseDto();
		
		List<PRLineDto> prLine=prComponent.findPRById(prId);
		List<ThirdPartyPRApproverDto> emailList=prComponent.findThirdPartyPRId(prId);
		List<PRAttachmentDto> prAtt=prComponent.findAttbyPRId(prId);
		Map<String, Object> params = new HashMap<String, Object>();
		List<UserDto> internalUserList=userService.findDtos("getAllInternalUser", params);
		resp.addObject("prLine", prLine);
		resp.addObject("emailList", emailList);
		resp.addObject("attachmentList", prAtt);
		resp.addObject("internalUserList", internalUserList);
		return resp;
	}
	
	@PostMapping(value="/getPRforEnquiry")
	public @ResponseBody CustomResponseDto getPRforEnquiry(){
		CustomResponseDto resp = null;List<PRDto> prList = null;String role =null;BPartnerDto partner = null;
		Map<String, String> prStatusList = refListService.getReferenceListMap(CoreReferenceConstants.PR_STATUS);
		
		if(contextService!=null && contextService.getDefaultRole()!=null 
				&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())){
			role =contextService.getDefaultRole().getValue();
			partner = contextService.getPartner();
		}
		
		prList=prComponent.getPRforEnquiry(role,contextService.getUserDetails());
		
		List<UserDto> buyerList=userService.findDtos("getUserListByRole", 
				AbstractContextServiceImpl.getParamMap("value", AppBaseConstant.ROLE_BUYER_ADMIN));
		
		List<UserDto> technicalList=userService.findDtos("getUserListByRole", 
				AbstractContextServiceImpl.getParamMap("value", AppBaseConstant.ROLE_TECHNICAL_ADMIN));
		
		Map<String, String> priorityList = refListService.getReferenceListMap(CoreReferenceConstants.PRIORITY);
		
		resp=new CustomResponseDto("prList", prList);
		resp.addObject("prStatusList", prStatusList);
		resp.addObject("role", role);
		resp.addObject("partner", partner);
		resp.addObject("buyerList", buyerList);
		resp.addObject("technicalList", technicalList);
		resp.addObject("priorityList", priorityList);
		
		return resp;
	}
	@PostMapping(value="/getPRLineByFilter")
	public @ResponseBody CustomResponseDto getPRLineByFilter(@RequestBody PRLineFilterDto prLineDto){
		CustomResponseDto respDto = null;
//		List<PRLineDto> prLineList=null;
		List<PRLineDto> prLineList=null;
		Map<String,String> plantList=null;
		Map<String, String> prStatusList = refListService.getReferenceListMap(CoreReferenceConstants.PR_STATUS);
		List<UserDto> buyerList=userService.findDtos("getUserListByRole", 
				AbstractContextServiceImpl.getParamMap("value", AppBaseConstant.ROLE_BUYER_ADMIN));
		
		List<UserDto> technicalList=userService.findDtos("getUserListByRole", 
				AbstractContextServiceImpl.getParamMap("value", AppBaseConstant.ROLE_TECHNICAL_ADMIN));
		
		Map<String, String> priorityList = refListService.getReferenceListMap(CoreReferenceConstants.PRIORITY);
		
		
		
		String role =null;BPartnerDto partner = null;
		
		if(contextService!=null && contextService.getDefaultRole()!=null 
				&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())){
			role =contextService.getDefaultRole().getValue();
			partner = contextService.getPartner();
		}
		
		if(AppBaseConstant.ROLE_PURCHASE_MANAGER_ADMIN.equals(role)){
//			prLineList=prComponent.getPRLine(role, contextService.getUserDetails());
			plantList =refListService.getReferenceListMap(CoreReferenceConstants.PlANT);
		}
		if(AppBaseConstant.ROLE_BUYER_ADMIN.equals(role)){
//			prLineList=prComponent.findPRLineForBuyer(contextService.getUser().getUserId());
			plantList =refListService.getReferenceListMap(CoreReferenceConstants.PlANT);
		}
		
		try {
			/*oldCode*/
		//	prLineList=prLineService.getPRLineListBYFilter(prLineDto);
			
			
			
			if(AppBaseConstant.ROLE_PURCHASE_MANAGER_ADMIN.equals(role)){
			prLineList=prLineService.getPRLineListBYFilter(prLineDto);
			}
			
			if(AppBaseConstant.ROLE_BUYER_ADMIN.equals(role)){
				Errors errors = new Errors();
				prValidator.validateprPlant(prLineDto, errors);
				
				if(errors.getErrorCount()>0){
					return respDto=new CustomResponseDto(false, errors.getErrorString());
				}
				
				
			      prLineList=prLineService.getPRFromSAPAfterPOCreation(prLineDto);
			}
		}catch (Exception e) {
			log.info("ERROR",e);
			return new CustomResponseDto(false,e.getMessage());
		}
		respDto=new CustomResponseDto("prLineList", prLineList);
		respDto.addObject("prStatusList", prStatusList);
		respDto.addObject("buyerList", buyerList);
		respDto.addObject("technicalList", technicalList);
		respDto.addObject("priorityList", priorityList);
		respDto.addObject("plantList",plantList);
		respDto.addObject("role", role);
		respDto.addObject("partner", partner);
		
		return respDto;
	}
	@PostMapping(value="/updatePRBuyerAssignNew")
	public @ResponseBody CustomResponseDto updatePRBuyerAssignNew(@RequestBody List<PRLineDto> prLineDto){
		CustomResponseDto respDto = null;
		try {
			Errors errors = new Errors();
			prValidator.validate(prLineDto, errors);
			
			if(errors.getErrorCount()>0){
				return respDto=new CustomResponseDto(false, errors.getErrorString());
			}
			boolean status=prLineService.updatePRBuyerAssign(prLineDto);
			if(!status){
				return new CustomResponseDto(false,"Failed to Update Record");
			}
			respDto=new CustomResponseDto(true,"Buyer Assigned");
		} catch (Exception e) {
			log.info("ERROR",e);
			return new CustomResponseDto(false,e.getMessage());
		}
		return respDto;
	}
	@PostMapping(value = "/getPRLineForBuyer")
	public @ResponseBody CustomResponseDto getPRLineForBuyer(){
		CustomResponseDto resp = new CustomResponseDto();
		
		/*List<PRLineDto> prLine=prComponent.findPRById(prId);*/
		List<PRLineDto> prLine=prComponent.findPRLineForBuyer(contextService.getUser().getUserId());
		/*List<ThirdPartyPRApproverDto> emailList=prComponent.findThirdPartyPRId(prId);
		List<PRAttachmentDto> prAtt=prComponent.findAttbyPRId(prId);*/
		Map<String, Object> params = new HashMap<String, Object>();
		List<UserDto> internalUserList=userService.findDtos("getAllInternalUser", params);
		resp.addObject("prLine", prLine);
	/*	resp.addObject("emailList", emailList);
		resp.addObject("attachmentList", prAtt);*/
		resp.addObject("internalUserList", internalUserList);
		return resp;
	}
	@PostMapping(value = "/getPRStatus")
	public @ResponseBody CustomResponseDto getPRStatus(){
		CustomResponseDto resp = new CustomResponseDto();
		Map<String, String> prStatusList = refListService.getReferenceListMap(CoreReferenceConstants.PR_STATUS);
		Map<String, String> plantList =refListService.getReferenceListMap(CoreReferenceConstants.PlANT);
		List<UserDto> buyerList=userService.findDtos("getUserListByRole", 
				AbstractContextServiceImpl.getParamMap("value", AppBaseConstant.ROLE_BUYER_ADMIN));
		
		Map<String, String> purchaseGroupList = refListService.getReferenceListMap(CoreReferenceConstants.PURCHASE_GROUP_UPDATED);
		resp.addObject("prStatusList", prStatusList);
		resp.addObject("plantList", plantList);
		resp.addObject("buyerList", buyerList);
		resp.addObject("purchaseGroupList", purchaseGroupList);
		return resp;
	}
	
	@PostMapping(value = "/getPRByFilter")
	public @ResponseBody CustomResponseDto getPRByFilter(@RequestBody PRReadDto dto) {
		CustomResponseDto resp = null;List<PRDto> prList = null;List<PRLineDto> prLineList=null; String role =null;BPartnerDto partner = null;
		Map<String,String> plantList=null;
		Map<String, String> prStatusList = refListService.getReferenceListMap(CoreReferenceConstants.PR_STATUS);
		
		if(contextService!=null && contextService.getDefaultRole()!=null 
				&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())){
			role =contextService.getDefaultRole().getValue();
			partner = contextService.getPartner();
		}
		try{
		prList=prService.getPRByFilter(dto);
		}catch (Exception e) {
			
		}
		List<UserDto> buyerList=userService.findDtos("getUserListByRole", 
				AbstractContextServiceImpl.getParamMap("value", AppBaseConstant.ROLE_BUYER_ADMIN));
		
		List<UserDto> technicalList=userService.findDtos("getUserListByRole", 
				AbstractContextServiceImpl.getParamMap("value", AppBaseConstant.ROLE_TECHNICAL_ADMIN));
		
		Map<String, String> priorityList = refListService.getReferenceListMap(CoreReferenceConstants.PRIORITY);
		
		resp=new CustomResponseDto("prList", prList);
		resp.addObject("prStatusList", prStatusList);
		resp.addObject("role", role);
		resp.addObject("partner", partner);
		resp.addObject("buyerList", buyerList);
		resp.addObject("technicalList", technicalList);
		resp.addObject("priorityList", priorityList);
		resp.addObject("prLineList", prLineList);
		resp.addObject("plantList",plantList);
		return resp;
		
	}
	@PostMapping(value = "/deletePRAttachment/{attchID}")
	public @ResponseBody CustomResponseDto deletePRAttachment(@PathVariable("attchID") Long attchID){
		try{
			Map<String, Object> param = AbstractContextServiceImpl.getParamMap("isActive", "N");
			int result = prAttachmentService.updateByJpql(param,
					AbstractContextServiceImpl.getParamMap("prAttachmentId", attchID));
			if(result>0){
				CustomResponseDto resp =new CustomResponseDto(true, "Document Deleted");
				return resp;
			}else{
				return new CustomResponseDto(false, "Error while Deleting Document. Please refresh and try again.");
			}
		}catch (Exception e) {
			return new CustomResponseDto(false, e.getMessage());
		}
	}
	@PostMapping(value = "/getAttachmentbyPrId/{prId}")
	public @ResponseBody CustomResponseDto getAttachmentbyPrId(@PathVariable("prId") Long prId){
		CustomResponseDto resp = new CustomResponseDto();
	
		List<ThirdPartyPRApproverDto> emailList=prComponent.findThirdPartyPRId(prId);
		List<PRAttachmentDto> prAtt=prComponent.findAttbyPRId(prId);
		resp.addObject("emailList", emailList);
		resp.addObject("attachmentList", prAtt);
		return resp;
	}
	@PostMapping(value="/getPRReportMaster")
	public @ResponseBody CustomResponseDto getPRReportMaster(){
		CustomResponseDto resp = null;List<PRDto> prList = null;List<PRLineDto> prLineList=null; String role =null;BPartnerDto partner = null;
		Map<String,String> plantList=null;
		Map<String, String> prStatusList = refListService.getReferenceListMap(CoreReferenceConstants.PR_STATUS);
		
		if(contextService!=null && contextService.getDefaultRole()!=null 
				&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())){
			role =contextService.getDefaultRole().getValue();
			partner = contextService.getPartner();
		}	
		List<UserDto> technicalList=userService.findDtos("getUserListByRole", 
				AbstractContextServiceImpl.getParamMap("value", AppBaseConstant.ROLE_TECHNICAL_ADMIN));
		
		Map<String, String> priorityList = refListService.getReferenceListMap(CoreReferenceConstants.PRIORITY);
		
		resp=new CustomResponseDto("prList", prList);
		resp.addObject("prStatusList", prStatusList);
		resp.addObject("role", role);
		resp.addObject("partner", partner);
		resp.addObject("technicalList", technicalList);
		resp.addObject("priorityList", priorityList);
		resp.addObject("prLineList", prLineList);
		resp.addObject("plantList",plantList);
		return resp;
	}
	
	@PostMapping(value = "/getPRbyPrId/{prNo}")
	public @ResponseBody CustomResponseDto getPRbyPrId(@PathVariable("prNo") String prNo){
		CustomResponseDto resp = new CustomResponseDto();
		Map<String, String> prStatusList = refListService.getReferenceListMap(CoreReferenceConstants.PR_STATUS);
		PRDto dto=prService.findDto("getPRByPRNumber", AbstractContextServiceImpl.getParamMap("prNo", prNo));
		resp=new CustomResponseDto("pr", dto);
		resp.addObject("prStatus", prStatusList);
		return resp;
	}
	@PostMapping(value = "/updatePRStatus/{prId}/{status}")
	public @ResponseBody CustomResponseDto updatePRStatus(@PathVariable("prId") Long prId,@PathVariable("status") String status){
		try{
			Map<String, Object> param = AbstractContextServiceImpl.getParamMap("status",status);
			int result = prService.updateByJpql(param,
					AbstractContextServiceImpl.getParamMap("prId", prId));
			if(result>0){
				CustomResponseDto resp =new CustomResponseDto(true, "Status Updated");
				return resp;
			}else{
				return new CustomResponseDto(false, "Error while updating. Please refresh and try again.");
			}
		}catch (Exception e) {
			return new CustomResponseDto(false, e.getMessage());
		}
	}
	
	
	@PostMapping(value="/updatePRDocumentSave")
	public @ResponseBody CustomResponseDto updatePRDocumentSave(@RequestBody PRDto prDto){
		CustomResponseDto respDto = null;
		try {
			List<PRAttachmentDto> resDto=new ArrayList<>();
			
		
//			resDto=prComponent.savePRAtt(prDto.getPrAttSet());
			
			resDto=prComponent.savePRDoc(prDto.getPrAttSet());
			
			if(resDto!=null) {
				respDto=new CustomResponseDto(true,"Attachment Saved");
			}

		} catch (Exception e) {
			log.info("ERROR",e);
			return new CustomResponseDto(false,e.getMessage());
		}
		return respDto;
	}
	

	
}
