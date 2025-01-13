package com.novelerp.alkyl.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.core.util.DateUtil;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.ItemBidDto;

public class EnquiryDto extends CommonContextDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long enquiryId;
	private String code;
	private String name;
	private String description;
	private List<BidderDto> bidderList;
	private List<ItemBidDto> itemBidList;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date bidEndDate;
	private String qcfNo;
	private String enqNo;
	private String isMailsentFinalApproval;
	private String finalApprovalStatus;
	private String firstLevelApprovalStatus;
	private UserDto createdBy;
	private Long qcfAtt;

	public List<BidderDto> getBidderList() {
		return bidderList;
	}

	public void setBidderList(List<BidderDto> bidderList) {
		this.bidderList = bidderList;
	}

	public List<ItemBidDto> getItemBidList() {
		return itemBidList;
	}

	public void setItemBidList(List<ItemBidDto> itemBidList) {
		this.itemBidList = itemBidList;
	}

	public Long getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(Long enquiryId) {
		this.enquiryId = enquiryId;
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

	public Date getBidEndDate() {
		return bidEndDate;
	}

	public void setBidEndDate(Date bidEndDate) {
		this.bidEndDate = bidEndDate;
	}

	public String getQcfNo() {
		return qcfNo;
	}

	public void setQcfNo(String qcfNo) {
		this.qcfNo = qcfNo;
	}

	public String getEnqNo() {
		return enqNo;
	}

	public void setEnqNo(String enqNo) {
		this.enqNo = enqNo;
	}

	public String getIsMailsentFinalApproval() {
		return isMailsentFinalApproval;
	}

	public void setIsMailsentFinalApproval(String isMailsentFinalApproval) {
		this.isMailsentFinalApproval = isMailsentFinalApproval;
	}

	public String getFinalApprovalStatus() {
		return finalApprovalStatus;
	}

	public void setFinalApprovalStatus(String finalApprovalStatus) {
		this.finalApprovalStatus = finalApprovalStatus;
	}

	public String getFirstLevelApprovalStatus() {
		return firstLevelApprovalStatus;
	}

	public void setFirstLevelApprovalStatus(String firstLevelApprovalStatus) {
		this.firstLevelApprovalStatus = firstLevelApprovalStatus;
	}

	public UserDto getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserDto createdBy) {
		this.createdBy = createdBy;
	}

	public Long getQcfAtt() {
		return qcfAtt;
	}

	public void setQcfAtt(Long qcfAtt) {
		this.qcfAtt = qcfAtt;
	}




	
}
