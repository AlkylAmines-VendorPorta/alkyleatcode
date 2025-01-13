package com.novelerp.eat.dto;

import java.util.Set;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.eat.entity.ItemScrutiny;

/**
 * 
 * @author Aman Sahu
 *
 */
public class TechnicalScrutinyDto extends CommonContextDto{

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long technicalScrutinyId;
	private ItemScrutiny itemScrutiny;
	private String deviationStatus;
	private Set<GtpScrutinyDto> gtpScrutinyList;
	
	public Long getTechnicalScrutinyId() {
		return technicalScrutinyId;
	}
	public void setTechnicalScrutinyId(Long technicalScrutinyId) {
		this.technicalScrutinyId = technicalScrutinyId;
	}
	public ItemScrutiny getItemScrutiny() {
		return itemScrutiny;
	}
	public void setItemScrutiny(ItemScrutiny itemScrutiny) {
		this.itemScrutiny = itemScrutiny;
	}
	public String getDeviationStatus() {
		return deviationStatus;
	}
	public void setDeviationStatus(String deviationStatus) {
		this.deviationStatus = deviationStatus;
	}
	public Set<GtpScrutinyDto> getGtpScrutinyList() {
		return gtpScrutinyList;
	}
	public void setGtpScrutinyList(Set<GtpScrutinyDto> gtpScrutinyList) {
		this.gtpScrutinyList = gtpScrutinyList;
	}
	
	
	
	
	
}
