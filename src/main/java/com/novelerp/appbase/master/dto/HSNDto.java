package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class HSNDto extends CommonContextDto{

	/**
	 * @author Ankita
	 */
	private static final long serialVersionUID = 1L;
	private Long hsnId;
	private String name;
	private String code;
	private String description;
	
	public Long getHsnId() {
		return hsnId;
	}
	public void setHsnId(Long hsnId) {
		this.hsnId = hsnId;
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
