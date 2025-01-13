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
@Table(name="m_bp_org_certification")
public class PartnerOrgCertification extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long partnerOrgCertificateId;
	private String isoName;
	private String isoCertifyingAuthority;
	private String isoCertificationNo;
	private Date isoValidityDate;
	private String isNotApplicable;
	private PartnerOrg partnerOrg;
	private String isApproved;
	private String remark;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private Attachment certificateCopy;
	
	@Id
	@SequenceGenerator(name="m_bp_org_certification_Seq",sequenceName="m_bp_org_certification_Seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_org_certification_Seq")	
	@Column(name = "m_bp_org_certification_id", updatable=false)
	public Long getPartnerOrgCertificateId() {
		return partnerOrgCertificateId;
	}
	public void setPartnerOrgCertificateId(Long partnerOrgCertificateId) {
		this.partnerOrgCertificateId = partnerOrgCertificateId;
	}
	@Column(name="iso_name")
	public String getIsoName() {
		return isoName;
	}
	
	public void setIsoName(String isoName) {
		this.isoName = isoName;
	}
	@Column(name="certifying_Authority")
	public String getIsoCertifyingAuthority() {
		return isoCertifyingAuthority;
	}
	public void setIsoCertifyingAuthority(String isoCertifyingAuthority) {
		this.isoCertifyingAuthority = isoCertifyingAuthority;
	}
	@Column(name="certificate_no")
	public String getIsoCertificationNo() {
		return isoCertificationNo;
	}
	public void setIsoCertificationNo(String isoCertificationNo) {
		this.isoCertificationNo = isoCertificationNo;
	}
	
	@Column(name="valid_from")
	public Date getIsoValidityDate() {
		return isoValidityDate;
	}
	public void setIsoValidityDate(Date isoValidityDate) {
		this.isoValidityDate = isoValidityDate;
	}
	
	
	@Column(name="isnotapplicable")
	public String getIsNotApplicable() {
		return isNotApplicable;
	}
	public void setIsNotApplicable(String isNotApplicable) {
		
		this.isNotApplicable = isNotApplicable;
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
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="certificate_copy",referencedColumnName="m_attachment_id")
	public Attachment getCertificateCopy() {
		if(certificateCopy==null || certificateCopy.getAttachmentId()==null)
		{
			return null;
		}
		return certificateCopy;
	}
	public void setCertificateCopy(Attachment certificateCopy) {
		this.certificateCopy = certificateCopy;
	}
	
}
