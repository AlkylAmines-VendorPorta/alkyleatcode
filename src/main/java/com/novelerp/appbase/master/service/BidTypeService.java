package com.novelerp.appbase.master.service;

import java.util.List;

import com.novelerp.appbase.master.dto.BidTypeDto;
import com.novelerp.appbase.master.entity.BidType;
import com.novelerp.core.service.CommonService;

/**
 *  @author Aman
  */
public interface BidTypeService extends CommonService<BidType, BidTypeDto> {

	public List<BidTypeDto> getBidTypeList();
	public BidTypeDto getBidType(Long id);
	/*public ResponseDto addBidType(BidTypeDto dto);
	public ResponseDto editBidType(BidTypeDto dto);
	public ResponseDto deleteBidType(Long id);*/
}
