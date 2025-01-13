package com.novelerp.alkyl.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.alkyl.dao.ConditionMasterDao;
import com.novelerp.alkyl.dto.ConditionMasterDto;
import com.novelerp.alkyl.entity.ConditionMaster;
import com.novelerp.alkyl.service.ConditionMasterService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

@Service
public class ConditionMasterServiceImpl extends AbstractContextServiceImpl<ConditionMaster,ConditionMasterDto> implements ConditionMasterService {

	@Autowired
	private ConditionMasterDao conditionmaster;
	
	@PostConstruct
	public void init(){
		super.init(ConditionMasterServiceImpl.class, conditionmaster, ConditionMaster.class, ConditionMasterDto.class);
		setByPassProxy(true);
	}
}
