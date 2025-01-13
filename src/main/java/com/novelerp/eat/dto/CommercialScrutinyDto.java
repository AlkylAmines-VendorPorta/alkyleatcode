package com.novelerp.eat.dto;

import java.util.Set;

import com.novelerp.appcontext.dto.CommonContextDto;

/**
 * 
 * @author Aman Sahu
 *
 */
public class CommercialScrutinyDto extends CommonContextDto{

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long commercialScrutinyId;
	private ItemScrutinyDto itemScrutiny;
	private String deviationStatus;
	private Set<CommercialScrutinyPointDto> commercialScrutinyPoint;
	
	public Long getCommercialScrutinyId() {
		return commercialScrutinyId;
	}
	public void setCommercialScrutinyId(Long commercialScrutinyId) {
		this.commercialScrutinyId = commercialScrutinyId;
	}
	public ItemScrutinyDto getItemScrutiny() {
		return itemScrutiny;
	}
	public void setItemScrutiny(ItemScrutinyDto itemScrutiny) {
		this.itemScrutiny = itemScrutiny;
	}
	public String getDeviationStatus() {
		return deviationStatus;
	}
	public void setDeviationStatus(String deviationStatus) {
		this.deviationStatus = deviationStatus;
	}
	public Set<CommercialScrutinyPointDto> getCommercialScrutinyPoint() {
		return commercialScrutinyPoint;
	}
	public void setCommercialScrutinyPoint(Set<CommercialScrutinyPointDto> commercialScrutinyPoint) {
		this.commercialScrutinyPoint = commercialScrutinyPoint;
	}
	
	
	
	
	
	
}

