package com.novelerp.alkyl.entity;

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
import com.novelerp.eat.entity.Bidder;

@Entity
@Table(name="t_bidder_attachment")
public class BidderAttachment extends ContextPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long bidderAttachmentId;
	private Bidder bidder;
	private String name;
	private Attachment attachment;
	private String description;
	private String code;
	
	@Id
	@SequenceGenerator(name = "t_bidder_attachment_seq", sequenceName = "t_bidder_attachment_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_bidder_attachment_seq")
	@Column(name="t_bidder_attachment_id", updatable=false)
	public Long getBidderAttachmentId() {
		return bidderAttachmentId;
	}
	public void setBidderAttachmentId(Long bidderAttachmentId) {
		this.bidderAttachmentId = bidderAttachmentId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="t_bidder_id",referencedColumnName="t_bidder_id")
	public Bidder getBidder() {
		return bidder;
	}
	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="m_attachment_id",referencedColumnName="m_attachment_id")
	public Attachment getAttachment() {
		return attachment;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
