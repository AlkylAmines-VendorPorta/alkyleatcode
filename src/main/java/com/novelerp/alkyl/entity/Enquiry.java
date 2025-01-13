package com.novelerp.alkyl.entity;

import java.util.Date;

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

import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="t_enquiry")
public class Enquiry extends ContextPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long enquiryId;
	private String code;
	private String name;
	private String description;
	private Date bidEndDate;
	private String qcfNo;
	private String enqNo;
	private String isMailsentFinalApproval;
	private String finalApprovalStatus;
	private String firstLevelApprovalStatus;
	private Long qcfAtt;
	
	
	@Id
	@SequenceGenerator(name = "t_enquiry_seq", sequenceName = "t_enquiry_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_enquiry_seq")
	@Column(name="t_enquiry_id",updatable = false)
	public Long getEnquiryId() {
		return enquiryId;
	}
	public void setEnquiryId(Long enquiryId) {
		this.enquiryId = enquiryId;
	}
	@Column(name="value")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="bid_end_date")
	public Date getBidEndDate() {
		return bidEndDate;
	}
	public void setBidEndDate(Date bidEndDate) {
		this.bidEndDate = bidEndDate;
	}
	@Column(name="qcf_no")
	public String getQcfNo() {
		return qcfNo;
	}
	public void setQcfNo(String qcfNo) {
		this.qcfNo = qcfNo;
	}
	@Column(name="enq_no")
	public String getEnqNo() {
		return enqNo;
	}
	public void setEnqNo(String enqNo) {
		this.enqNo = enqNo;
	}
	
	@Column(name="is_mail_sent_finalapproval")
	public String getIsMailsentFinalApproval() {
		return isMailsentFinalApproval;
	}
	
	public void setIsMailsentFinalApproval(String isMailsentFinalApproval) {
		this.isMailsentFinalApproval = isMailsentFinalApproval;
	}
	
	@Column(name="final_approval_status")
	public String getFinalApprovalStatus() {
		return finalApprovalStatus;
	}
	public void setFinalApprovalStatus(String finalApprovalStatus) {
		this.finalApprovalStatus = finalApprovalStatus;
	}
	
	
	@Column(name="firstlevel_approval_status")
	public String getFirstLevelApprovalStatus() {
		return firstLevelApprovalStatus;
	}
	public void setFirstLevelApprovalStatus(String firstLevelApprovalStatus) {
		this.firstLevelApprovalStatus = firstLevelApprovalStatus;
	}

	
//	@OneToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="m_attachment_id")
	@Column(name="m_attachment_id")
	public Long getQcfAtt() {
		return qcfAtt;
	}
	public void setQcfAtt(Long qcfAtt) {
		this.qcfAtt = qcfAtt;
	}
	
	
	
}
