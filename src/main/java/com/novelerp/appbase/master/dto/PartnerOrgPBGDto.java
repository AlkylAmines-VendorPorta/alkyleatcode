package com.novelerp.appbase.master.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.util.DateUtil;
/**
 * 
 * @author Aman
 */
public class PartnerOrgPBGDto extends CommonContextDto {

	private static final long serialVersionUID = 1L;
	private Long partnerOrgPbgId;
	private String bankGuaranteeNo;
	private BigDecimal pbgAmount;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date issueDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date validityDate;
	private AttachmentDto bankGauranteeCopy;
	private String isNotApplicable;
	private PartnerOrgDto partnerOrg;
	private String isApproved;
	private String remark; 
	
	
	public Long getPartnerOrgPbgId() {
		return partnerOrgPbgId;
	}
	public void setPartnerOrgPbgId(Long partnerOrgPbgId) {
		this.partnerOrgPbgId = partnerOrgPbgId;
	}
	public String getBankGuaranteeNo() {
		return bankGuaranteeNo;
	}
	public void setBankGuaranteeNo(String bankGuaranteeNo) {
		this.bankGuaranteeNo = bankGuaranteeNo;
	}
	public BigDecimal getPbgAmount() {
		return pbgAmount;
	}
	public void setPbgAmount(BigDecimal pbgAmount) {
		this.pbgAmount = pbgAmount;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getValidityDate() {
		return validityDate;
	}
	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}
	
	
	public String getIsNotApplicable() {
		if(CommonUtil.isStringEmpty(isNotApplicable)){
			return "N";
		}
		return isNotApplicable;
	}
	public void setIsNotApplicable(String isNotApplicable) {
		this.isNotApplicable = isNotApplicable;
	}
	
	public AttachmentDto getBankGauranteeCopy() {
		return bankGauranteeCopy;
	}
	public void setBankGauranteeCopy(AttachmentDto bankGauranteeCopy) {
		this.bankGauranteeCopy = bankGauranteeCopy;
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
	
}
