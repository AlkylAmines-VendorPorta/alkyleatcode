package com.novelerp.alkyl.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class VendorProfileHistoryDto extends CommonContextDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long vendorProfileHistoryID;
	private String module;
	private String oldValue;
	private String newValue;
	private String fieldName;
	public Long getVendorProfileHistoryID() {
		return vendorProfileHistoryID;
	}
	public void setVendorProfileHistoryID(Long vendorProfileHistoryID) {
		this.vendorProfileHistoryID = vendorProfileHistoryID;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getOldValue() {
		return oldValue;
	}
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	public String getNewValue() {
		return newValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	

}
