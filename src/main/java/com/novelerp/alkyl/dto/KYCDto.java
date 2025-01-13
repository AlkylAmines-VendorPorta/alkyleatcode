package com.novelerp.alkyl.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.core.util.DateUtil;

public class KYCDto extends CommonContextDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long kycId;
	private AttachmentDto gst1;
	private AttachmentDto gst2;
	private AttachmentDto gst3;
	private AttachmentDto threeB1;
	private AttachmentDto threeB2;
	private AttachmentDto threeB3;
	private AttachmentDto cancelledCheque;
	private String isMSME;
	private AttachmentDto msmeCertificate;
	private String isLowerDeduction;
	private AttachmentDto lowerDeductionCert;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date validFrom;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date validTo; 
	private String ldValue;
	private String isRelatedParty;
	private List<OtherDocumentsDto> otherDocuments;
	private String msmeNumber;
	private String msmeType;
	
	public Long getKycId() {
		return kycId;
	}
	public void setKycId(Long kycId) {
		this.kycId = kycId;
	}
	public AttachmentDto getGst1() {
		return gst1;
	}
	public void setGst1(AttachmentDto gst1) {
		this.gst1 = gst1;
	}
	public AttachmentDto getGst2() {
		return gst2;
	}
	public void setGst2(AttachmentDto gst2) {
		this.gst2 = gst2;
	}
	public AttachmentDto getGst3() {
		return gst3;
	}
	public void setGst3(AttachmentDto gst3) {
		this.gst3 = gst3;
	}
	public AttachmentDto getThreeB1() {
		return threeB1;
	}
	public void setThreeB1(AttachmentDto threeB1) {
		this.threeB1 = threeB1;
	}
	public AttachmentDto getThreeB2() {
		return threeB2;
	}
	public void setThreeB2(AttachmentDto threeB2) {
		this.threeB2 = threeB2;
	}
	public AttachmentDto getThreeB3() {
		return threeB3;
	}
	public void setThreeB3(AttachmentDto threeB3) {
		this.threeB3 = threeB3;
	}
	public AttachmentDto getCancelledCheque() {
		return cancelledCheque;
	}
	public void setCancelledCheque(AttachmentDto cancelledCheque) {
		this.cancelledCheque = cancelledCheque;
	}
	public String getIsMSME() {
		return isMSME;
	}
	public void setIsMSME(String isMSME) {
		if(isMSME==null){
			isMSME="N";
		}
		this.isMSME = isMSME;
	}
	public AttachmentDto getMsmeCertificate() {
		return msmeCertificate;
	}
	public void setMsmeCertificate(AttachmentDto msmeCertificate) {
		this.msmeCertificate = msmeCertificate;
	}
	public String getIsLowerDeduction() {
		return isLowerDeduction;
	}
	public void setIsLowerDeduction(String isLowerDeduction) {
		if(isLowerDeduction==null){
			isLowerDeduction = "N";
		}
		this.isLowerDeduction = isLowerDeduction;
	}
	public AttachmentDto getLowerDeductionCert() {
		return lowerDeductionCert;
	}
	public void setLowerDeductionCert(AttachmentDto lowerDeductionCert) {
		this.lowerDeductionCert = lowerDeductionCert;
	}
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	public Date getValidTo() {
		return validTo;
	}
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
	public String getLdValue() {
		return ldValue;
	}
	public void setLdValue(String ldValue) {
		this.ldValue = ldValue;
	}
	public String getIsRelatedParty() {
		return isRelatedParty;
	}
	public void setIsRelatedParty(String isRelatedParty) {
		if(isRelatedParty==null){
			isRelatedParty="N";
		}
		this.isRelatedParty = isRelatedParty;
	}	
	public String getMsmeNumber() {
		return msmeNumber;
	}

	public void setMsmeNumber(String msmeNumber) {
		this.msmeNumber = msmeNumber;
	}
	public List<OtherDocumentsDto> getOtherDocuments() {
		return otherDocuments;
	}
	public void setOtherDocuments(List<OtherDocumentsDto> otherDocuments) {
		this.otherDocuments = otherDocuments;
	}
	public String getMsmeType() {
		return msmeType;
	}
	public void setMsmeType(String msmeType) {
		this.msmeType = msmeType;
	}
	
	
	
}
