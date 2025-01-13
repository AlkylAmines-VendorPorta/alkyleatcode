package com.novelerp.alkyl.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.dto.ConditionMasterDto;
import com.novelerp.alkyl.service.ConditionMasterService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;

@Component
public class ConditionComponent {

	@Autowired
	private ConditionMasterService conditionService;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=true)
	public ConditionMasterDto getConditionByCode(String conditionCode){
		return conditionService.findDto("getConditionByCode", AbstractContextServiceImpl.getParamMap("conditionCode", conditionCode));
	} 
	
}
