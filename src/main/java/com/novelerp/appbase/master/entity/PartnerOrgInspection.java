package com.novelerp.appbase.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

/**
 * 
 * @author Varsha Patil
 *
 */
@Entity
@Table(name="m_bp_org_inspection")
public class PartnerOrgInspection extends ContextPO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long orgInspectionId;
	private PartnerOrg partnerOrg;
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
	
	@Id
	@SequenceGenerator(name="m_bp_org_inspection_seq",sequenceName="m_bp_org_inspection_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_org_inspection_seq")	
	@Column(name = "m_bp_org_inspection_id", updatable=false)
	public Long getOrgInspectionId() {
		return orgInspectionId;
	}
	public void setOrgInspectionId(Long orgInspectionId) {
		this.orgInspectionId = orgInspectionId;
	}
	@OneToOne(fetch=FetchType.LAZY)
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
	@Column(name="is_note_received")
	public String getIsNoteReceived() {
		return isNoteReceived;
	}
	public void setIsNoteReceived(String isNoteReceived) {
		this.isNoteReceived = isNoteReceived;
	}
	@Column(name="is_capable_for_manufacturing")
	public String getIsCapableForManufacturing() {
		return isCapableForManufacturing;
	}
	public void setIsCapableForManufacturing(String isCapableForManufacturing) {
		this.isCapableForManufacturing = isCapableForManufacturing;
	}	
	@Column(name="is_adequet_testing")
	public String getIsAdequetTesting() {
		return isAdequetTesting;
	}
	public void setIsAdequetTesting(String isAdequetTesting) {
		this.isAdequetTesting = isAdequetTesting;
	}
	@Column(name="is_financially_capable")
	public String getIsFinanciallyCapable() {
		return isFinanciallyCapable;
	}
	public void setIsFinanciallyCapable(String isFinanciallyCapable) {
		this.isFinanciallyCapable = isFinanciallyCapable;
	}
	@Column(name="is_all_inspectionreport_filled")
	public String getIsAllInspectionReportFilled() {
		return isAllInspectionReportFilled;
	}
	public void setIsAllInspectionReportFilled(String isAllInspectionReportFilled) {
		this.isAllInspectionReportFilled = isAllInspectionReportFilled;
	}
	@Column(name="is_machine_working")
	public String getIsMachineWorking() {
		return isMachineWorking;
	}
	public void setIsMachineWorking(String isMachineWorking) {
		this.isMachineWorking = isMachineWorking;
	}
	@Column(name="is_calibration_certified")
	public String getIsCalibrationCertified() {
		return isCalibrationCertified;
	}
	public void setIsCalibrationCertified(String isCalibrationCertified) {
		this.isCalibrationCertified = isCalibrationCertified;
	}
	@Column(name="is_other_items_manufactured")
	public String getIsOtherItemsManufactured() {
		return isOtherItemsManufactured;
	}
	public void setIsOtherItemsManufactured(String isOtherItemsManufactured) {
		this.isOtherItemsManufactured = isOtherItemsManufactured;
	}
	@Column(name="other_items_manufactured")
	public String getOtherItemsManufactured() {
		return otherItemsManufactured;
	}
	public void setOtherItemsManufactured(String otherItemsManufactured) {
		this.otherItemsManufactured = otherItemsManufactured;
	}
	
}
