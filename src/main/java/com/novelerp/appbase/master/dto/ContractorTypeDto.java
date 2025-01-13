package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class ContractorTypeDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	private Long contractorTypeId;
	private String name;
	private String code;
	private String description;
	
	
	
	public Long getContractorTypeId() {
		return contractorTypeId;
	}
	public void setContractorTypeId(Long contractorTypeId) {
		this.contractorTypeId = contractorTypeId;
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
