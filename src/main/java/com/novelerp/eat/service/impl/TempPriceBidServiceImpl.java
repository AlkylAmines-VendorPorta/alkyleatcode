package com.novelerp.eat.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.BidderParticipationDto;
import com.novelerp.eat.dao.TempPriceBidDao;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.entity.TempPriceBid;
import com.novelerp.eat.service.TempPriceBidService;

@Service
public class TempPriceBidServiceImpl extends AbstractContextServiceImpl<TempPriceBid, PriceBidDto> implements TempPriceBidService{
	
	@Autowired
	private TempPriceBidDao  tempPriceBidDao;
	
	@PostConstruct
	private void init() {
		super.init(TempPriceBidServiceImpl.class, tempPriceBidDao, TempPriceBid.class, PriceBidDto.class);
		setByPassProxy(true);
	}

	@Override
	public List<BidderParticipationDto> getBidderParticipation(Long tahdrId, Long materialId) {
		
			return tempPriceBidDao.getBidderInvitation(tahdrId,materialId);
	}
	
	@Override
	public List<PriceBidDto> getBidListBySessionId(String sessionString) {
		List<PriceBidDto> priceBidList=null;
		if(sessionString!=null){
			priceBidList=findDtos("getBidListBySessionString", AbstractContextServiceImpl.getParamMap("sessionString", sessionString)) ;
		}
		return priceBidList;
	}

}
