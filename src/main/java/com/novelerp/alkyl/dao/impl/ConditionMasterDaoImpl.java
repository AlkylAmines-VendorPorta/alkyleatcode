package com.novelerp.alkyl.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.ConditionMasterDao;
import com.novelerp.alkyl.dto.ConditionMasterDto;
import com.novelerp.alkyl.entity.ConditionMaster;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class ConditionMasterDaoImpl extends AbstractJpaDAO<ConditionMaster, ConditionMasterDto> implements ConditionMasterDao {

	
	@PostConstruct
	public void postConstruct(){
		
		setClazz(ConditionMaster.class, ConditionMasterDto.class);
	}
	
	public String getConditionByCode(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM ConditionMaster A ")
				.append(" WHERE A.code = :conditionCode ");
		return jpql.toString();
	}
}
