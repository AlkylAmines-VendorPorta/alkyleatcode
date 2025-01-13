package com.novelerp.appcontext.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Vivek Birdi
 *
 */
@Entity
@Table(name="c_city")
public class City extends ContextPO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8190983642694329155L;
	private Long cityId;
	private String name;
	
	@Id
	@Column(name = "c_city_id")
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
