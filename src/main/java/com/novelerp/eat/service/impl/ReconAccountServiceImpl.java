package com.novelerp.eat.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dao.ReconAccountDao;
import com.novelerp.eat.dto.ReconAccountDto;
import com.novelerp.eat.entity.ReconAccount;
import com.novelerp.eat.service.ReconAccountService;

@Service
public class ReconAccountServiceImpl extends AbstractContextServiceImpl<ReconAccount, ReconAccountDto> implements ReconAccountService{

	@Autowired
	private ReconAccountDao reconAccountDao;
	
	@PostConstruct
	void init(){
		super.init(ReconAccountServiceImpl.class, reconAccountDao, ReconAccount.class, ReconAccountDto.class);
		setByPassProxy(true);
	}
}
