package com.novelerp.alkyl.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class ConditionMasterDto extends CommonContextDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long conditionMasterId;
	private String name;
	private String code;
	private String description;
	
	
	
	public Long getConditionMasterId() {
		return conditionMasterId;
	}
	public void setConditionMasterId(Long conditionMasterId) {
		this.conditionMasterId = conditionMasterId;
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
