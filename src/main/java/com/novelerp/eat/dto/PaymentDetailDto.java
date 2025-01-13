package com.novelerp.eat.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appbase.master.dto.PaymentTypeDto;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.util.DateUtil;

/**
 * 
 * @author ankita
 *
 */

public class PaymentDetailDto extends CommonContextDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long paymentDetailId;
	private PaymentTypeDto paymentType;
	private String paymentMode;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="tahdrId",scope=Long.class)
	private TAHDRDto tahdr;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="tahdrDetailId",scope=Long.class)
	private TAHDRDetailDto tahdrDetail;
	private BigDecimal amount;
	private BigDecimal gst;
	private BigDecimal total;
	private String bankName;
	private String branchName;
	private String micrCode;
	private String referenceNo;
	
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date paymentDate;
	private String isFOApproved;
	private String isFAApproved;
	private String foComment;
	private String faComment;
	private PartnerOrgDto partnerOrg;
	private String isApproved;
	private String remark;
	private String isTrader;
	private BigDecimal igst;
	private BigDecimal cgst;
	private BigDecimal sgst;
	private BigDecimal gstAmount;
	private String vendorTypePayment;
	private String realisationStatus;
	private Long docNo;
	private String isPushed;
	private String lvexport;
	
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date realisationDate;
	
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date moneyReceiptDate;
	
	private String moneyReceiptNumber;
	private BigDecimal igstAmount;
	private BigDecimal cgstAmount;
	private BigDecimal sgstAmount;
	private String paymentGatewayStatus;
	private boolean isVendorPayment=false;
	private String isCEApproved;
	private UserDto foApprovedBy;
	private UserDto faApprovedBy;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date foApprovedDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date faApprovedDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date validityDate;
	private AttachmentDto bankGuaranteeAttachment;
	
	
	public Long getPaymentDetailId() {
		return paymentDetailId;
	}
	public void setPaymentDetailId(Long paymentDetailId) {
		this.paymentDetailId = paymentDetailId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getGst() {
		return gst;
	}
	public void setGst(BigDecimal gst) {
		this.gst = gst;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
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
	public String getMicrCode() {
		return micrCode;
	}
	public void setMicrCode(String micrCode) {
		this.micrCode = micrCode;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getIsFOApproved() {
		return isFOApproved;
	}
	public void setIsFOApproved(String isFOApproved) {
		this.isFOApproved = isFOApproved;
	}
	public String getIsFAApproved() {
		return isFAApproved;
	}
	public void setIsFAApproved(String isFAApproved) {
		this.isFAApproved = isFAApproved;
	}
	public PaymentTypeDto getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(PaymentTypeDto paymentType) {
		this.paymentType = paymentType;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getFoComment() {
		return foComment;
	}
	public void setFoComment(String foComment) {
		this.foComment = foComment;
	}
	public String getFaComment() {
		return faComment;
	}
	public void setFaComment(String faComment) {
		this.faComment = faComment;
	}
	public TAHDRDto getTahdr() {
		return tahdr;
	}
	public void setTahdr(TAHDRDto tahdr) {
		this.tahdr = tahdr;
	}
	public TAHDRDetailDto getTahdrDetail() {
		return tahdrDetail;
	}
	public void setTahdrDetail(TAHDRDetailDto tahdrDetail) {
		this.tahdrDetail= tahdrDetail;
	}
	public PartnerOrgDto getPartnerOrg() {
		return partnerOrg;
	}
	public void setPartnerOrg(PartnerOrgDto partnerOrg) {
		this.partnerOrg = partnerOrg;
	}
	public String getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getIsTrader() {
		if(CommonUtil.isStringEmpty(isTrader)){
			isTrader="N";
		}
		return isTrader;
	}
	public void setIsTrader(String isTrader) {
		this.isTrader = isTrader;
	}
	
	public BigDecimal getIgst() {
		return igst;
	}
	public void setIgst(BigDecimal igst) {
		this.igst = igst;
	}
	
	public BigDecimal getCgst() {
		return cgst;
	}
	public void setCgst(BigDecimal cgst) {
		this.cgst = cgst;
	}
	
	public BigDecimal getSgst() {
		return sgst;
	}
	public void setSgst(BigDecimal sgst) {
		this.sgst = sgst;
	}
	
	public BigDecimal getGstAmount() {
		return gstAmount;
	}
	public void setGstAmount(BigDecimal gstAmount) {
		this.gstAmount = gstAmount;
	}
	public String getVendorTypePayment() {
		return vendorTypePayment;
	}
	public void setVendorTypePayment(String vendorTypePayment) {
		this.vendorTypePayment = vendorTypePayment;
	}
	public String getRealisationStatus() {
		return realisationStatus;
	}
	public void setRealisationStatus(String realisationStatus) {
		this.realisationStatus = realisationStatus;
	}
	public Date getRealisationDate() {
		return realisationDate;
	}
	public void setRealisationDate(Date realisationDate) {
		this.realisationDate = realisationDate;
	}
	public Date getMoneyReceiptDate() {
		return moneyReceiptDate;
	}
	public void setMoneyReceiptDate(Date moneyReceiptDate) {
		this.moneyReceiptDate = moneyReceiptDate;
	}
	public String getMoneyReceiptNumber() {
		return moneyReceiptNumber;
	}
	public void setMoneyReceiptNumber(String moneyReceiptNumber) {
		this.moneyReceiptNumber = moneyReceiptNumber;
	}
	public BigDecimal getIgstAmount() {
		return igstAmount;
	}
	public void setIgstAmount(BigDecimal igstAmount) {
		this.igstAmount = igstAmount;
	}
	public BigDecimal getCgstAmount() {
		return cgstAmount;
	}
	public void setCgstAmount(BigDecimal cgstAmount) {
		this.cgstAmount = cgstAmount;
	}
	public BigDecimal getSgstAmount() {
		return sgstAmount;
	}
	public void setSgstAmount(BigDecimal sgstAmount) {
		this.sgstAmount = sgstAmount;
	}
	public Long getDocNo() {
		return docNo;
	}
	public void setDocNo(Long docNo) {
		this.docNo = docNo;
	}
	public String getPaymentGatewayStatus() {
		return paymentGatewayStatus;
	}
	public void setPaymentGatewayStatus(String paymentGatewayStatus) {
		this.paymentGatewayStatus = paymentGatewayStatus;
	}
	public boolean isVendorPayment() {
		return isVendorPayment;
	}
	public void setVendorPayment(boolean isVendorPayment) {
		this.isVendorPayment = isVendorPayment;
	}
	public String getIsCEApproved() {
		return isCEApproved;
	}
	public void setIsCEApproved(String isCEApproved) {
		this.isCEApproved = isCEApproved;
	}
	public String getIsPushed() {
		if(isPushed == null){
			isPushed = "N";
		}
		return isPushed;
	}
	public void setIsPushed(String isPushed) {
		this.isPushed = isPushed;
	}
	public UserDto getFoApprovedBy() {
		return foApprovedBy;
	}
	public void setFoApprovedBy(UserDto foApprovedBy) {
		this.foApprovedBy = foApprovedBy;
	}
	public UserDto getFaApprovedBy() {
		return faApprovedBy;
	}
	public void setFaApprovedBy(UserDto faApprovedBy) {
		this.faApprovedBy = faApprovedBy;
	}
	public String getLvexport() {
		return lvexport;
	}
	public void setLvexport(String lvexport) {
		this.lvexport = lvexport;
	}
	public Date getFoApprovedDate() {
		return foApprovedDate;
	}
	public void setFoApprovedDate(Date foApprovedDate) {
		this.foApprovedDate = foApprovedDate;
	}
	public Date getFaApprovedDate() {
		return faApprovedDate;
	}
	public void setFaApprovedDate(Date faApprovedDate) {
		this.faApprovedDate = faApprovedDate;
	}
	
	public Date getValidityDate() {
		return validityDate;
	}
	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}
	public AttachmentDto getBankGuaranteeAttachment() {
		return bankGuaranteeAttachment;
	}
	public void setBankGuaranteeAttachment(AttachmentDto bankGuaranteeAttachment) {
		this.bankGuaranteeAttachment = bankGuaranteeAttachment;
	}
	
	
}