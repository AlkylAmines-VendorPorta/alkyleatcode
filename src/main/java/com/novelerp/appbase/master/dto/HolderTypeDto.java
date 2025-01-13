package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class HolderTypeDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	private Long holderTypeId;
	private String name;
	private String code;
	private String description;
	
	
	
	public Long getHolderTypeId() {
		return holderTypeId;
	}
	public void setHolderTypeId(Long holderTypeId) {
		this.holderTypeId = holderTypeId;
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
