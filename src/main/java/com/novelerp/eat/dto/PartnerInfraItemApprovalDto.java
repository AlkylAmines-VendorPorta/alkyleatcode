package com.novelerp.eat.dto;

import com.novelerp.appbase.master.dto.InfraApprovalLevelDto;
import com.novelerp.appbase.master.dto.PartnerInfraItemDto;
import com.novelerp.appcontext.dto.CommonContextDto;

public class PartnerInfraItemApprovalDto extends CommonContextDto {
	
	private static final long serialVersionUID = 1L;
	private Long partnerInfraItemApprovalId;
	private InfraApprovalLevelDto infraApprovalLevel;
	private PartnerInfraItemDto partnerInfraItem;
	/*private String status;*/
	private String comment;
	private String isApproved;
	
	public Long getPartnerInfraItemApprovalId() {
		return partnerInfraItemApprovalId;
	}
	public void setPartnerInfraItemApprovalId(Long partnerInfraItemApprovalId) {
		this.partnerInfraItemApprovalId = partnerInfraItemApprovalId;
	}
	public InfraApprovalLevelDto getInfraApprovalLevel() {
		return infraApprovalLevel;
	}
	public void setInfraApprovalLevel(InfraApprovalLevelDto infraApprovalLevel) {
		this.infraApprovalLevel = infraApprovalLevel;
	}
	public PartnerInfraItemDto getPartnerInfraItem() {
		return partnerInfraItem;
	}
	public void setPartnerInfraItem(PartnerInfraItemDto partnerInfraItem) {
		this.partnerInfraItem = partnerInfraItem;
	}
	/*public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}*/
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	
	
}
