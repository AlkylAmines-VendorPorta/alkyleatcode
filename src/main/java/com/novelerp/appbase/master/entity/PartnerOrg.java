package com.novelerp.appbase.master.entity;

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

import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.appcontext.entity.Location;
import com.novelerp.eat.entity.PaymentDetail;
/**
 * 
 * @author Aman
 */
@Entity
@Table(name="m_bp_org")
public class PartnerOrg extends ContextPO {
	
	private static final long serialVersionUID = 1L;
	private Long partnerOrgId;
	private String name;
	private Date estdDate;
	private String inspectionReportNo;
	private String licenseType;
	private String licenceNo;
	private Date licenceValidityDate;
	private Attachment licenceCopy;
	private Attachment machinaryListCopy;
	private Attachment inspectionReportCopy;
	private Date inspectionDate;
	private String manPower;
	private Location location;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String remark;
	private String isApproved;
	private PaymentDetail paymentDetail;
	private Timestamp validTo;
	private Attachment staffListCopy;
	private Attachment authorizationCertificate;
	private PartnerItemManufacturer partnerItemManufacturer;
	private String isFactoryInspected;
	private String isRenewed;
	private Attachment testingEquipmentDetails;
	private Set<PartnerOrgUser> partnerOrgUser;
	private Set<PartnerOrgExperience> partnerOrgExperience;
	private Set<PartnerOrgCertification> partnerOrgCertification;
	private Set<PartnerOrgRegistration> partnerOrgRegistration;
	private Set<PartnerOrgBIS> partnerOrgBIS;
	private Set<PartnerOrgProduct> partnerOrgProduct;
	private Set<PartnerOrgPerformance> partnerOrgPerformance;
	private Set<PartnerOrgRDAEC> partnerOrgRDAEC;
	private Set<PartnerOrgOE> partnerOrgOE;
	private Set<PartnerOrgInspection> partnerOrgInspection;
	
	
	@Id
	@SequenceGenerator(name="m_bp_org_seq",sequenceName="m_bp_org_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_org_seq")	
	@Column(name = "m_bp_org_id", updatable=false)
	public Long getPartnerOrgId() {
		return partnerOrgId;
	}
	public void setPartnerOrgId(Long partnerOrgId) {
		this.partnerOrgId = partnerOrgId;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="estd_date")
	public Date getEstdDate() {
		return estdDate;
	}
	public void setEstdDate(Date estdDate) {
		this.estdDate = estdDate;
	}
	@Column(name="inspection_reportno")
	public String getInspectionReportNo() {
		return inspectionReportNo;
	}
	public void setInspectionReportNo(String inspectionReportNo) {
		this.inspectionReportNo = inspectionReportNo;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="c_location_id")
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	@Column(name="licence_no")
	public String getLicenceNo() {
		return licenceNo;
	}
	
	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}
	@Column(name="valid_from")
	public Date getLicenceValidityDate() {
		return licenceValidityDate;
	}
	public void setLicenceValidityDate(Date licenceValidityDate) {
		this.licenceValidityDate = licenceValidityDate;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="licencecopy",referencedColumnName="m_attachment_id")
	public Attachment getLicenceCopy() {
		if(licenceCopy==null || licenceCopy.getAttachmentId()==null)
		{
			return null;
		}
		return licenceCopy;
	}
	public void setLicenceCopy(Attachment licenceCopy) {
		this.licenceCopy = licenceCopy;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="machinarycopy",referencedColumnName="m_attachment_id")
	public Attachment getMachinaryListCopy() {
		if(machinaryListCopy==null || machinaryListCopy.getAttachmentId()==null)
		{
			return null;
		}
		return machinaryListCopy;
	}
	public void setMachinaryListCopy(Attachment machinaryListCopy) {
		this.machinaryListCopy = machinaryListCopy;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="inspection_report_copy",referencedColumnName="m_attachment_id")
	public Attachment getInspectionReportCopy() {
		if(inspectionReportCopy==null || inspectionReportCopy.getAttachmentId()==null)
		{
			return null;
		}
		return inspectionReportCopy;
	}
	public void setInspectionReportCopy(Attachment inspectionReportCopy) {
		this.inspectionReportCopy = inspectionReportCopy;
	}
	@Column(name="license_type")
	public String getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	@Column(name="manPower")
	public String getManPower() {
		return manPower;
	}
	public void setManPower(String manPower) {
		this.manPower = manPower;
	}
	@OneToMany(mappedBy="partnerOrg",fetch=FetchType.LAZY)
	public Set<PartnerOrgUser> getPartnerOrgUser() {
		return partnerOrgUser;
	}
	public void setPartnerOrgUser(Set<PartnerOrgUser> partnerOrgUser) {
		this.partnerOrgUser = partnerOrgUser;
	}
	@OneToMany(mappedBy="partnerOrg",fetch=FetchType.LAZY)
	public Set<PartnerOrgExperience> getPartnerOrgExperience() {
		return partnerOrgExperience;
	}
	public void setPartnerOrgExperience(Set<PartnerOrgExperience> partnerOrgExperience) {
		this.partnerOrgExperience = partnerOrgExperience;
	}
	@OneToMany(mappedBy="partnerOrg",fetch=FetchType.LAZY)
	public Set<PartnerOrgCertification> getPartnerOrgCertification() {
		return partnerOrgCertification;
	}
	public void setPartnerOrgCertification(Set<PartnerOrgCertification> partnerOrgCertification) {
		this.partnerOrgCertification = partnerOrgCertification;
	}
	@OneToMany(mappedBy="partnerOrg",fetch=FetchType.LAZY)
	public Set<PartnerOrgRegistration> getPartnerOrgRegistration() {
		return partnerOrgRegistration;
	}
	public void setPartnerOrgRegistration(Set<PartnerOrgRegistration> partnerOrgRegistration) {
		this.partnerOrgRegistration = partnerOrgRegistration;
	}
	@OneToMany(mappedBy="partnerOrg",fetch=FetchType.LAZY)
	public Set<PartnerOrgBIS> getPartnerOrgBIS() {
		return partnerOrgBIS;
	}
	public void setPartnerOrgBIS(Set<PartnerOrgBIS> partnerOrgBIS) {
		this.partnerOrgBIS = partnerOrgBIS;
	}
	@OneToMany(mappedBy="partnerOrg",fetch=FetchType.LAZY)
	public Set<PartnerOrgProduct> getPartnerOrgProduct() {
		return partnerOrgProduct;
	}
	public void setPartnerOrgProduct(Set<PartnerOrgProduct> partnerOrgProduct) {
		this.partnerOrgProduct = partnerOrgProduct;
	}
	@OneToMany(mappedBy="partnerOrg",fetch=FetchType.LAZY)
	public Set<PartnerOrgPerformance> getPartnerOrgPerformance() {
		return partnerOrgPerformance;
	}
	public void setPartnerOrgPerformance(Set<PartnerOrgPerformance> partnerOrgPerformance) {
		this.partnerOrgPerformance = partnerOrgPerformance;
	}
	@OneToMany(mappedBy="partnerOrg",fetch=FetchType.LAZY)
	public Set<PartnerOrgRDAEC> getPartnerOrgRDAEC() {
		return partnerOrgRDAEC;
	}
	public void setPartnerOrgRDAEC(Set<PartnerOrgRDAEC> partnerOrgRDAEC) {
		this.partnerOrgRDAEC = partnerOrgRDAEC;
	}
	@OneToMany(mappedBy="partnerOrg",fetch=FetchType.LAZY)
	public Set<PartnerOrgOE> getPartnerOrgOE() {
		return partnerOrgOE;
	}
	public void setPartnerOrgOE(Set<PartnerOrgOE> partnerOrgOE) {
		this.partnerOrgOE = partnerOrgOE;
	}
	@OneToMany(mappedBy="partnerOrg",fetch=FetchType.LAZY)
	public Set<PartnerOrgInspection> getPartnerOrgInspection() {
		return partnerOrgInspection;
	}
	public void setPartnerOrgInspection(Set<PartnerOrgInspection> partnerOrgInspection) {
		this.partnerOrgInspection = partnerOrgInspection;
	}
	@Column(name="inspection_date")
	public Date getInspectionDate() {
		return inspectionDate;
	}
	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
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
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_payment_detail_id")
	public PaymentDetail getPaymentDetail() {
		return paymentDetail;
	}
	public void setPaymentDetail(PaymentDetail paymentDetail) {
		this.paymentDetail = paymentDetail;
	}
	@Column(name="valid_to")
	public Timestamp getValidTo() {
		return validTo;
	}
	public void setValidTo(Timestamp validTo) {
		this.validTo = validTo;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_bp_item_manufacturer_id")
	public PartnerItemManufacturer getPartnerItemManufacturer() {
		return partnerItemManufacturer;
	}
	public void setPartnerItemManufacturer(PartnerItemManufacturer partnerItemManufacturer) {
		this.partnerItemManufacturer = partnerItemManufacturer;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="staffList_Copy",referencedColumnName="m_attachment_id")
	public Attachment getStaffListCopy() {
		if(staffListCopy==null || staffListCopy.getAttachmentId()==null)
		{
			return null;
		}
		return staffListCopy;
	}
	public void setStaffListCopy(Attachment staffListCopy) {
		this.staffListCopy = staffListCopy;
	}
	@Column(name="is_factory_inspected")
	public String getIsFactoryInspected() {
		return isFactoryInspected;
	}
	public void setIsFactoryInspected(String isFactoryInspected) {
		this.isFactoryInspected = isFactoryInspected;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="authorization_certificate",referencedColumnName="m_attachment_id")
	public Attachment getAuthorizationCertificate() {
		if(authorizationCertificate==null || authorizationCertificate.getAttachmentId()==null)
		{
			return null;
		}
		return authorizationCertificate;
	}
	public void setAuthorizationCertificate(Attachment authorizationCertificate) {
		this.authorizationCertificate = authorizationCertificate;
	}
	@Column(name="is_renewed")
	public String getIsRenewed() {
		return isRenewed;
	}
	public void setIsRenewed(String isRenewed) {
		this.isRenewed = isRenewed;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="testing_equipment_copy",referencedColumnName="m_attachment_id")
	public Attachment getTestingEquipmentDetails() {
		if(testingEquipmentDetails==null || testingEquipmentDetails.getAttachmentId()==null)
		{
			return null;
		}
		return testingEquipmentDetails;
	}
	public void setTestingEquipmentDetails(Attachment testingEquipmentDetails) {
		this.testingEquipmentDetails = testingEquipmentDetails;
	}
	
	
}
