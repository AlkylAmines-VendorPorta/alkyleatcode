package com.novelerp.alkyl.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.alkyl.entity.ASNLineCostCenter;

import com.novelerp.appcontext.dto.CommonContextDto;

public class AdvanceShipmentNoticeLineDto extends CommonContextDto{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String code;
	private String description;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="advanceShipmentNoticeLineId",scope=Long.class)
	private Long advanceShipmentNoticeLineId;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="advanceShipmentNoticeId",scope=AdvanceShipmentNoticeLineDto.class)
	private AdvanceShipmentNoticeDto advanceshipmentnotice;
	private String lineItemNo;
	private Double deliveryQuantity=new Double(0);
	private Double basicValue=new Double(0);
	private Double loadingUnloadingCharges=new Double(0);
	private Double grossTotal=new Double(0);
	private Double mismatchAmount=new Double(0);
	private Double shortageQuantity=new Double(0);
	private Double rejectedQuantity=new Double(0);
	private Double confirmQuantity=new Double(0);
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="advanceShipmentNoticeLineId",scope=Long.class)
	private AdvanceShipmentNoticeLineDto parentASNLine;
	private PurchaseOrderLineDto poLine;
	
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="advanceShipmentNoticeLineId",scope=Long.class)
	private List<AdvanceShipmentNoticeLineDto> serviceLineList;
	private String storageLocation;
	

	private String batch;
	private String costCenter;
	private List<ASNLineCostCenterDto> asnLineCostCenter;
	private String valueType;
	private Double rate=new Double(0);
	private String uom;
//	private String sapServiceSheetNo;
	
	
	public Long getAdvanceShipmentNoticeLineId() {
		return advanceShipmentNoticeLineId;
	}
	public void setAdvanceShipmentNoticeLineId(Long advanceShipmentNoticeLineId) {
		this.advanceShipmentNoticeLineId = advanceShipmentNoticeLineId;
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
	public Double getDeliveryQuantity() {
		if(null==this.deliveryQuantity){
			return new Double(0);
		}
		return deliveryQuantity;
	}
	public void setDeliveryQuantity(Double deliveryQuantity) {
		this.deliveryQuantity = deliveryQuantity;
	}
	public Double getBasicValue() {
		if(null==this.basicValue){
			return new Double(0);
		}
		return basicValue;
	}
	public void setBasicValue(Double basicValue) {
		this.basicValue = basicValue;
	}
	public Double getLoadingUnloadingCharges() {
		if(null==this.loadingUnloadingCharges){
			return new Double(0);
		}
		return loadingUnloadingCharges;
	}
	public void setLoadingUnloadingCharges(Double loadingUnloadingCharges) {
		this.loadingUnloadingCharges = loadingUnloadingCharges;
	}
	public Double getGrossTotal() {
		if(null==this.grossTotal){
			return new Double(0);
		}
		return grossTotal;
	}
	public void setGrossTotal(Double grossTotal) {
		this.grossTotal = grossTotal;
	}
	public Double getMismatchAmount() {
		if(null==this.mismatchAmount){
			return new Double(0);
		}
		return mismatchAmount;
	}
	public void setMismatchAmount(Double mismatchAmount) {
		this.mismatchAmount = mismatchAmount;
	}
	public AdvanceShipmentNoticeDto getAdvanceshipmentnotice() {
		return advanceshipmentnotice;
	}
	public void setAdvanceshipmentnotice(AdvanceShipmentNoticeDto advanceshipmentnotice) {
		this.advanceshipmentnotice = advanceshipmentnotice;
	}
	
	
	public String getLineItemNo() {
		return lineItemNo;
	}
	public void setLineItemNo(String lineItemNo) {
		this.lineItemNo = lineItemNo;
	}
	public PurchaseOrderLineDto getPoLine() {
		return poLine;
	}
	public void setPoLine(PurchaseOrderLineDto poLine) {
		this.poLine = poLine;
	}
	public Double getShortageQuantity() {
		if(null==this.shortageQuantity){
			return new Double(0);
		}
		return shortageQuantity;
	}
	public void setShortageQuantity(Double shortageQuantity) {
		this.shortageQuantity = shortageQuantity;
	}
	public Double getRejectedQuantity() {
		if(null==this.rejectedQuantity){
			return new Double(0);
		}
		return rejectedQuantity;
	}
	public void setRejectedQuantity(Double rejectedQuantity) {
		this.rejectedQuantity = rejectedQuantity;
	}
	public Double getConfirmQuantity() {
		if(null==this.confirmQuantity){
			return new Double(0);
		}
		return confirmQuantity;
	}
	public void setConfirmQuantity(Double confirmQuantity) {
		this.confirmQuantity = confirmQuantity;
	}
	public AdvanceShipmentNoticeLineDto getParentASNLine() {
		return parentASNLine;
	}
	public void setParentASNLine(AdvanceShipmentNoticeLineDto parentASNLine) {
		this.parentASNLine = parentASNLine;
	}
	public List<AdvanceShipmentNoticeLineDto> getServiceLineList() {
		return serviceLineList;
	}
	public void setServiceLineList(List<AdvanceShipmentNoticeLineDto> serviceLineList) {
		this.serviceLineList = serviceLineList;
	}
	public String getStorageLocation() {
		return storageLocation;
	}
	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public List<ASNLineCostCenterDto> getAsnLineCostCenter() {
		return asnLineCostCenter;
	}
	public void setAsnLineCostCenter(List<ASNLineCostCenterDto> asnLineCostCenter) {
		this.asnLineCostCenter = asnLineCostCenter;
	}	
	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}


	public Double getRate() {
		return rate;
	}
	public String getUom() {
		return uom;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	@Override
	public String toString() {
		return "AdvanceShipmentNoticeLineDto [name=" + name + ", code=" + code + ", description=" + description
				+ ", advanceShipmentNoticeLineId=" + advanceShipmentNoticeLineId + ", advanceshipmentnotice="
				+ advanceshipmentnotice + ", lineItemNo=" + lineItemNo + ", deliveryQuantity=" + deliveryQuantity
				+ ", basicValue=" + basicValue + ", loadingUnloadingCharges=" + loadingUnloadingCharges
				+ ", grossTotal=" + grossTotal + ", mismatchAmount=" + mismatchAmount + ", shortageQuantity="
				+ shortageQuantity + ", rejectedQuantity=" + rejectedQuantity + ", confirmQuantity=" + confirmQuantity
				+ ", parentASNLine=" + parentASNLine + ", poLine=" + poLine + ", serviceLineList=" + serviceLineList
				+ ", storageLocation=" + storageLocation + ", batch=" + batch + ", costCenter=" + costCenter
				+ ", asnLineCostCenter=" + asnLineCostCenter + ", valueType=" + valueType + ", rate=" + rate + ", uom="
				+ uom + "]";
	}
	
	
	
//	public String getSapServiceSheetNo() {
//		return sapServiceSheetNo;
//	}
//	public void setSapServiceSheetNo(String sapServiceSheetNo) {
//		this.sapServiceSheetNo = sapServiceSheetNo;
//	}
	
	



}
