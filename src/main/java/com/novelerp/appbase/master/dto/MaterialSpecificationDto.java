package com.novelerp.appbase.master.dto;

import java.math.BigDecimal;

import com.novelerp.appcontext.dto.CommonContextDto;

public class MaterialSpecificationDto extends CommonContextDto{

	private static final long serialVersionUID = 1L;
	private Long materialSpecificationId;
	private String name;
	private String code;
	private String description;
	private BomVersionDto material;
	/*private String specification;*/
	private MaterialDto specification;
	private BigDecimal quantity;
	
	
	public Long getMaterialSpecificationId() {
		return materialSpecificationId;
	}
	public void setMaterialSpecificationId(Long materialSpecificationId) {
		this.materialSpecificationId = materialSpecificationId;
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
	public BomVersionDto getMaterial() {
		return material;
	}
	public void setMaterial(BomVersionDto material) {
		this.material = material;
	}
	/*public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}*/
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public MaterialDto getSpecification() {
		return specification;
	}
	public void setSpecification(MaterialDto specification) {
		this.specification = specification;
	}
	
	
	
}
