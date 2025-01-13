/**
 * @author Ankush
 */
package com.novelerp.eat.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.novelerp.appcontext.entity.ContextPO;

public class AnnexureC1Bid extends ContextPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long annexureC1BidId;
	private ItemBid itemBid;
	private BigDecimal offeredQuantity;
	private BigDecimal matchedPrice;
	private String isAccepted;
	private Bidder bidder;
	private TAHDR tahdr;
	private TAHDRDetail tahdrDetail;
	
	@Id
	@SequenceGenerator(name = "t_annexure_c1_bid_seq",sequenceName="t_annexure_c1_bid_seq",allocationSize=1)
	@GeneratedValue(generator="t_annexure_c1_bid_seq",strategy=GenerationType.SEQUENCE)
	@Column(name="t_annexure_c1_bid_id")
	public Long getAnnexureC1BidId() {
		return annexureC1BidId;
	}
	public void setAnnexureC1BidId(Long annexureC1BidId) {
		this.annexureC1BidId = annexureC1BidId;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_item_bid_id")
	public ItemBid getItemBid() {
		return itemBid;
	}
	public void setItemBid(ItemBid itemBid) {
		this.itemBid = itemBid;
	}
	
	@Column(name="offered_quantity")
	public BigDecimal getOfferedQuantity() {
		return offeredQuantity;
	}
	public void setOfferedQuantity(BigDecimal offeredQuantity) {
		this.offeredQuantity = offeredQuantity;
	}
	
	@Column(name="mateched_price")
	public BigDecimal getMatchedPrice() {
		return matchedPrice;
	}
	public void setMatchedPrice(BigDecimal matchedPrice) {
		this.matchedPrice = matchedPrice;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_bidder_id")
	public Bidder getBidder() {
		return bidder;
	}
	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_tahdr_id")
	public TAHDR getTahdr() {
		return tahdr;
	}
	public void setTahdr(TAHDR tahdr) {
		this.tahdr = tahdr;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_tahdr_detail_id")
	public TAHDRDetail getTahdrDetail() {
		return tahdrDetail;
	}
	public void setTahdrDetail(TAHDRDetail tahdrDetail) {
		this.tahdrDetail = tahdrDetail;
	}
	
	@Column(name="isAccepted")
	public String getIsAccepted() {
		return isAccepted;
	}
	public void setIsAccepted(String isAccepted) {
		this.isAccepted = isAccepted;
	}
	
}
