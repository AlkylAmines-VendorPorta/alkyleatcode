package com.novelerp.appbase.master.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.util.DateUtil;
/**
 * 
 * @author Aman
 */
public class PartnerOrgRegistrationDto extends CommonContextDto{

	private static final long serialVersionUID = 1L;
	private Long partnerOrgRegistrationId;
	/*private Type type;
	private RegistrationType RegistrationType;*/
	private String registrationAuthority;
	private String registrationNo;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date issueDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date validityDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date productCommencement;
	private Set<MaterialDto> itemList;
	private String isUnderRenewal;
	private String isApplicable;
	private AttachmentDto registrationCopy;
	private String registrationType;
	private PartnerOrgDto partnerOrg;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String remark;
	private String isApproved;
    private String validityType;
	private BigDecimal plantInvestment;
	private String natureCode;
	private String categoryCode;
	private BigDecimal monetaryLimit;
    
	public Long getPartnerOrgRegistrationId() {
		return partnerOrgRegistrationId;
	}
	public void setPartnerOrgRegistrationId(Long partnerOrgRegistrationId) {
		this.partnerOrgRegistrationId = partnerOrgRegistrationId;
	}
	public String getRegistrationAuthority() {
		return registrationAuthority;
	}
	public void setRegistrationAuthority(String registrationAuthority) {
		this.registrationAuthority = registrationAuthority;
	}
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
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
	public Date getProductCommencement() {
		return productCommencement;
	}
	public void setProductCommencement(Date productCommencement) {
		this.productCommencement = productCommencement;
	}
	public Set<MaterialDto> getItemList() {
		return itemList;
	}
	public void setItemList(Set<MaterialDto> itemList) {
		this.itemList = itemList;
	}
	public String getIsUnderRenewal() {
		return isUnderRenewal;
	}
	public void setIsUnderRenewal(String isUnderRenewal) {
		this.isUnderRenewal = isUnderRenewal;
	}
	public String getIsApplicable() {
		if(CommonUtil.isStringEmpty(isApplicable)){
			return "N";
		}
		return isApplicable;
	}
	public void setIsApplicable(String isApplicable) {
		this.isApplicable = isApplicable;
	}
	public AttachmentDto getRegistrationCopy() {
		return registrationCopy;
	}
	public void setRegistrationCopy(AttachmentDto registrationCopy) {
		this.registrationCopy = registrationCopy;
	}
	public String getRegistrationType() {
		return registrationType;
	}
	public void setRegistrationType(String registrationType) {
		this.registrationType = registrationType;
	}
	public PartnerOrgDto getPartnerOrg() {
		return partnerOrg;
	}
	public void setPartnerOrg(PartnerOrgDto partnerOrg) {
		this.partnerOrg = partnerOrg;
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
	public String getValidityType() {
		return validityType;
	}
	public void setValidityType(String validityType) {
		this.validityType = validityType;
	}
	public BigDecimal getPlantInvestment() {
		return plantInvestment;
	}
	public void setPlantInvestment(BigDecimal plantInvestment) {
		this.plantInvestment = plantInvestment;
	}
	
	public String getNatureCode() {
		return natureCode;
	}
	public void setNatureCode(String natureCode) {
		this.natureCode = natureCode;
	}
	
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
	public BigDecimal getMonetaryLimit() {
		return monetaryLimit;
	}
	public void setMonetaryLimit(BigDecimal monetaryLimit) {
		this.monetaryLimit = monetaryLimit;
	}
	
}
