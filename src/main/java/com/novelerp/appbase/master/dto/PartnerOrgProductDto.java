package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class PartnerOrgProductDto extends CommonContextDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long partnerOrgProductId;
	private double qtyManufacturedPM;
	private double turnOver;
	private String licenceNo;
	private PartnerOrgDto partnerOrg;
	private MaterialDto material;
	private UOMDto uom;
	private String registrationType;
	private PartnerOrgRegistrationDto partnerOrgRegistration;
	private PartnerOrgBISDto partnerOrgBIS;
	private AttachmentDto industrialLicenceCopy;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	
	public Long getPartnerOrgProductId() {
		return partnerOrgProductId;
	}
	public void setPartnerOrgProductId(Long partnerOrgProductId) {
		this.partnerOrgProductId = partnerOrgProductId;
	}
	
	public double getQtyManufacturedPM() {
		return qtyManufacturedPM;
	}
	public void setQtyManufacturedPM(double qtyManufacturedPM) {
		this.qtyManufacturedPM = qtyManufacturedPM;
	}
	public double getTurnOver() {
		return turnOver;
	}
	public void setTurnOver(double turnOver) {
		this.turnOver = turnOver;
	}
	public String getLicenceNo() {
		return licenceNo;
	}
	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}
	public PartnerOrgDto getPartnerOrg() {
		return partnerOrg;
	}
	public void setPartnerOrg(PartnerOrgDto partnerOrg) {
		this.partnerOrg = partnerOrg;
	}
	public MaterialDto getMaterial() {
		return material;
	}
	public void setMaterial(MaterialDto material) {
		this.material = material;
	}
	public UOMDto getUom() {
		return uom;
	}
	public void setUom(UOMDto uom) {
		this.uom = uom;
	}
	public String getRegistrationType() {
		return registrationType;
	}
	public void setRegistrationType(String registrationType) {
		this.registrationType = registrationType;
	}
	public PartnerOrgRegistrationDto getPartnerOrgRegistration() {
		return partnerOrgRegistration;
	}
	public void setPartnerOrgRegistration(PartnerOrgRegistrationDto partnerOrgRegistration) {
		this.partnerOrgRegistration = partnerOrgRegistration;
	}
	public PartnerOrgBISDto getPartnerOrgBIS() {
		return partnerOrgBIS;
	}
	public void setPartnerOrgBIS(PartnerOrgBISDto partnerOrgBIS) {
		this.partnerOrgBIS = partnerOrgBIS;
	}
	public AttachmentDto getIndustrialLicenceCopy() {
		return industrialLicenceCopy;
	}
	public void setIndustrialLicenceCopy(AttachmentDto industrialLicenceCopy) {
		this.industrialLicenceCopy = industrialLicenceCopy;
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
	
	
	
}
