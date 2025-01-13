package com.novelerp.appbase.master.dto;

import com.novelerp.core.dto.CommonDto;

/**
 *  @author ankita
 * ProductCategoryDto
 */

public class ProductCategoryDto extends CommonDto {


	private static final long serialVersionUID = 4301197393755862171L;
	private Long productCategoryId;
	private String value;
	private String name;
	private String description;
	private String isDefault;
	private String msg;
	
	
	

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	
}