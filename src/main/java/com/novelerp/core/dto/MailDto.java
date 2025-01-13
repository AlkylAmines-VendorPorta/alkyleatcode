package com.novelerp.core.dto;

import java.util.ArrayList;
import java.util.List;

public class MailDto {
	
	private String subject;
	private String mailContent;
	private List<String> recipientList = new ArrayList<>();
	private List<String> ccList = new ArrayList<>();
	private String singleRecipient;
	private String filePath;
	private String fileName;
	//private List<String> cclistemailid = new ArrayList<>();
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMailContent() {
		return mailContent;
	}
	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}
	public List<String> getRecipientList() {
		return recipientList;
	}
	public void setRecipientList(List<String> recipientList) {
		this.recipientList = recipientList;
	}
	public List<String> getCcList() {
		return ccList;
	}
	public void setCcList(List<String> ccList) {
		this.ccList = ccList;
	}
	public String getSingleRecipient() {
		return singleRecipient;
	}
	public void setSingleRecipient(String singleRecipient) {
		this.singleRecipient = singleRecipient;
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
