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
public class PartnerOrgCertificationDto extends CommonContextDto {

	private static final long serialVersionUID = 1L;
	private Long partnerOrgCertificateId;
	private String isoName;
	private String isoCertifyingAuthority;
	private String isoCertificationNo;
	@DateTimeFormat(pattern = DateUtil.DEFAULT_DATE_FORMAT)
	private Date isoValidityDate;
	private String isNotApplicable;
	private PartnerOrgDto partnerOrg;
	private String isApproved;
	private String remark;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private AttachmentDto certificateCopy;
	public Long getPartnerOrgCertificateId() {
		return partnerOrgCertificateId;
	}

	public void setPartnerOrgCertificateId(Long partnerOrgCertificateId) {
		this.partnerOrgCertificateId = partnerOrgCertificateId;
	}

	public String getIsoName() {
		return isoName;
	}

	public void setIsoName(String isoName) {
		this.isoName = isoName;
	}

	public String getIsoCertifyingAuthority() {
		return isoCertifyingAuthority;
	}

	public void setIsoCertifyingAuthority(String isoCertifyingAuthority) {
		this.isoCertifyingAuthority = isoCertifyingAuthority;
	}

	public String getIsoCertificationNo() {
		return isoCertificationNo;
	}

	public void setIsoCertificationNo(String isoCertificationNo) {
		this.isoCertificationNo = isoCertificationNo;
	}

	public Date getIsoValidityDate() {
		return isoValidityDate;
	}

	public void setIsoValidityDate(Date isoValidityDate) {
		this.isoValidityDate = isoValidityDate;
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

	public PartnerOrgDto getPartnerOrg() {
		return partnerOrg;
	}

	public void setPartnerOrg(PartnerOrgDto partnerOrg) {
		this.partnerOrg = partnerOrg;
	}

	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public AttachmentDto getCertificateCopy() {
		return certificateCopy;
	}

	public void setCertificateCopy(AttachmentDto certificateCopy) {
		this.certificateCopy = certificateCopy;
	}

}
