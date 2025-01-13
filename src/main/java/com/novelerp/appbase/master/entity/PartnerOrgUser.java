package com.novelerp.appbase.master.entity;

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

import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.appcontext.entity.Designation;
import com.novelerp.appcontext.entity.UserDetails;
/**
 * 
 * @author Aman
 */
@Entity
@Table(name="m_bp_org_user")
public class PartnerOrgUser extends ContextPO {

	private static final long serialVersionUID = 1L;
	private Long partnerOrgUserId;

	private UserDetails userDetail;
	private Designation designation;
	private PartnerOrg partnerOrg;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String remark;
	private String isApproved;
	@Id
	@SequenceGenerator(name="m_bp_org_user_seq",sequenceName="m_bp_org_user_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_org_user_seq")	
	@Column(name = "m_bp_org_user_id", updatable=false)
	public Long getPartnerOrgUserId() {
		return partnerOrgUserId;
	}
	public void setPartnerOrgUserId(Long partnerOrgUserId) {
		this.partnerOrgUserId = partnerOrgUserId;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ad_user_details_id")
	public UserDetails getUserDetail() {
		return userDetail;
	}
	public void setUserDetail(UserDetails userDetail) {
		this.userDetail = userDetail;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ad_designation_id")
	public Designation getDesignation() {
		return designation;
	}
	public void setDesignation(Designation designation) {
		this.designation = designation;
	}
	@ManyToOne(fetch=FetchType.LAZY)
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
}
