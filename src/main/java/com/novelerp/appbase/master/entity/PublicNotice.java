package com.novelerp.appbase.master.entity;

import java.util.Date;

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

import com.novelerp.appcontext.entity.ContextPO;
import com.novelerp.eat.entity.TAHDR;

@Entity
@Table(name="m_public_notice")
public class PublicNotice extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long publicNoticeId;
	private String name;
	private String code;
	private String description;
	
	private Date publishingDate;
	private Date tenderSaleOpeningDate;
	private Date tenderSaleClosingDate;
	private String title;
	private String matter;
	
	private Attachment attachment;
	private TAHDR tahdr;
	/*private TenderDetail tenderDetails;*/
	
	
	@Id
	@SequenceGenerator(name="m_public_notice_seq",sequenceName="m_public_notice_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_public_notice_seq")	
	@Column(name = "m_public_notice_id", updatable=false)
	public Long getPublicNoticeId() {
		return publicNoticeId;
	}
	public void setPublicNoticeId(Long publicNoticeId) {
		this.publicNoticeId = publicNoticeId;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "value")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "publishing_date")
	public Date getPublishingDate() {
		return publishingDate;
	}
	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}
	@Column(name = "sale_opening_date")
	public Date getTenderSaleOpeningDate() {
		return tenderSaleOpeningDate;
	}
	public void setTenderSaleOpeningDate(Date tenderSaleOpeningDate) {
		this.tenderSaleOpeningDate = tenderSaleOpeningDate;
	}
	@Column(name = "sale_closing_date")
	public Date getTenderSaleClosingDate() {
		return tenderSaleClosingDate;
	}
	public void setTenderSaleClosingDate(Date tenderSaleClosingDate) {
		this.tenderSaleClosingDate = tenderSaleClosingDate;
	}
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "matter")
	public String getMatter() {
		return matter;
	}
	public void setMatter(String matter) {
		this.matter = matter;
	}
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "m_attachment_id")
	public Attachment getAttachment() {
		return attachment;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_tahdr_id")
	public TAHDR getTahdr() {
		return tahdr;
	}
	public void setTahdr(TAHDR tahdr) {
		this.tahdr = tahdr;
	}
	
	
}

