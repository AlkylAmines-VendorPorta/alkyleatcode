package com.novelerp.alkyl.entity;

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

import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="t_gate_entry_line")
public class GateEntryLine extends ContextPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long gateEntryLineId;
	private String serialNo;
	private String materialCode;
	private String materialDesc;
	private Double materialQty;
	private Double materialRate;
	private Double materialCost;
	private Double repairingCost;
	private GateEntry gateEntry;
	private Double receivedQty;
	private Double gateInQty;
	private String uom;
	private String purpose;
	
	@Id
	@SequenceGenerator(name = "t_gate_entry_line_seq", sequenceName = "t_gate_entry_line_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_gate_entry_line_seq")
	@Column(name="t_gate_entry_line_id",updatable = false)
	public Long getGateEntryLineId() {
		return gateEntryLineId;
	}
	public void setGateEntryLineId(Long gateEntryLineId) {
		this.gateEntryLineId = gateEntryLineId;
	}
	@Column(name="serial_no")
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	@Column(name="material_code")
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	@Column(name="material_desc")
	public String getMaterialDesc() {
		return materialDesc;
	}
	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_gate_entry_id")
	public GateEntry getGateEntry() {
		return gateEntry;
	}
	public void setGateEntry(GateEntry gateEntry) {
		this.gateEntry = gateEntry;
	}
	@Column(name="material_qty")
	public Double getMaterialQty() {
		return materialQty;
	}
	public void setMaterialQty(Double materialQty) {
		this.materialQty = materialQty;
	}
	@Column(name="material_rate")
	public Double getMaterialRate() {
		return materialRate;
	}
	public void setMaterialRate(Double materialRate) {
		this.materialRate = materialRate;
	}
	@Column(name="material_cost")
	public Double getMaterialCost() {
		return materialCost;
	}
	public void setMaterialCost(Double materialCost) {
		this.materialCost = materialCost;
	}
	@Column(name="repairing_cost")
	public Double getRepairingCost() {
		return repairingCost;
	}
	public void setRepairingCost(Double repairingCost) {
		this.repairingCost = repairingCost;
	}
	@Column(name="received_qty")
	public Double getReceivedQty() {
		return receivedQty;
	}
	public void setReceivedQty(Double receivedQty) {
		this.receivedQty = receivedQty;
	}
	@Column(name="gate_in_qty")
	public Double getGateInQty() {
		return gateInQty;
	}
	public void setGateInQty(Double gateInQty) {
		this.gateInQty = gateInQty;
	}
	@Column(name="uom")
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	
	@Column(name="purpose")
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	
}
