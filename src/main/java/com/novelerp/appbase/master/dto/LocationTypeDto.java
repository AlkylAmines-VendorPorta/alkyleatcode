package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class LocationTypeDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	private Long locationTypeId;
	private String name;
	private String code;
	private String description;
	private Long levels; 
	
	
	public Long getLocationTypeId() {
		return locationTypeId;
	}
	public void setLocationTypeId(Long locationTypeId) {
		this.locationTypeId = locationTypeId;
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
	public Long getLevels() {
		return levels;
	}
	public void setLevels(Long levels) {
		this.levels = levels;
	}

	
	
}
