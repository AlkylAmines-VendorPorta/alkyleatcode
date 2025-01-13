package com.novelerp.eat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

/**
 * 
 * @author Aman Sahu
 *
 */
@Entity
@Table(name="t_tech_doc_scrutiny")
public class TechnicalDocumentScrutiny extends ContextPO{

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long technicalDocumentScrutinyId;
	private ItemScrutiny itemScrutiny;
	private String deviationTypeCode;
	private String deviationStatusCode;
	private String comment;
	private BidderSectionDoc bidderSectionDoc;
	
	@Id
	@SequenceGenerator(name="t_tech_doc_scrutiny_seq",sequenceName="t_tech_doc_scrutiny_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="t_tech_doc_scrutiny_seq")	
	@Column(name = "t_tech_doc_scrutiny_id", updatable=false)
	public Long getTechnicalDocumentScrutinyId() {
		return technicalDocumentScrutinyId;
	}
	public void setTechnicalDocumentScrutinyId(Long technicalDocumentScrutinyId) {
		this.technicalDocumentScrutinyId = technicalDocumentScrutinyId;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_item_scrutiny_id")
	public ItemScrutiny getItemScrutiny() {
		return itemScrutiny;
	}
	public void setItemScrutiny(ItemScrutiny itemScrutiny) {
		this.itemScrutiny = itemScrutiny;
	}
	@Column(name="deviation_type_code")
	public String getDeviationTypeCode() {
		return deviationTypeCode;
	}
	public void setDeviationTypeCode(String deviationTypeCode) {
		this.deviationTypeCode = deviationTypeCode;
	}
	@Column(name="deviation_status_code")
	public String getDeviationStatusCode() {
		return deviationStatusCode;
	}
	public void setDeviationStatusCode(String deviationStatusCode) {
		this.deviationStatusCode = deviationStatusCode;
	}
	@Column(name="comment")
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_bidder_section_doc_id")
	public BidderSectionDoc getBidderSectionDoc() {
		return bidderSectionDoc;
	}
	public void setBidderSectionDoc(BidderSectionDoc bidderSectionDoc) {
		this.bidderSectionDoc = bidderSectionDoc;
	}
	
	
}
