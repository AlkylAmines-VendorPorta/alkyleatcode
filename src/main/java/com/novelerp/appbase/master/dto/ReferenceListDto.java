/**
 * @author Ankush
 */
package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class ReferenceListDto extends CommonContextDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long referenceListId;
	private String description;
	private String referenceCode;
	private String code;
	private ReferenceDto reference;
	private String name;
	private String actionName;
	private String auctionUrl;
	private String tenderUrl;
	
	public Long getReferenceListId() {
		return referenceListId;
	}
	public void setReferenceListId(Long referenceListId) {
		this.referenceListId = referenceListId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReferenceCode() {
		return referenceCode;
	}
	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ReferenceDto getReference() {
		return reference;
	}
	public void setReference(ReferenceDto reference) {
		this.reference = reference;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getAuctionUrl() {
		return auctionUrl;
	}
	public void setAuctionUrl(String auctionUrl) {
		this.auctionUrl = auctionUrl;
	}
	public String getTenderUrl() {
		return tenderUrl;
	}
	public void setTenderUrl(String tenderUrl) {
		this.tenderUrl = tenderUrl;
	}
	
}