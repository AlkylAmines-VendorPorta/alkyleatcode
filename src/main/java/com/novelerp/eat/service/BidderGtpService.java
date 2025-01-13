package com.novelerp.eat.service;

import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.BidderGtpDto;
import com.novelerp.eat.dto.TechnicalBidDto;
import com.novelerp.eat.entity.BidderGtp;

/**
 * @author Aman Sahu
 *
 */
public interface BidderGtpService  extends CommonService<BidderGtp, BidderGtpDto>{

	/**
	 * @param technicalBid
	 * @return
	 */
	public TechnicalBidDto save(TechnicalBidDto technicalBid);


}
