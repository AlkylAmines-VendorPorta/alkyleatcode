package com.novelerp.eat.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.commons.util.CommonUtil;

/**
 * @author Aman Sahu
 *
 */

public class BidderGtpDto extends CommonContextDto {

	private static final long serialVersionUID = 1L;
	private Long bidderGtpId;
	private String textResponse;
	private AttachmentDto fileResponse;
	private TAHDRMaterialGTPDto tahdrMaterialgtp;
	@JsonIdentityInfo(generator=PropertyGenerator.class,property="technicalBidId",scope=Long.class)
	private TechnicalBidDto technicalBid;
	private String isNotApplicable;
	
	
	public Long getBidderGtpId() {
		return bidderGtpId;
	}
	public void setBidderGtpId(Long bidderGtpId) {
		this.bidderGtpId = bidderGtpId;
	}
	
	public TAHDRMaterialGTPDto getTahdrMaterialgtp() {
		return tahdrMaterialgtp;
	}
	public void setTahdrMaterialgtp(TAHDRMaterialGTPDto tahdrMaterialgtp) {
		this.tahdrMaterialgtp = tahdrMaterialgtp;
	}
	public TechnicalBidDto getTechnicalBid() {
		return technicalBid;
	}
	public void setTechnicalBid(TechnicalBidDto technicalBid) {
		this.technicalBid = technicalBid;
	}
	public AttachmentDto getFileResponse() {
		return fileResponse;
	}
	public void setFileResponse(AttachmentDto fileResponse) {
		this.fileResponse = fileResponse;
	}
	public String getTextResponse() {
		return textResponse;
	}
	public void setTextResponse(String textResponse) {
		this.textResponse = textResponse;
	}
	
	public String getIsNotApplicable() {
		if(CommonUtil.isStringEmpty(isNotApplicable)){
			isNotApplicable="N";
		}
		return isNotApplicable;
	}
	public void setIsNotApplicable(String isNotApplicable) {
		this.isNotApplicable = isNotApplicable;
	}

}
