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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="t_temp_price_bid")
public class TempPriceBid extends ContextPO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long priceBidId;
	private ItemBid itemBid;
	private BigDecimal offeredQuantity;
	private BigDecimal exGroupPriceRate;
	private BigDecimal totalExGroupPrice;
	private BigDecimal freightChargeRate;
	private BigDecimal totalFreightCharge;
	private BigDecimal ticRate; //transit insurance charges
	private BigDecimal totalTic;
	private BigDecimal exciseDutyRate;
	private BigDecimal exciseDutyAmount;
	private BigDecimal taxRate;
	private BigDecimal taxAmount;
	private BigDecimal fddRate; /*Free Door Delivery Price in Rs per Unit on 
									Road Upto Destination/Store/Sub-Station (in Rs)*/	
	private BigDecimal fddAmount;
	private String fddInWords;
	private String isConfirmed;
	private Set<BidderSectionDoc> bidderSecDoc;
	private String status;
	private String isQuashed="N";
	private String isMatched;
	private ItemBid referedItemBid;
	private Attachment digiSignedDoc;
	
	private String isAnnexureC1;
	
	private String previousFddAmountWithGST;
	private String fddAmountWithGST;
	private String isPreviousBid;
	private String bidRemark;
	
	@Id
	@SequenceGenerator(name = "t_temp_price_bid_seq",sequenceName="t_temp_price_bid_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t_temp_price_bid_seq")
	@Column(name="t_price_bid_id")
	public Long getPriceBidId() {
		return priceBidId;
	}
	public void setPriceBidId(Long priceBidId) {
		this.priceBidId = priceBidId;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
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
	
	@Column(name="excise_duty_rate")
	public BigDecimal getExciseDutyRate() {
		return exciseDutyRate;
	}
	public void setExciseDutyRate(BigDecimal exciseDutyRate) {
		this.exciseDutyRate = exciseDutyRate;
	}
	
	@Column(name="excise_duty_amount")
	public BigDecimal getExciseDutyAmount() {
		return exciseDutyAmount;
	}
	public void setExciseDutyAmount(BigDecimal exciseDutyAmount) {
		this.exciseDutyAmount = exciseDutyAmount;
	}
	
	@Column(name="tax_rate")
	public BigDecimal getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
	
	@Column(name="tax_amount")
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	
	@Column(name="fdd_rate")
	public BigDecimal getFddRate() {
		return fddRate;
	}
	public void setFddRate(BigDecimal fddRate) {
		this.fddRate = fddRate;
	}
	
	@Column(name="fdd_amount")
	public BigDecimal getFddAmount() {
		return fddAmount;
	}
	public void setFddAmount(BigDecimal fddAmount) {
		this.fddAmount = fddAmount;
	}
	
	@Column(name="fdd_in_words")
	public String getFddInWords() {
		return fddInWords;
	}
	public void setFddInWords(String fddInWords) {
		this.fddInWords = fddInWords;
	}
	
	@Column(name="is_confirmed")
	public String getIsConfirmed() {
		return isConfirmed;
	}
	public void setIsConfirmed(String isConfirmed) {
		this.isConfirmed = isConfirmed;
	}
	
	@Column(name="ex_group_price_rate")
	public BigDecimal getExGroupPriceRate() {
		return exGroupPriceRate;
	}
	public void setExGroupPriceRate(BigDecimal exGroupPriceRate) {
		this.exGroupPriceRate = exGroupPriceRate;
	}
	
	@Column(name="total_ex_group_price")
	public BigDecimal getTotalExGroupPrice() {
		return totalExGroupPrice;
	}
	public void setTotalExGroupPrice(BigDecimal totalExGroupPrice) {
		this.totalExGroupPrice = totalExGroupPrice;
	}
	
	@Column(name="freight_charge_rate")
	public BigDecimal getFreightChargeRate() {
		return freightChargeRate;
	}
	public void setFreightChargeRate(BigDecimal freightChargeRate) {
		this.freightChargeRate = freightChargeRate;
	}
	
	@Column(name="total_freight_charge")
	public BigDecimal getTotalFreightCharge() {
		return totalFreightCharge;
	}
	public void setTotalFreightCharge(BigDecimal totalFreightCharge) {
		this.totalFreightCharge = totalFreightCharge;
	}
	
	@Column(name="tic_rate")
	public BigDecimal getTicRate() {
		return ticRate;
	}
	public void setTicRate(BigDecimal ticRate) {
		this.ticRate = ticRate;
	}
	
	@Column(name="total_tic")
	public BigDecimal getTotalTic() {
		return totalTic;
	}
	public void setTotalTic(BigDecimal totalTic) {
		this.totalTic = totalTic;
	}
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="priceBid")
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
	
	@Column(name="is_quashed")
	@Where(clause = "is_quashed='N'")
	public String getIsQuashed() {
		return isQuashed;
	}
	public void setIsQuashed(String isQuashed) {
		this.isQuashed = isQuashed;
	}
	
	@Column(name="is_matched")
	public String getIsMatched() {
		return isMatched;
	}
	public void setIsMatched(String isMatched) {
		this.isMatched = isMatched;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="refered_item_bid_id", referencedColumnName="t_item_bid_id")
	public ItemBid getReferedItemBid() {
		return referedItemBid;
	}
	public void setReferedItemBid(ItemBid referedItemBid) {
		this.referedItemBid = referedItemBid;
	}
	

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="digitally_signed_doc_id")
	public Attachment getDigiSignedDoc() {
		return digiSignedDoc;
	}
	public void setDigiSignedDoc(Attachment digiSignedDoc) {
		this.digiSignedDoc = digiSignedDoc;
	}
	
	@Column(name="is_annexure_c1")
	public String getIsAnnexureC1() {
		return isAnnexureC1;
	}
	public void setIsAnnexureC1(String isAnnexureC1) {
		this.isAnnexureC1 = isAnnexureC1;
	}
	@Column(name="previous_ffd_amount_with_gst")
	public String getPreviousFddAmountWithGST() {
		return previousFddAmountWithGST;
	}
	public void setPreviousFddAmountWithGST(String previousFddAmountWithGST) {
		this.previousFddAmountWithGST = previousFddAmountWithGST;
	}
	@Column(name="fdd_amount_with_gst")
	public String getFddAmountWithGST() {
		return fddAmountWithGST;
	}
	public void setFddAmountWithGST(String fddAmountWithGST) {
		this.fddAmountWithGST = fddAmountWithGST;
	}
	@Column(name="is_previous_bid")
	public String getIsPreviousBid() {
		return isPreviousBid;
	}
	public void setIsPreviousBid(String isPreviousBid) {
		this.isPreviousBid = isPreviousBid;
	}
	@Column(name="bid_remark")
	public String getBidRemark() {
		return bidRemark;
	}
	public void setBidRemark(String bidRemark) {
		this.bidRemark = bidRemark;
	}
	
}
