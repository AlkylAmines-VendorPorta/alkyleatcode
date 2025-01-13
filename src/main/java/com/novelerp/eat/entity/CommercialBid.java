/**
 * @author Ankush
 */
package com.novelerp.eat.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appbase.master.entity.Tax;
import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="t_commercial_bid")
public class CommercialBid extends ContextPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long commercialBidId;
	private BigDecimal exciseDuty;
	private BigDecimal ExciseDutyRate;
	private Tax tax;
	private BigDecimal firstLot;
	private Long deliveringMonth;
	private BigDecimal ratePerMonth;
	private Attachment digiSignedDoc;
	private Bidder bidder;

	private Set<BidderSectionDoc> bidderSecDoc;

	private String status;	
	
	private BigDecimal gst;
	private BigDecimal igst;
	private BigDecimal cgst;
	private BigDecimal sgst;
	
	private TAHDRDetail tahdrDetail;
	
	@Id
	@SequenceGenerator(name="t_commercial_bid_seq",sequenceName="t_commercial_bid_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="t_commercial_bid_seq")	
	@Column(name = "t_commercial_bid_id", updatable=false)
	public Long getCommercialBidId() {
		return commercialBidId;
	}
	public void setCommercialBidId(Long commercialBidId) {
		this.commercialBidId = commercialBidId;
	}
	
	@Column(name="excise_duty")
	public BigDecimal getExciseDuty() {
		return exciseDuty;
	}
	public void setExciseDuty(BigDecimal exciseDuty) {
		this.exciseDuty = exciseDuty;
	}
	
	@Column(name="excise_rate")
	public BigDecimal getExciseDutyRate() {
		return ExciseDutyRate;
	}
	public void setExciseDutyRate(BigDecimal exciseDutyRate) {
		ExciseDutyRate = exciseDutyRate;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_tax_id")
	public Tax getTax() {
		return tax;
	}
	public void setTax(Tax tax) {
		this.tax = tax;
	}
	
	@Column(name="first_lot")
	public BigDecimal getFirstLot() {
		return firstLot;
	}
	public void setFirstLot(BigDecimal firstLot) {
		this.firstLot = firstLot;
	}
	
	@Column(name="delivering_month")
	public Long getDeliveringMonth() {
		return deliveringMonth;
	}
	public void setDeliveringMonth(Long deliveringMonth) {
		this.deliveringMonth = deliveringMonth;
	}
	
	@Column(name="rate_per_month")
	public BigDecimal getRatePerMonth() {
		return ratePerMonth;
	}
	public void setRatePerMonth(BigDecimal ratePerMonth) {
		this.ratePerMonth = ratePerMonth;
	}
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="commercialBid")
	public Set<BidderSectionDoc> getBidderSecDoc() {
		return bidderSecDoc;
	}
	public void setBidderSecDoc(Set<BidderSectionDoc> bidderSecDoc) {
		this.bidderSecDoc = bidderSecDoc;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

	/*@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_item_bid_id")
	public ItemBid getItemBid() {
		return itemBid;
	}
	public void setItemBid(ItemBid itemBid) {
		this.itemBid = itemBid;
	}*/
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_bidder_id")	
	public Bidder getBidder() {
		return bidder;
	}
	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="digitally_signed_doc_id")
	public Attachment getDigiSignedDoc() {
		return digiSignedDoc;
	}
	public void setDigiSignedDoc(Attachment digiSignedDoc) {
		this.digiSignedDoc = digiSignedDoc;
	}
	
	@Column(name="gst")
	public BigDecimal getGst() {
		return gst;
	}
	public void setGst(BigDecimal gst) {
		this.gst = gst;
	}
	
	@Column(name="igst")
	public BigDecimal getIgst() {
		return igst;
	}
	public void setIgst(BigDecimal igst) {
		this.igst = igst;
	}
	
	@Column(name="cgst")
	public BigDecimal getCgst() {
		return cgst;
	}
	public void setCgst(BigDecimal cgst) {
		this.cgst = cgst;
	}
	
	@Column(name="sgst")
	public BigDecimal getSgst() {
		return sgst;
	}
	public void setSgst(BigDecimal sgst) {
		this.sgst = sgst;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_tahdr_detail_id")
	public TAHDRDetail getTahdrDetail() {
		return tahdrDetail;
	}
	public void setTahdrDetail(TAHDRDetail tahdrDetail) {
		this.tahdrDetail = tahdrDetail;
	}
	
}