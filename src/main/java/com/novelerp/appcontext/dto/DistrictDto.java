package com.novelerp.appcontext.dto;

public class DistrictDto extends CommonContextDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 351449226446280664L;
	private Long districtId;
	private String code;
	private String name;
	private RegionDto region;
	
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
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
	public RegionDto getRegion() {
		return region;
	}
	public void setRegion(RegionDto region) {
		this.region = region;
	}
	
	
}
