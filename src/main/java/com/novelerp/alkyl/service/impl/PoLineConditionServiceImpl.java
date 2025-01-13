package com.novelerp.alkyl.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.alkyl.component.ConditionComponent;
import com.novelerp.alkyl.dao.PoLineConditionDao;
import com.novelerp.alkyl.dto.ConditionMasterDto;
import com.novelerp.alkyl.dto.PoLineConditionDto;
import com.novelerp.alkyl.dto.PurchaseOrderLineDto;
import com.novelerp.alkyl.entity.PoLineCondition;
import com.novelerp.alkyl.service.PoLineConditionService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.commons.util.CommonUtil;

@Service
public class PoLineConditionServiceImpl extends AbstractContextServiceImpl<PoLineCondition, PoLineConditionDto> implements PoLineConditionService {

	@Autowired
	private ConditionComponent conditionComponent;
	
	@Autowired
	private PoLineConditionDao polinecondition;
	
	@PostConstruct
	public void init(){
		
		super.init(PoLineConditionServiceImpl.class, polinecondition, PoLineCondition.class, PoLineConditionDto.class);
		setByPassProxy(true);
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<PoLineConditionDto> save(List<PoLineConditionDto> poLineConditionList,PurchaseOrderLineDto poLine){
		if(CommonUtil.isCollectionEmpty(poLineConditionList)){
			return poLineConditionList;
		}
		for(PoLineConditionDto poLineCondition : poLineConditionList){
			ConditionMasterDto condition = conditionComponent.getConditionByCode(poLineCondition.getCondition().getCode());
			poLineCondition.setCondition(condition);
			poLineCondition.setPurchaseOrderLine(poLine);
			poLineCondition = super.save(poLineCondition);
		}
		return poLineConditionList;
	}
}
