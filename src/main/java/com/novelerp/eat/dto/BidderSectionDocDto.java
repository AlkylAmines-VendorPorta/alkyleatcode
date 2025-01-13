/**
 * @author Ankush
 */
package com.novelerp.eat.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appcontext.dto.CommonContextDto;

public class BidderSectionDocDto extends CommonContextDto {

	private static final long serialVersionUID = 1L;
	private Long bidderSectionDocId;
	private SectionDocumentDto sectionDocument;
	private AttachmentDto attachment;
	private String bidSection;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="technicalBidId",scope=Long.class)
	private TechnicalBidDto technicalBid;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="commercialBidId",scope=Long.class)
	private CommercialBidDto commercialBid;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="priceBidId",scope=Long.class)
	private PriceBidDto priceBid;
	
	public Long getBidderSectionDocId() {
		return bidderSectionDocId;
	}
	public void setBidderSectionDocId(Long bidderSectionDocId) {
		this.bidderSectionDocId = bidderSectionDocId;
	}
	public SectionDocumentDto getSectionDocument() {
		return sectionDocument;
	}
	public void setSectionDocument(SectionDocumentDto sectionDocument) {
		this.sectionDocument = sectionDocument;
	}
	public AttachmentDto getAttachment() {
		return attachment;
	}
	public void setAttachment(AttachmentDto attachment) {
		this.attachment = attachment;
	}
	public String getBidSection() {
		return bidSection;
	}
	public void setBidSection(String bidSection) {
		this.bidSection = bidSection;
	}
	public TechnicalBidDto getTechnicalBid() {
		return technicalBid;
	}
	public void setTechnicalBid(TechnicalBidDto technicalBid) {
		this.technicalBid = technicalBid;
	}
	public CommercialBidDto getCommercialBid() {
		return commercialBid;
	}
	public void setCommercialBid(CommercialBidDto commercialBid) {
		this.commercialBid = commercialBid;
	}
	public PriceBidDto getPriceBid() {
		return priceBid;
	}
	public void setPriceBid(PriceBidDto priceBid) {
		this.priceBid = priceBid;
	}
	
}
