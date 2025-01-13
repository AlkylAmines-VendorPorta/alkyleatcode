package com.novelerp.eat.dto;

import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.dto.ScrutinyPointDto;
import com.novelerp.appcontext.dto.CommonContextDto;

/**
 * 
 * @author Aman Sahu
 *
 */
public class ItemScrutinyLineDto extends CommonContextDto{

	
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long itemScrutinyLineId;
	private ItemScrutinyDto itemScrutiny;
	private TAHDRMaterialDto tahdrMaterial;
	private BidderGtpDto bidderGtp;
	private String isDeviation;
	private String deviationType;
	private String deviationComment;
	private BidderSectionDocDto bidderSectionDoc;
	private ScrutinyPointDto scrutinyPoint;
	private String finalScrutinyComment;
	private String finalScrutinyStatus;
	private String deviationBidComment;
	private String textResponse;
	private AttachmentDto fileResponse;
	private String isDeviationSubmitted;
	private String isFinalScrutinySubmitted;
	private String auditorStatus;
	private String auditorComment;
	private String finalAuditorStatus;
	private String finalAuditorComment;
	
	public Long getItemScrutinyLineId() {
		return itemScrutinyLineId;
	}
	public void setItemScrutinyLineId(Long itemScrutinyLineId) {
		this.itemScrutinyLineId = itemScrutinyLineId;
	}
	public ItemScrutinyDto getItemScrutiny() {
		return itemScrutiny;
	}
	public void setItemScrutiny(ItemScrutinyDto itemScrutiny) {
		this.itemScrutiny = itemScrutiny;
	}
	public TAHDRMaterialDto getTahdrMaterial() {
		return tahdrMaterial;
	}
	public void setTahdrMaterial(TAHDRMaterialDto tahdrMaterial) {
		this.tahdrMaterial = tahdrMaterial;
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
	public String getDeviationType() {
		return deviationType;
	}
	public void setDeviationType(String deviationType) {
		this.deviationType = deviationType;
	}
	public String getDeviationComment() {
		return deviationComment;
	}
	public void setDeviationComment(String deviationComment) {
		this.deviationComment = deviationComment;
	}
	public BidderSectionDocDto getBidderSectionDoc() {
		return bidderSectionDoc;
	}
	public void setBidderSectionDoc(BidderSectionDocDto bidderSectionDoc) {
		this.bidderSectionDoc = bidderSectionDoc;
	}
	public ScrutinyPointDto getScrutinyPoint() {
		return scrutinyPoint;
	}
	public void setScrutinyPoint(ScrutinyPointDto scrutinyPoint) {
		this.scrutinyPoint = scrutinyPoint;
	}
	public String getDeviationBidComment() {
		return deviationBidComment;
	}
	public void setDeviationBidComment(String deviationBidComment) {
		this.deviationBidComment = deviationBidComment;
	}
	public String getTextResponse() {
		return textResponse;
	}
	public void setTextResponse(String textResponse) {
		this.textResponse = textResponse;
	}
	public AttachmentDto getFileResponse() {
		return fileResponse;
	}
	public void setFileResponse(AttachmentDto fileResponse) {
		this.fileResponse = fileResponse;
	}
	public String getFinalScrutinyComment() {
		return finalScrutinyComment;
	}
	public void setFinalScrutinyComment(String finalScrutinyComment) {
		this.finalScrutinyComment = finalScrutinyComment;
	}
	public String getFinalScrutinyStatus() {
		return finalScrutinyStatus;
	}
	public void setFinalScrutinyStatus(String finalScrutinyStatus) {
		this.finalScrutinyStatus = finalScrutinyStatus;
	}
	public String getIsDeviationSubmitted() {
		return isDeviationSubmitted;
	}
	public void setIsDeviationSubmitted(String isDeviationSubmitted) {
		this.isDeviationSubmitted = isDeviationSubmitted;
	}
	public String getIsFinalScrutinySubmitted() {
		return isFinalScrutinySubmitted;
	}
	public void setIsFinalScrutinySubmitted(String isFinalScrutinySubmitted) {
		this.isFinalScrutinySubmitted = isFinalScrutinySubmitted;
	}
	public String getAuditorStatus() {
		return auditorStatus;
	}
	public void setAuditorStatus(String auditorStatus) {
		this.auditorStatus = auditorStatus;
	}
	public String getAuditorComment() {
		return auditorComment;
	}
	public void setAuditorComment(String auditorComment) {
		this.auditorComment = auditorComment;
	}
	public String getFinalAuditorStatus() {
		return finalAuditorStatus;
	}
	public void setFinalAuditorStatus(String finalAuditorStatus) {
		this.finalAuditorStatus = finalAuditorStatus;
	}
	public String getFinalAuditorComment() {
		return finalAuditorComment;
	}
	public void setFinalAuditorComment(String finalAuditorComment) {
		this.finalAuditorComment = finalAuditorComment;
	}
	
	
}
