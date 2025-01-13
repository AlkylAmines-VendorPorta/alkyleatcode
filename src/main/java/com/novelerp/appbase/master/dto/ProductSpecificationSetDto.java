package com.novelerp.appbase.master.dto;

import com.novelerp.core.dto.CommonDto;

/**
 * @author varsha
 * ProductSpecificationSetDto
 */

public class ProductSpecificationSetDto  extends CommonDto {

	
	private static final long serialVersionUID = -1534117243465520058L;
	
	private Long productSpecificationSetId ;
	private String name;
	
	
	public Long getProductSpecificationSetId() {
		return productSpecificationSetId;
	}
	public void setProductSpecificationSetId(Long productSpecificationSetId) {
		this.productSpecificationSetId = productSpecificationSetId;
	}
	public String getName() {
		return name;
	}
	public void setProductSpecificationSetName(String name) {
		this.name = name;
	}
}
