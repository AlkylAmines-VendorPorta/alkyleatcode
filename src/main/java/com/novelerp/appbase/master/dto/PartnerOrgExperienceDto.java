package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;
/**
 * 
 * @author Aman
 */
public class PartnerOrgExperienceDto extends CommonContextDto{

	private static final long serialVersionUID = 1L;
	private Long partnerOrgExperienceId;
	private Short expManufacturingYear;
	private Short expManufacturingMonths; 
	private Short expDesignYear;
	private Short expDesignMonths; 
	private Short expTestingYear;
	private Short expTestingMonths; 
	private Short expSupplyingYear;
	private Short expSupplyingMonths;
	private PartnerOrgDto partnerOrg;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String remark;
	private String isApproved;
	
	public Long getPartnerOrgExperienceId() {
		return partnerOrgExperienceId;
	}
	public void setPartnerOrgExperienceId(Long partnerOrgExperienceId) {
		this.partnerOrgExperienceId = partnerOrgExperienceId;
	}
	public Short getExpManufacturingYear() {
		return expManufacturingYear;
	}
	public void setExpManufacturingYear(Short expManufacturingYear) {
		this.expManufacturingYear = expManufacturingYear;
	}
	public Short getExpManufacturingMonths() {
		return expManufacturingMonths;
	}
	public void setExpManufacturingMonths(Short expManufacturingMonths) {
		this.expManufacturingMonths = expManufacturingMonths;
	}
	public Short getExpDesignYear() {
		return expDesignYear;
	}
	public void setExpDesignYear(Short expDesignYear) {
		this.expDesignYear = expDesignYear;
	}
	public Short getExpDesignMonths() {
		return expDesignMonths;
	}
	public void setExpDesignMonths(Short expDesignMonths) {
		this.expDesignMonths = expDesignMonths;
	}
	public Short getExpTestingYear() {
		return expTestingYear;
	}
	public void setExpTestingYear(Short expTestingYear) {
		this.expTestingYear = expTestingYear;
	}
	public Short getExpTestingMonths() {
		return expTestingMonths;
	}
	public void setExpTestingMonths(Short expTestingMonths) {
		this.expTestingMonths = expTestingMonths;
	}
	public Short getExpSupplyingYear() {
		return expSupplyingYear;
	}
	public void setExpSupplyingYear(Short expSupplyingYear) {
		this.expSupplyingYear = expSupplyingYear;
	}
	public Short getExpSupplyingMonths() {
		return expSupplyingMonths;
	}
	public void setExpSupplyingMonths(Short expSupplyingMonths) {
		this.expSupplyingMonths = expSupplyingMonths;
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
