package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class IsStdDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	private Long isStdId;
	private String name;
	private String code;
	private String description;
	private MaterialDto material;
	
	
	public Long getIsStdId() {
		return isStdId;
	}
	public void setIsStdId(Long isStdId) {
		this.isStdId = isStdId;
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
	public MaterialDto getMaterial() {
		return material;
	}
	public void setMaterial(MaterialDto material) {
		this.material = material;
	}
	

	
}
