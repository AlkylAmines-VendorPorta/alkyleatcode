package com.novelerp.alkyl.dto;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appcontext.dto.CommonContextDto;

public class OtherDocumentsDto extends CommonContextDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long otherDocumentId;
	private AttachmentDto attachmentdetails;
	private KYCDto kycDetails;
	private String name;
	private String code;
	private String description;
	
	
	public KYCDto getKycDetails() {
		return kycDetails;
	}
	public void setKycDetails(KYCDto kycDetails) {
		this.kycDetails = kycDetails;
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
	public Long getOtherDocumentId() {
		return otherDocumentId;
	}
	public void setOtherDocumentId(Long otherDocumentId) {
		this.otherDocumentId = otherDocumentId;
	}
	public AttachmentDto getAttachmentdetails() {
		return attachmentdetails;
	}
	public void setAttachmentdetails(AttachmentDto attachmentdetails) {
		this.attachmentdetails = attachmentdetails;
	}
		
	
}
