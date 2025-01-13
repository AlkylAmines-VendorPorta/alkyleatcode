package com.novelerp.eat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.alkyl.entity.Annexure;
import com.novelerp.alkyl.entity.PRLine;
import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name = "t_winner_selection")
public class WinnerSelection extends ContextPO {

	/**
	 * ankita
	 */
	private static final long serialVersionUID = 1L;
	private Long winnerSelectionId;
	private TAHDR tahdr;
	/*private TAHDRDetail tahdrDetail;
	private TAHDRMaterial tahdrMaterial;
	private Bidder bidder;*/
	private ItemBid itemBid;
	
	private double allocatedQty;
	private Integer qualityRating;
	private Integer priceRating;
	private Integer deliveryRating;
	private Integer serviceRating;
	private double rating;
	private PRLine prLine;
	private Annexure annexure;

	@Id
	@SequenceGenerator(name = "t_winner_selection_seq", sequenceName = "t_winner_selection_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_winner_selection_seq")
	@Column(name = "t_winner_selection_id", updatable = false)
	public Long getWinnerSelectionId() {
		return winnerSelectionId;
	}

	public void setWinnerSelectionId(Long winnerSelectionId) {
		this.winnerSelectionId = winnerSelectionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_tahdr_id")
	public TAHDR getTahdr() {
		return tahdr;
	}

	public void setTahdr(TAHDR tahdr) {
		this.tahdr = tahdr;
	}

	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_tahdr_material_id")
	public TAHDRMaterial getTahdrMaterial() {
		return tahdrMaterial;
	}

	public void setTahdrMaterial(TAHDRMaterial tahdrMaterial) {
		this.tahdrMaterial = tahdrMaterial;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_bidder_id")
	public Bidder getBidder() {
		return bidder;
	}

	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}
*/

	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_tahdr_detail_id")
	public TAHDRDetail getTahdrDetail() {
		return tahdrDetail;
	}

	public void setTahdrDetail(TAHDRDetail tahdrDetail) {
		this.tahdrDetail = tahdrDetail;
	}*/

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_item_bid_id")
	public ItemBid getItemBid() {
		return itemBid;
	}

	public void setItemBid(ItemBid itemBid) {
		this.itemBid = itemBid;
	}

	@Column(name = "quality_rating")
	public Integer getQualityRating() {
		return qualityRating;
	}

	public void setQualityRating(Integer qualityRating) {
		this.qualityRating = qualityRating;
	}

	@Column(name = "price_rating")
	public Integer getPriceRating() {
		return priceRating;
	}

	public void setPriceRating(Integer priceRating) {
		this.priceRating = priceRating;
	}

	@Column(name = "delivery_rating")
	public Integer getDeliveryRating() {
		return deliveryRating;
	}

	public void setDeliveryRating(Integer deliveryRating) {
		this.deliveryRating = deliveryRating;
	}

	@Column(name = "service_rating")
	public Integer getServiceRating() {
		return serviceRating;
	}

	public void setServiceRating(Integer serviceRating) {
		this.serviceRating = serviceRating;
	}

	@Column(name = "rating")
	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_pr_line_id")
	public PRLine getPrLine() {
		return prLine;
	}

	public void setPrLine(PRLine prLine) {
		this.prLine = prLine;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_annexure_id")
	public Annexure getAnnexure() {
		return annexure;
	}

	public void setAnnexure(Annexure annexure) {
		this.annexure = annexure;
	}
	@Column(name = "allocated_qty")
	public double getAllocatedQty() {
		return allocatedQty;
	}

	public void setAllocatedQty(double allocatedQty) {
		this.allocatedQty = allocatedQty;
	}
	
}
