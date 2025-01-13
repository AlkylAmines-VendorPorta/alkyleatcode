package com.novelerp.appbase.master.dto;

import com.novelerp.core.dto.CommonDto;

public class TaxSetDto extends CommonDto {
	

/**
 * 
 * @author Aman 
 * (Changed by Vivek Birdi)
 *
 */
	private static final long serialVersionUID = -8725520676237732372L;
	private Long taxId;	
	private String name;
	private String code;
	private String description;
	
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
	
	

}
