package com.novelerp.alkyl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dao.PurchaserOrderLineDao;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.dto.PurchaseOrderLineDto;
import com.novelerp.alkyl.entity.PurchaseOrderLine;
import com.novelerp.alkyl.service.PurchaseOrderLineService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;

@Service
public class PurchaseOrderLineServiceImpl extends AbstractContextServiceImpl<PurchaseOrderLine, PurchaseOrderLineDto> implements PurchaseOrderLineService{

	
	@Autowired
	private PurchaserOrderLineDao purchaseorderline;
	
	@Autowired
	private PurchaseOrderLineService poLineService;
	
	@Autowired
	private PurchaserOrderLineDao purchaseOrderLineDao;
	
	@PostConstruct
	public void init(){
		
		super.init(PurchaseOrderLineServiceImpl.class, purchaseorderline, PurchaseOrderLine.class, PurchaseOrderLineDto.class);
		setByPassProxy(true);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<PurchaseOrderLineDto> save(List<PurchaseOrderLineDto> poLineList,PurchaseOrderDto po, BPartnerDto partner){
		if(!CommonUtil.isCollectionEmpty(poLineList)){
			
//			if(!po.getPstyp().equals("9")) {
//			List<PurchaseOrderLineDto> oldAllData = poLineService.findDtos("getPOLineByPONumber",AbstractContextServiceImpl.getParamMap("poId", po.getPurchaseOrderId()));
//			
//			List<String> data=new ArrayList<>();
//			List<String> newdata=new ArrayList<>();
//			for(PurchaseOrderLineDto oldList:oldAllData) {
//				data.add(oldList.getLineItemNumber());
//			}
//					
//			for(PurchaseOrderLineDto newList:poLineList) {
//				newdata.add(newList.getLineItemNumber());
//			}
//			
//			List<String> deletedListinSAP= getDeletedLineNoList(data,newdata);
	//		setisActiveinpoLine(deletedListinSAP, po.getPurchaseOrderNumber(),po.getPstyp());
			
//			}
			
			for(PurchaseOrderLineDto poLine : poLineList){
				//List<PoLineConditionDto> poLineConditionList = poLine.getPoLineConditionList();
				List<PurchaseOrderLineDto> serviceList = poLine.getServiceList();
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("poNumber", po.getPurchaseOrderNumber());
				params.put("lineItemNumber", poLine.getLineItemNumber());
				PurchaseOrderLineDto old = findDto("getPOLineByPOandLineItemNumber", params);
				
					
				poLine.setPoLineConditionList(null);
				poLine.setServiceList(null);
				
				poLine.setPartner(partner);
				
				if(old!=null){
					poLine = getNewPOLineFromOld(old, poLine);
					poLine.setPurchaseOrder(po);					
					poLine = super.updateDto(poLine);
				
				}else{
					poLine.setPurchaseOrder(po);
					poLine = super.save(poLine);
				}
							
				//poLineConditionList=poLineConditionService.save(poLineConditionList,poLine);
				//poLine.setPoLineConditionList(poLineConditionList);
				
				serviceList=saveService(serviceList,po,partner,poLine);
				poLine.setServiceList(serviceList);
			}
			
		}
		return poLineList;
	}
	
	private PurchaseOrderLineDto getNewPOLineFromOld(PurchaseOrderLineDto oldDto,PurchaseOrderLineDto newDto){
		oldDto.setCode(newDto.getCode());
		oldDto.setControlCode(newDto.getControlCode());
		oldDto.setCurrency(newDto.getCurrency());
		oldDto.setDeliveryDate(newDto.getDeliveryDate());
		oldDto.setDeliveryQuantity(newDto.getDeliveryQuantity());
		oldDto.setDeliveryScheduleAnnual(newDto.getDeliveryScheduleAnnual());
		oldDto.setDeliveryStatus(newDto.getDeliveryStatus());
		oldDto.setDescription(newDto.getDescription());
		oldDto.setMaterial(newDto.getMaterial());
		oldDto.setName(newDto.getName());
		oldDto.setOverDeliveryTol(newDto.getOverDeliveryTol());
		oldDto.setPlant(newDto.getPlant());
		oldDto.setPoQuantity(newDto.getPoQuantity());
		oldDto.setRate(newDto.getRate());
		oldDto.setTrackingNmber(newDto.getTrackingNmber());
		oldDto.setUnderdeliveryTol(newDto.getUnderdeliveryTol());
		oldDto.setUom(newDto.getUom());
		oldDto.setGlno(newDto.getGlno());
		oldDto.setBalanceQuantity(newDto.getBalanceQuantity());
		oldDto.setDeleted(newDto.getDeleted());
		oldDto.setGrnQuantity(newDto.getGrnQuantity());

		if(("B".equalsIgnoreCase(newDto.getBlocked())) || ("D".equalsIgnoreCase(newDto.getDeleted()))){
			oldDto.setDeleted("X");
		}else {
			oldDto.setDeleted("");
		}
		
//		if("B".equalsIgnoreCase(newDto.getBlocked())){
//			oldDto.setDeleted("X");
//		}else {
//			oldDto.setDeleted(newDto.getBlocked());
//		}
//		
//		if("D".equalsIgnoreCase(newDto.getDeleted())){
//		        oldDto.setDeleted(newDto.getDeleted());		
//	     }
//		else {
//			oldDto.setDeleted(newDto.getDeleted());
//		}

		
//		if("X".equalsIgnoreCase(newDto.getDeleted())){
//			oldDto.setDeleted(newDto.getDeleted());
//			oldDto.setIsActive("N");
//		}
		return oldDto;
	}
	
	private List<String> getDeletedLineNoList(List<String> data,List<String> newdata){
	
		List<String> remainingNo=new ArrayList<>();
		for(String deletedItem:data) {
			
			if(newdata.contains(deletedItem)) {
				
			}else {
				remainingNo.add(deletedItem);
			}	
		}		
		return remainingNo;
}
	
	private PurchaseOrderLineDto setisActiveinpoLine(List<String> deletedList,String poNo, String pstyp){		
		
		for(String deletedLineNo:deletedList) {
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("poNumber", poNo);
			params.put("lineItemNumber", deletedLineNo);
			PurchaseOrderLineDto old = findDto("getPOLineByPOandLineItemNumber", params);	
			
			
			Map<String, Object> params1 = new HashMap<String, Object>();
			params1.put("isActive", "N");
			
			poLineService.updateByJpql(params1, AbstractContextServiceImpl.getParamMap("purchaseOrderLineId",
					old.getPurchaseOrderLineId()));
			
//			if("9".equals(pstyp)) {
//			
//			       updateisActiveforServiceLine(old.getPurchaseOrderLineId());
//			
//		}
			
//			Map<String, Object> serviceparams = new HashMap<String, Object>();
//			params.put("poLineId", old.getPurchaseOrderLineId());
		//	List<PurchaseOrderLineDto> Serviceold = (List<PurchaseOrderLineDto>) findDto("getServiceLineByPOLineId", AbstractContextServiceImpl.getParamMap("poLineId", old.getPurchaseOrderLineId()));	
			
	
			
		}
		return null;
		
		
		}
	
	
@Transactional(propagation=Propagation.REQUIRED)
private void updateisActiveforServiceLine(Long purchaseOrderLineId) {
	
	Map<String, Object> serviceparams = new HashMap<String, Object>();
	serviceparams.put("poLineId", purchaseOrderLineId);
	//PurchaseOrderLineDto Serviceold = findDto("getoldServiceLineByPOLineId", serviceparams);	
	
	List<PurchaseOrderLineDto> oldserviceLinepoData = poLineService.findDtos("getoldServiceLineByPOLineId",AbstractContextServiceImpl.getParamMap("poLineId", purchaseOrderLineId));

	for(PurchaseOrderLineDto old:oldserviceLinepoData) {
	if(old!=null) {
		Map<String, Object> serviceparams1 = new HashMap<String, Object>();
		serviceparams1.put("isActive", "N");
		
		poLineService.updateByJpql(serviceparams1, AbstractContextServiceImpl.getParamMap("purchaseOrderLineId",
				old.getPurchaseOrderLineId()));
	}
	}
	}

//		if("X".equalsIgnoreCase(newDto.getDeleted())){
//			oldDto.setDeleted(newDto.getDeleted());
//			oldDto.setIsActive("N");
//		}
	
	
	
	private PurchaseOrderLineDto getNewServiceLineFromOld(PurchaseOrderLineDto oldDto,PurchaseOrderLineDto newDto){
		oldDto.setCode(newDto.getCode());
		oldDto.setControlCode(newDto.getControlCode());
		oldDto.setCurrency(newDto.getCurrency());
		oldDto.setDeliveryDate(newDto.getDeliveryDate());
		oldDto.setDeliveryQuantity(newDto.getDeliveryQuantity());
		oldDto.setDeliveryScheduleAnnual(newDto.getDeliveryScheduleAnnual());
		oldDto.setDeliveryStatus(newDto.getDeliveryStatus());
		oldDto.setDescription(newDto.getDescription());
		oldDto.setMaterial(newDto.getMaterial());
		oldDto.setName(newDto.getName());
		oldDto.setOverDeliveryTol(newDto.getOverDeliveryTol());
		oldDto.setPlant(newDto.getPlant());
		oldDto.setPoQuantity(newDto.getPoQuantity());
		oldDto.setRate(newDto.getRate());
		oldDto.setTrackingNmber(newDto.getTrackingNmber());
		oldDto.setUnderdeliveryTol(newDto.getUnderdeliveryTol());
		oldDto.setUom(newDto.getUom());
		oldDto.setGlno(newDto.getGlno());
//		if("X".equalsIgnoreCase(newDto.getDeleted())){
//			oldDto.setDeleted(newDto.getDeleted());
//			oldDto.setIsActive("N");
//		}
		
		if(("B".equalsIgnoreCase(newDto.getBlocked())) || ("D".equalsIgnoreCase(newDto.getDeleted()))){
			oldDto.setDeleted("X");
			
		}else {
			oldDto.setDeleted("");
		}
		return oldDto;
	}
	
	

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<PurchaseOrderLineDto> saveService(List<PurchaseOrderLineDto> serviceList,PurchaseOrderDto po, BPartnerDto partner,PurchaseOrderLineDto poLineItem){
		List<PurchaseOrderLineDto> newServiceList = new ArrayList<>(); 
		if(!CommonUtil.isCollectionEmpty(serviceList)){
			
//			List<PurchaseOrderLineDto> oldserviceLinepoData = getoldServiceList(poLineItem.getPurchaseOrderLineId());
//					
//			
//			List<String> oldServicedata=new ArrayList<>();
//			List<String> newServicedata=new ArrayList<>();
//
//			for(PurchaseOrderLineDto oldList:oldserviceLinepoData) {
//				oldServicedata.add(oldList.getLineItemNumber());
//			}
					
//			for(PurchaseOrderLineDto newList:serviceList) {
//				newServicedata.add(newList.getLineItemNumber());
//			}
//			
//			List<String> deletedServiceListinSAP= getServiceDeletedLineNoList(oldServicedata,newServicedata);
//			setisActiveinServicePOLine(deletedServiceListinSAP, po.getPurchaseOrderNumber(), poLineItem);
			
			
			
			for(PurchaseOrderLineDto service : serviceList){
				
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("poNumber", po.getPurchaseOrderNumber());
				params.put("lineItemNumber", poLineItem.getLineItemNumber());
				params.put("serviceItemNumber", service.getLineItemNumber());
				
				service.setPurchaseOrder(po);
				service.setPartner(partner);
				service.setParentPOLine(poLineItem);
				PurchaseOrderLineDto old = findDto("getServiceLineByPOandLineItemNumber", params);
				if(old==null){
					service = super.save(service);
				}else{
					old=getNewServiceLineFromOld(old, service);
					service=super.updateDto(old);
				}
				
				newServiceList.add(service);
			}
			
			//   List<PurchaseOrderLineDto> oldserviceLinepoData = poLineService.findDtos("getoldServiceLineByPOLineId",AbstractContextServiceImpl.getParamMap("poLineId", poLineItem.getPurchaseOrderLineId()));
		}
		
		return newServiceList;
	}

	private List<PurchaseOrderLineDto> getoldServiceList(Long purchaseOrderLineId) {
		
		
		List<PurchaseOrderLineDto> serviceList	=poLineService.findDtos("getoldServiceLineByPOLineId",AbstractContextServiceImpl.getParamMap("poLineId", purchaseOrderLineId));     
		return serviceList;
	}

	private PurchaseOrderLineDto setisActiveinServicePOLine(List<String> deletedServiceListinSAP, String purchaseOrderNumber,PurchaseOrderLineDto poLineItem) {
	
		
		
		for(String deletedLineNo:deletedServiceListinSAP) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("poNumber", purchaseOrderNumber);
		params.put("lineItemNumber", poLineItem.getLineItemNumber());
		params.put("serviceItemNumber", deletedLineNo);
		
		PurchaseOrderLineDto Serviceold = findDto("getServiceLineByPOandLineItemNumber", params);

		if(Serviceold!=null) {
			Map<String, Object> serviceparams1 = new HashMap<String, Object>();
			serviceparams1.put("isActive", "N");
			
			poLineService.updateByJpql(serviceparams1, AbstractContextServiceImpl.getParamMap("purchaseOrderLineId",
					Serviceold.getPurchaseOrderLineId()));
		}
		}
		
		return null;
		
	}

	private List<String> getServiceDeletedLineNoList(List<String> oldServicedata, List<String> newServicedata) {
		List<String> remainingNo=new ArrayList<>();
		
		for(String deletedItem:oldServicedata) {
			
			if(newServicedata.contains(deletedItem)) {
				
			}else {
				remainingNo.add(deletedItem);
			}	
		}		
		return remainingNo;
	}
	
}
