package com.novelerp.alkyl.component;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.service.BidderService;

@Component
public class QuotationComponent {

	@Autowired
	private BidderService bidderService;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public boolean validateBidEndDateById(Long bidderId){
		
		Map<String, Object> params = AbstractContextServiceImpl.getParamMap("bidderId", bidderId);
		params.put("currentDate",new Date());
		
		BidderDto dto=bidderService.findDto("validateBidEndDateById",params);
		
		if(dto==null) return false; else return true;
		
	}
	
}
