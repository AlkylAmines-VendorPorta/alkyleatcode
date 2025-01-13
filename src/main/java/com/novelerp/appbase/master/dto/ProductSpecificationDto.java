package com.novelerp.appbase.master.dto;

import com.novelerp.core.dto.CommonDto;

/**
 * @author varsha
 * ProductSpecificationDto
 */

public class ProductSpecificationDto extends CommonDto{

	
	private static final long serialVersionUID = -5844348477434502763L;
	private Long productSpecfnId;
	private String name;
	private ProductSpecificationSetDto productSpecificationSet; 
	private String  attributeLevel;
	
	public Long getProductSpecfnId() {
		return productSpecfnId;
	}
	public void setProductSpecfnId(Long productSpecfnId) {
		this.productSpecfnId = productSpecfnId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAttributeLevel() {
		return attributeLevel;
	}
	public void setAttributeLevel(String attributeLevel) {
		this.attributeLevel = attributeLevel;
	}
	public ProductSpecificationSetDto productSpecificationSet() {
		return productSpecificationSet;
	}
	public void setProductSpecfnSet(ProductSpecificationSetDto productSpecificationSet) {
		this.productSpecificationSet = productSpecificationSet;
	}
	
}
