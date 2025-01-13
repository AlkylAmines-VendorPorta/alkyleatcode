package com.novelerp.appbase.master.entity;

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

import com.novelerp.appcontext.entity.ContextPO;
/**
 * 
 * @author Aman
 */
@Entity
@Table(name="m_bp_org_registration")
public class PartnerOrgRegistration  extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long partnerOrgRegistrationId;
	private String registrationAuthority;
	private String registrationNo;
	private Date issueDate;
	private Date validityDate;
	private Date productCommencement;
	private String isUnderRenewal;
	private String isApplicable;
	private Attachment registrationCopy;
	private String registrationType;
	private PartnerOrg partnerOrg;
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
	
	@Id
	@SequenceGenerator(name="m_bp_org_registration_seq",sequenceName="m_bp_org_registration_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_org_registration_seq")	
	@Column(name = "m_bp_org_registration_id", updatable=false)
	public Long getPartnerOrgRegistrationId() {
		return partnerOrgRegistrationId;
	}
	public void setPartnerOrgRegistrationId(Long partnerOrgRegistrationId) {
		this.partnerOrgRegistrationId = partnerOrgRegistrationId;
	}

	/*@Column(name="type")
	
	@Column(name="registration_type")*/
	
	@Column(name="registration_authority")
	public String getRegistrationAuthority() {
		return registrationAuthority;
	}
	
	public void setRegistrationAuthority(String registrationAuthority) {
		this.registrationAuthority = registrationAuthority;
	}
	@Column(name="registration_no")
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
	@Column(name="valid_from")
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	@Column(name="valid_to")
	public Date getValidityDate() {
		return validityDate;
	}
	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}
	@Column(name="commencement_date")
	public Date getProductCommencement() {
		return productCommencement;
	}
	public void setProductCommencement(Date productCommencement) {
		this.productCommencement = productCommencement;
	}
	/*@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="m_material_id")
	public Set<Material> getItemList() {
		return itemList;
	}
	public void setItemList(Set<Material> itemList) {
		this.itemList = itemList;
	}*/
	@Column(name="isunderrenewal")
	public String getIsUnderRenewal() {
		return isUnderRenewal;
	}
	public void setIsUnderRenewal(String isUnderRenewal) {
		this.isUnderRenewal = isUnderRenewal;
	}
	@Column(name="isapplicable")
	public String getIsApplicable() {
		return isApplicable;
	}
	public void setIsApplicable(String isApplicable) {
		this.isApplicable = isApplicable;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_attachment_id")
	public Attachment getRegistrationCopy() {
		if(registrationCopy == null || registrationCopy.getAttachmentId() ==null){
			return null;
		}
		return registrationCopy;
	}
	public void setRegistrationCopy(Attachment registrationCopy) {
		this.registrationCopy = registrationCopy;
	}
	@Column(name="registration_type")
	public String getRegistrationType() {
		return registrationType;
	}
	public void setRegistrationType(String registrationType) {
		this.registrationType = registrationType;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_bp_org_id")
	public PartnerOrg getPartnerOrg() {
		return partnerOrg;
	}
	public void setPartnerOrg(PartnerOrg partnerOrg) {
		this.partnerOrg = partnerOrg;
	}
	@Column(name="is_ee_approved")
	public String getIsEEApproved() {
		return isEEApproved;
	}
	public void setIsEEApproved(String isEEApproved) {
		this.isEEApproved = isEEApproved;
	}
	@Column(name="ee_comment")
	public String getEeComment() {
		return eeComment;
	}
	public void setEeComment(String eeComment) {
		this.eeComment = eeComment;
	}
	@Column(name="is_ce_approved")
	public String getIsCEApproved() {
		return isCEApproved;
	}
	public void setIsCEApproved(String isCEApproved) {
		this.isCEApproved = isCEApproved;
	}
	@Column(name="ce_comment")
	public String getCeComment() {
		return ceComment;
	}
	public void setCeComment(String ceComment) {
		this.ceComment = ceComment;
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
	@Column(name="validity_type")
	public String getValidityType() {
		return validityType;
	}
	public void setValidityType(String validityType) {
		this.validityType = validityType;
	}
	
	@Column(name="plant_investment")
	public BigDecimal getPlantInvestment() {
		return plantInvestment;
	}
	public void setPlantInvestment(BigDecimal plantInvestment) {
		this.plantInvestment = plantInvestment;
	}
	
	@Column(name="nature_code")
	public String getNatureCode() {
		return natureCode;
	}
	public void setNatureCode(String natureCode) {
		this.natureCode = natureCode;
	}
	
	@Column(name="category_code")
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
	@Column(name="monetary_limit")
	public BigDecimal getMonetaryLimit() {
		return monetaryLimit;
	}
	public void setMonetaryLimit(BigDecimal monetaryLimit) {
		this.monetaryLimit = monetaryLimit;
	}
	
}
