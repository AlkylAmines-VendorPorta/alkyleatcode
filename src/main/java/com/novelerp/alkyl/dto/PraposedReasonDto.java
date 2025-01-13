package com.novelerp.alkyl.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.novelerp.appcontext.dto.CommonContextDto;

public class PraposedReasonDto extends CommonContextDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long praposedReasonId;
	private String code;
	private String name;
	private String description;
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="annexureId")
	private AnnexureDto annexure;
	public Long getPraposedReasonId() {
		return praposedReasonId;
	}
	public void setPraposedReasonId(Long praposedReasonId) {
		this.praposedReasonId = praposedReasonId;
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
	public AnnexureDto getAnnexure() {
		return annexure;
	}
	public void setAnnexure(AnnexureDto annexure) {
		this.annexure = annexure;
	}
	
	
}
