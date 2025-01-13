package com.novelerp.eat.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dao.ContractServiceDao;
import com.novelerp.eat.dto.ContractServiceDto;
import com.novelerp.eat.entity.ContractService;
import com.novelerp.eat.service.ContractServiceService;

@Service
public class ContractServiceServiceImpl extends AbstractContextServiceImpl<ContractService, ContractServiceDto> implements ContractServiceService{

	@Autowired
	private ContractServiceDao contractServiceDao;

	@PostConstruct
	public void init() {
		super.init(ContractServiceServiceImpl.class, contractServiceDao, ContractService.class,
				ContractServiceDto.class);
		setByPassProxy(true);
	}

}
