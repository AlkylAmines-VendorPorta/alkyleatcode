package com.novelerp.appbase.master.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.core.util.DateUtil;
/**
 * 
 * @author Aman
 */
public class PartnerOrgLicenseDto extends CommonContextDto {


	private static final long serialVersionUID = 1L;
	private Long partnerOrgLicenceId;
	private String licenceNo;
	
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date licenceValidityDate;
	private AttachmentDto licenceCopy;
	private AttachmentDto machinaryListCopy;
	private PartnerOrgDto partnerOrg;
	
	private String licenseType;
	
	public Long getPartnerOrgLicenceId() {
		return partnerOrgLicenceId;
	}
	public void setPartnerOrgLicenceId(Long partnerOrgLicenceId) {
		this.partnerOrgLicenceId = partnerOrgLicenceId;
	}
	public String getLicenceNo() {
		return licenceNo;
	}
	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}
	public Date getLicenceValidityDate() {
		return licenceValidityDate;
	}
	public void setLicenceValidityDate(Date licenceValidityDate) {
		this.licenceValidityDate = licenceValidityDate;
	}
	public AttachmentDto getLicenceCopy() {
		return licenceCopy;
	}
	public void setLicenceCopy(AttachmentDto licenceCopy) {
		this.licenceCopy = licenceCopy;
	}
	public AttachmentDto getMachinaryListCopy() {
		return machinaryListCopy;
	}
	public void setMachinaryListCopy(AttachmentDto machinaryListCopy) {
		this.machinaryListCopy = machinaryListCopy;
	}
	public PartnerOrgDto getPartnerOrg() {
		return partnerOrg;
	}
	public void setPartnerOrg(PartnerOrgDto partnerOrg) {
		this.partnerOrg = partnerOrg;
	}
	public String getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}
	
}
