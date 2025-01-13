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

/**
 * 
 * @author Aman Sahu
 *
 */
@Entity
@Table(name="t_item_scrutiny")
public class ItemScrutiny extends ContextPO{

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long itemScrutinyId;
	private Bidder bidder;
	private ItemBid itemBid;
	private String scrutinyType;
	private Attachment scrutinyFile;
	private String finalScrutinyComment;
	private String finalScrutinyStatus;
	private String preliminaryScrutinyComment;
	private String preliminaryScrutinyStatus;
	private String bidderDeviationStatus;
	private String isAudited;
	private String preliminaryAuditorComment;
	private String preliminaryAuditorStatus;
	private String isFinalAudited;
	private String finalAuditorComment;
	private String finalAuditorStatus;
	private String isScrutinySubmitted;
	private String isFinalScrutinySubmitted;
	
	@Id
	@SequenceGenerator(name="t_item_scrutiny_seq",sequenceName="t_item_scrutiny_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="t_item_scrutiny_seq")	
	@Column(name = "t_item_scrutiny_id", updatable=false)
	public Long getItemScrutinyId() {
		return itemScrutinyId;
	}
	public void setItemScrutinyId(Long itemScrutinyId) {
		this.itemScrutinyId = itemScrutinyId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_bidder_id")
	public Bidder getBidder() {
		return bidder;
	}
	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_item_bid_id")
	public ItemBid getItemBid() {
		return itemBid;
	}
	public void setItemBid(ItemBid itemBid) {
		this.itemBid = itemBid;
	}
	@Column(name="scrutiny_type")
	public String getScrutinyType() {
		return scrutinyType;
	}
	public void setScrutinyType(String scrutinyType) {
		this.scrutinyType = scrutinyType;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_attachment_id")
	public Attachment getScrutinyFile() {
		return scrutinyFile;
	}
	
	public void setScrutinyFile(Attachment scrutinyFile) {
		this.scrutinyFile = scrutinyFile;
	}
	@Column(name="final_comment")
	public String getFinalScrutinyComment() {
		return finalScrutinyComment;
	}
	public void setFinalScrutinyComment(String finalScrutinyComment) {
		this.finalScrutinyComment = finalScrutinyComment;
	}
	@Column(name="final_status")
	public String getFinalScrutinyStatus() {
		return finalScrutinyStatus;
	}
	public void setFinalScrutinyStatus(String finalScrutinyStatus) {
		this.finalScrutinyStatus = finalScrutinyStatus;
	}
	@Column(name="preliminary_comment")
	public String getPreliminaryScrutinyComment() {
		return preliminaryScrutinyComment;
	}
	public void setPreliminaryScrutinyComment(String preliminaryScrutinyComment) {
		this.preliminaryScrutinyComment = preliminaryScrutinyComment;
	}
	@Column(name="preliminary_status")
	public String getPreliminaryScrutinyStatus() {
		return preliminaryScrutinyStatus;
	}
	public void setPreliminaryScrutinyStatus(String preliminaryScrutinyStatus) {
		this.preliminaryScrutinyStatus = preliminaryScrutinyStatus;
	}
	@Column(name="bidder_deviation_status")
	public String getBidderDeviationStatus() {
		return bidderDeviationStatus;
	}
	public void setBidderDeviationStatus(String bidderDeviationStatus) {
		this.bidderDeviationStatus = bidderDeviationStatus;
	}
	@Column(name="is_audited")
	public String getIsAudited() {
		return isAudited;
	}
	public void setIsAudited(String isAudited) {
		this.isAudited = isAudited;
	}
	@Column(name="audit_comment")
	public String getPreliminaryAuditorComment() {
		return preliminaryAuditorComment;
	}
	public void setPreliminaryAuditorComment(String preliminaryAuditorComment) {
		this.preliminaryAuditorComment = preliminaryAuditorComment;
	}
	@Column(name="audit_status")
	public String getPreliminaryAuditorStatus() {
		return preliminaryAuditorStatus;
	}
	public void setPreliminaryAuditorStatus(String preliminaryAuditorStatus) {
		this.preliminaryAuditorStatus = preliminaryAuditorStatus;
	}
	@Column(name="is_final_audited")
	public String getIsFinalAudited() {
		return isFinalAudited;
	}
	public void setIsFinalAudited(String isFinalAudited) {
		this.isFinalAudited = isFinalAudited;
	}
	@Column(name="final_audit_comment")
	public String getFinalAuditorComment() {
		return finalAuditorComment;
	}
	public void setFinalAuditorComment(String finalAuditorComment) {
		this.finalAuditorComment = finalAuditorComment;
	}
	@Column(name="final_audit_status")
	public String getFinalAuditorStatus() {
		return finalAuditorStatus;
	}
	public void setFinalAuditorStatus(String finalAuditorStatus) {
		this.finalAuditorStatus = finalAuditorStatus;
	}
	@Column(name="scrutiny_submitted")
	public String getIsScrutinySubmitted() {
		return isScrutinySubmitted;
	}
	public void setIsScrutinySubmitted(String isScrutinySubmitted) {
		this.isScrutinySubmitted = isScrutinySubmitted;
	}
	@Column(name="final_scrutiny_submitted")
	public String getIsFinalScrutinySubmitted() {
		return isFinalScrutinySubmitted;
	}
	public void setIsFinalScrutinySubmitted(String isFinalScrutinySubmitted) {
		this.isFinalScrutinySubmitted = isFinalScrutinySubmitted;
	}
	
	
	
	
}
