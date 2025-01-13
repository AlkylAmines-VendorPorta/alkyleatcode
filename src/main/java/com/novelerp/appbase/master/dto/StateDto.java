package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class StateDto extends CommonContextDto {
	
	private static final long serialVersionUID = 1L;
	private Long stateId;
	private String name;
	private String code;
	private String description;
	

	
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
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
