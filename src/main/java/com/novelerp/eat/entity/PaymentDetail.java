package com.novelerp.eat.entity;

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

import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appbase.master.entity.PartnerOrg;
import com.novelerp.appbase.master.entity.PaymentType;
import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.appcontext.entity.User;

/**
 * 
 * @author ankita
 *
 */
@Entity
@Table(name="t_payment")
public class PaymentDetail extends ContextPO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long paymentDetailId;
	private PaymentType paymentType;
	private String paymentMode; 
	private TAHDR tahdr;
	private TAHDRDetail tahdrDetail;
	private BigDecimal amount;
	private BigDecimal gst;
	private BigDecimal gstAmount;
	private BigDecimal total;
	private String bankName;
	private String branchName;
	private String micrCode;
	private String referenceNo;
	private Date paymentDate;
	private Date validityDate;
	private String isFOApproved;
	private String isFAApproved;
	private String foComment;
	private String faComment;
	private PartnerOrg partnerOrg;
	private String isApproved;
	private String remark;
	private String isTrader;

	private BigDecimal cgst;

	private BigDecimal sgst;

	private BigDecimal igst;
    private String vendorTypePayment;
	
    private String realisationStatus;
	private Date realisationDate;
	private Date moneyReceiptDate;
	private String moneyReceiptNumber;
	private BigDecimal igstAmount;
	private BigDecimal cgstAmount;
	private BigDecimal sgstAmount;
	private Long docNo;
	private String paymentGatewayStatus;
	private String isCEApproved;
	private String isPushed;
	private String lvexport;
	private User foApprovedBy;
	private User faApprovedBy;
	private Date foApprovedDate;
	private Date faApprovedDate;
	private Attachment bankGuaranteeAttachment;
	
	@Id
	@SequenceGenerator(name="t_payment_detail_seq",sequenceName="t_payment_detail_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="t_payment_detail_seq")	
	@Column(name = "t_payment_detail_id", updatable=false)
	public Long getPaymentDetailId() {
		return paymentDetailId;
	}
	public void setPaymentDetailId(Long paymentDetailId) {
		this.paymentDetailId = paymentDetailId;
	}	
	@Column(name="amount")
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	@Column(name="gst")
	public BigDecimal getGst() {
		return gst;
	}
	public void setGst(BigDecimal gst) {
		this.gst = gst;
	}
	@Column(name="total")
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
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
	@Column(name="micr_code")
	public String getMicrCode() {
		return micrCode;
	}
	public void setMicrCode(String micrCode) {
		this.micrCode = micrCode;
	}
	@Column(name="reference_no")
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	@Column(name="payment_date")
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	@Column(name="is_fo_approved")
	public String getIsFOApproved() {
		return isFOApproved;
	}
	public void setIsFOApproved(String isFOApproved) {
		this.isFOApproved = isFOApproved;
	}
	@Column(name="is_fa_approved")
	public String getIsFAApproved() {
		return isFAApproved;
	}
	public void setIsFAApproved(String isFAApproved) {
		this.isFAApproved = isFAApproved;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_charge_id")
	public PaymentType getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	@Column(name="payment_mode")
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	@Column(name="fo_comment")
	public String getFoComment() {
		return foComment;
	}
	public void setFoComment(String foComment) {
		this.foComment = foComment;
	}
	@Column(name="fa_comment")
	public String getFaComment() {
		return faComment;
	}
	public void setFaComment(String faComment) {
		this.faComment = faComment;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_tahdr_id")
	public TAHDR getTahdr() {
		return tahdr;
	}
	public void setTahdr(TAHDR tahdr) {
		this.tahdr = tahdr;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_tahdr_detail_id")
	public TAHDRDetail getTahdrDetail() {
		return tahdrDetail;
	}
	public void setTahdrDetail(TAHDRDetail tahdrDetail) {
		this.tahdrDetail = tahdrDetail;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_bp_org_id")
	public PartnerOrg getPartnerOrg() {
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
	
	@Column(name="is_trader")
	public String getIsTrader() {
		return isTrader;
	}
	public void setIsTrader(String isTrader) {
		this.isTrader = isTrader;
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
	
	@Column(name="gstAmount")
	public BigDecimal getGstAmount() {
		return gstAmount;
	}
	public void setGstAmount(BigDecimal gstAmount) {
		this.gstAmount = gstAmount;
	}
	@Column(name="vendor_type_payment")
	public String getVendorTypePayment() {
		return vendorTypePayment;
	}
	public void setVendorTypePayment(String vendorTypePayment) {
		this.vendorTypePayment = vendorTypePayment;
	}
	@Column(name="realisation_status")
	public String getRealisationStatus() {
		return realisationStatus;
	}
	public void setRealisationStatus(String realisationStatus) {
		this.realisationStatus = realisationStatus;
	}
	@Column(name="realisation_date")
	public Date getRealisationDate() {
		return realisationDate;
	}
	public void setRealisationDate(Date realisationDate) {
		this.realisationDate = realisationDate;
	}
	@Column(name="money_receipt_date")
	public Date getMoneyReceiptDate() {
		return moneyReceiptDate;
	}
	public void setMoneyReceiptDate(Date moneyReceiptDate) {
		this.moneyReceiptDate = moneyReceiptDate;
	}
	@Column(name="money_receipt_no")
	public String getMoneyReceiptNumber() {
		return moneyReceiptNumber;
	}
	public void setMoneyReceiptNumber(String moneyReceiptNumber) {
		this.moneyReceiptNumber = moneyReceiptNumber;
	}
	@Column(name="igst_amount")
	public BigDecimal getIgstAmount() {
		return igstAmount;
	}
	public void setIgstAmount(BigDecimal igstAmount) {
		this.igstAmount = igstAmount;
	}
	@Column(name="cgst_amount")
	public BigDecimal getCgstAmount() {
		return cgstAmount;
	}
	public void setCgstAmount(BigDecimal cgstAmount) {
		this.cgstAmount = cgstAmount;
	}
	@Column(name="sgst_amount")
	public BigDecimal getSgstAmount() {
		return sgstAmount;
	}
	public void setSgstAmount(BigDecimal sgstAmount) {
		this.sgstAmount = sgstAmount;
	}
	
	@Column(name = "doc_no", updatable=false)
	public Long getDocNo() {
		return docNo;
	}
	public void setDocNo(Long docNo) {
		this.docNo = docNo;
	}
	@Column(name = "paymetgateway_response")
	public String getPaymentGatewayStatus() {
		return paymentGatewayStatus;
	}
	public void setPaymentGatewayStatus(String paymentGatewayStatus) {
		this.paymentGatewayStatus = paymentGatewayStatus;
	}
	@Column(name="is_ce_approved")
	public String getIsCEApproved() {
		return isCEApproved;
	}
	public void setIsCEApproved(String isCEApproved) {
		this.isCEApproved = isCEApproved;
	}
	@Column(name = "is_pushed")
	public String getIsPushed() {
		return isPushed;
	}
	public void setIsPushed(String isPushed) {
		this.isPushed = isPushed;
	}
	
	@Column(name = "lv_export")
	public String getLvexport() {
		return lvexport;
	}
	public void setLvexport(String lvexport) {
		this.lvexport = lvexport;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fo_approvedby")
	public User getFoApprovedBy() {
		return foApprovedBy;
	}
	public void setFoApprovedBy(User foApprovedBy) {
		this.foApprovedBy = foApprovedBy;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fa_approvedby")
	public User getFaApprovedBy() {
		return faApprovedBy;
	}
	public void setFaApprovedBy(User faApprovedBy) {
		this.faApprovedBy = faApprovedBy;
	}
	@Column(name="fo_approved_date")
	public Date getFoApprovedDate() {
		return foApprovedDate;
	}
	public void setFoApprovedDate(Date foApprovedDate) {
		this.foApprovedDate = foApprovedDate;
	}
	@Column(name="fa_approved_date")
	public Date getFaApprovedDate() {
		return faApprovedDate;
	}
	public void setFaApprovedDate(Date faApprovedDate) {
		this.faApprovedDate = faApprovedDate;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bg_attachment", referencedColumnName="m_attachment_id")
	public Attachment getBankGuaranteeAttachment() {
		return bankGuaranteeAttachment;
	}
	public void setBankGuaranteeAttachment(Attachment bankGuaranteeAttachment) {
		this.bankGuaranteeAttachment = bankGuaranteeAttachment;
	}
	@Column(name="validity_date")
	public Date getValidityDate() {
		return validityDate;
	}
	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}
	
}
