package com.novelerp.eat.dto;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appcontext.dto.CommonContextDto;

/**
 * 
 * @author Aman Sahu
 *
 */
public class GtpScrutinyDto extends CommonContextDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long gtpScrutinyId;
	private TechnicalScrutinyDto technicalScrutiny;
	private BidderGtpDto bidderGtp;
	private String isDeviation;
	private String comment;
	private String deviationTypeCode;
	private String textResponse;
	private AttachmentDto attachment;
	
	public Long getGtpScrutinyId() {
		return gtpScrutinyId;
	}
	public void setGtpScrutinyId(Long gtpScrutinyId) {
		this.gtpScrutinyId = gtpScrutinyId;
	}
	public TechnicalScrutinyDto getTechnicalScrutiny() {
		return technicalScrutiny;
	}
	public void setTechnicalScrutiny(TechnicalScrutinyDto technicalScrutiny) {
		this.technicalScrutiny = technicalScrutiny;
	}
	public BidderGtpDto getBidderGtp() {
		return bidderGtp;
	}
	public void setBidderGtp(BidderGtpDto bidderGtp) {
		this.bidderGtp = bidderGtp;
	}
	public String getIsDeviation() {
		return isDeviation;
	}
	public void setIsDeviation(String isDeviation) {
		this.isDeviation = isDeviation;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDeviationTypeCode() {
		return deviationTypeCode;
	}
	public void setDeviationTypeCode(String deviationTypeCode) {
		this.deviationTypeCode = deviationTypeCode;
	}
	public String getTextResponse() {
		return textResponse;
	}
	public void setTextResponse(String textResponse) {
		this.textResponse = textResponse;
	}
	public AttachmentDto getAttachment() {
		return attachment;
	}
	public void setAttachment(AttachmentDto attachment) {
		this.attachment = attachment;
	}
	
	
}
