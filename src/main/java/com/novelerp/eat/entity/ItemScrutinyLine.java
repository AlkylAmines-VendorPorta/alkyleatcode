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
import com.novelerp.appbase.master.entity.ScrutinyPoint;
import com.novelerp.appcontext.entity.ContextPO;
/**
 * 
 * @author Aman Sahu
 *
 */
@Entity
@Table(name="t_item_scrutiny_line")
public class ItemScrutinyLine extends ContextPO{

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long itemScrutinyLineId;
	private ItemScrutiny itemScrutiny;
	private TAHDRMaterial tahdrMaterial;
	private BidderGtp bidderGtp;
	private String isDeviation;
	private String deviationType;
	private String deviationComment;
	private BidderSectionDoc bidderSectionDoc;
	private ScrutinyPoint scrutinyPoint;
	private String finalScrutinyComment;
	private String finalScrutinyStatus;
	private String deviationBidComment;
	private String textResponse;
	private Attachment fileResponse;
	private String isDeviationSubmitted;
	private String isFinalScrutinySubmitted;
	private String auditorStatus;
	private String auditorComment;
	private String finalAuditorStatus;
	private String finalAuditorComment;
	
	@Id
	@SequenceGenerator(name="t_item_scrutiny_line_seq",sequenceName="t_item_scrutiny_line_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="t_item_scrutiny_line_seq")	
	@Column(name = "t_item_scrutiny_line_id", updatable=false)
	public Long getItemScrutinyLineId() {
		return itemScrutinyLineId;
	}
	public void setItemScrutinyLineId(Long itemScrutinyLineId) {
		this.itemScrutinyLineId = itemScrutinyLineId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_item_scrutiny_id")
	public ItemScrutiny getItemScrutiny() {
		return itemScrutiny;
	}
	public void setItemScrutiny(ItemScrutiny itemScrutiny) {
		this.itemScrutiny = itemScrutiny;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_bidder_gtp_id")
	public BidderGtp getBidderGtp() {
		return bidderGtp;
	}
	public void setBidderGtp(BidderGtp bidderGtp) {
		this.bidderGtp = bidderGtp;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_tahdr_material_id")
	public TAHDRMaterial getTahdrMaterial() {
		return tahdrMaterial;
	}
	public void setTahdrMaterial(TAHDRMaterial tahdrMaterial) {
		this.tahdrMaterial = tahdrMaterial;
	}
	
	@Column(name="isdeviation")
	public String getIsDeviation() {
		return isDeviation;
	}
	public void setIsDeviation(String isDeviation) {
		this.isDeviation = isDeviation;
	}
	@Column(name="deviation_type")
	public String getDeviationType() {
		return deviationType;
	}
	public void setDeviationType(String deviationType) {
		this.deviationType = deviationType;
	}
	@Column(name="deviation_comment")
	public String getDeviationComment() {
		return deviationComment;
	}
	public void setDeviationComment(String deviationComment) {
		this.deviationComment = deviationComment;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_bidder_section_doc_id")
	public BidderSectionDoc getBidderSectionDoc() {
		return bidderSectionDoc;
	}
	public void setBidderSectionDoc(BidderSectionDoc bidderSectionDoc) {
		this.bidderSectionDoc = bidderSectionDoc;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_scrutiny_point_id")
	public ScrutinyPoint getScrutinyPoint() {
		return scrutinyPoint;
	}
	public void setScrutinyPoint(ScrutinyPoint scrutinyPoint) {
		this.scrutinyPoint = scrutinyPoint;
	}
	@Column(name="final_scrutiny_comment")
	public String getFinalScrutinyComment() {
		return finalScrutinyComment;
	}
	public void setFinalScrutinyComment(String finalScrutinyComment) {
		this.finalScrutinyComment = finalScrutinyComment;
	}
	@Column(name="final_scrutiny_status")
	public String getFinalScrutinyStatus() {
		return finalScrutinyStatus;
	}
	public void setFinalScrutinyStatus(String finalScrutinyStatus) {
		this.finalScrutinyStatus = finalScrutinyStatus;
	}
	@Column(name="deviation_bid_comment")
	public String getDeviationBidComment() {
		return deviationBidComment;
	}
	public void setDeviationBidComment(String deviationBidComment) {
		this.deviationBidComment = deviationBidComment;
	}
	@Column(name="text_response")
	public String getTextResponse() {
		return textResponse;
	}
	public void setTextResponse(String textResponse) {
		this.textResponse = textResponse;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="file_response",referencedColumnName="m_attachment_id")
	public Attachment getFileResponse() {
		return fileResponse;
	}
	public void setFileResponse(Attachment fileResponse) {
		this.fileResponse = fileResponse;
	}
	@Column(name="deviation_submitted")
	public String getIsDeviationSubmitted() {
		return isDeviationSubmitted;
	}
	public void setIsDeviationSubmitted(String isDeviationSubmitted) {
		this.isDeviationSubmitted = isDeviationSubmitted;
	}
	@Column(name="finalscrutiny_submitted")
	public String getIsFinalScrutinySubmitted() {
		return isFinalScrutinySubmitted;
	}
	public void setIsFinalScrutinySubmitted(String isFinalScrutinySubmitted) {
		this.isFinalScrutinySubmitted = isFinalScrutinySubmitted;
	}
	@Column(name="auditor_status")
	public String getAuditorStatus() {
		return auditorStatus;
	}
	public void setAuditorStatus(String auditorStatus) {
		this.auditorStatus = auditorStatus;
	}
	@Column(name="auditor_comment")
	public String getAuditorComment() {
		return auditorComment;
	}
	public void setAuditorComment(String auditorComment) {
		this.auditorComment = auditorComment;
	}
	@Column(name="final_auditor_status")
	public String getFinalAuditorStatus() {
		return finalAuditorStatus;
	}
	public void setFinalAuditorStatus(String finalAuditorStatus) {
		this.finalAuditorStatus = finalAuditorStatus;
	}
	@Column(name="final_auditor_comment")
	public String getFinalAuditorComment() {
		return finalAuditorComment;
	}
	public void setFinalAuditorComment(String finalAuditorComment) {
		this.finalAuditorComment = finalAuditorComment;
	}	
	
}
