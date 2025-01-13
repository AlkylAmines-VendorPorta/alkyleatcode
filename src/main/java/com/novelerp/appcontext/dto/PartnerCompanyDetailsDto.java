package com.novelerp.appcontext.dto;

/**
 * 
 * @author Aman
 */
public class PartnerCompanyDetailsDto extends CommonContextDto {

	private static final long serialVersionUID = 1L;
	private Long partnerCompanyDetailsId;
	private String companyName;
	private String companyType;
	private String crnNo;
	private String gstIdentificationNo;
	private String gstInApplicableType;
	private String contractorType;
	private String licenseNo;

	
	public Long getPartnerCompanyDetailsId() {
		return partnerCompanyDetailsId;
	}

	public void setPartnerCompanyDetailsId(Long partnerCompanyDetailsId) {
		this.partnerCompanyDetailsId = partnerCompanyDetailsId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCrnNo() {
		return crnNo;
	}

	public void setCrnNo(String crnNo) {
		this.crnNo = crnNo;
	}
	public String getGstIdentificationNo() {
		return gstIdentificationNo;
	}

	public void setGstIdentificationNo(String gstIdentificationNo) {
		this.gstIdentificationNo = gstIdentificationNo;
	}

	public String getGstInApplicableType() {
		return gstInApplicableType;
	}

	public void setGstInApplicableType(String gstInApplicableType) {
		this.gstInApplicableType = gstInApplicableType;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
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
	
}
