package com.novelerp.appbase.master.entity;

import java.math.BigDecimal;
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
@Table(name="m_bp_org_pgb")
public class PartnerOrgPBG extends ContextPO {

	private static final long serialVersionUID = 1L;
	private Long partnerOrgPbgId;
	private String bankGuaranteeNo;
	private BigDecimal pbgAmount;
	private Date issueDate;
	private Date validityDate;
	private Attachment bankGauranteeCopy;
	private String isNotApplicable;
	private PartnerOrg partnerOrg;
	private String isApproved;
	private String remark;
	
	@Id
	@SequenceGenerator(name="m_bp_org_pgb_seq",sequenceName="m_bp_org_pgb_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_org_pgb_seq")	
	@Column(name = "m_bp_org_pgb_id", updatable=false)
	public Long getPartnerOrgPbgId() {
		return partnerOrgPbgId;
	}

	public void setPartnerOrgPbgId(Long partnerOrgPbgId) {
		this.partnerOrgPbgId = partnerOrgPbgId;
	}
	@Column(name="bank_guarantee_no")
	public String getBankGuaranteeNo() {
		return bankGuaranteeNo;
	}
	public void setBankGuaranteeNo(String bankGuaranteeNo) {
		this.bankGuaranteeNo = bankGuaranteeNo;
	}
	@Column(name="pgb_amount")
	public BigDecimal getPbgAmount() {
		return pbgAmount;
	}

	public void setPbgAmount(BigDecimal pbgAmount) {
		this.pbgAmount = pbgAmount;
	}
	@Column(name="valid_from")
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	@Column(name="valid_to")
	public Date getValidityDate() {
		return validityDate;
	}
	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_attachment_id")
	public Attachment getBankGauranteeCopy() {
		if(bankGauranteeCopy==null || bankGauranteeCopy.getAttachmentId()==null)
		{
			return null;
		}
		return bankGauranteeCopy;
	}

	public void setBankGauranteeCopy(Attachment bankGauranteeCopy) {
		this.bankGauranteeCopy = bankGauranteeCopy;
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
		if(partnerOrg==null || partnerOrg.getPartnerOrgId()==null)
		{
			return null;
		}
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
	
	
}
