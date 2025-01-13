package com.novelerp.appcontext.dto;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.dto.LocationTypeDto;
import com.novelerp.appbase.master.dto.PartnerCompanyAddressDto;
import com.novelerp.appbase.master.entity.PartnerCompanyAddress;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.core.dto.CommonDto;

public class BPartnerDto extends CommonDto {

	private static final long serialVersionUID = -2438120528126842389L;

	private Date requestedDate;
	private String requestToName;
	private Long bPartnerId;
	private String value;
	private String name;
	private String lastName;
	private String description;
	private String isVendor;
	private String isCustomer;
	private String isEmployee;
	private String isSalesRep;
	private String url;
	private int image;
	private UserDto createdBy;
	private UserDto updatedBy;
	private String crnNumber;
	private String panNumber;
	private PartnerCompanyDetailsDto partnerCompany;

	private String companyType;
	private String contractorType;
	private String isGstApplicable;
	private String gstinNo;
	private String isContractor;
	private String isManufacturer;
	private String isTrader;
	private String hasAccepted;
	private String priority;
	private String status;
	private String isApproved;
	private Timestamp validDate;
	private String remarks;
	private String isProfileUpdated;
	private AttachmentDto panCardCopy;
	private AttachmentDto gstinCopy;
	private AttachmentDto companyRegCertificate;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private Timestamp validTo;
	private AttachmentDto ceSignCopy;
	private AttachmentDto partnerSignCopy;
	private AttachmentDto partnerCoSignCopy;
	private String registrationType;
	private String isEECompDetailApproved;
	private String isCECompDetailApproved;
	private String eeCompDetailComment;
	private String ceCompDetailComment;
	private AttachmentDto partnershipDEEDCopy;
	private String isPartnerDeleted;
	private Double rating;
	private Long rateCount;
	private String isRenewed;
	private String isTraderRenewed;
	private String isManufactureRenewed;
	private String isRegCompleted;
	private UserDto ce_approveBy;

    private UserDto ee_approveBy;
    private String vendorSapCode;
    private OfficeLocationDto officeLocation;
    private LocationTypeDto officeType;
    private String isTraderApproved;
    private Date traderValidTo;
    private String blacklistingReason;
    private String isInfra;
    private String isSetToFinalApproval;
    private String createAndInvite;
    private String vendorType;
//    private Set<PartnerCompanyAddressDto> partnerCompanyAddress;
//    private UserDto user;
    
	public Date getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}

	public String getRequestToName() {
		return requestToName;
	}

	public void setRequestToName(String requestToName) {
		this.requestToName = requestToName;
	}

	public Long getbPartnerId() {
		return bPartnerId;
	}

	public void setbPartnerId(Long bPartnerId) {
		this.bPartnerId = bPartnerId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsSalesRep() {
		return isSalesRep;
	}

	public void setIsSalesRep(String isSalesRep) {
		this.isSalesRep = isSalesRep;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public String getIsVendor() {
		if (!"Y".equals(isVendor)) {
			isVendor = "N";
		}
		return isVendor;
	}

	public void setIsVendor(String isVendor) {
		this.isVendor = isVendor;
	}

	public String getIsCustomer() {
		if (!"Y".equals(isCustomer)) {
			isCustomer = "N";
		}

		return isCustomer;
	}

	public void setIsCustomer(String isCustomer) {
		this.isCustomer = isCustomer;
	}

	public String getIsEmployee() {
		if (!"Y".equals(isEmployee)) {
			isEmployee = "N";
		}

		return isEmployee;
	}

	public void setIsEmployee(String isEmployee) {
		this.isEmployee = isEmployee;
	}

	public UserDto getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserDto createdBy) {
		this.createdBy = createdBy;
	}

	public UserDto getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(UserDto updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getCrnNumber() {
		return crnNumber;
	}

	public void setCrnNumber(String crnNumber) {
		this.crnNumber = crnNumber;
	}

	public String getPanNumber() {
		return panNumber == null ? null : panNumber.toUpperCase();
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public PartnerCompanyDetailsDto getPartnerCompany() {
		return partnerCompany;
	}

	public void setPartnerCompany(PartnerCompanyDetailsDto partnerCompany) {
		this.partnerCompany = partnerCompany;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getContractorType() {
		return contractorType;
	}

	public void setContractorType(String contractorType) {
		this.contractorType = contractorType;
	}

	public String getIsGstApplicable() {
		
		
//		if (isGstApplicable==null) {
//			isGstApplicable = "N";
//		}
//
//		return isGstApplicable;
		
		if (isGstApplicable==null || !"Y".equals(isGstApplicable)) {
			isGstApplicable = "N";
		}

		return isGstApplicable;
	}

	public void setIsGstApplicable(String isGstApplicable) {
		this.isGstApplicable = isGstApplicable;
	}

	public String getGstinNo() {
		return gstinNo;
	}

	public void setGstinNo(String gstinNo) {
		this.gstinNo = gstinNo;
	}

	public String getIsContractor() {
		if (!"Y".equals(isContractor)) {
			isContractor = "N";
		}

		return isContractor;
	}

	public void setIsContractor(String isContractor) {
		this.isContractor = isContractor;
	}

	public String getIsManufacturer() {
		if (!("Y".equals(isManufacturer))) {
			isManufacturer = "N";
		}
		return isManufacturer;
	}

	public void setIsManufacturer(String isManufacturer) {
		this.isManufacturer = isManufacturer;
	}

	public String getIsTrader() {
		if (!("Y".equals(isTrader))) {
			isTrader = "N";
		}
		return isTrader;
	}

	public void setIsTrader(String isTrader) {
		this.isTrader = isTrader;
	}

	/*
	 * public boolean isHasAccepted() { return hasAccepted; }
	 * 
	 * public void setHasAccepted(boolean hasAccepted) { this.hasAccepted =
	 * hasAccepted; }
	 */

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getTenderPriority() {

		if (("Y".equals(isManufacturer) || "Y".equals(isTrader)) && "Y".equals(isContractor)) {
			priority = "WT";
		} else if ("Y".equals(isManufacturer) || "Y".equals(isTrader)) {
			priority = "PT";
		} else if ("Y".equals(isContractor)) {
			priority = "WT";
		}
		return priority;
	}

	public String getAuctionPriority() {

		if ("Y".equals(isCustomer)) {
			priority = "FA";
		}
		if ("Y".equals(isContractor)) {
			priority = "RA";
		}
		return priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean checkIsValidTypeCode(String typeCode) {
		boolean check = false;
		if (("Y".equals(isManufacturer) || "Y".equals(isTrader)) && ("RA".equals(typeCode) || "PT".equals(typeCode))) {
			check = true;
		} else if ("Y".equals(isCustomer) && "FA".equals(typeCode)) {
			check = true;
		} else if ("Y".equals(isContractor) && "WT".equals(typeCode)) {
			check = true;
		} else if (typeCode.equals("RFQ") || typeCode.equals("QRFQ")) {
			check = true;
		}
		return check;
	}

	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	public Timestamp getValidDate() {
		return validDate;
	}

	public void setValidDate(Timestamp validDate) {
		this.validDate = validDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public AttachmentDto getPanCardCopy() {
		return panCardCopy;
	}

	public void setPanCardCopy(AttachmentDto panCardCopy) {
		this.panCardCopy = panCardCopy;
	}

	public AttachmentDto getGstinCopy() {
		return gstinCopy;
	}

	public void setGstinCopy(AttachmentDto gstinCopy) {
		this.gstinCopy = gstinCopy;
	}

	public AttachmentDto getCompanyRegCertificate() {
		return companyRegCertificate;
	}

	public void setCompanyRegCertificate(AttachmentDto companyRegCertificate) {
		this.companyRegCertificate = companyRegCertificate;
	}

	public String getPriority() {
		return priority;
	}

	public String getIsProfileUpdated() {
		return isProfileUpdated;
	}

	public void setIsProfileUpdated(String isProfileUpdated) {
		this.isProfileUpdated = isProfileUpdated;
	}

	public String getHasAccepted() {
		return hasAccepted;
	}

	public void setHasAccepted(String hasAccepted) {
		this.hasAccepted = hasAccepted;
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

	public Timestamp getValidTo() {
		return validTo;
	}

	public void setValidTo(Timestamp validTo) {
		this.validTo = validTo;
	}

	public boolean isIntraState() {
		if (gstinNo != null) {
			return gstinNo.startsWith(ContextConstant.GST_STATE_CODE_MAHARASHTRA);
		} else {
			return false;
		}

	}

	public AttachmentDto getCeSignCopy() {
		return ceSignCopy;
	}

	public void setCeSignCopy(AttachmentDto ceSignCopy) {
		this.ceSignCopy = ceSignCopy;
	}

	public AttachmentDto getPartnerSignCopy() {
		return partnerSignCopy;
	}

	public void setPartnerSignCopy(AttachmentDto partnerSignCopy) {
		this.partnerSignCopy = partnerSignCopy;
	}

	public AttachmentDto getPartnerCoSignCopy() {
		return partnerCoSignCopy;
	}

	public void setPartnerCoSignCopy(AttachmentDto partnerCoSignCopy) {
		this.partnerCoSignCopy = partnerCoSignCopy;
	}

	public String getRegistrationType() {
		return registrationType;
	}

	public void setRegistrationType(String registrationType) {
		this.registrationType = registrationType;
	}

	public String getIsEECompDetailApproved() {
		return isEECompDetailApproved;
	}

	public void setIsEECompDetailApproved(String isEECompDetailApproved) {
		this.isEECompDetailApproved = isEECompDetailApproved;
	}

	public String getIsCECompDetailApproved() {
		return isCECompDetailApproved;
	}

	public void setIsCECompDetailApproved(String isCECompDetailApproved) {
		this.isCECompDetailApproved = isCECompDetailApproved;
	}

	public String getEeCompDetailComment() {
		return eeCompDetailComment;
	}

	public void setEeCompDetailComment(String eeCompDetailComment) {
		this.eeCompDetailComment = eeCompDetailComment;
	}

	public String getCeCompDetailComment() {
		return ceCompDetailComment;
	}

	public void setCeCompDetailComment(String ceCompDetailComment) {
		this.ceCompDetailComment = ceCompDetailComment;
	}

	public AttachmentDto getPartnershipDEEDCopy() {
		return partnershipDEEDCopy;
	}

	public void setPartnershipDEEDCopy(AttachmentDto partnershipDEEDCopy) {
		this.partnershipDEEDCopy = partnershipDEEDCopy;
	}

	public String getIsPartnerDeleted() {
		return isPartnerDeleted;
	}

	public void setIsPartnerDeleted(String isPartnerDeleted) {
		this.isPartnerDeleted = isPartnerDeleted;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Long getRateCount() {
		return rateCount;
	}

	public void setRateCount(Long rateCount) {
		this.rateCount = rateCount;
	}

	public String getIsRenewed() {
		return isRenewed;
	}

	public void setIsRenewed(String isRenewed) {
		this.isRenewed = isRenewed;
	}

	public String getIsTraderRenewed() {
		return isTraderRenewed;
	}

	public void setIsTraderRenewed(String isTraderRenewed) {
		this.isTraderRenewed = isTraderRenewed;
	}

	public String getIsManufactureRenewed() {
		return isManufactureRenewed;
	}

	public void setIsManufactureRenewed(String isManufactureRenewed) {
		this.isManufactureRenewed = isManufactureRenewed;
	}

	public String getIsRegCompleted() {
		return isRegCompleted;
	}

	public void setIsRegCompleted(String isRegCompleted) {
		this.isRegCompleted = isRegCompleted;
	}

	public UserDto getCe_approveBy() {
		return ce_approveBy;
	}

	public void setCe_approveBy(UserDto ce_approveBy) {
		this.ce_approveBy = ce_approveBy;
	}

	public UserDto getEe_approveBy() {
		return ee_approveBy;
	}

	public void setEe_approveBy(UserDto ee_approveBy) {
		this.ee_approveBy = ee_approveBy;
	}

	public String getVendorSapCode() {
		return vendorSapCode;
	}

	public void setVendorSapCode(String vendorSapCode) {
		this.vendorSapCode = vendorSapCode;
	}

	public OfficeLocationDto getOfficeLocation() {
		return officeLocation;
	}

	public void setOfficeLocation(OfficeLocationDto officeLocation) {
		this.officeLocation = officeLocation;
	}

	public LocationTypeDto getOfficeType() {
		return officeType;
	}

	public void setOfficeType(LocationTypeDto officeType) {
		this.officeType = officeType;
	}

	public String getIsTraderApproved() {
		return isTraderApproved;
	}

	public void setIsTraderApproved(String isTraderApproved) {
		this.isTraderApproved = isTraderApproved;
	}

	public Date getTraderValidTo() {
		return traderValidTo;
	}

	public void setTraderValidTo(Date traderValidTo) {
		this.traderValidTo = traderValidTo;
	}

	public String getBlacklistingReason() {
		return blacklistingReason;
	}

	public void setBlacklistingReason(String blacklistingReason) {
		this.blacklistingReason = blacklistingReason;
	}

	public String getIsInfra() {
		if (!"Y".equals(isInfra)) {
			isInfra = "N";
		}
		return isInfra;
	}

	public void setIsInfra(String isInfra) {
		this.isInfra = isInfra;
	}

	public String getIsSetToFinalApproval() {
		return isSetToFinalApproval;
	}

	public void setIsSetToFinalApproval(String isSetToFinalApproval) {
		this.isSetToFinalApproval = isSetToFinalApproval;
	}

	public String getCreateAndInvite() {
		return createAndInvite;
	}

	public void setCreateAndInvite(String createAndInvite) {
		this.createAndInvite = createAndInvite;
	}

	public String getVendorType() {
		return vendorType;
	}

	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}

//	public Set<PartnerCompanyAddressDto> getPartnerCompanyAddress() {
//		return partnerCompanyAddress;
//	}
//
//	public void setPartnerCompanyAddress(Set<PartnerCompanyAddressDto> partnerCompanyAddress) {
//		this.partnerCompanyAddress = partnerCompanyAddress;
//	}

//	public UserDto getUser() {
//		return user;
//	}
//
//	public void setUser(UserDto user) {
//		this.user = user;
//	}
//
//	




	

	


	
}
