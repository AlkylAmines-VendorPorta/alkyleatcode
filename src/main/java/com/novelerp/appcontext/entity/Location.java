package com.novelerp.appcontext.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author Vivek Birdi
 *
 */
@Entity
@Table(name = "c_location")
public class Location extends ContextPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1940804295831121317L;
	private Long locationId;
	private String address1;
	private String address2;
	private String address3;
	private String postal;
	private Country country;
	private Region region;
	private String city;
	private String toaddress;
	private String locationType;
	private District district;
	//private String state;
	

	@Id	
	@SequenceGenerator(name="c_location_seq",sequenceName="c_location_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="c_location_seq")	
	@Column(name = "c_location_id", updatable=false)
	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	@Column(name = "address_1")
	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	@Column(name = "address_2")
	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	@Column(name = "postal")
	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "m_country_id")
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "c_region_id")
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}

	@Column(name = "city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name = "to_address")
	public String getToaddress() {
		return toaddress;
	}

	public void setToaddress(String toaddress) {
		this.toaddress = toaddress;
	}
	@Column(name = "location_type")
	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="c_district_id")
	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}
	@Column(name = "address_3")
	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	
	/*@Column(name = "state")
	public String state() {
		return state;
	}

	public void state(String state) {
		this.state = state;
	}*/
	
}
