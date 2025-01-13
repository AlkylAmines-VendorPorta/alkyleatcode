package com.novelerp.eat.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dao.ContractHeaderDao;
import com.novelerp.eat.dto.ContractHeaderDto;
import com.novelerp.eat.entity.ContractHeader;
import com.novelerp.eat.service.ContractHeaderService;

@Service
public class ContractHeaderServiceImpl extends AbstractContextServiceImpl<ContractHeader, ContractHeaderDto> implements ContractHeaderService{

	@Autowired
	private ContractHeaderDao contractHeaderDao;

	@PostConstruct
	public void init() {
		super.init(ContractHeaderServiceImpl.class, contractHeaderDao, ContractHeader.class, ContractHeaderDto.class);
		setByPassProxy(true);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateHeader(Long contractHeaderId){
		 Map<String , Object> param = new HashMap<String, Object>();
		 param.put("isContractCreated", "Y");
		 updateByJpql(param, "contractHeaderId", contractHeaderId);
	}

}
