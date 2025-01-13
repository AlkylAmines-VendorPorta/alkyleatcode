package com.novelerp.appcontext.dto;

public class BPartnerGroupDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	private Long bPartnerGroupId;
	private String name;
	private String code;
	private String description;
	
	public Long getbPartnerGroupId() {
		return bPartnerGroupId;
	}
	public void setbPartnerGroupId(Long bPartnerGroupId) {
		this.bPartnerGroupId = bPartnerGroupId;
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

