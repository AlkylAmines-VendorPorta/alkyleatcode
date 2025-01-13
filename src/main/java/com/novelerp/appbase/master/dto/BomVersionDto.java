package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class BomVersionDto extends CommonContextDto {

	/**
	 * Ankita
	 */
	private static final long serialVersionUID = 1L;
	private Long bomVersionId;
	private String name;
	private MaterialDto material;
	
	public Long getBomVersionId() {
		return bomVersionId;
	}
	public void setBomVersionId(Long bomVersionId) {
		this.bomVersionId = bomVersionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MaterialDto getMaterial() {
		return material;
	}
	public void setMaterial(MaterialDto material) {
		this.material = material;
	}

	
}
