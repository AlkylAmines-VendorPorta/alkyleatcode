/**
 * 21-Jun-2017
 * @author Nikita
 * TaxAttributesDTO.java
 */
package com.novelerp.appbase.master.dto;

import com.novelerp.core.dto.CommonDto;

public class TaxAttributesDto extends CommonDto {
	
	private static final long serialVersionUID = -495135791374150589L;
	private String name;
	private String description; 
	private Long taxAttributesId;
	private String isTaxable;
	private String isTaxOnTax;
	private String msg;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
		
	public Long getTaxAttributesId() {
		return taxAttributesId;
	}
	public void setTaxAttributesId(Long taxAttributesId) {
		this.taxAttributesId = taxAttributesId;
	}
		
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getIsTaxable() {
		return isTaxable;
	}
	public void setIsTaxable(String isTaxable) {
		this.isTaxable = isTaxable;
	}
	public String getIsTaxOnTax() {
		return isTaxOnTax;
	}
	public void setIsTaxOnTax(String isTaxOnTax) {
		this.isTaxOnTax = isTaxOnTax;
	}
	
	
		
}//end of class