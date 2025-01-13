package com.novelerp.appbase.master.dto;

import com.novelerp.core.dto.CommonDto;

public class CityDto extends CommonDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9015906851029417791L;
	private Long cityId;
	private String name;
	
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		
}
