package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class AttachmentDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;

	private Long attachmentId;
	private String name;
	private String code;
	private String description;
	private String path;
	private String fileName;
	private String type;
	private byte[] media;
	private String fileExtension;
	private String isDocEncrypted;
	private String sessionFileName;
	private String isTimestampDoc;
	public Long getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getMedia() {
		return media;
	}
	public void setMedia(byte[] media) {
		this.media = media;
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
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public String getIsDocEncrypted() {
		if(!"Y".equals(isDocEncrypted)){
			return "N";
		}
		return isDocEncrypted;
	}
	public void setIsDocEncrypted(String isDocEncrypted) {
		this.isDocEncrypted = isDocEncrypted;
	}
	public String getIsTimestampDoc(){
		if(!"Y".equals(isTimestampDoc)){
			return "N";
		}
		return isTimestampDoc;
	}
	public void setIsTimestampDoc(String isTimestampDoc) {
		this.isTimestampDoc = isTimestampDoc;
	}
	public String getSessionFileName() {
		return sessionFileName;
	}
	public void setSessionFileName(String sessionFileName) {
		this.sessionFileName = sessionFileName;
	}
	
	
	
}
