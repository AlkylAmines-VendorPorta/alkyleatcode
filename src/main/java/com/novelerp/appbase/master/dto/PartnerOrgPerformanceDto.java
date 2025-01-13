package com.novelerp.appbase.master.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.core.util.DateUtil;

public class PartnerOrgPerformanceDto extends CommonContextDto{

	private static final long serialVersionUID = 1L;
	private Long partnerOrgPerformanceId;
	private MaterialDto material;
	private String firmName;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date orderStartDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date orderEndDate;
	private String quantitySupplied;
	private String referenc1;
	private String referenc2;
	private AttachmentDto certificateAward;
	private PartnerOrgDto partnerOrg;
	private String isApproved;
	private String remark;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String poNumber;
	public Long getPartnerOrgPerformanceId() {
		return partnerOrgPerformanceId;
	}
	public void setPartnerOrgPerformanceId(Long partnerOrgPerformanceId) {
		this.partnerOrgPerformanceId = partnerOrgPerformanceId;
	}
	public Date getOrderStartDate() {
		return orderStartDate;
	}
	public void setOrderStartDate(Date orderStartDate) {
		this.orderStartDate = orderStartDate;
	}
	public Date getOrderEndDate() {
		return orderEndDate;
	}
	public void setOrderEndDate(Date orderEndDate) {
		this.orderEndDate = orderEndDate;
	}
	public String getQuantitySupplied() {
		return quantitySupplied;
	}
	public void setQuantitySupplied(String quantitySupplied) {
		this.quantitySupplied = quantitySupplied;
	}
	public String getReferenc1() {
		return referenc1;
	}
	public void setReferenc1(String referenc1) {
		this.referenc1 = referenc1;
	}
	public String getReferenc2() {
		return referenc2;
	}
	public void setReferenc2(String referenc2) {
		this.referenc2 = referenc2;
	}
	
	public String getFirmName() {
		return firmName;
	}
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	public AttachmentDto getCertificateAward() {
		return certificateAward;
	}
	public void setCertificateAward(AttachmentDto certificateAward) {
		this.certificateAward = certificateAward;
	}
	public MaterialDto getMaterial() {
		return material;
	}
	public void setMaterial(MaterialDto material) {
		this.material = material;
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
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	
}
