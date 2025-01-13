package com.novelerp.alkyl.dao;

import java.util.List;

import com.novelerp.alkyl.dto.EnquiryDto;
import com.novelerp.alkyl.entity.Enquiry;
import com.novelerp.core.dao.CommonDao;
import com.novelerp.eat.dto.BidderDto;

public interface VendorEnquiryDao extends CommonDao<Enquiry, EnquiryDto>{
	//public String getNewEnqNo();

	

	public String getNewEnqNo(String Doctype);

//	public String getNewEnqNo(List<BidderDto> itemPRListDoctype);


}
