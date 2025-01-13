package com.novelerp.eat.service;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.eat.dto.TAHDRDto;

public interface PreBidMeetingService {
	
	public CustomResponseDto getTenderListForPreBidMeeting(@PathVariable("typeCode") String typeCode) ;
	
	public CustomResponseDto getTenderList(int pageNumber, int pageSize,String searchColumn , 
			String searchValue, String typeCode);
	
	public CustomResponseDto uploadMOM(@ModelAttribute("tahdr")TAHDRDto tahdr);
	
	

}
