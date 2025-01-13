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

import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appcontext.entity.ContextPO;

/**
 * Aman Sahu
 */

@Entity
@Table(name="t_scrutiny_file")
public class ScrutinyFile extends ContextPO{

	private static final long serialVersionUID = 1L;
	private Long scrutinyFileId;
	private ItemScrutiny itemScrutiny;
	private Bidder bidder;
	private ItemBid itemBid;
	private String scrutinyLevel;
	private String scrutinyType;
	private Attachment attachment;
	
	@Id
	@SequenceGenerator(name="t_scrutiny_file_seq",sequenceName="t_scrutiny_file_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="t_scrutiny_file_seq")	
	@Column(name = "t_scrutiny_file_id", updatable=false)
	public Long getScrutinyFileId() {
		return scrutinyFileId;
	}
	public void setScrutinyFileId(Long scrutinyFileId) {
		this.scrutinyFileId = scrutinyFileId;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_bidder_id")
	public Bidder getBidder() {
		return bidder;
	}
	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}
	@Column(name="scrutiny_lvl")
	public String getScrutinyLevel() {
		return scrutinyLevel;
	}
	public void setScrutinyLevel(String scrutinyLevel) {
		this.scrutinyLevel = scrutinyLevel;
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
	public Attachment getAttachment() {
		return attachment;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_item_Bid_id")
	public ItemBid getItemBid() {
		return itemBid;
	}
	public void setItemBid(ItemBid itemBid) {
		this.itemBid = itemBid;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_item_scrutiny_id")
	public ItemScrutiny getItemScrutiny() {
		return itemScrutiny;
	}
	public void setItemScrutiny(ItemScrutiny itemScrutiny) {
		this.itemScrutiny = itemScrutiny;
	}
	
	
	
	
	
	
	

}
