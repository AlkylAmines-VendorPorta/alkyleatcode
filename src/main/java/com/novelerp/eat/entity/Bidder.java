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

import com.novelerp.alkyl.dto.EnquiryDto;
import com.novelerp.alkyl.entity.Enquiry;
import com.novelerp.appbase.master.entity.PartnerOrg;
import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.appcontext.entity.User;
/**
 * 
 * @author ankita
 *
 */
@Entity
@Table(name="t_bidder")
public class Bidder extends ContextPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long bidderId;
	private TAHDRDetail tenderDetail;
	private TAHDR tahdr;
	private PaymentDetail tenderPurchase;
	private PaymentDetail emdPayment;
	private User user;
	private String bidderStatusCode;
	private Set<ItemBid> itemBidList;
	
	private String status;
	/*private CommercialBid commercialBid;*/
	private Set<ItemScrutiny> itemScrutinyList;
	private PartnerOrg factory;
	private double rating;
	private long rateCount;
	/*private PR pr;*/
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
	private Enquiry enquiry;
	private String isMailSent;
	private String otherTaxAmt;
	private String paymentTerms;
	private String negotiatorPaymentTerms;
	private String uploadExcelFile;
	private Date validityDateFrom;
	private Date validityDateTo;
	private String vendorOfferNo;
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
	
	@Id
	@SequenceGenerator(name="t_bidder_seq",sequenceName="t_bidder_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="t_bidder_seq")	
	@Column(name = "t_bidder_id", updatable=false)
	public Long getBidderId() {
		return bidderId;
	}
	public void setBidderId(Long bidderId) {
		this.bidderId = bidderId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_tahdr_detail_id")
	public TAHDRDetail getTenderDetail() {
		return tenderDetail;
	}
	public void setTenderDetail(TAHDRDetail tenderDetail) {
		this.tenderDetail = tenderDetail;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ad_user_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Column(name="bidder_status_code")
	public String getBidderStatusCode() {
		return bidderStatusCode;
	}
	public void setBidderStatusCode(String bidderStatusCode) {
		this.bidderStatusCode = bidderStatusCode;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_payment_detail_id")
	public PaymentDetail getTenderPurchase() {
		return tenderPurchase;
	}
	public void setTenderPurchase(PaymentDetail tenderPurchase) {
		this.tenderPurchase = tenderPurchase;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_emd_payment_id")
	public PaymentDetail getEmdPayment() {
		return emdPayment;
	}
	public void setEmdPayment(PaymentDetail emdPayment) {
		this.emdPayment = emdPayment;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_tahdr_id")
	public TAHDR getTahdr() {
		return tahdr;
	}
	public void setTahdr(TAHDR tahdr) {
		this.tahdr = tahdr;
	}
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="t_bidder_id")
	public Set<ItemBid> getItemBidList() {
		return itemBidList;
	}
	public void setItemBidList(Set<ItemBid> itemBidList) {
		this.itemBidList = itemBidList;
	}
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	/*@OneToOne(fetch=FetchType.LAZY,mappedBy="bidder")
	@JoinColumn(name="t_bidder_id")
	public CommercialBid getCommercialBid() {
		return commercialBid;
	}
	public void setCommercialBid(CommercialBid commercialBid) {
		this.commercialBid = commercialBid;
	}*/
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="t_bidder_id")
	public Set<ItemScrutiny> getItemScrutinyList() {
		return itemScrutinyList;
	}
	public void setItemScrutinyList(Set<ItemScrutiny> itemScrutinyList) {
		this.itemScrutinyList = itemScrutinyList;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_bp_org_id")
	public PartnerOrg getFactory() {
		return factory;
	}
	public void setFactory(PartnerOrg factory) {
		this.factory = factory;
	}
	
	@Column(name = "rating")
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	@Column(name = "rate_count")
	public long getRateCount() {
		return rateCount;
	}
	public void setRateCount(long rateCount) {
		this.rateCount = rateCount;
	}
	
	/*@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_pr_id")
	public PR getPr() {
		return pr;
	}
	public void setPr(PR pr) {
		this.pr = pr;
	}*/
	
	@Column(name = "basic_amt")
	public String getBasicAmt() {
		return basicAmt;
	}
	public void setBasicAmt(String basicAmt) {
		this.basicAmt = basicAmt;
	}
	
	@Column(name = "other_charge_type")
	public String getOtherChargeType() {
		return otherChargeType;
	}
	public void setOtherChargeType(String otherChargeType) {
		this.otherChargeType = otherChargeType;
	}
	
	@Column(name = "other_charge")
	public String getOtherCharge() {
		return otherCharge;
	}
	public void setOtherCharge(String otherCharge) {
		this.otherCharge = otherCharge;
	}
	
	@Column(name = "tax_amt")
	public String getTaxAmt() {
		return taxAmt;
	}
	public void setTaxAmt(String taxAmt) {
		this.taxAmt = taxAmt;
	}
	
	@Column(name = "gross_amt")
	public String getGrossAmt() {
		return grossAmt;
	}
	public void setGrossAmt(String grossAmt) {
		this.grossAmt = grossAmt;
	}
	
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "total_freight")
	public String getTotalFreight() {
		return totalFreight;
	}
	public void setTotalFreight(String totalFreight) {
		this.totalFreight = totalFreight;
	}
	@Column(name = "total_pk_fwd")
	public String getTotalPKFWD() {
		return totalPKFWD;
	}
	public void setTotalPKFWD(String totalPKFWD) {
		this.totalPKFWD = totalPKFWD;
	}
	@Column(name = "other_rates")
	public String getOtherRates() {
		return otherRates;
	}
	public void setOtherRates(String otherRates) {
		this.otherRates = otherRates;
	}
	@Column(name = "split_basic_value")
	public String getSplitBasicValue() {
		return splitBasicValue;
	}
	public void setSplitBasicValue(String splitBasicValue) {
		this.splitBasicValue = splitBasicValue;
	}
	@Column(name = "split_gross_value")
	public String getSplitGrossValue() {
		return splitGrossValue;
	}
	public void setSplitGrossValue(String splitGrossValue) {
		this.splitGrossValue = splitGrossValue;
	}
	@Column(name = "split_landed_cost")
	public String getSplitLandedCost() {
		return splitLandedCost;
	}
	public void setSplitLandedCost(String splitLandedCost) {
		this.splitLandedCost = splitLandedCost;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_enquiry_id")
	
	public Enquiry getEnquiry() {
		return enquiry;
	}
	public void setEnquiry(Enquiry enquiry) {
		this.enquiry = enquiry;
	}
	@Column(name = "is_mail_sent")
	public String getIsMailSent() {
		return isMailSent;
	}
	public void setIsMailSent(String isMailSent) {
		this.isMailSent = isMailSent;
	}
	@Column(name = "other_tax_amt")
	public String getOtherTaxAmt() {
		return otherTaxAmt;
	}
	public void setOtherTaxAmt(String otherTaxAmt) {
		this.otherTaxAmt = otherTaxAmt;
	}
	@Column(name = "payment_terms")
	public String getPaymentTerms() {
		return paymentTerms;
	}
	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}
	
	@Column(name = "negotiator_payment_terms")
	public String getnegotiatorPaymentTerms() {
		return negotiatorPaymentTerms;
	}
	public void setnegotiatorPaymentTerms(String negotiatorPaymentTerms) {
		this.negotiatorPaymentTerms = negotiatorPaymentTerms;
	}
	
	
	@Column(name = "excel_upload")
	public String getUploadExcelFile() {
		return uploadExcelFile;
	}
	public void setUploadExcelFile(String uploadExcelFile) {
		this.uploadExcelFile = uploadExcelFile;
	}
	
	@Column(name = "validity_date_from")
	public Date getValidityDateFrom() {
		return validityDateFrom;
	}
	public void setValidityDateFrom(Date validityDateFrom) {
		this.validityDateFrom = validityDateFrom;
	}

	@Column(name = "validity_date_to")
	public Date getValidityDateTo() {
		return validityDateTo;
	}
	
	public void setValidityDateTo(Date validityDateTo) {
		this.validityDateTo = validityDateTo;
	}
	
	@Column(name = "vendor_offer_no")
	public String getVendorOfferNo() {
		return vendorOfferNo;
	}
	public void setVendorOfferNo(String vendorOfferNo) {
		this.vendorOfferNo = vendorOfferNo;
	}
	
	@Column(name = "vendor_offer_date")
	public Date getVendorOfferDate() {
		return vendorOfferDate;
	}
	
	public void setVendorOfferDate(Date vendorOfferDate) {
		this.vendorOfferDate = vendorOfferDate;
	}
	
	
	
	@Column(name = "header_freight_type")
	public String getHeaderFreightType() {
		return headerFreightType;
	}
	public void setHeaderFreightType(String headerFreightType) {
		this.headerFreightType = headerFreightType;
	}
	
	@Column(name = "header_pkfwd_type")
	public String getHeaderPKFWDType() {
		return headerPKFWDType;
	}
	
	public void setHeaderPKFWDType(String headerPKFWDType) {
		this.headerPKFWDType = headerPKFWDType;
	}
	
	@Column(name = "header_other_type")
	public String getHeaderOtherType() {
		return headerOtherType;
	}

	public void setHeaderOtherType(String headerOtherType) {
		this.headerOtherType = headerOtherType;
	}

	
//	@Column(name = "rfq_no")
//	public String getRfqNo() {
//		return rfqNo;
//	}
//	public void setRfqNo(String rfqNo) {
//		this.rfqNo = rfqNo;
//	}
	
	
	
	@Column(name = "inco_terms")
	public String getIncoTerms() {
		return incoTerms;
	}
	public void setIncoTerms(String incoTerms) {
		this.incoTerms = incoTerms;
	}
	
	
	@Column(name = "total_freight_charge_amt")
	public String getTotalFreightChargeAmt() {
		return totalFreightChargeAmt;
	}
	public void setTotalFreightChargeAmt(String totalFreightChargeAmt) {
		this.totalFreightChargeAmt = totalFreightChargeAmt;
	}
	
	@Column(name = "total_pkfwd_charge_amt")
	public String getTotalPackingChargeAmt() {
		return totalPackingChargeAmt;
	}
	public void setTotalPackingChargeAmt(String totalPackingChargeAmt) {
		this.totalPackingChargeAmt = totalPackingChargeAmt;
	}
	
	
	@Column(name = "total_other_charge_amt")
	public String getTotalOtherChargeAmt() {
		return totalOtherChargeAmt;
	}

	public void setTotalOtherChargeAmt(String totalOtherChargeAmt) {
		this.totalOtherChargeAmt = totalOtherChargeAmt;
	}
	
	
	@Column(name = "inco_description")
	public String getVendorIncoDescription() {
		return vendorIncoDescription;
	}
	public void setVendorIncoDescription(String vendorIncoDescription) {
		this.vendorIncoDescription = vendorIncoDescription;
	}
	
	
	
}
