/**
 * @author Ankush
 */
package com.novelerp.eat.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.commons.util.CommonUtil;

@Entity
@Table(name="t_technical_bid")
public class TechnicalBid extends ContextPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long technicalBidId;
	private Set<BidderGtp> bidderGtp;
	private String isSubmit;
	private Attachment digiSignedDoc;
	private ItemBid itemBid;
	private Set<BidderSectionDoc> bidderSecDoc;
	private String status;
	
	@Id
	@SequenceGenerator(name="t_technical_bid_seq",sequenceName="t_technical_bid_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="t_technical_bid_seq")	
	@Column(name = "t_technical_bid_id", updatable=false)
	public Long getTechnicalBidId() {
		return technicalBidId;
	}
	public void setTechnicalBidId(Long technicalBidId) {
		this.technicalBidId = technicalBidId;
	}
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="technicalBid")
	public Set<BidderGtp> getBidderGtp() {
		return bidderGtp;
	}
	public void setBidderGtp(Set<BidderGtp> bidderGtp) {
		this.bidderGtp = bidderGtp;
	}
	
	@Column(name="is_submit")
	public String getIsSubmit() {
		if(CommonUtil.isStringEmpty(isSubmit)){
			return "N";
		}
		return isSubmit;
	}
	public void setIsSubmit(String isSubmit) {
		this.isSubmit = isSubmit;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="digitally_signed_doc_id")
	public Attachment getDigiSignedDoc() {
		return digiSignedDoc;
	}
	public void setDigiSignedDoc(Attachment digiSignedDoc) {
		this.digiSignedDoc = digiSignedDoc;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_item_bid_id")
	public ItemBid getItemBid() {
		return itemBid;
	}
	public void setItemBid(ItemBid itemBid) {
		this.itemBid = itemBid;
	}
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="technicalBid")
	public Set<BidderSectionDoc> getBidderSecDoc() {
		return bidderSecDoc;
	}
	public void setBidderSecDoc(Set<BidderSectionDoc> bidderSecDoc) {
		this.bidderSecDoc = bidderSecDoc;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
