package com.novelerp.eat.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.alkyl.dto.PRLineDto;
import com.novelerp.appcontext.dto.CommonContextDto;

/**
 * 
 * @author Aman Sahu
 *
 */
public class ItemBidDto extends CommonContextDto {

	private static final long serialVersionUID = 1L;
	private Long itemBidId;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="bidderId",scope=Long.class)
	private BidderDto bidder;
	private TAHDRMaterialDto tahdrMaterial;
	private TechnicalBidDto technicalBid;
	private PriceBidDto priceBid;
	private String status;
	private String isLowestBid;
	
	private PRLineDto prLine;
	private Double quantity = new Double(0);
	private String lineNumber;
	
	public Long getItemBidId() {
		return itemBidId;
	}
	public void setItemBidId(Long itemBidId) {
		this.itemBidId = itemBidId;
	}
	public BidderDto getBidder() {
		return bidder;
	}
	public void setBidder(BidderDto bidder) {
		this.bidder = bidder;
	}
	public TAHDRMaterialDto getTahdrMaterial() {
		return tahdrMaterial;
	}
	public void setTahdrMaterial(TAHDRMaterialDto tahdrMaterial) {
		this.tahdrMaterial = tahdrMaterial;
	}
	public TechnicalBidDto getTechnicalBid() {
		return technicalBid;
	}
	public void setTechnicalBid(TechnicalBidDto technicalBid) {
		this.technicalBid = technicalBid;
	}
	public PriceBidDto getPriceBid() {
		return priceBid;
	}
	public void setPriceBid(PriceBidDto priceBid) {
		this.priceBid = priceBid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getIsLowestBid() {
		return isLowestBid;
	}
	public void setIsLowestBid(String isLowestBid) {
		this.isLowestBid = isLowestBid;
	}
	public PRLineDto getPrLine() {
		return prLine;
	}
	public void setPrLine(PRLineDto prLine) {
		this.prLine = prLine;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public String getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	
}
