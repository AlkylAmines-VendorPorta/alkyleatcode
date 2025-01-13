package com.novelerp.alkyl.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.alkyl.dao.VendorEnquiryDao;
import com.novelerp.alkyl.dto.EnquiryDto;
import com.novelerp.alkyl.entity.Enquiry;
import com.novelerp.alkyl.service.VendorEnquiryService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
@Service
public class VendorEnquiryServiceImpl extends AbstractContextServiceImpl<Enquiry, EnquiryDto> implements VendorEnquiryService {

	@Autowired
	private VendorEnquiryDao vendorEnquiryDao;
	
	@PostConstruct
	protected void init() {
		// TODO Auto-generated method stub
		super.init(VendorEnquiryServiceImpl.class, vendorEnquiryDao, Enquiry.class, EnquiryDto.class);
		setByPassProxy(true);
	}




}
