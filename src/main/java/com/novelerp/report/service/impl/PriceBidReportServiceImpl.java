package com.novelerp.report.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.service.BidderService;
import com.novelerp.report.service.PriceBidReportService;

@Service
public class PriceBidReportServiceImpl implements PriceBidReportService{

	private Logger log = LoggerFactory.getLogger(PriceBidReportServiceImpl.class);

	@Autowired
	 @Qualifier("jwtUserContext") private ContextService contextService;
	
	@Autowired
	private BidderService bidderService;
	
	@Override
	public boolean validate(Long bidderId) {
		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("bidderId", bidderId);
		BidderDto bidder =  bidderService.findDto("getBidderPartnerQuery", params);
		BPartnerDto partner = bidder.getPartner();
		BPartnerDto contextPartner =  contextService.getPartner();
		return CommonUtil.isEqual(partner.getbPartnerId(), contextPartner.getbPartnerId());
	}
	

}
