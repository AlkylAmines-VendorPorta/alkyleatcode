package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.LocationDto;
/**
 * 
 * @author Aman
 */
public class PartnerCompanyContactDto extends CommonContextDto{

	private static final long serialVersionUID = 1L;
	private Long partnerCompanyContactId;
	private String title;
	private String firstName;
	private String middleName;
	private String lastName;
	private String telephone1;
	private String telephone2;
	private String mobileNo;
	private String fax1;
	private String fax2;
	private String email;
	private String locationType;
	private LocationDto location;
	
	
	public Long getPartnerCompanyContactId() {
		return partnerCompanyContactId;
	}
	public void setPartnerCompanyContactId(Long partnerCompanyContactId) {
		this.partnerCompanyContactId = partnerCompanyContactId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getTelephone1() {
		return telephone1;
	}
	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}
	public String getTelephone2() {
		return telephone2;
	}
	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getFax1() {
		return fax1;
	}
	public void setFax1(String fax1) {
		this.fax1 = fax1;
	}
	public String getFax2() {
		return fax2;
	}
	public void setFax2(String fax2) {
		this.fax2 = fax2;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLocationType() {
		return locationType;
	}
	public LocationDto getLocation() {
		return location;
	}
	public void setLocation(LocationDto location) {
		this.location = location;
	}
	
	
}
