package com.novelerp.appbase.master.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

/**
 * 
 * @author Varsha Patil
 *
 */
@Entity
@Table(name="m_bp_item_type_test_details")
public class PartnerItemTypeTestDetails extends ContextPO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long partnerItemTypeTestId;
	private PartnerInfraItem partnerInfraItem;
	private MaterialTypeTestDetails materialTypeTestDetails;
	private Date issueDate;
	private String letterNo; 
	private String status;
	private String remark;
	private String isEEApproved;
	private String isSEApproved;
	private String isCEApproved;
	private String isEDApproved;
	private String isDIRApproved;
	private String eeComment;
	private String seComment;
	private String ceComment;
	private String edComment;
	private String dirComment;
	@Id
	@SequenceGenerator(name = "m_bp_item_type_test_seq", sequenceName = "m_bp_item_type_test_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_bp_item_type_test_seq")
	@Column(name = "m_bp_item_type_test_id", updatable = false)
	public Long getPartnerItemTypeTestId() {
		return partnerItemTypeTestId;
	}
	public void setPartnerItemTypeTestId(Long partnerItemTypeTestId) {
		this.partnerItemTypeTestId = partnerItemTypeTestId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_bp_infra_item_id")
	public PartnerInfraItem getPartnerInfraItem() {
		return partnerInfraItem;
	}
	public void setPartnerInfraItem(PartnerInfraItem partnerInfraItem) {
		this.partnerInfraItem = partnerInfraItem;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_material_type_test_id")
	public MaterialTypeTestDetails getMaterialTypeTestDetails() {
		return materialTypeTestDetails;
	}
	public void setMaterialTypeTestDetails(MaterialTypeTestDetails materialTypeTestDetails) {
		this.materialTypeTestDetails = materialTypeTestDetails;
	}
	@Column(name="issue_date")
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	@Column(name="letter_no")
	public String getLetterNo() {
		return letterNo;
	}
	public void setLetterNo(String letterNo) {
		this.letterNo = letterNo;
	}
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="is_ee_approved")
	public String getIsEEApproved() {
		return isEEApproved;
	}
	public void setIsEEApproved(String isEEApproved) {
		this.isEEApproved = isEEApproved;
	}
	@Column(name="is_se_approved")
	public String getIsSEApproved() {
		return isSEApproved;
	}
	public void setIsSEApproved(String isSEApproved) {
		this.isSEApproved = isSEApproved;
	}
	@Column(name="is_ce_approved")
	public String getIsCEApproved() {
		return isCEApproved;
	}
	public void setIsCEApproved(String isCEApproved) {
		this.isCEApproved = isCEApproved;
	}
	@Column(name="is_ed_approved")
	public String getIsEDApproved() {
		return isEDApproved;
	}
	public void setIsEDApproved(String isEDApproved) {
		this.isEDApproved = isEDApproved;
	}
	@Column(name="is_dir_approved")
	public String getIsDIRApproved() {
		return isDIRApproved;
	}
	public void setIsDIRApproved(String isDIRApproved) {
		this.isDIRApproved = isDIRApproved;
	}
	@Column(name="ee_comment")
	public String getEeComment() {
		return eeComment;
	}
	public void setEeComment(String eeComment) {
		this.eeComment = eeComment;
	}
	@Column(name="se_comment")
	public String getSeComment() {
		return seComment;
	}
	public void setSeComment(String seComment) {
		this.seComment = seComment;
	}
	@Column(name="ce_comment")
	public String getCeComment() {
		return ceComment;
	}
	public void setCeComment(String ceComment) {
		this.ceComment = ceComment;
	}
	@Column(name="ed_comment")
	public String getEdComment() {
		return edComment;
	}
	public void setEdComment(String edComment) {
		this.edComment = edComment;
	}
	@Column(name="dir_comment")
	public String getDirComment() {
		return dirComment;
	}
	public void setDirComment(String dirComment) {
		this.dirComment = dirComment;
	} 
}
