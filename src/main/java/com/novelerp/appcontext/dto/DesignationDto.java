package com.novelerp.appcontext.dto;


public class DesignationDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	private Long designationId;
	private String name;
	private String code;
	private String description;
	private String isBPDesignation;
	
	
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
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
	public String getIsBPDesignation() {
		return isBPDesignation;
	}
	public void setIsBPDesignation(String isBPDesignation) {
		this.isBPDesignation = isBPDesignation;
	}
	
	

}
