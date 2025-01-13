package com.novelerp.appbase.master.dto;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.LocationDto;
import com.novelerp.core.util.DateUtil;
import com.novelerp.eat.dto.PaymentDetailDto;

/**
 * DTO for partner's Organisation/ Plant/ Factory.
 * @author Vivek Birdi
 *
 */
public class PartnerOrgDto extends CommonContextDto {
	
	private static final long serialVersionUID = 1L;

	private String orgType;
	private Long partnerOrgId;
	private String name;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date estdDate;
	private String inspectionReportNo;
	private LocationDto location;
	private String manPower;
    private String licenceNo;
    
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date licenceValidityDate;
	private AttachmentDto licenceCopy;
	private AttachmentDto machinaryListCopy;
	private AttachmentDto inspectionReportCopy;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date inspectionDate;
	private String licenseType;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String remark;
	private String isApproved;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="paymentDetailId",scope=Long.class)
	private PaymentDetailDto paymentDetail;
	private Timestamp validTo;
	private PartnerItemManufacturerDto partnerItemManufacturer;
	private AttachmentDto staffListCopy;
	private String isFactoryInspected;
	private AttachmentDto authorizationCertificate;
	private String isRenewed;
	private AttachmentDto testingEquipmentDetails;
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}	
	public Long getPartnerOrgId() {
		return partnerOrgId;
	}
	public void setPartnerOrgId(Long partnerOrgId) {
		this.partnerOrgId = partnerOrgId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getEstdDate() {
		return estdDate;
	}
	public void setEstdDate(Date estdDate) {
		this.estdDate = estdDate;
	}
	public String getInspectionReportNo() {
		return inspectionReportNo;
	}
	public void setInspectionReportNo(String inspectionReportNo) {
		this.inspectionReportNo = inspectionReportNo;
	}
	public LocationDto getLocation() {
		return location;
	}
	public void setLocation(LocationDto location) {
		this.location = location;
	}
	public String getManPower() {
		return manPower;
	}
	public void setManPower(String manPower) {
		this.manPower = manPower;
	}
	public String getLicenceNo() {
		return licenceNo;
	}
	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}
	public Date getLicenceValidityDate() {
		return licenceValidityDate;
	}
	public void setLicenceValidityDate(Date licenceValidityDate) {
		this.licenceValidityDate = licenceValidityDate;
	}
	public AttachmentDto getLicenceCopy() {
		return licenceCopy;
	}
	public void setLicenceCopy(AttachmentDto licenceCopy) {
		
		this.licenceCopy = licenceCopy;
	}
	public AttachmentDto getMachinaryListCopy() {
		return machinaryListCopy;
	}
	public void setMachinaryListCopy(AttachmentDto machinaryListCopy) {
	  
		this.machinaryListCopy =machinaryListCopy;
	}
	public String getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}
	public AttachmentDto getInspectionReportCopy() {
		return inspectionReportCopy;
	}
	public void setInspectionReportCopy(AttachmentDto inspectionReportCopy) {
		this.inspectionReportCopy = inspectionReportCopy;
	}
	public Date getInspectionDate() {
		return inspectionDate;
	}
	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
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
	public PaymentDetailDto getPaymentDetail() {
		return paymentDetail;
	}
	public void setPaymentDetail(PaymentDetailDto paymentDetail) {
		this.paymentDetail = paymentDetail;
	}
	public Timestamp getValidTo() {
		return validTo;
	}
	public void setValidTo(Timestamp validTo) {
		this.validTo = validTo;
	}
	public PartnerItemManufacturerDto getPartnerItemManufacturer() {
		return partnerItemManufacturer;
	}
	public void setPartnerItemManufacturer(PartnerItemManufacturerDto partnerItemManufacturer) {
		this.partnerItemManufacturer = partnerItemManufacturer;
	}
	public AttachmentDto getStaffListCopy() {
		return staffListCopy;
	}
	public void setStaffListCopy(AttachmentDto staffListCopy) {
		this.staffListCopy = staffListCopy;
	}
	public String getIsFactoryInspected() {
		return isFactoryInspected;
	}
	public void setIsFactoryInspected(String isFactoryInspected) {
		this.isFactoryInspected = isFactoryInspected;
	}
	public AttachmentDto getAuthorizationCertificate() {
		return authorizationCertificate;
	}
	public void setAuthorizationCertificate(AttachmentDto authorizationCertificate) {
		this.authorizationCertificate = authorizationCertificate;
	}
	public String getIsRenewed() {
		return isRenewed;
	}
	public void setIsRenewed(String isRenewed) {
		this.isRenewed = isRenewed;
	}
	public AttachmentDto getTestingEquipmentDetails() {
		return testingEquipmentDetails;
	}
	public void setTestingEquipmentDetails(AttachmentDto testingEquipmentDetails) {
		this.testingEquipmentDetails = testingEquipmentDetails;
	}
	
	
}
