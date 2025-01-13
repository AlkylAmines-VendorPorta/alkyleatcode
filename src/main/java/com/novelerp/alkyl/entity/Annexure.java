package com.novelerp.alkyl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.appcontext.entity.User;


@Entity
@Table(name="t_annexure")
public class Annexure extends ContextPO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long annexureId;
	private String code;
	private String name;
	private String description;
	private String remark;
	private String Status;
	private PR pr;
	private String totalBasicValue;
	private String totalGrossValue;
	private String totalLandedCost;
	private Enquiry enquiry;
	private Attachment qcfFormat;
	private String qcf_to_mailid;
	private User rejectedby;
	
	
	
	@Id
	@SequenceGenerator(name = "t_annexure_seq", sequenceName = "t_annexure_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_annexure_seq")
	@Column(name="t_annexure_id",updatable = false)
	public Long getAnnexureId() {
		return annexureId;
	}
	public void setAnnexureId(Long annexureId) {
		this.annexureId = annexureId;
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
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="status")
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_pr_id")
	public PR getPr() {
		return pr;
	}
	public void setPr(PR pr) {
		this.pr = pr;
	}
	@Column(name="total_basic_value")
	public String getTotalBasicValue() {
		return totalBasicValue;
	}
	public void setTotalBasicValue(String totalBasicValue) {
		this.totalBasicValue = totalBasicValue;
	}
	@Column(name="total_gross_value")
	public String getTotalGrossValue() {
		return totalGrossValue;
	}
	public void setTotalGrossValue(String totalGrossValue) {
		this.totalGrossValue = totalGrossValue;
	}
	@Column(name="total_landed_cost")
	public String getTotalLandedCost() {
		return totalLandedCost;
	}
	public void setTotalLandedCost(String totalLandedCost) {
		this.totalLandedCost = totalLandedCost;
	}
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_enquiry_id")
	public Enquiry getEnquiry() {
		return enquiry;
	}
	public void setEnquiry(Enquiry enquiry) {
		this.enquiry = enquiry;
	}
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "m_attachment_id")
	public Attachment getQcfFormat() {
		return qcfFormat;
	}
	public void setQcfFormat(Attachment qcfFormat) {
		this.qcfFormat = qcfFormat;
	}

	
	
	@Column(name="qcf_to_mailid")
	public String getQcf_to_mailid() {
		return qcf_to_mailid;
	}
	public void setQcf_to_mailid(String qcf_to_mailid) {
		this.qcf_to_mailid = qcf_to_mailid;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="rejectedby",referencedColumnName="ad_user_id")
	public User getRejectedby() {
		return rejectedby;
	}
	public void setRejectedby(User rejectedby) {
		this.rejectedby = rejectedby;
	}


}
