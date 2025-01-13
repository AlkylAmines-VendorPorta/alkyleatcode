package com.novelerp.alkyl.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class ApprovalMatrixDto extends CommonContextDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long approvalMatrixId;
	private String title;
	private String name;
	private String designation;
	private String department;
	private String mobileNo;
	private String telephoneNo;
	private String faxNo;
	private String emailId;
	private String companyCode;
	private String purchaseOrganization;
	private String vendorClassification;
	private String reconAccount;
	private String vendorSchemaGroup;
	private String purchaseGroup;
	private String orderCurrency;
	private String description;
	private String isApprovedForCriticalItems;
	private String isCheckDoubleInv;
	private String isPaymentTerms;
	private String isIncomeTerms;
	private String isVendorPaymentBlock;
	private String isVendorOverallBlock;
	private String vendorIncoTerms;
	private String vendorPaymentTerms;
	private String vendorIncoDescription;
	private String industry;
	private String vendorCode;
	
	
	public String getIsApprovedForCriticalItems() {
		return isApprovedForCriticalItems;
	}
	public void setIsApprovedForCriticalItems(String isApprovedForCriticalItems) {
		this.isApprovedForCriticalItems = isApprovedForCriticalItems;
	}
	public String getIsCheckDoubleInv() {
		return isCheckDoubleInv;
	}
	public void setIsCheckDoubleInv(String isCheckDoubleInv) {
		this.isCheckDoubleInv = isCheckDoubleInv;
	}
	public String getIsPaymentTerms() {
		return isPaymentTerms;
	}
	public void setIsPaymentTerms(String isPaymentTerms) {
		this.isPaymentTerms = isPaymentTerms;
	}
	public String getIsIncomeTerms() {
		return isIncomeTerms;
	}
	public void setIsIncomeTerms(String isIncomeTerms) {
		this.isIncomeTerms = isIncomeTerms;
	}
	public String getIsVendorPaymentBlock() {
		return isVendorPaymentBlock;
	}
	public void setIsVendorPaymentBlock(String isVendorPaymentBlock) {
		this.isVendorPaymentBlock = isVendorPaymentBlock;
	}
	public String getIsVendorOverallBlock() {
		return isVendorOverallBlock;
	}
	public void setIsVendorOverallBlock(String isVendorOverallBlock) {
		this.isVendorOverallBlock = isVendorOverallBlock;
	}
	
	public Long getApprovalMatrixId() {
		return approvalMatrixId;
	}
	public void setApprovalMatrixId(Long approvalMatrixId) {
		this.approvalMatrixId = approvalMatrixId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public String getFaxNo() {
		return faxNo;
	}
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getPurchaseOrganization() {
		return purchaseOrganization;
	}
	public void setPurchaseOrganization(String purchaseOrganization) {
		this.purchaseOrganization = purchaseOrganization;
	}
	public String getVendorClassification() {
		return vendorClassification;
	}
	public void setVendorClassification(String vendorClassification) {
		this.vendorClassification = vendorClassification;
	}
	public String getReconAccount() {
		return reconAccount;
	}
	public void setReconAccount(String reconAccount) {
		this.reconAccount = reconAccount;
	}
	
	public String getPurchaseGroup() {
		return purchaseGroup;
	}
	public void setPurchaseGroup(String purchaseGroup) {
		this.purchaseGroup = purchaseGroup;
	}
	public String getOrderCurrency() {
		return orderCurrency;
	}
	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVendorSchemaGroup() {
		return vendorSchemaGroup;
	}
	public void setVendorSchemaGroup(String vendorSchemaGroup) {
		this.vendorSchemaGroup = vendorSchemaGroup;
	}
	public String getTelephoneNo() {
		return telephoneNo;
	}
	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}
	public String getVendorIncoTerms() {
		return vendorIncoTerms;
	}
	public void setVendorIncoTerms(String vendorIncoTerms) {
		this.vendorIncoTerms = vendorIncoTerms;
	}
	public String getVendorPaymentTerms() {
		return vendorPaymentTerms;
	}
	public void setVendorPaymentTerms(String vendorPaymentTerms) {
		this.vendorPaymentTerms = vendorPaymentTerms;
	}
	public String getVendorIncoDescription() {
		return vendorIncoDescription;
	}
	public void setVendorIncoDescription(String vendorIncoDescription) {
		this.vendorIncoDescription = vendorIncoDescription;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	@Override
	public String toString() {
		return "ApprovalMatrixDto [approvalMatrixId=" + approvalMatrixId + ", title=" + title + ", name=" + name
				+ ", designation=" + designation + ", department=" + department + ", mobileNo=" + mobileNo
				+ ", telephoneNo=" + telephoneNo + ", faxNo=" + faxNo + ", emailId=" + emailId + ", companyCode="
				+ companyCode + ", purchaseOrganization=" + purchaseOrganization + ", vendorClassification="
				+ vendorClassification + ", reconAccount=" + reconAccount + ", vendorSchemaGroup=" + vendorSchemaGroup
				+ ", purchaseGroup=" + purchaseGroup + ", orderCurrency=" + orderCurrency + ", description="
				+ description + ", isApprovedForCriticalItems=" + isApprovedForCriticalItems + ", isCheckDoubleInv="
				+ isCheckDoubleInv + ", isPaymentTerms=" + isPaymentTerms + ", isIncomeTerms=" + isIncomeTerms
				+ ", isVendorPaymentBlock=" + isVendorPaymentBlock + ", isVendorOverallBlock=" + isVendorOverallBlock
				+ ", vendorIncoTerms=" + vendorIncoTerms + ", vendorPaymentTerms=" + vendorPaymentTerms
				+ ", vendorIncoDescription=" + vendorIncoDescription + ", industry=" + industry + ", vendorCode="
				+ vendorCode + "]";
	}
		
	
}
