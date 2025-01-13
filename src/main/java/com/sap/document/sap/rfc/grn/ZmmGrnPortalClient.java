package com.sap.document.sap.rfc.grn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
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
import javax.xml.ws.Holder;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelerp.alkyl.dto.ASNLineCostCenterDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.service.AdvanceShipmentNoticeLineService;
import com.novelerp.alkyl.service.AdvanceShipmentNoticeService;
import com.novelerp.appbase.master.service.ReferenceListService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.service.impl.FTPMediaServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.CoreReferenceConstants;
import com.novelerp.core.util.DateUtil;
import com.sap.document.sap.rfc.ses.YSERVICECOSTCENTER;


@RequestMapping("/rest")
@Component
public class ZmmGrnPortalClient {
	
	@Autowired
	private AdvanceShipmentNoticeLineService asnLineService;
	
	@Autowired
	private AdvanceShipmentNoticeService asnService;
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;

	@Autowired
	@Qualifier(value=AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
	private MediaService mediaService;
	
	@Autowired
	private GrnPortalObjectFactory grnPortalObjectFactory;
	@Autowired
	private ReferenceListService refListService;
	
	
	private static final String USER_NAME = "basis";
	private static final String PASSWORD = "basis1917";
	
	
	
	private static final String STAGE_LOC="K13";
	private static final String MOVE_TYPE_101="101";
	private static final String MOVE_TYPE_103="103";
	private static final String MOVE_TYPE_104="104";
	private static final String MOVE_TYPE_105="105";
	private static final String ENTRY_EOM="EA";
	
	// QUALITY
	private static final String ADDRESS = "http://103.231.11.53:8000/sap/bc/srt/rfc/sap/zmmgrn_portal/009/zmmgrn_portal/zmmgrn_portal";
	
	// SAPDEV
	//private static final String ADDRESS = "http://103.231.11.53:8000/sap/bc/srt/rfc/sap/zvendor_portal/009/zvendor_portal/zvendor_portal";

	
	
	public static void main(String[] args) throws java.lang.Exception {
		
		MediaService mediaService = new FTPMediaServiceImpl();
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		 factory.setServiceClass(ZmmgrnPortal.class); 
		    factory.setAddress(ADDRESS);
		    
		    ZmmgrnPortal port1 = (ZmmgrnPortal) factory.create();
		    
		    Client client = ClientProxy.getClient(port1);
		    HTTPConduit http = (HTTPConduit) client.getConduit();

		    http.getAuthorization().setUserName("basis");
		    http.getAuthorization().setPassword("basis1917");   
		        
		    {	
		    	
		    
		    	 TABLEOFZMMGRN inputVal=new TABLEOFZMMGRN();		    	 
		    	 Holder<TABLEOFZMMGRN> in=new Holder<>(inputVal);		    	 
		    	 List<ZMMGRN> zmmgrns =inputVal.getItem();	    	 
		    	 ZMMGRN zmmgrn=new ZMMGRN();
		    	 GrnFtpObject grn = new GrnFtpObject();
//		    	 grn.setLineItem(new BAPI2017GMITEMCREATE());
		    	 grn.setPoDetails(new ZMMGRN());
		    	 ObjectMapper objJson = new ObjectMapper();
		    	 String json = objJson.writeValueAsString(grn);
				 json=json.replaceAll("null", "\"\"");
				 System.out.println(json);
				 
		    	 zmmgrn.setPONO("4600010347");
		    	 zmmgrn.setGATEENO("89863");		    	 
		    	 zmmgrns.add(zmmgrn);

		    	 mediaService.writeByteArrayTo(json.getBytes(), AppBaseConstant.FTP_GRN_TO_PROCESS_PATH, "Test123.txt");   	
		    	
		    	/*TABLEOFBAPI2017GMITEMCREATE lineItemInputVal=new TABLEOFBAPI2017GMITEMCREATE();
		    	Holder<TABLEOFBAPI2017GMITEMCREATE> lineItemIn=new Holder<TABLEOFBAPI2017GMITEMCREATE>(lineItemInputVal);		    	
		    	List<BAPI2017GMITEMCREATE> lineItemList=lineItemInputVal.getItem();		    			    
		    	 BAPI2017GMITEMCREATE lineItem=new BAPI2017GMITEMCREATE();		    	 
		    	 lineItem.setMATERIAL("150178");
		    	 lineItem.setSTGELOC("D13");
		    	 lineItem.setMOVETYPE("101");
		    	 lineItem.setQUANTITY(new BigDecimal(1));
		    	 lineItem.setENTRYUOM("EA");	    	 
		    	 //Setting lineItem into List
		    	 lineItemList.add(lineItem);
		    	 
		    	 		    	 		    	 
		    	 
		    	 
		    	 TABLEOFBAPI2017GMHEADRET output=new TABLEOFBAPI2017GMHEADRET();
		    	 Holder<TABLEOFBAPI2017GMHEADRET> outputHolder=new Holder<TABLEOFBAPI2017GMHEADRET>(output);
		    
		    	 
		    	 TABLEOFBAPIRET2 returnResponse=new TABLEOFBAPIRET2();
		    	 Holder<TABLEOFBAPIRET2> returnHolder=new Holder<TABLEOFBAPIRET2>(returnResponse);
		    	 
		    	 
		    	 port1.zmmGRNPORTAL(in, lineItemIn, outputHolder, returnHolder);
		 
		    	 System.out.println("zmmVENDORPORTAL._zmmVENDORPORTAL_input=" + in.value);
					System.out.println("zmmVENDORPORTAL._zmmVENDORPORTAL_output=" + lineItemIn.value);
					System.out.println("zmmVENDORPORTAL._zmmVENDORPORTAL_input=" + outputHolder.value.item.stream().map(i->i.matdoc).collect(Collectors.toList()));
					System.out.println("zmmVENDORPORTAL._zmmVENDORPORTAL_input=" + outputHolder.value.item.get(0).getMATDOC());
					System.out.println("zmmVENDORPORTAL._zmmVENDORPORTAL_output=" + returnHolder.value);
					String msg=returnHolder.value.item.stream().map(i->i.getMESSAGE()).findFirst().orElse(null);
					System.out.println("zmmVENDORPORTAL._zmmVENDORPORTAL_output=" + msg);*/


		    }
		
	}//psvm
	
	


	
	private ZmmgrnPortal getPort(String address){
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean(); 
	    factory.setServiceClass(ZmmgrnPortal.class); 
	    factory.setAddress(address); 
	    return (ZmmgrnPortal) factory.create();
	}
	
	private void setupClient(ZmmgrnPortal port,String usr, String pwd){
		Client client = ClientProxy.getClient(port);
	    HTTPConduit http = (HTTPConduit) client.getConduit();
	    http.getAuthorization().setUserName(usr);
	    http.getAuthorization().setPassword(pwd);
	}
	
	
	private TABLEOFZMMGRN getCreateWSInput(AdvanceShipmentNoticeDto asnDto){   	 
   	TABLEOFZMMGRN input = grnPortalObjectFactory.createTABLEOFZMMGRN();
		List<ZMMGRN> zmmGRNList = input.getItem();
		zmmGRNList.clear();
		zmmGRNList.add(getZmmGRN(asnDto));
		return input;
	}	
	public ZMMGRN getZmmGRN(AdvanceShipmentNoticeDto noticeDto) {
		 ZMMGRN zmmgrn=new ZMMGRN();		
		  zmmgrn.setPONO(noticeDto.getPo().getPurchaseOrderNumber());
		  zmmgrn.setGATEENO(noticeDto.getAdvanceShipmentNoticeNo().toString()); 
		  zmmgrn.setPSTNGDATE(noticeDto.getIssueDate());
		  zmmgrn.setBILLOFLADINGLONG(noticeDto.getLrNumber() +" "+ noticeDto.getTransporterNo());
		  zmmgrn.setHEADERTXT(noticeDto.getVehicalNo());
		/*  if(noticeDto.getPostingDate()!=null || !("").equals(noticeDto.getPostingDate()))
			  zmmgrn.setPSTNGDATE(noticeDto.getPostingDate());*/
//		  if("Y".equals(noticeDto.getInvoiceApplicable())
		  if("Y".equals(noticeDto.getInvoiceApplicable()) || noticeDto.getInvoiceApplicable()==null){
			  zmmgrn.setDOCDATE(DateUtil.getDateString(noticeDto.getInvoiceDate(),DateUtil.DEFAULT_DATE_FORMAT));
			  zmmgrn.setREFDOCNO(noticeDto.getInvoiceNo());
		  }else{
			  zmmgrn.setDOCDATE(DateUtil.getDateString(noticeDto.getDeliveryNoteDate(),DateUtil.DEFAULT_DATE_FORMAT));
			  zmmgrn.setREFDOCNO(noticeDto.getDeliveryNoteNo());
		  }
		  
         return zmmgrn;		
	}
	public ZMMGRN getZmmGRN105(AdvanceShipmentNoticeDto noticeDto) {
		 ZMMGRN zmmgrn=new ZMMGRN();	
		
	   String sizeString = noticeDto.getLrNumber() +" "+ noticeDto.getTransporterNo();
	   Integer maxSize = 16;
	   
	
	   if(sizeString.length() > maxSize ){
		    sizeString = sizeString.substring(0, maxSize);
		}
	
		  zmmgrn.setPONO(noticeDto.getPo().getPurchaseOrderNumber());
		  zmmgrn.setGATEENO(noticeDto.getAdvanceShipmentNoticeNo().toString()); 
		  zmmgrn.setPSTNGDATE(noticeDto.getPostingDate());
		//  zmmgrn.setBILLOFLADINGLONG(noticeDto.getLrNumber() +" "+ noticeDto.getTransporterNo());
		  zmmgrn.setBILLOFLADINGLONG(sizeString);
		  zmmgrn.setHEADERTXT(noticeDto.getVehicalNo());
		//  if("Y".equals(noticeDto.getInvoiceApplicable())
		  if("Y".equals(noticeDto.getInvoiceApplicable()) || noticeDto.getInvoiceApplicable()==null){
			  zmmgrn.setDOCDATE(DateUtil.getDateString(noticeDto.getInvoiceDate(),DateUtil.DEFAULT_DATE_FORMAT));
			  zmmgrn.setREFDOCNO(noticeDto.getInvoiceNo());
		  }else{
			  zmmgrn.setDOCDATE(DateUtil.getDateString(noticeDto.getDeliveryNoteDate(),DateUtil.DEFAULT_DATE_FORMAT));
			  zmmgrn.setREFDOCNO(noticeDto.getDeliveryNoteNo());
		  }
		  
        return zmmgrn;		
	}
	
	public ZMMGRN getZmmGRN101(AdvanceShipmentNoticeDto noticeDto) {
		 ZMMGRN zmmgrn=new ZMMGRN();		
		 // zmmgrn.setPONO(noticeDto.getPo().getPurchaseOrderNumber());
		  zmmgrn.setGATEENO(noticeDto.getAdvanceShipmentNoticeNo().toString()); 
		  zmmgrn.setPSTNGDATE(noticeDto.getPostingDate());
		  zmmgrn.setBILLOFLADINGLONG(noticeDto.getLrNumber() +" "+ noticeDto.getTransporterNo());
		  zmmgrn.setHEADERTXT(noticeDto.getVehicalNo());
		  
		//  String outbounddeliveryno=noticeDto.getPo().getOutboundDeliveryNo();
		//  if("Y".equals(noticeDto.getInvoiceApplicable())
		  if("Y".equals(noticeDto.getInvoiceApplicable()) || noticeDto.getInvoiceApplicable()==null){
//			  if(outbounddeliveryno!=null) {
//				  zmmgrn.setREFDOCNO(outbounddeliveryno);
//				  zmmgrn.setDOCDATE(DateUtil.getDateString(noticeDto.getPo().getDate(),DateUtil.DEFAULT_DATE_FORMAT));
//			  }
//			  else {
			  zmmgrn.setDOCDATE(DateUtil.getDateString(noticeDto.getInvoiceDate(),DateUtil.DEFAULT_DATE_FORMAT));
			  zmmgrn.setREFDOCNO(noticeDto.getInvoiceNo());
			//  }
		  }
		  else{
			  zmmgrn.setDOCDATE(DateUtil.getDateString(noticeDto.getDeliveryNoteDate(),DateUtil.DEFAULT_DATE_FORMAT));
			  zmmgrn.setREFDOCNO(noticeDto.getDeliveryNoteNo());
		  }
		  
       return zmmgrn;		
	}
	
	
	
	
	
	private TABLEOFBAPI2017GMITEMCREATE getCreateWSInputLine103(List<AdvanceShipmentNoticeLineDto> noticeLinrDto){   	 
		TABLEOFBAPI2017GMITEMCREATE input = grnPortalObjectFactory.createTABLEOFBAPI2017GMITEMCREATE();
			List<BAPI2017GMITEMCREATE> lineItemList = input.getItem();
			lineItemList.clear();
			
			for(AdvanceShipmentNoticeLineDto noticeLineDto:noticeLinrDto) {			
			lineItemList.add(getLineItemFor103(noticeLineDto));			
			}
			return input;
		}
	
	private List<BAPI2017GMITEMCREATE> getLineItemList103(List<AdvanceShipmentNoticeLineDto> noticeLinrDto){   	 
			List<BAPI2017GMITEMCREATE> lineItemList = new ArrayList<>();
			for(AdvanceShipmentNoticeLineDto noticeLineDto:noticeLinrDto) {	
				if(noticeLineDto.getDeliveryQuantity()>0){
					lineItemList.add(getLineItemFor103(noticeLineDto));
				}
			}
			return lineItemList;
		}
	
	private BAPI2017GMITEMCREATE getLineItemFor103(AdvanceShipmentNoticeLineDto noticeLineDto) {
		Map<String, String> plantList = refListService.getReferenceListMap(CoreReferenceConstants.GATE_IN_PLANT);
		Object value =plantList.get(noticeLineDto.getPoLine().getPlant());
		BAPI2017GMITEMCREATE lineItem=new BAPI2017GMITEMCREATE();		    	 		
		  lineItem.setMATERIAL(noticeLineDto.getPoLine().getCode());
		  
	//	 String outbounddeliveryNo=noticeLineDto.getPoLine().getPurchaseOrder().getOutboundDeliveryNo();
	//	  String doctype=noticeLineDto.getAdvanceshipmentnotice().getPo().getDoctyp();
		  String doctype=noticeLineDto.getAdvanceshipmentnotice().getDoctyp();
		  if("".equals(value) || value==null ){
		   //   lineItem.setMOVETYPE(MOVE_TYPE_103);		     
		      if("".equals(doctype) || doctype==null ) {
		    	  lineItem.setMOVETYPE(MOVE_TYPE_103);
		    	  
		      }
		      else {
		    	  lineItem.setMOVETYPE(MOVE_TYPE_101);
		      }
		  }
		  else if(doctype.equals("STO")) {
			  lineItem.setMOVETYPE(MOVE_TYPE_101);
		  }
		  else{
			  lineItem.setMOVETYPE(MOVE_TYPE_101);
		  }
		  lineItem.setENTRYQNT(new BigDecimal(noticeLineDto.getDeliveryQuantity()));
		  lineItem.setENTRYUOM(noticeLineDto.getPoLine().getUom());
		  lineItem.setPOITEM(noticeLineDto.getPoLine().getLineItemNumber());
		return lineItem;
	}
	

	
	private TABLEOFBAPI2017GMITEMCREATE getCreateWSInputLine105(List<AdvanceShipmentNoticeLineDto> noticeLinrDto){   	 
		TABLEOFBAPI2017GMITEMCREATE input = grnPortalObjectFactory.createTABLEOFBAPI2017GMITEMCREATE();
			List<BAPI2017GMITEMCREATE> lineItemList = input.getItem();
			lineItemList.clear();
			
			for(AdvanceShipmentNoticeLineDto noticeLineDto:noticeLinrDto) {			
			lineItemList.add(getLineItemFor105(noticeLineDto));			
			}
			return input;
		}
	
	private List<BAPI2017GMITEMCREATE> getLineItemList105(List<AdvanceShipmentNoticeLineDto> noticeLinrDto){   	 
			List<BAPI2017GMITEMCREATE> lineItemList = new ArrayList<>();
			
			for(AdvanceShipmentNoticeLineDto noticeLineDto:noticeLinrDto) {
				if(noticeLineDto.getDeliveryQuantity()>0){
					lineItemList.add(getLineItemFor105(noticeLineDto));
				}			
			}
			return lineItemList;
		}
	
	private List<BAPI2017GMITEMCREATE> getLineItemList101(List<AdvanceShipmentNoticeLineDto> noticeLinrDto){   	 
		List<BAPI2017GMITEMCREATE> lineItemList = new ArrayList<>();
		
		for(AdvanceShipmentNoticeLineDto noticeLineDto:noticeLinrDto) {
			if(noticeLineDto.getDeliveryQuantity()>0){
				lineItemList.add(getLineItemFor101(noticeLineDto));
			//	lineItemList.add(getLineItemFor103(noticeLineDto));
				
			}			
		}
		return lineItemList;
	}
	
	private List<BAPI2017GMITEMCREATE> getLineItemList104(List<AdvanceShipmentNoticeLineDto> noticeLinrDto){   	 
		List<BAPI2017GMITEMCREATE> lineItemList = new ArrayList<>();
		
		for(AdvanceShipmentNoticeLineDto noticeLineDto:noticeLinrDto) {		
			if(noticeLineDto.getDeliveryQuantity()>0){
				lineItemList.add(getLineItemFor104(noticeLineDto));
			}
		}
		return lineItemList;
	}
	
	private BAPI2017GMITEMCREATE getLineItemFor105(AdvanceShipmentNoticeLineDto noticeLineDto) {
		BAPI2017GMITEMCREATE lineItem=new BAPI2017GMITEMCREATE();		    	 		
		  lineItem.setMATERIAL(noticeLineDto.getPoLine().getCode());
		  lineItem.setSTGELOC(noticeLineDto.getStorageLocation()); 
		  String plant=noticeLineDto.getPoLine().getPlant();
		  
		  
		//  String outbounddeliveryNo=noticeLineDto.getPoLine().getPurchaseOrder().getOutboundDeliveryNo();
		
//		  if(plant.equals("1203") || plant.equals("1202") || plant.equals("1201")) {
			  
		      lineItem.setMOVETYPE(MOVE_TYPE_105);
	//	  }
//		  else if(outbounddeliveryNo!=null) {
//			  lineItem.setMOVETYPE(MOVE_TYPE_101);
//		  }
//		  else {
//			  lineItem.setMOVETYPE(MOVE_TYPE_101);
//		  }
//		  if(plant.equals("1880") || plant.equals("1820") || plant.equals("1810")) {
//		  
//	      lineItem.setMOVETYPE(MOVE_TYPE_105);
//	  }
//	  else {
//		  lineItem.setMOVETYPE(MOVE_TYPE_101);
//	  }
		  lineItem.setQUANTITY(new BigDecimal(noticeLineDto.getRejectedQuantity()));
		  lineItem.setSTUNQTYY1(new BigDecimal(noticeLineDto.getShortageQuantity()));
		  lineItem.setENTRYQNT(new BigDecimal(noticeLineDto.getConfirmQuantity()));
		  lineItem.setDELIVERYQNT(new BigDecimal(noticeLineDto.getDeliveryQuantity()));
		  lineItem.setENTRYUOM(noticeLineDto.getPoLine().getUom());
		  lineItem.setPOITEM(noticeLineDto.getPoLine().getLineItemNumber());
		  lineItem.setVALTYPE(noticeLineDto.getValueType());
		  if(noticeLineDto.getAsnLineCostCenter()!=null){
			  lineItem.setYservicecostcenter(getYSERVICECOSTCENTER(noticeLineDto));
		  }
		return lineItem;
	}
	
	
	private BAPI2017GMITEMCREATE getLineItemFor101(AdvanceShipmentNoticeLineDto noticeLineDto) {
		BAPI2017GMITEMCREATE lineItem=new BAPI2017GMITEMCREATE();	
		
		String MaterialName=noticeLineDto.getName();
		String MaterialCode=MaterialName.substring(0, 6);
		lineItem.setMATERIAL(MaterialCode);
		//  lineItem.setMATERIAL(noticeLineDto.getPoLine().getCode());
		  lineItem.setSTGELOC(noticeLineDto.getStorageLocation()); 
		  
		  String doctype=noticeLineDto.getAdvanceshipmentnotice().getDoctyp();
		//  String plant=noticeLineDto.getPoLine().getPlant();
		  String plant=noticeLineDto.getAdvanceshipmentnotice().getPlant();
		  
		//  String outbounddeliveryNo=noticeLineDto.getAdvanceshipmentnotice().getPo().getOutboundDeliveryNo();
	//	  String outbounddeliveryNo=noticeLineDto.getPoLine().getPurchaseOrder().getOutboundDeliveryNo();
		
		  if(plant.equals("1203") || plant.equals("1202") || plant.equals("1201")) {
			  
			  if(doctype.equals("STO")) {
		    	  lineItem.setMOVETYPE(MOVE_TYPE_101);
		      }
		      else {
		    	  lineItem.setMOVETYPE(MOVE_TYPE_105);
		      }
			  
		    //  lineItem.setMOVETYPE(MOVE_TYPE_105);
		  }
		  else if(doctype.equals("STO")) {
			  lineItem.setMOVETYPE(MOVE_TYPE_101);
		  }
		  else {
			  lineItem.setMOVETYPE(MOVE_TYPE_101);
		  }
//		  if(plant.equals("1880") || plant.equals("1820") || plant.equals("1810")) {
//		  
//	      lineItem.setMOVETYPE(MOVE_TYPE_105);
//	  }
//	  else {
//		  lineItem.setMOVETYPE(MOVE_TYPE_101);
//	  }
		//  lineItem.setREFDOC(outbounddeliveryNo);
		  lineItem.setQUANTITY(new BigDecimal(noticeLineDto.getRejectedQuantity()));
		  lineItem.setSTUNQTYY1(new BigDecimal(noticeLineDto.getShortageQuantity()));
		  lineItem.setENTRYQNT(new BigDecimal(noticeLineDto.getConfirmQuantity()));
		  lineItem.setENTRYUOM(noticeLineDto.getUom());
		//  lineItem.setENTRYUOM(noticeLineDto.getPoLine().getUom());
		//  lineItem.setPOITEM(noticeLineDto.getPoLine().getLineItemNumber());
		  if(noticeLineDto.getAsnLineCostCenter()!=null){
			  lineItem.setYservicecostcenter(getYSERVICECOSTCENTER(noticeLineDto));
		  }
		return lineItem;
	}
	
	private List<YSERVICECOSTCENTER> getYSERVICECOSTCENTER(AdvanceShipmentNoticeLineDto noticeLineDto){
		List<YSERVICECOSTCENTER> ySERVICECOSTCENTERList = new ArrayList<>();
		for(ASNLineCostCenterDto dto:noticeLineDto.getAsnLineCostCenter()){
			YSERVICECOSTCENTER ySERVICECOSTCENTER = new  YSERVICECOSTCENTER();
			ySERVICECOSTCENTER.setStorage(dto.getStorageLocation());
			ySERVICECOSTCENTER.setQty(dto.getQuantity());
			ySERVICECOSTCENTERList.add(ySERVICECOSTCENTER);
		}
		return ySERVICECOSTCENTERList;
	}
	
	private BAPI2017GMITEMCREATE getLineItemFor104(AdvanceShipmentNoticeLineDto noticeLineDto) {
		BAPI2017GMITEMCREATE lineItem=new BAPI2017GMITEMCREATE();		    	 		
		  lineItem.setMATERIAL(noticeLineDto.getPoLine().getCode());
		  lineItem.setSTGELOC(noticeLineDto.getStorageLocation()); 
		  lineItem.setMOVETYPE(MOVE_TYPE_104);
//		  lineItem.setQUANTITY(new BigDecimal(noticeLineDto.getRejectedQuantity()));
//		  lineItem.setSTUNQTYY1(new BigDecimal(noticeLineDto.getShortageQuantity()));
		  lineItem.setENTRYQNT(new BigDecimal(noticeLineDto.getDeliveryQuantity()));
		  lineItem.setENTRYUOM(noticeLineDto.getPoLine().getUom());
		  lineItem.setPOITEM(noticeLineDto.getPoLine().getLineItemNumber());
		return lineItem;
	}
	
	
	/*@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public String setASNForMoveType103(List<AdvanceShipmentNoticeLineDto> noticeLineDtos ){
		ZmmgrnPortal port = getPort(ADDRESS);
		
		
		setupClient(port, USER_NAME, PASSWORD);
		
		AdvanceShipmentNoticeLineDto noticeLineDto = null;
		AdvanceShipmentNoticeDto noticeDto=null;
		
		if(!(noticeLineDtos.size()==0)) {
			noticeLineDto=noticeLineDtos.get(0);		
		}
		
		if(noticeLineDto!=null) {
		 noticeDto=noticeLineDto.getAdvanceshipmentnotice();
		}
		
		TABLEOFZMMGRN inputVal = getCreateWSInput(noticeDto);
		Holder<TABLEOFZMMGRN> in = new Holder<>(inputVal);
						
		TABLEOFBAPI2017GMITEMCREATE lineItemInputVal=getCreateWSInputLine103(noticeLineDtos);
    	Holder<TABLEOFBAPI2017GMITEMCREATE> lineItemIn=new Holder<TABLEOFBAPI2017GMITEMCREATE>(lineItemInputVal);
		
   	 TABLEOFBAPI2017GMHEADRET output=grnPortalObjectFactory.createTABLEOFBAPI2017GMHEADRET();
   	 Holder<TABLEOFBAPI2017GMHEADRET> outputHolder=new Holder<TABLEOFBAPI2017GMHEADRET>(output);
   
   	 
   	 TABLEOFBAPIRET2 returnResponse=grnPortalObjectFactory.createTABLEOFBAPIRET2();
   	 Holder<TABLEOFBAPIRET2> returnHolder=new Holder<TABLEOFBAPIRET2>(returnResponse);
		
		String vendorCode = "" ;
		try {
			port.zmmGRNPORTAL(in,lineItemIn, outputHolder,returnHolder);
			if(null!=outputHolder && null!= outputHolder.value && !CommonUtil.isCollectionEmpty(outputHolder.value.item)){
				BAPI2017GMHEADRET outPut = outputHolder.value.item.get(0);
				vendorCode = outPut.matdoc;
				if(CommonUtil.isStringEmpty(vendorCode)){
					throw new RuntimeException(outPut.toString());
				}
			}
			return vendorCode;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}*/
	
	
	
	/*@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public String setASNForMoveType105(List<AdvanceShipmentNoticeLineDto> noticeLineDtos){
		ZmmgrnPortal port = getPort(ADDRESS);
		setupClient(port, USER_NAME, PASSWORD);
		
		AdvanceShipmentNoticeLineDto noticeLineDto = null;
		AdvanceShipmentNoticeDto noticeDto=null;
		
		if(!(noticeLineDtos.size()==0)) {
			noticeLineDto=noticeLineDtos.get(0);		
		}
		
		if(noticeLineDto!=null) {
		 noticeDto=noticeLineDto.getAdvanceshipmentnotice();
		}
		
		TABLEOFZMMGRN inputVal = getCreateWSInput(noticeDto);
		Holder<TABLEOFZMMGRN> in = new Holder<>(inputVal);
						
		TABLEOFBAPI2017GMITEMCREATE lineItemInputVal=getCreateWSInputLine105(noticeLineDtos);
    	Holder<TABLEOFBAPI2017GMITEMCREATE> lineItemIn=new Holder<TABLEOFBAPI2017GMITEMCREATE>(lineItemInputVal);
		
   	 TABLEOFBAPI2017GMHEADRET output=grnPortalObjectFactory.createTABLEOFBAPI2017GMHEADRET();
   	 Holder<TABLEOFBAPI2017GMHEADRET> outputHolder=new Holder<TABLEOFBAPI2017GMHEADRET>(output);
   
   	 
   	 TABLEOFBAPIRET2 returnResponse=grnPortalObjectFactory.createTABLEOFBAPIRET2();
   	 Holder<TABLEOFBAPIRET2> returnHolder=new Holder<TABLEOFBAPIRET2>(returnResponse);
		
		String vendorCode = "" ;
		try {
			port.zmmGRNPORTAL(in,lineItemIn, outputHolder,returnHolder);
			if(null!=outputHolder && null!= outputHolder.value && !CommonUtil.isCollectionEmpty(outputHolder.value.item)){
				BAPI2017GMHEADRET outPut = outputHolder.value.item.get(0);
				vendorCode = outPut.matdoc;
				if(CommonUtil.isStringEmpty(vendorCode)){
					throw new RuntimeException(outPut.toString());
				}
			}
			return vendorCode;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}*/
	
	
//	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
//	public @ResponseBody CustomResponseDto setASNForMoveType103(List<AdvanceShipmentNoticeLineDto> noticeLineDtos, String gDate, String user, String pass, String pono, Integer asnno, Long asnId ) throws Exception{
//		
//		GrnFtpObject grnObject = new GrnFtpObject();
//		
//		AdvanceShipmentNoticeDto asnDto=null;
//		 
//		
//		if(!CommonUtil.isCollectionEmpty(noticeLineDtos)) {
//			asnDto=noticeLineDtos.get(0).getAdvanceshipmentnotice();
//			asnDto.setIssueDate(gDate);
//		}
//		
//	
//		grnObject.setPoDetails(getZmmGRN(asnDto));
//		grnObject.setLineItem(getLineItemList103(noticeLineDtos));
//		
//		String vendorCode = "" ;
//		try {
//		//	String json = CommonUtil.getObjectJson(grnObject);
//			boolean result = false;
//			 try {
//					// Create a trust manager that does not validate certificate chains
//						TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
//							public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//								return null;
//							}
//
//							public void checkClientTrusted(X509Certificate[] certs, String authType) {
//							}
//
//							public void checkServerTrusted(X509Certificate[] certs, String authType) {
//							}
//						} };
//
//						// Install the all-trusting trust manager
//						SSLContext sc = null;
//						try {
//							sc = SSLContext.getInstance("SSL");
//						} catch (NoSuchAlgorithmException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//						try {
//							sc.init(null, trustAllCerts, new java.security.SecureRandom());
//						} catch (KeyManagementException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//						HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//
//						// Create all-trusting host name verifier
//						HostnameVerifier allHostsValid = new HostnameVerifier() {
//							public boolean verify(String hostname, SSLSession session) {
//								return true;
//							}
//						};
//
//						// Install the all-trusting host verifier
//						HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
//						 String username=user;
//					     String password=pass;
////					     String username="abap_ext1";
////					     String password="HanaDS@123";
//						String json = CommonUtil.getObjectJson(grnObject);
//					
////						String jsonnewlist = new String(newjsonlist.toString());
////						String json = mapper.writeValueAsString(jsonnewlist);
////						System.out.println(json);
//					 
//						String url ="https://vhaklds4ci.sap.alkylamines.com:44300/sap/bc/yweb03_ws_31?sap-client=110&po="+pono+"&asn="+asnno+"&asnid="+asnId;
//						System.out.println(url);
//						ResponseDto resp = new ResponseDto();
//						URLConnection request = null;
//						try {
//						//	URL u = new URL( URLEncoder.encode(url, "UTF-8"));
//							URL u = new URL(url.replace(" ","%20"));
//							request = u.openConnection();
//							request.setRequestProperty("Accept", "application/json");
//						     // snippet begins
//						     request.setRequestProperty("Authorization",
//						       "Basic " + Base64.getEncoder().encodeToString(
//						         (username + ":" + password).getBytes()
//						       )
//						     );
//							request.connect();
//							BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
//							StringBuilder sb = new StringBuilder();
//							String line;
//							while ((line = br.readLine()) != null) {
//								sb.append(line + "\n");
//							}
//							System.out.println(sb);
//							br.close();
//							
//							JSONObject obj = new JSONObject(sb.toString());
//							String flag = obj.getString("Stts_flg");
//							String docype = obj.getString("MAT_DOC");
//							String year = obj.getString("DOC_YEAR");
//							result = Boolean.parseBoolean(flag);	
//							Map<String, Object> params = new HashMap<>();
//							params.put("sap103Id", docype);
//							params.put("sap103Year", year);
//						//	int result1 = updateByJpql(params, AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asnId));
////							asnDto.setSap103Id(docType);
////							asnDto.setSap103Year(year);
//						}
//						catch (MalformedURLException ex) {
//							ex.printStackTrace();
//						} catch (IOException ex) {
//							ex.printStackTrace();
//								throw new RuntimeException("Error while writing file");
//										
//						} finally {
//							if (request != null) {
//								try {
//									((HttpURLConnection) request).disconnect();
//								} catch (Exception ex) {
//									ex.printStackTrace();
//								}
//							}
//
//						}
//					} catch (JSONException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//			
//		//	boolean result = mediaService.writeByteArrayTo(json.getBytes(), AppBaseConstant.FTP_GRN_TO_PROCESS_PATH, asnDto.getAdvanceShipmentNoticeNo()+".txt");
////			if(!result){
////				throw new RuntimeException("Error while writing file");
////			}
//			 if(result = false){
//					throw new RuntimeException("Error while writing file");
//					}
//			//return vendorCode;
//			 return null;
//		} catch (Exception e) {
//			throw e;
//		}
//	}
	

	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public String setASNForMoveType103(List<AdvanceShipmentNoticeLineDto> noticeLineDtos, String gDate ) throws Exception{
		
		GrnFtpObject grnObject = new GrnFtpObject();
		
		AdvanceShipmentNoticeDto asnDto=null;
		
		
		if(!CommonUtil.isCollectionEmpty(noticeLineDtos)) {
			asnDto=noticeLineDtos.get(0).getAdvanceshipmentnotice();
			asnDto.setIssueDate(gDate);
		}
		
	
		grnObject.setPoDetails(getZmmGRN(asnDto));
		grnObject.setLineItem(getLineItemList103(noticeLineDtos));
		
		String vendorCode = "" ;
		try {
			String json = CommonUtil.getObjectJson(grnObject);
			boolean result = mediaService.writeByteArrayTo(json.getBytes(), AppBaseConstant.FTP_GRN_TO_PROCESS_PATH, asnDto.getAdvanceShipmentNoticeNo()+".txt");
			if(!result){
				throw new RuntimeException("Error while writing file");
			}
			return vendorCode;
		} catch (Exception e) {
			throw e;
		}
	}



	@RequestMapping(value="/moveType103ForSAP/{asnId}" , method = RequestMethod.GET)
	 public @ResponseBody String moveType103ForSAP(@PathVariable("asnId") Long asnId) throws Exception {
		
		GrnFtpObject grnObject = new GrnFtpObject();
		AdvanceShipmentNoticeDto asnDto=null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("asnId", asnId);
		List<AdvanceShipmentNoticeLineDto> noticelineDtos = asnLineService.findDtos("getASNLinesByASNId", params);
		
		
		if(!CommonUtil.isCollectionEmpty(noticelineDtos)) {
			asnDto=noticelineDtos.get(0).getAdvanceshipmentnotice();
			
		}
		
		grnObject.setPoDetails(getZmmGRN(asnDto));
		grnObject.setLineItem(getLineItemList103(noticelineDtos));
		
		String vendorCode = "" ;
		try {
			String json = CommonUtil.getObjectJson(grnObject);
		//	boolean result = mediaService.writeByteArrayTo(json.getBytes(), AppBaseConstant.FTP_GRN_TO_PROCESS_PATH, asnDto.getAdvanceShipmentNoticeNo()+".txt");
		//	if(!result){
		//		throw new RuntimeException("Error while writing file");
		//	}
			return json;
		} catch (Exception e) {
			throw e;
		}
	
	}
	
//	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
//	public String setASNForMoveType103ForAPI(List<AdvanceShipmentNoticeLineDto> noticeLineDtos ) throws Exception{
//		
//		GrnFtpObject grnObject = new GrnFtpObject();
//		
//		AdvanceShipmentNoticeDto asnDto=null;
//		
//		
//		if(!CommonUtil.isCollectionEmpty(noticeLineDtos)) {
//			asnDto=noticeLineDtos.get(0).getAdvanceshipmentnotice();
//			
//		}
//		
//	
//		grnObject.setPoDetails(getZmmGRN(asnDto));
//		grnObject.setLineItem(getLineItemList103(noticeLineDtos));
//		
//		String vendorCode = "" ;
//		try {
//			String json = CommonUtil.getObjectJson(grnObject);
//		//	boolean result = mediaService.writeByteArrayTo(json.getBytes(), AppBaseConstant.FTP_GRN_TO_PROCESS_PATH, asnDto.getAdvanceShipmentNoticeNo()+".txt");
//		//	if(!result){
//		//		throw new RuntimeException("Error while writing file");
//		//	}
//			return json;
//		} catch (Exception e) {
//			throw e;
//		}
//	}
	
	@RequestMapping(value="/moveType105ForSAP/{asnId}" , method = RequestMethod.GET)
	 public @ResponseBody String moveType105ForSAP(@PathVariable("asnId") Long asnId) throws Exception{
			
		GrnFtpObject grnObject = new GrnFtpObject();
		AdvanceShipmentNoticeDto asnDto=null;
	//	Map<String, Object> params = new HashMap<String, Object>();
	//	params.put("asnId", asnId);
		List<AdvanceShipmentNoticeLineDto> noticelineDtos = asnLineService.findDtos("getASNLinesByASNId", AbstractContextServiceImpl.getParamMap("asnId", asnId));
		List<ASNLineCostCenterDto> strLoc=null;
		if(!CommonUtil.isCollectionEmpty(noticelineDtos)) {
			asnDto=noticelineDtos.get(0).getAdvanceshipmentnotice();
			strLoc= noticelineDtos.get(0).getAsnLineCostCenter();
		}
		ZMMGRN poDet=getZmmGRN105(asnDto);
		if(asnDto.getSap103Id()!=null) {
			if(!CommonUtil.isStringEmpty(asnDto.getSap103Id()) && !CommonUtil.isStringEmpty(asnDto.getSap103Year())){
				poDet.setMBLNR(asnDto.getSap103Id());
				poDet.setMJAHR(asnDto.getSap103Year());
			}
			}else {
				
				//throw new RuntimeException("GRN 103 is not processed yet.");
				return ("GRN 103 is not processed yet");
			}
		
		grnObject.setPoDetails(poDet);
		
		grnObject.setLineItem(getLineItemList105(noticelineDtos));
		
		String vendorCode = "" ;
		
		try {
			String json = CommonUtil.getObjectJson(grnObject);
		//	boolean result = mediaService.writeByteArrayTo(json.getBytes(), AppBaseConstant.FTP_GRN_TO_PROCESS_PATH, asnDto.getAdvanceShipmentNoticeNo()+".txt");
		//	if(!result){
		//		throw new RuntimeException("Error while writing file");
		//	}
			return json;
		} catch (Exception e) {
			throw e;
		}
	}
	
	
//	}
	
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public String setASNForMoveType105(List<AdvanceShipmentNoticeLineDto> noticeLineDtos ) throws Exception{
		
		
		GrnFtpObject grnObject = new GrnFtpObject();
		
		AdvanceShipmentNoticeDto asnDto=null;
		
		if(!CommonUtil.isCollectionEmpty(noticeLineDtos)) {
			asnDto=noticeLineDtos.get(0).getAdvanceshipmentnotice();
		}
		ZMMGRN poDet=getZmmGRN105(asnDto);
		//if(asnDto.getPo().getPoLineList().get(0).getPlant()=="1810" || asnDto.getPo().getPoLineList().get(0).getPlant()=="1820" || asnDto.getPo().getPoLineList().get(0).getPlant()=="1880") {
		if(asnDto.getSap103Id()!=null) {
		if(!CommonUtil.isStringEmpty(asnDto.getGrnId()) && !CommonUtil.isStringEmpty(asnDto.getGrnYear())){
			poDet.setMBLNR(asnDto.getGrnId());
			poDet.setMJAHR(asnDto.getGrnYear());
		}
		}else {
			
			throw new RuntimeException("GRN 103 is not processed yet.");
		}
//		else{
//			throw new RuntimeException("GRN 103 is not processed yet.");
//		}
		
		grnObject.setPoDetails(poDet);
		
		grnObject.setLineItem(getLineItemList105(noticeLineDtos));
		
		String vendorCode = "" ;
		try {
			String json = CommonUtil.getObjectJson(grnObject);
			boolean result=mediaService.writeByteArrayTo(json.getBytes(), AppBaseConstant.FTP_GRN_TO_PROCESS_PATH, asnDto.getAdvanceShipmentNoticeNo()+".txt");
			if(!result){
				throw new RuntimeException("Error while writing file");
			}
			return vendorCode;
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public String setASNForMoveType101(List<AdvanceShipmentNoticeLineDto> noticeLineDtos ) throws Exception{
		
		
		GrnFtpObject grnObject = new GrnFtpObject();
		
		AdvanceShipmentNoticeDto asnDto=null;
		
		if(!CommonUtil.isCollectionEmpty(noticeLineDtos)) {
			asnDto=noticeLineDtos.get(0).getAdvanceshipmentnotice();
		}
		ZMMGRN poDet=getZmmGRN101(asnDto);
		//if(asnDto.getPo().getPoLineList().get(0).getPlant()=="1810" || asnDto.getPo().getPoLineList().get(0).getPlant()=="1820" || asnDto.getPo().getPoLineList().get(0).getPlant()=="1880") {
//		if(asnDto.getSap103Id()!=null) {
//		if(!CommonUtil.isStringEmpty(asnDto.getGrnId()) && !CommonUtil.isStringEmpty(asnDto.getGrnYear())){
//			poDet.setMBLNR(asnDto.getGrnId());
//			poDet.setMJAHR(asnDto.getGrnYear());
//		}
//		}else {
//			
//			throw new RuntimeException("GRN 103 is not processed yet.");
//		}
//		else{
//			throw new RuntimeException("GRN 103 is not processed yet.");
//		}
		
		grnObject.setPoDetails(poDet);
		
		grnObject.setLineItem(getLineItemList101(noticeLineDtos));
	//	grnObject.setLineItem(getLineItemFor103(noticeLineDtos));
		
		String vendorCode = "" ;
		try {
			String json = CommonUtil.getObjectJson(grnObject);
			boolean result=mediaService.writeByteArrayTo(json.getBytes(), AppBaseConstant.FTP_GRN_TO_PROCESS_PATH, asnDto.getAdvanceShipmentNoticeNo()+".txt");
			if(!result){
				throw new RuntimeException("Error while writing file");
			}
			return vendorCode;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public String setASNForMoveType104(List<AdvanceShipmentNoticeLineDto> noticeLineDtos ) throws Exception{
		
		GrnFtpObject grnObject = new GrnFtpObject();
		
		AdvanceShipmentNoticeDto asnDto=null;
		
		if(!CommonUtil.isCollectionEmpty(noticeLineDtos)) {
			asnDto=noticeLineDtos.get(0).getAdvanceshipmentnotice();
		}
		ZMMGRN poDet=getZmmGRN(asnDto);
		if(!CommonUtil.isStringEmpty(asnDto.getGrnId()) && !CommonUtil.isStringEmpty(asnDto.getGrnYear())){
			poDet.setMBLNR(asnDto.getGrnId());
			poDet.setMJAHR(asnDto.getGrnYear());
		}else{
			throw new RuntimeException("GRN 103 is not processed yet.");
		}
		grnObject.setPoDetails(poDet);
		
		grnObject.setLineItem(getLineItemList104(noticeLineDtos));
		
		String vendorCode = "" ;
		try {
			String json = CommonUtil.getObjectJson(grnObject);
			boolean result=mediaService.writeByteArrayTo(json.getBytes(), AppBaseConstant.FTP_GRN_TO_PROCESS_PATH, asnDto.getAdvanceShipmentNoticeNo()+".txt");
			if(!result){
				throw new RuntimeException("Error while writing file");
			}
			return vendorCode;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
}
