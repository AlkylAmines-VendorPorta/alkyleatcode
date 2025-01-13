package com.novelerp.eat.entity;

import java.math.BigDecimal;
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

import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.appcontext.entity.Designation;

@Entity
@Table(name="t_tahdr_detail")
public class TAHDRDetail extends ContextPO{

	/**
	 * @author Ankush
	 */
	private static final long serialVersionUID = 1L;
	private Long tahdrDetailId;
	private TAHDR tahdr;
	private Long version;
	private String description;
	private BigDecimal estimatedCost;
	private BigDecimal tahdrFees;
	private Long tahdrValidity;
	private Long deliveryDuration;
	private String contactEmailId;
	private String contactPersonName;
	private String contactPersonNo;
	private Long commencementPeriod;
	private String pricingProcCode;
	
	private String isBiennialContractRate;
	private Long minQuantity;
	
	private Date purchaseFromDate;
	private Date purchaseToDate;
	private Date preBidDate;
	
	private Date c1OpenningDate;
	private Date c1FromDate;
	private Date c1ToDate;
	private String isAnnexureC1;
	private String isSetC1Later;
	
	private Date deviationOpenningDate;
	private Date deviationFromDate;
	private Date deviationToDate;
	private String isDeviation;
	private String isDeviationSetLater;
	
	private Date technicalBidFromDate;
	private Date technicalBidToDate;
	private Date techBidOpenningDate;
	
	private Date commercialBidOpenningDate;
	private Date commercialBidFromDate;
	private Date commercialBidToDate;
	
	private Date priceBidOpenningDate;
	private Date priceBidFromDate;
	private Date priceBidToDate;
	private String isPBDSetLater;
	
	private Date publishingDate;
	private String tahdrStatusCode;
	
	private Set<TAHDRMaterial> tahdrMaterial;
	private String preQualReq;
	private BigDecimal emdFee;
	
	private Set<SectionDocument> sectionDocument;
	private Set<StandardCustomDoc> standardCustomDoc;
	/*private Set<TAHDRMaterialGTP> tahdrMaterialGtpList;*/
	private String bidOpeningAddr;
	private String preBidAddr;
	
	private BigDecimal minBidDifference;
	
	private Date revisedBidOpenningDate;
	private Date revisedBidToDate;
	private Date winnerSelectionDate;
	private String isWinnerSelectionDateSetLater;
	private Attachment officeNote;
	private String commencementPeriodCode;
	private Designation designation;
	private Attachment tenderDoc;
	/*private TenderCommittee tenderCommittee;*/
	private String datesSubmitted;
	private Date auctionFromDate;
	private Date auctionToDate;
	private String isAuctionDateSetLater;
	private String otherCommencementPeriod;
	
	private String isTechnicalBid;
	private String isGTP;
	private String isTechnicalDocs;
	
	private String isCommercialDocs;
	private String isPriceDocs;
	
	private String isReqDocs;
	private String isTenderDocs;
	
	private String isPreliminaryScrutiny;
	private String isFinalScrutiny;
	private String autoExtentionTime;
	
	
	
	@Id
	@SequenceGenerator(name="t_tahdr_detail_seq",sequenceName="t_tahdr_detail_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="t_tahdr_detail_seq")	
	@Column(name = "t_tahdr_detail_id", updatable=false)
	public Long getTahdrDetailId() {
		return tahdrDetailId;
	}
	public void setTahdrDetailId(Long tahdrDetailId) {
		this.tahdrDetailId = tahdrDetailId;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_tahdr_id")
	public TAHDR getTahdr() {
		return tahdr;
	}
	public void setTahdr(TAHDR tahdr) {
		this.tahdr = tahdr;
	}
	
	@Column(name="version")
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	
	@Column(name="estimated_cost")
	public BigDecimal getEstimatedCost() {
		return estimatedCost;
	}
	public void setEstimatedCost(BigDecimal estimatedCost) {
		this.estimatedCost = estimatedCost;
	}
	
	@Column(name="delivery_duration")
	public Long getDeliveryDuration() {
		return deliveryDuration;
	}
	public void setDeliveryDuration(Long deliveryDuration) {
		this.deliveryDuration = deliveryDuration;
	}
	
	@Column(name="contact_email")
	public String getContactEmailId() {
		return contactEmailId;
	}
	public void setContactEmailId(String contactEmailId) {
		this.contactEmailId = contactEmailId;
	}
	
	@Column(name="is_deviation")
	public String getIsDeviation() {
		return isDeviation;
	}
	public void setIsDeviation(String isDeviation) {
		this.isDeviation = isDeviation;
	}
	
	@Column(name="commencement_period")
	public Long getCommencementPeriod() {
		return commencementPeriod;
	}
	public void setCommencementPeriod(Long commencementPeriod) {
		this.commencementPeriod = commencementPeriod;
	}
	
	@Column(name="pricing_proc_code")
	public String getPricingProcCode() {
		return pricingProcCode;
	}
	public void setPricingProcCode(String pricingProcCode) {
		this.pricingProcCode = pricingProcCode;
	}
	
	@Column(name="is_annexure_c1")
	public String getIsAnnexureC1() {
		return isAnnexureC1;
	}
	public void setIsAnnexureC1(String isAnnexureC1) {
		this.isAnnexureC1 = isAnnexureC1;
	}
	
	@Column(name="is_biennial_contract_rate")
	public String getIsBiennialContractRate() {
		return isBiennialContractRate;
	}
	public void setIsBiennialContractRate(String isBiennialContractRate) {
		this.isBiennialContractRate = isBiennialContractRate;
	}
	
	@Column(name="min_quantity")
	public Long getMinQuantity() {
		return minQuantity;
	}
	public void setMinQuantity(Long minQuantity) {
		this.minQuantity = minQuantity;
	}
	
	
	@Column(name="is_set_c1_later")	
	public String getIsSetC1Later() {
		return isSetC1Later;
	}
	public void setIsSetC1Later(String isSetC1Later) {
		this.isSetC1Later = isSetC1Later;
	}
	
	@Column(name="technical_bid_from_date")
	public Date getTechnicalBidFromDate() {
		return technicalBidFromDate;
	}
	public void setTechnicalBidFromDate(Date technicalBidFromDate) {
		this.technicalBidFromDate = technicalBidFromDate;
	}
	
	@Column(name="technical_bid_to_date")
	public Date getTechnicalBidToDate() {
		return technicalBidToDate;
	}
	public void setTechnicalBidToDate(Date technicalBidToDate) {
		this.technicalBidToDate = technicalBidToDate;
	}
	
	@Column(name="technical_bid_openning_date")
	public Date getTechBidOpenningDate() {
		return techBidOpenningDate;
	}
	public void setTechBidOpenningDate(Date techBidOpenningDate) {
		this.techBidOpenningDate = techBidOpenningDate;
	}
	
	@Column(name="commercial_bid_from_date")
	public Date getCommercialBidFromDate() {
		return commercialBidFromDate;
	}
	public void setCommercialBidFromDate(Date commercialBidFromDate) {
		this.commercialBidFromDate = commercialBidFromDate;
	}
	
	@Column(name="commercial_bid_to_date")
	public Date getCommercialBidToDate() {
		return commercialBidToDate;
	}
	public void setCommercialBidToDate(Date commercialBidToDate) {
		this.commercialBidToDate = commercialBidToDate;
	}
	
	@Column(name="price_bid_from_date")
	public Date getPriceBidFromDate() {
		return priceBidFromDate;
	}
	public void setPriceBidFromDate(Date priceBidFromDate) {
		this.priceBidFromDate = priceBidFromDate;
	}
	
	@Column(name="price_bid_to_date")
	public Date getPriceBidToDate() {
		return priceBidToDate;
	}
	public void setPriceBidToDate(Date priceBidToDate) {
		this.priceBidToDate = priceBidToDate;
	}
	
	@Column(name="is_PBD_set_Later")
	public String getIsPBDSetLater() {
		return isPBDSetLater;
	}
	public void setIsPBDSetLater(String isPBDSetLater) {
		this.isPBDSetLater = isPBDSetLater;
	}
	
	@Column(name="tahdr_status_code")
	public String getTahdrStatusCode() {
		return tahdrStatusCode;
	}
	public void setTahdrStatusCode(String tahdrStatusCode) {
		this.tahdrStatusCode = tahdrStatusCode;
	}
	
	@Column(name="tahdr_fees")
	public BigDecimal getTahdrFees() {
		return tahdrFees;
	}
	public void setTahdrFees(BigDecimal tahdrFees) {
		this.tahdrFees = tahdrFees;
	}
	
	@Column(name="tahdr_validity")
	public Long getTahdrValidity() {
		return tahdrValidity;
	}
	public void setTahdrValidity(Long tahdrValidity) {
		this.tahdrValidity = tahdrValidity;
	}
	
	@Column(name="commercial_bid_oppening_date")
	public Date getCommercialBidOpenningDate() {
		return commercialBidOpenningDate;
	}
	public void setCommercialBidOpenningDate(Date commercialBidOpenningDate) {
		this.commercialBidOpenningDate = commercialBidOpenningDate;
	}
	
	
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="tahdrDetail")
	public Set<TAHDRMaterial> getTahdrMaterial() {
		return tahdrMaterial;
	}
	public void setTahdrMaterial(Set<TAHDRMaterial> tahdrMaterial) {
		this.tahdrMaterial = tahdrMaterial;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="c1_openning_date")
	public Date getC1OpenningDate() {
		return c1OpenningDate;
	}
	public void setC1OpenningDate(Date c1OpenningDate) {
		this.c1OpenningDate = c1OpenningDate;
	}
	
	@Column(name="c1_from_date")
	public Date getC1FromDate() {
		return c1FromDate;
	}
	public void setC1FromDate(Date c1FromDate) {
		this.c1FromDate = c1FromDate;
	}
	
	@Column(name="c1_To_date")
	public Date getC1ToDate() {
		return c1ToDate;
	}
	public void setC1ToDate(Date c1ToDate) {
		this.c1ToDate = c1ToDate;
	}
	
	@Column(name="deviation_openning_date")
	public Date getDeviationOpenningDate() {
		return deviationOpenningDate;
	}
	public void setDeviationOpenningDate(Date deviationOpenningDate) {
		this.deviationOpenningDate = deviationOpenningDate;
	}
	
	@Column(name="deviation_from_date")
	public Date getDeviationFromDate() {
		return deviationFromDate;
	}
	public void setDeviationFromDate(Date deviationFromDate) {
		this.deviationFromDate = deviationFromDate;
	}
	
	@Column(name="deviation_to_date")
	public Date getDeviationToDate() {
		return deviationToDate;
	}
	public void setDeviationToDate(Date deviationToDate) {
		this.deviationToDate = deviationToDate;
	}
	
	@Column(name="is_deviation_set_later")
	public String getIsDeviationSetLater() {
		return isDeviationSetLater;
	}
	public void setIsDeviationSetLater(String isDeviationSetLater) {
		this.isDeviationSetLater = isDeviationSetLater;
	}
	
	@Column(name="price_bid_openning_date")
	public Date getPriceBidOpenningDate() {
		return priceBidOpenningDate;
	}
	public void setPriceBidOpenningDate(Date priceBidOpenningDate) {
		this.priceBidOpenningDate = priceBidOpenningDate;
	}
	
	@Column(name="purchase_from_date")
	public Date getPurchaseFromDate() {
		return purchaseFromDate;
	}
	public void setPurchaseFromDate(Date purchaseFromDate) {
		this.purchaseFromDate = purchaseFromDate;
	}
	
	@Column(name="purchase_to_date")
	public Date getPurchaseToDate() {
		return purchaseToDate;
	}
	public void setPurchaseToDate(Date purchaseToDate) {
		this.purchaseToDate = purchaseToDate;
	}
	
	@Column(name="pre_bid_date")
	public Date getPreBidDate() {
		return preBidDate;
	}
	public void setPreBidDate(Date preBidDate) {
		this.preBidDate = preBidDate;
	}
	

	@Column(name="pre_qual_req")
	public String getPreQualReq() {
		return preQualReq;
	}
	public void setPreQualReq(String preQualReq) {
		this.preQualReq = preQualReq;
	}
	
	@Column(name="emd_fee")
	public BigDecimal getEmdFee() {
		return emdFee;
	}
	public void setEmdFee(BigDecimal emdFee) {
		this.emdFee = emdFee;
	}
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="tahdrDetail")
	public Set<SectionDocument> getSectionDocument() {
		return sectionDocument;
	}
	public void setSectionDocument(Set<SectionDocument> sectionDocument) {
		this.sectionDocument = sectionDocument;
	}
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="tahdrDetail")
	public Set<StandardCustomDoc> getStandardCustomDoc() {
		return standardCustomDoc;
	}
	public void setStandardCustomDoc(Set<StandardCustomDoc> standardCustomDoc) {
		this.standardCustomDoc = standardCustomDoc;
	}
	
	/*@OneToOne(fetch=FetchType.LAZY, mappedBy="tenderVersion")
	public TenderCommittee getTenderCommittee() {
		return tenderCommittee;
	}
	public void setTenderCommittee(TenderCommittee tenderCommittee) {
		this.tenderCommittee = tenderCommittee;
	}*/
	
	@Column(name = "publishing_date")
	public Date getPublishingDate() {
		return publishingDate;
	}
	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}
	
	@Column(name="bid_opening_addr")
	public String getBidOpeningAddr() {
		return bidOpeningAddr;
	}
	public void setBidOpeningAddr(String bidOpeningAddr) {
		this.bidOpeningAddr = bidOpeningAddr;
	}
	
	@Column(name="pre_bid_addr")
	public String getPreBidAddr() {
		return preBidAddr;
	}
	public void setPreBidAddr(String preBidAddr) {
		this.preBidAddr = preBidAddr;
	}
	
	@Column(name="min_bid_difference")
	public BigDecimal getMinBidDifference() {
		return minBidDifference;
	}
	public void setMinBidDifference(BigDecimal minBidDifference) {
		this.minBidDifference = minBidDifference;
	}
	@Column(name="revised_bid_openning")
	public Date getRevisedBidOpenningDate() {
		return revisedBidOpenningDate;
	}
	public void setRevisedBidOpenningDate(Date revisedBidOpenningDate) {
		this.revisedBidOpenningDate = revisedBidOpenningDate;
	}
	@Column(name="revised_bid_to_date")
	public Date getRevisedBidToDate() {
		return revisedBidToDate;
	}
	public void setRevisedBidToDate(Date revisedBidToDate) {
		this.revisedBidToDate = revisedBidToDate;
	}
	@Column(name="winner_selection")
	public Date getWinnerSelectionDate() {
		return winnerSelectionDate;
	}
	public void setWinnerSelectionDate(Date winnerSelectionDate) {
		this.winnerSelectionDate = winnerSelectionDate;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="office_note",referencedColumnName="m_attachment_id")
	public Attachment getOfficeNote() {
		return officeNote;
	}
	public void setOfficeNote(Attachment officeNote) {
		this.officeNote = officeNote;
	}
	@Column(name="commencement_period_code")
	public String getCommencementPeriodCode() {
		return commencementPeriodCode;
	}
	public void setCommencementPeriodCode(String commencementPeriodCode) {
		this.commencementPeriodCode = commencementPeriodCode;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tender_doc_id", referencedColumnName="m_attachment_id")
	public Attachment getTenderDoc(){
		return tenderDoc;
	}
	public void setTenderDoc(Attachment tenderDoc) {
		this.tenderDoc = tenderDoc;
	}
	@Column(name="contact_person_name")
	public String getContactPersonName() {
		return contactPersonName;
	}
	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}
	@Column(name="contact_person_number")
	public String getContactPersonNo() {
		return contactPersonNo;
	}
	public void setContactPersonNo(String contactPersonNo) {
		this.contactPersonNo = contactPersonNo;
	}
	@OneToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="m_designation_id")
	public Designation getDesignation() {
		return designation;
	}
	public void setDesignation(Designation designation) {
		this.designation = designation;
	}
	@Column(name="dates_submitted")
	public String getDatesSubmitted() {
		return datesSubmitted;
	}
	public void setDatesSubmitted(String datesSubmitted) {
		this.datesSubmitted = datesSubmitted;
	}
	@Column(name = "auction_from_date")
	public Date getAuctionFromDate() {
		return auctionFromDate;
	}
	public void setAuctionFromDate(Date auctionFromDate) {
		this.auctionFromDate = auctionFromDate;
	}
	@Column(name = "auction_to_date")
	public Date getAuctionToDate() {
		return auctionToDate;
	}
	public void setAuctionToDate(Date auctionToDate) {
		this.auctionToDate = auctionToDate;
	}
	@Column(name="is_winner_set_later")
	public String getIsWinnerSelectionDateSetLater() {
		return isWinnerSelectionDateSetLater;
	}
	public void setIsWinnerSelectionDateSetLater(String isWinnerSelectionDateSetLater) {
		this.isWinnerSelectionDateSetLater = isWinnerSelectionDateSetLater;
	}
	@Column(name="is_auction_set_later")
	public String getIsAuctionDateSetLater() {
		return isAuctionDateSetLater;
	}
	public void setIsAuctionDateSetLater(String isAuctionDateSetLater) {
		this.isAuctionDateSetLater = isAuctionDateSetLater;
	}
	@Column(name="other_commencement_period")
	public String getOtherCommencementPeriod() {
		return otherCommencementPeriod;
	}
	public void setOtherCommencementPeriod(String otherCommencementPeriod) {
		this.otherCommencementPeriod = otherCommencementPeriod;
	}
	@Column(name="is_technical_bid")
	public String getIsTechnicalBid() {
		return isTechnicalBid;
	}
	public void setIsTechnicalBid(String isTechnicalBid) {
		this.isTechnicalBid = isTechnicalBid;
	}
	@Column(name="is_tender_docs")
	public String getIsTenderDocs() {
		return isTenderDocs;
	}
	public void setIsTenderDocs(String isTenderDocs) {
		this.isTenderDocs = isTenderDocs;
	}
	@Column(name="is_required_docs")
	public String getIsReqDocs() {
		return isReqDocs;
	}
	public void setIsReqDocs(String isReqDocs) {
		this.isReqDocs = isReqDocs;
	}
	@Column(name="is_gtp")
	public String getIsGTP() {
		return isGTP;
	}
	public void setIsGTP(String isGTP) {
		this.isGTP = isGTP;
	}
	@Column(name="is_technical_docs")
	public String getIsTechnicalDocs() {
		return isTechnicalDocs;
	}
	public void setIsTechnicalDocs(String isTechnicalDocs) {
		this.isTechnicalDocs = isTechnicalDocs;
	}
	@Column(name="is_commercial_docs")
	public String getIsCommercialDocs() {
		return isCommercialDocs;
	}
	public void setIsCommercialDocs(String isCommercialDocs) {
		this.isCommercialDocs = isCommercialDocs;
	}
	@Column(name="is_price_docs")
	public String getIsPriceDocs() {
		return isPriceDocs;
	}
	public void setIsPriceDocs(String isPriceDocs) {
		this.isPriceDocs = isPriceDocs;
	}
	@Column(name="is_preliminary_scrutiny")
	public String getIsPreliminaryScrutiny() {
		return isPreliminaryScrutiny;
	}
	public void setIsPreliminaryScrutiny(String isPreliminaryScrutiny) {
		this.isPreliminaryScrutiny = isPreliminaryScrutiny;
	}
	@Column(name="is_final_scrutiny")
	public String getIsFinalScrutiny() {
		return isFinalScrutiny;
	}
	public void setIsFinalScrutiny(String isFinalScrutiny) {
		this.isFinalScrutiny = isFinalScrutiny;
	}
	@Column(name="auto_extention_time")
	public String getAutoExtentionTime() {
		return autoExtentionTime;
	}
	public void setAutoExtentionTime(String autoExtentionTime) {
		this.autoExtentionTime = autoExtentionTime;
	}
	
	
	
	/*@OneToMany(fetch=FetchType.LAZY,mappedBy="tahdrDetail")
	public Set<TAHDRMaterialGTP> getTahdrMaterialGtpList() {
		return tahdrMaterialGtpList;
	}
	public void setTahdrMaterialGtpList(Set<TAHDRMaterialGTP> tahdrMaterialGtpList) {
		this.tahdrMaterialGtpList = tahdrMaterialGtpList;
	}*/
	
	
	
}
