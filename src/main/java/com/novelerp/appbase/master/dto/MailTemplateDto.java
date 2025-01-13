package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class MailTemplateDto extends CommonContextDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long mailId;
	private String mailTemplate;
	private String templateType;
	private String subject;
	private String filePath;
	private String fileName;
	
	public Long getMailId() {
		return mailId;
	}
	public void setMailId(Long mailId) {
		this.mailId = mailId;
	}
	public String getMailTemplate() {
		return mailTemplate;
	}
	public void setMailTemplate(String mailTemplate) {
		this.mailTemplate = mailTemplate;
	}
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
