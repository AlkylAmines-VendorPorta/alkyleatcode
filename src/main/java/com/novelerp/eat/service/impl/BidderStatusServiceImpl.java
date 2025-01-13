package com.novelerp.eat.service.impl;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dao.BidderStatusDao;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.BidderStatusDto;
import com.novelerp.eat.entity.BidderStatus;
import com.novelerp.eat.service.BidderStatusService;

@Service
public class BidderStatusServiceImpl extends AbstractContextServiceImpl<BidderStatus, BidderStatusDto> implements BidderStatusService {

	protected Logger log;
	
	@Autowired
	private BidderStatusDao bidderStatusDao;
	
	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@PostConstruct
	private void init(){
		super.init(BidderStatusServiceImpl.class, bidderStatusDao, BidderStatus.class, BidderStatusDto.class);
		setByPassProxy(true);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean saveBidderStatus(BidderDto bidder,String status,String comment)
	{
		BPartnerDto partner=contextService.getPartner();
		BidderStatusDto bidderStatus=new BidderStatusDto();
		bidderStatus.setBidder(bidder);
		bidderStatus.setScrutinyComment(comment);
		bidderStatus.setStatus(status);
		bidderStatus.setPartner(partner);
		return update(bidderStatus);
		
	}
	
}
