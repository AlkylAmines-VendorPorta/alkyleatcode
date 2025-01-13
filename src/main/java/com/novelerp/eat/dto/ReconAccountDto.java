package com.novelerp.eat.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class ReconAccountDto extends CommonContextDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long reconAccountId;
	private String value;
	private String name;
	
	public Long getReconAccountId() {
		return reconAccountId;
	}
	public void setReconAccountId(Long reconAccountId) {
		this.reconAccountId = reconAccountId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
