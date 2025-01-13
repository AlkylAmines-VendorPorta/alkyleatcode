package com.novelerp.eat.entity;

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

import com.novelerp.appbase.master.entity.InfraApprovalLevel;
import com.novelerp.appbase.master.entity.PartnerInfraItem;
import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="m_bp_infra_item_ap")
public class PartnerInfraItemApproval extends ContextPO{
	
	private static final long serialVersionUID = 1L;

	private Long partnerInfraItemApprovalId;
	private InfraApprovalLevel infraApprovalLevel;
	private PartnerInfraItem partnerInfraItem;
/*	private String status;*/
	private String comment;
	private String isApproved;
	
	@Id
	@SequenceGenerator(name="m_bp_infra_item_ap_seq",sequenceName="m_bp_infra_item_ap_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bp_infra_item_ap_seq")	
	@Column(name = "m_bp_infra_item_ap_id", updatable=false)
	public Long getPartnerInfraItemApprovalId() {
		return partnerInfraItemApprovalId;
	}
	public void setPartnerInfraItemApprovalId(Long partnerInfraItemApprovalId) {
		this.partnerInfraItemApprovalId = partnerInfraItemApprovalId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_infra_ap_lvl_id")
	public InfraApprovalLevel getInfraApprovalLevel() {
		return infraApprovalLevel;
	}
	public void setInfraApprovalLevel(InfraApprovalLevel infraApprovalLevel) {
		this.infraApprovalLevel = infraApprovalLevel;
	}
	/*@Column(name="ap_status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}*/
	@Column(name="ap_comment")
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_bp_infra_item_id")
	public PartnerInfraItem getPartnerInfraItem() {
		return partnerInfraItem;
	}
	public void setPartnerInfraItem(PartnerInfraItem partnerInfraItem) {
		this.partnerInfraItem = partnerInfraItem;
	}
    @Column(name="isapproved")
	public String getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	
	
}
