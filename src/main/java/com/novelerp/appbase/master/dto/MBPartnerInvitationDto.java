package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.CommonContextDto;

public class MBPartnerInvitationDto extends CommonContextDto {
	
	private static final long serialVersionUID = 1L;
	
	private Long mBPartnerInvitationId;
	private String vendorEmailId;
	private String companyName;
	private String firstName;
	private String lastName;
	private String mobileNo;
	private String vendorPancardNo;
	private String isInvitationSend;
	private String isInvitationApproved;
	private BPartnerDto requestedPartner;
	
	public Long getmBPartnerInvitationId() {
		return mBPartnerInvitationId;
	}
	public void setmBPartnerInvitationId(Long mBPartnerInvitationId) {
		this.mBPartnerInvitationId = mBPartnerInvitationId;
	}
	
	public String getVendorEmailId() {
		return vendorEmailId;
	}
	public void setVendorEmailId(String vendorEmailId) {
		this.vendorEmailId = vendorEmailId;
	}
	public String getVendorPancardNo() {
		return vendorPancardNo;
	}
	public void setVendorPancardNo(String vendorPancardNo) {
		this.vendorPancardNo = vendorPancardNo;
	}
	public String getIsInvitationSend() {
		return isInvitationSend;
	}
	public void setIsInvitationSend(String isInvitationSend) {
		this.isInvitationSend = isInvitationSend;
	}
	public String getIsInvitationApproved() {
		return isInvitationApproved;
	}
	public void setIsInvitationApproved(String isInvitationApproved) {
		this.isInvitationApproved = isInvitationApproved;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public BPartnerDto getRequestedPartner() {
		return requestedPartner;
	}
	public void setRequestedPartner(BPartnerDto requestedPartner) {
		this.requestedPartner = requestedPartner;
	}

}
