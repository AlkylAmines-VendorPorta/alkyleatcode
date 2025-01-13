package com.novelerp.alkyl.dto;

import com.novelerp.core.dto.CommonDto;

public class VendorCredentialReadDto extends CommonDto{
	
	private String vendorCode;
	private String mailID;
	private String status;
	//private String City;
	private String district;	
	private String state;
	private Boolean isVendorCodeFilter = false;
	private Boolean isMailIDFilter = false;
	private Boolean isStatusFilter = false;
	private Boolean isDistrictFilter = false;
	private Boolean isStateFilter = false;
	public String getVendorCode() {
		return vendorCode;
	}
	public String getMailID() {
		return mailID;
	}
	public String getStatus() {
		return status;
	}
	public String getDistrict() {
		return district;
	}
	public String getState() {
		return state;
	}
	public Boolean getIsVendorCodeFilter() {
		return isVendorCodeFilter;
	}
	public Boolean getIsMailIDFilter() {
		return isMailIDFilter;
	}
	public Boolean getIsStatusFilter() {
		return isStatusFilter;
	}
	public Boolean getIsDistrictFilter() {
		return isDistrictFilter;
	}
	public Boolean getIsStateFilter() {
		return isStateFilter;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public void setMailID(String mailID) {
		this.mailID = mailID;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setDistrict(String district) {
		
		this.district = district;
		
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setIsVendorCodeFilter(Boolean isVendorCodeFilter) {
		this.isVendorCodeFilter = isVendorCodeFilter;
	}
	public void setIsMailIDFilter(Boolean isMailIDFilter) {
		this.isMailIDFilter = isMailIDFilter;
	}
	public void setIsStatusFilter(Boolean isStatusFilter) {
		this.isStatusFilter = isStatusFilter;
	}
	public void setIsDistrictFilter(Boolean isDistrictFilter) {
		this.isDistrictFilter = isDistrictFilter;
	}
	public void setIsStateFilter(Boolean isStateFilter) {
		this.isStateFilter = isStateFilter;
	}
	@Override
	public String toString() {
		return "VendorCredentialReadDto [vendorCode=" + vendorCode + ", mailID=" + mailID + ", status=" + status
				+ ", district=" + district + ", state=" + state + ", isVendorCodeFilter=" + isVendorCodeFilter
				+ ", isMailIDFilter=" + isMailIDFilter + ", isStatusFilter=" + isStatusFilter + ", isDistrictFilter="
				+ isDistrictFilter + ", isStateFilter=" + isStateFilter + "]";
	}
	
	

	  


}
