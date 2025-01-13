package com.novelerp.alkyl.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.appbase.master.dto.MaterialDto;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.util.DateUtil;

public class PurchaseOrderLineDto extends CommonContextDto{


	@Override
	public String toString() {
		return "PurchaseOrderLineDto [purchaseOrderLineId=" + purchaseOrderLineId + ", purchaseOrder=" + purchaseOrder
				+ ", name=" + name + ", code=" + code + ", description=" + description + ", lineItemNumber="
				+ lineItemNumber + ", material=" + material + ", uom=" + uom + ", currency=" + currency
				+ ", poQuantity=" + poQuantity + ", rate=" + rate + ", deliveryDate=" + deliveryDate + ", plant="
				+ plant + ", deliveryStatus=" + deliveryStatus + ", controlCode=" + controlCode + ", trackingNmber="
				+ trackingNmber + ", overDeliveryTol=" + overDeliveryTol + ", underdeliveryTol=" + underdeliveryTol
				+ ", deliveryScheduleAnnual=" + deliveryScheduleAnnual + ", deliveryQuantity=" + deliveryQuantity
				+ ", balanceQuantity=" + balanceQuantity + ", asnQuantity=" + asnQuantity + ", grnQuantity="
				+ grnQuantity + ", actualLimit=" + actualLimit + ", overallLimit=" + overallLimit + ", balanceLimit="
				+ balanceLimit + ", actualQuantity=" + actualQuantity + ", balanceQuantity1=" + balanceQuantity1
				+ ", blocked=" + blocked + ", poLineConditionList=" + poLineConditionList + ", parentPOLine="
				+ parentPOLine + ", serviceList=" + serviceList + ", glno=" + glno + ", batch=" + batch + ", deleted="
				+ deleted + "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long purchaseOrderLineId;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="purchaseOrderId",scope=PurchaseOrderLineDto.class)
	private PurchaseOrderDto purchaseOrder;
	
	
	private String name;
	private String code;
	private String description;
	
	private String lineItemNumber;
	private MaterialDto material;
	private String uom;
	private String currency;
	private Double poQuantity=new Double(0);
	private Double rate=new Double(0);
	//private CurrencyDto currency;
	//private UOMDto uom;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date deliveryDate;
	private String plant;
	private String deliveryStatus;
	private String controlCode;
	private String trackingNmber;
	private Double overDeliveryTol=new Double(0);
	private Double underdeliveryTol=new Double(0);
	private String deliveryScheduleAnnual;
	private Double deliveryQuantity=new Double(0);
	private Double balanceQuantity=new Double(0);
	private Double asnQuantity=new Double(0);
	private Double grnQuantity=new Double(0);
	
	public Double actualLimit;
	public Double overallLimit;
	public Double balanceLimit;
	public Double actualQuantity;
	public Double balanceQuantity1;
	public String blocked;
	
	
	
	public Double getBalanceLimit() {
		return balanceLimit;
	}
	public void setBalanceLimit(Double balanceLimit) {
		this.balanceLimit = balanceLimit;
	}

	
	public Double getActualQuantity() {
		return actualQuantity;
	}
	public Double getBalanceQuantity1() {
		return balanceQuantity1;
	}
	public void setActualQuantity(Double actualQuantity) {
		this.actualQuantity = actualQuantity;
	}
	public void setBalanceQuantity1(Double balanceQuantity1) {
		this.balanceQuantity1 = balanceQuantity1;
	}
	public Double getActualLimit() {
		return actualLimit;
	}
	public Double getOverallLimit() {
		return overallLimit;
	}
	public void setActualLimit(Double actualLimit) {
		this.actualLimit = actualLimit;
	}
	public void setOverallLimit(Double overallLimit) {
		this.overallLimit = overallLimit;
	}
	private List<PoLineConditionDto> poLineConditionList;

	private PurchaseOrderLineDto parentPOLine;	
	
	private List<PurchaseOrderLineDto> serviceList;

	private String glno;

	private String batch;
	private String deleted;
	
	public Long getPurchaseOrderLineId() {
		return purchaseOrderLineId;
	}
	public void setPurchaseOrderLineId(Long purchaseOrderLineId) {
		this.purchaseOrderLineId = purchaseOrderLineId;
	}
	public PurchaseOrderDto getPurchaseOrder() {
		return purchaseOrder;
	}
	public void setPurchaseOrder(PurchaseOrderDto purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLineItemNumber() {
		return lineItemNumber;
	}
	public void setLineItemNumber(String lineItemNumber) {
		this.lineItemNumber = lineItemNumber;
	}
	
//	public String getMaterialDescription() {
//		return materialDescription;
//	}
//	public void setMaterialDescription(String materialDescription) {
//		this.materialDescription = materialDescription;
//	}
	public Double getPoQuantity() {
		if(null==this.poQuantity){
			return new Double(0);
		}
		return poQuantity;
	}
	public void setPoQuantity(Double poQuantity) {
		this.poQuantity = poQuantity;
	}
	public Double getRate() {
		if(null==this.rate){
			return new Double(0);
		}
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public String getControlCode() {
		return controlCode;
	}
	public void setControlCode(String controlCode) {
		this.controlCode = controlCode;
	}
	public String getTrackingNmber() {
		return trackingNmber;
	}
	public void setTrackingNmber(String trackingNmber) {
		this.trackingNmber = trackingNmber;
	}
	public Double getOverDeliveryTol() {
		if(null==this.overDeliveryTol){
			return new Double(0);
		}
		return overDeliveryTol;
	}
	public void setOverDeliveryTol(Double overDeliveryTol) {
		this.overDeliveryTol = overDeliveryTol;
	}
	public Double getUnderdeliveryTol() {
		if(null==this.underdeliveryTol){
			return new Double(0);
		}
		return underdeliveryTol;
	}
	public void setUnderdeliveryTol(Double underdeliveryTol) {
		this.underdeliveryTol = underdeliveryTol;
	}
	public String getDeliveryScheduleAnnual() {
		return deliveryScheduleAnnual;
	}
	public void setDeliveryScheduleAnnual(String deliveryScheduleAnnual) {
		this.deliveryScheduleAnnual = deliveryScheduleAnnual;
	}
	public Double getDeliveryQuantity() {
		if(null==this.deliveryQuantity){
			return new Double(0);
		}
		return deliveryQuantity;
	}
	public void setDeliveryQuantity(Double deliveryQuantity) {
		this.deliveryQuantity = deliveryQuantity;
	}
	public Double getBalanceQuantity() {
		if(null==this.balanceQuantity){
			return new Double(0);
		}
		return balanceQuantity;
	}
	public void setBalanceQuantity(Double balanceQuantity) {
		this.balanceQuantity = balanceQuantity;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public MaterialDto getMaterial() {
		return material;
	}
	public void setMaterial(MaterialDto material) {
		this.material = material;
	}
	public List<PoLineConditionDto> getPoLineConditionList() {
		return poLineConditionList;
	}
	public void setPoLineConditionList(List<PoLineConditionDto> poLineConditionList) {
		this.poLineConditionList = poLineConditionList;
	}
	
	public void addPOLineCondition(PoLineConditionDto poLineCondition){
		if(CommonUtil.isCollectionEmpty(this.poLineConditionList)){
			this.poLineConditionList = new ArrayList<>();
		}
		
		this.poLineConditionList.add(poLineCondition);
	}
	
	public Double getAsnQuantity() {
		if(null==this.asnQuantity){
			return new Double(0);
		}
		return asnQuantity;
	}
	public void setAsnQuantity(Double asnQuantity) {
		this.asnQuantity = asnQuantity;
	}
	
	public PurchaseOrderLineDto getParentPOLine() {
		return parentPOLine;
	}
	public void setParentPOLine(PurchaseOrderLineDto parentPOLine) {
		this.parentPOLine = parentPOLine;
	}
	public List<PurchaseOrderLineDto> getServiceList() {
		return serviceList;
	}
	public void setServiceList(List<PurchaseOrderLineDto> serviceList) {
		this.serviceList = serviceList;
	}
	public Double getGrnQuantity() {
		return grnQuantity;
	}
	public void setGrnQuantity(Double grnQuantity) {
		this.grnQuantity = grnQuantity;
	}
	public String getGlno() {
		return glno;
	}
	public void setGlno(String glno) {
		this.glno = glno;
	}	
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public String getBlocked() {
		return blocked;
	}
	public void setBlocked(String blocked) {
		this.blocked = blocked;
	}
	
	
}
