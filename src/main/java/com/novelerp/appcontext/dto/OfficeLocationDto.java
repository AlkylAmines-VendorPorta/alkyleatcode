package com.novelerp.appcontext.dto;


/** 
 * @author Aman
 *
 */

public class OfficeLocationDto extends CommonContextDto{


	private static final long serialVersionUID = 1L;
	private Long officeLocationId;
	private String name;
	private String description;
	private LocationDto location;
	private String locationTypeRef;
	private long locationTypeId;
	
	
	public Long getOfficeLocationId() {
		return officeLocationId;
	}
	public void setOfficeLocationId(Long officeLocationId) {
		this.officeLocationId = officeLocationId;
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
	public LocationDto getLocation() {
		return location;
	}
	public void setLocation(LocationDto location) {
		this.location = location;
	}
	public String getLocationTypeRef() {
		return locationTypeRef;
	}
	public void setLocationTypeRef(String locationTypeRef) {
		this.locationTypeRef = locationTypeRef;
	}
	public long getLocationTypeId() {
		return locationTypeId;
	}
	public void setLocationTypeId(long locationTypeId) {
		this.locationTypeId = locationTypeId;
	}
	
	
	
}
