package com.novelerp.appbase.master.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.LocationDto;
/**
 * 
 * @author Aman
 */
import com.novelerp.appcontext.dto.UserDetailsDto;
import com.novelerp.core.util.DateUtil;
public class PartnerSignatoryDto extends CommonContextDto {

	private static final long serialVersionUID = 1L;
	private Long partnerSignatoryId;

	private UserDetailsDto userDetail;
	private LocationDto location;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date validFrom;
	private String isEEApproved;
	private String eeComment;
	private String isCEApproved;
	private String ceComment;
	private String remark;
	private String isApproved;
	private String holderType;
	private AttachmentDto attorneyCertificate;
	private AttachmentDto digitallySignTestDoc;
	private String otherDesignation;
	
	public Long getPartnerSignatoryId() {
		return partnerSignatoryId;
	}
	public void setPartnerSignatoryId(Long partnerSignatoryId) {
		this.partnerSignatoryId = partnerSignatoryId;
	}
	public UserDetailsDto getUserDetail() {
		return userDetail;
	}
	public void setUserDetail(UserDetailsDto userDetail) {
		this.userDetail = userDetail;
	}
	public LocationDto getLocation() {
		return location;
	}
	public void setLocation(LocationDto location) {
		this.location = location;
	}
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	public String getIsEEApproved() {
		return isEEApproved;
	}
	public void setIsEEApproved(String isEEApproved) {
		this.isEEApproved = isEEApproved;
	}
	public String getEeComment() {
		return eeComment;
	}
	public void setEeComment(String eeComment) {
		this.eeComment = eeComment;
	}
	public String getIsCEApproved() {
		return isCEApproved;
	}
	public void setIsCEApproved(String isCEApproved) {
		this.isCEApproved = isCEApproved;
	}
	public String getCeComment() {
		return ceComment;
	}
	public void setCeComment(String ceComment) {
		this.ceComment = ceComment;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	public String getHolderType() {
		return holderType;
	}
	public void setHolderType(String holderType) {
		this.holderType = holderType;
	}
	public AttachmentDto getAttorneyCertificate() {
		return attorneyCertificate;
	}
	public void setAttorneyCertificate(AttachmentDto attorneyCertificate) {
		this.attorneyCertificate = attorneyCertificate;
	}
	public AttachmentDto getDigitallySignTestDoc() {
		return digitallySignTestDoc;
	}
	public void setDigitallySignTestDoc(AttachmentDto digitallySignTestDoc) {
		this.digitallySignTestDoc = digitallySignTestDoc;
	}
	public String getOtherDesignation() {
		return otherDesignation;
	}
	public void setOtherDesignation(String otherDesignation) {
		this.otherDesignation = otherDesignation;
	}

	
	
	
}
