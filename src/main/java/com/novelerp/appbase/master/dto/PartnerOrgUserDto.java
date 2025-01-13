package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.UserDetailsDto;
/**
 * 
 * @author Aman
 */
public class PartnerOrgUserDto extends CommonContextDto {

	private static final long serialVersionUID = 1L;
	private Long partnerOrgUserId;
	private UserDetailsDto userDetail;
	private PartnerOrgDto partnerOrg;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String remark;
	private String isApproved;
	public Long getPartnerOrgUserId() {
		return partnerOrgUserId;
	}
	public void setPartnerOrgUserId(Long partnerOrgUserId) {
		this.partnerOrgUserId = partnerOrgUserId;
	}
	public UserDetailsDto getUserDetail() {
		return userDetail;
	}
	public void setUserDetail(UserDetailsDto userDetail) {
		this.userDetail = userDetail;
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
	public String getIsCEApproved() {
		return isCEApproved;
	}
	public void setIsCEApproved(String isCEApproved) {
		this.isCEApproved = isCEApproved;
	}
	public String getCeComment() {
		return ceComment;
	}
	public void setCeComment(String ceComment) {
		this.ceComment = ceComment;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

}
