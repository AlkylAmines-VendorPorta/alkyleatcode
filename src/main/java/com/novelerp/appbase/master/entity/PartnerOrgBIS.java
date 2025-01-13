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
@Table(name="m_bp_org_bis")
public class PartnerOrgBIS extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long partnerOrgBisId;
	private String bisLicenceNo;
	private Date validFrom;
	private Date validTo;
	private Attachment bisLicenceCertificate;
	private PartnerOrg partnerOrg;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String isNotApplicable;
	
	
	@Id
	@SequenceGenerator(name="m_bp_org_bis_seq",sequenceName="m_bp_org_bis_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_org_bis_seq")	
	@Column(name = "m_bp_org_bis_id", updatable=false)
	public Long getPartnerOrgBisId() {
		return partnerOrgBisId;
	}
	public void setPartnerOrgBisId(Long partnerOrgBisId) {
		this.partnerOrgBisId = partnerOrgBisId;
	}
	@Column(name="bis_licence_no")
	public String getBisLicenceNo() {
		return bisLicenceNo;
	}
	
	public void setBisLicenceNo(String bisLicenceNo) {
		this.bisLicenceNo = bisLicenceNo;
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
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_attachment_id")
	public Attachment getBisLicenceCertificate() {
		if(bisLicenceCertificate==null || bisLicenceCertificate.getAttachmentId()==null)
		{
			return null;
		}
		return bisLicenceCertificate;
	}
	public void setBisLicenceCertificate(Attachment bisLicenceCertificate) {
		this.bisLicenceCertificate = bisLicenceCertificate;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_bp_org_id")
	public PartnerOrg getPartnerOrg() {
		return partnerOrg;
	}
	public void setPartnerOrg(PartnerOrg partnerOrg) {
		this.partnerOrg = partnerOrg;
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
	
	@Column(name="is_not_applicable")
	public String getIsNotApplicable() {
		return isNotApplicable;
	}
	public void setIsNotApplicable(String isNotApplicable) {
		this.isNotApplicable = isNotApplicable;
	}
}
