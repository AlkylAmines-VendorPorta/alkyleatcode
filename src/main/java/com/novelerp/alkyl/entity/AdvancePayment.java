package com.novelerp.alkyl.entity;

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
import com.novelerp.appcontext.entity.User;

@Entity
@Table(name="t_advance_payment")
public class AdvancePayment extends ContextPO{
	
private static final long serialVersionUID = 1L;
	
	private Long advancePaymentId;
	private String vendorCode;
	private String documentNumber;
	private String reference;
	private String docType;
	private Double amountInLC;
//	private Date baselinePaymentDate;;
	private Date invoiceDate;
	private Date actualPaymentDate;
	private String interestRate;
	private String interestType;
	private Double interestAmount;
	private Double grossAmount;
	private String isMailSent;
	private String status;
	private String rejectReason;
	private Date nextPaymentDate;
	private Double cgst;
	private Double sgst;
	private Double igst;
	private Double cgstAmount;
	private Double sgstAmount;
	private Double igstAmount;
	private String vendorName;
	private String poNo;
	private Long gapinDays;
	private User approvedBy;
	private Date approvedDate;
	private Double netPayableAmount;
	
	
	
	@Id
	@SequenceGenerator(name = "t_advance_payment_seq", sequenceName = "t_advance_payment_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_advance_payment_seq")
	@Column(name="t_advance_payment_id",updatable = false)
	public Long getAdvancePaymentId() {
		return advancePaymentId;
	}
	
	public void setAdvancePaymentId(Long advancePaymentId) {
		this.advancePaymentId = advancePaymentId;
	}
	
	
	@Column(name="supplier")
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	
	@Column(name="document_number")
	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	
	@Column(name="reference")
	public String getReference() {
		return reference;
	}


	public void setReference(String reference) {
		this.reference = reference;
	}
	
	@Column(name="doc_type")
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	
	@Column(name="amount_in_LC")
	public Double getAmountInLC() {
		return amountInLC;
	}

	public void setAmountInLC(Double amountInLC) {
		this.amountInLC = amountInLC;
	}
	
	
	@Column(name="invoice_date")
	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
//	public Date getBaselinePaymentDate() {
//		return baselinePaymentDate;
//	}
//
//
//	public void setBaselinePaymentDate(Date baselinePaymentDate) {
//		this.baselinePaymentDate = baselinePaymentDate;
//	}

	
	@Column(name="actual_payment_date")
	public Date getActualPaymentDate() {
		return actualPaymentDate;
	}


	public void setActualPaymentDate(Date actualPaymentDate) {
		this.actualPaymentDate = actualPaymentDate;
	}

	
	@Column(name="interest_rate")
	public String getInterestRate() {
		return interestRate;
	}


	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}
	
	@Column(name="interest_type")
	public String getInterestType() {
		return interestType;
	}
	public void setInterestType(String interestType) {
		this.interestType = interestType;
	}
	
	@Column(name="interest_amount")
	public Double getInterestAmount() {
		return interestAmount;
	}

	public void setInterestAmount(Double interestAmount) {
		this.interestAmount = interestAmount;
	}

	
	@Column(name="gross_amount")
	public Double getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(Double grossAmount) {
		this.grossAmount = grossAmount;
	}

	@Column(name="ismailsent")
	public String getIsMailSent() {
		return isMailSent;
	}

	public void setIsMailSent(String isMailSent) {
		this.isMailSent = isMailSent;
	}

	
	@Column(name="status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	@Column(name="rejectreason")
	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	
	@Column(name="nextpaymentdate")
	public Date getNextPaymentDate() {
		return nextPaymentDate;
	}

	public void setNextPaymentDate(Date nextPaymentDate) {
		this.nextPaymentDate = nextPaymentDate;
	}

	@Column(name="cgst")
	public Double getCgst() {
		return cgst;
	}
	
	public void setCgst(Double cgst) {
		this.cgst = cgst;
	}

	@Column(name="sgst")
	public Double getSgst() {
		return sgst;
	}
	
	public void setSgst(Double sgst) {
		this.sgst = sgst;
	}

	@Column(name="igst")
	public Double getIgst() {
		return igst;
	}

	public void setIgst(Double igst) {
		this.igst = igst;
	}
	
	@Column(name="cgst_amount")
	public Double getCgstAmount() {
		return cgstAmount;
	}
	
	public void setCgstAmount(Double cgstAmount) {
		this.cgstAmount = cgstAmount;
	}

	@Column(name="sgst_amount")
	public Double getSgstAmount() {
		return sgstAmount;
	}
	
	public void setSgstAmount(Double sgstAmount) {
		this.sgstAmount = sgstAmount;
	}

	@Column(name="igst_amount")
	public Double getIgstAmount() {
		return igstAmount;
	}

	public void setIgstAmount(Double igstAmount) {
		this.igstAmount = igstAmount;
	}

	@Column(name="vendorname")
	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	
	@Column(name="pono")
	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	
	@Column(name="gapindays")
	public Long getGapinDays() {
		return gapinDays;
	}

	public void setGapinDays(Long gapinDays) {
		this.gapinDays = gapinDays;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="approvedBy",referencedColumnName="ad_user_id")
	public User getApprovedBy() {
		return approvedBy;
	}
	
	public void setApprovedBy(User approvedBy) {
		this.approvedBy = approvedBy;
	}

	
	@Column(name="approved_date")
	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	
	
	@Column(name="net_payable_amt")
	public Double getNetPayableAmount() {
		return netPayableAmount;
	}

	public void setNetPayableAmount(Double netPayableAmount) {
		this.netPayableAmount = netPayableAmount;
	}
	
	
	
	
	
	
	
	
}
