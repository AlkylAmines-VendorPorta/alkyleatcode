package com.novelerp.eat.dto;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appcontext.dto.CommonContextDto;

public class ScrutinyFileDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	private Long scrutinyFileId;
	private ItemScrutinyDto itemScrutiny;
	private BidderDto bidder;
	private ItemBidDto itemBid;
	private String scrutinyLevel;
	private String scrutinyType;
	private AttachmentDto attachment;
	
	public Long getScrutinyFileId() {
		return scrutinyFileId;
	}
	public void setScrutinyFileId(Long scrutinyFileId) {
		this.scrutinyFileId = scrutinyFileId;
	}
	public BidderDto getBidder() {
		return bidder;
	}
	public void setBidder(BidderDto bidder) {
		this.bidder = bidder;
	}
	public String getScrutinyLevel() {
		return scrutinyLevel;
	}
	public void setScrutinyLevel(String scrutinyLevel) {
		this.scrutinyLevel = scrutinyLevel;
	}
	public String getScrutinyType() {
		return scrutinyType;
	}
	public void setScrutinyType(String scrutinyType) {
		this.scrutinyType = scrutinyType;
	}
	
	public ItemBidDto getItemBid() {
		return itemBid;
	}
	public void setItemBid(ItemBidDto itemBid) {
		this.itemBid = itemBid;
	}
	public ItemScrutinyDto getItemScrutiny() {
		return itemScrutiny;
	}
	public void setItemScrutiny(ItemScrutinyDto itemScrutiny) {
		this.itemScrutiny = itemScrutiny;
	}
	public AttachmentDto getAttachment() {
		return attachment;
	}
	public void setAttachment(AttachmentDto attachment) {
		this.attachment = attachment;
	}
	
	
	
}
