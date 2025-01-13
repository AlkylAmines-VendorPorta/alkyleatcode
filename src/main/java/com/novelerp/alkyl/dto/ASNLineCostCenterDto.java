package com.novelerp.alkyl.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novelerp.appcontext.dto.CommonContextDto;

public class ASNLineCostCenterDto extends CommonContextDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long asnLineCostCenterId;
	private String name;
	private String code;
	private String description;
	@JsonIgnore
	private AdvanceShipmentNoticeLineDto asnLine;
	private String costCenter;
	private String storageLocation;
	private String quantity;
	//private String fundCenter;
	public Long getAsnLineCostCenterId() {
		return asnLineCostCenterId;
	}
	public void setAsnLineCostCenterId(Long asnLineCostCenterId) {
		this.asnLineCostCenterId = asnLineCostCenterId;
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
	public AdvanceShipmentNoticeLineDto getAsnLine() {
		return asnLine;
	}
	public void setAsnLine(AdvanceShipmentNoticeLineDto asnLine) {
		this.asnLine = asnLine;
	}
	public String getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	public String getStorageLocation() {
		return storageLocation;
	}
	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
//	public String getFundCenter() {
//		return fundCenter;
//	}
//	public void setFundCenter(String fundCenter) {
//		this.fundCenter = fundCenter;
//	}

	
	
	

	
}
