package com.novelerp.alkyl.dto;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.UserDto;

public class GateEntryLineDto extends CommonContextDto{
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
	private GateEntryDto gateEntry;
	private Double receivedQty=0.0;
	private Double gateInQty=0.0;
	private String uom;
	private String purpose;
	
	public Long getGateEntryLineId() {
		return gateEntryLineId;
	}
	public void setGateEntryLineId(Long gateEntryLineId) {
		this.gateEntryLineId = gateEntryLineId;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getMaterialDesc() {
		return materialDesc;
	}
	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}
	public GateEntryDto getGateEntry() {
		return gateEntry;
	}
	public void setGateEntry(GateEntryDto gateEntry) {
		this.gateEntry = gateEntry;
	}
	public Double getMaterialQty() {
		return materialQty;
	}
	public void setMaterialQty(Double materialQty) {
		this.materialQty = materialQty;
	}
	public Double getMaterialRate() {
		return materialRate;
	}
	public void setMaterialRate(Double materialRate) {
		this.materialRate = materialRate;
	}
	public Double getMaterialCost() {
		return materialCost;
	}
	public void setMaterialCost(Double materialCost) {
		this.materialCost = materialCost;
	}
	public Double getRepairingCost() {
		return repairingCost;
	}
	public void setRepairingCost(Double repairingCost) {
		this.repairingCost = repairingCost;
	}
	public Double getReceivedQty() {
		return receivedQty;
	}
	public void setReceivedQty(Double receivedQty) {
		this.receivedQty = receivedQty;
	}
	public Double getGateInQty() {
		return gateInQty;
	}
	public void setGateInQty(Double gateInQty) {
		this.gateInQty = gateInQty;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	
	
	
	
}
