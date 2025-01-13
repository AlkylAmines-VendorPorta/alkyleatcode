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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;
/**
 * 
 * @author Aman
 */
@Entity
@Table(name="m_bp_org_payment")
public class PartnerOrgPayment extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long partnerOrgPaymentId;
	private PaymentMode paymentmode;
	private PaymentType paymenttype;
	private Date demandDraftDate;
	private String demandDraftNo;
	private String micrNo;
	private BigDecimal amount;
	private String gstin;
	private Integer gstRate;
	private BigDecimal totalAmount;
	private String bankName;
	private String branchName;
	
	
	
	@Id
	@SequenceGenerator(name="m_bp_org_payment_seq",sequenceName="m_bp_org_payment_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_org_payment_seq")	
	@Column(name = "m_bp_org_payment_id", updatable=false)
	public Long getPartnerOrgPaymentId() {
		return partnerOrgPaymentId;
	}
	public void setPartnerOrgPaymentId(Long partnerOrgPaymentId) {
		this.partnerOrgPaymentId = partnerOrgPaymentId;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_payment_mode_id")
	public PaymentMode getPaymentmode() {
		return paymentmode;
	}
	
	public void setPaymentmode(PaymentMode paymentmode) {
		this.paymentmode = paymentmode;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_charge_id")
	public PaymentType getPaymenttype() {
		return paymenttype;
	}
	public void setPaymenttype(PaymentType paymenttype) {
		this.paymenttype = paymenttype;
	}
	@Column(name="dd_date")
	public Date getDemandDraftDate() {
		return demandDraftDate;
	}
	public void setDemandDraftDate(Date demandDraftDate) {
		this.demandDraftDate = demandDraftDate;
	}
	@Column(name="dd_no")
	public String getDemandDraftNo() {
		return demandDraftNo;
	}
	public void setDemandDraftNo(String demandDraftNo) {
		this.demandDraftNo = demandDraftNo;
	}
	@Column(name="micr_no")
	public String getMicrNo() {
		return micrNo;
	}
	public void setMicrNo(String micrNo) {
		this.micrNo = micrNo;
	}
	@Column(name="amount")
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	@Column(name="gstin")
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	@Column(name="gst_rate")
	public Integer getGstRate() {
		return gstRate;
	}
	public void setGstRate(Integer gstRate) {
		this.gstRate = gstRate;
	}
	@Column(name="total_amount")
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Column(name="bank_name")
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	@Column(name="branch_name")
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
}
