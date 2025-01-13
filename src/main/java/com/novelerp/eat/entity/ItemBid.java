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

import com.novelerp.alkyl.entity.PRLine;
import com.novelerp.appcontext.entity.ContextPO;

/**
 * @author Aman Sahu
 *
 */
@Entity
@Table(name="t_item_bid")
public class ItemBid extends ContextPO{

	private static final long serialVersionUID = 1L;
	private Long itemBidId;
	private Bidder bidder;
	private TAHDRMaterial tahdrMaterial;
	private String status;
	private String isLowestBid;
	
	private PRLine prLine;
	private Double quantity = new Double(0);
	private String lineNumber;
	
	@Id
	@SequenceGenerator(name="t_item_bid_seq",sequenceName="t_item_bid_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="t_item_bid_seq")	
	@Column(name = "t_item_bid_id", updatable=false)
	public Long getItemBidId() {
		return itemBidId;
	}
	public void setItemBidId(Long itemBidId) {
		this.itemBidId = itemBidId;
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
	@JoinColumn(name="t_tahdr_material_id")
	public TAHDRMaterial getTahdrMaterial() {
		return tahdrMaterial;
	}
	public void setTahdrMaterial(TAHDRMaterial tahdrMaterial) {
		this.tahdrMaterial = tahdrMaterial;
	}

	/*@OneToOne(fetch=FetchType.LAZY,mappedBy="itemBid")
	public CommercialBid getCommercialBid() {
		return commercialBid;
	}
	public void setCommercialBid(CommercialBid commercialBid) {
		this.commercialBid = commercialBid;
	}*/
	/*@OneToOne(fetch=FetchType.LAZY,mappedBy="itemBid")
	public TechnicalBid getTechnicalBid() {
		return technicalBid;
	}
	public void setTechnicalBid(TechnicalBid technicalBid) {
		this.technicalBid = technicalBid;
	}*/
	/*@OneToOne(fetch=FetchType.LAZY,mappedBy="itemBid")
	@Where(clause = " m_material_specification_id is null ")
	public PriceBid getPriceBid() {
		return priceBid;
	}
	public void setPriceBid(PriceBid priceBid) {
		this.priceBid = priceBid;
	}*/
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

	@Column(name="is_lowest_bid")
	public String getIsLowestBid() {
		return isLowestBid;
	}
	public void setIsLowestBid(String isLowestBid) {
		this.isLowestBid = isLowestBid;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_pr_line_id")
	public PRLine getPrLine() {
		return prLine;
	}
	public void setPrLine(PRLine prLine) {
		this.prLine = prLine;
	}
	@Column(name="qty")
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	@Column(name="line_number")
	public String getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

}
