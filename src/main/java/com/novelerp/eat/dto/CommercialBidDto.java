/**
 * @author Ankush
 */
package com.novelerp.eat.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.dto.TaxDto;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.commons.util.CommonUtil;

public class CommercialBidDto extends CommonContextDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long commercialBidId;
	private BigDecimal exciseDuty;
	private BigDecimal exciseDutyRate;
	private TaxDto tax;
	private BigDecimal firstLot;
	private Long deliveringMonth;
	private BigDecimal ratePerMonth;
	/*private ItemBidDto itemBid;*/
	private Set<BidderSectionDocDto> bidderSecDoc;
	private List<BidderSectionDocDto> bidderSecDocList;
	private String status;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="bidderId",scope=Long.class)
	private BidderDto bidder;
	private AttachmentDto digiSignedDoc;
	
	private BigDecimal gst;
	private BigDecimal igst;
	private BigDecimal cgst;
	private BigDecimal sgst;
	
	private TAHDRDetailDto tahdrDetail;
	
	public Long getCommercialBidId() {
		return commercialBidId;
	}
	public void setCommercialBidId(Long commercialBidId) {
		this.commercialBidId = commercialBidId;
	}
	public BigDecimal getExciseDuty() {
		return exciseDuty;
	}
	public void setExciseDuty(BigDecimal exciseDuty) {
		this.exciseDuty = exciseDuty;
	}
	public BigDecimal getExciseDutyRate() {
		return exciseDutyRate;
	}
	public void setExciseDutyRate(BigDecimal exciseDutyRate) {
		this.exciseDutyRate = exciseDutyRate;
	}
	public TaxDto getTax() {
		return tax;
	}
	public void setTax(TaxDto tax) {
		this.tax = tax;
	}
	public BigDecimal getFirstLot() {
		return firstLot;
	}
	public void setFirstLot(BigDecimal firstLot) {
		this.firstLot = firstLot;
	}
	public Long getDeliveringMonth() {
		return deliveringMonth;
	}
	public void setDeliveringMonth(Long deliveringMonth) {
		this.deliveringMonth = deliveringMonth;
	}
	public BigDecimal getRatePerMonth() {
		return ratePerMonth;
	}
	public void setRatePerMonth(BigDecimal ratePerMonth) {
		this.ratePerMonth = ratePerMonth;
	}
	/*public ItemBidDto getItemBid() {
		return itemBid;
	}
	public void setItemBid(ItemBidDto itemBid) {
		this.itemBid = itemBid;
	}*/
	public Set<BidderSectionDocDto> getBidderSecDoc() {
		if(CommonUtil.isCollectionEmpty(bidderSecDoc) && !CommonUtil.isCollectionEmpty(bidderSecDocList)){
			bidderSecDoc=new HashSet<>();
			bidderSecDoc.addAll(getBidderSecDocList());
		}
		return bidderSecDoc;
	}
	public List<BidderSectionDocDto> getBidderSecDocList() {
		return bidderSecDocList;
	}
	public void setBidderSecDocList(List<BidderSectionDocDto> bidderSecDocList) {
		this.bidderSecDocList = bidderSecDocList;
	}
	public void setBidderSecDoc(Set<BidderSectionDocDto> bidderSecDoc) {
		this.bidderSecDoc = bidderSecDoc;
	}	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BidderDto getBidder() {
		return bidder;
	}
	public void setBidder(BidderDto bidder) {
		this.bidder = bidder;
	}
	public AttachmentDto getDigiSignedDoc() {
		return digiSignedDoc;
	}
	public void setDigiSignedDoc(AttachmentDto digiSignedDoc) {
		this.digiSignedDoc = digiSignedDoc;
	}
	public BigDecimal getGst() {
		return gst;
	}
	public void setGst(BigDecimal gst) {
		this.gst = gst;
	}
	public BigDecimal getIgst() {
		return igst;
	}
	public void setIgst(BigDecimal igst) {
		this.igst = igst;
	}
	public BigDecimal getCgst() {
		return cgst;
	}
	public void setCgst(BigDecimal cgst) {
		this.cgst = cgst;
	}
	public BigDecimal getSgst() {
		return sgst;
	}
	public void setSgst(BigDecimal sgst) {
		this.sgst = sgst;
	}
	public TAHDRDetailDto getTahdrDetail() {
		return tahdrDetail;
	}
	public void setTahdrDetail(TAHDRDetailDto tahdrDetail) {
		this.tahdrDetail = tahdrDetail;
	}
}
