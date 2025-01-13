package com.novelerp.alkyl.dto;

import java.util.List;

import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.PriceBidDto;

public class QuotationDto {

	private List<PriceBidDto> quotations;

	private BidderDto bidder;
	
	private List<BidderAttachmentDto> bidderAttachment;
	
	public List<PriceBidDto> getQuotations() {
		return quotations;
	}

	public void setQuotations(List<PriceBidDto> quotations) {
		this.quotations = quotations;
	}

	public BidderDto getBidder() {
		return bidder;
	}

	public void setBidder(BidderDto bidder) {
		this.bidder = bidder;
	}

	public List<BidderAttachmentDto> getBidderAttachment() {
		return bidderAttachment;
	}

	public void setBidderAttachment(List<BidderAttachmentDto> bidderAttachment) {
		this.bidderAttachment = bidderAttachment;
	}
	
	
}
