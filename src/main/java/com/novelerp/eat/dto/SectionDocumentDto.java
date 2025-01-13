/**
 * @author Ankush
 */
package com.novelerp.eat.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.appcontext.dto.CommonContextDto;


public class SectionDocumentDto extends CommonContextDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long sectionDocumentId;
	private String code;
	private String name;
	private String description;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="tahdrDetailId",scope=Long.class)
	private TAHDRDetailDto tahdrDetail;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="tahdrMaterialId",scope=Long.class)
	private TAHDRMaterialDto tahdrMaterial;
	
	public Long getSectionDocumentId() {
		return sectionDocumentId;
	}
	public void setSectionDocumentId(Long sectionDocumentId) {
		this.sectionDocumentId = sectionDocumentId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public TAHDRDetailDto getTahdrDetail() {
		return tahdrDetail;
	}
	public void setTahdrDetail(TAHDRDetailDto tahdrDetail) {
		this.tahdrDetail = tahdrDetail;
	}
	public TAHDRMaterialDto getTahdrMaterial() {
		return tahdrMaterial;
	}
	public void setTahdrMaterial(TAHDRMaterialDto tahdrMaterial) {
		this.tahdrMaterial = tahdrMaterial;
	}
	
	
}
