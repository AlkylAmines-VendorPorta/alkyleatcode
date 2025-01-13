package com.novelerp.appbase.master.service;

import com.novelerp.appbase.master.dto.PartnerTradingItemDto;
import com.novelerp.appbase.master.entity.PartnerTradingItem;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.CommonService;

/**
 * 
 * @author Aman Sahu
 *
 */
public interface PartnerTradingItemService extends CommonService<PartnerTradingItem, PartnerTradingItemDto>{
	public ResponseDto updateItemsApproval(PartnerTradingItemDto dto);
}
