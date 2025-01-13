package com.novelerp.alkyl.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dao.VendorProfileHistoryDao;
import com.novelerp.alkyl.dto.VendorProfileHistoryDto;
import com.novelerp.alkyl.entity.VendorProfileHistory;
import com.novelerp.alkyl.service.AdvanceShipmentNoticeService;
import com.novelerp.alkyl.service.VendorProfileHistoryService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

@Service
public class VendorProfileHistoryServiceImpl extends AbstractContextServiceImpl<VendorProfileHistory, VendorProfileHistoryDto> implements VendorProfileHistoryService{
	
	@Autowired
	private VendorProfileHistoryDao vendorProfileHistoryDao;
	@PostConstruct
	public void init(){
		
		super.init(AdvanceShipmentNoticeService.class, vendorProfileHistoryDao, VendorProfileHistory.class, VendorProfileHistoryDto.class);
	setByPassProxy(true);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveDto(VendorProfileHistoryDto dto) {
		save(dto);
		
	}

}
