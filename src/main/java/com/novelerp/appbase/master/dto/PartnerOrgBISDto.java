package com.novelerp.appbase.master.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.util.DateUtil;

/**
 * 
 * @author Aman
 */

public class PartnerOrgBISDto extends CommonContextDto {

	private static final long serialVersionUID = -8017645578886819424L;
	private Long partnerOrgBisId;
	private String bisLicenceNo;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date validFrom;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date validTo;
	private AttachmentDto bisLicenceCertificate;
	private PartnerOrgDto partnerOrg;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String isNotApplicable;
	
	public Long getPartnerOrgBisId() {
		return partnerOrgBisId;
	}
	public void setPartnerOrgBisId(Long partnerOrgBisId) {
		this.partnerOrgBisId = partnerOrgBisId;
	}
	public String getBisLicenceNo() {
		return bisLicenceNo;
	}
	public void setBisLicenceNo(String bisLicenceNo) {
		this.bisLicenceNo = bisLicenceNo;
	}
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	public Date getValidTo() {
		return validTo;
	}
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
	public AttachmentDto getBisLicenceCertificate() {
		return bisLicenceCertificate;
	}
	public void setBisLicenceCertificate(AttachmentDto bisLicenceCertificate) {
		this.bisLicenceCertificate = bisLicenceCertificate;
	}
	public PartnerOrgDto getPartnerOrg() {
		return partnerOrg;
	}
	public void setPartnerOrg(PartnerOrgDto partnerOrg) {
		this.partnerOrg = partnerOrg;
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
	public String getIsNotApplicable() {
		if(CommonUtil.isStringEmpty(isNotApplicable)){
			return "N";
		}
		return isNotApplicable;
	}
	public void setIsNotApplicable(String isNotApplicable) {
		this.isNotApplicable = isNotApplicable;
	}
	
}
