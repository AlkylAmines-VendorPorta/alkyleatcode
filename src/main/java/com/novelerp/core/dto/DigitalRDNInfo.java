package com.novelerp.core.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Vivek Birdi
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class DigitalRDNInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(value="C")
	private String country;
	
	@JsonProperty(value="CN")
	private String signatory;
	@JsonProperty(value="O")
	private String organization;
	@JsonProperty(value="OU")
	private String orgUnit;
	@JsonProperty(value="PostalCode")
	private String postalCode;
	@JsonProperty(value="ST")
	private String state;
	@JsonProperty(value="TelephoneNumber")
	private String telephone;
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getSignatory() {
		return signatory;
	}
	public void setSignatory(String signatory) {
		this.signatory = signatory;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getOrgUnit() {
		return orgUnit;
	}
	public void setOrgUnit(String orgUnit) {
		this.orgUnit = orgUnit;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
