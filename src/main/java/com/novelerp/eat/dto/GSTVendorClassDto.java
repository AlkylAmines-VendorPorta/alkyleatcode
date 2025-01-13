package com.novelerp.eat.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class GSTVendorClassDto extends CommonContextDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long gstVendorClassId;
	private String code;
	private String name;
	
	
	public Long getGstVendorClassId() {
		return gstVendorClassId;
	}
	public void setGstVendorClassId(Long gstVendorClassId) {
		this.gstVendorClassId = gstVendorClassId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
