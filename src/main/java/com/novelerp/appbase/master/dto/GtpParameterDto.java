package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class GtpParameterDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	private Long gtpParameterId;
	private String name;
	private String code;
	private String description;
	private GtpParameterTypeDto gtpParameterType;
	private MaterialDto material;
	private String isCopied;
	
	
	
	public Long getGtpParameterId() {
		return gtpParameterId;
	}
	public void setGtpParameterId(Long gtpParameterId) {
		this.gtpParameterId = gtpParameterId;
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
	
	public GtpParameterTypeDto getGtpParameterType() {
		return gtpParameterType;
	}
	public void setGtpParameterType(GtpParameterTypeDto gtpParameterType) {
		this.gtpParameterType = gtpParameterType;
	}
	public MaterialDto getMaterial() {
		return material;
	}
	public void setMaterial(MaterialDto material) {
		this.material = material;
	}
	public String getIsCopied() {
		return isCopied;
	}
	public void setIsCopied(String isCopied) {
		this.isCopied = isCopied;
	}
	
	

}

