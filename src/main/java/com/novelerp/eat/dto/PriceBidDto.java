/**
 * @author Ankush
 */
package com.novelerp.eat.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.dto.MaterialSpecificationDto;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.commons.util.CommonUtil;

public class PriceBidDto extends CommonContextDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long priceBidId;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="itemBidId",scope=Long.class)
	private ItemBidDto itemBid;
	private String offeredQuantity;
	private String exGroupPriceRate;
	private String totalExGroupPrice;
	private double freightChargeRate;
	private double totalFreightCharge;
	private String ticRate; //transit insurance charges
	private String totalTic;
	private String exciseDutyRate;
	private String exciseDutyAmount;
	private String taxRate;
	private String taxAmount;
	private String fddRate; /*Free Door Delivery Price in Rs per Unit on 
									Road Upto Destination/Store/Sub-Station (in Rs)*/	
	private String fddAmount;
	private String fddInWords;
	private String isConfirmed;
	private String status;
	
	private Set<BidderSectionDocDto> bidderSecDoc;
	private String isQuashed="N";
	private String isMatched;
	private String lowestPrice;
	private String lowestPriceQuantity;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="itemBidId",scope=Long.class)
	private ItemBidDto referedItemBid;
	private List<BidderSectionDocDto> bidderSecDocList;
	private AttachmentDto digiSignedDoc;
	
	private String isAnnexureC1;
	private String sgst;
	private String cgst;
	private String igst;
	
	private MaterialSpecificationDto materialSpecification;
	
	private String totalSgst;
	private String totalCgst;
	private String totalIgst;
	private String fddAmountWithGST;
	
	private String previousFddAmountWithGST;
	
	private String fddRateWithGST;
	private String totalTax;
	private String sgstAmount;
	private String cgstAmount;
	private String igstAmount;
	private String isEncrypted;
	private boolean encryptBid=false;
	private String amountInWords;
	private String isRevised;
	private String clauseA;
	private String clauseB;
	private String isC1DigidocSubmitted;
	
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
	
	public Long getPriceBidId() {
		return priceBidId;
	}
	public void setPriceBidId(Long priceBidId) {
		this.priceBidId = priceBidId;
	}
	public ItemBidDto getItemBid() {
		return itemBid;
	}
	public void setItemBid(ItemBidDto itemBid) {
		this.itemBid = itemBid;
	}
	public String getOfferedQuantity() {
		return offeredQuantity;
	}
	public void setOfferedQuantity(String offeredQuantity) {
		this.offeredQuantity = offeredQuantity;
	}
	
	public String getExciseDutyAmount() {
		return exciseDutyAmount;
	}
	public void setExciseDutyAmount(String exciseDutyAmount) {
		this.exciseDutyAmount = exciseDutyAmount;
	}
	
	public String getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}
	
	public String getFddAmount() {
		return fddAmount;
	}
	public void setFddAmount(String fddAmount) {
		this.fddAmount = fddAmount;
	}
	public String getFddInWords() {
		return fddInWords;
	}
	public void setFddInWords(String fddInWords) {
		this.fddInWords = fddInWords;
	}
	public String getIsConfirmed() {
		return isConfirmed;
	}
	public void setIsConfirmed(String isConfirmed) {
		this.isConfirmed = isConfirmed;
	}
	
	public String getExciseDutyRate() {
		return exciseDutyRate;
	}
	public void setExciseDutyRate(String exciseDutyRate) {
		this.exciseDutyRate = exciseDutyRate;
	}
	public String getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
	public String getFddRate() {
		return fddRate;
	}
	public void setFddRate(String fddRate) {
		this.fddRate = fddRate;
	}
	public String getExGroupPriceRate() {
		return exGroupPriceRate;
	}
	public void setExGroupPriceRate(String exGroupPriceRate) {
		this.exGroupPriceRate = exGroupPriceRate;
	}
	public String getTotalExGroupPrice() {
		return totalExGroupPrice;
	}
	public void setTotalExGroupPrice(String totalExGroupPrice) {
		this.totalExGroupPrice = totalExGroupPrice;
	}
	
	public double getFreightChargeRate() {
		return freightChargeRate;
	}
	public void setFreightChargeRate(double freightChargeRate) {
		this.freightChargeRate = freightChargeRate;
	}
	public double getTotalFreightCharge() {
		return totalFreightCharge;
	}
	public void setTotalFreightCharge(double totalFreightCharge) {
		this.totalFreightCharge = totalFreightCharge;
	}
	public String getTicRate() {
		return ticRate;
	}
	public void setTicRate(String ticRate) {
		this.ticRate = ticRate;
	}
	public String getTotalTic() {
		return totalTic;
	}
	public void setTotalTic(String totalTic) {
		this.totalTic = totalTic;
	}
	public Set<BidderSectionDocDto> getBidderSecDoc() {
		if(CommonUtil.isCollectionEmpty(bidderSecDoc) && !CommonUtil.isCollectionEmpty(bidderSecDocList)){
			bidderSecDoc=new HashSet<>();
			bidderSecDoc.addAll(getBidderSecDocList());
		}
		return bidderSecDoc;
	}
	public void setBidderSecDoc(Set<BidderSectionDocDto> bidderSecDoc) {
		this.bidderSecDoc = bidderSecDoc;
	}
	public String getIsQuashed() {
		if(null==isQuashed){
			return "N";
		}
		return isQuashed;
	}
	public void setIsQuashed(String isQuashed) {
		this.isQuashed = isQuashed;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLowestPrice() {
		return lowestPrice;
	}
	public void setLowestPrice(String lowestPrice) {
		this.lowestPrice = lowestPrice;
	}
	public String getLowestPriceQuantity() {
		return lowestPriceQuantity;
	}
	public void setLowestPriceQuantity(String lowestPriceQuantity) {
		this.lowestPriceQuantity = lowestPriceQuantity;
	}
	public String getIsMatched() {
		if(null==isMatched){
			return "N";
		}
		return isMatched;
	}
	public void setIsMatched(String isMatched) {
		this.isMatched = isMatched;
	}
	
	public ItemBidDto getReferedItemBid() {
		return referedItemBid;
	}
	public void setReferedItemBid(ItemBidDto referedItemBid) {
		this.referedItemBid = referedItemBid;
	}
	public List<BidderSectionDocDto> getBidderSecDocList() {
		return bidderSecDocList;
	}
	public void setBidderSecDocList(List<BidderSectionDocDto> bidderSecDocList) {
		this.bidderSecDocList = bidderSecDocList;
	}
	public AttachmentDto getDigiSignedDoc() {
		return digiSignedDoc;
	}
	public void setDigiSignedDoc(AttachmentDto digiSignedDoc) {
		this.digiSignedDoc = digiSignedDoc;
	}
	
	public String getIsAnnexureC1() {
		if(null==isAnnexureC1){
			return "N";
		}
		return isAnnexureC1;
	}
	public void setIsAnnexureC1(String isAnnexureC1) {
		this.isAnnexureC1 = isAnnexureC1;
	}
	
	public String getIgst() {
		if(igst==null){
			return "0";
		}
		return igst;
	}

	public void setIgst(String igst) {
		this.igst = igst;
	}

	public String getCgst() {
		if(cgst==null){
			return "0";
		}
		return cgst;
	}

	public void setCgst(String cgst) {
		this.cgst = cgst;
	}

	public String getSgst() {
		if(sgst==null){
			return "0";
		}
		return sgst;
	}

	public void setSgst(String sgst) {
		this.sgst = sgst;
	}
	
	public MaterialSpecificationDto getMaterialSpecification() {
		return materialSpecification;
	}
	public void setMaterialSpecification(MaterialSpecificationDto materialSpecification) {
		this.materialSpecification = materialSpecification;
	}
	
	public String getIgstAmount() {
		return igstAmount;
	}
	public void setIgstAmount(String igstAmount) {
		this.igstAmount = igstAmount;
	}
	public String getCgstAmount() {
		return cgstAmount;
	}
	public void setCgstAmount(String cgstAmount) {
		this.cgstAmount = cgstAmount;
	}
	public String getSgstAmount() {
		return sgstAmount;
	}
	public void setSgstAmount(String sgstAmount) {
		this.sgstAmount = sgstAmount;
	}
	public String getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(String totalTax) {
		this.totalTax = totalTax;
	}
	public String getFddRateWithGST() {
		return fddRateWithGST;
	}
	public void setFddRateWithGST(String fddRateWithGST) {
		this.fddRateWithGST = fddRateWithGST;
	}
	public String getFddAmountWithGST() {
		return fddAmountWithGST;
	}
	public void setFddAmountWithGST(String fddAmountWithGST) {
		this.fddAmountWithGST = fddAmountWithGST;
	}
	public String getTotalIgst() {
		return totalIgst;
	}
	public void setTotalIgst(String totalIgst) {
		this.totalIgst = totalIgst;
	}
	public String getTotalCgst() {
		return totalCgst;
	}
	public void setTotalCgst(String totalCgst) {
		this.totalCgst = totalCgst;
	}
	public String getTotalSgst() {
		return totalSgst;
	}
	public void setTotalSgst(String totalSgst) {
		this.totalSgst = totalSgst;
	}
	public String getIsEncrypted() {
		if(isEncrypted==null){
			return "N";
		}
		return isEncrypted;
	}
	public void setIsEncrypted(String isEncrypted) {
		this.isEncrypted = isEncrypted;
	}
	public boolean getEncryptBid() {
		return encryptBid;
	}
	public void setEncryptBid(boolean encryptBid) {
		this.encryptBid = encryptBid;
	}
	public String getPreviousFddAmountWithGST() {
		return previousFddAmountWithGST;
	}
	public void setPreviousFddAmountWithGST(String previousFddAmountWithGST) {
		this.previousFddAmountWithGST = previousFddAmountWithGST;
	}
	public String getAmountInWords() {
		return amountInWords;
	}
	public void setAmountInWords(String amountInWords) {
		this.amountInWords = amountInWords;
	}
	public String getIsRevised() {
		if(CommonUtil.isStringEmpty(isRevised)){
			return "N";
		}
		return isRevised;
	}
	public void setIsRevised(String isRevised) {
		this.isRevised = isRevised;
	}
	public String getClauseA() {
		return clauseA;
	}
	public void setClauseA(String clauseA) {
		this.clauseA = clauseA;
	}
	public String getClauseB() {
		return clauseB;
	}
	public void setClauseB(String clauseB) {
		this.clauseB = clauseB;
	}
	public String getIsC1DigidocSubmitted() {
		return isC1DigidocSubmitted;
	}
	public void setIsC1DigidocSubmitted(String isC1DigidocSubmitted) {
		this.isC1DigidocSubmitted = isC1DigidocSubmitted;
	}
	public String getFreightChargesType() {
		return freightChargesType;
	}
	public void setFreightChargesType(String freightChargesType) {
		this.freightChargesType = freightChargesType;
	}
	public double getPackingFwdCharge() {
		return packingFwdCharge;
	}
	public void setPackingFwdCharge(double packingFwdCharge) {
		this.packingFwdCharge = packingFwdCharge;
	}
	public String getPackingFwdChargeType() {
		return packingFwdChargeType;
	}
	public void setPackingFwdChargeType(String packingFwdChargeType) {
		this.packingFwdChargeType = packingFwdChargeType;
	}
	public double getTotalPackingFwdChargeAmt() {
		return totalPackingFwdChargeAmt;
	}
	public void setTotalPackingFwdChargeAmt(double totalPackingFwdChargeAmt) {
		this.totalPackingFwdChargeAmt = totalPackingFwdChargeAmt;
	}
	public double getBasicAmount() {
		return basicAmount;
	}
	public void setBasicAmount(double basicAmount) {
		this.basicAmount = basicAmount;
	}
	public String getOtherChargesType() {
		return otherChargesType;
	}
	public void setOtherChargesType(String otherChargesType) {
		this.otherChargesType = otherChargesType;
	}
	public double getOtherChargesRate() {
		return otherChargesRate;
	}
	public void setOtherChargesRate(double otherChargesRate) {
		this.otherChargesRate = otherChargesRate;
	}
	public double getOtherChargesAmt() {
		return otherChargesAmt;
	}
	public void setOtherChargesAmt(double otherChargesAmt) {
		this.otherChargesAmt = otherChargesAmt;
	}
	public double getCompleteOther() {
		return completeOther;
	}
	public void setCompleteOther(double completeOther) {
		this.completeOther = completeOther;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public double getPerQtyFreightAmt() {
		return perQtyFreightAmt;
	}
	public void setPerQtyFreightAmt(double perQtyFreightAmt) {
		this.perQtyFreightAmt = perQtyFreightAmt;
	}
	public double getPerQtyPKAmt() {
		return perQtyPKAmt;
	}
	public void setPerQtyPKAmt(double perQtyPKAmt) {
		this.perQtyPKAmt = perQtyPKAmt;
	}
	public double getPerQtyOtherAmt() {
		return perQtyOtherAmt;
	}
	public void setPerQtyOtherAmt(double perQtyOtherAmt) {
		this.perQtyOtherAmt = perQtyOtherAmt;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	public double getPerQtyDiscount() {
		return perQtyDiscount;
	}
	public void setPerQtyDiscount(double perQtyDiscount) {
		this.perQtyDiscount = perQtyDiscount;
	}
	public double getNetRate() {
		return netRate;
	}
	public void setNetRate(double netRate) {
		this.netRate = netRate;
	}
	public String getDiscountCharge() {
		return discountCharge;
	}
	public void setDiscountCharge(String discountCharge) {
		this.discountCharge = discountCharge;
	}
	public String getVendorText() {
		return vendorText;
	}
	public void setVendorText(String vendorText) {
		this.vendorText = vendorText;
	}
	
	
	
}
