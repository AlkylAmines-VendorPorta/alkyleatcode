package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class DocumentTypeDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	private Long documentTypeId;
	private String name;
	private String code;
	private String description;
	
	
	
	public Long getDocumentTypeId() {
		return documentTypeId;
	}
	public void setDocumentTypeId(Long documentTypeId) {
		this.documentTypeId = documentTypeId;
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