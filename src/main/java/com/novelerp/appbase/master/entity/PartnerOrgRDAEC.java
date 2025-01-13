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
@Table(name="m_bp_org_rdaec")
public class PartnerOrgRDAEC extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long partnerOrgRDAECId;
	private String isApplicable;
    private Date validFrom;
    private Date validTo;
    private String elegibilityType;
    private String developingRegion;
    private String isPioneer;
    private PartnerOrg partnerOrg;
    private Attachment eligibilityCertificate;
    private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String remark;
	private String isApproved;
	@Id
	@SequenceGenerator(name="m_bp_org_rdaec_seq",sequenceName="m_bp_org_rdaec_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_org_rdaec_seq")	
	@Column(name = "m_bp_org_rdaec_id", updatable=false)
	public Long getPartnerOrgRDAECId() {
		return partnerOrgRDAECId;
	}
	public void setPartnerOrgRDAECId(Long partnerOrgRDAECId) {
		this.partnerOrgRDAECId = partnerOrgRDAECId;
	}
	
	@Column(name="isapplicable")
	public String getIsApplicable() {
		return isApplicable;
	}
	
	public void setIsApplicable(String isApplicable) {
		this.isApplicable = isApplicable;
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
	@Column(name="elegibility_type")
	public String getElegibilityType() {
		return elegibilityType;
	}
	public void setElegibilityType(String elegibilityType) {
		this.elegibilityType = elegibilityType;
	}
	@Column(name="developing_region")
	public String getDevelopingRegion() {
		return developingRegion;
	}
	public void setDevelopingRegion(String developingRegion) {
		this.developingRegion = developingRegion;
	}
	@Column(name="ispoineer")
	public String getIsPioneer() {
		return isPioneer;
	}
	public void setIsPioneer(String isPioneer) {
		this.isPioneer = isPioneer;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_bp_org_id")
	public PartnerOrg getPartnerOrg() {
		return partnerOrg;
	}
	public void setPartnerOrg(PartnerOrg partnerOrg) {
		this.partnerOrg = partnerOrg;
	}
	@OneToOne
	@JoinColumn(name="m_attachment_id")
	public Attachment getEligibilityCertificate() {
		if(eligibilityCertificate==null || eligibilityCertificate.getAttachmentId()==null)
				{
			          return null;
				}
		return eligibilityCertificate;
	}
	public void setEligibilityCertificate(Attachment eligibilityCertificate) {
		this.eligibilityCertificate = eligibilityCertificate;
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
	
}
