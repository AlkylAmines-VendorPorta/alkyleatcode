package com.novelerp.appbase.master.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.PartnerTradingItemDao;
import com.novelerp.appbase.master.dto.PartnerTradingItemDto;
import com.novelerp.appbase.master.entity.PartnerTradingItem;
import com.novelerp.appbase.master.service.PartnerTradingItemService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.dto.ResponseDto;

/**
 * 
 * @author Aman Sahu
 *
 */
@Service
public class PartnerTradingItemServiceImpl extends AbstractContextServiceImpl<PartnerTradingItem, PartnerTradingItemDto> implements PartnerTradingItemService{

	@Autowired
	private PartnerTradingItemDao  partnerTradingItemDao;
	
	@PostConstruct
	public void init(){
		super.init(PartnerTradingItemServiceImpl.class, partnerTradingItemDao, PartnerTradingItem.class, PartnerTradingItemDto.class);
		setByPassProxy(true);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerTradingItemDto save(PartnerTradingItemDto dto) {
		PartnerTradingItemDto partnerTradingItemDto = super.save(dto);
		return partnerTradingItemDto;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PartnerTradingItemDto updateDto(PartnerTradingItemDto dto) {
		PartnerTradingItemDto partnerTradingItemDto = super.updateDto(dto);
		return partnerTradingItemDto;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ResponseDto updateItemsApproval(PartnerTradingItemDto dto) {
	  return null;
		
	}

}
