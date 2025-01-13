package com.novelerp.eat.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.DesignationDto;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.util.DateUtil;

public class TAHDRDetailDto extends CommonContextDto implements Cloneable{

	/**
	 * @author Ankush
	 */
	private static final long serialVersionUID = 1L;
	
	private Long commencementPeriod;
	private String commencementPeriodCode;
	private Long tahdrDetailId;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="tahdrId",scope=Long.class)
	private TAHDRDto tahdr;
	private Long version;
	private BigDecimal estimatedCost;
	private BigDecimal tahdrFees;	
	private String description;
	
	private Long tahdrValidity;
	
	private Long deliveryDuration;
	private String contactEmailId;
	private String contactPersonName;
	private String contactPersonNo;
	
	private String pricingProcCode;
	
	private String isBiennialContractRate;
	private Long minQuantity;
	private Set<TAHDRMaterialDto> tahdrMaterial;
	
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date deviationFromDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date deviationToDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date deviationOpenningDate;
	private String isDeviationSetLater;
	private String isDeviation;
	
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date c1FromDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date c1ToDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date c1OpenningDate;
	private String isSetC1Later;
	private String isAnnexureC1;
	
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date technicalBidFromDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date technicalBidToDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date techBidOpenningDate;
	
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date commercialBidFromDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date commercialBidToDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date commercialBidOpenningDate;
	
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date priceBidFromDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date priceBidToDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date priceBidOpenningDate;
	private String isPBDSetLater;
	
	private String tahdrStatusCode;
	
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date purchaseFromDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date purchaseToDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date preBidDate;
	
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date publishingDate;
	
	private String preQualReq;
	private BigDecimal emdFee;
	private DesignationDto designation;
	private Set<SectionDocumentDto> sectionDocument;
	private Set<StandardCustomDocDto> standardCustomDoc;
	private List<StandardCustomDocDto> standardCustomDocList;
	
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="tenderCommitteeId",scope=Long.class)
	private TenderCommitteeDto tenderCommittee;
	
	private String bidOpeningAddr;
	private String preBidAddr;
	private BigDecimal minBidDifference;
	private String tahdrScheduling;
	private AttachmentDto officeNote;
	
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date revisedBidOpenningDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date revisedBidToDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date winnerSelectionDate;
	private String isWinnerSelectionDateSetLater;
	
	private String isNoDeviation;
	
	private Long oldTahdrDetailId;
	private String editedDates;
	private String datesSubmitted;
	private AttachmentDto tenderDoc;
	
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date auctionFromDate;
	
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date auctionToDate;
	private String isAuctionDateSetLater;
	private String isCreateVersion;
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
	
	public Long getTahdrDetailId() {
		return tahdrDetailId;
	}
	public void setTahdrDetailId(Long tahdrDetailId) {
		this.tahdrDetailId = tahdrDetailId;
	}
	public TAHDRDto getTahdr() {
		return tahdr;
	}
	public void setTahdr(TAHDRDto tahdr) {
		this.tahdr = tahdr;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public BigDecimal getEstimatedCost() {
		return estimatedCost;
	}
	public void setEstimatedCost(BigDecimal estimatedCost) {
		this.estimatedCost = estimatedCost;
	}
	public BigDecimal getTahdrFees() {
		return tahdrFees;
	}
	public void setTahdrFees(BigDecimal tahdrFees) {
		this.tahdrFees = tahdrFees;
	}
	public Long getTahdrValidity() {
		return tahdrValidity;
	}
	public void setTahdrValidity(Long tahdrValidity) {
		this.tahdrValidity = tahdrValidity;
	}
	public Long getDeliveryDuration() {
		return deliveryDuration;
	}
	public void setDeliveryDuration(Long deliveryDuration) {
		this.deliveryDuration = deliveryDuration;
	}
	public String getContactEmailId() {
		return contactEmailId==null?null:contactEmailId.toLowerCase();
	}
	public void setContactEmailId(String contactEmailId) {
		this.contactEmailId = contactEmailId;
	}
	public String getIsDeviation() {
		if(CommonUtil.isStringEmpty(isDeviation)){
			return "N";
		}
		return isDeviation;
	}
	public void setIsDeviation(String isDeviation) {
		this.isDeviation = isDeviation;
	}
	public String getPricingProcCode() {
		return pricingProcCode;
	}
	public void setPricingProcCode(String pricingProcCode) {
		this.pricingProcCode = pricingProcCode;
	}
	public String getIsAnnexureC1() {
		if(CommonUtil.isStringEmpty(isAnnexureC1)){
			return "N";
		}
		return isAnnexureC1;
	}
	public void setIsAnnexureC1(String isAnnexureC1) {
		this.isAnnexureC1 = isAnnexureC1;
	}
	public String getIsBiennialContractRate() {
		if(CommonUtil.isStringEmpty(isBiennialContractRate)){
			return "N";
		}
		return isBiennialContractRate;
	}
	public void setIsBiennialContractRate(String isBiennialContractRate) {
		this.isBiennialContractRate = isBiennialContractRate;
	}
	public Long getMinQuantity() {
		return minQuantity;
	}
	public void setMinQuantity(Long minQuantity) {
		this.minQuantity = minQuantity;
	}
	public Date getTechnicalBidFromDate() {
		return technicalBidFromDate;
	}
	public void setTechnicalBidFromDate(Date technicalBidFromDate) {
		this.technicalBidFromDate = technicalBidFromDate;
	}
	public Date getTechnicalBidToDate() {
		return technicalBidToDate;
	}
	public void setTechnicalBidToDate(Date technicalBidToDate) {
		this.technicalBidToDate = technicalBidToDate;
	}
	public Date getTechBidOpenningDate() {
		return techBidOpenningDate;
	}
	public void setTechBidOpenningDate(Date techBidOpenningDate) {
		this.techBidOpenningDate = techBidOpenningDate;
	}
	public Date getCommercialBidFromDate() {
		return commercialBidFromDate;
	}
	public void setCommercialBidFromDate(Date commercialBidFromDate) {
		this.commercialBidFromDate = commercialBidFromDate;
	}
	public Date getCommercialBidToDate() {
		return commercialBidToDate;
	}
	public void setCommercialBidToDate(Date commercialBidToDate) {
		this.commercialBidToDate = commercialBidToDate;
	}
	public Date getPriceBidFromDate() {
		return priceBidFromDate;
	}
	public void setPriceBidFromDate(Date priceBidFromDate) {
		this.priceBidFromDate = priceBidFromDate;
	}
	public Date getPriceBidToDate() {
		return priceBidToDate;
	}
	public void setPriceBidToDate(Date priceBidToDate) {
		this.priceBidToDate = priceBidToDate;
	}
	public String getIsPBDSetLater() {
		if(CommonUtil.isStringEmpty(isPBDSetLater)){
			return "N";
		}
		
		return isPBDSetLater;
	}
	public void setIsPBDSetLater(String isPBDSetLater) {
		this.isPBDSetLater = isPBDSetLater;
	}
	public String getTahdrStatusCode() {
		return tahdrStatusCode;
	}
	public void setTahdrStatusCode(String tahdrStatusCode) {
		this.tahdrStatusCode = tahdrStatusCode;
	}
	public String getIsSetC1Later() {
		if(CommonUtil.isStringEmpty(isSetC1Later)){
			return "N";
		}
		return isSetC1Later;
	}
	public void setIsSetC1Later(String isSetC1Later) {
		this.isSetC1Later = isSetC1Later;
	}
	public Date getCommercialBidOpenningDate() {
		return commercialBidOpenningDate;
	}
	public void setCommercialBidOpenningDate(Date commercialBidOpenningDate) {
		this.commercialBidOpenningDate = commercialBidOpenningDate;
	}
	public Long getCommencementPeriod() {
		return commencementPeriod;
	}
	public void setCommencementPeriod(Long commencementPeriod) {
		this.commencementPeriod = commencementPeriod;
	}
	
	public Set<TAHDRMaterialDto> getTahdrMaterial() {
		return tahdrMaterial;
	}
	public void setTahdrMaterial(Set<TAHDRMaterialDto> tahdrMaterial) {
		this.tahdrMaterial = tahdrMaterial;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDeviationFromDate() {
		return deviationFromDate;
	}
	public void setDeviationFromDate(Date deviationFromDate) {
		this.deviationFromDate = deviationFromDate;
	}
	public Date getDeviationToDate() {
		return deviationToDate;
	}
	public void setDeviationToDate(Date deviationToDate) {
		this.deviationToDate = deviationToDate;
	}
	public Date getDeviationOpenningDate() {
		return deviationOpenningDate;
	}
	public void setDeviationOpenningDate(Date deviationOpenningDate) {
		this.deviationOpenningDate = deviationOpenningDate;
	}
	public String getIsDeviationSetLater() {
		if(CommonUtil.isStringEmpty(isDeviationSetLater)){
			return "N";
		}
		return isDeviationSetLater;
	}
	public void setIsDeviationSetLater(String isDeviationSetLater) {
		this.isDeviationSetLater = isDeviationSetLater;
	}
	public Date getC1FromDate() {
		return c1FromDate;
	}
	public void setC1FromDate(Date c1FromDate) {
		this.c1FromDate = c1FromDate;
	}
	public Date getC1ToDate() {
		return c1ToDate;
	}
	public void setC1ToDate(Date c1ToDate) {
		this.c1ToDate = c1ToDate;
	}
	public Date getC1OpenningDate() {
		return c1OpenningDate;
	}
	public void setC1OpenningDate(Date c1OpenningDate) {
		this.c1OpenningDate = c1OpenningDate;
	}
	public Date getPriceBidOpenningDate() {
		return priceBidOpenningDate;
	}
	public void setPriceBidOpenningDate(Date priceBidOpenningDate) {
		this.priceBidOpenningDate = priceBidOpenningDate;
	}
	public Date getPurchaseFromDate() {
		return purchaseFromDate;
	}
	public void setPurchaseFromDate(Date purchaseFromDate) {
		this.purchaseFromDate = purchaseFromDate;
	}
	public Date getPurchaseToDate() {
		return purchaseToDate;
	}
	public void setPurchaseToDate(Date purchaseToDate) {
		this.purchaseToDate = purchaseToDate;
	}
	public Date getPreBidDate() {
		return preBidDate;
	}
	public void setPreBidDate(Date preBidDate) {
		this.preBidDate = preBidDate;
	}
	public String getPreQualReq() {
		return preQualReq;
	}
	public void setPreQualReq(String preQualReq) {
		this.preQualReq = preQualReq;
	}
	public BigDecimal getEmdFee() {
		return emdFee;
	}
	public void setEmdFee(BigDecimal emdFee) {
		this.emdFee = emdFee;
	}
	public Set<SectionDocumentDto> getSectionDocument() {
		return sectionDocument;
	}
	public void setSectionDocument(Set<SectionDocumentDto> sectionDocument) {
		this.sectionDocument = sectionDocument;
	}
	public Set<StandardCustomDocDto> getStandardCustomDoc() {
		if(CommonUtil.isCollectionEmpty(standardCustomDoc) && !CommonUtil.isCollectionEmpty(standardCustomDocList)){
			standardCustomDoc=new HashSet<>();
			standardCustomDoc.addAll(getStandardCustomDocList());
		}
		return standardCustomDoc;
	}
	public void setStandardCustomDoc(Set<StandardCustomDocDto> standardCustomDoc) {
		this.standardCustomDoc = standardCustomDoc;
	}
	public List<StandardCustomDocDto> getStandardCustomDocList() {
		return standardCustomDocList;
	}
	public void setStandardCustomDocList(List<StandardCustomDocDto> standardCustomDocList) {
		this.standardCustomDocList = standardCustomDocList;
	}
	public TenderCommitteeDto getTenderCommittee() {
		return tenderCommittee;
	}
	public void setTenderCommittee(TenderCommitteeDto tenderCommittee) {
		this.tenderCommittee = tenderCommittee;
	}
	public Date getPublishingDate() {
		return publishingDate;
	}
	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}
	public String getBidOpeningAddr() {
		return bidOpeningAddr;
	}
	public void setBidOpeningAddr(String bidOpeningAddr) {
		this.bidOpeningAddr = bidOpeningAddr;
	}
	public String getPreBidAddr() {
		return preBidAddr;
	}
	public void setPreBidAddr(String preBidAddr) {
		this.preBidAddr = preBidAddr;
	}
	public String getTahdrScheduling() {
		return tahdrScheduling;
	}
	public void setTahdrScheduling(String tahdrScheduling) {
		this.tahdrScheduling = tahdrScheduling;
	}
	public BigDecimal getMinBidDifference() {
		return minBidDifference;
	}
	public void setMinBidDifference(BigDecimal minBidDifference) {
		this.minBidDifference = minBidDifference;
	}
	public Date getRevisedBidOpenningDate() {
		return revisedBidOpenningDate;
	}
	public void setRevisedBidOpenningDate(Date revisedBidOpenningDate) {
		this.revisedBidOpenningDate = revisedBidOpenningDate;
	}
	public Date getRevisedBidToDate() {
		return revisedBidToDate;
	}
	public void setRevisedBidToDate(Date revisedBidToDate) {
		this.revisedBidToDate = revisedBidToDate;
	}
	public Date getWinnerSelectionDate() {
		return winnerSelectionDate;
	}
	public void setWinnerSelectionDate(Date winnerSelectionDate) {
		this.winnerSelectionDate = winnerSelectionDate;
	}
	public AttachmentDto getOfficeNote() {
		return officeNote;
	}
	public void setOfficeNote(AttachmentDto officeNote) {
		this.officeNote = officeNote;
	}
	public String getCommencementPeriodCode() {
		return commencementPeriodCode;
	}
	public void setCommencementPeriodCode(String commencementPeriodCode) {
		this.commencementPeriodCode = commencementPeriodCode;
	}
	public Long getOldTahdrDetailId() {
		return oldTahdrDetailId;
	}
	public void setOldTahdrDetailId(Long oldTahdrDetailId) {
		this.oldTahdrDetailId = oldTahdrDetailId;
	}
	public String getIsNoDeviation() {
		return isNoDeviation;
	}
	public void setIsNoDeviation(String isNoDeviation) {
		this.isNoDeviation = isNoDeviation;
	}
	public AttachmentDto getTenderDoc() {
		return tenderDoc;
	}
	public void setTenderDoc(AttachmentDto tenderDoc) {
		this.tenderDoc = tenderDoc;
	}
	public String getContactPersonName() {
		return contactPersonName;
	}
	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}
	public String getContactPersonNo() {
		return contactPersonNo;
	}
	public void setContactPersonNo(String contactPersonNo) {
		this.contactPersonNo = contactPersonNo;
	}
	public String getEditedDates() {
		return editedDates;
	}
	public void setEditedDates(String editedDates) {
		this.editedDates = editedDates;
	}
	public DesignationDto getDesignation() {
		return designation;
	}
	public void setDesignation(DesignationDto designation) {
		this.designation = designation;
	}
	public String getDatesSubmitted() {
		return datesSubmitted;
	}
	public void setDatesSubmitted(String datesSubmitted) {
		this.datesSubmitted = datesSubmitted;
	}
	public Date getAuctionFromDate() {
		return auctionFromDate;
	}
	public void setAuctionFromDate(Date auctionFromDate) {
		this.auctionFromDate = auctionFromDate;
	}
	public Date getAuctionToDate() {
		return auctionToDate;
	}
	public void setAuctionToDate(Date auctionToDate) {
		this.auctionToDate = auctionToDate;
	}
	public String getIsAuctionDateSetLater() {
		if(CommonUtil.isStringEmpty(isAuctionDateSetLater)){
			return "N";
		}
		return isAuctionDateSetLater;
	}
	public void setIsAuctionDateSetLater(String isAuctionDateSetLater) {
		this.isAuctionDateSetLater = isAuctionDateSetLater;
	}
	public String getIsWinnerSelectionDateSetLater() {
		if(CommonUtil.isStringEmpty(isWinnerSelectionDateSetLater)){
			return "N";
		}
		return isWinnerSelectionDateSetLater;
	}
	public void setIsWinnerSelectionDateSetLater(String isWinnerSelectionDateSetLater) {
		this.isWinnerSelectionDateSetLater = isWinnerSelectionDateSetLater;
	}
	public String getIsCreateVersion() {
		return isCreateVersion;
	}
	public void setIsCreateVersion(String isCreateVersion) {
		this.isCreateVersion = isCreateVersion;
	}
	
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
	public String getOtherCommencementPeriod() {
		return otherCommencementPeriod;
	}
	public void setOtherCommencementPeriod(String otherCommencementPeriod) {
		this.otherCommencementPeriod = otherCommencementPeriod;
	}
	public String getIsTechnicalBid() {
		return isTechnicalBid;
	}
	public void setIsTechnicalBid(String isTechnicalBid) {
		this.isTechnicalBid = isTechnicalBid;
	}
	public String getIsTenderDocs() {
		return isTenderDocs;
	}
	public void setIsTenderDocs(String isTenderDocs) {
		this.isTenderDocs = isTenderDocs;
	}
	public String getIsReqDocs() {
		return isReqDocs;
	}
	public void setIsReqDocs(String isReqDocs) {
		this.isReqDocs = isReqDocs;
	}
	public String getIsGTP() {
		return isGTP;
	}
	public void setIsGTP(String isGTP) {
		this.isGTP = isGTP;
	}
	public String getIsTechnicalDocs() {
		return isTechnicalDocs;
	}
	public void setIsTechnicalDocs(String isTechnicalDocs) {
		this.isTechnicalDocs = isTechnicalDocs;
	}
	public String getIsCommercialDocs() {
		return isCommercialDocs;
	}
	public void setIsCommercialDocs(String isCommercialDocs) {
		this.isCommercialDocs = isCommercialDocs;
	}
	public String getIsPriceDocs() {
		return isPriceDocs;
	}
	public void setIsPriceDocs(String isPriceDocs) {
		this.isPriceDocs = isPriceDocs;
	}
	public String getIsPreliminaryScrutiny() {
		return isPreliminaryScrutiny;
	}
	public void setIsPreliminaryScrutiny(String isPreliminaryScrutiny) {
		this.isPreliminaryScrutiny = isPreliminaryScrutiny;
	}
	public String getIsFinalScrutiny() {
		return isFinalScrutiny;
	}
	public void setIsFinalScrutiny(String isFinalScrutiny) {
		this.isFinalScrutiny = isFinalScrutiny;
	}
	public String getAutoExtentionTime() {
		return autoExtentionTime;
	}
	public void setAutoExtentionTime(String autoExtentionTime) {
		this.autoExtentionTime = autoExtentionTime;
	}
	
}
