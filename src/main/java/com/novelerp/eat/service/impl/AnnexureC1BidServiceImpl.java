/**
 * @author Ankush
 */
package com.novelerp.eat.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dao.AnnexureC1BidDao;
import com.novelerp.eat.dto.AnnexureC1BidDto;
import com.novelerp.eat.entity.AnnexureC1Bid;
import com.novelerp.eat.service.AnnexureC1BidService;

@Service
public class AnnexureC1BidServiceImpl extends AbstractContextServiceImpl<AnnexureC1Bid, AnnexureC1BidDto>
		implements AnnexureC1BidService {

	@Autowired
	private AnnexureC1BidDao annexureC1BidDao;
	
	@PostConstruct
	void init(){
		super.init(AnnexureC1BidServiceImpl.class, annexureC1BidDao, AnnexureC1Bid.class, AnnexureC1BidDto.class);
		setByPassProxy(true);
	}
	
}
