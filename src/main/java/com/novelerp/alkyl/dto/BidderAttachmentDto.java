package com.novelerp.alkyl.dto;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.eat.dto.BidderDto;

public class BidderAttachmentDto extends CommonContextDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long bidderAttachmentId;
	private BidderDto bidder;
	private String name;
	private AttachmentDto attachment;
	private String description;
	private String code;
	public Long getBidderAttachmentId() {
		return bidderAttachmentId;
	}
	public void setBidderAttachmentId(Long bidderAttachmentId) {
		this.bidderAttachmentId = bidderAttachmentId;
	}
	public BidderDto getBidder() {
		return bidder;
	}
	public void setBidder(BidderDto bidder) {
		this.bidder = bidder;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AttachmentDto getAttachment() {
		return attachment;
	}
	public void setAttachment(AttachmentDto attachment) {
		this.attachment = attachment;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
