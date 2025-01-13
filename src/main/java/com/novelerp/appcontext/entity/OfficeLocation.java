package com.novelerp.appcontext.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/** 
 * @author Aman
 *
 */

@Entity
@Table(name="m_office_location")
public class OfficeLocation extends ContextPO  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long officeLocationId;
	private String name;
	private String description;
	private Location location;
	private String locationTypeRef;
	private long locationTypeId;
	
	@Id
	@SequenceGenerator(name="m_office_location_seq",sequenceName="m_office_location_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_office_location_seq")	
	@Column(name = "m_office_location_id", updatable=false)
	public Long getOfficeLocationId() {
		return officeLocationId;
	}
	public void setOfficeLocationId(Long officeLocationId) {
		this.officeLocationId = officeLocationId;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@OneToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="c_location_id")
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	@Column(name="location_type")
	public String getLocationTypeRef() {
		return locationTypeRef;
	}
	public void setLocationTypeRef(String locationTypeRef) {
		this.locationTypeRef = locationTypeRef;
	}
	@Column(name="location_type_ID")
	public long getLocationTypeId() {
		return locationTypeId;
	}
	public void setLocationTypeId(long locationTypeId) {
		this.locationTypeId = locationTypeId;
	}

}
