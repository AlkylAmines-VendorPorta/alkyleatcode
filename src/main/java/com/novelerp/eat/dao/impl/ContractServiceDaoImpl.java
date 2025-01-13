package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.ContractServiceDao;
import com.novelerp.eat.dto.ContractServiceDto;
import com.novelerp.eat.entity.ContractService;

@Repository
public class ContractServiceDaoImpl extends AbstractJpaDAO<ContractService, ContractServiceDto>
		implements ContractServiceDao {

	@PostConstruct
	public void postContruct() {
		setClazz(ContractService.class, ContractServiceDto.class);
	}
	
	public String getContractSrvcByHeaderId(){
		StringBuilder query = new  StringBuilder("SELECT ci FROM ContractService ci ");
		query.append(" LEFT JOIN FETCH ci.winnerSelection ws");
		query.append(" WHERE ci.contractHeader.contractHeaderId = :headerId ");
		return query.toString();
	}

}
