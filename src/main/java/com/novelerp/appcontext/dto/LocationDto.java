package com.novelerp.appcontext.dto;

/**
 * LocationDto
 * @author namdev.patil
 *
 */
/*@JsonInclude(Include.NON_NULL)*/
public class LocationDto  extends CommonContextDto{

	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -8225999786639577164L;
	
	private Long locationId;
	private String address1;
	private String address2;
	private String postal;
	private String address3;
	private String value;
	private String toaddress;
	private String locationType;
	private CountryDto country;
	private RegionDto region;
	private String city;
	private DistrictDto district;
	/*private String state;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}*/
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public CountryDto getCountry() {
		return country;
	}
	public void setCountry(CountryDto country) {
		this.country = country;
	}
	
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getToaddress() {
		return toaddress;
	}
	public void setToaddress(String toaddress) {
		this.toaddress = toaddress;
	}
	public RegionDto getRegion() {
		return region;
	}
	public void setRegion(RegionDto region) {
		this.region = region;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public DistrictDto getDistrict() {
		return district;
	}
	public void setDistrict(DistrictDto district) {
		this.district = district;
	}

}
