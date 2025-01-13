package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class CompanyTypeDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	private Long companyTypeId;
	private String name;
	private String code;
	private String description;
	
	
	
	public Long getCompanyTypeId() {
		return companyTypeId;
	}
	public void setCompanyTypeId(Long companyTypeId) {
		this.companyTypeId = companyTypeId;
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
	
	

}
