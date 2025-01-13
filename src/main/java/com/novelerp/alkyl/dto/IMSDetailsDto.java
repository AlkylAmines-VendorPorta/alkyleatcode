package com.novelerp.alkyl.dto;

import java.util.Date;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appcontext.dto.CommonContextDto;

public class IMSDetailsDto extends CommonContextDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long imsId;
	private String isRawMaterial;
    private String isInProcess;
    private String isFinishedProduct;
    private String inspectionStandard;
    private String majorTestingInstru;
    private String isFCRM;
    private String isISIApproved;
    private String isISOCertified;
    private String isISO14001;
    private Date iso14001ValidUpto;
    private AttachmentDto iso14001Attachment;
    private String isISO9001;
    private Date iso9001ValidUpto;
    private AttachmentDto iso9001Attachment;
    private String isISO50001;
    private Date iso50001ValidUpto;
    private AttachmentDto iso50001Attachment;
    private String isRCLogo ;
    private Date isRCLogoValidUpto;
    private AttachmentDto isRCLogoAttachment;
    private String isOHSMS45001;
    private Date ohsms45001ValidUpto;
    private AttachmentDto  ohsms45001Attachment;
    
	public Long getImsId() {
		return imsId;
	}
	public void setImsId(Long imsId) {
		this.imsId = imsId;
	}
	public String getIsRawMaterial() {
		return isRawMaterial;
	}
	public void setIsRawMaterial(String isRawMaterial) {
		this.isRawMaterial = isRawMaterial;
	}
	public String getIsInProcess() {
		return isInProcess;
	}
	public void setIsInProcess(String isInProcess) {
		this.isInProcess = isInProcess;
	}
	public String getIsFinishedProduct() {
		return isFinishedProduct;
	}
	public void setIsFinishedProduct(String isFinishedProduct) {
		this.isFinishedProduct = isFinishedProduct;
	}
	public String getInspectionStandard() {
		return inspectionStandard;
	}
	public void setInspectionStandard(String inspectionStandard) {
		this.inspectionStandard = inspectionStandard;
	}
	public String getMajorTestingInstru() {
		return majorTestingInstru;
	}
	public void setMajorTestingInstru(String majorTestingInstru) {
		this.majorTestingInstru = majorTestingInstru;
	}
	public String getIsFCRM() {
		return isFCRM;
	}
	public void setIsFCRM(String isFCRM) {
		this.isFCRM = isFCRM;
	}
	public String getIsISIApproved() {
		return isISIApproved;
	}
	public void setIsISIApproved(String isISIApproved) {
		this.isISIApproved = isISIApproved;
	}
	public String getIsISOCertified() {
		return isISOCertified;
	}
	public void setIsISOCertified(String isISOCertified) {
		this.isISOCertified = isISOCertified;
	}
	public String getIsISO14001() {
		return isISO14001;
	}
	public void setIsISO14001(String isISO14001) {
		this.isISO14001 = isISO14001;
	}
	public Date getIso14001ValidUpto() {
		return iso14001ValidUpto;
	}
	public void setIso14001ValidUpto(Date iso14001ValidUpto) {
		this.iso14001ValidUpto = iso14001ValidUpto;
	}
	public AttachmentDto getIso14001Attachment() {
		return iso14001Attachment;
	}
	public void setIso14001Attachment(AttachmentDto iso14001Attachment) {
		this.iso14001Attachment = iso14001Attachment;
	}
	public String getIsISO9001() {
		return isISO9001;
	}
	public void setIsISO9001(String isISO9001) {
		this.isISO9001 = isISO9001;
	}
	public Date getIso9001ValidUpto() {
		return iso9001ValidUpto;
	}
	public void setIso9001ValidUpto(Date iso9001ValidUpto) {
		this.iso9001ValidUpto = iso9001ValidUpto;
	}
	public AttachmentDto getIso9001Attachment() {
		return iso9001Attachment;
	}
	public void setIso9001Attachment(AttachmentDto iso9001Attachment) {
		this.iso9001Attachment = iso9001Attachment;
	}
	public String getIsISO50001() {
		return isISO50001;
	}
	public void setIsISO50001(String isISO50001) {
		this.isISO50001 = isISO50001;
	}
	public Date getIso50001ValidUpto() {
		return iso50001ValidUpto;
	}
	public void setIso50001ValidUpto(Date iso50001ValidUpto) {
		this.iso50001ValidUpto = iso50001ValidUpto;
	}
	public AttachmentDto getIso50001Attachment() {
		return iso50001Attachment;
	}
	public void setIso50001Attachment(AttachmentDto iso50001Attachment) {
		this.iso50001Attachment = iso50001Attachment;
	}
	public String getIsRCLogo() {
		return isRCLogo;
	}
	public void setIsRCLogo(String isRCLogo) {
		this.isRCLogo = isRCLogo;
	}
	public Date getIsRCLogoValidUpto() {
		return isRCLogoValidUpto;
	}
	public void setIsRCLogoValidUpto(Date isRCLogoValidUpto) {
		this.isRCLogoValidUpto = isRCLogoValidUpto;
	}
	public AttachmentDto getIsRCLogoAttachment() {
		return isRCLogoAttachment;
	}
	public void setIsRCLogoAttachment(AttachmentDto isRCLogoAttachment) {
		this.isRCLogoAttachment = isRCLogoAttachment;
	}
	public String getIsOHSMS45001() {
		return isOHSMS45001;
	}
	public void setIsOHSMS45001(String isOHSMS45001) {
		this.isOHSMS45001 = isOHSMS45001;
	}
	public Date getOhsms45001ValidUpto() {
		return ohsms45001ValidUpto;
	}
	public void setOhsms45001ValidUpto(Date ohsms45001ValidUpto) {
		this.ohsms45001ValidUpto = ohsms45001ValidUpto;
	}
	public AttachmentDto getOhsms45001Attachment() {
		return ohsms45001Attachment;
	}
	public void setOhsms45001Attachment(AttachmentDto ohsms45001Attachment) {
		this.ohsms45001Attachment = ohsms45001Attachment;
	}
}
