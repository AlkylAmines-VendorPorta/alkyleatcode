package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class TaxDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	private Long taxId;
	private String name;
	private String code;
	private String description;
	private TaxCategoryDto taxcategory;
	
	
	
	public Long getTaxId() {
		return taxId;
	}
	public void setTaxId(Long taxId) {
		this.taxId = taxId;
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
	public TaxCategoryDto getTaxcategory() {
		return taxcategory;
	}
	public void setTaxcategory(TaxCategoryDto taxcategory) {
		this.taxcategory = taxcategory;
	}
	
	

}
