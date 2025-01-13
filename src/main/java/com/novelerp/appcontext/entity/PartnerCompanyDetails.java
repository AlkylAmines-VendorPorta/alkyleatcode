package com.novelerp.appcontext.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*
 * CreatedBy Aman
 */
@Entity
@Table(name="m_bp_company_details")
public class PartnerCompanyDetails extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long partnerCompanyDetailsId;
	private String companyName;
	private String companyType;
	private String crnNo;
	private String GstIdentificationNo;
	private String GstinApplicableType;
	private String contractorType;
	private String licenseNo;
	
	@Id
	@SequenceGenerator(name="m_bp_company_details_seq",sequenceName="m_bp_company_details_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_company_details_seq")	
	@Column(name = "m_bp_company_details_id", updatable=false)
	public Long getPartnerCompanyDetailsId() {
		return partnerCompanyDetailsId;
	}
	public void setPartnerCompanyDetailsId(Long partnerCompanyDetailsId) {
		this.partnerCompanyDetailsId = partnerCompanyDetailsId;
	}
	@Column(name = "name")
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Column(name = "crn_no")
	public String getCrnNo() {
		return crnNo;
	}
	public void setCrnNo(String crnNo) {
		this.crnNo = crnNo;
	}
	@Column(name = "gst_No")
	public String getGstIdentificationNo() {
		return GstIdentificationNo;
	}
	public void setGstIdentificationNo(String gstIdentificationNo) {
		GstIdentificationNo = gstIdentificationNo;
	}
	@Column(name = "gstin_applicable_type")
	public String getGstinApplicableType() {
		return GstinApplicableType;
	}
	public void setGstinApplicableType(String gstinApplicableType) {
		GstinApplicableType = gstinApplicableType;
	}
	@Column(name = "licence_no")
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	@Column(name = "company_type")
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	@Column(name="ccontractor_type")
	public String getContractorType() {
		return contractorType;
	}
	public void setContractorType(String contractorType) {
		this.contractorType = contractorType;
	}
	
}
