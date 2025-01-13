package com.novelerp.eat.service;

import java.util.List;

import com.novelerp.core.dto.BidderParticipationDto;
import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.entity.TempPriceBid;

public interface TempPriceBidService extends CommonService<TempPriceBid, PriceBidDto>  {

	public List<BidderParticipationDto> getBidderParticipation(Long tahdrId, Long materialId);
	
	public List<PriceBidDto> getBidListBySessionId(String userSessionId);

}
