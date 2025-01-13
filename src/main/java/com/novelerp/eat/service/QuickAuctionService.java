package com.novelerp.eat.service;

import java.util.List;
import java.util.Map;

import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.entity.TAHDR;

public interface QuickAuctionService extends CommonService<TAHDR, TAHDRDto> {

	public List<TAHDRDto> getQuickAuctionList(Map<String, Object> map, int pageNumber, int pageSize, String searchColumn,
			String searchValue);

	public long getQuickAuctionListQueryCount(Map<String, Object> map, String searchColumn, String searchValue);

	public TAHDRDto createQuickAuction(TAHDRDto tahdr);

	public TAHDRDto updateQuickAuction(TAHDRDto tahdr,TAHDRDetailDto oldDto);

}
