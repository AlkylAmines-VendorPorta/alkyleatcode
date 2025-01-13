package com.novelerp.appbase.master.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="m_charge")
public class PaymentType extends ContextPO {

	
	private static final long serialVersionUID = 7145905595117314888L;

	private Long paymentTypeId;
	private String name;
	private String code;
	private String description;
	private BigDecimal amount;
	private BigDecimal igst;
	private BigDecimal cgst;
	private BigDecimal sgst;
	private String isRefundable;
	private String periodType;
	private BigDecimal gst;
	private HSN hsn;
	private String paymentPostingCode;

	@Id	
	@SequenceGenerator(name="m_charge_seq",sequenceName="m_charge_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_charge_seq")	
	@Column(name = "m_charge_id", updatable=false)
	public Long getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(Long paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "value")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name = "isrefundable")
	public String getIsRefundable() {
		return isRefundable;
	}
	public void setIsRefundable(String isRefundable) {
		this.isRefundable = isRefundable;
	}
	@Column(name = "period_type")
	public String getPeriodType() {
		return periodType;
	}
	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}
	@Column(name = "amount")
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Column(name="igst")
	public BigDecimal getIgst() {
		return igst;
	}

	public void setIgst(BigDecimal igst) {
		this.igst = igst;
	}

	@Column(name="cgst")
	public BigDecimal getCgst() {
		return cgst;
	}

	public void setCgst(BigDecimal cgst) {
		this.cgst = cgst;
	}

	@Column(name="sgst")
	public BigDecimal getSgst() {
		return sgst;
	}

	public void setSgst(BigDecimal sgst) {
		this.sgst = sgst;
	}
	
	@Column(name="gst")
	public BigDecimal getGst() {
		return gst;
	}

	public void setGst(BigDecimal gst) {
		this.gst = gst;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "m_hsn_id")
	public HSN getHsn() {
		return hsn;
	}

	public void setHsn(HSN hsn) {
		this.hsn = hsn;
	}

	@Column(name="payment_posting_code")
	public String getPaymentPostingCode() {
		return paymentPostingCode;
	}

	public void setPaymentPostingCode(String paymentPostingCode) {
		this.paymentPostingCode = paymentPostingCode;
	}
	
}

