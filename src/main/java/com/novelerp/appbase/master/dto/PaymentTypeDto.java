package com.novelerp.appbase.master.dto;

import java.math.BigDecimal;

import com.novelerp.appcontext.dto.CommonContextDto;

public class PaymentTypeDto  extends CommonContextDto{

	private static final long serialVersionUID = 1L;
	private Long PaymentTypeId;
	private String name;
	private String code;
	private String description;
	private BigDecimal amount;
	private BigDecimal gst;
	private String isRefundable;
	private String periodType;
	private BigDecimal igst;
	private BigDecimal cgst;
	private BigDecimal sgst;
	private HSNDto hsn;
	private String paymentPostingCode;
	
	public Long getPaymentTypeId() {
		return PaymentTypeId;
	}
	public void setPaymentTypeId(Long paymentTypeId) {
		PaymentTypeId = paymentTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getIsRefundable() {
		return isRefundable;
	}
	public void setIsRefundable(String isRefundable) {
		this.isRefundable = isRefundable;
	}
	public String getPeriodType() {
		return periodType;
	}
	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}
	public BigDecimal getGst() {
		return gst;
	}
	public void setGst(BigDecimal gst) {
		this.gst = gst;
	}
	
	public BigDecimal getIgst() {
		if(igst==null){
			return BigDecimal.ZERO;
		}
		return igst;
	}

	public void setIgst(BigDecimal igst) {
		this.igst = igst;
	}

	public BigDecimal getCgst() {
		if(cgst==null){
			return BigDecimal.ZERO;
		}
		return cgst;
	}

	public void setCgst(BigDecimal cgst) {
		this.cgst = cgst;
	}

	public BigDecimal getSgst() {
		if(sgst==null){
			return BigDecimal.ZERO;
		}
		return sgst;
	}

	public void setSgst(BigDecimal sgst) {
		this.sgst = sgst;
	}
	public HSNDto getHsn() {
		return hsn;
	}
	public void setHsn(HSNDto hsn) {
		this.hsn = hsn;
	}
	public String getPaymentPostingCode() {
		return paymentPostingCode;
	}
	public void setPaymentPostingCode(String paymentPostingCode) {
		this.paymentPostingCode = paymentPostingCode;
	}
	
	
}
