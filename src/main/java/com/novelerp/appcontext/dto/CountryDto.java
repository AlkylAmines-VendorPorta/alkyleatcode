package com.novelerp.appcontext.dto;

public class CountryDto extends CommonContextDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1885497397773985346L;
	private Long countryId;
	private String name;
	private String code;
	private String description;

	
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
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
