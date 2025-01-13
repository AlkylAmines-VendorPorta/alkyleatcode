package com.novelerp.eat.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dao.ContractConditionDao;
import com.novelerp.eat.dto.ContractConditionDto;
import com.novelerp.eat.entity.ContractCondition;
import com.novelerp.eat.service.ContractConditionService;
@Service
public class ContractConditionServiceImpl extends AbstractContextServiceImpl<ContractCondition, ContractConditionDto> implements ContractConditionService{

	@Autowired
	private ContractConditionDao contractConditionDao;
	
	@PostConstruct
	public void init(){
		super.init(ContractConditionServiceImpl.class, contractConditionDao, ContractCondition.class, ContractConditionDto.class);
		setByPassProxy(true);
	}
	
}
