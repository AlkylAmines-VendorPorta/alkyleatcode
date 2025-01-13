package com.novelerp.appbase.master.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.SendRequestToParticipantDao;
import com.novelerp.appbase.master.dto.BPartnerMapDto;
import com.novelerp.appbase.master.entity.BPartnerMap;
import com.novelerp.appbase.master.service.SendRequestToParticipantService;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
@Service
public class SendRequestToParticipantServiceImpl  extends AbstractContextServiceImpl<BPartnerMap, BPartnerMapDto> implements SendRequestToParticipantService{

	@Autowired
	private SendRequestToParticipantDao SendRequestToParticipantDao;
	@PostConstruct
	private void init() {
		super.init(SendRequestToParticipantServiceImpl.class,SendRequestToParticipantDao , BPartnerMap.class, BPartnerMapDto.class);
		setByPassProxy(true);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public BPartnerMapDto saveRequestDetails(BPartnerDto bpartnerdto, UserDto userdto) {
		BPartnerMapDto bPartnerMapDto = new BPartnerMapDto();
		bPartnerMapDto.setRequestPartnerId(bpartnerdto);
		bPartnerMapDto.setTargetPartnerId(userdto);
		bPartnerMapDto.setIsRequestSend("Y");
		bPartnerMapDto.setIsRequestApproved("N");
		
		bPartnerMapDto=save(bPartnerMapDto);
		
		// TODO Auto-generated method stub
		return bPartnerMapDto;
	}

}
