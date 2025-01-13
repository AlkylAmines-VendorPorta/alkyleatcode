package com.novelerp.alkyl.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.BigDecimalDeserializer;
import com.novelerp.alkyl.component.PRComponent;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.BidderAttachmentDto;
import com.novelerp.alkyl.dto.BidderFilterDto;
import com.novelerp.alkyl.dto.EnquiryDto;
import com.novelerp.alkyl.dto.POReadDto;
import com.novelerp.alkyl.dto.PRAttachmentDto;
import com.novelerp.alkyl.dto.PRDto;
import com.novelerp.alkyl.dto.QuotationDto;
import com.novelerp.alkyl.service.BidderAttachmentService;
import com.novelerp.alkyl.service.PRService;
import com.novelerp.alkyl.service.VendorEnquiryService;
import com.novelerp.alkyl.validator.QuotationValidator;
import com.novelerp.appbase.master.dao.AttachmentDao;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.service.AttachmentService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;
import com.novelerp.core.util.DateUtil;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.eat.service.ItemBidService;
import com.novelerp.eat.service.PriceBidService;

@Controller
@RequestMapping(value="/rest")
public class QuotationController {

	@Autowired
	private PriceBidService priceBidService;
	
	@Autowired
	private BidderService bidderService;
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	
	@Autowired
	private ItemBidService itemBidService;
	
	@Autowired
	private ReferenceListService refListService;
	
	@Autowired
	private PRService prService;
	
	@Autowired
	private BidderAttachmentService bidderAttachmentService;
	
	@Autowired
	private QuotationValidator quotValidator;
	
	@Autowired
	private VendorEnquiryService vendorEnquiryService;
	
	@Autowired
	private PRComponent prComponent;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private AttachmentDao attachmentDao;
	
	@Autowired
	private ApprovalMatrixController approveMatrixController;
	
	@Autowired
	 @Qualifier(AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
	    private MediaService mediaService;
	
	@PostMapping(value="/saveQuotation")
	public @ResponseBody CustomResponseDto saveQuotation(@RequestBody QuotationDto quotation){
		
		try{

			Errors errors = new Errors();
			quotValidator.validate(quotation, errors);
			
			if(errors.getErrorCount()>0){
				return new CustomResponseDto(false, errors.getErrorString());
			}

			/*List<PriceBidDto> pbList = priceBidService.saveQuaotation(quotation.getQuotations());*/
			quotation=priceBidService.saveQuaotation(quotation);
			/*quotation.setQuotations(pbList);*/
			CustomResponseDto resp =new CustomResponseDto(true, "Quotation saved Successfully");
			resp.addObject("quotation", quotation);
			return resp;
		}catch (Exception e) {
			return new CustomResponseDto(false, e.getMessage());
		}
		
	}
	
	
	@PostMapping(value="/getQuotation")
	public @ResponseBody CustomResponseDto getQuotation(){
		
		try{
			
			QuotationDto quotation = new QuotationDto();
			List<PriceBidDto> pbList = new ArrayList<PriceBidDto>();
			PriceBidDto pb = new PriceBidDto();
			pbList.add(pb);
			
			BidderDto bidder = new BidderDto();
			
			quotation.setBidder(bidder);
			quotation.setQuotations(pbList);
			
			CustomResponseDto resp =new CustomResponseDto(true, "Quotation saved Successfully");
			resp.addObject("quotation", quotation);
			return resp;
		}catch (Exception e) {
			return new CustomResponseDto(false, e.getMessage());
		}
		
	}
	
	@PostMapping(value="/submitQuotation")
	public @ResponseBody CustomResponseDto submitQuotation(@RequestBody QuotationDto quotation){
		
		try{

			Errors errors = new Errors();
			quotValidator.validate(quotation, errors);
			
			if(errors.getErrorCount()>0){
				return new CustomResponseDto(false, errors.getErrorString());
			}
			
			quotation.getBidder().setStatus(AppBaseConstant.BIDDER_STATUS_BID_SUBMITED);
			quotation=priceBidService.saveQuaotation(quotation);
			try {
				prComponent.sendMainQuatationSubmition(quotation.getBidder().getBidderId());
			} catch (Exception e) {

			}
			/*BidderDto bidder = quotation.getBidder();
			List<PriceBidDto> pbList = priceBidService.submitQuotation(quotation.getQuotations(),bidder.getBidderId());
			quotation.setQuotations(pbList);*/
			CustomResponseDto resp =new CustomResponseDto(true, "Quotation submit Successfully");
			resp.addObject("quotation", quotation);
			resp.addObject("enqStatus", AppBaseConstant.BIDDER_STATUS_BID_SUBMITED);
			return resp;
		}catch (Exception e) {
			return new CustomResponseDto(false, e.getMessage());
		}
		
	}
	
//	@PostMapping(value="/approveQuotationold/{enquiryId}")
//	public @ResponseBody CustomResponseDto approveQuotationold(@PathVariable("enquiryId")Long enquiryId){
//		
//		try{
//			Map<String, Object> whereCls = AbstractContextServiceImpl.getParamMap("bidderId", enquiryId);
//			Map<String, Object> params = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.BIDDER_STATUS_APPROVED);
//			int result = bidderService.updateByJpql(params, whereCls);
//			if(result>0){
//				CustomResponseDto resp =new CustomResponseDto(true, "Quotation Approved");
//				resp.addObject("enqStatus", AppBaseConstant.BIDDER_STATUS_APPROVED);
//				return resp;
//			}else{
//				return new CustomResponseDto(false, "Error while approving Quotation. Please refresh and try again.");
//			}
//		}catch (Exception e) {
//			return new CustomResponseDto(false, e.getMessage());
//		}
//		
//	}
	
//	@PostMapping(value="/approveQuotation")
	@RequestMapping(value="/approveQuotation", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CustomResponseDto approveQuotation(@RequestBody QuotationDto quotation){
		
		try{
			Errors errors = new Errors();
			quotValidator.validateQuotation(quotation, errors);
			
			if(errors.getErrorCount()>0){
				return new CustomResponseDto(false, errors.getErrorString());
			}
			
			
			quotation=priceBidService.saveQuaotation(quotation);
			Map<String, Object> whereCls = AbstractContextServiceImpl.getParamMap("bidderId", quotation.getBidder().getBidderId());
			Map<String, Object> params = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.BIDDER_STATUS_APPROVED);
			int result = bidderService.updateByJpql(params, whereCls);
			if(result>0){
				CustomResponseDto resp =new CustomResponseDto(true, "Quotation Approved");
				resp.addObject("enqStatus", AppBaseConstant.BIDDER_STATUS_APPROVED);
				return resp;
			}else{
				return new CustomResponseDto(false, "Error while approving Quotation. Please refresh and try again.");
			}
		}catch (Exception e) {
			return new CustomResponseDto(false, e.getMessage());
		}
		
	}
	
	@PostMapping(value="/rejectQuotation/{enquiryId}/{reason}")
	public @ResponseBody CustomResponseDto rejectQuotation(@PathVariable("enquiryId")Long enquiryId
			, @PathVariable("reason")String reason){
		
		try{
			Map<String, Object> whereCls = AbstractContextServiceImpl.getParamMap("bidderId", enquiryId);
			Map<String, Object> params = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.BIDDER_STATUS_REJECTED);
			params.put("remark", reason);
			int result = bidderService.updateByJpql(params, whereCls);
			if(result>0){
				CustomResponseDto resp =new CustomResponseDto(true, "Quotation Rejected");
				resp.addObject("enqStatus", AppBaseConstant.BIDDER_STATUS_REJECTED);
				return resp;
			}else{
				return new CustomResponseDto(false, "Error while approving Quotation. Please refresh and try again.");
			}
		}catch (Exception e) {
			return new CustomResponseDto(false, e.getMessage());
		}
		
	}
/*	@PostMapping(value="/getVendorQuotation")
	public @ResponseBody CustomResponseDto getVendorQuotation(){
		CustomResponseDto resp = null;String role =null;BPartnerDto partner = null;
		if(contextService!=null && contextService.getDefaultRole()!=null 
				&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())){
			role =contextService.getDefaultRole().getValue();
			partner = contextService.getPartner();
		}
		if(role.equals(AppBaseConstant.ROLE_NEGOTIATOR_ADMIN)){
			String timeStamp = new SimpleDateFormat("yyyyMMdd hh:mm:ss").format(Calendar.getInstance().getTime());
            Map<String , Object> params = new HashMap<String, Object>();
            params.put("currentDate",new Date());
            params.put("status", AppBaseConstant.BIDDER_STATUS_BID_SUBMITED);
			List<BidderDto> bidderList=bidderService.findDtos("getVendorQuotationForApproval", params);
            List<PRDto> prList=prService.findDtos("getPRForBidderApproval", params);
			resp=new CustomResponseDto("prList", prList);
		}else{
		List<String> param = new ArrayList<>();
		Map<String, Object> whereCls = AbstractContextServiceImpl.getParamMap("bpartnerId", contextService.getPartner().getbPartnerId());
		param.add(AppBaseConstant.BIDDER_STATUS_DRAFTED);
		param.add(AppBaseConstant.BIDDER_STATUS_REJECTED);
		whereCls.put("status",param );
		whereCls.put("currentDate",new Date());
		List<BidderDto> bidderList=bidderService.findDtos("getVendorQuotation", whereCls);
		resp=new CustomResponseDto("bidderList", bidderList);
		}
		Map<String, String> prStatusList = refListService.getReferenceListMap(CoreReferenceConstants.PR_STATUS);
		Map<String, String> priorityList = refListService.getReferenceListMap(CoreReferenceConstants.PRIORITY);
		
		resp.addObject("prStatusList", prStatusList);
		resp.addObject("priorityList", priorityList);
		resp.addObject("role", role);
		resp.addObject("partner", partner);
		return resp;
		
	}*/
	@PostMapping(value="/getVendorQuotation")
	public @ResponseBody CustomResponseDto getVendorQuotation(){
		CustomResponseDto resp = null;String role =null;BPartnerDto partner = null;
		
		if(contextService!=null && contextService.getDefaultRole()!=null 
				&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())){
			role =contextService.getDefaultRole().getValue();
			partner = contextService.getPartner();
		}
		if(role.equals(AppBaseConstant.ROLE_NEGOTIATOR_ADMIN)){
			
			/*String timeStamp = new SimpleDateFormat("yyyyMMdd hh:mm:ss").format(Calendar.getInstance().getTime());*/
            Map<String , Object> params = new HashMap<String, Object>();
           /* params.put("currentDate",new Date());*/
            /*params.put("status", AppBaseConstant.BIDDER_STATUS_BID_SUBMITED);*/
			/*List<BidderDto> bidderList=bidderService.findDtos("getVendorQuotationForApproval", params);*/
            List<EnquiryDto> enqList=vendorEnquiryService.findDtos("getEnquiryForNegotiate", params);
			resp=new CustomResponseDto("enqList", enqList);
		}else{
		List<String> param = new ArrayList<>();
		Map<String, Object> whereCls = AbstractContextServiceImpl.getParamMap("bpartnerId", contextService.getPartner().getbPartnerId());
		param.add(AppBaseConstant.BIDDER_STATUS_DRAFTED);
		param.add(AppBaseConstant.BIDDER_STATUS_REJECTED);
		param.add(AppBaseConstant.BIDDER_STATUS_BID_SUBMITED);
		param.add(AppBaseConstant.BIDDER_STATUS_APPROVED);
		whereCls.put("status",param );
//		whereCls.put("currentDate",new Date());
		List<BidderDto> bidderList=bidderService.findDtos("getVendorQuotation", whereCls);
		resp=new CustomResponseDto("bidderList", bidderList);
		}
		Map<String, String> prStatusList = refListService.getReferenceListMap(CoreReferenceConstants.PR_STATUS);
		Map<String, String> priorityList = refListService.getReferenceListMap(CoreReferenceConstants.PRIORITY);
		
		resp.addObject("prStatusList", prStatusList);
		resp.addObject("priorityList", priorityList);
		resp.addObject("role", role);
		resp.addObject("partner", partner);
		
		return resp;
		
	}
	
	@PostMapping(value="/getVendorQuotationLine/{bidderId}")
	public @ResponseBody CustomResponseDto getVendorQuotationLine(@PathVariable("bidderId")Long bidderId){
		
		CustomResponseDto resp = null;
	//String url="	https://172.18.2.28:44300/sap/bc/yweb03_ws_18?sap-client=100";
		/*String url= "http://103.231.11.54:8000/sap/bc/yweb03_ws_18?sap-client=009";
		//System.out.println("After Print");
		//System.out.println(url);
		List<String> cntList = new ArrayList<>();
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
			paymentTermsList.put(temp.trim(), "");
		}*/
//		-----------------------------------------------------------------Payment Terms WS--------------------------------
		
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
        
    		String url ="https://172.18.2.36:44300/sap/bc/yweb03_ws_18?sap-client=100";

    		System.out.println(url);
    		List<String> cntList = new ArrayList<>();
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
    			
    			String[] dropdownelement = sb.toString().replaceAll("\"", "").split(",");
	            cntList = Arrays.asList(dropdownelement);
  
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
    		
    		Map<String, String> paymentTermsList = new HashMap<>();
			for(String temp : cntList) {
				paymentTermsList.put(temp.split("-")[0].trim(), temp.trim());
			}
		
		
//		Map<String, String> paymentTermsList = refListService
//				.getReferenceListMap(CoreReferenceConstants.PAYMENT_TERMS);
		Map<String, Object> whereCls = AbstractContextServiceImpl.getParamMap("bidderId", bidderId);
		List<PriceBidDto> priceBid = priceBidService.findDtos("getPriceBidByBidderId", whereCls);
		
		/*30-01-2024 New Code for pr att*/
		Long prId=null;
		List<PRAttachmentDto> prAtt = new ArrayList<PRAttachmentDto>(); 
		
		if(priceBid.size()>0) {
			List<PRAttachmentDto> attdto=new ArrayList<PRAttachmentDto>();
			for(PriceBidDto pbdto:priceBid){				
				
				prId= pbdto.getItemBid().getPrLine().getPr().getPrId();
				attdto=prComponent.findAttbyPRId(prId);
//				prAtt.addAll(attdto);
			}
			prAtt.addAll(attdto);
			
		}
		
		
		
		
		if(priceBid.isEmpty()){
			List<ItemBidDto> itemBid = itemBidService.findDtos("getItemBidByBidderId", whereCls);
		
			List<PriceBidDto> priceBidList = new ArrayList<PriceBidDto>();
			List<PRAttachmentDto> prAttach = new ArrayList<PRAttachmentDto>(); 
			Long PRId = null;
			List<PRAttachmentDto> attdto=new ArrayList<PRAttachmentDto>();
			for(ItemBidDto dto:itemBid){
				PriceBidDto priceDto=new PriceBidDto();
				
				priceDto.setItemBid(dto);
				priceBidList.add(priceDto);
	
				
				/*30-01-2024 New Code for pr att*/
				PRId= dto.getPrLine().getPr().getPrId();				
				attdto=prComponent.findAttbyPRId(PRId);
				
			}
			prAttach.addAll(attdto);
			
			resp=new CustomResponseDto("priceBid", priceBidList);
			resp.addObject("prAttchList", prAttach);
		}else{
			 resp=new CustomResponseDto("priceBid", priceBid);
			 resp.addObject("prAttchList", prAtt);
		}
	
		
		CustomResponseDto incoTermsList = approveMatrixController.vendorApprovalincoTermsListDetails();
		
		Object finalIncoTermsList = incoTermsList.getObjectMap().get("incoTermsList");
		
		 Map<String , Object> params = new HashMap<String, Object>();
		 params.put("bidderId", bidderId);
		List<BidderAttachmentDto> bidderAttachment=bidderAttachmentService.findDtos("getAllAttachmentByBidderID", params);
		resp.addObject("bidAttchList", bidderAttachment);
		resp.addObject("paymentTermsList", paymentTermsList);
		resp.addObject("incoTermsList", finalIncoTermsList);
	
		
		return resp;
	}
	
	
	@PostMapping(value="/getBidderQuotByPrId/{prId}")
	public @ResponseBody CustomResponseDto getBidderQuotByPrId(@PathVariable("prId") Long prId){

		CustomResponseDto resp = null;String role =null;
		
		List<String> param = new ArrayList<>();
		if(contextService!=null && contextService.getDefaultRole()!=null 
				&& !CommonUtil.isStringEmpty(contextService.getDefaultRole().getValue())){
			role =contextService.getDefaultRole().getValue();
		}
		if(role.equals(AppBaseConstant.ROLE_NEGOTIATOR_ADMIN)){
			param.add(AppBaseConstant.BIDDER_STATUS_DRAFTED);
			param.add(AppBaseConstant.BIDDER_STATUS_BID_SUBMITED);
		}else{
		    param.add(AppBaseConstant.BIDDER_STATUS_BID_SUBMITED);
		}
		
		Map<String, Object> params= new HashMap<>();
		params.put("prId", prId);
		params.put("status", param);
		
		List<BidderDto> bidderList=bidderService.findDtos("getBidderByPrIdAndStatus",params);
		resp=new CustomResponseDto("bidderList", bidderList);
		return resp;
	}
	@PostMapping(value="/getBidderByFilter")
	public @ResponseBody CustomResponseDto getBidderByFilter(@RequestBody BidderFilterDto dto) {
		CustomResponseDto resp = null;
		List<BidderDto> bidderList=bidderService.getBidderByFilter(dto);
		resp=new CustomResponseDto("bidderList", bidderList);
	
		return resp;
		
	}
	@PostMapping(value="/getNegotiatorFilterDropDown")
	public @ResponseBody CustomResponseDto getNegotiatorFilterDropDown(){
		Map<String, String> bidderStatusList = refListService.getReferenceListMap(CoreReferenceConstants.BIDDER_STATUS);
		Map<String, String> enqStatusList = refListService.getReferenceListMap(CoreReferenceConstants.ENQ_STATUS);
		CustomResponseDto res = new CustomResponseDto();
		res.addObject("bidderStatus", bidderStatusList);
		res.addObject("enqStatus", enqStatusList);
		return res;
	}
	@PostMapping(value = "/deleteBidderAttachment/{attchID}")
	public @ResponseBody CustomResponseDto deleteBidderAttachment(@PathVariable("attchID") Long attchID){
		try{
			Map<String, Object> param = AbstractContextServiceImpl.getParamMap("isActive", "N");
			int result = bidderAttachmentService.updateByJpql(param,
					AbstractContextServiceImpl.getParamMap("bidderAttachmentId", attchID));
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
	
	
	@GetMapping("/exportExcel/{bidderId}")
	public @ResponseBody AttachmentDto exportExcel(@PathVariable ("bidderId") Long bidderId,HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException{
	   String home = System.getProperty("user.home");
		
	 
	 Map<String, Object> params= new HashMap<>();
	 params.put("bidderId",bidderId);
	 
	 
	 List<ItemBidDto> itemBiddto = itemBidService.findDtos("getItemBidByeqnId", params);
	// List<BidderDto> itemBid = bidderService.findDtos("getBidderByenquiryId",params);
	 
	 Long EnquiryNo=null;
		
	//	Code to create dynamic excel
		//Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
	      CellStyle unlockedCellStyle = workbook.createCellStyle();
//          unlockedCellStyle.setLocked(false); //true or false based on the cell.
	      unlockedCellStyle.setLocked(false);
	      
	 	 CellStyle lockedStyle = workbook.createCellStyle();
		 lockedStyle.setLocked(false);
		//Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("Quotation Data");
		
		 sheet.protectSheet("password");
		 
		 XSSFDataValidationHelper dtHelper = new XSSFDataValidationHelper(sheet);
		 XSSFDataValidationConstraint dtConstraint = (XSSFDataValidationConstraint)
				 dtHelper.createExplicitListConstraint(new String[]{"select","%", "Per Unit"});
		 CellRangeAddressList discounttype = new CellRangeAddressList(1, 5000, 8, 8);
		 XSSFDataValidation dtvalidation = (XSSFDataValidation)dtHelper.createValidation(
				 dtConstraint, discounttype);
		 dtvalidation.setShowErrorBox(true);
		 sheet.addValidationData(dtvalidation);
		 
		 XSSFDataValidationHelper freightHelper = new XSSFDataValidationHelper(sheet);
		 XSSFDataValidationConstraint freightConstraint = (XSSFDataValidationConstraint)
				 freightHelper.createExplicitListConstraint(new String[]{"select","%", "Per Unit"});
		 CellRangeAddressList freighttype = new CellRangeAddressList(1, 5000, 10, 10);
		 XSSFDataValidation freightvalidation = (XSSFDataValidation)freightHelper.createValidation(
				 freightConstraint, freighttype);
		 freightvalidation.setShowErrorBox(true);
		 sheet.addValidationData(freightvalidation);
		 
		 XSSFDataValidationHelper packingtHelper = new XSSFDataValidationHelper(sheet);
		 XSSFDataValidationConstraint packingConstraint = (XSSFDataValidationConstraint)
				 packingtHelper.createExplicitListConstraint(new String[]{"select","%", "Per Unit"});
		 CellRangeAddressList packingttype = new CellRangeAddressList(1, 5000, 12, 12);
		 XSSFDataValidation packingvalidation = (XSSFDataValidation)packingtHelper.createValidation(
				 packingConstraint, packingttype);
		 packingvalidation.setShowErrorBox(true);
		 sheet.addValidationData(packingvalidation);
		 
		 XSSFDataValidationHelper otherchargeHelper = new XSSFDataValidationHelper(sheet);
		 XSSFDataValidationConstraint otherchargeConstraint = (XSSFDataValidationConstraint)
				 otherchargeHelper.createExplicitListConstraint(new String[]{"select","%", "Per Unit"});
		 CellRangeAddressList otherchargetype = new CellRangeAddressList(1, 5000, 14, 14);
		 XSSFDataValidation otherchargevalidation = (XSSFDataValidation)otherchargeHelper.createValidation(
				 otherchargeConstraint, otherchargetype);
		 otherchargevalidation.setShowErrorBox(true);
		 sheet.addValidationData(otherchargevalidation);
		 

		 
	 
		 
//		 CellStyle defaultStyle = workbook.createCellStyle();
//		 defaultStyle.setLocked(false);
		
		//This data needs to be written (Object[])
		 
		// String[] HEADERs = {"Serial No", "Enquiry Number","Document Number","Description & Material Code", "Qty","Qty(UOM)","Plant","Rate","DiscountType","Discount","FreightType","Freight","Packing & Fwd Type","Packing & Fwd","Other Charges Type","Other Charges","Taxes(%)","Delivery Date","Net Value","Basic","Other Chrg","Taxes","Gross(RS)"};
		 String[] HEADERs = {"Serial No", "Enquiry Number","Document Number","Description & Material Code", "Qty","Qty(UOM)","Plant","Rate","DiscountType","Discount","FreightType","Freight","Packing & Fwd Type","Packing & Fwd","Other Charges Type","Other Charges","Taxes(%)","Delivery Date"};
		//String[] HEADERs = {"Serial No", "Enquiry Number","Document Number","Description & Material Code", "Qty","Qty(UOM)","Plant","Rate","Discount(Lumpsum)","Freight(Lumpsum)","Packing & Fwd(Lumpsum)","Other Charges(Lumpsum)","Taxes(%)","Delivery Date"};
	//	 String[] HEADERs = {"Serial No", "Enquiry Number","Document Number","Material Code & Description", "Qty","Qty(UOM)","Plant","Rate","Discount(Lumpsum)","Freight(Lumpsum)","Packing & Fwd(Lumpsum)","Other Charges(Lumpsum)","Taxes(%)"};	
		// Header
	      Row headerRow = sheet.createRow(0);
	      
	
	      
	      for (int col = 0; col < HEADERs.length; col++) {
	        Cell cell = headerRow.createCell(col);
	        cell.setCellValue(HEADERs[col]);
	        
	      }
	      
	      int SerialNo=1;
	      int rowIdx = 1;
	      for (ItemBidDto bidder : itemBiddto) {
	        Row row = sheet.createRow(rowIdx++);

	        
	        EnquiryNo=bidder.getBidder().getEnquiry().getEnquiryId();
	        Date deliverydate=bidder.getPrLine().getDeliverDate();
	      // String pattern = "yyyy/MM/dd";
	        String pattern = "dd/MM/yyyy";
	         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	         String date = simpleDateFormat.format(deliverydate);
	       // row.createCell(0).setCellStyle(unlockedCellStyle);
	        row.createCell(0).setCellStyle(lockedStyle);
	        row.createCell(0).setCellValue(SerialNo++);
	        row.createCell(1).setCellValue(EnquiryNo);
	        row.createCell(2).setCellValue(bidder.getItemBidId());
	        
	        row.createCell(3).setCellValue(bidder.getPrLine().getMaterialDesc()+"("+ bidder.getPrLine().getMaterialCode()+")");
	        row.createCell(4).setCellValue(bidder.getPrLine().getQuantity());
	        row.createCell(5).setCellValue(bidder.getPrLine().getQuantity()+"("+ bidder.getPrLine().getUom()+")");
	        row.createCell(6).setCellValue(bidder.getPrLine().getPlant());
	        
	        row.createCell(7).setCellValue("0.0");	
	        row.createCell(7).setCellStyle(unlockedCellStyle);
	        row.createCell(8).setCellValue("");
	        row.createCell(8).setCellStyle(unlockedCellStyle);
	        row.createCell(9).setCellValue("0.0");
	        row.createCell(9).setCellStyle(unlockedCellStyle);
	        row.createCell(10).setCellValue("");
	        row.createCell(10).setCellStyle(unlockedCellStyle);
	        row.createCell(11).setCellValue("0.0");
	        row.createCell(11).setCellStyle(unlockedCellStyle);
	        row.createCell(12).setCellValue("");
	        row.createCell(12).setCellStyle(unlockedCellStyle);
	        row.createCell(13).setCellValue("0.0");
	        row.createCell(13).setCellStyle(unlockedCellStyle);
	        row.createCell(14).setCellValue("");
	        row.createCell(14).setCellStyle(unlockedCellStyle);
	        row.createCell(15).setCellValue("0.0");
	        row.createCell(15).setCellStyle(unlockedCellStyle);
	        row.createCell(16).setCellValue("0.0");
	        row.createCell(16).setCellStyle(unlockedCellStyle);
	        addCell(row, 17, unlockedCellStyle,date);
//	        row.createCell(18).setCellValue("0.0");
//	        row.createCell(18).setCellStyle(unlockedCellStyle);
//	        row.createCell(19).setCellValue("0.0");
//	        row.createCell(19).setCellStyle(unlockedCellStyle);
//	        row.createCell(20).setCellValue("0.0");
//	        row.createCell(20).setCellStyle(unlockedCellStyle);
//	        row.createCell(21).setCellValue("0.0");
//	        row.createCell(21).setCellStyle(unlockedCellStyle);
//	        row.createCell(22).setCellValue("0.0");
//	        row.createCell(22).setCellStyle(unlockedCellStyle);
//	        row.createCell(8).setCellValue("0.0");
//	        row.createCell(8).setCellStyle(unlockedCellStyle);
//	        row.createCell(9).setCellValue("0.0");
//	        row.createCell(9).setCellStyle(unlockedCellStyle);
//	        row.createCell(10).setCellValue("0.0");
//	        row.createCell(10).setCellStyle(unlockedCellStyle);
//	        row.createCell(11).setCellValue("0.0");
//	        row.createCell(11).setCellStyle(unlockedCellStyle);
//	        row.createCell(12).setCellValue("0.0");
//	        row.createCell(12).setCellStyle(unlockedCellStyle);
//	      //  addCell(row, 13, unlockedCellStyle, DateUtil.getDateString(date, DateUtil.DEFAULT_DATE_FORMAT));
//	        addCell(row, 13, unlockedCellStyle,date);
	       
	    
	        
	        
	      }
	      
	     
		try
		{
	
			 
			//declare file name to be create   
			String filename = "Quotation_"+EnquiryNo+".xlsx";  
		
			
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
			
			

		//Write the workbook in file system
		FileOutputStream out = new FileOutputStream(new File(home+"/Downloads/" + filename));

	        
		workbook.write(out);
		workbook.close();
		out.close();
		File file = new File(home+"/Downloads/" + filename);
		String name =filename;
		String originalFileName = file.getName();
		String contentType = "application/octet-stream";

		InputStream inputStream = new FileInputStream(file);
		byte[] bytes = StreamUtils.copyToByteArray(inputStream);

		MultipartFile multipartFile = new MockMultipartFile(name, originalFileName, contentType, bytes);
		AttachmentDto attachment = new AttachmentDto();	
		attachment=attachmentService.addAttachment(multipartFile);
		if(!attachment.getResponse().isHasError()){
			attachment= attachmentService.save(attachment);
		}
		
		return attachment;
		
//		 if(attachment!=null){
//			  mediaService.downloadFile(attachment,request,response);
//		  }else{
//			 // log.error("Attachment record not found....");
//		  }  
	//	return new CustomResponseDto(true, "File Downloaded SuccesFully in Downloads Folder");
		 
	//	System.out.println("file written successfully on disk.");
		}
		catch (Exception e)
		{
		e.printStackTrace();
		}
		return null;
		
		
	//return workbook;
		
		
	}
	
	public static Cell addCell(Row row, int columnIndex, CellStyle cellStyle, String value) 
	{
	    Cell cell = row.createCell(columnIndex);
	    cell.setCellStyle(cellStyle); 
	    cell.setCellValue(value);
	    return cell;
	}
	
	

//	@PostMapping(value="/SaveQCFPDF")
//	public @ResponseBody AttachmentDto SaveQCFPDF(@RequestParam("file") MultipartFile multipartFile) throws IOException, ParseException{
//	   String home = System.getProperty("user.home");
//		try
//		{
//	
//			ResponseDto response=attachmentService.isValid(multipartFile);
//			AttachmentDto attachment = new AttachmentDto();	
//			if(response.isHasError()){
//				attachment.setResponse(response);
//				return attachment;
//			}else{
//				
//			
//					attachment=attachmentService.addAttachment(multipartFile);
//					if(!attachment.getResponse().isHasError()){
//						return attachmentService.save(attachment);
////						List<AttachmentDto> newattachment = null;	
////						newattachment= attachmentService.findDtos("getAttachmentByName", AbstractContextServiceImpl.getParamMap("fileName", multipartFile.getOriginalFilename()));
////						
////						if(newattachment.size()>0) {
////							attachmentService.updateDto(attachment);
////						}
////						else
////							{
////							return attachmentService.save(attachment);
////							}
//					}
//					return attachment;
//				/*}*/
//			}
//		
//
//		}
//		catch (Exception e)
//		{
//		e.printStackTrace();
//		}
//		return null;
//		
//		
//	//return workbook;
//		
//		
//	}
	
	@PostMapping(value="/SaveQCFPDF")
	public @ResponseBody AttachmentDto SaveQCFPDF(@RequestParam("file") MultipartFile file) throws IOException, ParseException{
	   String home = System.getProperty("user.home");
		try
		{
			
			File path = new File("D:\\VendorQCFComparisonFileUpload\\" + file.getOriginalFilename());
	    	if (path.exists() && path.isFile()) {
	    		path.delete();
	    	  }
	    	
            path.createNewFile();
            FileOutputStream output = new FileOutputStream(path);
            output.write(file.getBytes());
            output.close();
     
		}
		catch (Exception e)
		{
		e.printStackTrace();
		}
		return null;
		
		
	//return workbook;
		
		
	}

	
	
	
	
	
}
