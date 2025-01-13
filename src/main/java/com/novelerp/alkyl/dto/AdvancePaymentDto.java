package com.novelerp.alkyl.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.core.util.DateUtil;

public class AdvancePaymentDto extends CommonContextDto {
	private static final long serialVersionUID = 1L;
	
	private Long advancePaymentId;
	private String companyCode;
	private String vendorCode;
	private String year;
	private String assignment;
	private String documentNumber;
	private String lineItem;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date postingDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date entryDate;
	private String curr;
	private String reference;
	private String docType;
	private String taxCode;
	private Double amountInLC;
	private Double amount;
	private String text;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date invoiceDate;
//	private Date baselinePaymentDate;
	
	private String payTerms;
	private String days1;
	private String interestRate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date actualPaymentDate;
	private String interestType;
	private Double interestAmount;
	private Double grossAmount;
	private String isMailSent;
	private String status;
	private String rejectReason;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
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
	private UserDto approvedBy;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date approvedDate;
	private String message;
	private String mailStatus;
	private Double netPayableAmount;
	
	
	public Long getAdvancePaymentId() {
		return advancePaymentId;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public String getYear() {
		return year;
	}
	public String getAssignment() {
		return assignment;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public String getLineItem() {
		return lineItem;
	}
	public Date getPostingDate() {
		return postingDate;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public String getCurr() {
		return curr;
	}
	public String getReference() {
		return reference;
	}
	public String getDocType() {
		return docType;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public Double getAmountInLC() {
		return amountInLC;
	}
	public Double getAmount() {
		return amount;
	}
	public String getText() {
		return text;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public String getPayTerms() {
		return payTerms;
	}
	public String getDays1() {
		return days1;
	}
	public String getInterestRate() {
		return interestRate;
	}
	public Date getActualPaymentDate() {
		return actualPaymentDate;
	}
	public String getInterestType() {
		return interestType;
	}
	public Double getInterestAmount() {
		return interestAmount;
	}
	public Double getGrossAmount() {
		return grossAmount;
	}
	public String getIsMailSent() {
		return isMailSent;
	}
	public String getStatus() {
		return status;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public Date getNextPaymentDate() {
		return nextPaymentDate;
	}
	public Double getCgst() {
		return cgst;
	}
	public Double getSgst() {
		return sgst;
	}
	public Double getIgst() {
		return igst;
	}
	public Double getCgstAmount() {
		return cgstAmount;
	}
	public Double getSgstAmount() {
		return sgstAmount;
	}
	public Double getIgstAmount() {
		return igstAmount;
	}
	public String getVendorName() {
		return vendorName;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setAdvancePaymentId(Long advancePaymentId) {
		this.advancePaymentId = advancePaymentId;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public void setLineItem(String lineItem) {
		this.lineItem = lineItem;
	}
	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public void setCurr(String curr) {
		this.curr = curr;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public void setAmountInLC(Double amountInLC) {
		this.amountInLC = amountInLC;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public void setPayTerms(String payTerms) {
		this.payTerms = payTerms;
	}
	public void setDays1(String days1) {
		this.days1 = days1;
	}
	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}
	public void setActualPaymentDate(Date actualPaymentDate) {
		this.actualPaymentDate = actualPaymentDate;
	}
	public void setInterestType(String interestType) {
		this.interestType = interestType;
	}
	public void setInterestAmount(Double interestAmount) {
		this.interestAmount = interestAmount;
	}
	public void setGrossAmount(Double grossAmount) {
		this.grossAmount = grossAmount;
	}
	public void setIsMailSent(String isMailSent) {
		this.isMailSent = isMailSent;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	public void setNextPaymentDate(Date nextPaymentDate) {
		this.nextPaymentDate = nextPaymentDate;
	}
	public void setCgst(Double cgst) {
		this.cgst = cgst;
	}
	public void setSgst(Double sgst) {
		this.sgst = sgst;
	}
	public void setIgst(Double igst) {
		this.igst = igst;
	}
	public void setCgstAmount(Double cgstAmount) {
		this.cgstAmount = cgstAmount;
	}
	public void setSgstAmount(Double sgstAmount) {
		this.sgstAmount = sgstAmount;
	}
	public void setIgstAmount(Double igstAmount) {
		this.igstAmount = igstAmount;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	
	
	public Long getGapinDays() {
		return gapinDays;
	}
	public void setGapinDays(Long gapinDays) {
		this.gapinDays = gapinDays;
	}	
	
	public UserDto getApprovedBy() {
		return approvedBy;
	}
	public Date getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedBy(UserDto approvedBy) {
		this.approvedBy = approvedBy;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}	
	
	public String getMailStatus() {
		return mailStatus;
	}
	public void setMailStatus(String mailStatus) {
		this.mailStatus = mailStatus;
	}
	
	
	public Double getNetPayableAmount() {
		return netPayableAmount;
	}
	public void setNetPayableAmount(Double netPayableAmount) {
		this.netPayableAmount = netPayableAmount;
	}
	
	@Override
	public String toString() {
		return "AdvancePaymentDto [advancePaymentId=" + advancePaymentId + ", companyCode=" + companyCode
				+ ", vendorCode=" + vendorCode + ", year=" + year + ", assignment=" + assignment + ", documentNumber="
				+ documentNumber + ", lineItem=" + lineItem + ", postingDate=" + postingDate + ", entryDate="
				+ entryDate + ", curr=" + curr + ", reference=" + reference + ", docType=" + docType + ", taxCode="
				+ taxCode + ", amountInLC=" + amountInLC + ", amount=" + amount + ", text=" + text + ", invoiceDate="
				+ invoiceDate + ", payTerms=" + payTerms + ", days1=" + days1 + ", interestRate=" + interestRate
				+ ", actualPaymentDate=" + actualPaymentDate + ", interestType=" + interestType + ", interestAmount="
				+ interestAmount + ", grossAmount=" + grossAmount + ", isMailSent=" + isMailSent + ", status=" + status
				+ ", rejectReason=" + rejectReason + ", nextPaymentDate=" + nextPaymentDate + ", cgst=" + cgst
				+ ", sgst=" + sgst + ", igst=" + igst + ", cgstAmount=" + cgstAmount + ", sgstAmount=" + sgstAmount
				+ ", igstAmount=" + igstAmount + ", vendorName=" + vendorName + ", poNo=" + poNo + ", gapinDays="
				+ gapinDays + ", approvedBy=" + approvedBy + ", approvedDate=" + approvedDate + ", message=" + message
				+ ", mailStatus=" + mailStatus + ", netPayableAmount=" + netPayableAmount + "]";
	}
	
	




	
	
	

	

}
