package com.novelerp.appbase.master.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="m_bp_financial_attachment")
public class PartnerFinancialAttachment extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long financialAttachmentId;
	private Attachment attachment;
	private Date validFrom;
	private Date validTo;
	private double amount;
	private String finacialType;
	private FinancialYear financialYear;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String remark;
	private String isApproved;
	private String financialYearNew;
	@Id
	@SequenceGenerator(name="m_bp_financial_attachment_seq",sequenceName="m_bp_financial_attachment_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_financial_attachment_seq")	
	@Column(name = "m_bp_financial_attachment_id", updatable=false)
	public Long getFinancialAttachmentId() {
		return financialAttachmentId;
	}
	public void setFinancialAttachmentId(Long financialAttachmentId) {
		this.financialAttachmentId = financialAttachmentId;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_attachment_id")
	public Attachment getAttachment() {
		if(attachment == null || attachment.getAttachmentId() ==null){
			return null;
		}
		return attachment;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	@Column(name="amount")
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Column(name="validFrom")
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	@Column(name="validTo")
	public Date getValidTo() {
		return validTo;
	}
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
	@Column(name="finacialType")
	public String getFinacialType() {
		return finacialType;
	}
	public void setFinacialType(String finacialType) {
		this.finacialType = finacialType;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_financial_year_id")
	public FinancialYear getFinancialYear() {
		if(financialYear == null || financialYear.getFinancialYearId() ==null){
			return null;
		}
		return financialYear;
	}
	public void setFinancialYear(FinancialYear financialYear) {
		this.financialYear = financialYear;
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
	@Column(name="financial_year_new")
	public String getFinancialYearNew() {
		return financialYearNew;
	}
	public void setFinancialYearNew(String financialYearNew) {
		this.financialYearNew = financialYearNew;
	}
	
	
	
}
