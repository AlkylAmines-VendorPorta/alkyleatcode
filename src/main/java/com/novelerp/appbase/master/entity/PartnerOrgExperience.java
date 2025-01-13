	package com.novelerp.appbase.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="m_bp_org_experience")
public class PartnerOrgExperience extends ContextPO {

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
	private PartnerOrg partnerOrg;
	private String isApproved;
	private String remark;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	
	@Id
	@SequenceGenerator(name="m_bp_org_experience_seq",sequenceName="m_bp_org_experience_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_org_experience_seq")	
	@Column(name = "m_bp_org_experience_id", updatable=false)
	public Long getPartnerOrgExperienceId() {
		return partnerOrgExperienceId;
	}
	public void setPartnerOrgExperienceId(Long partnerOrgExperienceId) {
		this.partnerOrgExperienceId = partnerOrgExperienceId;
	}
	@Column(name="exp_manufacturing_yrs")
	public Short getExpManufacturingYear() {
		return expManufacturingYear;
	}
	
	
	public void setExpManufacturingYear(Short expManufacturingYear) {
		this.expManufacturingYear = expManufacturingYear;
	}
	@Column(name="exp_manufacturing_months")
	public Short getExpManufacturingMonths() {
		return expManufacturingMonths;
	}
	public void setExpManufacturingMonths(Short expManufacturingMonths) {
		this.expManufacturingMonths = expManufacturingMonths;
	}
	@Column(name="exp_design_yrs")
	public Short getExpDesignYear() {
		return expDesignYear;
	}
	public void setExpDesignYear(Short expDesignYear) {
		this.expDesignYear = expDesignYear;
	}
	@Column(name="exp_design_months")
	public Short getExpDesignMonths() {
		return expDesignMonths;
	}
	public void setExpDesignMonths(Short expDesignMonths) {
		this.expDesignMonths = expDesignMonths;
	}
	@Column(name="exp_testing_yrs")
	public Short getExpTestingYear() {
		return expTestingYear;
	}
	public void setExpTestingYear(Short expTestingYear) {
		this.expTestingYear = expTestingYear;
	}
	@Column(name="exp_testing_months")
	public Short getExpTestingMonths() {
		return expTestingMonths;
	}
	public void setExpTestingMonths(Short expTestingMonths) {
		this.expTestingMonths = expTestingMonths;
	}
	@Column(name="exp_supplying_yrs")
	public Short getExpSupplyingYear() {
		return expSupplyingYear;
	}
	public void setExpSupplyingYear(Short expSupplyingYear) {
		this.expSupplyingYear = expSupplyingYear;
	}
	@Column(name="exp_supplying_months")
	public Short getExpSupplyingMonths() {
		return expSupplyingMonths;
	}
	public void setExpSupplyingMonths(Short expSupplyingMonths) {
		this.expSupplyingMonths = expSupplyingMonths;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_bp_org_id")
	public PartnerOrg getPartnerOrg() {
		return partnerOrg;
	}
	public void setPartnerOrg(PartnerOrg partnerOrg) {
		this.partnerOrg = partnerOrg;
	}
	@Column(name="is_approved")
	public String getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="is_ee_approved")
	public String getIsEEApproved() {
		return isEEApproved;
	}
	public void setIsEEApproved(String isEEApproved) {
		this.isEEApproved = isEEApproved;
	}
	@Column(name="ee_comment")
	public String getEeComment() {
		return eeComment;
	}
	public void setEeComment(String eeComment) {
		this.eeComment = eeComment;
	}
	@Column(name="is_ce_approved")
	public String getIsCEApproved() {
		return isCEApproved;
	}
	public void setIsCEApproved(String isCEApproved) {
		this.isCEApproved = isCEApproved;
	}
	@Column(name="ce_comment")
	public String getCeComment() {
		return ceComment;
	}
	public void setCeComment(String ceComment) {
		this.ceComment = ceComment;
	}
}
