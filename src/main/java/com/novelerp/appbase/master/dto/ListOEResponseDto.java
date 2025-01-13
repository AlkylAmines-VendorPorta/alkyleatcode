package com.novelerp.appbase.master.dto;

import java.util.List;

/** 
 * @author ankita
 *for list of Other Eligibity details in Partner Registration
 */
public class ListOEResponseDto {

	private List<PartnerOrgOEDto> oeList;

	public List<PartnerOrgOEDto> getOeList() {
		return oeList;
	}

	public void setOeList(List<PartnerOrgOEDto> oeList) {
		this.oeList = oeList;
	}

	
	
}
