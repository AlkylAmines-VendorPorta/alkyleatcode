/**
 * @author Ankush
 */
package com.novelerp.eat.entity;

import java.util.Date;
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
import com.novelerp.appbase.master.entity.MaterialSpecification;
import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="t_price_bid")
public class PriceBid extends ContextPO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long priceBidId;
	private ItemBid itemBid;
	private String offeredQuantity;
	private String exGroupPriceRate;
	private String totalExGroupPrice;
	private double freightChargeRate;
	private double totalFreightCharge;
	private String ticRate; //transit insurance charges
	private String totalTic;
	private String taxRate;//per unit GST
	private String taxAmount;//per unit GST AMOUNT
	private String fddRate; /*Free Door Delivery Price in Rs per Unit on 
									Road Upto Destination/Store/Sub-Station (in Rs) withoun GST*/	
	private String fddAmount;//total fdd without GST
	private String igst;
	private String cgst;
	private String sgst;
	private String igstAmount;//per unit
	private String cgstAmount;//per unit
	private String sgstAmount;//per unit
	
	private String totalTax;
	private String fddRateWithGST;
	private String fddAmountWithGST;
	
	private String previousFddAmountWithGST;
	
	private String totalIgst;
	private String totalCgst;
	private String totalSgst;
	
	private String fddInWords;
	private String isConfirmed;
	private Set<BidderSectionDoc> bidderSecDoc;
	private String status;
	private String isQuashed="N";
	private String isMatched;
	private ItemBid referedItemBid;
	private Attachment digiSignedDoc;
	private String isAnnexureC1;
	private MaterialSpecification materialSpecification;
	private String isEncrypted;
	private String amountInWords;
	private String clauseA;
	private String clauseB;
	private String isC1DigidocSubmitted;
	private String isPreviousBid;
	private String bidRemark;
	
	
	private String freightChargesType;
	private double packingFwdCharge;
	private String packingFwdChargeType;
	private double totalPackingFwdChargeAmt;
	private double basicAmount;
	private String otherChargesType;
	private double otherChargesRate;
	private double otherChargesAmt;
	private double completeOther;
	private Date deliveryDate;
	private double perQtyFreightAmt;
	private double perQtyPKAmt;
	private double perQtyOtherAmt;
	private String discountType;
	private double perQtyDiscount;
	private double netRate;
	private String discountCharge;
	private String vendorText;
	
	@Id
	@SequenceGenerator(name = "t_price_bid_seq",sequenceName="t_price_bid_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t_price_bid_seq")
	@Column(name="t_price_bid_id",updatable=false)
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
	public String getOfferedQuantity() {
		return offeredQuantity;
	}
	public void setOfferedQuantity(String offeredQuantity) {
		this.offeredQuantity = offeredQuantity;
	}
	
	/*@Column(name="excise_duty_rate")
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
	}*/
	
	@Column(name="tax_rate")
	public String getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
	
	@Column(name="tax_amount")
	public String getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}
	
	@Column(name="fdd_rate")
	public String getFddRate() {
		return fddRate;
	}
	public void setFddRate(String fddRate) {
		this.fddRate = fddRate;
	}
	
	@Column(name="fdd_amount")
	public String getFddAmount() {
		return fddAmount;
	}
	public void setFddAmount(String fddAmount) {
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
	public String getExGroupPriceRate() {
		return exGroupPriceRate;
	}
	public void setExGroupPriceRate(String exGroupPriceRate) {
		this.exGroupPriceRate = exGroupPriceRate;
	}
	
	@Column(name="total_ex_group_price")
	public String getTotalExGroupPrice() {
		return totalExGroupPrice;
	}
	public void setTotalExGroupPrice(String totalExGroupPrice) {
		this.totalExGroupPrice = totalExGroupPrice;
	}
	
	
	
	@Column(name="tic_rate")
	public String getTicRate() {
		return ticRate;
	}
	public void setTicRate(String ticRate) {
		this.ticRate = ticRate;
	}
	
	@Column(name="total_tic")
	public String getTotalTic() {
		return totalTic;
	}
	public void setTotalTic(String totalTic) {
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
	
	@Column(name="igst")
	public String getIgst() {
		return igst;
	}
	public void setIgst(String igst) {
		this.igst = igst;
	}
	
	@Column(name="cgst")
	public String getCgst() {
		return cgst;
	}
	public void setCgst(String cgst) {
		this.cgst = cgst;
	}
	
	@Column(name="sgst")
	public String getSgst() {
		return sgst;
	}
	public void setSgst(String sgst) {
		this.sgst = sgst;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_material_specification_id")
	public MaterialSpecification getMaterialSpecification() {
		return materialSpecification;
	}
	public void setMaterialSpecification(MaterialSpecification materialSpecification) {
		this.materialSpecification = materialSpecification;
	}
	
	@Column(name="igst_amount")
	public String getIgstAmount() {
		return igstAmount;
	}
	public void setIgstAmount(String igstAmount) {
		this.igstAmount = igstAmount;
	}
	
	@Column(name="cgst_amount")
	public String getCgstAmount() {
		return cgstAmount;
	}
	public void setCgstAmount(String cgstAmount) {
		this.cgstAmount = cgstAmount;
	}
	
	@Column(name="sgst_amount")
	public String getSgstAmount() {
		return sgstAmount;
	}
	public void setSgstAmount(String sgstAmount) {
		this.sgstAmount = sgstAmount;
	}
	
	@Column(name="total_tax")
	public String getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(String totalTax) {
		this.totalTax = totalTax;
	}
	
	@Column(name="fdd_rate_with_gst")
	public String getFddRateWithGST() {
		return fddRateWithGST;
	}
	public void setFddRateWithGST(String fddRateWithGST) {
		this.fddRateWithGST = fddRateWithGST;
	}
	
	@Column(name="fdd_amount_with_gst")
	public String getFddAmountWithGST() {
		return fddAmountWithGST;
	}
	public void setFddAmountWithGST(String fddAmountWithGST) {
		this.fddAmountWithGST = fddAmountWithGST;
	}
	
	@Column(name="total_igst")
	public String getTotalIgst() {
		return totalIgst;
	}
	public void setTotalIgst(String totalIgst) {
		this.totalIgst = totalIgst;
	}
	
	@Column(name="total_cgst")
	public String getTotalCgst() {
		return totalCgst;
	}
	public void setTotalCgst(String totalCgst) {
		this.totalCgst = totalCgst;
	}
	
	@Column(name="total_sgst")
	public String getTotalSgst() {
		return totalSgst;
	}
	public void setTotalSgst(String totalSgst) {
		this.totalSgst = totalSgst;
	}
	
	@Column(name="is_encrypted")
	public String getIsEncrypted() {
		return isEncrypted;
	}
	public void setIsEncrypted(String isEncrypted) {
		this.isEncrypted = isEncrypted;
	}
	@Column(name="previous_ffd_amount_with_gst")
	public String getPreviousFddAmountWithGST() {
		return previousFddAmountWithGST;
	}
	public void setPreviousFddAmountWithGST(String previousFddAmountWithGST) {
		this.previousFddAmountWithGST = previousFddAmountWithGST;
	}
	
	@Column(name="amount_in_words")
	public String getAmountInWords() {
		return amountInWords;
	}
	public void setAmountInWords(String amountInWords) {
		this.amountInWords = amountInWords;
	}
	@Column(name="clause5a")
	public String getClauseA() {
		return clauseA;
	}
	public void setClauseA(String clauseA) {
		this.clauseA = clauseA;
	}
	@Column(name="clause5b")
	public String getClauseB() {
		return clauseB;
	}
	public void setClauseB(String clauseB) {
		this.clauseB = clauseB;
	}
	@Column(name="is_C1_File_Submitted")
	public String getIsC1DigidocSubmitted() {
		return isC1DigidocSubmitted;
	}
	public void setIsC1DigidocSubmitted(String isC1DigidocSubmitted) {
		this.isC1DigidocSubmitted = isC1DigidocSubmitted;
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
	
	@Column(name="freight_charges_type")
	public String getFreightChargesType() {
		return freightChargesType;
	}
	public void setFreightChargesType(String freightChargesType) {
		this.freightChargesType = freightChargesType;
	}

	
	@Column(name="packing_fwd_charge_type")
	public String getPackingFwdChargeType() {
		return packingFwdChargeType;
	}
	public void setPackingFwdChargeType(String packingFwdChargeType) {
		this.packingFwdChargeType = packingFwdChargeType;
	}
	

	
	@Column(name="other_charges_type")
	public String getOtherChargesType() {
		return otherChargesType;
	}
	public void setOtherChargesType(String otherChargesType) {
		this.otherChargesType = otherChargesType;
	}
	

	
	@Column(name="delivery_date")
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	@Column(name="freight_charge_rate")
	public double getFreightChargeRate() {
		return freightChargeRate;
	}
	public void setFreightChargeRate(double freightChargeRate) {
		this.freightChargeRate = freightChargeRate;
	}
	@Column(name="total_freight_charge")
	public double getTotalFreightCharge() {
		return totalFreightCharge;
	}
	public void setTotalFreightCharge(double totalFreightCharge) {
		this.totalFreightCharge = totalFreightCharge;
	}
	@Column(name="packing_fwd_charge")
	public double getPackingFwdCharge() {
		return packingFwdCharge;
	}
	public void setPackingFwdCharge(double packingFwdCharge) {
		this.packingFwdCharge = packingFwdCharge;
	}
	@Column(name="total_packing_fwd_charge_amt")
	public double getTotalPackingFwdChargeAmt() {
		return totalPackingFwdChargeAmt;
	}
	public void setTotalPackingFwdChargeAmt(double totalPackingFwdChargeAmt) {
		this.totalPackingFwdChargeAmt = totalPackingFwdChargeAmt;
	}
	@Column(name="basic_amount")
	public double getBasicAmount() {
		return basicAmount;
	}
	public void setBasicAmount(double basicAmount) {
		this.basicAmount = basicAmount;
	}
	@Column(name="other_charges_rate")
	public double getOtherChargesRate() {
		return otherChargesRate;
	}
	public void setOtherChargesRate(double otherChargesRate) {
		this.otherChargesRate = otherChargesRate;
	}
	@Column(name="other_charges_amt")
	public double getOtherChargesAmt() {
		return otherChargesAmt;
	}
	public void setOtherChargesAmt(double otherChargesAmt) {
		this.otherChargesAmt = otherChargesAmt;
	}
	@Column(name="complete_other")	
	public double getCompleteOther() {
		return completeOther;
	}
	public void setCompleteOther(double completeOther) {
		this.completeOther = completeOther;
	}
	@Column(name="per_qty_freight_amt")
	public double getPerQtyFreightAmt() {
		return perQtyFreightAmt;
	}
	public void setPerQtyFreightAmt(double perQtyFreightAmt) {
		this.perQtyFreightAmt = perQtyFreightAmt;
	}
	@Column(name="per_qty_pk_amt")
	public double getPerQtyPKAmt() {
		return perQtyPKAmt;
	}
	public void setPerQtyPKAmt(double perQtyPKAmt) {
		this.perQtyPKAmt = perQtyPKAmt;
	}
	@Column(name="per_qty_other_amt")
	public double getPerQtyOtherAmt() {
		return perQtyOtherAmt;
	}
	public void setPerQtyOtherAmt(double perQtyOtherAmt) {
		this.perQtyOtherAmt = perQtyOtherAmt;
	}
	@Column(name="discount_type")
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	@Column(name="per_qty_discount")
	public double getPerQtyDiscount() {
		return perQtyDiscount;
	}
	public void setPerQtyDiscount(double perQtyDiscount) {
		this.perQtyDiscount = perQtyDiscount;
	}
	@Column(name="net_rate")
	public double getNetRate() {
		return netRate;
	}
	public void setNetRate(double netRate) {
		this.netRate = netRate;
	}
	/*@Column(name="discount_rate")
	public double getDiscountCharge() {
		return discountCharge;
	}
	public void setDiscountCharge(double discountCharge) {
		this.discountCharge = discountCharge;
	}*/
	@Column(name="discount_rate")
	public String getDiscountCharge() {
		return discountCharge;
	}
	public void setDiscountCharge(String discountCharge) {
		this.discountCharge = discountCharge;
	}
	
	@Column(name="vendor_text")
	public String getVendorText() {
		return vendorText;
	}
	public void setVendorText(String vendorText) {
		this.vendorText = vendorText;
	}

	
	
	
}
