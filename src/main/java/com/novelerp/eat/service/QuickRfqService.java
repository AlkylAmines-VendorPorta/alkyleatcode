package com.novelerp.eat.service;

import java.util.List;
import java.util.Map;

import com.novelerp.eat.dto.TAHDRDetailDto;
import com.novelerp.eat.dto.TAHDRDto;

public interface QuickRfqService {
	
	public List<TAHDRDto> getQuickRfqList(Map<String, Object> map, int pageNumber, int pageSize, String searchColumn,
			String searchValue);

	public long getQuickRfqListQueryCount(Map<String, Object> map, String searchColumn, String searchValue);

	public TAHDRDto createQuickRfq(TAHDRDto tahdr);

	public TAHDRDto updateQuickRfq(TAHDRDto tahdr,TAHDRDetailDto newDto,TAHDRDetailDto oldDto);

}
