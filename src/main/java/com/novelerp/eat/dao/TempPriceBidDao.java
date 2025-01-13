package com.novelerp.eat.dao;

import java.util.List;

import com.novelerp.core.dao.CommonDao;
import com.novelerp.core.dto.BidderParticipationDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.entity.TempPriceBid;

public interface TempPriceBidDao extends CommonDao<TempPriceBid, PriceBidDto>{

	List<BidderParticipationDto> getBidderInvitation(Long tahdrId, Long materialId);

	/*String getBidderParticipation(Long tahdrId, Long materialId);*/

}
