package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.ContractConditionDao;
import com.novelerp.eat.dto.ContractConditionDto;
import com.novelerp.eat.entity.ContractCondition;

@Repository
public class ContractConditionDaoImpl extends AbstractJpaDAO<ContractCondition, ContractConditionDto>
		implements ContractConditionDao {

	@PostConstruct
	public void postConstruct() {
		setClazz(ContractCondition.class, ContractConditionDto.class);
	}
	
	public String getContractCndnByHeaderId(){
		StringBuilder query = new  StringBuilder("SELECT ci FROM ContractCondition ci ");
		query.append(" LEFT JOIN FETCH ci.winnerSelection ws");
		query.append(" WHERE ci.contractHeader.contractHeaderId = :headerId ");
		return query.toString();
	}

}
