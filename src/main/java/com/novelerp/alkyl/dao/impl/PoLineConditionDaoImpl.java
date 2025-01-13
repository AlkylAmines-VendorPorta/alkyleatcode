package com.novelerp.alkyl.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.PoLineConditionDao;
import com.novelerp.alkyl.dto.PoLineConditionDto;
import com.novelerp.alkyl.entity.PoLineCondition;
import com.novelerp.core.dao.impl.AbstractJpaDAO;


@Repository
public class PoLineConditionDaoImpl extends AbstractJpaDAO<PoLineCondition, PoLineConditionDto> implements PoLineConditionDao {

	
	@PostConstruct
	public void postConstruct(){
		
		setClazz(PoLineCondition.class, PoLineConditionDto.class);
		
	}
	
	public String getPOLineConditionsByLineId(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM PoLineCondition A ")
				.append(" INNER JOIN FETCH A.purchaseOrderLine B ")
				.append(" INNER JOIN FETCH A.condition C ")
				.append(" WHERE B.purchaseOrderLineId = :poLineId");
		return jpql.toString();
	}
}
