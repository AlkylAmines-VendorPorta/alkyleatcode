package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class MaterialSubGroupDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	private Long materialSubGroupId;
	private String name;
	private String code;
	private String description;
	private MaterialGroupDto materialGroup;
	
	
	
	public Long getMaterialSubGroupId() {
		return materialSubGroupId;
	}
	public void setMaterialSubGroupId(Long materialSubGroupId) {
		this.materialSubGroupId = materialSubGroupId;
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
	public MaterialGroupDto getMaterialGroup() {
		return materialGroup;
	}
	public void setMaterialGroup(MaterialGroupDto materialGroup) {
		this.materialGroup = materialGroup;
	}
	
	

}
