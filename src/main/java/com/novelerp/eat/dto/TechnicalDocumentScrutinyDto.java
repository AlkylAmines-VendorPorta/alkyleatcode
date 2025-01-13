package com.novelerp.eat.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

/**
 * 
 * @author Aman Sahu
 *
 */
public class TechnicalDocumentScrutinyDto extends CommonContextDto {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long technicalDocumentScrutinyId;
	private ItemScrutinyDto itemScrutiny;
	private String deviationTypeCode;
	private String deviationStatusCode;
	private String comment;
	
	
	
	public Long getTechnicalDocumentScrutinyId() {
		return technicalDocumentScrutinyId;
	}
	public void setTechnicalDocumentScrutinyId(Long technicalDocumentScrutinyId) {
		this.technicalDocumentScrutinyId = technicalDocumentScrutinyId;
	}
	public ItemScrutinyDto getItemScrutiny() {
		return itemScrutiny;
	}
	public void setItemScrutiny(ItemScrutinyDto itemScrutiny) {
		this.itemScrutiny = itemScrutiny;
	}
	public String getDeviationTypeCode() {
		return deviationTypeCode;
	}
	public void setDeviationTypeCode(String deviationTypeCode) {
		this.deviationTypeCode = deviationTypeCode;
	}
	public String getDeviationStatusCode() {
		return deviationStatusCode;
	}
	public void setDeviationStatusCode(String deviationStatusCode) {
		this.deviationStatusCode = deviationStatusCode;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
	
}
