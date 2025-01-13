package com.novelerp.appbase.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="m_attachment")
public class Attachment extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long attachmentId;
	private String name;
	private String code;
	private String description;
	private String path;
	private String fileName;
	private String type;
	private byte[] media;
	private String isDocEncrypted;
	
	@Id
	@SequenceGenerator(name="m_attachment_seq",sequenceName="m_attachment_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_attachment_seq")	
	@Column(name = "m_attachment_id", updatable=false)
	public Long getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="value")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="path")
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Column(name="file_name")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="media")
	public byte[] getMedia() {
		return media;
	}
	public void setMedia(byte[] media) {
		this.media = media;
	}
	@Column(name="is_doc_encrypted")
	public String getIsDocEncrypted() {
		return isDocEncrypted;
	}
	public void setIsDocEncrypted(String isDocEncrypted) {
		this.isDocEncrypted = isDocEncrypted;
	}
	
}
