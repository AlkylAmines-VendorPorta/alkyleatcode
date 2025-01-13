package com.novelerp.alkyl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="m_bp_approval_matrix")
public class ApprovalMatrix  extends ContextPO{

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
	
	@Id
	@SequenceGenerator(name = "m_approval_matrix_seq", sequenceName = "m_approval_matrix_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_approval_matrix_seq")
	@Column(name = "m_approval_matrix_id", updatable = false)
	public Long getApprovalMatrixId() {
		return approvalMatrixId;
	}
	public void setApprovalMatrixId(Long approvalMatrixId) {
		this.approvalMatrixId = approvalMatrixId;
	}
		
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="designation")
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@Column(name="department")
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Column(name="mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	
	
	
	@Column(name="fax_no")
	public String getFaxNo() {
		return faxNo;
	}
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	
	@Column(name="email_id")
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	@Column(name="company_code")
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	
	@Column(name="purchase_organization")
	public String getPurchaseOrganization() {
		return purchaseOrganization;
	}
	public void setPurchaseOrganization(String purchaseOrganization) {
		this.purchaseOrganization = purchaseOrganization;
	}
	
	@Column(name="vendor_classification")
	public String getVendorClassification() {
		return vendorClassification;
	}
	public void setVendorClassification(String vendorClassification) {
		this.vendorClassification = vendorClassification;
	}
	
	@Column(name="recon_account")
	public String getReconAccount() {
		return reconAccount;
	}
	public void setReconAccount(String reconAccount) {
		this.reconAccount = reconAccount;
	}
	
	@Column(name="vendor_schema_group")
	public String getVendorSchemaGroup() {
		return vendorSchemaGroup;
	}
	public void setVendorSchemaGroup(String vendorSchemaGroup) {
		this.vendorSchemaGroup = vendorSchemaGroup;
	}
	
	@Column(name="purchase_group")
	public String getPurchaseGroup() {
		return purchaseGroup;
	}
	public void setPurchaseGroup(String purchaseGroup) {
		this.purchaseGroup = purchaseGroup;
	}
	
	@Column(name="order_currency")
	public String getOrderCurrency() {
		return orderCurrency;
	}
	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="is_approved_for_critical_items")
	public String getIsApprovedForCriticalItems() {
		return isApprovedForCriticalItems;
	}
	public void setIsApprovedForCriticalItems(String isApprovedForCriticalItems) {
		this.isApprovedForCriticalItems = isApprovedForCriticalItems;
	}
	@Column(name="is_check_double_inv")
	public String getIsCheckDoubleInv() {
		return isCheckDoubleInv;
	}
	public void setIsCheckDoubleInv(String isCheckDoubleInv) {
		this.isCheckDoubleInv = isCheckDoubleInv;
	}
	
	@Column(name="is_payment_terms")
	public String getIsPaymentTerms() {
		return isPaymentTerms;
	}
	public void setIsPaymentTerms(String isPaymentTerms) {
		this.isPaymentTerms = isPaymentTerms;
	}
	@Column(name="is_income_terms")
	public String getIsIncomeTerms() {
		return isIncomeTerms;
	}
	public void setIsIncomeTerms(String isIncomeTerms) {
		this.isIncomeTerms = isIncomeTerms;
	}
	@Column(name="is_vendor_payment_block")
	public String getIsVendorPaymentBlock() {
		return isVendorPaymentBlock;
	}
	public void setIsVendorPaymentBlock(String isVendorPaymentBlock) {
		this.isVendorPaymentBlock = isVendorPaymentBlock;
	}
	@Column(name="is_vendor_overall_block")
	public String getIsVendorOverallBlock() {
		return isVendorOverallBlock;
	}
	public void setIsVendorOverallBlock(String isVendorOverallBlock) {
		this.isVendorOverallBlock = isVendorOverallBlock;
	}
	@Column(name="telephone_no")
	public String getTelephoneNo() {
		return telephoneNo;
	}
	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}
	@Column(name="inco_terms")
	public String getVendorIncoTerms() {
		return vendorIncoTerms;
	}
	public void setVendorIncoTerms(String vendorIncoTerms) {
		this.vendorIncoTerms = vendorIncoTerms;
	}
	@Column(name="payment_terms")
	public String getVendorPaymentTerms() {
		return vendorPaymentTerms;
	}
	public void setVendorPaymentTerms(String vendorPaymentTerms) {
		this.vendorPaymentTerms = vendorPaymentTerms;
	}
	@Column(name="inco_desciption")
	public String getVendorIncoDescription() {
		return vendorIncoDescription;
	}
	public void setVendorIncoDescription(String vendorIncoDescription) {
		this.vendorIncoDescription = vendorIncoDescription;
	}
	
	@Column(name="industry")
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	@Column(name="vendor_code")
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	

}
