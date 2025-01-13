package com.novelerp.appbase.master.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.novelerp.appcontext.dto.CommonContextDto;

/**
 * Partner organisation's Payment Details.
 * 
 * @author Vivek Birdi
 *
 */
public class PartnerOrgPaymentDto extends CommonContextDto{

	
	private static final long serialVersionUID = 1L;
	private Long partnerOrgPaymentId;
	private PaymentModeDto paymentmode;
	private PaymentTypeDto paymenttype;
	private Date demandDraftDate;
	private String demandDraftNo;
	private String micrNo;
	private BigDecimal amount;
	private String gstin;
	private Integer gstRate;
	private BigDecimal totalAmount;
	private String bankName;
	private String branchName;
	
	
	
	public Long getPartnerOrgPaymentId() {
		return partnerOrgPaymentId;
	}
	public void setPartnerOrgPaymentId(Long partnerOrgPaymentId) {
		this.partnerOrgPaymentId = partnerOrgPaymentId;
	}
	public Date getDemandDraftDate() {
		return demandDraftDate;
	}
	public void setDemandDraftDate(Date demandDraftDate) {
		this.demandDraftDate = demandDraftDate;
	}
	public String getDemandDraftNo() {
		return demandDraftNo;
	}
	public void setDemandDraftNo(String demandDraftNo) {
		this.demandDraftNo = demandDraftNo;
	}
	public String getMicrNo() {
		return micrNo;
	}
	public void setMicrNo(String micrNo) {
		this.micrNo = micrNo;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	public Integer getGstRate() {
		return gstRate;
	}
	public void setGstRate(Integer gstRate) {
		this.gstRate = gstRate;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public PaymentModeDto getPaymentmode() {
		return paymentmode;
	}
	public void setPaymentmode(PaymentModeDto paymentmode) {
		this.paymentmode = paymentmode;
	}
	public PaymentTypeDto getPaymenttype() {
		return paymenttype;
	}
	public void setPaymenttype(PaymentTypeDto paymenttype) {
		this.paymenttype = paymenttype;
	}

	
}
