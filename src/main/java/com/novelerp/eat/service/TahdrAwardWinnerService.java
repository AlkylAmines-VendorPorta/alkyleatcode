package com.novelerp.eat.service;

import java.util.List;
import java.util.Map;

import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.RatingWeightageDto;
import com.novelerp.eat.dto.WinnerSelectionDto;
import com.novelerp.eat.entity.WinnerSelection;

public interface TahdrAwardWinnerService extends CommonService<WinnerSelection, WinnerSelectionDto>  {
	

	public CustomResponseDto calculateRating(WinnerSelectionDto dto, RatingWeightageDto ratingWeightageDto);
	public int updateRating(CustomResponseDto customResponseDto, WinnerSelectionDto winnerSelectionDto, BidderDto bidderDto,
			BPartnerDto bPartnerDto);
	List<WinnerSelectionDto> getTahdrWithDetailsForCrateContract(Map<String, Object> params, int pageNumber,
			int pageSize, String searchColumn, String searchValue);
	List<BidderDto> getWinnerBidderListByTahdrId(Long tahdrId);
	CustomResponseDto getBidderContractDetails(Long bidderId, Long tahdrId);

}
