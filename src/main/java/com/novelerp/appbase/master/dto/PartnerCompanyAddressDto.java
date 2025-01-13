package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.LocationDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.commons.util.CommonUtil;
/**
 * 
 * @author Aman
 */
public class PartnerCompanyAddressDto extends CommonContextDto{

	private static final long serialVersionUID = 1L;
	private Long partnerCompanyAddressId;
	private String registeredAddress;
	private LocationDto location;
	private String isShipToAddress;
	private String isBillToAddress;
	private String locationType;
	private PartnerCompanyAddressDto officeLocation;
	private String remark;
	private String isApproved;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String nameOfCompany;
	private String isPrimaryAccount;
	private UserDto user;
	
	public Long getPartnerCompanyAddressId() {
		return partnerCompanyAddressId;
	}
	public void setPartnerCompanyAddressId(Long partnerCompanyAddressId) {
		this.partnerCompanyAddressId = partnerCompanyAddressId;
	}
	public String getRegisteredAddress() {
		return registeredAddress;
	}
	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}
	public LocationDto getLocation() {
		return location;
	}
	public void setLocation(LocationDto location) {
		this.location = location;
	}
	
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public PartnerCompanyAddressDto getOfficeLocation() {
		return officeLocation;
	}
	public void setOfficeLocation(PartnerCompanyAddressDto officeLocation) {
		this.officeLocation = officeLocation;
	}
	public String getIsShipToAddress() {
		if(CommonUtil.isStringEmpty(isShipToAddress)){
			return "N";
		}
		return isShipToAddress;
	}
	public void setIsShipToAddress(String isShipToAddress) {
		this.isShipToAddress = isShipToAddress;
	}
	public String getIsBillToAddress() {
		if(CommonUtil.isStringEmpty(isBillToAddress)){
			return "N";
		}
		return isBillToAddress;
	}
	public void setIsBillToAddress(String isBillToAddress) {
		this.isBillToAddress = isBillToAddress;
	}
	public String getIsEEApproved() {
		return isEEApproved;
	}
	public void setIsEEApproved(String isEEApproved) {
		this.isEEApproved = isEEApproved;
	}
	public String getEeComment() {
		return eeComment;
	}
	public void setEeComment(String eeComment) {
		this.eeComment = eeComment;
	}
	public String getIsCEApproved() {
		return isCEApproved;
	}
	public void setIsCEApproved(String isCEApproved) {
		this.isCEApproved = isCEApproved;
	}
	public String getCeComment() {
		return ceComment;
	}
	public void setCeComment(String ceComment) {
		this.ceComment = ceComment;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	public String getNameOfCompany() {
		return nameOfCompany;
	}
	public void setNameOfCompany(String nameOfCompany) {
		this.nameOfCompany = nameOfCompany;
	}
	public String getIsPrimaryAccount() {
		return isPrimaryAccount;
	}
	public void setIsPrimaryAccount(String isPrimaryAccount) {
		this.isPrimaryAccount = isPrimaryAccount;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	
}
