package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.ContractItemDao;
import com.novelerp.eat.dto.ContractItemDto;
import com.novelerp.eat.entity.ContractItem;

@Repository
public class ContractItemDaoImpl extends AbstractJpaDAO<ContractItem, ContractItemDto> implements ContractItemDao {

	@PostConstruct
	public void postConstruct() {
		setClazz(ContractItem.class, ContractItemDto.class);
	}
	
	public String getContractItemsByHeaderId(){
		StringBuilder query = new  StringBuilder("SELECT ci FROM ContractItem ci ");
		query.append(" LEFT JOIN FETCH ci.winnerSelection ws");
		query.append(" WHERE ci.contractHeader.contractHeaderId = :headerId ");
		return query.toString();
	}

}
