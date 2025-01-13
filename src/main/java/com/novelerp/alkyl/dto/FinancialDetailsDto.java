package com.novelerp.alkyl.dto;

import java.util.List;

import com.novelerp.appcontext.dto.CommonContextDto;

public class FinancialDetailsDto extends CommonContextDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<PartnerFinancialDetailDto> financialDetailsList;

	public List<PartnerFinancialDetailDto> getFinancialDetailsList() {
		return financialDetailsList;
	}

	public void setFinancialDetailsList(List<PartnerFinancialDetailDto> financialDetailsList) {
		this.financialDetailsList = financialDetailsList;
	}
}
