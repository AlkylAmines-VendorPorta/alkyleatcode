/**
 * @author Ankush
 */
package com.novelerp.eat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="t_bidder_section_doc")
public class BidderSectionDoc extends ContextPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long bidderSectionDocId;
	private SectionDocument sectionDocument;
	private Attachment attachment;
	private String bidSection;
	private TechnicalBid technicalBid;
	private CommercialBid commercialBid;
	private PriceBid priceBid;
	
	@Id
	@SequenceGenerator(name="t_bidder_section_doc_seq",sequenceName="t_bidder_section_doc_seq",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t_bidder_section_doc_seq")
	@Column(name="t_bidder_section_doc_id")
	public Long getBidderSectionDocId() {
		return bidderSectionDocId;
	}
	public void setBidderSectionDocId(Long bidderSectionDocId) {
		this.bidderSectionDocId = bidderSectionDocId;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_section_document_id")
	public SectionDocument getSectionDocument() {
		return sectionDocument;
	}
	public void setSectionDocument(SectionDocument sectionDocument) {
		this.sectionDocument = sectionDocument;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_attachment_id")
	public Attachment getAttachment() {
		return attachment;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	
	@Column(name="bid_section")
	public String getBidSection() {
		return bidSection;
	}
	public void setBidSection(String bidSection) {
		this.bidSection = bidSection;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_technical_bid_id")
	public TechnicalBid getTechnicalBid() {
		return technicalBid;
	}
	public void setTechnicalBid(TechnicalBid technicalBid) {
		this.technicalBid = technicalBid;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_commercial_bid_id")
	public CommercialBid getCommercialBid() {
		return commercialBid;
	}
	public void setCommercialBid(CommercialBid commercialBid) {
		this.commercialBid = commercialBid;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_price_bid_id")
	public PriceBid getPriceBid() {
		return priceBid;
	}
	public void setPriceBid(PriceBid priceBid) {
		this.priceBid = priceBid;
	}
	
}
