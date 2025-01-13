package com.novelerp.alkyl.service.impl;

import java.util.ArrayList;
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

import com.novelerp.alkyl.component.POLineComponent;
import com.novelerp.alkyl.dao.ASNLineCostCenterDao;
import com.novelerp.alkyl.dao.AdvanceShipmentNoticeLineDao;
import com.novelerp.alkyl.dto.ASNLineCostCenterDto;
import com.novelerp.alkyl.dto.ASNReadDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.PurchaseOrderLineDto;
import com.novelerp.alkyl.dto.SSNReadDto;
import com.novelerp.alkyl.entity.AdvanceShipmentNoticeLine;
import com.novelerp.alkyl.service.ASNLIneCostCenterService;
import com.novelerp.alkyl.service.AdvanceShipmentNoticeLineService;
import com.novelerp.alkyl.service.AdvanceShipmentNoticeService;
import com.novelerp.alkyl.service.PurchaseOrderLineService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.ResponseDto;
import com.sap.document.sap.rfc.grn.ZmmGrnPortalClient;

@Service
public class AdvanceShipmentNoticeLineServiceImpl extends AbstractContextServiceImpl<AdvanceShipmentNoticeLine, AdvanceShipmentNoticeLineDto> implements AdvanceShipmentNoticeLineService {

	@Autowired
	private AdvanceShipmentNoticeLineDao advanceshipmentnoticeline;
	
//	@Autowired
//	private ASNLineComponent asnLineComponent;
	
	@Autowired
	private AdvanceShipmentNoticeService asnService;
	
	@Autowired
	private PurchaseOrderLineService poLineService;
	
	@Autowired
	private POLineComponent poLineComponent;
	
	@Autowired
	private ZmmGrnPortalClient zmmGrnPortalClient;
	
	@Autowired
	private ASNLIneCostCenterService asnLIneCostCenterService;
	
	@Autowired
	private ASNLineCostCenterDao asnLineCostCenterDao;
	
	@PostConstruct
	public void init(){
		
	 super.init(AdvanceShipmentNoticeLineServiceImpl.class, advanceshipmentnoticeline, AdvanceShipmentNoticeLine.class, AdvanceShipmentNoticeLineDto.class);
	setByPassProxy(true);
	}
	
	@Autowired
	@Qualifier("jwtUserContext")
	private ContextService contextService;
	
	@Autowired
	private AdvanceShipmentNoticeLineDao advanceShipmentNoticeLineDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public AdvanceShipmentNoticeDto submitASN(AdvanceShipmentNoticeDto asn){
		asn = asnService.save(asn);
		List<AdvanceShipmentNoticeLineDto> asnLines=asn.getAsnLineList(); //asnLineComponent.getASNLineByASNId(asn.getAdvanceShipmentNoticeId());
		for(AdvanceShipmentNoticeLineDto asnLine : asnLines){
			if (null==asnLine.getPoLine()) {
				throw new RuntimeException("Error occurred while submitting ASN");
			}
			PurchaseOrderLineDto poLine=poLineComponent.getPOLineByPOLineId(asnLine.getPoLine().getPurchaseOrderLineId());
			if (null==poLine) {
				throw new RuntimeException("Error occurred while submitting ASN");
			}
//			if(poLine.getAsnQuantity()+asnLine.getDeliveryQuantity()>poLine.getPoQuantity()){
//				throw new RuntimeException("ASN Quantity cannot exceed PO Quantity");
//			}
			poLineService.updateByJpql(AbstractContextServiceImpl.getParamMap("asnQuantity", poLine.getAsnQuantity()+asnLine.getDeliveryQuantity()), 
					AbstractContextServiceImpl.getParamMap("purchaseOrderLineId", poLine.getPurchaseOrderLineId()));
		}
		int result = asnService.updateByJpql(AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.ASN_STATUS_IN_TRANSIT)
				, AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asn.getAdvanceShipmentNoticeId()));
		if (result<=0) {
			throw new RuntimeException("Error occurred while submitting ASN");
		}
		return asn;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<AdvanceShipmentNoticeLineDto> save(List<AdvanceShipmentNoticeLineDto> asnLines,AdvanceShipmentNoticeDto asn){
		if(CommonUtil.isCollectionEmpty(asnLines)){
			return asnLines;
		}
		
		List<AdvanceShipmentNoticeLineDto> asnLineList = new ArrayList<>();
		for(AdvanceShipmentNoticeLineDto asnLine : asnLines){
			if(asnLine!=null){
				asnLine.setAdvanceshipmentnotice(asn);
				List<AdvanceShipmentNoticeLineDto> serviceLines = asnLine.getServiceLineList();
				asnLine.setServiceLineList(null);
				if(asnLine.getAdvanceShipmentNoticeLineId()==null){
					asnLine = super.save(asnLine);
				}else{
					asnLine = super.updateDto(asnLine);
				}
				serviceLines = saveServiceLines(serviceLines,asnLine,asn);
				asnLine.setServiceLineList(serviceLines);
				asnLineList.add(asnLine);
			}
		}
		
		return asnLineList;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<AdvanceShipmentNoticeLineDto> savelist(List<AdvanceShipmentNoticeLineDto> asnLines,AdvanceShipmentNoticeDto asn){
		if(CommonUtil.isCollectionEmpty(asnLines)){
			return asnLines;
		}
		
		List<AdvanceShipmentNoticeLineDto> asnLineList = new ArrayList<>();
		for(AdvanceShipmentNoticeLineDto asnLine : asnLines){
			if(asnLine!=null){
				asnLine.setAdvanceshipmentnotice(asn);
				
		
				if(null==asnLine.getAdvanceShipmentNoticeLineId()){
					asnLine = super.save(asnLine);
				}
				else{
					Map<String, Object> parameter = new HashMap<>();
					parameter.put("deliveryQuantity", asnLine.getDeliveryQuantity());
					parameter.put("rejectedQuantity", asnLine.getRejectedQuantity());
					parameter.put("confirmQuantity", asnLine.getConfirmQuantity());
					int result = updateByJpql(parameter, AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeLineId", asnLine.getAdvanceShipmentNoticeLineId()));

					//asnLine = super.updateDto(asnLine);
				}
				asnLineList.add(asnLine);
			    }				
			}
		
		
		return asnLineList;
	}
	
//	@Override
//	@Transactional(propagation=Propagation.REQUIRED)
//	public boolean unloadASN105(List<AdvanceShipmentNoticeLineDto> asnLines,AdvanceShipmentNoticeDto asn) throws Exception{
//		if(CommonUtil.isCollectionEmpty(asnLines)){
//			return false;
//		}
//		for(AdvanceShipmentNoticeLineDto asnLine : asnLines){
//			asnLine.setAdvanceshipmentnotice(asn);
//			if(asnLine!=null){
//				Map<String, Object> propertyValueMap = new HashMap<>();
//				propertyValueMap.put("rejectedQuantity", asnLine.getRejectedQuantity());
//				propertyValueMap.put("shortageQuantity", asnLine.getShortageQuantity());
//				propertyValueMap.put("confirmQuantity", asnLine.getConfirmQuantity());
//				propertyValueMap.put("storageLocation", asnLine.getStorageLocation());
//				propertyValueMap.put("valueType", asnLine.getValueType());
//				int result = updateByJpql(propertyValueMap, 
//						AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeLineId", asnLine.getAdvanceShipmentNoticeLineId()));
//				if(result>0){
//					if(asnLine.getPoLine()!=null){
//						PurchaseOrderLineDto poLine=poLineComponent.getPOLineByPOLineId(asnLine.getPoLine().getPurchaseOrderLineId());
//						poLineService.updateByJpql(AbstractContextServiceImpl.getParamMap("grnQuantity", poLine.getGrnQuantity()+asnLine.getConfirmQuantity()), 
//								AbstractContextServiceImpl.getParamMap("purchaseOrderLineId", poLine.getPurchaseOrderLineId()));
//					}
//					
//				}
//				
//			}
//			if(asnLine.getAsnLineCostCenter()!=null){
//				for(ASNLineCostCenterDto dto:asnLine.getAsnLineCostCenter()){
//					dto.setCode(AppBaseConstant.ASN_STORAGE_LOCATION);
//					dto.setAsnLine(asnLine);
//					asnLIneCostCenterService.save(dto);
//				}
//			}
//		}
//		boolean result= asnService.updateNewASNGateOutStatus(asn.getAdvanceShipmentNoticeId());
//		if(!result){
//			Map<String , Object> param = null;
//			Map<String , Object> where = null;
//			
//		//	param = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.ASN_STATUS_105);
//		//	param = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.ASN_STATUS_105_IN_Progress);
//			param.put("isGrn","Y" );
//			param.put("grnDate", new Date());
////			param.put("grnPostedby", contextService.getUser());
//			where = AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId",asn.getAdvanceShipmentNoticeId() );
//			result= asnService.updateByJpql(param,where)>0;
//		}
//		
//		zmmGrnPortalClient.setASNForMoveType105(asnLines);
//	//	zmmGrnPortalClient.moveType105ForSAP(asnLines);
//		
//		return result;
//	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean unloadASN105(List<AdvanceShipmentNoticeLineDto> asnLines,AdvanceShipmentNoticeDto asn) throws Exception{
		if(CommonUtil.isCollectionEmpty(asnLines)){
			return false;
		}
		for(AdvanceShipmentNoticeLineDto asnLine : asnLines){
			asnLine.setAdvanceshipmentnotice(asn);
			if(asnLine!=null){
				Map<String, Object> propertyValueMap = new HashMap<>();
				propertyValueMap.put("rejectedQuantity", asnLine.getRejectedQuantity());
				propertyValueMap.put("shortageQuantity", asnLine.getShortageQuantity());
				propertyValueMap.put("confirmQuantity", asnLine.getConfirmQuantity());
				propertyValueMap.put("storageLocation", asnLine.getStorageLocation());
				propertyValueMap.put("valueType", asnLine.getValueType());
				
				int result = updateByJpql(propertyValueMap, 
						AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeLineId", asnLine.getAdvanceShipmentNoticeLineId()));
				if(result>0){
					if(asnLine.getPoLine()!=null){
						PurchaseOrderLineDto poLine=poLineComponent.getPOLineByPOLineId(asnLine.getPoLine().getPurchaseOrderLineId());
						poLineService.updateByJpql(AbstractContextServiceImpl.getParamMap("grnQuantity", poLine.getGrnQuantity()+asnLine.getConfirmQuantity()), 
								AbstractContextServiceImpl.getParamMap("purchaseOrderLineId", poLine.getPurchaseOrderLineId()));
					}
					
				}
				
			}
			
			asnLineCostCenterDao.DeleteasnLineCostCenter(asnLine.getAdvanceShipmentNoticeLineId());
			
			if(asnLine.getAsnLineCostCenter()!=null){
				for(ASNLineCostCenterDto dto:asnLine.getAsnLineCostCenter()){
					dto.setCode(AppBaseConstant.ASN_STORAGE_LOCATION);
					dto.setAsnLine(asnLine);
					
					asnLIneCostCenterService.save(dto);
				//	 asnLIneCostCenterService.saveCostCenter(dto);
				}
				
			}
		}
		boolean result= asnService.updateNewASNGateOutStatus(asn.getAdvanceShipmentNoticeId());
//		if(!result){
//			Map<String , Object> param = null;
//			Map<String , Object> where = null;
//			
//		//	param = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.ASN_STATUS_105);
//		//	param = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.ASN_STATUS_105_IN_Progress);
//			param = AbstractContextServiceImpl.getParamMap("isGrn","Y");
//		//	param.put("isGrn","Y" );
//			param.put("grnDate", new Date());
////			param.put("grnPostedby", contextService.getUser());
//			where = AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId",asn.getAdvanceShipmentNoticeId() );
//			result= asnService.updateByJpql(param,where)>0;
//		}
		
	//	zmmGrnPortalClient.setASNForMoveType105(asnLines);
		
		return result;
	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean unloadASN101(List<AdvanceShipmentNoticeLineDto> asnLines,AdvanceShipmentNoticeDto asn) throws Exception{
		if(CommonUtil.isCollectionEmpty(asnLines)){
			return false;
		}
		for(AdvanceShipmentNoticeLineDto asnLine : asnLines){
			asnLine.setAdvanceshipmentnotice(asn);
			if(asnLine!=null){
				Map<String, Object> propertyValueMap = new HashMap<>();
				propertyValueMap.put("rejectedQuantity", asnLine.getRejectedQuantity());
				propertyValueMap.put("shortageQuantity", asnLine.getShortageQuantity());
				propertyValueMap.put("confirmQuantity", asnLine.getConfirmQuantity());
				propertyValueMap.put("storageLocation", asnLine.getStorageLocation());
				int result = updateByJpql(propertyValueMap, 
						AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeLineId", asnLine.getAdvanceShipmentNoticeLineId()));
				if(result>0){
					if(asnLine.getPoLine()!=null){
						PurchaseOrderLineDto poLine=poLineComponent.getPOLineByPOLineId(asnLine.getPoLine().getPurchaseOrderLineId());
						poLineService.updateByJpql(AbstractContextServiceImpl.getParamMap("grnQuantity", poLine.getGrnQuantity()+asnLine.getConfirmQuantity()), 
								AbstractContextServiceImpl.getParamMap("purchaseOrderLineId", poLine.getPurchaseOrderLineId()));
					}
					
				}
				
			}
			if(asnLine.getAsnLineCostCenter()!=null){
				for(ASNLineCostCenterDto dto:asnLine.getAsnLineCostCenter()){
					dto.setCode(AppBaseConstant.ASN_STORAGE_LOCATION);
					dto.setAsnLine(asnLine);
					asnLIneCostCenterService.save(dto);
				}
			}
		}
		boolean result= asnService.updateNewASNGateOutStatus(asn.getAdvanceShipmentNoticeId());
		if(!result){
			Map<String , Object> param = null;
			Map<String , Object> where = null;
			
			param = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.ASN_STATUS_101);
		//	param = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.ASN_STATUS_105_IN_Progress);
//			param.put("isGrn","Y" );
//			param.put("grnDate", new Date());
//			param.put("grnPostedby", contextService.getUser());
			where = AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId",asn.getAdvanceShipmentNoticeId() );
			result= asnService.updateByJpql(param,where)>0;
		}
		
		zmmGrnPortalClient.setASNForMoveType101(asnLines);
		
		return result;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean unloadASN(List<AdvanceShipmentNoticeLineDto> asnLines,AdvanceShipmentNoticeDto asn){
		if(CommonUtil.isCollectionEmpty(asnLines)){
			return false;
		}
//		for(AdvanceShipmentNoticeLineDto asnLine : asnLines){
//			asnLine.setAdvanceshipmentnotice(asn);
//			if(asnLine!=null){
//				Map<String, Object> propertyValueMap = new HashMap<>();
//				propertyValueMap.put("rejectedQuantity", asnLine.getRejectedQuantity());
//				propertyValueMap.put("shortageQuantity", asnLine.getShortageQuantity());
//				propertyValueMap.put("confirmQuantity", asnLine.getConfirmQuantity());
//				updateByJpql(propertyValueMap, 
//						AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeLineId", asnLine.getAdvanceShipmentNoticeLineId()));
//			}
//		}
		
		Map<String , Object> param = AbstractContextServiceImpl.getParamMap("isUnload", "Y");
		param.put("unloadDate", new Date());
		param.put("status", AppBaseConstant.ASN_STATUS_UNLOAD);
		
		int result = asnService.updateByJpql(param, AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asn.getAdvanceShipmentNoticeId()));
		
		//zmmGrnPortalClient.setASNForMoveType105(asnLines);
		
		return result > 0;
	}
	
	
	private List<AdvanceShipmentNoticeLineDto> saveServiceLines(List<AdvanceShipmentNoticeLineDto> serviceLineList, 
			AdvanceShipmentNoticeLineDto parentAsnLine, AdvanceShipmentNoticeDto asn){
		if(CommonUtil.isCollectionEmpty(serviceLineList)){
			return serviceLineList;
		}
		
		
		
		List<AdvanceShipmentNoticeLineDto> serviceLines = new ArrayList<>();
		List<ASNLineCostCenterDto> asnCostCenter = new ArrayList<>();
		for(AdvanceShipmentNoticeLineDto serviceLine : serviceLineList){
			
			
			if(null!=serviceLine){
			asnCostCenter=serviceLine.getAsnLineCostCenter();
			serviceLine.setAsnLineCostCenter(null);
			}else{
				asnCostCenter=null;
			}
					
			
			if(serviceLine!=null){
//				if(serviceLine.getDeliveryQuantity().equals(0.0)|| serviceLine.getDeliveryQuantity()==null || serviceLine.getDeliveryQuantity().toString().equals("")) {
//					throw new RuntimeException("Delivery Quantity Cannot be bull");
//				}
				
				serviceLine.setParentASNLine(parentAsnLine);
				serviceLine.setAdvanceshipmentnotice(asn);
				if(serviceLine.getAdvanceShipmentNoticeLineId()==null){
					serviceLine = super.save(serviceLine);
				}else{
					serviceLine = super.updateDto(serviceLine);
				}
				serviceLines.add(serviceLine);
			}
			
			if(serviceLine!=null){
			asnLineCostCenterDao.DeleteasnLineCostCenter(serviceLine.getAdvanceShipmentNoticeLineId());
			}
			
			if(asnCostCenter!=null){
				for(ASNLineCostCenterDto dto:asnCostCenter){
					if(dto.getAsnLineCostCenterId()==null){
					dto.setCode(AppBaseConstant.ASN_COST_CENTER);
					dto.setAsnLine(serviceLine);
					asnLIneCostCenterService.save(dto);
					//asnLIneCostCenterService.saveCostCenter(dto);
					}else{
						Map<String, Object> propertyValueMap = new HashMap<>();
						propertyValueMap.put("costCenter", dto.getCostCenter());
						//propertyValueMap.put("fundCenter", dto.getFundCenter());
						propertyValueMap.put("quantity", dto.getQuantity());
						AdvanceShipmentNoticeLine dummy= new AdvanceShipmentNoticeLine();
						dummy.setAdvanceShipmentNoticeLineId(serviceLine.getAdvanceShipmentNoticeLineId());
						propertyValueMap.put("asnLine", dummy);
						asnLIneCostCenterService.updateByJpql(propertyValueMap, 
									AbstractContextServiceImpl.getParamMap("asnLineCostCenterId", dto.getAsnLineCostCenterId()));
					
					}
				}
			}
		}
		
		return serviceLines;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public AdvanceShipmentNoticeDto submitServiceSheet(AdvanceShipmentNoticeDto asn){

		asn = asnService.save(asn);
		//List<AdvanceShipmentNoticeLineDto> asnLines=asnLineComponent.getASNLineByASNId(asn.getAdvanceShipmentNoticeId());
		for(AdvanceShipmentNoticeLineDto asnLine : asn.getAsnLineList()){

			
			if (null==asnLine.getPoLine()) {
				throw new RuntimeException("Error occurred while submitting ASN");
			}
			PurchaseOrderLineDto poLine=poLineComponent.getPOLineByPOLineId(asnLine.getPoLine().getPurchaseOrderLineId());
			if (null==poLine) {
				throw new RuntimeException("Error occurred while submitting ASN");
			}
//			if(poLine.getAsnQuantity()+asnLine.getDeliveryQuantity()>poLine.getPoQuantity()){
//				throw new RuntimeException("ASN Quantity cannot exceed PO Quantity");
//			}
//			poLineService.updateByJpql(AbstractContextServiceImpl.getParamMap("asnQuantity", poLine.getAsnQuantity()+asnLine.getDeliveryQuantity()), 
//					AbstractContextServiceImpl.getParamMap("purchaseOrderLineId", poLine.getPurchaseOrderLineId()));
		}
		int result = asnService.updateByJpql(AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.SERVICE_SHEET_STATUS_IN_PROGRESS)
				, AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asn.getAdvanceShipmentNoticeId()));
		if (result<=0) {
			throw new RuntimeException("Error occurred while submitting ASN");
		}
		return asn;
	} 
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public AdvanceShipmentNoticeDto submitServiceEntry(AdvanceShipmentNoticeDto asn){
		asn = asnService.save(asn);
		//List<AdvanceShipmentNoticeLineDto> asnLines=asnLineComponent.getASNLineByASNId(asn.getAdvanceShipmentNoticeId());
		for(AdvanceShipmentNoticeLineDto asnLine : asn.getAsnLineList()){
			if (null==asnLine.getPoLine()) {
				throw new RuntimeException("Error occurred while submitting ASN");
			}
			PurchaseOrderLineDto poLine=poLineComponent.getPOLineByPOLineId(asnLine.getPoLine().getPurchaseOrderLineId());
			if (null==poLine) {
				throw new RuntimeException("Error occurred while submitting ASN");
			}
//			if(poLine.getAsnQuantity()+asnLine.getDeliveryQuantity()>poLine.getPoQuantity()){
//				throw new RuntimeException("ASN Quantity cannot exceed PO Quantity");
//			}
			poLineService.updateByJpql(AbstractContextServiceImpl.getParamMap("asnQuantity", poLine.getAsnQuantity()+asnLine.getDeliveryQuantity()), 
					AbstractContextServiceImpl.getParamMap("purchaseOrderLineId", poLine.getPurchaseOrderLineId()));
		}
		int result = asnService.updateByJpql(AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.SERVICE_ENTRY_SHEET_STATUS_IN_PROGRESS)
				, AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asn.getAdvanceShipmentNoticeId()));
		if (result<=0) {
			throw new RuntimeException("Error occurred while submitting ASN");
		}
		return asn;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean qcPassASN(List<AdvanceShipmentNoticeLineDto> asnLineList, AdvanceShipmentNoticeDto asn) {
		if(CommonUtil.isCollectionEmpty(asnLineList)){
			return false;
		}
		for(AdvanceShipmentNoticeLineDto asnLine : asnLineList){
			asnLine.setAdvanceshipmentnotice(asn);
			if(asnLine!=null){
				Map<String, Object> propertyValueMap = new HashMap<>();
				propertyValueMap.put("rejectedQuantity", asnLine.getRejectedQuantity());
				updateByJpql(propertyValueMap, 
						AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeLineId", asnLine.getAdvanceShipmentNoticeLineId()));
			}
		}
		
		
		Map<String , Object> param = AbstractContextServiceImpl.getParamMap("isQCPassed", "Y");
		param.put("qcPassedDate", new Date());
		param.put("status", AppBaseConstant.ASN_STATUS_QC_PASSED);
		
		int result = asnService.updateByJpql(param
				, AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asn.getAdvanceShipmentNoticeId()));
		
		//zmmGrnPortalClient.setASNForMoveType105(asnLines);
		
		return result > 0;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean qcFailASN(List<AdvanceShipmentNoticeLineDto> lineDtos, AdvanceShipmentNoticeDto asn,String remark) throws Exception {
		
		Map<String , Object> param = AbstractContextServiceImpl.getParamMap("status", AppBaseConstant.ASN_STATUS_QC_FAILED);
		param.put("rejectReason", remark);
		int result = asnService.updateByJpql(param, AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeId", asn.getAdvanceShipmentNoticeId()));
		zmmGrnPortalClient.setASNForMoveType104(lineDtos);
		return result>0;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateCostCenter(List<AdvanceShipmentNoticeLineDto> asnLineList) {
		try{
		for(AdvanceShipmentNoticeLineDto asnLine : asnLineList){
			if(null!=asnLine.getServiceLineList()){
			for(AdvanceShipmentNoticeLineDto serviceLine:asnLine.getServiceLineList()){
				if(null!=serviceLine){
			if(serviceLine.getAsnLineCostCenter()!=null){
			for(ASNLineCostCenterDto costCenterDto :serviceLine.getAsnLineCostCenter()){
				
				costCenterDto.setCode(AppBaseConstant.ASN_COST_CENTER);
				if(costCenterDto.getAsnLineCostCenterId()==null){
					costCenterDto.setAsnLine(serviceLine);
				asnLIneCostCenterService.save(costCenterDto);
				}else{
					Map<String, Object> propertyValueMap = new HashMap<>();
					propertyValueMap.put("costCenter", costCenterDto.getCostCenter());
					propertyValueMap.put("quantity", costCenterDto.getQuantity());
					//propertyValueMap.put("fundCenter", costCenterDto.getFundCenter());
					asnLIneCostCenterService.updateByJpql(propertyValueMap, 
								AbstractContextServiceImpl.getParamMap("asnLineCostCenterId", costCenterDto.getAsnLineCostCenterId()));
				
				}
			}
			}
			/*Map<String, Object> propertyValueMap = new HashMap<>();
			propertyValueMap.put("costCenter", asnLine.getCostCenter());
			updateByJpql(propertyValueMap, 
						AbstractContextServiceImpl.getParamMap("advanceShipmentNoticeLineId", asnLine.getAdvanceShipmentNoticeLineId()));
			*/
				}
			}
			}
		}
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	
	
	@Override
	public List<AdvanceShipmentNoticeLineDto> getASNLineReportbyFilter(ASNReadDto dto) {
		Map<String, Object> params=getParameterMap(dto);
	//	System.out.println(dto.toString());
		//ASNReadDto getdto = getdto(dto);
		List<String> param = new ArrayList<>();
		
	//	List<String> param = new ArrayList<>();
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

		
		if(dto.getStatus().equals("Reported")) {
			param.add(AppBaseConstant.ASN_STATUS_REPORTED);
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
	
		
		String query=advanceShipmentNoticeLineDao.getASNReportLinelist(dto);
		List<AdvanceShipmentNoticeLineDto> asnLineList=findDtosByQuery(query, params);
		return asnLineList;
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
   		
   		if(dto.getVendorCode()!=null && dto.getVendorCodeTo()==null){
   			params.put("vendorCode",dto.getVendorCode());
   		}
   		
   		if(dto.getVendorCodeTo()!=null && dto.getVendorCode()==null){
   			params.put("vendorCodeTo",dto.getVendorCodeTo());
   		}
   		
   		if(dto.getVendorCode()!=null && dto.getVendorCodeTo()!=null){
			params.put("vendorCode", dto.getVendorCode());
			params.put("vendorCodeTo", dto.getVendorCodeTo());
   		}
   		
   		if(dto.getItemCodeFrom()!=null && dto.getItemCodeTo()==null){
   			params.put("itemCodeFrom",dto.getItemCodeFrom());
   		}
   		
   		if(dto.getItemCodeTo()!=null && dto.getItemCodeFrom()==null){
   			params.put("itemCodeTo",dto.getItemCodeTo());
   		}
   		
   		if(dto.getItemCodeFrom()!=null && dto.getItemCodeTo()!=null){
			params.put("itemCodeFrom", dto.getItemCodeFrom());
			params.put("itemCodeTo", dto.getItemCodeTo());
   		}
   		
   		if(dto.getStatus()!=null && !dto.getStatus().equals("")){
   			params.put("status",dto.getStatus());
   		}

//   		if(dto.getRequestedBy()!=null && !dto.getRequestedBy().equals("")){
//   			params.put("requestedBy",dto.getRequestedBy());
//   		}
   		
   		if(dto.getPlant()!=null && !dto.getPlant().equals("")){
   			params.put("plant",dto.getPlant());
   		}
   		
   		
   		if(dto.getGateInDateFrom()!=null && dto.getGateInDateTo()!=null){
   			
   			params.put("gateInDateFrom", dto.getStartgateInDateFrom());
   			dto.setGateInDateFrom(dto.getStartgateInDateFrom());
   			params.put("gateInDateTo", dto.getLastgateInDateTo());
   			dto.setGateInDateTo(dto.getLastgateInDateTo());
   		}
   		if(dto.getGateInDateFrom()!=null && dto.getGateInDateTo()==null){
   			params.put("gateInDateFrom", dto.getStartgateInDateFrom());
			params.put("gateInDateTo", dto.getLastgateInDateFrom());
			dto.setGateInDateFrom(dto.getStartgateInDateFrom());
			dto.setGateInDateTo(dto.getLastgateInDateFrom());
   		}
   		
   		if(dto.getGateInDateFrom()==null && dto.getGateInDateTo()!=null){
   			params.put("gateInDateFrom", dto.getStartgateInDateTo());
			params.put("gateInDateTo", dto.getLastgateInDateTo());
			dto.setGateInDateFrom(dto.getStartgateInDateTo());
			dto.setGateInDateTo(dto.getLastgateInDateTo());
   		}
   		
   		
		return params;
	}
	
	@Override
	public List<AdvanceShipmentNoticeLineDto> getASNLineReportwithoutPObyFilter(ASNReadDto dto) {
		Map<String, Object> params=getParameterMapWithoutPO(dto);
	//	System.out.println(dto.toString());
		//ASNReadDto getdto = getdto(dto);
		List<String> param = new ArrayList<>();
		
	//	List<String> param = new ArrayList<>();
		if(dto.getStatus().equals("Gate In")) {
			param.add(AppBaseConstant.ASN_STATUS_GATE_IN);
			params.put("status", param);
			
		}
		
		if(dto.getStatus().equals("Gate Out")) {
			param.add(AppBaseConstant.ASN_STATUS_GATE_OUT);
			params.put("status", param);
		}
		
		if(dto.getStatus().equals("GATE_OUT_WITHOUT_PO")) {
			param.add(AppBaseConstant.ASN_STATUS_GATE_OUT_WITHOUT_PO);
			params.put("status", param);
		}
		
		
		if(dto.getStatus().equals("CANCELED")) {
			param.add(AppBaseConstant.ASN_STATUS_CANCELED);
			params.put("status", param);
		}
		
		
		if(dto.getStatus().equals("Reported")) {
			param.add(AppBaseConstant.ASN_STATUS_REPORTED);
			params.put("status", param);
		}
		
		String query=advanceShipmentNoticeLineDao.getASNReportLinelistWithoutPO(dto);
		List<AdvanceShipmentNoticeLineDto> asnLineList=findDtosByQuery(query, params);
		return asnLineList;
	}
	private Map<String,Object> getParameterMapWithoutPO(ASNReadDto dto){
		System.out.println(dto.toString());
		Map<String, Object> params = new HashMap<String, Object>();
//		if(dto.getPoNoFrom()!=null && dto.getPoNoTo()!=null){
//			params.put("fromNo", dto.getPoNoFrom());
//			params.put("toNo", dto.getPoNoTo());
//   		}
//   		if(dto.getPoNoFrom()!=null && dto.getPoNoTo()==null){
//   			params.put("fromNo", dto.getPoNoFrom());
//   		}
//   		if(dto.getPoNoFrom()==null && dto.getPoNoTo()!=null){
//   			params.put("toNo", dto.getPoNoTo());
//   		}
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
   		
//   		if(dto.getVendorCode()!=null && dto.getVendorCodeTo()==null){
//   			params.put("vendorCode",dto.getVendorCode());
//   		}
//   		
//   		if(dto.getVendorCodeTo()!=null && dto.getVendorCode()==null){
//   			params.put("vendorCodeTo",dto.getVendorCodeTo());
//   		}
//   		
//   		if(dto.getVendorCode()!=null && dto.getVendorCodeTo()!=null){
//			params.put("vendorCode", dto.getVendorCode());
//			params.put("vendorCodeTo", dto.getVendorCodeTo());
//   		}
   		
//   		if(dto.getItemCodeFrom()!=null && dto.getItemCodeTo()==null){
//   			params.put("itemCodeFrom",dto.getItemCodeFrom());
//   		}
//   		
//   		if(dto.getItemCodeTo()!=null && dto.getItemCodeFrom()==null){
//   			params.put("itemCodeTo",dto.getItemCodeTo());
//   		}
//   		
//   		if(dto.getItemCodeFrom()!=null && dto.getItemCodeTo()!=null){
//			params.put("itemCodeFrom", dto.getItemCodeFrom());
//			params.put("itemCodeTo", dto.getItemCodeTo());
//   		}
   		
   		if(dto.getStatus()!=null && !dto.getStatus().equals("")){
   			params.put("status",dto.getStatus());
   		}

   		
   		if(dto.getPlant()!=null && !dto.getPlant().equals("")){
   			params.put("plant",dto.getPlant());
   		}
   		
   		
		return params;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
	public List<AdvanceShipmentNoticeLineDto> getSSNLineReportbyFilter(SSNReadDto dto) {
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
		
		String query=advanceShipmentNoticeLineDao.getSSNReportLinelist(dto);
		List<AdvanceShipmentNoticeLineDto> ssnList=findDtosByQuery(query, params);
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



}
