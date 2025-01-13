package com.sap.document.sap.rfc.ses;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.novelerp.alkyl.component.ASNComponent;
import com.novelerp.alkyl.component.ASNLineComponent;
import com.novelerp.alkyl.dto.ASNLineCostCenterDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.service.AdvanceShipmentNoticeService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.SystemConfiguratorDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.util.DateUtil;

@RequestMapping("/rest")
@Component
public class ZMMServiceSheet_Client {

	@Autowired
	private ASNLineComponent asnLineComponent;
	
	@Autowired
	private ASNComponent asnComponent;
	
	@Autowired
	@Qualifier(value=AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
	private MediaService mediaService;
	
	@Autowired
	private SystemConfiguratorService sysConfigService;
	
	@Autowired
	private AdvanceShipmentNoticeService asnService;
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	/**
	 * @param asn
	 * @param param 
	 * @param errors 
	 * @return
	 * @throws JsonProcessingException
	 */
	public boolean postServiceEntrySheet(AdvanceShipmentNoticeDto asn) throws JsonProcessingException{
	//public CustomResponseDto postServiceEntrySheet(AdvanceShipmentNoticeDto asn, Map<String, Object> param) throws JsonProcessingException{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("value", AppBaseConstant.SSNVERSION);
		SystemConfiguratorDto sysConfig=sysConfigService.findDto("getSysConfiguratorByType",params);
		List<AdvanceShipmentNoticeLineDto> serviceLines=null;
		if("9".equals(asn.getPo().getPstyp())){
			serviceLines = asnLineComponent.getServiceLineByASNId(asn.getAdvanceShipmentNoticeId());
		}
		if(!"2".equals(sysConfig.getName())){
		if("9".equals(asn.getPo().getPstyp())){
		 for(AdvanceShipmentNoticeLineDto lineDto: serviceLines){
			for(AdvanceShipmentNoticeLineDto line:asn.getAsnLineList()){
				if(line.getPoLine().getPurchaseOrderLineId().equals(lineDto.getPoLine().getPurchaseOrderLineId())){
					lineDto.getPoLine().setGlno(line.getPoLine().getGlno());
				}
			}
		 }
		}
		}
		
		if(!"9".equals(asn.getPo().getPstyp())){
			serviceLines = asnLineComponent.getASNLineByASNId(asn.getAdvanceShipmentNoticeId());
		}
		AdvanceShipmentNoticeDto asndto=asnComponent.getASN(asn.getAdvanceShipmentNoticeId());
		ServiceObject serviceList = new ServiceObject();
		
		if(!CommonUtil.isStringEmpty(asn.getWithHoldingTax())){
			String[] wht=asn.getWithHoldingTax().split("_");
			serviceList.setWithHoldingTaxType(wht[0]);
			serviceList.setWithHoldingTaxCode(wht[1]);
		}
		if(null!=asn.getMiroDescription()){
		serviceList.setMiroDescription(asn.getMiroDescription());
		}
		if(null!=asndto.getTcs()){
			serviceList.setTcs(String.valueOf(asndto.getTcs()));
		}else{
			serviceList.setTcs("0");
		}
		if(null!=asndto.getRoundOffAmount()){
			DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
			df.setMaximumFractionDigits(340);
			serviceList.setRoundoff1(String.valueOf(df.format(asndto.getRoundOffAmount())));
		}else{
			serviceList.setRoundoff1("0");
		}
		if(null!=asndto.getRoundOffValue()){
			serviceList.setRoundoff2(String.valueOf(asndto.getRoundOffValue()));
		}else{
			serviceList.setRoundoff2("0");
		}
		if(null!=asndto.getFreightCharges()){
			serviceList.setFreightAmount(String.valueOf(asndto.getFreightCharges()));
		}else{
			serviceList.setFreightAmount("0");
		}
		if(null!=asndto.getPackingCharges()){
			serviceList.setPackingFWRD(String.valueOf(asndto.getPackingCharges()));
		}else{
			serviceList.setPackingFWRD("0");
		}
		if(null!=asndto.getLoadingUnloadingCharges()){
			serviceList.setLoadingUNLD(String.valueOf(asndto.getLoadingUnloadingCharges()));
		}else{
			serviceList.setLoadingUNLD("0");
		}
		if(null!=asndto.getSgst()){
			serviceList.setSgst(String.valueOf(asndto.getSgst()));
		}else{
			serviceList.setSgst("0");
		}
		if(null!=asndto.getCgst()){
			serviceList.setCgst(String.valueOf(asndto.getCgst()));
		}else{
			serviceList.setCgst("0");
		}
		if(null!=asndto.getIgst()){
			serviceList.setIgst(String.valueOf(asndto.getIgst()));
		}else{
			serviceList.setIgst("0");
		}
		if("9".equals(asndto.getPo().getPstyp())){
			serviceList.setSsnVersion(sysConfig.getName());
		}
		List<YSERVICE> yserviceList = getYSERVICEListFromServiceLineList(serviceLines, asndto, asndto.getPo(),asn);
		serviceList.setServices(yserviceList);
		/*if(asndto.getPo().getDocumentType().equals("ZNPO")){*/
		if("9".equals(asndto.getPo().getPstyp())){
//			String json = CommonUtil.getObjectJson(serviceList);
//			CustomResponseDto resp=new CustomResponseDto();
//			Errors errors = new Errors();
//			resp=asnLineComponent.sendServiceApprovedDataToSAP(json,param,asn,errors);
//			if (errors.getErrorCount() > 0) {
//				resp.setSuccess(false);
//				resp.setMessage(errors.getErrorString());
//				return resp;
//
//			}else {
//			   return resp;
//			}
		return mediaService.writeByteArrayTo(CommonUtil.getObjectByteArray(serviceList)
				, AppBaseConstant.FTP_SES_TO_PROCESS_PATH, asndto.getServiceSheetNo()+".txt");
		}
		else{
			return mediaService.writeByteArrayTo(CommonUtil.getObjectByteArray(serviceList)
					, AppBaseConstant.FTP_MPO_TO_PROCESS_PATH, asndto.getAdvanceShipmentNoticeNo()+".txt");
			
			
				
		}
		//return resp;
	//	return ;
	//	return null;
	}
	
	@RequestMapping(value="/serviceSheetForSAP/{asnId}" , method = RequestMethod.GET)
	 public @ResponseBody String serviceSheetForSAP(@PathVariable("asnId") Long asnId) throws Exception {
		AdvanceShipmentNoticeDto asn = asnService.findDto("getAsnAndPartnerByASNId",
				AbstractContextServiceImpl.getParamMap("asnId", asnId));
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("value", AppBaseConstant.SSNVERSION);
		SystemConfiguratorDto sysConfig=sysConfigService.findDto("getSysConfiguratorByType",params);
		List<AdvanceShipmentNoticeLineDto> serviceLines=null;
		if("9".equals(asn.getPo().getPstyp())){
			serviceLines = asnLineComponent.getServiceLineByASNId(asn.getAdvanceShipmentNoticeId());
		}
		if(!"2".equals(sysConfig.getName())){
		if("9".equals(asn.getPo().getPstyp())){
		 for(AdvanceShipmentNoticeLineDto lineDto: serviceLines){
			for(AdvanceShipmentNoticeLineDto line:asn.getAsnLineList()){
				if(line.getPoLine().getPurchaseOrderLineId().equals(lineDto.getPoLine().getPurchaseOrderLineId())){
					lineDto.getPoLine().setGlno(line.getPoLine().getGlno());
				}
			}
		 }
		}
		}
		
		if(!"9".equals(asn.getPo().getPstyp())){
			serviceLines = asnLineComponent.getASNLineByASNId(asn.getAdvanceShipmentNoticeId());
		}
		AdvanceShipmentNoticeDto asndto=asnComponent.getASN(asn.getAdvanceShipmentNoticeId());
		ServiceObject serviceList = new ServiceObject();
		
		if(!CommonUtil.isStringEmpty(asn.getWithHoldingTax())){
			String[] wht=asn.getWithHoldingTax().split("_");
			serviceList.setWithHoldingTaxType(wht[0]);
			serviceList.setWithHoldingTaxCode(wht[1]);
		}
		if(null!=asn.getMiroDescription()){
		serviceList.setMiroDescription(asn.getMiroDescription());
		}
		if(null!=asndto.getTcs()){
			serviceList.setTcs(String.valueOf(asndto.getTcs()));
		}else{
			serviceList.setTcs("0");
		}
		if(null!=asndto.getRoundOffAmount()){
			DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
			df.setMaximumFractionDigits(340);
			serviceList.setRoundoff1(String.valueOf(df.format(asndto.getRoundOffAmount())));
		}else{
			serviceList.setRoundoff1("0");
		}
		if(null!=asndto.getRoundOffValue()){
			serviceList.setRoundoff2(String.valueOf(asndto.getRoundOffValue()));
		}else{
			serviceList.setRoundoff2("0");
		}
		if(null!=asndto.getFreightCharges()){
			serviceList.setFreightAmount(String.valueOf(asndto.getFreightCharges()));
		}else{
			serviceList.setFreightAmount("0");
		}
		if(null!=asndto.getPackingCharges()){
			serviceList.setPackingFWRD(String.valueOf(asndto.getPackingCharges()));
		}else{
			serviceList.setPackingFWRD("0");
		}
		if(null!=asndto.getLoadingUnloadingCharges()){
			serviceList.setLoadingUNLD(String.valueOf(asndto.getLoadingUnloadingCharges()));
		}else{
			serviceList.setLoadingUNLD("0");
		}
		if(null!=asndto.getSgst()){
			serviceList.setSgst(String.valueOf(asndto.getSgst()));
		}else{
			serviceList.setSgst("0");
		}
		if(null!=asndto.getCgst()){
			serviceList.setCgst(String.valueOf(asndto.getCgst()));
		}else{
			serviceList.setCgst("0");
		}
		if(null!=asndto.getIgst()){
			serviceList.setIgst(String.valueOf(asndto.getIgst()));
		}else{
			serviceList.setIgst("0");
		}
		if("9".equals(asndto.getPo().getPstyp())){
			serviceList.setSsnVersion(sysConfig.getName());
		}
		List<YSERVICE> yserviceList = getYSERVICEListFromServiceLineList(serviceLines, asndto, asndto.getPo(),asn);
		serviceList.setServices(yserviceList);
		/*if(asndto.getPo().getDocumentType().equals("ZNPO")){*/
//		if("9".equals(asndto.getPo().getPstyp())){
//		return serviceList;
//		}else{
//			return serviceList;
//			
//			
//				
//		}
		if("9".equals(asndto.getPo().getPstyp())){
		try {
			String json = CommonUtil.getObjectJson(serviceList);
	
			return json;
		} catch (Exception e) {
			throw e;
		}
		}
		return null;
		
		
	}
	
	private List<YSERVICE> getYSERVICEListFromServiceLineList(List<AdvanceShipmentNoticeLineDto> serviceLines
			,AdvanceShipmentNoticeDto asn, PurchaseOrderDto po, AdvanceShipmentNoticeDto oldASN){
		List<YSERVICE> yserviceList = new ArrayList<>();
		/*if(asn.getPo().getDocumentType().equals("ZRPO") || asn.getPo().getDocumentType().equals("ZLCO")){*/
		if(!"9".equals(asn.getPo().getPstyp())){
			YSERVICE serviceLine = getYSERVICEFromServiceLineForMaterialPo(serviceLines, asn, po);
			yserviceList.add(serviceLine);
		}
		for(AdvanceShipmentNoticeLineDto service : serviceLines){
			if(service.getDeliveryQuantity()>0){
				YSERVICE serviceLine = getYSERVICEFromServiceLine(service, asn, po,oldASN);
				yserviceList.add(serviceLine);
			}
		}
		return yserviceList;
	}
	
	private YSERVICE getYSERVICEFromServiceLine(AdvanceShipmentNoticeLineDto serviceLine,
			AdvanceShipmentNoticeDto asn,PurchaseOrderDto po, AdvanceShipmentNoticeDto oldASN){
		YSERVICE service = new YSERVICE();
		if(serviceLine.getParentASNLine()!=null){
		service.setPOLINELITEM(serviceLine.getParentASNLine().getPoLine().getLineItemNumber());
		}else{
		service.setPOLINELITEM(serviceLine.getPoLine().getLineItemNumber());
		}
		service.setPONO(po.getPurchaseOrderNumber());
		service.setSERVICECODE(serviceLine.getPoLine().getCode());
		service.setSERVICELINEITEM(serviceLine.getPoLine().getLineItemNumber());
		/*service.setWORKDONE(new BigDecimal(Math.round(serviceLine.getDeliveryQuantity()*100.0)/100.0));*/
		service.setWORKDONE((new BigDecimal(serviceLine.getDeliveryQuantity())));
		service.setGlno(serviceLine.getPoLine().getGlno());
		service.setInvoiceDate(DateUtil.getDateString(oldASN.getInvoiceDate(), DateUtil.DEFAULT_DATE_FORMAT));
		service.setInvoiceNo(oldASN.getInvoiceNo());
		DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
		df.setMaximumFractionDigits(340);
		service.setInvoiceAmount(String.valueOf(df.format(asn.getInvoiceAmount())));
//		service.setPostingdate(oldASN.getIssueDate());
		service.setPostingdate(DateUtil.getDateString(oldASN.getServicePostingDate(), DateUtil.DEFAULT_DATE_FORMAT));
		service.setCostCenter(serviceLine.getCostCenter());
		service.setSsnCreationDate(DateUtil.getDateString(asn.getCreated(), DateUtil.DEFAULT_DATE_FORMAT));
		service.setSsnCreationEmpCode(asn.getCreatedBy().getUserName());
		//service.setSsnApprovalEmpCode(contextService.getUser().getUserName());
		service.setServiceLocation(oldASN.getServiceLocation());
		service.setServiceFromDate(DateUtil.getDateString(oldASN.getServiceFromDate(), DateUtil.DEFAULT_DATE_FORMAT));
		service.setServiceToDate(DateUtil.getDateString(oldASN.getServiceToDate(), DateUtil.DEFAULT_DATE_FORMAT));
		service.setSsnNo(oldASN.getServiceSheetNo());
		if(serviceLine.getAsnLineCostCenter()!=null){
			List<YSERVICECOSTCENTER> yCostCenterList = new ArrayList<>();
			yCostCenterList=getYSERVICECOSTCENTER(serviceLine);
			service.setLinecostcenter(yCostCenterList);
		}
		return service;
	}
	private YSERVICE getYSERVICEFromServiceLineForMaterialPo(List<AdvanceShipmentNoticeLineDto> serviceLines,
			AdvanceShipmentNoticeDto asn,PurchaseOrderDto po){
		YSERVICE service = new YSERVICE();
		service.setPONO(po.getPurchaseOrderNumber());
		return service;
	}
	private List<YSERVICECOSTCENTER> getYSERVICECOSTCENTER(AdvanceShipmentNoticeLineDto serviceLines){
		List<YSERVICECOSTCENTER> yCostCenterList = new ArrayList<>();
		for(ASNLineCostCenterDto dto:serviceLines.getAsnLineCostCenter()){
			YSERVICECOSTCENTER yCostCenter = new YSERVICECOSTCENTER();
			yCostCenter.setCostcenter(dto.getCostCenter());
			yCostCenter.setQty(dto.getQuantity());
			//yCostCenter.setFundcenter(dto.getFundCenter());
			yCostCenterList.add(yCostCenter);
		}
		
		return yCostCenterList;
		
	}
	
}

