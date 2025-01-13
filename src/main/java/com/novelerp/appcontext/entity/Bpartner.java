package com.novelerp.appcontext.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appbase.master.entity.LocationType;
import com.novelerp.appbase.master.entity.PartnerBankDetail;
import com.novelerp.appbase.master.entity.PartnerCompanyAddress;
import com.novelerp.appbase.master.entity.PartnerFinancialAttachment;
import com.novelerp.appbase.master.entity.PartnerItemManufacturer;
import com.novelerp.appbase.master.entity.PartnerOrg;
import com.novelerp.appbase.master.entity.PartnerOrgExperience;
import com.novelerp.appbase.master.entity.PartnerOrgPBG;
import com.novelerp.appbase.master.entity.PartnerOrgPerformance;
import com.novelerp.appbase.master.entity.PartnerSignatory;
import com.novelerp.core.entity.PO;
import com.novelerp.eat.entity.PaymentDetail;

/**
 * @author Vivek Birdi
 * 
 *
 */
@Entity
@Table(name = "m_bpartner")
public class Bpartner extends PO {
	
	private static final long serialVersionUID = 1976840623817707666L;
	private Long bPartnerId;
	private String value;
	private String name;
	private String description;
	private String isVendor;
	private String isCustomer;
	private String isEmployee;
	private BPartnerGroup partnerGroup;
	private String isSalesRep;
	private String url;
	private String panNumber;
	private User createdBy;
	private User updatedBy;
	private String crnNumber;
	private String companyType;
	private String contractorType;
	private String isGstApplicable;
	private String gstinNo;
	private String isContractor;
	private String isManufacturer;
	private String isTrader;
	private String status;
	private String isApproved;
	private Timestamp validDate;
	private String remarks;
	private Attachment panCardCopy;
	private Attachment gstinCopy;
	private Attachment companyRegCertificate;
	private String isProfileUpdated;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private Timestamp validTo;
	private Attachment ceSignCopy;
	private Attachment partnerSignCopy;
	private Attachment partnerCoSignCopy;
	private String isEECompDetailApproved;
	private String isCECompDetailApproved;
	private String eeCompDetailComment;
	private String ceCompDetailComment;
	private Attachment partnershipDEEDCopy;
	private Set<UserDetails> partnerCompanyContact;
	private Set<PartnerCompanyAddress> partnerCompanyAddress;
	private Set<PartnerSignatory> partnerSignatory;
	private Set<UserDetails> directoryDetails;
	private Set<PaymentDetail> paymentDetails;
	private Set<PartnerOrg> partnerOrg;
	private Set<PartnerOrgPBG> partnerOrgPBG;
	private Set<PartnerFinancialAttachment> partnerFinancialAttachment;
	private Set<PartnerBankDetail> partnerBankDetail;
	private Set<PartnerItemManufacturer> partnerItemManufacturer;
	private Set<PartnerOrgExperience> partnerTradingExperience;
	private Set<PartnerOrgPerformance> partnerTradingPastPerformance;
/*	private Set<BpartnerApproval> bpartnerApproval;*/
	/*private Set<PaymentDetail> paymentDetail;*/
	private String registrationType;
	 private String isPartnerDeleted; 
    private Double rating;
	private Long rateCount;
    private String isRenewed;
    private String isTraderRenewed;
    private String isManufactureRenewed;
    private String isRegCompleted;
    private User ceApproveBy;
    private User eeApproveBy;
    private String vendorSapCode;
    private OfficeLocation officeLocation;
    private LocationType officeType;
    private String isTraderApproved;
    private Date traderValidTo;
    private String blacklistingReason;
    private String isInfra;
    private String isSetToFinalApproval;
    private String createAndInvite;
    private String vendorType;
//    private User user;

    @Id
	@SequenceGenerator(name = "m_bpartner_seq", sequenceName = "m_bpartner_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_bpartner_seq")
	@Column(name = "m_bpartner_id", updatable = false)
	public Long getbPartnerId() {
		return bPartnerId;
	}

	public void setbPartnerId(Long bPartnerId) {
		this.bPartnerId = bPartnerId;
	}

	@Column(name = "value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	@Column(name = "isvendor")
	public String getIsVendor() {
		return isVendor;
	}

	public void setIsVendor(String isVendor) {
		this.isVendor = isVendor;
	}

	@Column(name = "iscustomer")
	public String getIsCustomer() {
		return isCustomer;
	}

	public void setIsCustomer(String isCustomer) {
		this.isCustomer = isCustomer;
	}

	@Column(name = "isemployee")
	public String getIsEmployee() {
		return isEmployee;
	}

	public void setIsEmployee(String isEmployee) {
		this.isEmployee = isEmployee;
	}

	@Column(name = "issalesrep")
	public String getIsSalesRep() {
		return isSalesRep;
	}

	public void setIsSalesRep(String isSalesRep) {
		this.isSalesRep = isSalesRep;
	}

	@Column(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "pan_no")
	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	
//	@OneToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="m_bpartner_id")
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="createdBy")
	public User getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="updatedBy")
	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name="crn_number")
	public String getCrnNumber() {
		return crnNumber;
	}

	public void setCrnNumber(String crnNumber) {
		this.crnNumber = crnNumber;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_bpartner_group_id")
	public BPartnerGroup getPartnerGroup() {
		return partnerGroup;
	}

	public void setPartnerGroup(BPartnerGroup partnerGroup) {
		this.partnerGroup = partnerGroup;
	}

	@Column(name="company_type")
	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	@Column(name="contractor_type")
	public String getContractorType() {
		return contractorType;
	}

	public void setContractorType(String contractorType) {
		this.contractorType = contractorType;
	}

	@Column(name="isgstapplicable")
	public String getIsGstApplicable() {
		return isGstApplicable;
	}

	public void setIsGstApplicable(String isGstApplicable) {
		this.isGstApplicable = isGstApplicable;
	}

	@Column(name="gstin_no")
	public String getGstinNo() {
		return gstinNo;
	}

	public void setGstinNo(String gstinNo) {
		this.gstinNo = gstinNo;
	}

	@Column(name="iscontractor")
	public String getIsContractor() {
		return isContractor;
	}

	public void setIsContractor(String isContractor) {
		this.isContractor = isContractor;
	}
	@Column(name="ismanufacturer")
	public String getIsManufacturer() {
		return isManufacturer;
	}

	public void setIsManufacturer(String isManufacturer) {
		this.isManufacturer = isManufacturer;
	}

	@Column(name="istrader")
	public String getIsTrader() {
		return isTrader;
	}

	public void setIsTrader(String isTrader) {
		this.isTrader = isTrader;
	}

	@OneToMany(mappedBy="partner",fetch=FetchType.LAZY)
	@Where(clause = "user_detail_type='COMPUSR'")
	public Set<UserDetails> getPartnerCompanyContact() {
		return partnerCompanyContact;
	}

	public void setPartnerCompanyContact(Set<UserDetails> partnerCompanyContact) {
		this.partnerCompanyContact = partnerCompanyContact;
	}
	@OneToMany(mappedBy="partner",fetch=FetchType.LAZY)
	public Set<PartnerCompanyAddress> getPartnerCompanyAddress() {
		return partnerCompanyAddress;
	}

	public void setPartnerCompanyAddress(Set<PartnerCompanyAddress> partnerCompanyAddress) {
		this.partnerCompanyAddress = partnerCompanyAddress;
	}
	@OneToMany(mappedBy="partner",fetch=FetchType.LAZY)
	@Where(clause=" isactive='Y' ")
	public Set<PartnerSignatory> getPartnerSignatory() {
		return partnerSignatory;
	}

	public void setPartnerSignatory(Set<PartnerSignatory> partnerSignatory) {
		this.partnerSignatory = partnerSignatory;
	}
	@OneToMany(mappedBy="partner",fetch=FetchType.LAZY)
	@Where(clause = "user_detail_type='COMPDIR' AND isactive='Y' " )
	public Set<UserDetails> getDirectoryDetails() {
		return directoryDetails;
	}

	public void setDirectoryDetails(Set<UserDetails> directoryDetails) {
		this.directoryDetails = directoryDetails;
	}

	@OneToMany(mappedBy="partner",fetch=FetchType.LAZY)
	public Set<PaymentDetail> getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(Set<PaymentDetail> paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	@OneToMany(mappedBy="partner",fetch=FetchType.LAZY)
	@Where(clause=" m_bp_item_manufacturer_id IS NULL AND isactive='Y' ")
	public Set<PartnerOrg> getPartnerOrg() {
		return partnerOrg;
	}

	public void setPartnerOrg(Set<PartnerOrg> partnerOrg) {
		this.partnerOrg = partnerOrg;
	}
	@OneToMany(mappedBy="partner",fetch=FetchType.LAZY)
	public Set<PartnerOrgPBG> getPartnerOrgPBG() {
		return partnerOrgPBG;
	}

	public void setPartnerOrgPBG(Set<PartnerOrgPBG> partnerOrgPBG) {
		this.partnerOrgPBG = partnerOrgPBG;
	}
	@OneToMany(mappedBy="partner",fetch=FetchType.LAZY)
	public Set<PartnerFinancialAttachment> getPartnerFinancialAttachment() {
		return partnerFinancialAttachment;
	}

	public void setPartnerFinancialAttachment(Set<PartnerFinancialAttachment> partnerFinancialAttachment) {
		this.partnerFinancialAttachment = partnerFinancialAttachment;
	}
	@OneToMany(mappedBy="partner",fetch=FetchType.LAZY)
	public Set<PartnerBankDetail> getPartnerBankDetail() {
		return partnerBankDetail;
	}

	public void setPartnerBankDetail(Set<PartnerBankDetail> partnerBankDetail) {
		this.partnerBankDetail = partnerBankDetail;
	}
	@OneToMany(mappedBy="partner",fetch=FetchType.LAZY)
	@Where(clause=" isactive='Y' ")
	public Set<PartnerItemManufacturer> getPartnerItemManufacturer() {
		return partnerItemManufacturer;
	}

	public void setPartnerItemManufacturer(Set<PartnerItemManufacturer> partnerItemManufacturer) {
		this.partnerItemManufacturer = partnerItemManufacturer;
	}
  
    @Column(name="isapproved")
	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
    @Column(name="valid_date")
	public Timestamp getValidDate() {
		return validDate;
	}

	public void setValidDate(Timestamp validDate) {
		this.validDate = validDate;
	}
    @Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    @Column(name="status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	/*@OneToMany(mappedBy="approvalPartner",fetch=FetchType.LAZY)
	public Set<BpartnerApproval> getBpartnerApproval() {
		return bpartnerApproval;
	}

	public void setBpartnerApproval(Set<BpartnerApproval> bpartnerApproval) {
		this.bpartnerApproval = bpartnerApproval;
	}*/

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pancard_copy",referencedColumnName="m_attachment_id")
	public Attachment getPanCardCopy() {
		if(panCardCopy==null || panCardCopy.getAttachmentId()==null)
		{
			return null;
		}
		return panCardCopy;
	}

	public void setPanCardCopy(Attachment panCardCopy) {
		this.panCardCopy = panCardCopy;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gstin_copy",referencedColumnName="m_attachment_id")
	public Attachment getGstinCopy() {
		if(gstinCopy==null || gstinCopy.getAttachmentId()==null)
		{
			return null;
		}
		return gstinCopy;
	}

	public void setGstinCopy(Attachment gstinCopy) {
		this.gstinCopy = gstinCopy;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="company_reg_certificate",referencedColumnName="m_attachment_id")
	public Attachment getCompanyRegCertificate() {
		if(companyRegCertificate==null || companyRegCertificate.getAttachmentId()==null)
		{
			return null;
		}
		return companyRegCertificate;
	}

	public void setCompanyRegCertificate(Attachment companyRegCertificate) {
		this.companyRegCertificate = companyRegCertificate;
	}

    @Column(name="isprofileupdated")
	public String getIsProfileUpdated() {
		return isProfileUpdated;
	}
	public void setIsProfileUpdated(String isProfileUpdated) {
		this.isProfileUpdated = isProfileUpdated;
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
	/*@OneToMany(mappedBy="partner",fetch=FetchType.LAZY)
	public Set<PaymentDetail> getPaymentDetail() {
		return paymentDetail;
	}

	public void setPaymentDetail(Set<PaymentDetail> paymentDetail) {
		this.paymentDetail = paymentDetail;
	}*/
    @Column(name="valid_to")
	public Timestamp getValidTo() {
		return validTo;
	}

	public void setValidTo(Timestamp validTo) {
		this.validTo = validTo;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ce_sign_copy",referencedColumnName="m_attachment_id")
	public Attachment getCeSignCopy() {
		if(ceSignCopy==null || ceSignCopy.getAttachmentId()==null)
		{
		    return null;
		}
		return ceSignCopy;
	}

	public void setCeSignCopy(Attachment ceSignCopy) {
		this.ceSignCopy = ceSignCopy;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="partner_sign_copy",referencedColumnName="m_attachment_id")
	public Attachment getPartnerSignCopy() {
		if(partnerSignCopy==null || partnerSignCopy.getAttachmentId()==null)
		{
			return null;
		}
		return partnerSignCopy;
	}

	public void setPartnerSignCopy(Attachment partnerSignCopy) {
		this.partnerSignCopy = partnerSignCopy;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="partner_co_sign_copy",referencedColumnName="m_attachment_id")
	public Attachment getPartnerCoSignCopy() {
		if(partnerCoSignCopy==null || partnerCoSignCopy.getAttachmentId()==null)
		{
			return null;
		}
		return partnerCoSignCopy;
	}

	public void setPartnerCoSignCopy(Attachment partnerCoSignCopy) {
		this.partnerCoSignCopy = partnerCoSignCopy;
	}
	@Column(name="registration_type")
	public String getRegistrationType() {
		return registrationType;
	}

	public void setRegistrationType(String registrationType) {
		this.registrationType = registrationType;
	}
	
    @Column(name="is_ee_comp_detail_approved")
	public String getIsEECompDetailApproved() {
		return isEECompDetailApproved;
	}
	public void setIsEECompDetailApproved(String isEECompDetailApproved) {
		this.isEECompDetailApproved = isEECompDetailApproved;
	}
	@Column(name="is_ce_comp_detail_approved")
	public String getIsCECompDetailApproved() {
		return isCECompDetailApproved;
	}

	public void setIsCECompDetailApproved(String isCECompDetailApproved) {
		this.isCECompDetailApproved = isCECompDetailApproved;
	}
    @Column(name="ee_comp_detail_comment")
	public String getEeCompDetailComment() {
		return eeCompDetailComment;
	}

	public void setEeCompDetailComment(String eeCompDetailComment) {
		this.eeCompDetailComment = eeCompDetailComment;
	}
    @Column(name="ce_comp_detail_comment")
	public String getCeCompDetailComment() {
		return ceCompDetailComment;
	}

	public void setCeCompDetailComment(String ceCompDetailComment) {
		this.ceCompDetailComment = ceCompDetailComment;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="partnership_deed_copy",referencedColumnName="m_attachment_id")
	public Attachment getPartnershipDEEDCopy() {
		if(partnershipDEEDCopy==null || partnershipDEEDCopy.getAttachmentId()==null)
		{
			return null;
		}
		return partnershipDEEDCopy;
	}

	public void setPartnershipDEEDCopy(Attachment partnershipDEEDCopy) {
		this.partnershipDEEDCopy = partnershipDEEDCopy;
	}
    @Column(name="is_partner_deleted")
	public String getIsPartnerDeleted() {
		return isPartnerDeleted;
	}

	public void setIsPartnerDeleted(String isPartnerDeleted) {
		this.isPartnerDeleted = isPartnerDeleted;
	}
	
	@Column(name = "rating")
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	
	@Column(name = "rate_count")
	public Long getRateCount() {
		return rateCount;
	}
	public void setRateCount(Long rateCount) {
		this.rateCount = rateCount;
	}
    @Column(name="is_renewed")
	public String getIsRenewed() {
		return isRenewed;
	}

	public void setIsRenewed(String isRenewed) {
		this.isRenewed = isRenewed;
	}
    @Column(name="is_trader_renewed")
	public String getIsTraderRenewed() {
		return isTraderRenewed;
	}

	public void setIsTraderRenewed(String isTraderRenewed) {
		this.isTraderRenewed = isTraderRenewed;
	}
	@Column(name="is_manufacture_renewed")
	public String getIsManufactureRenewed() {
		return isManufactureRenewed;
	}

	public void setIsManufactureRenewed(String isManufactureRenewed) {
		this.isManufactureRenewed = isManufactureRenewed;
	}
    @Column(name="is_reg_completed")
	public String getIsRegCompleted() {
		return isRegCompleted;
	}

	public void setIsRegCompleted(String isRegCompleted) {
		this.isRegCompleted = isRegCompleted;
	}

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ce_approveBy")
	public User getCeApproveBy() {
		return ceApproveBy;
	}

	public void setCeApproveBy(User ceApproveBy) {
		this.ceApproveBy = ceApproveBy;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ee_approveBy")
	public User getEeApproveBy() {
		return eeApproveBy;
	}

	public void setEeApproveBy(User eeApproveBy) {
		this.eeApproveBy = eeApproveBy;
	}
    @Column(name="vendor_sap_code")
	public String getVendorSapCode() {
		return vendorSapCode;
	}

	public void setVendorSapCode(String vendorSapCode) {
		this.vendorSapCode = vendorSapCode;
	}
	@OneToMany(mappedBy="partner",fetch=FetchType.LAZY)
	@Where(clause="m_bp_org_id IS NULL ")
	public Set<PartnerOrgExperience> getPartnerTradingExperience() {
		return partnerTradingExperience;
	}

	public void setPartnerTradingExperience(Set<PartnerOrgExperience> partnerTradingExperience) {
		this.partnerTradingExperience = partnerTradingExperience;
	}
	@OneToMany(mappedBy="partner",fetch=FetchType.LAZY)
	@Where(clause=" m_bp_org_id IS NULL ")
	public Set<PartnerOrgPerformance> getPartnerTradingPastPerformance() {
		return partnerTradingPastPerformance;
	}

	public void setPartnerTradingPastPerformance(Set<PartnerOrgPerformance> partnerTradingPastPerformance) {
		this.partnerTradingPastPerformance = partnerTradingPastPerformance;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "m_location_type_id")
	public LocationType getOfficeType() {
		return officeType;
	}

	public void setOfficeType(LocationType officeType) {
		this.officeType = officeType;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "m_office_location_id")
	public OfficeLocation getOfficeLocation() {
		return officeLocation;
	}

	public void setOfficeLocation(OfficeLocation officeLocation) {
		this.officeLocation = officeLocation;
	}
	@Column(name="is_trader_approved")
	public String getIsTraderApproved() {
		return isTraderApproved;
	}

	public void setIsTraderApproved(String isTraderApproved) {
		this.isTraderApproved = isTraderApproved;
	}
    @Column(name="trader_valid_to")
	public Date getTraderValidTo() {
		return traderValidTo;
	}

	public void setTraderValidTo(Date traderValidTo) {
		this.traderValidTo = traderValidTo;
	}
	@Column(name="blacklisting_comment")
	public String getBlacklistingReason() {
		return blacklistingReason;
	}

	public void setBlacklistingReason(String blacklistingReason) {
		this.blacklistingReason = blacklistingReason;
	}
    @Column(name="isinfra")
	public String getIsInfra() {
		return isInfra;
	}

	public void setIsInfra(String isInfra) {
		this.isInfra = isInfra;
	} 

    @Column(name="is_set_to_final_approval")
	public String getIsSetToFinalApproval() {
		return isSetToFinalApproval;
	}

	public void setIsSetToFinalApproval(String isSetToFinalApproval) {
		this.isSetToFinalApproval = isSetToFinalApproval;
	}
	@Column(name="create_invite")
	public String getCreateAndInvite() {
		return createAndInvite;
	}

	public void setCreateAndInvite(String createAndInvite) {
		this.createAndInvite = createAndInvite;
	}

	@Column(name="vendor_type")
	public String getVendorType() {
		return vendorType;
	}

	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}


	
}
