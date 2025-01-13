package com.novelerp.appcontext.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author Vivek Birdi
 *
 */
@Entity
@Table(name="c_region")
public class Region extends ContextPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 50772183474271297L;
	private Long regionId;
	private String name;
	private String description;
	private Country country;
	private String isDefault;
	private String code;
	
	@Column(name="isdefault")
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	@Id
	@Column(name = "c_region_id")
	public Long getRegionId() {
		return regionId;
	}
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_country_id")
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	
	@Column(name = "code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
