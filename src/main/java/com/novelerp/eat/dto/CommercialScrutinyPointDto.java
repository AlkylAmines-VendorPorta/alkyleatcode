package com.novelerp.eat.dto;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.dto.ScrutinyPointDto;
import com.novelerp.appcontext.dto.CommonContextDto;

/**
 * 
 * @author Aman Sahu
 *
 */
public class CommercialScrutinyPointDto extends CommonContextDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long commercialScrutinyPointId;
	private CommercialScrutinyDto commercialScrutiny;
	private ScrutinyPointDto scrutinyPoint;
	private String deviationTypeCode;
	private String deviationStatus;
	private String comment;
	private String textResponse;
	private AttachmentDto attachment;
	
	public Long getCommercialScrutinyPointId() {
		return commercialScrutinyPointId;
	}
	public void setCommercialScrutinyPointId(Long commercialScrutinyPointId) {
		this.commercialScrutinyPointId = commercialScrutinyPointId;
	}
	public CommercialScrutinyDto getCommercialScrutiny() {
		return commercialScrutiny;
	}
	public void setCommercialScrutiny(CommercialScrutinyDto commercialScrutiny) {
		this.commercialScrutiny = commercialScrutiny;
	}
	public ScrutinyPointDto getScrutinyPoint() {
		return scrutinyPoint;
	}
	public void setScrutinyPoint(ScrutinyPointDto scrutinyPoint) {
		this.scrutinyPoint = scrutinyPoint;
	}
	public String getDeviationTypeCode() {
		return deviationTypeCode;
	}
	public void setDeviationTypeCode(String deviationTypeCode) {
		this.deviationTypeCode = deviationTypeCode;
	}
	public String getDeviationStatus() {
		return deviationStatus;
	}
	public void setDeviationStatus(String deviationStatus) {
		this.deviationStatus = deviationStatus;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
