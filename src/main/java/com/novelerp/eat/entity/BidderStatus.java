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
@Table(name="t_bidder_status")
public class BidderStatus extends ContextPO{

	private static final long serialVersionUID = 1L;
	private Long bidderStatusId;
    private String status;
    private String scrutinyComment;
    private Bidder bidder;
    
    @Id
	@SequenceGenerator(name="t_bidder_status_seq",sequenceName="t_bidder_status_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="t_bidder_status_seq")	
	@Column(name = "t_bidder_status_id", updatable=false)
	public Long getBidderStatusId() {
		return bidderStatusId;
	}
	public void setBidderStatusId(Long bidderStatusId) {
		this.bidderStatusId = bidderStatusId;
	}
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="scrutiny_comment")
	public String getScrutinyComment() {
		return scrutinyComment;
	}
	public void setScrutinyComment(String scrutinyComment) {
		this.scrutinyComment = scrutinyComment;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_bidder_id")
	public Bidder getBidder() {
		return bidder;
	}
	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}
    
    
}
