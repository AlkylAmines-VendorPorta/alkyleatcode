package com.novelerp.eat.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class VendorAccountGroupDto extends CommonContextDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long vendorAccountGroupId;
	private String accountGrp;
	private String name;
	
	public Long getVendorAccountGroupId() {
		return vendorAccountGroupId;
	}
	public void setVendorAccountGroupId(Long vendorAccountGroupId) {
		this.vendorAccountGroupId = vendorAccountGroupId;
	}
	public String getAccountGrp() {
		return accountGrp;
	}
	public void setAccountGrp(String accountGrp) {
		this.accountGrp = accountGrp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
