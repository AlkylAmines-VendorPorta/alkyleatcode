package com.novelerp.alkyl.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class PoLineConditionDto extends CommonContextDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PurchaseOrderLineDto purchaseOrderLine;
	private ConditionMasterDto condition;
	
	private String name;
	private String code;
	private String description;
	
	
	
	public PurchaseOrderLineDto getPurchaseOrderLine() {
		return purchaseOrderLine;
	}
	public void setPurchaseOrderLine(PurchaseOrderLineDto purchaseOrderLine) {
		this.purchaseOrderLine = purchaseOrderLine;
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
	public ConditionMasterDto getCondition() {
		return condition;
	}
	public void setCondition(ConditionMasterDto condition) {
		this.condition = condition;
	}
		
	
	
	
}
