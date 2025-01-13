package com.novelerp.eat.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.novelerp.alkyl.dto.AnnexureDto;
import com.novelerp.alkyl.dto.PRLineDto;
import com.novelerp.appcontext.dto.CommonContextDto;

public class WinnerSelectionDto  extends CommonContextDto {

	/**
	 * Ankita
	 */
	private static final long serialVersionUID = 1L;

	private Long winnerSelectionId;
	private TAHDRDto tahdr;
	/*private TAHDRDetailDto tahdrDetail;
	private TAHDRMaterialDto tahdrMaterial;*/
	private ItemBidDto itemBid;
	/*private BidderDto bidder;*/
	private double allocatedQty;
	private double remainingQty;
	private Integer qualityRating;
	private Integer priceRating;
	private Integer deliveryRating;
	private Integer serviceRating;
	private double rating;
	private PRLineDto prLine;
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="annexureId")
	private AnnexureDto annexure;
	
	public Long getWinnerSelectionId() {
		return winnerSelectionId;
	}
	public void setWinnerSelectionId(Long winnerSelectionId) {
		this.winnerSelectionId = winnerSelectionId;
	}
	public TAHDRDto getTahdr() {
		return tahdr;
	}
	public void setTahdr(TAHDRDto tahdr) {
		this.tahdr = tahdr;
	}
	/*public TAHDRMaterialDto getTahdrMaterial() {
		return tahdrMaterial;
	}
	public void setTahdrMaterial(TAHDRMaterialDto tahdrMaterial) {
		this.tahdrMaterial = tahdrMaterial;
	}
	public BidderDto getBidder() {
		return bidder;
	}
	public void setBidder(BidderDto bidder) {
		this.bidder = bidder;
	}*/
	/*public TAHDRDetailDto getTahdrDetail() {
		return tahdrDetail;
	}
	public void setTahdrDetail(TAHDRDetailDto tahdrDetail) {
		this.tahdrDetail = tahdrDetail;
	}*/
	public ItemBidDto getItemBid() {
		return itemBid;
	}
	public void setItemBid(ItemBidDto itemBid) {
		this.itemBid = itemBid;
	}
	public Integer getQualityRating() {
		return qualityRating;
	}
	public void setQualityRating(Integer qualityRating) {
		this.qualityRating = qualityRating;
	}
	public Integer getPriceRating() {
		return priceRating;
	}
	public void setPriceRating(Integer priceRating) {
		this.priceRating = priceRating;
	}
	public Integer getDeliveryRating() {
		return deliveryRating;
	}
	public void setDeliveryRating(Integer deliveryRating) {
		this.deliveryRating = deliveryRating;
	}
	public Integer getServiceRating() {
		return serviceRating;
	}
	public void setServiceRating(Integer serviceRating) {
		this.serviceRating = serviceRating;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public PRLineDto getPrLine() {
		return prLine;
	}
	public void setPrLine(PRLineDto prLine) {
		this.prLine = prLine;
	}
	public AnnexureDto getAnnexure() {
		return annexure;
	}
	public void setAnnexure(AnnexureDto annexure) {
		this.annexure = annexure;
	}
	public double getAllocatedQty() {
		return allocatedQty;
	}
	public void setAllocatedQty(double allocatedQty) {
		this.allocatedQty = allocatedQty;
	}
	public double getRemainingQty() {
		return remainingQty;
	}
	public void setRemainingQty(double remainingQty) {
		this.remainingQty = remainingQty;
	}
	

	
}
