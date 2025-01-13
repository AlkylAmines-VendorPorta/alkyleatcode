package com.novelerp.alkyl.dto;

import java.util.List;

import com.novelerp.alkyl.entity.Enquiry;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.WinnerSelectionDto;

public class AnnexureDto extends CommonContextDto {
	
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
	private PRDto pr;
	private List<WinnerSelectionDto> winnerSelectionSet;
	private List<PraposedReasonDto> praposedReasonSet;
	private List<BidderDto> bidder;
	private String totalBasicValue;
	private String totalGrossValue;
	private String totalLandedCost;
	private EnquiryDto enquiry;
	private AttachmentDto qcfFormat;
	private String qcf_to_mailid;
	private UserDto rejectedby;
	
	
	public Long getAnnexureId() {
		return annexureId;
	}
	public void setAnnexureId(Long annexureId) {
		this.annexureId = annexureId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public PRDto getPr() {
		return pr;
	}
	public void setPr(PRDto pr) {
		this.pr = pr;
	}
	public List<WinnerSelectionDto> getWinnerSelectionSet() {
		return winnerSelectionSet;
	}
	public void setWinnerSelectionSet(List<WinnerSelectionDto> winnerSelectionSet) {
		this.winnerSelectionSet = winnerSelectionSet;
	}
	public List<PraposedReasonDto> getPraposedReasonSet() {
		return praposedReasonSet;
	}
	public void setPraposedReasonSet(List<PraposedReasonDto> praposedReasonSet) {
		this.praposedReasonSet = praposedReasonSet;
	}
	public List<BidderDto> getBidder() {
		return bidder;
	}
	public void setBidder(List<BidderDto> bidder) {
		this.bidder = bidder;
	}
	public String getTotalBasicValue() {
		return totalBasicValue;
	}
	public void setTotalBasicValue(String totalBasicValue) {
		this.totalBasicValue = totalBasicValue;
	}
	public String getTotalGrossValue() {
		return totalGrossValue;
	}
	public void setTotalGrossValue(String totalGrossValue) {
		this.totalGrossValue = totalGrossValue;
	}
	public String getTotalLandedCost() {
		return totalLandedCost;
	}
	public void setTotalLandedCost(String totalLandedCost) {
		this.totalLandedCost = totalLandedCost;
	}
	public EnquiryDto getEnquiry() {
		return enquiry;
	}
	public void setEnquiry(EnquiryDto enquiry) {
		this.enquiry = enquiry;
	}
	public AttachmentDto getQcfFormat() {
		return qcfFormat;
	}
	public void setQcfFormat(AttachmentDto qcfFormat) {
		this.qcfFormat = qcfFormat;
	}
	public String getQcf_to_mailid() {
		return qcf_to_mailid;
	}
	public void setQcf_to_mailid(String qcf_to_mailid) {
		this.qcf_to_mailid = qcf_to_mailid;
	}
	public UserDto getRejectedby() {
		return rejectedby;
	}
	public void setRejectedby(UserDto rejectedby) {
		this.rejectedby = rejectedby;
	}
	

}
