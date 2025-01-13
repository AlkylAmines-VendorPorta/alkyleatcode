package com.novelerp.alkyl.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="t_advance_shipment_notice_line")
public class AdvanceShipmentNoticeLine extends ContextPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String code;
	private String description;
	private Long advanceShipmentNoticeLineId;
	private AdvanceShipmentNotice advanceshipmentnotice;
	private String lineItemNo;
	private Double deliveryQuantity;
	private Double basicValue;
	private Double loadingUnloadingCharges;
	private Double grossTotal;
	private Double mismatchAmount;
	private PurchaseOrderLine poLine; 
	private Double shortageQuantity;
	private Double rejectedQuantity;
	private Double confirmQuantity;
	private AdvanceShipmentNoticeLine parentASNLine;
	private String storageLocation;
	
	private String batch;
	private String costCenter;
	private List<ASNLineCostCenter> asnLineCostCenter;
	private String valueType; 
	private Double rate=new Double(0);
	private String uom;
	private String sapServiceSheetNo;
	
	@Id
	@SequenceGenerator(name = "t_advance_shipment_notice_line_seq", sequenceName = "t_advance_shipment_notice_line_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_advance_shipment_notice_line_seq")
	@Column(name="t_advance_shipment_notice_line_id",updatable = false)
	public Long getAdvanceShipmentNoticeLineId() {
		return advanceShipmentNoticeLineId;
	}
	public void setAdvanceShipmentNoticeLineId(Long advanceShipmentNoticeLineId) {
		this.advanceShipmentNoticeLineId = advanceShipmentNoticeLineId;
	}
	@Column(name="line_item_no")
	public String getLineItemNo() {
		return lineItemNo;
	}
	public void setLineItemNo(String lineItemNo) {
		this.lineItemNo = lineItemNo;
	}
	
	@Column(name="delivery_quanity")
	public Double getDeliveryQuantity() {
		return deliveryQuantity;
	}
	public void setDeliveryQuantity(Double deliveryQuantity) {
		this.deliveryQuantity = deliveryQuantity;
	}
	@Column(name="basic_value")
	public Double getBasicValue() {
		return basicValue;
	}
	public void setBasicValue(Double basicValue) {
		this.basicValue = basicValue;
	}
	@Column(name="loading_unloading_charges")
	public Double getLoadingUnloadingCharges() {
		return loadingUnloadingCharges;
	}
	public void setLoadingUnloadingCharges(Double loadingUnloadingCharges) {
		this.loadingUnloadingCharges = loadingUnloadingCharges;
	}
	
	@Column(name="gross_total")
	public Double getGrossTotal() {
		return grossTotal;
	}
	public void setGrossTotal(Double grossTotal) {
		this.grossTotal = grossTotal;
	}
	
	@Column(name="mismatch_amount")
	public Double getMismatchAmount() {
		return mismatchAmount;
	}
	public void setMismatchAmount(Double mismatchAmount) {
		this.mismatchAmount = mismatchAmount;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="value")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_advance_shipment_notice_id", referencedColumnName="t_advance_shipment_notice_id")
	public AdvanceShipmentNotice getAdvanceshipmentnotice() {
		return advanceshipmentnotice;
	}
	public void setAdvanceshipmentnotice(AdvanceShipmentNotice advanceshipmentnotice) {
		this.advanceshipmentnotice = advanceshipmentnotice;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_purchase_order_line_id", referencedColumnName="t_purchase_order_line_id")
	public PurchaseOrderLine getPoLine() {
		return poLine;
	}
	public void setPoLine(PurchaseOrderLine poLine) {
		this.poLine = poLine;
	}
	@Column(name="shortage_qty")
	public Double getShortageQuantity() {
		return shortageQuantity;
	}
	public void setShortageQuantity(Double shortageQuantity) {
		this.shortageQuantity = shortageQuantity;
	}
	@Column(name="rejected_qty")
	public Double getRejectedQuantity() {
		return rejectedQuantity;
	}
	public void setRejectedQuantity(Double rejectedQuantity) {
		this.rejectedQuantity = rejectedQuantity;
	}
	@Column(name="confirm_qty")
	public Double getConfirmQuantity() {
		return confirmQuantity;
	}
	public void setConfirmQuantity(Double confirmQuantity) {
		this.confirmQuantity = confirmQuantity;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_asn_line_id",referencedColumnName="t_advance_shipment_notice_line_id")
	public AdvanceShipmentNoticeLine getParentASNLine() {
		return parentASNLine;
	}
	public void setParentASNLine(AdvanceShipmentNoticeLine parentASNLine) {
		this.parentASNLine = parentASNLine;
	}
	@Column(name="storage_location")
	public String getStorageLocation() {
		return storageLocation;
	}
	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}
	@Column(name="batch")
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	@Column(name="cost_center")
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="asn_line_id")
	public List<ASNLineCostCenter> getAsnLineCostCenter() {
		return asnLineCostCenter;
	}
	public void setAsnLineCostCenter(List<ASNLineCostCenter> asnLineCostCenter) {
		this.asnLineCostCenter = asnLineCostCenter;
	}
	
	@Column(name="value_type")
	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	
	
	
	
	@Column(name="rate")
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	
	@Column(name="uom")
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	
	
	
//	@Column(name="sap_servicesheet_no")
//	public String getSapServiceSheetNo() {
//		return sapServiceSheetNo;
//	}
//	public void setSapServiceSheetNo(String sapServiceSheetNo) {
//		this.sapServiceSheetNo = sapServiceSheetNo;
//	}

}
