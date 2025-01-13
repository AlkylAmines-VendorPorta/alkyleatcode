package com.novelerp.appbase.master.dto;

import java.util.Arrays;

import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.UserDto;

public class AsnReminderDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;

	private Long reminderId;
	private String vendorEmail;
	private String description;
	private String path;
	private String fileName;
	private String type;
	private byte[] media;
	private String fileExtension;
	private String isDocEncrypted;
	private String sessionFileName;
	private String isTimestampDoc;
	private String poNo;
	private String invoiceNo;
	private String invoiceDate;
	private UserDto vendor;
	private PurchaseOrderDto CCMail;
	
	public PurchaseOrderDto getCCMail() {
		return CCMail;
	}
	public void setCCMail(PurchaseOrderDto cCMail) {
		CCMail = cCMail;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public Long getReminderId() {
		return reminderId;
	}
	public void setReminderId(Long reminderId) {
		this.reminderId = reminderId;
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
	
	public String getVendorEmail() {
		return vendorEmail;
	}
	public void setVendorEmail(String vendorEmail) {
		this.vendorEmail = vendorEmail;
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
	public UserDto getVendor() {
		return vendor;
	}
	public void setVendor(UserDto vendor) {
		this.vendor = vendor;
	}
	@Override
	public String toString() {
		return "AsnReminderDto [reminderId=" + reminderId + ", vendorEmail=" + vendorEmail + ", description="
				+ description + ", path=" + path + ", fileName=" + fileName + ", type=" + type + ", media="
				+ Arrays.toString(media) + ", fileExtension=" + fileExtension + ", isDocEncrypted=" + isDocEncrypted
				+ ", sessionFileName=" + sessionFileName + ", isTimestampDoc=" + isTimestampDoc + ", poNo=" + poNo
				+ ", invoiceNo=" + invoiceNo + ", invoiceDate=" + invoiceDate + ", vendor=" + vendor + ", CCMail="
				+ CCMail + "]";
	}
	
	
	
}
