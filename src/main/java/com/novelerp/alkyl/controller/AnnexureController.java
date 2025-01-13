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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jfree.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelerp.alkyl.component.AnnexureComponent;
import com.novelerp.alkyl.dao.AnnexureDao;
import com.novelerp.alkyl.dto.AnnexureDto;
import com.novelerp.alkyl.dto.InvitationReadDto;
import com.novelerp.alkyl.dto.PRDto;
import com.novelerp.alkyl.dto.PRLineDto;
import com.novelerp.alkyl.dto.PraposedReasonDto;
import com.novelerp.alkyl.dto.QCFApproverSAPDto;
import com.novelerp.alkyl.service.AnnexureService;
import com.novelerp.alkyl.service.VendorEnquiryService;
import com.novelerp.alkyl.validator.AnnexureValidator;
import com.novelerp.appbase.master.dao.AttachmentDao;
import com.novelerp.appbase.master.dto.AsnReminderDto;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.service.AttachmentService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.MailDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.eat.dto.WinnerSelectionDto;
import com.sap.document.sap.rfc.functions.ObjectFactory;
import com.sap.document.sap.rfc.functions.ZQCFAnnexureClient;
import com.sap.document.sap.rfc.functions.ZQCFAnnexute_Type;

@Controller
@RequestMapping("/rest")
public class AnnexureController {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AnnexureService annexureService;
	
	@Autowired
	private AnnexureValidator annexureValidator;
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	
	@Autowired
	private AnnexureComponent annexureComponent;
	
	@Autowired
	private AnnexureDao annexureDao;
	
	@Autowired
	private ObjectFactory objectFactory;
	
	@Autowired
	private ZQCFAnnexureClient webclient;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	@Qualifier(value=AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
	private MediaService mediaService;
	
	@Autowired
	private VendorEnquiryService vendorEnquiryService;
	
	@Autowired
	private AttachmentDao attachmentDao;

	
	/*@Autowired
	private WinnerSelectionService winnerSelectionService;*/
	
	@PostMapping(value="/saveAnnexure")
	public @ResponseBody CustomResponseDto saveAnnexure(@RequestBody AnnexureDto annexureDto){
		try{
			annexureDto.setStatus(AppBaseConstant.ANNEXURE_DRAFT_STATUS);
			annexureDto = annexureService.saveAnnexure(annexureDto);
		}catch (Exception e) {
			Log.error(e.getMessage());
			return new CustomResponseDto(false,"Error While Saving Annexure");
		}
		CustomResponseDto resp = new CustomResponseDto(true,"Annexure Saved Successfully");
		resp.addObject("annexure", annexureDto);
		return resp;
	}
	
	@PostMapping(value="/submitAnnexure")
	public @ResponseBody CustomResponseDto submitAnnexure(@RequestBody AnnexureDto annexureDto){
		try{
			annexureDto.setStatus(AppBaseConstant.ANNEXURE_SUBMIT_STATUS);
			annexureDto = annexureService.saveAnnexure(annexureDto);
		}catch (Exception e) {
			Log.error(e.getMessage());
			return new CustomResponseDto(false,"Error While Saving Annexure");
		}
		CustomResponseDto resp = new CustomResponseDto(true,"Annexure Submitted Successfully");
		resp.addObject("annexure", annexureDto);
		return resp;
	}

	@PostMapping(value="/approveAnnexure/{annexureId}")
	public @ResponseBody CustomResponseDto approveAnnexure(@PathVariable("annexureId")Long annexureId){
		
		try{
			Map<String, Object> whereCls = AbstractContextServiceImpl.getParamMap("annexureId", annexureId);
			Map<String, Object> params = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.ANNEXURE_APPROVE_STATUS);
			int result = annexureService.updateByJpql(params, whereCls);
			if(result>0){
				CustomResponseDto resp =new CustomResponseDto(true, "Annexure Approved");
				resp.addObject("enqStatus", AppBaseConstant.ANNEXURE_APPROVE_STATUS);
				return resp;
			}else{
				return new CustomResponseDto(false, "Error while approving Annexure. Please refresh and try again.");
			}
		}catch (Exception e) {
			return new CustomResponseDto(false, e.getMessage());
		}
		
	}
	
	@PostMapping(value="/rejectAnnexure/{annexureId}/{reason}")
	public @ResponseBody CustomResponseDto rejectAnnexure(@PathVariable("annexureId")Long annexureId
			, @PathVariable("reason")String reason){
		
		try{
			Map<String, Object> whereCls = AbstractContextServiceImpl.getParamMap("annexureId", annexureId);
			Map<String, Object> params = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.ANNEXURE_REJECT_STATUS);
			params.put("remark", reason);
			int result = annexureService.updateByJpql(params, whereCls);
			if(result>0){
				CustomResponseDto resp =new CustomResponseDto(true, "Annexure Rejected");
				resp.addObject("enqStatus", AppBaseConstant.ANNEXURE_REJECT_STATUS);
				return resp;
			}else{
				return new CustomResponseDto(false, "Error while approving Annexure. Please refresh and try again.");
			}
		}catch (Exception e) {
			return new CustomResponseDto(false, e.getMessage());
		}
		
	}
	@PostMapping(value="/getAnnexure")
	public  @ResponseBody AnnexureDto getAnnexure(){
		AnnexureDto annexure = new AnnexureDto();
		PRDto pr= new PRDto();
		annexure.setPr(pr);
		List<WinnerSelectionDto> winnerSelectionList = new ArrayList<WinnerSelectionDto>();
		WinnerSelectionDto winnerSelection = new WinnerSelectionDto();
		PRLineDto prLine = new PRLineDto();
		winnerSelection.setPrLine(prLine);
		winnerSelectionList.add(winnerSelection);
		
		List<PraposedReasonDto> praposedReasonList = new ArrayList<PraposedReasonDto>();
		PraposedReasonDto praposedReason = new PraposedReasonDto();
		praposedReasonList.add(praposedReason);
		annexure.setWinnerSelectionSet(winnerSelectionList);
		annexure.setPraposedReasonSet(praposedReasonList);
		return annexure;
		
	}
	
//	@PostMapping(value="/updateAnnexureApproval/{annexureId}/{attachmentId}")
	@PostMapping(value="/updateAnnexureApproval/{annexureId}")
	public @ResponseBody CustomResponseDto updateAnnexureApproval(@PathVariable("annexureId") Long annexureId){
		
		ResponseDto respDto = null;
		try {
		//	Errors errors = new Errors();
			/*List<WinnerSelectionDto> winnerSelectionList=winnerSelectionService.findDtos("getWinnerSelectionByAnnexureId", AbstractContextServiceImpl.getParamMap("annexureId", annexureId));*/
			/*List<WinnerSelectionDto> winnerSelectionList=annexureComponent.getWinnerSelectionByAnnexureId(annexureId);
			annexureValidator.validateApprove(winnerSelectionList,contextService.getDefaultRole().getValue(), errors);			
			if(errors.getErrorCount()>0){
				return new CustomResponseDto(false, errors.getErrorString());
			}*/
			
			respDto=annexureService.updateAnnexureApproval(annexureId);

			
			
			if(respDto.isHasError()){
				return new CustomResponseDto(false,"Failed to Update Record");
			}
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("Id", annexureId);
			List<AnnexureDto> QCFList = annexureService.findDtos("getAnnexureById", params);
			
			
			 HashMap<String, Object> propertyValueMap = new HashMap<>();
				 propertyValueMap.put("firstLevelApprovalStatus", "APPROVED");
//				 propertyValueMap.put("qcfAtt", attachmentId);
				   int result1 = vendorEnquiryService.updateByJpql(
							propertyValueMap,
							AbstractContextServiceImpl.getParamMap("enquiryId",QCFList.get(0).getEnquiry().getEnquiryId()));
			return new CustomResponseDto(AppBaseConstant.ANNEXURE_APPROVE_STATUS,"Approved",true);
		} catch (Exception e) {
			log.info("ERROR",e);
			return new CustomResponseDto(false,e.getMessage());
		}
	}
	
	@RequestMapping(value="/sentQCFApprovalMail", method = {RequestMethod.POST })
	//@PostMapping(value="/sentQCFApprovalMail/{qcfno}")
	public @ResponseBody CustomResponseDto sentQCFApprovalMail(@RequestParam(value="ccList") List<String> ccList,@RequestParam(value="recipientList") String recipientList,@RequestParam(value="annexureId") Long annexureId,@RequestParam(value="recipientName") String recipientName){
	//	public @ResponseBody CustomResponseDto sentQCFApprovalMail(@RequestParam("recipientList") String recipientList,@RequestParam("annexureId") Long annexureId){	
		CustomResponseDto resp =new CustomResponseDto();
		CustomResponseDto mailDto=null;
		try {
			//ccList.getCcList();
			
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("Id", annexureId);
			List<AnnexureDto> QCFList = annexureService.findDtos("getAnnexureById", params);
			
			
			for(AnnexureDto dto : QCFList) {
				try {
					if(!recipientList.isEmpty()) {
					//mailDto=annexureComponent.sendQCFApprovalMail(dto.getEnquiry().getEnquiryId().toString(),dto.getEnquiry().getQcfNo(),ccList,recipientList);
					
					mailDto=annexureComponent.sendQCFApprovalMail(dto.getEnquiry().getEnquiryId().toString(),dto.getEnquiry().getQcfNo(),recipientList,recipientName);	
					
					HashMap<String, Object> annexureMap = new HashMap<>();
					annexureMap.put("qcf_to_mailid", recipientName);
				    annexureService.updateByJpql(annexureMap, AbstractContextServiceImpl.getParamMap("annexureId", annexureId));
					
				 	
//				    Map<String, Object> attachment = new HashMap<String, Object>();
//				    attachment.put("attachmentId", attachmentId);
//					
//					AttachmentDto getQCFattachment=attachmentService.findDto("getAttachmentByAttId", params);
					if(!ccList.isEmpty()) {
					    annexureComponent.sendQCFApprovalMailtoCCList(dto.getEnquiry().getEnquiryId().toString(),dto.getEnquiry().getQcfNo(),ccList,recipientName,dto.getEnquiry().getQcfAtt());	
					}
					
              //      ZQCFAnnexute_Type qcf = objectFactory.createZQCFAnnexute_Type();
                    
                     webclient.sendQCFApprovalListToSAP(recipientList,ccList,dto.getEnquiry().getQcfNo());
                    
               
                    
 					
					}else {
					//	resp.addObject("Please Select Recepient",false);
						return new CustomResponseDto(false,"Please Select Recepient");
					}
				
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(mailDto.getMessage().equals("Mail Sucessfully Sent")) {
				 HashMap<String, Object> propertyValueMap = new HashMap<>();
	  			   propertyValueMap.put("isMailsentFinalApproval", "Y");
	  			   int result1 = vendorEnquiryService.updateByJpql(
	  						propertyValueMap,
	  						AbstractContextServiceImpl.getParamMap("enquiryId",QCFList.get(0).getEnquiry().getEnquiryId()));
				   
	  			   
			   return new CustomResponseDto(true,"Mail Sucessfully Sent");
			   
			   
			  
			}else {
			//	resp.addObject("Mail Sending Failed",false);
				return new CustomResponseDto(false,"Mail Sending Failed");
			}
			
		} catch (Exception e) {
			log.info("ERROR",e);
			//resp.addObject(e.getMessage(),false);
			return new CustomResponseDto(false,e.getMessage());
		}
	//	return resp.toString();

	}
	
	
	@PostMapping(value="/updateAnnexureReject/{annexureId}/{remarks}")
	public @ResponseBody CustomResponseDto updatePRReject(@PathVariable("annexureId") Long annexureId,@PathVariable("remarks") String remarks){
		
		ResponseDto respDto = null;
		try {
			Errors errors = new Errors();
			annexureValidator.validateReject(annexureId,contextService.getDefaultRole().getValue(), errors);
			
			if(errors.getErrorCount()>0){
				return new CustomResponseDto(false, errors.getErrorString());
			}
			
			respDto=annexureService.updateAnnexureReject(annexureId,remarks);
			
			annexureService.SendMailonPMRejection(annexureId);
			if(respDto.isHasError()){
				return new CustomResponseDto(false,"Failed to Update Record");
			}
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("Id", annexureId);
			List<AnnexureDto> QCFList = annexureService.findDtos("getAnnexureById", params);
			
			
			 HashMap<String, Object> propertyValueMap = new HashMap<>();
			
			   propertyValueMap.put("firstLevelApprovalStatus", AppBaseConstant.ANNEXURE_REJECT_STATUS);
			   int result1 = vendorEnquiryService.updateByJpql(
						propertyValueMap,
						AbstractContextServiceImpl.getParamMap("enquiryId",QCFList.get(0).getEnquiry().getEnquiryId()));
			
				
				 
			return new CustomResponseDto(AppBaseConstant.ANNEXURE_REJECT_STATUS,"Rejected",true);
		} catch (Exception e) {
			log.info("ERROR",e);
			return new CustomResponseDto(false,e.getMessage());
		}
	}
	
	
	@PostMapping(value="/updateFinalAnnexureReject/{annexureId}/{remarks}")
	public @ResponseBody CustomResponseDto updateFinalPRReject(@PathVariable("annexureId") Long annexureId,@PathVariable("remarks") String remarks){
		
		ResponseDto respDto = null;
		try {
			Errors errors = new Errors();
			annexureValidator.validateReject(annexureId,contextService.getDefaultRole().getValue(), errors);
			
			if(errors.getErrorCount()>0){
				return new CustomResponseDto(false, errors.getErrorString());
			}
			
			//respDto=annexureService.updateAnnexureReject(annexureId,remarks);	
			respDto=annexureService.updateFinalAnnexureReject(annexureId,remarks);	
			annexureService.SendMailonFinalRejection(annexureId);
			if(respDto.isHasError()){
				return new CustomResponseDto(false,"Failed to Update Record");
			}
			
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("Id", annexureId);
			List<AnnexureDto> QCFList = annexureService.findDtos("getAnnexureById", params);
			
			
			 HashMap<String, Object> propertyValueMap = new HashMap<>();
			
			   propertyValueMap.put("finalApprovalStatus", AppBaseConstant.ANNEXURE_REJECT_STATUS);
			   int result1 = vendorEnquiryService.updateByJpql(
						propertyValueMap,
						AbstractContextServiceImpl.getParamMap("enquiryId",QCFList.get(0).getEnquiry().getEnquiryId()));
			
				// propertyValueMap.put("finalApprovalStatus", AppBaseConstant.ANNEXURE_FINAL_APPROVE_STATUS);
			
			
			return new CustomResponseDto(AppBaseConstant.ANNEXURE_REJECT_STATUS,"Rejected",true);
		} catch (Exception e) {
			log.info("ERROR",e);
			return new CustomResponseDto(false,e.getMessage());
		}
	}
	
	@PostMapping(value="/updateFinalApproval/{annexureId}")
	public @ResponseBody CustomResponseDto updateFinalApproval(@PathVariable("annexureId") Long annexureId){
		
		ResponseDto respDto = null;
		try {
		//	Errors errors = new Errors();
			/*List<WinnerSelectionDto> winnerSelectionList=winnerSelectionService.findDtos("getWinnerSelectionByAnnexureId", AbstractContextServiceImpl.getParamMap("annexureId", annexureId));*/
			/*List<WinnerSelectionDto> winnerSelectionList=annexureComponent.getWinnerSelectionByAnnexureId(annexureId);
			annexureValidator.validateApprove(winnerSelectionList,contextService.getDefaultRole().getValue(), errors);			
			if(errors.getErrorCount()>0){
				return new CustomResponseDto(false, errors.getErrorString());
			}*/
			
			
			respDto=annexureService.updateFinalApproval(annexureId);		
			
			if(respDto.isHasError()){
				return new CustomResponseDto(false,"Failed to Update Record");
			}else {
				annexureService.updateQCFAnnexureDataToSAP(annexureId);
			}
			
			annexureService.SendMailonFinalApproval(annexureId);
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("Id", annexureId);
			List<AnnexureDto> QCFList = annexureService.findDtos("getAnnexureById", params);
			
			
			 HashMap<String, Object> propertyValueMap = new HashMap<>();
				 propertyValueMap.put("finalApprovalStatus", "APPROVED");
				   int result1 = vendorEnquiryService.updateByJpql(
							propertyValueMap,
							AbstractContextServiceImpl.getParamMap("enquiryId",QCFList.get(0).getEnquiry().getEnquiryId()));
				 
		
			 
			return new CustomResponseDto(AppBaseConstant.ANNEXURE_FINAL_APPROVE_STATUS,"Approved",true);
			
			
		} catch (Exception e) {
			log.info("ERROR",e);
			return new CustomResponseDto(false,e.getMessage());
		}
	}
	
	@PostMapping(value="/getQCFApproverListFromSAP")
	public @ResponseBody CustomResponseDto getQCFApproverListFromSAP(){
		
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
       
//       String approverurl="https://172.18.2.28:44300/sap/bc/yweb03_ws_43?sap-client=100";
//       String approverurl="https://172.18.2.29:44300/sap/bc/yweb03_ws_43?sap-client=100";
       String approverurl="https://172.18.2.36:44300/sap/bc/yweb03_ws_43?sap-client=100";
		
				List<String> cntList = new ArrayList<>();
				URLConnection request=null;   
				try {
			            URL u = new URL(approverurl);
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
			            
			         //   JSONArray array=new JSONArray(sb.toString());
			            ObjectMapper objJson = new ObjectMapper();
			         //   objJson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			            QCFApproverSAPDto[] dto = objJson.readValue(sb.toString(), QCFApproverSAPDto[].class);
						 List<QCFApproverSAPDto> QCFApproverList= new ArrayList<QCFApproverSAPDto>();
						 for(QCFApproverSAPDto dtos:dto){
							 QCFApproverList.add(dtos);
			          	   
			             }
						 resp.addObject("QCFApproverList", QCFApproverList);
			            
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

               return resp;
	}
	
}
