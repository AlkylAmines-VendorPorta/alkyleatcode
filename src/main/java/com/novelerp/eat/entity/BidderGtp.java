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
 * @author Aman Sahu
 *
 */

@Entity
@Table(name="t_bidder_gtp")
public class BidderGtp extends ContextPO {

	private static final long serialVersionUID = 1L;
	private Long bidderGtpId;
	private Bidder bidder;
	private String textResponse;
	private Attachment fileResponse;
	private TAHDRMaterialGTP tahdrMaterialgtp;
	private TechnicalBid technicalBid;
	private String isNotApplicable;
	
	@Id
	@SequenceGenerator(name="t_bidder_gtp_seq",sequenceName="t_bidder_gtp_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="t_bidder_gtp_seq")	
	@Column(name = "t_bidder_gtp_id", updatable=false)
	public Long getBidderGtpId() {
		return bidderGtpId;
	}
	public void setBidderGtpId(Long bidderGtpId) {
		this.bidderGtpId = bidderGtpId;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_bidder_id")
	public Bidder getBidder() {
		return bidder;
	}
	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_tahdr_material_gtp_id")
	public TAHDRMaterialGTP getTahdrMaterialgtp() {
		return tahdrMaterialgtp;
	}
	public void setTahdrMaterialgtp(TAHDRMaterialGTP tahdrMaterialgtp) {
		this.tahdrMaterialgtp = tahdrMaterialgtp;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_technical_bid_id")
	public TechnicalBid getTechnicalBid() {
		return technicalBid;
	}
	public void setTechnicalBid(TechnicalBid technicalBid) {
		this.technicalBid = technicalBid;
	}
	
	@Column(name="text_response")
	public String getTextResponse() {
		return textResponse;
	}
	public void setTextResponse(String textResponse) {
		this.textResponse = textResponse;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="file_response",referencedColumnName="m_attachment_id")
	public Attachment getFileResponse() {
		return fileResponse;
	}
	public void setFileResponse(Attachment fileResponse) {
		this.fileResponse = fileResponse;
	}
	
	@Column(name="is_not_applicable")
	public String getIsNotApplicable() {
		return isNotApplicable;
	}
	public void setIsNotApplicable(String isNotApplicable) {
		this.isNotApplicable = isNotApplicable;
	}
	
}
