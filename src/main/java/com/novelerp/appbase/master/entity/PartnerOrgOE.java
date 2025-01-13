package com.novelerp.appbase.master.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;
/**
 * 
 * @author Aman
 */
@Entity
@Table(name="m_bp_org_oe")
public class PartnerOrgOE extends ContextPO{
	
	
	private static final long serialVersionUID = 1L;
	private Long partnerOrgOEId; 
	private String oeType;
	private String authority;
	private String RegsNo;
	private Date validFrom;
	private Date validTo;
	private PartnerOrg partnerOrg;
	private String isApproved;
	private String remark;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String validityType;
	private Attachment eligibilityCertificte;
	private String isNotApplicable;
	
	@Id
	@SequenceGenerator(name="m_bp_org_oe_seq",sequenceName="m_bp_org_oe_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_org_oe_seq")	
	@Column(name = "m_bp_org_oe_id", updatable=false)
	public Long getPartnerOrgOEId() {
		return partnerOrgOEId;
	}

	public void setPartnerOrgOEId(Long partnerOrgOEId) {
		this.partnerOrgOEId = partnerOrgOEId;
	}
	@Column(name="authority")
	public String getAuthority() {
		return authority;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	@Column(name="registration_no")
	public String getRegsNo() {
		return RegsNo;
	}
	public void setRegsNo(String regsNo) {
		RegsNo = regsNo;
	}
	@Column(name="valid_from")
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	@Column(name="valid_to")
	public Date getValidTo() {
		return validTo;
	}
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
	@Column(name="oe_type")
	public String getOeType() {
		return oeType;
	}
	public void setOeType(String oeType) {
		this.oeType = oeType;
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
    @Column(name="validity_type")
	public String getValidityType() {
		return validityType;
	}

	public void setValidityType(String validityType) {
		this.validityType = validityType;
	}
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="eligibility_certificte", referencedColumnName="m_attachment_id")
	public Attachment getEligibilityCertificte() {
		return eligibilityCertificte;
	}

	public void setEligibilityCertificte(Attachment eligibilityCertificte) {
		this.eligibilityCertificte = eligibilityCertificte;
	}
    @Column(name="is_not_applicable")
	public String getIsNotApplicable() {
		return isNotApplicable;
	}

	public void setIsNotApplicable(String isNotApplicable) {
		this.isNotApplicable = isNotApplicable;
	}
	
	
}

