package com.novelerp.eat.service;

import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.TAHDRDetailDto;

public interface TAHDRSchedulingService {

	public   CustomResponseDto getMyTenderDetailsForScheduling(Long tahdrId);
	
	public   CustomResponseDto getTenderDetailsForScheduling(String tenderTypeCode);
	
	public   CustomResponseDto getTenderList(int pageNumber, 
			int pageSize,String searchColumn ,String searchValue,String typeCode);
	
	public   CustomResponseDto updateTahdrScheduling(TAHDRDetailDto tahdrDetail);
	
	
}
