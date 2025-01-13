package com.novelerp.appbase.master.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.core.util.DateUtil;

/**
 * Partner's other eligibility details
 * @author Vivek Birdi
 *
 */
public class PartnerOrgOEDto extends CommonContextDto{

	private static final long serialVersionUID = 1L;
	private Long partnerOrgOEId;
	private String authority;
	private String RegsNo;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date validFrom;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date validTo;
	private String oeType;
	private PartnerOrgDto partnerOrg;
	private String isApproved;
	private String remark;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String validityType;
	private AttachmentDto eligibilityCertificte;
	private String isNotApplicable;
	
	public Long getPartnerOrgOEId() {
		return partnerOrgOEId;
	}
	public void setPartnerOrgOEId(Long partnerOrgOEId) {
		this.partnerOrgOEId = partnerOrgOEId;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getRegsNo() {
		return RegsNo;
	}
	public void setRegsNo(String regsNo) {
		RegsNo = regsNo;
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
	public String getOeType() {
		return oeType;
	}
	public void setOeType(String oeType) {
		this.oeType = oeType;
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
	public String getValidityType() {
		return validityType;
	}
	public void setValidityType(String validityType) {
		this.validityType = validityType;
	}
	public AttachmentDto getEligibilityCertificte() {
		return eligibilityCertificte;
	}
	public void setEligibilityCertificte(AttachmentDto eligibilityCertificte) {
		this.eligibilityCertificte = eligibilityCertificte;
	}
	public String getIsNotApplicable() {
		if(!"Y".equals(isNotApplicable)){
			isNotApplicable="N";
		}
		return isNotApplicable;
	}
	public void setIsNotApplicable(String isNotApplicable) {
		this.isNotApplicable = isNotApplicable;
	}
	
}
