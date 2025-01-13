package com.novelerp.appbase.master.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.core.util.DateUtil;

/**
 * Partner Organisation's religion development authority eligibility details.
 * @author Vivek Birdi
 *
 */
public class PartnerOrgRDAECDto extends CommonContextDto {

	private static final long serialVersionUID = 1L;
	private Long partnerOrgRDAECId;
	private String isApplicable;
	/*private EligibilityCertificateType eligibilityCertificateType;
    private DevelopingRegion developingRegion;*/
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date validFrom;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date validTo;
    private String elegibilityType;
    private String developingRegion;
    private String isPioneer;
    private PartnerOrgDto partnerOrg;
	private AttachmentDto eligibilityCertificate;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String remark;
	private String isApproved;
	public Long getPartnerOrgRDAECId() {
		return partnerOrgRDAECId;
	}
	public void setPartnerOrgRDAECId(Long partnerOrgRDAECId) {
		this.partnerOrgRDAECId = partnerOrgRDAECId;
	}
	public String getIsApplicable() {
		return isApplicable;
	}
	public void setIsApplicable(String isApplicable) {
		this.isApplicable = isApplicable;
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
	public String getElegibilityType() {
		return elegibilityType;
	}
	public void setElegibilityType(String elegibilityType) {
		this.elegibilityType = elegibilityType;
	}
	public String getDevelopingRegion() {
		return developingRegion;
	}
	public void setDevelopingRegion(String developingRegion) {
		this.developingRegion = developingRegion;
	}
	public String getIsPioneer() {
		return isPioneer;
	}
	public void setIsPioneer(String isPioneer) {
		this.isPioneer = isPioneer;
	}
	public PartnerOrgDto getPartnerOrg() {
		return partnerOrg;
	}
	public void setPartnerOrg(PartnerOrgDto partnerOrg) {
		this.partnerOrg = partnerOrg;
	}
	public AttachmentDto getEligibilityCertificate() {
		return eligibilityCertificate;
	}
	public void setEligibilityCertificate(AttachmentDto eligibilityCertificate) {
		this.eligibilityCertificate = eligibilityCertificate;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	
	
	
}
