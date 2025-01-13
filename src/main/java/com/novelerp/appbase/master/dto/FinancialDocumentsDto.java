package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class FinancialDocumentsDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	private Long financialDocumentsId;
	private String name;
	private String code;
	private String description;
	
	
	
	public Long getFinancialDocumentsId() {
		return financialDocumentsId;
	}
	public void setFinancialDocumentsId(Long financialDocumentsId) {
		this.financialDocumentsId = financialDocumentsId;
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
