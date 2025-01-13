package com.novelerp.eat.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

/**
 * 
 * @author Aman Sahu
 *
 */
public class BidderStatusDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	private Long bidderStatusId;
    private String status;
    private String scrutinyComment;
    private BidderDto bidder;
    
	public Long getBidderStatusId() {
		return bidderStatusId;
	}
	public void setBidderStatusId(Long bidderStatusId) {
		this.bidderStatusId = bidderStatusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getScrutinyComment() {
		return scrutinyComment;
	}
	public void setScrutinyComment(String scrutinyComment) {
		this.scrutinyComment = scrutinyComment;
	}
	public BidderDto getBidder() {
		return bidder;
	}
	public void setBidder(BidderDto bidder) {
		this.bidder = bidder;
	}
    
    
}
