package com.novelerp.eat.dto;

import java.util.Set;

import com.novelerp.appcontext.dto.CommonContextDto;

/**
 * 
 * @author Aman Sahu
 *
 */

public class ScrutinyDto extends CommonContextDto{

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long scrutinyId;
	private BidderDto bidder;
	private String scrutinyStatusCode;
	private String tenderSecCode;
	private String scrutinyTypeCode;
	private Set<ItemScrutinyDto> itemScrutinyList;
	
	public Long getScrutinyId() {
		return scrutinyId;
	}
	public void setScrutinyId(Long scrutinyId) {
		this.scrutinyId = scrutinyId;
	}
	
	public String getScrutinyStatusCode() {
		return scrutinyStatusCode;
	}
	public void setScrutinyStatusCode(String scrutinyStatusCode) {
		this.scrutinyStatusCode = scrutinyStatusCode;
	}
	public String getTenderSecCode() {
		return tenderSecCode;
	}
	public void setTenderSecCode(String tenderSecCode) {
		this.tenderSecCode = tenderSecCode;
	}
	public String getScrutinyTypeCode() {
		return scrutinyTypeCode;
	}
	public void setScrutinyTypeCode(String scrutinyTypeCode) {
		this.scrutinyTypeCode = scrutinyTypeCode;
	}
	public Set<ItemScrutinyDto> getItemScrutinyList() {
		return itemScrutinyList;
	}
	public void setItemScrutinyList(Set<ItemScrutinyDto> itemScrutinyList) {
		this.itemScrutinyList = itemScrutinyList;
	}
	public BidderDto getBidder() {
		return bidder;
	}
	public void setBidder(BidderDto bidder) {
		this.bidder = bidder;
	}
	

	

}
