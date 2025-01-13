package com.novelerp.eat.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appcontext.dto.CommonContextDto;

/**
 * 
 * @author Aman Sahu
 *
 */
public class ItemScrutinyDto extends CommonContextDto{

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long itemScrutinyId;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="bidderId",scope=Long.class)
	private BidderDto bidder;
	private ItemBidDto itemBid;
	private String scrutinyType;
	private AttachmentDto scrutinyFile;
	private String finalScrutinyComment;
	private String finalScrutinyStatus;
	private String preliminaryScrutinyComment;
	private String preliminaryScrutinyStatus;
	private String bidderDeviationStatus;
	private String isAudited;
	private String preliminaryAuditorComment;
	private String preliminaryAuditorStatus;
	private String isFinalAudited;
	private String finalAuditorComment;
	private String finalAuditorStatus;
	private String isScrutinySubmitted;
	private String isFinalScrutinySubmitted;
	
	
	public Long getItemScrutinyId() {
		return itemScrutinyId;
	}
	public void setItemScrutinyId(Long itemScrutinyId) {
		this.itemScrutinyId = itemScrutinyId;
	}
	public String getScrutinyType() {
		return scrutinyType;
	}
	public void setScrutinyType(String scrutinyType) {
		this.scrutinyType = scrutinyType;
	}
	public AttachmentDto getScrutinyFile() {
		return scrutinyFile;
	}
	public void setScrutinyFile(AttachmentDto scrutinyFile) {
		this.scrutinyFile = scrutinyFile;
	}
	public BidderDto getBidder() {
		return bidder;
	}
	public void setBidder(BidderDto bidder) {
		this.bidder = bidder;
	}
	public ItemBidDto getItemBid() {
		return itemBid;
	}
	public void setItemBid(ItemBidDto itemBid) {
		this.itemBid = itemBid;
	}
	public String getFinalScrutinyComment() {
		return finalScrutinyComment;
	}
	public void setFinalScrutinyComment(String finalScrutinyComment) {
		this.finalScrutinyComment = finalScrutinyComment;
	}
	public String getFinalScrutinyStatus() {
		return finalScrutinyStatus;
	}
	public void setFinalScrutinyStatus(String finalScrutinyStatus) {
		this.finalScrutinyStatus = finalScrutinyStatus;
	}
	public String getPreliminaryScrutinyComment() {
		return preliminaryScrutinyComment;
	}
	public void setPreliminaryScrutinyComment(String preliminaryScrutinyComment) {
		this.preliminaryScrutinyComment = preliminaryScrutinyComment;
	}
	public String getPreliminaryScrutinyStatus() {
		return preliminaryScrutinyStatus;
	}
	public void setPreliminaryScrutinyStatus(String preliminaryScrutinyStatus) {
		this.preliminaryScrutinyStatus = preliminaryScrutinyStatus;
	}
	public String getBidderDeviationStatus() {
		return bidderDeviationStatus;
	}
	public void setBidderDeviationStatus(String bidderDeviationStatus) {
		this.bidderDeviationStatus = bidderDeviationStatus;
	}
	public String getIsAudited() {
		return isAudited;
	}
	public void setIsAudited(String isAudited) {
		this.isAudited = isAudited;
	}
	public String getPreliminaryAuditorComment() {
		return preliminaryAuditorComment;
	}
	public void setPreliminaryAuditorComment(String preliminaryAuditorComment) {
		this.preliminaryAuditorComment = preliminaryAuditorComment;
	}
	public String getPreliminaryAuditorStatus() {
		return preliminaryAuditorStatus;
	}
	public void setPreliminaryAuditorStatus(String preliminaryAuditorStatus) {
		this.preliminaryAuditorStatus = preliminaryAuditorStatus;
	}
	public String getIsFinalAudited() {
		return isFinalAudited;
	}
	public void setIsFinalAudited(String isFinalAudited) {
		this.isFinalAudited = isFinalAudited;
	}
	public String getFinalAuditorComment() {
		return finalAuditorComment;
	}
	public void setFinalAuditorComment(String finalAuditorComment) {
		this.finalAuditorComment = finalAuditorComment;
	}
	public String getFinalAuditorStatus() {
		return finalAuditorStatus;
	}
	public void setFinalAuditorStatus(String finalAuditorStatus) {
		this.finalAuditorStatus = finalAuditorStatus;
	}
	public String getIsScrutinySubmitted() {
		return isScrutinySubmitted;
	}
	public void setIsScrutinySubmitted(String isScrutinySubmitted) {
		this.isScrutinySubmitted = isScrutinySubmitted;
	}
	public String getIsFinalScrutinySubmitted() {
		return isFinalScrutinySubmitted;
	}
	public void setIsFinalScrutinySubmitted(String isFinalScrutinySubmitted) {
		this.isFinalScrutinySubmitted = isFinalScrutinySubmitted;
	}
	
	
}

