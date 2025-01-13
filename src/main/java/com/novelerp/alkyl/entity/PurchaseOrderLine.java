package com.novelerp.alkyl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appbase.master.entity.Material;
import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="t_purchase_order_line")
public class PurchaseOrderLine  extends ContextPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long purchaseOrderLineId;
	private PurchaseOrder purchaseOrder;
	
	private String name;
	private String code;
	private String description;
	
	private String lineItemNumber;
	private Material material;
	private String uom;
	private Double poQuantity;
	private Double rate;
	private String currency;
//	private UOM uom;
	private Date deliveryDate;
	private String plant;
	private String deliveryStatus;
	private String controlCode;
	private String trackingNmber;
	private Double overDeliveryTol;
	private Double underdeliveryTol;
	private String deliveryScheduleAnnual;
	private Double deliveryQuantity;
	private Double balanceQuantity;

	private Double asnQuantity;
	
	private PurchaseOrderLine parentPOLine;

	private Double grnQuantity;
	private String glno;

	private String batch;
	private String deleted;
	
	@Id
	@SequenceGenerator(name = "t_purchase_order_line_seq", sequenceName = "t_purchase_order_line_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_purchase_order_line_seq")
	@Column(name="t_purchase_order_line_id",updatable = false)
	public Long getPurchaseOrderLineId() {
		return purchaseOrderLineId;
	}
	public void setPurchaseOrderLineId(Long purchaseOrderLineId) {
		this.purchaseOrderLineId = purchaseOrderLineId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_purchase_order_id", referencedColumnName = "t_purchase_order_id")
	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}
	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
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
	
	@Column(name="line_item_number")
	public String getLineItemNumber() {
		return lineItemNumber;
	}
	public void setLineItemNumber(String lineItemNumber) {
		this.lineItemNumber = lineItemNumber;
	}
	
//	@Column(name="material_description")
//	public String getMaterialDescription() {
//		return materialDescription;
//	}
//	public void setMaterialDescription(String materialDescription) {
//		this.materialDescription = materialDescription;
//	}
	
	@Column(name="po_quantity")
	public Double getPoQuantity() {
		return poQuantity;
	}
	public void setPoQuantity(Double poQuantity) {
		this.poQuantity = poQuantity;
	}
	
	@Column(name="rate")
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	
	@Column(name="delivery_date")
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	@Column(name="plant")
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	
	@Column(name="delivery_status")
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	
	@Column(name="control_code")
	public String getControlCode() {
		return controlCode;
	}
	public void setControlCode(String controlCode) {
		this.controlCode = controlCode;
	}
	
	@Column(name="tracking_number")
	public String getTrackingNmber() {
		return trackingNmber;
	}
	public void setTrackingNmber(String trackingNmber) {
		this.trackingNmber = trackingNmber;
	}
	
	@Column(name="over_delivery_tol")
	public Double getOverDeliveryTol() {
		return overDeliveryTol;
	}
	public void setOverDeliveryTol(Double overDeliveryTol) {
		this.overDeliveryTol = overDeliveryTol;
	}
	
	@Column(name="under_delivery_tol")
	public Double getUnderdeliveryTol() {
		return underdeliveryTol;
	}
	public void setUnderdeliveryTol(Double underdeliveryTol) {
		this.underdeliveryTol = underdeliveryTol;
	}
	
	@Column(name="delivery_schedule_annual")
	public String getDeliveryScheduleAnnual() {
		return deliveryScheduleAnnual;
	}
	public void setDeliveryScheduleAnnual(String deliveryScheduleAnnual) {
		this.deliveryScheduleAnnual = deliveryScheduleAnnual;
	}
	
	@Column(name="delivery_quantity")
	public Double getDeliveryQuantity() {
		return deliveryQuantity;
	}
	public void setDeliveryQuantity(Double deliveryQuantity) {
		this.deliveryQuantity = deliveryQuantity;
	}
	
	@Column(name="balance_quantity")
	public Double getBalanceQuantity() {
		return balanceQuantity;
	}
	public void setBalanceQuantity(Double balanceQuantity) {
		this.balanceQuantity = balanceQuantity;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_material_id", referencedColumnName="m_material_id")
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	@Column(name="uom")
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	
	@Column(name="currency")
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	@Column(name="asn_quantity")
	public Double getAsnQuantity() {
		return asnQuantity;
	}
	public void setAsnQuantity(Double asnQuantity) {
		this.asnQuantity = asnQuantity;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_po_line_id",referencedColumnName="t_purchase_order_line_id")
	public PurchaseOrderLine getParentPOLine() {
		return parentPOLine;
	}
	public void setParentPOLine(PurchaseOrderLine parentPOLine) {
		this.parentPOLine = parentPOLine;
	}
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="c_currency_id", referencedColumnName="c_currency_id")
//	public Currency getCurrency() {
//		return currency;
//	}
//	public void setCurrency(Currency currency) {
//		this.currency = currency;
//	}
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="m_uom_id", referencedColumnName="m_uom_id")
//	public UOM getUom() {
//		return uom;
//	}
//	public void setUom(UOM uom) {
//		this.uom = uom;
//	}
	@Column(name="grn_quantity")
	public Double getGrnQuantity() {
		return grnQuantity;
	}
	public void setGrnQuantity(Double grnQuantity) {
		this.grnQuantity = grnQuantity;
	}
	
	@Column(name="glno")
	public String getGlno() {
		return glno;
	}
	public void setGlno(String glno) {
		this.glno = glno;
	}	
	@Column(name="batch")
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	@Column(name="deleted")
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
}
