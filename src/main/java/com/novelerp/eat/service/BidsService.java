package com.novelerp.eat.service;

import java.util.List;
import java.util.Map;

import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.CommercialBidDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.dto.ScrutinyFileDto;
import com.novelerp.eat.dto.TechnicalBidDto;

public interface BidsService {
	
	public   CustomResponseDto getTenderList(int pageNumber, 
			int pageSize,String searchColumn ,String searchValue,String typeCode);
	
	public CustomResponseDto getBidderList(Long tahdrId,Long tahdrMaterialId);
	
	public CustomResponseDto getBidderBids(Long bidderId,Long tahdrMaterialId);
	
	public TechnicalBidDto getTechnicalBid(Map<String, Object> technicalParams);
	
	public CommercialBidDto getCommercialBid(Map<String, Object> commercialParams);
	
	public PriceBidDto getPriceBid(Map<String, Object> technicalParams);
	
	public List<PriceBidDto> getTempPriceBid(Map<String, Object> technicalParams);
	
	public ScrutinyFileDto getFinalTechnicalScrutinyBid(Map<String, Object> technicalParams);
	
    public ScrutinyFileDto getPreTechnicalScrutinyBid(Map<String, Object> technicalParams);
	
	public ScrutinyFileDto getFinalCommercialScrutinyBid(Map<String, Object> commercialParams);
	
	public ScrutinyFileDto getPreCommercialScrutinyBid(Map<String, Object> commercialParams);

}
