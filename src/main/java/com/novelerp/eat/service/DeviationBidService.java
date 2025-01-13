package com.novelerp.eat.service;

import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.ItemScrutinyDto;
import com.novelerp.eat.dto.ItemScrutinyLineDto;

public interface DeviationBidService {
	
	public CustomResponseDto confirmCommercialDeviationByBidder(ItemScrutinyDto itemScrutiny);
	
	public CustomResponseDto confirmTechicalDeviationByBidder(ItemScrutinyDto itemScrutiny);
	
    public CustomResponseDto saveCommercialDeviationByBidder(ItemScrutinyLineDto itemScrutinyLine);
	
	public CustomResponseDto saveTechicalDeviationByBidder(ItemScrutinyLineDto itemScrutinyLine);
	
	public boolean revokeDeviationConfirmation(Long itemScrutinyId);

}
