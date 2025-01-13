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

import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.appcontext.entity.User;

@Entity
@Table(name="t_pr")
public class PR extends ContextPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long prId;
	private String code;
	private String name;
	private String description;
	private String prNumber;
	private User requestedBy;
	private User tcApprover;
	private String status;
	private String docType;
	private String isTC;
	private String pstyp;
	private User buyer;
	private String priority;
	private String assignment;
	private User approvedBy;
	private Date date;
	private String remarks;
	private String qcfNo;
	private Date bidEndDate;
	private User releasedBy;
	private Date releasedDate;
	private Date approvedDate;
	private User pmapprovedBy;
	private Date pmapprovedDate;
//	private User createdBy;

	
	@Id
	@SequenceGenerator(name = "t_pr_seq", sequenceName = "t_pr_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_pr_seq")
	@Column(name="t_pr_id",updatable = false)
	public Long getPrId() {
		return prId;
	}
	public void setPrId(Long prId) {
		this.prId = prId;
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
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="requested_by",referencedColumnName="ad_user_id")
	public User getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(User requestedBy) {
		this.requestedBy = requestedBy;
	}
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tc_approver",referencedColumnName="ad_user_id")
	public User getTcApprover() {
		return tcApprover;
	}
	public void setTcApprover(User tcApprover) {
		this.tcApprover = tcApprover;
	}
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name="doc_type")
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	
	@Column(name="is_tc")
	public String getIsTC() {
		return isTC;
	}
	public void setIsTC(String isTC) {
		this.isTC = isTC;
	}
	
	@Column(name="pstyp")
	public String getPstyp() {
		return pstyp;
	}
	public void setPstyp(String pstyp) {
		this.pstyp = pstyp;
	}
	
	@Column(name="pr_number")
	public String getPrNumber() {
		return prNumber;
	}
	public void setPrNumber(String prNumber) {
		this.prNumber = prNumber;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="buyer",referencedColumnName="ad_user_id")
	public User getBuyer() {
		return buyer;
	}
	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}
	
	@Column(name="priority")
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	@Column(name="assignment")
	public String getAssignment() {
		return assignment;
	}
	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="approvedBy",referencedColumnName="ad_user_id")
	public User getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(User approvedBy) {
		this.approvedBy = approvedBy;
	}
	
	@Column(name="date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Column(name="qcf_no")
	public String getQcfNo() {
		return qcfNo;
	}
	public void setQcfNo(String qcfNo) {
		this.qcfNo = qcfNo;
	}
	@Column(name="bid_end_date")
	public Date getBidEndDate() {
		return bidEndDate;
	}
	public void setBidEndDate(Date bidEndDate) {
		this.bidEndDate = bidEndDate;
	}

	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="releasedby",referencedColumnName="ad_user_id")
	public User getReleasedBy() {
		return releasedBy;
	}
	public void setReleasedBy(User releasedBy) {
		this.releasedBy = releasedBy;
	}
	
	@Column(name="released_date")
	public Date getReleasedDate() {
		return releasedDate;
	}
	public void setReleasedDate(Date releasedDate) {
		this.releasedDate = releasedDate;
	}
	
	@Column(name="approved_date")
	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	@Column(name="pmapproved_date")
	public Date getPmapprovedDate() {
		return pmapprovedDate;
	}

	public void setPmapprovedDate(Date pmapprovedDate) {
		this.pmapprovedDate = pmapprovedDate;
	}
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pmapprovedby",referencedColumnName="ad_user_id")
	public User getPmapprovedBy() {
		return pmapprovedBy;
	}
	
	public void setPmapprovedBy(User pmapprovedBy) {
		this.pmapprovedBy = pmapprovedBy;
	}
	
}
