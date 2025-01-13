package com.novelerp.eat.service;

import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.BidderStatusDto;
import com.novelerp.eat.entity.BidderStatus;

public interface BidderStatusService extends CommonService<BidderStatus, BidderStatusDto> {

	public boolean saveBidderStatus(BidderDto bidder,String status,String comment);
}
