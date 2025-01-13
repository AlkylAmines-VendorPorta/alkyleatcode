package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class MaterialVersionDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	private Long materialVersionId;
	private String name;
	private String code;
	private String description;
	/*private MaterialGroupDto materialGroup;*/
	private MaterialDto material;
	private AttachmentDto versionSpecification;
	
	
	public Long getMaterialVersionId() {
		return materialVersionId;
	}
	public void setMaterialVersionId(Long materialVersionId) {
		this.materialVersionId = materialVersionId;
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
	/*public MaterialGroupDto getMaterialGroup() {
		return materialGroup;
	}
	public void setMaterialGroup(MaterialGroupDto materialGroup) {
		this.materialGroup = materialGroup;
	}*/
	public MaterialDto getMaterial() {
		return material;
	}
	public void setMaterial(MaterialDto material) {
		this.material = material;
	}
	public AttachmentDto getVersionSpecification() {
		return versionSpecification;
	}
	public void setVersionSpecification(AttachmentDto versionSpecification) {
		this.versionSpecification = versionSpecification;
	}
	

}

