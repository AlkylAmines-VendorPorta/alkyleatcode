/**
 * @author Ankush
 */
package com.novelerp.eat.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.appcontext.dto.CommonContextDto;

public class AnnexureC1BidDto extends CommonContextDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long annexureC1BidId;
	private ItemBidDto itemBid;
	private BigDecimal offeredQuantity;
	private BigDecimal matchedPrice;
	private String isAccepted;
	private BidderDto bidder;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="tahdrId",scope=Long.class)
	private TAHDRDto tahdr;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="tahdrDetailId",scope=Long.class)
	private TAHDRDetailDto tahdrDetail;
	
	public Long getAnnexureC1BidId() {
		return annexureC1BidId;
	}
	public void setAnnexureC1BidId(Long annexureC1BidId) {
		this.annexureC1BidId = annexureC1BidId;
	}
	public ItemBidDto getItemBid() {
		return itemBid;
	}
	public void setItemBid(ItemBidDto itemBid) {
		this.itemBid = itemBid;
	}
	public BigDecimal getOfferedQuantity() {
		return offeredQuantity;
	}
	public void setOfferedQuantity(BigDecimal offeredQuantity) {
		this.offeredQuantity = offeredQuantity;
	}
	public BigDecimal getMatchedPrice() {
		return matchedPrice;
	}
	public void setMatchedPrice(BigDecimal matchedPrice) {
		this.matchedPrice = matchedPrice;
	}
	public String getIsAccepted() {
		return isAccepted;
	}
	public void setIsAccepted(String isAccepted) {
		this.isAccepted = isAccepted;
	}
	public BidderDto getBidder() {
		return bidder;
	}
	public void setBidder(BidderDto bidder) {
		this.bidder = bidder;
	}
	public TAHDRDto getTahdr() {
		return tahdr;
	}
	public void setTahdr(TAHDRDto tahdr) {
		this.tahdr = tahdr;
	}
	public TAHDRDetailDto getTahdrDetail() {
		return tahdrDetail;
	}
	public void setTahdrDetail(TAHDRDetailDto tahdrDetail) {
		this.tahdrDetail = tahdrDetail;
	}
	
}
