package com.novelerp.appcontext.dto;

public class RegionDto extends CommonContextDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8862829487655726913L;
	private Long regionId;
	private String name;
	private String description;
	private CountryDto country;
	private String code;
	
	public Long getRegionId() {
		return regionId;
	}
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
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
	public CountryDto getCountry() {
		return country;
	}
	public void setCountry(CountryDto country) {
		this.country = country;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}