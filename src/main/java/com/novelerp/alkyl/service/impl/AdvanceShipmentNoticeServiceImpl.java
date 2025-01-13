package com.novelerp.alkyl.service.impl;

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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.novelerp.alkyl.component.ASNComponent;
import com.novelerp.alkyl.dao.AdvanceShipmentNoticeDao;
import com.novelerp.alkyl.dao.SecurityPOHeaderDao;
import com.novelerp.alkyl.dto.ASNReadDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.POReadDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.dto.SSNReadDto;
import com.novelerp.alkyl.dto.SecurityPOHeaderDto;
import com.novelerp.alkyl.entity.AdvanceShipmentNotice;
import com.novelerp.alkyl.service.AdvanceShipmentNoticeLineService;
import com.novelerp.alkyl.service.AdvanceShipmentNoticeService;
import com.novelerp.alkyl.service.PurchaseOrderLineService;
import com.novelerp.alkyl.service.SecurityPOHeaderService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.entity.User;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;
import com.sap.document.sap.rfc.grn.ZmmGrnPortalClient;
import com.sap.document.sap.rfc.ses.ZMMServiceSheet_Client;

@Service
public class AdvanceShipmentNoticeServiceImpl extends AbstractContextServiceImpl<AdvanceShipmentNotice, AdvanceShipmentNoticeDto> implements AdvanceShipmentNoticeService{

	
	@Autowired
	private AdvanceShipmentNoticeDao advanceShipmentNoticeDao;
	

	@Autowired
	private AdvanceShipmentNoticeLineService asnLineService;
	
	@Autowired
	private ASNComponent asnComponent;
	
	@Autowired
	private ZMMServiceSheet_Client zmmServiceSheetClient;
	
	@Autowired
	private PurchaseOrderLineService poLineService;
	
	@Autowired
	private ZmmGrnPortalClient zmmGrnPortalClient;
	
	@Autowired
	private SecurityPOHeaderService securityPOHeaderService;
	
	@Autowired 
	private SecurityPOHeaderDao securityPOHeaderDao;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private AdvanceShipmentNoticeService asnService;
	
	@PostConstruct
	public void init(){
		
		super.init(AdvanceShipmentNoticeService.class, advanceShipmentNoticeDao, AdvanceShipmentNotice.class, AdvanceShipmentNoticeDto.class);
	setByPassProxy(true);
	}
	
	@Override
	public Integer getNewASNNumber(String plant){
		return advanceShipmentNoticeDao.getASNNumber(plant);
	}
	
	@Override
	public String getNewServiceSheetNumber(){
		return advanceShipmentNoticeDao.getServiceSheetNumber();
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateNewASNClosedStatus(Long asnId){
		
		return advanceShipmentNoticeDao.updateASNClosedStatus(asnId);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public AdvanceShipmentNoticeDto save(AdvanceShipmentNoticeDto asn) {
		
		if(asn==null){
			asn = new AdvanceShipmentNoticeDto();
			asn.setResponse(new ResponseDto(true,"Empty ASN"));
			return asn;
		}
		
		if(null==asn.getAsnLineList()){
			return asn;
		}
		List<AdvanceShipmentNoticeLineDto> asnLines = asn.getAsnLineList();
		if(null==asnLines.get(0) || null==asnLines.get(0).getPoLine() || CommonUtil.isStringEmpty(asnLines.get(0).getPoLine().getPlant())) {
			return asn;
		}
		asn.setAsnLineList(null);
		
		
		String status="";
		if( (AppBaseConstant.PO_DOCUMENT_TYPE_ZNPO.equals(asn.getPo().getDocumentType()) || AppBaseConstant.PO_DOCUMENT_TYPE_ZNPW.equals(asn.getPo().getDocumentType())) && !"SSAP".equals(asn.getStatus())) {
			
			status=AppBaseConstant.SERVICE_SHEET_STATUS_DRAFTED;
			
		}else if((AppBaseConstant.PO_DOCUMENT_TYPE_ZNPO.equals(asn.getPo().getDocumentType()) || AppBaseConstant.PO_DOCUMENT_TYPE_ZNPW.equals(asn.getPo().getDocumentType())) && "SSAP".equals(asn.getStatus())){
			status=AppBaseConstant.SERVICE_ENTRY_SHEET_STATUS_DRAFTED;
		}
		else
		{
			status=AppBaseConstant.ASN_STATUS_DRAFTED;
			
		}
		
		if(asn.getAdvanceShipmentNoticeId()==null){
			if("9".equals(asn.getPo().getPstyp())){
				
				asn.setServiceSheetNo(asnComponent.getNewServiceSheetNumber());
			
			}else{
				asn.setAdvanceShipmentNoticeNo(asnComponent.getNewASNNumber(asnLines.get(0).getPoLine().getPlant()));
			}
			
			asn.setStatus(status);
			asn = super.save(asn);
		}else{
			asn.setStatus(status);
			asn = super.updateDto(asn);
			
			
//			Map<String , Object> parameter = new HashMap<String, Object>();
//					//AbstractContextServiceImpl.getParamMap("status", status);
//			parameter.put("invoiceNo", asn.getInvoiceNo());
//			parameter.put("invoiceDate", asn.getInvoiceDate());
//			parameter.put("transporterNo", asn.getTransporterNo());
//			parameter.put("lrNumber", asn.getLrNumber());
//			parameter.put("lrDate", asn.getLrDate());
//			parameter.put("vehicalNo", asn.getVehicalNo());
//			parameter.put("numberOfPackages", asn.getNumberOfPackages());
//			parameter.put("grossWeight", asn.getGrossWeight());
//			parameter.put("netWeight", asn.getNetWeight());
//			parameter.put("tareWeight", asn.getTareWeight());
//			parameter.put("nameOfDriver", asn.getNameOfDriver());
//			parameter.put("mobileNumber", asn.getMobileNumber());
//			parameter.put("photoIdProof", asn.getPhotoIdProof());
//			parameter.put("serviceFromDate", asn.getServiceFromDate());
//			parameter.put("serviceToDate", asn.getServiceToDate());
//			parameter.put("serviceLocation", asn.getServiceLocation());
//			
//			int Asnresult = asnService.updateByJpql(parameter,AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asn.getAdvanceShipmentNoticeId()));
		}
		
		asn.setAsnLineList(asnLineService.save(asnLines,asn));
		return asn;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public AdvanceShipmentNoticeDto saveasn(AdvanceShipmentNoticeDto asn) {
		
		
		
		if(asn==null){
			asn = new AdvanceShipmentNoticeDto();
			asn.setResponse(new ResponseDto(true,"Empty ASN"));
			return asn;
		}
		
		if(null==asn.getAsnLineList()){
			return asn;
		}
		List<AdvanceShipmentNoticeLineDto> asnLines = asn.getAsnLineList();
		if(null==asnLines.get(0) || null==asnLines.get(0).getPoLine() || CommonUtil.isStringEmpty(asnLines.get(0).getPoLine().getPlant())) {
			return asn;
		}
		asn.setAsnLineList(null);
	
		if(asn.getAdvanceShipmentNoticeId()==null){
			if("9".equals(asn.getPo().getPstyp())){
				asn.setServiceSheetNo(asnComponent.getNewServiceSheetNumber());
			}else{
				asn.setAdvanceShipmentNoticeNo(asnComponent.getNewASNNumber(asnLines.get(0).getPoLine().getPlant()));
			//	asn.setAdvanceShipmentNoticeNo(asn.getAdvanceShipmentNoticeNo());
			}
			
			
			
			 String	status=AppBaseConstant.ASN_STATUS_REPORTED;
		     asn.setReportedBy(contextService.getUser());
		     asn.setReportedDate(new Date());
			 asn.setStatus(status);
			// asn.setDoctyp("PO");
			 asn = super.save(asn);
			
		}
		else{
//			int advanceShipmentNoticeNo=asn.getAdvanceShipmentNoticeNo();
//			AdvanceShipmentNoticeDto newasn=asnService.findDto("getASNByASNNoforCommercial", AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeNo", asn.getAdvanceShipmentNoticeNo()));
			
	//		AdvanceShipmentNoticeLineDto asnLine=asn.getAsnLineList().get(0);
			Map<String , Object> parameter = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.ASN_STATUS_GATE_IN);
			parameter.put("invoiceNo", asn.getInvoiceNo());
			parameter.put("invoiceDate", asn.getInvoiceDate());
			parameter.put("transporterNo", asn.getTransporterNo());
			parameter.put("lrNumber", asn.getLrNumber());
			parameter.put("lrDate", asn.getLrDate());
			parameter.put("vehicalNo", asn.getVehicalNo());
			parameter.put("numberOfPackages", asn.getNumberOfPackages());
			parameter.put("grossWeight", asn.getGrossWeight());
			parameter.put("netWeight", asn.getNetWeight());
			parameter.put("tareWeight", asn.getTareWeight());
			parameter.put("nameOfDriver", asn.getNameOfDriver());
			parameter.put("mobileNumber", asn.getMobileNumber());
			parameter.put("photoIdProof", asn.getPhotoIdProof());
			parameter.put("serviceFromDate", asn.getServiceFromDate());
			parameter.put("serviceToDate", asn.getServiceToDate());
			parameter.put("serviceLocation", asn.getServiceLocation());
			
			
			int Asnresult = asnService.updateByJpql(parameter,AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asn.getAdvanceShipmentNoticeId()));
//			return null ;
//			String	status=AppBaseConstant.ASN_STATUS_103_IN_Progress;
//			asn.setStatus(status);
	//		asn = super.updateDto(asn);
		}
		
		asn.setAsnLineList(asnLineService.savelist(asnLines,asn));
		return asn;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public AdvanceShipmentNoticeDto saveasnDirectFor103(AdvanceShipmentNoticeDto asn) {
		
		
		
		if(asn==null){
			asn = new AdvanceShipmentNoticeDto();
			asn.setResponse(new ResponseDto(true,"Empty ASN"));
			return asn;
		}
		
		if(null==asn.getAsnLineList()){
			return asn;
		}
		List<AdvanceShipmentNoticeLineDto> asnLines = asn.getAsnLineList();
		if(null==asnLines.get(0) || null==asnLines.get(0).getPoLine() || CommonUtil.isStringEmpty(asnLines.get(0).getPoLine().getPlant())) {
			return asn;
		}
		asn.setAsnLineList(null);
	
		if(asn.getAdvanceShipmentNoticeId()==null){
			if("9".equals(asn.getPo().getPstyp())){
				asn.setServiceSheetNo(asnComponent.getNewServiceSheetNumber());
			}else{
				asn.setAdvanceShipmentNoticeNo(asnComponent.getNewASNNumber(asnLines.get(0).getPoLine().getPlant()));
			//	asn.setAdvanceShipmentNoticeNo(asn.getAdvanceShipmentNoticeNo());
			}
			
			
			
			 String	status=AppBaseConstant.ASN_STATUS_GATE_IN;
//		     asn.setReportedBy(contextService.getUser());
//		     asn.setReportedDate(new Date());
			 asn.setStatus(status);
			// asn.setDoctyp("PO");
			 asn = super.save(asn);
			
		}
		else{
//			int advanceShipmentNoticeNo=asn.getAdvanceShipmentNoticeNo();
//			AdvanceShipmentNoticeDto newasn=asnService.findDto("getASNByASNNoforCommercial", AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeNo", asn.getAdvanceShipmentNoticeNo()));
			
	//		AdvanceShipmentNoticeLineDto asnLine=asn.getAsnLineList().get(0);
			Map<String , Object> parameter = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.ASN_STATUS_GATE_IN);
			parameter.put("invoiceNo", asn.getInvoiceNo());
			parameter.put("invoiceDate", asn.getInvoiceDate());
			parameter.put("transporterNo", asn.getTransporterNo());
			parameter.put("lrNumber", asn.getLrNumber());
			parameter.put("lrDate", asn.getLrDate());
			parameter.put("vehicalNo", asn.getVehicalNo());
			parameter.put("numberOfPackages", asn.getNumberOfPackages());
			parameter.put("grossWeight", asn.getGrossWeight());
			parameter.put("netWeight", asn.getNetWeight());
			parameter.put("tareWeight", asn.getTareWeight());
			parameter.put("nameOfDriver", asn.getNameOfDriver());
			parameter.put("mobileNumber", asn.getMobileNumber());
			parameter.put("photoIdProof", asn.getPhotoIdProof());
			parameter.put("serviceFromDate", asn.getServiceFromDate());
			parameter.put("serviceToDate", asn.getServiceToDate());
			parameter.put("serviceLocation", asn.getServiceLocation());
			
			
			int Asnresult = asnService.updateByJpql(parameter,AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asn.getAdvanceShipmentNoticeId()));
//			return null ;
//			String	status=AppBaseConstant.ASN_STATUS_103_IN_Progress;
//			asn.setStatus(status);
	//		asn = super.updateDto(asn);
		}
		
		asn.setAsnLineList(asnLineService.savelist(asnLines,asn));
		return asn;
	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public AdvanceShipmentNoticeDto saveasnwithoutpo(AdvanceShipmentNoticeDto asn) {
		
		
		
		if(asn==null){
			asn = new AdvanceShipmentNoticeDto();
			asn.setResponse(new ResponseDto(true,"Empty ASN"));
			return asn;
		}
		
		if(null==asn.getAsnLineList()){
			return asn;
		}
		List<AdvanceShipmentNoticeLineDto> asnLines = asn.getAsnLineList();
		if(null==asnLines.get(0)) {
			return asn;
		}
		asn.setAsnLineList(null);
	
		if(asn.getAdvanceShipmentNoticeId()==null){
			
				asn.setAdvanceShipmentNoticeNo(asnComponent.getNewASNNumber(asn.getPlant()));
			//	asn.setAdvanceShipmentNoticeNo(asn.getAdvanceShipmentNoticeNo());
			
			
			
			
			 String	status=AppBaseConstant.ASN_STATUS_REPORTED;
		     asn.setReportedBy(contextService.getUser());
		     asn.setReportedDate(new Date());
			asn.setStatus(status);
			asn = super.save(asn);
			
		}
		else{
//			int advanceShipmentNoticeNo=asn.getAdvanceShipmentNoticeNo();
//			AdvanceShipmentNoticeDto newasn=asnService.findDto("getASNByASNNoforCommercial", AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeNo", asn.getAdvanceShipmentNoticeNo()));
			
	//		AdvanceShipmentNoticeLineDto asnLine=asn.getAsnLineList().get(0);
			Map<String , Object> parameter = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.ASN_STATUS_GATE_IN);
			parameter.put("invoiceNo", asn.getInvoiceNo());
			parameter.put("invoiceDate", asn.getInvoiceDate());
			parameter.put("transporterNo", asn.getTransporterNo());
			parameter.put("lrNumber", asn.getLrNumber());
			parameter.put("lrDate", asn.getLrDate());
			parameter.put("vehicalNo", asn.getVehicalNo());
			parameter.put("numberOfPackages", asn.getNumberOfPackages());
			parameter.put("grossWeight", asn.getGrossWeight());
			parameter.put("netWeight", asn.getNetWeight());
			parameter.put("tareWeight", asn.getTareWeight());
			parameter.put("nameOfDriver", asn.getNameOfDriver());
			parameter.put("mobileNumber", asn.getMobileNumber());
			parameter.put("photoIdProof", asn.getPhotoIdProof());
			parameter.put("serviceFromDate", asn.getServiceFromDate());
			parameter.put("serviceToDate", asn.getServiceToDate());
			parameter.put("serviceLocation", asn.getServiceLocation());
			
			
			int Asnresult = asnService.updateByJpql(parameter,AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asn.getAdvanceShipmentNoticeId()));
//			return null ;
//			String	status=AppBaseConstant.ASN_STATUS_103_IN_Progress;
//			asn.setStatus(status);
	//		asn = super.updateDto(asn);
		}
		
		asn.setAsnLineList(asnLineService.savelist(asnLines,asn));
		return asn;
	}
	
	
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean approveServiceEntrySheet(AdvanceShipmentNoticeDto asn) throws JsonProcessingException{
		int result = updateByJpql(AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.SERVICE_ENTRY_SHEET_STATUS_APPROVED)
				, AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asn.getAdvanceShipmentNoticeId()));
		/*zmmServiceSheetClient.postServiceEntrySheet(asn);*/
		return true ;
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
//	public boolean billBooking(AdvanceShipmentNoticeDto asn) throws JsonProcessingException{
	public boolean billBooking(AdvanceShipmentNoticeDto asn) throws JsonProcessingException{
		Map<String, Object> asnValue = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.BILL_BOOKED);
		asnValue.put("invoiceNo", asn.getInvoiceNo());
		int result = updateByJpql(asnValue
				, AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asn.getAdvanceShipmentNoticeId()));
		if("9".equals(asn.getPo().getPstyp())){
			
		
		Map<String, Object> propertyValueMap = AbstractContextServiceImpl.getParamMap("tds", asn.getTds());
		propertyValueMap.put("baseAmount", asn.getBaseAmount());
		propertyValueMap.put("issueDate", asn.getIssueDate());
		propertyValueMap.put("payable", asn.getPayable());
		propertyValueMap.put("withHoldingTax", asn.getWithHoldingTax());
		propertyValueMap.put("invoiceDate", asn.getInvoiceDate());
		propertyValueMap.put("miroDescription", asn.getMiroDescription());
		updateByJpql(propertyValueMap, AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asn.getAdvanceShipmentNoticeId()));
		for(AdvanceShipmentNoticeLineDto asnLine : asn.getAsnLineList()){
			Map<String, Object> propValueMap = AbstractContextServiceImpl.getParamMap("glno", asnLine.getPoLine().getGlno());
			
			poLineService.updateByJpql(propValueMap, AbstractContextServiceImpl.getParamMap("purchaseOrderLineId", asnLine.getPoLine().getPurchaseOrderLineId()));
		}
//		return zmmServiceSheetClient.postServiceEntrySheet(asn);
		return true;
		}else{
//		return zmmServiceSheetClient.postServiceEntrySheet(asn);
			return false;
		}
	}
	//--------------------------------------------------- with FTP----------------------------------------------------------
//	@Override
//	@Transactional(propagation=Propagation.REQUIRED)
//	public boolean processGateIn(Long asnId,List<AdvanceShipmentNoticeLineDto> lineDtos,Map<String , Object> param ) throws Exception{
//
//		User gateinPostedby= new User();
//		gateinPostedby.setUserId(contextService.getUser().getUserId());
//		param.put("gateinPostedby", gateinPostedby);
//		int result = updateByJpql(param, AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asnId));
//		zmmGrnPortalClient.setASNForMoveType103(lineDtos,(String) param.get("issueDate"));
//		//zmmGrnPortalClient.setASNForMoveType103ForAPI(lineDtos);
//		return result>0;
//		
//	}
	//--------------------------------------------------- with FTP----------------------------------------------------------
	
	//--------------------------------------------------- with WS----------------------------------------------------------
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean processGateIn(Long asnId,List<AdvanceShipmentNoticeLineDto> lineDtos,Map<String , Object> param ) throws Exception{

		User gateinPostedby= new User();
		gateinPostedby.setUserId(contextService.getUser().getUserId()); 
		Object sap103Id = param.get("sap103Id");
		Object sap103Year = param.get("sap103Year");
	
		param.put("gateinPostedby", gateinPostedby);
		param.put("sap103Id", sap103Id);
		param.put("sap103Year", sap103Year);
		param.put("status", AppBaseConstant.ASN_STATUS_103_Posted);
		
		int result = updateByJpql(param, AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asnId));
	//	zmmGrnPortalClient.setASNForMoveType103(lineDtos,(String) param.get("issueDate"));
		//zmmGrnPortalClient.setASNForMoveType103ForAPI(lineDtos);
		return result>0;
		
	}
	
	//--------------------------------------------------- with WS----------------------------------------------------------
	@Override
	public boolean updateNewASNGateOutStatus(Long asnId) {
		
		return advanceShipmentNoticeDao.updateASNGateOutStatus(asnId);
	}

	@Override
	public Long getOpenAsnCountbyPartnerId(Long partnerId) {
		Map<String, Object> params = new HashMap<>();
		params.put("partnerId", partnerId);
		params.put("status", AppBaseConstant.ASN_STATUS_CLOSED);
		return getRecordCount("c.partner.bPartnerId = :partnerId and c.isActive = 'Y' and c.status !=:status ", params);
	}

	@Override
	public Long getPendingPoBillBookingCountbyPartnerId(Long partnerId) {
		Map<String, Object> params = new HashMap<>();
		params.put("partnerId", partnerId);
		params.put("status", AppBaseConstant.ASN_STATUS_CLOSED);
		return getRecordCount("c.partner.bPartnerId = :partnerId and c.isActive = 'Y' and c.status =:status ", params);
	}
	
	@Override
	public List<AdvanceShipmentNoticeDto> getASNReportbyFilter(ASNReadDto dto) {
		Map<String, Object> params=getParameterMap(dto);
	//	System.out.println(dto.toString());
		//ASNReadDto getdto = getdto(dto);
		List<String> param = new ArrayList<>();
		if(dto.getStatus().equals("Gate In")) {
			param.add(AppBaseConstant.ASN_STATUS_GATE_IN);
			params.put("status", param);
			
		}
		
		if(dto.getStatus().equals("Gate Out")) {
			param.add(AppBaseConstant.ASN_STATUS_GATE_OUT);
			params.put("status", param);
		}
		
		if(dto.getStatus().equals("Drafted")) {
			param.add(AppBaseConstant.ASN_STATUS_DRAFTED);
			params.put("status", param);
		}
		
		if(dto.getStatus().equals("GRN Posted")) {
			param.add(AppBaseConstant.ASN_STATUS_105);
			params.put("status", param);
		}
		
		if(dto.getStatus().equals("In Transit")) {
			param.add(AppBaseConstant.ASN_STATUS_IN_TRANSIT);
			params.put("status", param);
		}
		
		if(dto.getStatus().equals("CANCELED")) {
			param.add(AppBaseConstant.ASN_STATUS_CANCELED);
			params.put("status", param);
		}
		
		if(dto.getStatus().equals("Closed")) {
			param.add(AppBaseConstant.ASN_STATUS_CLOSED);
			params.put("status", param);
		}
		
		if(dto.getStatus().equals("OHC Failed")) {
			param.add(AppBaseConstant.ASN_STATUS_OHC_FAILED);
			params.put("status", param);
		}
		
		if(dto.getStatus().equals("OHC Hold")) {
			param.add(AppBaseConstant.ASN_STATUS_OHC_HOLD);
			params.put("status", param);
		}
		
		if(dto.getStatus().equals("OHC Passed")) {
			param.add(AppBaseConstant.ASN_STATUS_OHC_PASSED);
			params.put("status", param);
		}
		
		if(dto.getStatus().equals("QC Failed")) {
			param.add(AppBaseConstant.ASN_STATUS_QC_FAILED);
			params.put("status", param);
		}
		
		if(dto.getStatus().equals("QC Passed")) {
			param.add(AppBaseConstant.ASN_STATUS_QC_PASSED);
			params.put("status", param);
		}
		
		if(dto.getStatus().equals("Reported")) {
			param.add(AppBaseConstant.ASN_STATUS_REPORTED);
			params.put("status", param);
		}
		if(dto.getStatus().equals("Saftey Failed")) {
			param.add(AppBaseConstant.ASN_STATUS_SAFETY_CHECK_FAILED);
			params.put("status", param);
		}
		if(dto.getStatus().equals("Saftey Hold")) {
			param.add(AppBaseConstant.ASN_STATUS_SAFETY_CHECK_HOLD);
			params.put("status", param);
		}
		if(dto.getStatus().equals("Saftey Passed")) {
			param.add(AppBaseConstant.ASN_STATUS_SAFETY_CHECK_PASSED);
			params.put("status", param);
		}
		if(dto.getStatus().equals("Unload")) {
			param.add(AppBaseConstant.ASN_STATUS_UNLOAD);
			params.put("status", param);
			
		}
		if(dto.getStatus().equals("103_IN_Progress")) {
			param.add(AppBaseConstant.ASN_STATUS_103_IN_Progress);
			params.put("status", param);
			
		}
		
		if(dto.getStatus().equals("103_Posted")) {
			param.add(AppBaseConstant.ASN_STATUS_103_Posted);
			params.put("status", param);
			
		}
		
		String query=advanceShipmentNoticeDao.getASNReportlist(dto);
		List<AdvanceShipmentNoticeDto> asnList=findDtosByQuery(query, params);
		return asnList;
	}
	private Map<String,Object> getParameterMap(ASNReadDto dto){
		System.out.println(dto.toString());
		Map<String, Object> params = new HashMap<String, Object>();
		if(dto.getPoNoFrom()!=null && dto.getPoNoTo()!=null){
			params.put("fromNo", dto.getPoNoFrom());
			params.put("toNo", dto.getPoNoTo());
   		}
   		if(dto.getPoNoFrom()!=null && dto.getPoNoTo()==null){
   			params.put("fromNo", dto.getPoNoFrom());
   		}
   		if(dto.getPoNoFrom()==null && dto.getPoNoTo()!=null){
   			params.put("toNo", dto.getPoNoTo());
   		}
   		if(dto.getAsnNoFrom()!=null && dto.getAsnNoTo()!=null){
			params.put("asnNoFrom", dto.getAsnNoFrom());
			params.put("asnNoTo", dto.getAsnNoTo());
   		}
   		if(dto.getAsnNoFrom()!=null && dto.getAsnNoTo()==null){
   			params.put("asnNoFrom", dto.getAsnNoFrom());
   		}
   		if(dto.getAsnNoFrom()==null && dto.getAsnNoTo()!=null){
   			params.put("asnNoTo", dto.getAsnNoTo());
   		}
   		if(dto.getAsnDateFrom()!=null && dto.getAsnDateTo()!=null){
   			//params.put("fromDate", dto.getAsnDateFrom());
			//params.put("toDate", dto.getAsnDateTo());
   			params.put("fromDate", dto.getStartAsnDateFrom());
   			dto.setAsnDateFrom(dto.getStartAsnDateFrom());
   			params.put("toDate", dto.getLastAsnDateTo());
   			dto.setAsnDateTo(dto.getLastAsnDateTo());
   		}
   		if(dto.getAsnDateFrom()!=null && dto.getAsnDateTo()==null){
   			params.put("fromDate", dto.getStartAsnDateFrom());
			params.put("toDate", dto.getLastAsnDateFrom());
			dto.setAsnDateFrom(dto.getStartAsnDateFrom());
			dto.setAsnDateTo(dto.getLastAsnDateFrom());
   		}
   		
   		if(dto.getAsnDateFrom()==null && dto.getAsnDateTo()!=null){
   			params.put("fromDate", dto.getStartAsnDateTo());
			params.put("toDate", dto.getLastAsnDateTo());
			dto.setAsnDateFrom(dto.getStartAsnDateTo());
			dto.setAsnDateTo(dto.getLastAsnDateTo());
   		}
   		
   		if(dto.getVendorCode()!=null && !dto.getVendorCode().equals("")){
   			params.put("vendorCode",dto.getVendorCode());
   		}
   		
   		if(dto.getStatus()!=null && !dto.getStatus().equals("")){
   			params.put("status",dto.getStatus());
   		}

   		if(dto.getRequestedBy()!=null && !dto.getRequestedBy().equals("")){
   			params.put("requestedBy",dto.getRequestedBy());
   		}
   		
   		if(dto.getPlant()!=null && !dto.getPlant().equals("")){
   			params.put("plant",dto.getPlant());
   		}

		return params;
	}
	
	
	
	
	@Override
	public List<AdvanceShipmentNoticeDto> getSSNReportbyFilter(SSNReadDto dto) {
		Map<String, Object> params=getParameterMap(dto);
		List<String> param = new ArrayList<>();
		if(dto.getStatus().equals("Closed")) {
			param.add(AppBaseConstant.SERVICE_ENTRY_SHEET_STATUS_CLOSED);
			params.put("status", param);
			
		}
		if(dto.getStatus().equals("Service Canceled")) {
			param.add(AppBaseConstant.ASN_STATUS_CANCELED);
			params.put("status", param);
			
		}
		if(dto.getStatus().equals("Service Entry Note Approved")) {
			param.add(AppBaseConstant.SERVICE_ENTRY_SHEET_STATUS_APPROVED);
			params.put("status", param);
			
		}
		if(dto.getStatus().equals("Service Entry Note InProgress")) {
			param.add(AppBaseConstant.SERVICE_ENTRY_SHEET_STATUS_IN_PROGRESS);
			params.put("status", param);
			
		}
		if(dto.getStatus().equals("Service Entry Note Drafted")) {
			param.add(AppBaseConstant.SERVICE_ENTRY_SHEET_STATUS_DRAFTED);
			params.put("status", param);
			
		}
		if(dto.getStatus().equals("Service Entry Note Rejected")) {
			param.add(AppBaseConstant.SERVICE_ENTRY_SHEET_STATUS_REJECTED);
			params.put("status", param);
			
		}
		if(dto.getStatus().equals("Service Status Approved")) {
			param.add(AppBaseConstant.SERVICE_SHEET_STATUS_APPROVED_2);
			params.put("status", param);
			
		}
		if(dto.getStatus().equals("Service Status Aproved")) {
			param.add(AppBaseConstant.SERVICE_SHEET_STATUS_APPROVED);
			params.put("status", param);
			
		}
		if(dto.getStatus().equals("Service Status Created")) {
			param.add(AppBaseConstant.SERVICE_SHEET_STATUS_IN_PROGRESS);
			params.put("status", param);
			
		}
		if(dto.getStatus().equals("Service Status Drafted")) {
			param.add(AppBaseConstant.SERVICE_SHEET_STATUS_DRAFTED);
			params.put("status", param);
			
		}
		if(dto.getStatus().equals("Service Status Rejected")) {
			param.add(AppBaseConstant.SERVICE_SHEET_STATUS_REJECTED);
			params.put("status", param);
			
		}
		
		String query=advanceShipmentNoticeDao.getSSNReportlist(dto);
		List<AdvanceShipmentNoticeDto> ssnList=findDtosByQuery(query, params);
	return ssnList;
	}
	private Map<String,Object> getParameterMap(SSNReadDto dto){
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(dto.getPoNoFrom()!=null && dto.getPoNoTo()!=null){
			params.put("fromNo", dto.getPoNoFrom());
			params.put("toNo", dto.getPoNoTo());
   		}
		
		if(dto.getPoNoFrom()!=null && dto.getPoNoTo()==null){
   			params.put("fromNo", dto.getPoNoFrom());
   		}
   		if(dto.getPoNoFrom()==null && dto.getPoNoTo()!=null){
   			params.put("toNo", dto.getPoNoTo());
   		}
   		
   		if(dto.getSsnNoFrom()!=null && dto.getSsnNoTo()!=null){
			params.put("ssnNoFrom", dto.getSsnNoFrom());
			params.put("ssnNoTo", dto.getSsnNoTo());
   		}
   		if(dto.getSsnNoFrom()!=null && dto.getSsnNoTo()==null){
   			params.put("ssnNoFrom", dto.getSsnNoFrom());
   		}
   		if(dto.getSsnNoFrom()==null && dto.getSsnNoTo()!=null){
   			params.put("ssnNoTo", dto.getSsnNoTo());
   		}
   		if(dto.getSsnDateFrom()!=null && dto.getSsnDateTo()!=null){
   			//params.put("fromDate", dto.getAsnDateFrom());
			//params.put("toDate", dto.getAsnDateTo());
   			params.put("fromDate", dto.getStartSsnDateFrom());
   			dto.setSsnDateFrom(dto.getStartSsnDateFrom());
   			params.put("toDate", dto.getLastSsnDateTo());
   			dto.setSsnDateTo(dto.getLastSsnDateTo());
   		}
   		if(dto.getSsnDateFrom()!=null && dto.getSsnDateTo()==null){
   			params.put("fromDate", dto.getStartSsnDateFrom());
			params.put("toDate", dto.getLastSsnDateFrom());
			dto.setSsnDateFrom(dto.getStartSsnDateFrom());
			dto.setSsnDateTo(dto.getLastSsnDateFrom());
   		}
   		
   		if(dto.getSsnDateFrom()==null && dto.getSsnDateTo()!=null){
   			params.put("fromDate", dto.getStartSsnDateTo());
			params.put("toDate", dto.getLastSsnDateTo());
			dto.setSsnDateFrom(dto.getStartSsnDateTo());
			dto.setSsnDateTo(dto.getLastSsnDateTo());
   		}
   		
   		if(dto.getVendorCode()!=null && !dto.getVendorCode().equals("")){
   			params.put("vendorCode",dto.getVendorCode());
   		}
   		
   		if(dto.getStatus()!=null && !dto.getStatus().equals("")){
   			params.put("status",dto.getStatus());
   		}

   		if(dto.getRequestedBy()!=null && !dto.getRequestedBy().equals("")){
   			params.put("requestedBy",dto.getRequestedBy());
   		}
   		
   		if(dto.getPlant()!=null && !dto.getPlant().equals("")){
   			params.put("plant",dto.getPlant());
   		}
   		
		return params;
	}

//	@Override
//	public boolean updateasn(Long asnId, AdvanceShipmentNoticeDto lineDtos) throws Exception {
//		// TODO Auto-generated method stub
//		return false;
//	}
	
	@Override
	public List<AdvanceShipmentNoticeDto> getASNbyFilter(ASNReadDto dto) {
		Map<String, Object> params=getASNParameterMap(dto);
	//	System.out.println(dto.toString());
		//ASNReadDto getdto = getdto(dto);
		List<String> param = new ArrayList<>();
	
			String role = contextService.getDefaultRole().getValue();
			String plant = dto.getPlant();
//			String plant = contextService.getUserDetails().getPlant();
			// BPartnerDto partner = contextService.getPartner();
			if (role.equals(AppBaseConstant.ROLE_SECURITY_ADMIN)) {
				param.add(AppBaseConstant.ASN_STATUS_IN_TRANSIT);
				param.add(AppBaseConstant.ASN_STATUS_OHC_PASSED);
				param.add(AppBaseConstant.ASN_STATUS_105);
				param.add(AppBaseConstant.ASN_STATUS_GATE_IN);
				param.add(AppBaseConstant.ASN_STATUS_QC_PASSED);
				param.add(AppBaseConstant.ASN_STATUS_UNLOAD);
				param.add(AppBaseConstant.ASN_STATUS_REPORTED);
				param.add(AppBaseConstant.ASN_STATUS_103_Posted);
			} else if (role.equals(AppBaseConstant.ROLE_SAFETY_ADMIN)) {
				param.add(AppBaseConstant.ASN_STATUS_REPORTED);
				param.add(AppBaseConstant.ASN_STATUS_SAFETY_CHECK_FAILED);
				param.add(AppBaseConstant.ASN_STATUS_SAFETY_CHECK_HOLD);
			}
			else if (role.equals(AppBaseConstant.ROLE_OHC_ADMIN)) {
				param.add(AppBaseConstant.ASN_STATUS_SAFETY_CHECK_PASSED);
				param.add(AppBaseConstant.ASN_STATUS_OHC_FAILED);
				param.add(AppBaseConstant.ASN_STATUS_OHC_HOLD);
			} else if (role.equals(AppBaseConstant.ROLE_STORE_ADMIN)) {
				param.add(AppBaseConstant.ASN_STATUS_IN_TRANSIT);
				param.add(AppBaseConstant.ASN_STATUS_GATE_OUT);
				param.add(AppBaseConstant.ASN_STATUS_QC_PASSED);
				param.add(AppBaseConstant.ASN_STATUS_GATE_IN);
				param.add(AppBaseConstant.ASN_STATUS_UNLOAD);
				param.add(AppBaseConstant.ASN_STATUS_103_Posted);
				param.add(AppBaseConstant.ASN_STATUS_101);
			} else if (role.equals(AppBaseConstant.ROLE_QC_ADMIN)) {
				param.add(AppBaseConstant.ASN_STATUS_QC_PASSED);
				param.add(AppBaseConstant.ASN_STATUS_GATE_IN);
				param.add(AppBaseConstant.ASN_STATUS_UNLOAD);
				param.add(AppBaseConstant.ASN_STATUS_QC_FAILED);
			} else if (role.equals(AppBaseConstant.ROLE_PARTNER_ADMIN)) {
				param.add(AppBaseConstant.ASN_STATUS_IN_TRANSIT);
				param.add(AppBaseConstant.ASN_STATUS_105);
				param.add(AppBaseConstant.ASN_STATUS_REPORTED);
				param.add(AppBaseConstant.ASN_STATUS_GATE_IN);
				param.add(AppBaseConstant.ASN_STATUS_UNLOAD);
//				param.add(AppBaseConstant.ASN_STATUS_CLOSED);
				param.add(AppBaseConstant.ASN_STATUS_GATE_OUT);
				param.add(AppBaseConstant.ASN_STATUS_103_Posted);
				param.add(AppBaseConstant.ASN_STATUS_101);
			}
			params.put("status", param);
			params.put("plant", plant);
		
		
		String query=advanceShipmentNoticeDao.getASNListBYStatus(dto);
		List<AdvanceShipmentNoticeDto> asnList=findDtosByQuery(query, params);
		
		return asnList;
		
	}
	private Map<String,Object> getASNParameterMap(ASNReadDto dto){
		System.out.println(dto.toString());
		Map<String, Object> params = new HashMap<String, Object>();
		if(dto.getPoNoFrom()!=null && dto.getPoNoTo()!=null){
			params.put("fromNo", dto.getPoNoFrom());
			params.put("toNo", dto.getPoNoTo());
   		}
   		if(dto.getPoNoFrom()!=null && dto.getPoNoTo()==null){
   			params.put("fromNo", dto.getPoNoFrom());
   		}
   		if(dto.getPoNoFrom()==null && dto.getPoNoTo()!=null){
   			params.put("toNo", dto.getPoNoTo());
   		}
   		if(dto.getAsnNoFrom()!=null && dto.getAsnNoTo()!=null){
			params.put("asnNoFrom", dto.getAsnNoFrom());
			params.put("asnNoTo", dto.getAsnNoTo());
   		}
   		if(dto.getAsnNoFrom()!=null && dto.getAsnNoTo()==null){
   			params.put("asnNoFrom", dto.getAsnNoFrom());
   		}
   		if(dto.getAsnNoFrom()==null && dto.getAsnNoTo()!=null){
   			params.put("asnNoTo", dto.getAsnNoTo());
   		}
   		if(dto.getAsnDateFrom()!=null && dto.getAsnDateTo()!=null){
   			//params.put("fromDate", dto.getAsnDateFrom());
			//params.put("toDate", dto.getAsnDateTo());
   			params.put("fromDate", dto.getStartAsnDateFrom());
   			dto.setAsnDateFrom(dto.getStartAsnDateFrom());
   			params.put("toDate", dto.getLastAsnDateTo());
   			dto.setAsnDateTo(dto.getLastAsnDateTo());
   		}
   		if(dto.getAsnDateFrom()!=null && dto.getAsnDateTo()==null){
   			params.put("fromDate", dto.getStartAsnDateFrom());
			params.put("toDate", dto.getLastAsnDateFrom());
			dto.setAsnDateFrom(dto.getStartAsnDateFrom());
			dto.setAsnDateTo(dto.getLastAsnDateFrom());
   		}
   		
   		if(dto.getAsnDateFrom()==null && dto.getAsnDateTo()!=null){
   			params.put("fromDate", dto.getStartAsnDateTo());
			params.put("toDate", dto.getLastAsnDateTo());
			dto.setAsnDateFrom(dto.getStartAsnDateTo());
			dto.setAsnDateTo(dto.getLastAsnDateTo());
   		}
   		
   		if(dto.getVendorCode()!=null && !dto.getVendorCode().equals("")){
   			params.put("vendorCode",dto.getVendorCode());
   		}
   		
   		if(dto.getStatus()!=null && !dto.getStatus().equals("")){
   			params.put("status",dto.getStatus());
   		}

   		if(dto.getRequestedBy()!=null && !dto.getRequestedBy().equals("")){
   			params.put("requestedBy",dto.getRequestedBy());
   		}
   		
   		if(dto.getPlant()!=null && !dto.getPlant().equals("")){
   			params.put("plant",dto.getPlant());
   		}

		return params;
	}



}
