package com.novelerp.appbase.master.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.core.util.DateUtil;

public class PartnerFinancialAttachmentDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	private Long financialAttachmentId;
	private AttachmentDto attachment;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date validFrom;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date validTo;
	private double amount;
	private String finacialType;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="financialYearId",scope=Long.class)
//	private FinancialYearDto financialYear;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String remark;
	private String isApproved;
	private String financialYearNew;
	
	public Long getFinancialAttachmentId() {
		return financialAttachmentId;
	}
	public void setFinancialAttachmentId(Long financialAttachmentId) {
		this.financialAttachmentId = financialAttachmentId;
	}
	
	public AttachmentDto getAttachment() {
		return attachment;
	}
	public void setAttachment(AttachmentDto attachment) {
		this.attachment = attachment;
	}
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	public Date getValidTo() {
		return validTo;
	}
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getFinacialType() {
		return finacialType;
	}
	public void setFinacialType(String finacialType) {
		this.finacialType = finacialType;
	}
//	public FinancialYearDto getFinancialYear() {
//		return financialYear;
//	}
//	public void setFinancialYear(FinancialYearDto financialYear) {
//		this.financialYear = financialYear;
//	}
	public String getIsEEApproved() {
		return isEEApproved;
	}
	public void setIsEEApproved(String isEEApproved) {
		this.isEEApproved = isEEApproved;
	}
	public String getEeComment() {
		return eeComment;
	}
	public void setEeComment(String eeComment) {
		this.eeComment = eeComment;
	}
	public String getIsCEApproved() {
		return isCEApproved;
	}
	public void setIsCEApproved(String isCEApproved) {
		this.isCEApproved = isCEApproved;
	}
	public String getCeComment() {
		return ceComment;
	}
	public void setCeComment(String ceComment) {
		this.ceComment = ceComment;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	public String getFinancialYearNew() {
		return financialYearNew;
	}
	public void setFinancialYearNew(String financialYearNew) {
		this.financialYearNew = financialYearNew;
	}	
}