package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

/**
 * 
 * @author Varsha Patil
 *
 */
public class PartnerOrgInspectionDto extends CommonContextDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long orgInspectionId;
	private PartnerOrgDto partnerOrg;
    private String isEEApproved;
	private String eeComment;
	private String ceComment;
	private String isCEApproved;
	private String isNoteReceived;
	private String isCapableForManufacturing;
	private String isAdequetTesting;
	private String isFinanciallyCapable;
	private String isAllInspectionReportFilled;
	private String isMachineWorking;
	private String isCalibrationCertified;
	private String isOtherItemsManufactured;
	private String otherItemsManufactured;
	public Long getOrgInspectionId() {
		return orgInspectionId;
	}
	public void setOrgInspectionId(Long orgInspectionId) {
		this.orgInspectionId = orgInspectionId;
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
	public String getCeComment() {
		return ceComment;
	}
	public void setCeComment(String ceComment) {
		this.ceComment = ceComment;
	}
	public String getIsCEApproved() {
		return isCEApproved;
	}
	public void setIsCEApproved(String isCEApproved) {
		this.isCEApproved = isCEApproved;
	}
	public String getIsNoteReceived() {
		if(!("Y".equals(isNoteReceived))){
			isNoteReceived="N";
		}
		return isNoteReceived;
	}
	public void setIsNoteReceived(String isNoteReceived) {
		this.isNoteReceived = isNoteReceived;
	}
	public String getIsCapableForManufacturing() {
		if(!("Y".equals(isCapableForManufacturing))){
			isCapableForManufacturing="N";
		}
		return isCapableForManufacturing;
	}
	public void setIsCapableForManufacturing(String isCapableForManufacturing) {
		this.isCapableForManufacturing = isCapableForManufacturing;
	}
	public String getIsAdequetTesting() {
		if(!("Y".equals(isAdequetTesting))){
			isAdequetTesting="N";
		}
		return isAdequetTesting;
	}
	public void setIsAdequetTesting(String isAdequetTesting) {
		this.isAdequetTesting = isAdequetTesting;
	}
	public String getIsFinanciallyCapable() {
		if(!("Y".equals(isFinanciallyCapable))){
			isFinanciallyCapable="N";
		}
		return isFinanciallyCapable;
	}
	public void setIsFinanciallyCapable(String isFinanciallyCapable) {
		this.isFinanciallyCapable = isFinanciallyCapable;
	}
	public String getIsAllInspectionReportFilled() {
		if(!("Y".equals(isAllInspectionReportFilled))){
			isAllInspectionReportFilled="N";
		}
		return isAllInspectionReportFilled;
	}
	public void setIsAllInspectionReportFilled(String isAllInspectionReportFilled) {
		this.isAllInspectionReportFilled = isAllInspectionReportFilled;
	}
	public String getIsMachineWorking() {
		if(!("Y".equals(isMachineWorking))){
			isMachineWorking="N";
		}
		return isMachineWorking;
	}
	public void setIsMachineWorking(String isMachineWorking) {
		this.isMachineWorking = isMachineWorking;
	}
	public String getIsCalibrationCertified() {
		if(!("Y".equals(isCalibrationCertified))){
			isCalibrationCertified="N";
		}
		return isCalibrationCertified;
	}
	public void setIsCalibrationCertified(String isCalibrationCertified) {
		this.isCalibrationCertified = isCalibrationCertified;
	}
	public String getIsOtherItemsManufactured() {
		if(!("Y".equals(isOtherItemsManufactured))){
			isOtherItemsManufactured="N";
		}
		return isOtherItemsManufactured;
	}
	public void setIsOtherItemsManufactured(String isOtherItemsManufactured) {
		this.isOtherItemsManufactured = isOtherItemsManufactured;
	}
	public String getOtherItemsManufactured() {
		return otherItemsManufactured;
	}
	public void setOtherItemsManufactured(String otherItemsManufactured) {
		this.otherItemsManufactured = otherItemsManufactured;
	}
	

}
