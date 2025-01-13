package com.novelerp.eat.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dao.ContractItemDao;
import com.novelerp.eat.dto.ContractItemDto;
import com.novelerp.eat.entity.ContractItem;
import com.novelerp.eat.service.ContractItemService;

@Service
public class ContractItemServiceImpl extends AbstractContextServiceImpl<ContractItem, ContractItemDto> implements ContractItemService{

	@Autowired
	private ContractItemDao contractItemDao;
	
	@PostConstruct
	public void init() {
		super.init(ContractItemServiceImpl.class, contractItemDao, ContractItem.class, ContractItemDto.class);
		setByPassProxy(true);
	}

}
