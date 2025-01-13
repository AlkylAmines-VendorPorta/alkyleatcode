package com.novelerp.appbase.master.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.core.util.DateUtil;
import com.novelerp.eat.dto.TAHDRDto;

public class PublicNoticeDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	private Long publicNoticeId;
	private String name;
	private String code;
	private String description;

	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date publishingDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date tenderSaleOpeningDate;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date tenderSaleClosingDate;
	private String title;
	private String matter;
	
	private AttachmentDto attachment;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="tahdrId",scope=Long.class)
	private TAHDRDto tahdr;
	
	public Long getPublicNoticeId() {
		return publicNoticeId;
	}
	public void setPublicNoticeId(Long publicNoticeId) {
		this.publicNoticeId = publicNoticeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getPublishingDate() {
		return publishingDate;
	}
	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}
	public Date getTenderSaleOpeningDate() {
		return tenderSaleOpeningDate;
	}
	public void setTenderSaleOpeningDate(Date tenderSaleOpeningDate) {
		this.tenderSaleOpeningDate = tenderSaleOpeningDate;
	}
	public Date getTenderSaleClosingDate() {
		return tenderSaleClosingDate;
	}
	public void setTenderSaleClosingDate(Date tenderSaleClosingDate) {
		this.tenderSaleClosingDate = tenderSaleClosingDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMatter() {
		return matter;
	}
	public void setMatter(String matter) {
		this.matter = matter;
	}
	public AttachmentDto getAttachment() {
		return attachment;
	}
	public void setAttachment(AttachmentDto attachment) {
		this.attachment = attachment;
	}
	public TAHDRDto getTahdr() {
		return tahdr;
	}
	public void setTahdr(TAHDRDto tahdr) {
		this.tahdr = tahdr;
	}
   
	
}
