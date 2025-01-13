package com.novelerp.alkyl.dto;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appcontext.dto.CommonContextDto;

public class PRAttachmentDto extends CommonContextDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long prAttachmentId;
	private PRDto pr;
	private String name;
	private AttachmentDto attachment;
	private String description;
	private String code;
	private String istc;
	
	
	public Long getPrAttachmentId() {
		return prAttachmentId;
	}
	public void setPrAttachmentId(Long prAttachmentId) {
		this.prAttachmentId = prAttachmentId;
	}
	public PRDto getPr() {
		return pr;
	}
	public void setPr(PRDto pr) {
		this.pr = pr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AttachmentDto getAttachment() {
		return attachment;
	}
	public void setAttachment(AttachmentDto attachment) {
		this.attachment = attachment;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIstc() {
		return istc;
	}
	public void setIstc(String istc) {
		this.istc = istc;
	}
	
	
	
}
