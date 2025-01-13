package com.novelerp.eat.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.alkyl.dto.EnquiryDto;
import com.novelerp.alkyl.dto.PRDto;
import com.novelerp.appbase.master.dto.PartnerOrgDto;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.util.DateUtil;

/**
 * 
 * @author ankita
 *
 */

public class BidderDto extends CommonContextDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long bidderId;
	private TAHDRDetailDto tenderDetail;
	private TAHDRDto tahdr;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="paymentDetailId",scope=Long.class)
	private PaymentDetailDto tenderPurchase;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="paymentDetailId",scope=Long.class)
	private PaymentDetailDto emdPayment;
	private UserDto user;
	private String bidderStatusCode;
	private Set<ItemBidDto> itemBidList;
	private String status;
	/*private CommercialBidDto commercialBid;*/
	private Set<ItemScrutinyDto> itemScrutinyList;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="partnerOrgId",scope=Long.class)
	private PartnerOrgDto factory;
	private double rating;
	private long rateCount;
	
	private PRDto pr;
	private List<ItemBidDto> itemBids;
	private String basicAmt;
	private String otherChargeType;
	private String otherCharge; 
	private String taxAmt;
	private String grossAmt;
	
	private String remark;
	private String totalFreight;
	private String totalPKFWD;
	private String otherRates;
	private String splitBasicValue;
	private String splitGrossValue;
	private String splitLandedCost;
	private EnquiryDto enquiry;
	private String isMailSent;
	private String otherTaxAmt;
	private String paymentTerms;
	private String negotiatorPaymentTerms;
	private String uploadExcelFile;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date validityDateFrom;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date validityDateTo;
	private String vendorOfferNo;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date vendorOfferDate;
	private String headerFreightType;
	private String headerPKFWDType;
	private String headerOtherType;
	private String incoTerms;
	private String totalFreightChargeAmt;
	private String totalPackingChargeAmt;
	private String totalOtherChargeAmt;
	private String vendorIncoDescription;
	
//	private String rfqNo;
	
	public Long getBidderId() {
		return bidderId;
	}
	public void setBidderId(Long bidderId) {
		this.bidderId = bidderId;
	}
	public TAHDRDetailDto getTenderDetail() {
		return tenderDetail;
	}
	public void setTenderDetail(TAHDRDetailDto tenderDetail) {
		this.tenderDetail = tenderDetail;
	}
	public TAHDRDto getTahdr() {
		return tahdr;
	}
	public void setTahdr(TAHDRDto tahdr) {
		this.tahdr = tahdr;
	}
	public PaymentDetailDto getTenderPurchase() {
		return tenderPurchase;
	}
	public void setTenderPurchase(PaymentDetailDto tenderPurchase) {
		this.tenderPurchase = tenderPurchase;
	}
	public PaymentDetailDto getEmdPayment() {
		return emdPayment;
	}
	public void setEmdPayment(PaymentDetailDto emdPayment) {
		this.emdPayment = emdPayment;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public String getBidderStatusCode() {
		return bidderStatusCode;
	}
	public void setBidderStatusCode(String bidderStatusCode) {
		this.bidderStatusCode = bidderStatusCode;
	}
	public Set<ItemBidDto> getItemBidList() {
		if(CommonUtil.isCollectionEmpty(itemBidList) && ! CommonUtil.isCollectionEmpty(itemBids)){
			itemBidList=new HashSet<>();
			itemBidList.addAll(itemBids);
		}
		return itemBidList;
	}
	public void setItemBidList(Set<ItemBidDto> itemBidList) {
		this.itemBidList = itemBidList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/*public CommercialBidDto getCommercialBid() {
		return commercialBid;
	}
	public void setCommercialBid(CommercialBidDto commercialBid) {
		this.commercialBid = commercialBid;
	}*/
	public Set<ItemScrutinyDto> getItemScrutinyList() {
		return itemScrutinyList;
	}
	public void setItemScrutinyList(Set<ItemScrutinyDto> itemScrutinyList) {
		this.itemScrutinyList = itemScrutinyList;
	}
	public PartnerOrgDto getFactory() {
		return factory;
	}
	public void setFactory(PartnerOrgDto factory) {
		this.factory = factory;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public long getRateCount() {
		return rateCount;
	}
	public void setRateCount(long rateCount) {
		this.rateCount = rateCount;
	}
	public PRDto getPr() {
		return pr;
	}
	public void setPr(PRDto pr) {
		this.pr = pr;
	}
	public List<ItemBidDto> getItemBids() {
		if(CommonUtil.isCollectionEmpty(itemBids) && !CommonUtil.isCollectionEmpty(itemBidList)){
			itemBids = new ArrayList<>();
			itemBids.addAll(itemBidList);
		}
		return itemBids;
	}
	public void setItemBids(List<ItemBidDto> itemBids) {
		this.itemBids = itemBids;
	}
	public String getBasicAmt() {
		return basicAmt;
	}
	public void setBasicAmt(String basicAmt) {
		this.basicAmt = basicAmt;
	}
	public String getOtherChargeType() {
		return otherChargeType;
	}
	public void setOtherChargeType(String otherChargeType) {
		this.otherChargeType = otherChargeType;
	}
	public String getOtherCharge() {
		return otherCharge;
	}
	public void setOtherCharge(String otherCharge) {
		this.otherCharge = otherCharge;
	}
	public String getTaxAmt() {
		return taxAmt;
	}
	public void setTaxAmt(String taxAmt) {
		this.taxAmt = taxAmt;
	}
	public String getGrossAmt() {
		return grossAmt;
	}
	public void setGrossAmt(String grossAmt) {
		this.grossAmt = grossAmt;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTotalFreight() {
		return totalFreight;
	}
	public void setTotalFreight(String totalFreight) {
		this.totalFreight = totalFreight;
	}
	public String getTotalPKFWD() {
		return totalPKFWD;
	}
	public void setTotalPKFWD(String totalPKFWD) {
		this.totalPKFWD = totalPKFWD;
	}
	public String getOtherRates() {
		return otherRates;
	}
	public void setOtherRates(String otherRates) {
		this.otherRates = otherRates;
	}
	public String getSplitBasicValue() {
		return splitBasicValue;
	}
	public void setSplitBasicValue(String splitBasicValue) {
		this.splitBasicValue = splitBasicValue;
	}
	public String getSplitGrossValue() {
		return splitGrossValue;
	}
	public void setSplitGrossValue(String splitGrossValue) {
		this.splitGrossValue = splitGrossValue;
	}
	public String getSplitLandedCost() {
		return splitLandedCost;
	}
	public void setSplitLandedCost(String splitLandedCost) {
		this.splitLandedCost = splitLandedCost;
	}
	public EnquiryDto getEnquiry() {
		return enquiry;
	}
	public void setEnquiry(EnquiryDto enquiry) {
		this.enquiry = enquiry;
	}
	public String getIsMailSent() {
		return isMailSent;
	}
	public void setIsMailSent(String isMailSent) {
		this.isMailSent = isMailSent;
	}
	public String getOtherTaxAmt() {
		return otherTaxAmt;
	}
	public void setOtherTaxAmt(String otherTaxAmt) {
		this.otherTaxAmt = otherTaxAmt;
	}
	public String getPaymentTerms() {
		return paymentTerms;
	}
	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}
	public String getNegotiatorPaymentTerms() {
		return negotiatorPaymentTerms;
	}
	public void setNegotiatorPaymentTerms(String negotiatorPaymentTerms) {
		this.negotiatorPaymentTerms = negotiatorPaymentTerms;
	}
	public String getUploadExcelFile() {
		return uploadExcelFile;
	}
	public void setUploadExcelFile(String uploadExcelFile) {
		this.uploadExcelFile = uploadExcelFile;
	}
	public Date getValidityDateFrom() {
		return validityDateFrom;
	}
	public Date getValidityDateTo() {
		return validityDateTo;
	}
	public void setValidityDateFrom(Date validityDateFrom) {
		this.validityDateFrom = validityDateFrom;
	}
	public void setValidityDateTo(Date validityDateTo) {
		this.validityDateTo = validityDateTo;
	}
//	public String getRfqNo() {
//		return rfqNo;
//	}
//	public void setRfqNo(String rfqNo) {
//		this.rfqNo = rfqNo;
//	}
	public String getVendorOfferNo() {
		return vendorOfferNo;
	}
	public Date getVendorOfferDate() {
		return vendorOfferDate;
	}
	public void setVendorOfferNo(String vendorOfferNo) {
		this.vendorOfferNo = vendorOfferNo;
	}
	public void setVendorOfferDate(Date vendorOfferDate) {
		this.vendorOfferDate = vendorOfferDate;
	}
	public String getHeaderFreightType() {
		return headerFreightType;
	}
	public String getHeaderPKFWDType() {
		return headerPKFWDType;
	}
	public String getHeaderOtherType() {
		return headerOtherType;
	}
	public void setHeaderFreightType(String headerFreightType) {
		this.headerFreightType = headerFreightType;
	}
	public void setHeaderPKFWDType(String headerPKFWDType) {
		this.headerPKFWDType = headerPKFWDType;
	}
	public void setHeaderOtherType(String headerOtherType) {
		this.headerOtherType = headerOtherType;
	}
	public String getIncoTerms() {
		return incoTerms;
	}
	public void setIncoTerms(String incoTerms) {
		this.incoTerms = incoTerms;
	}
	public String getTotalFreightChargeAmt() {
		return totalFreightChargeAmt;
	}
	public void setTotalFreightChargeAmt(String totalFreightChargeAmt) {
		this.totalFreightChargeAmt = totalFreightChargeAmt;
	}
	public String getTotalPackingChargeAmt() {
		return totalPackingChargeAmt;
	}
	public String getTotalOtherChargeAmt() {
		return totalOtherChargeAmt;
	}
	public void setTotalPackingChargeAmt(String totalPackingChargeAmt) {
		this.totalPackingChargeAmt = totalPackingChargeAmt;
	}
	public void setTotalOtherChargeAmt(String totalOtherChargeAmt) {
		this.totalOtherChargeAmt = totalOtherChargeAmt;
	}
	public String getVendorIncoDescription() {
		return vendorIncoDescription;
	}
	public void setVendorIncoDescription(String vendorIncoDescription) {
		this.vendorIncoDescription = vendorIncoDescription;
	}


	
	
	
	
	
}
