package com.novelerp.appbase.master.service;

import com.novelerp.alkyl.dto.EnquiryDto;
import com.novelerp.alkyl.entity.Enquiry;
import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.service.CommonService;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.entity.Bidder;

public interface EnquiryService extends CommonService<Bidder, BidderDto> {

	public BidderDto createEnquiry(BidderDto enquiry);

	public CustomResponseDto createEnquiries(EnquiryDto enquiries);

	public void updateEnquiries(EnquiryDto enquiries);

	public void sendMail(BidderDto enquiry); 
	
}
