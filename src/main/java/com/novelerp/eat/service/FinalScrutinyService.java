package com.novelerp.eat.service;

import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.ItemScrutinyDto;
import com.novelerp.eat.dto.ItemScrutinyLineDto;
import com.novelerp.eat.dto.TAHDRDto;

public interface FinalScrutinyService {
	
    public CustomResponseDto confirmCommercialScrutiny(ItemScrutinyDto itemScrutiny);
	
	public CustomResponseDto confirmTechicalScrutiny(ItemScrutinyDto itemScrutiny,ItemBidDto itemBid);

	public   CustomResponseDto getTenderList(Long tahdrId);
	
	public   CustomResponseDto getTenderList(int pageNumber, 
			int pageSize,String searchColumn , String searchValue,String typeCode);
	
	public   CustomResponseDto getItemListBybidderId( Long bidderId);
	
	public   CustomResponseDto saveFinalTechnicalDeviationResponse(ItemScrutinyLineDto itemScrutinyLine);
	
	public   CustomResponseDto saveFinalCommercialDeviationResponse(ItemScrutinyLineDto itemScrutinyLine);
	
	public   CustomResponseDto saveTechnicalFinalDocumentDeviationResponse(ItemScrutinyLineDto itemScrutinyLine);
	
	public   CustomResponseDto saveCommercialFinalDocumentDeviationResponse(ItemScrutinyLineDto itemScrutinyLine);
	
	public   CustomResponseDto confirmFinalTechnicalScrutiny(ItemScrutinyDto itemScrutiny);
	
	public   CustomResponseDto confirmFinalCommercialScrutiny(ItemScrutinyDto itemScrutiny);
	
	public   CustomResponseDto getFinalScrutinyStatus(Long tahdrId);
	
	public   CustomResponseDto confirmFinalScrutiny(TAHDRDto tahdrDto);
	
	public   CustomResponseDto saveAuditorComment(ItemScrutinyLineDto dto);
	
	public   CustomResponseDto confirmAuditorComment(ItemScrutinyDto dto);
	
	public   CustomResponseDto intimidateFinalAuditing(TAHDRDto tahdr);
	
	public   CustomResponseDto notifyAuditor(Long tahdrId);
	
	public boolean sendFinalStatusToBidder(Long tahdrId,String tenderNo) ;
	
	public ItemScrutinyDto getTechnicalScrutinyDeviation(Long itemBidId);
	
	public ItemScrutinyDto getCommercialScrutinyDeviation(Long bidderId);
}
