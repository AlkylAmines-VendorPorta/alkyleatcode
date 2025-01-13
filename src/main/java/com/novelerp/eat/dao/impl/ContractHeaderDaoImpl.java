package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.text.StrBuilder;
import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.ContractHeaderDao;
import com.novelerp.eat.dto.ContractHeaderDto;
import com.novelerp.eat.entity.ContractHeader;

@Repository
public class ContractHeaderDaoImpl extends AbstractJpaDAO<ContractHeader, ContractHeaderDto> implements ContractHeaderDao{

	@PostConstruct
	public void postConstr(){
		setClazz(ContractHeader.class, ContractHeaderDto.class);
	}
	
	
	public String getContractHeader(){
		String jpql = "SELECT ch FROM ContractHeader ch WHERE ch.bidder.bidderId = :bidderId AND ch.tahdr.tahdrId = :tahdrId";
		return jpql;
	}
	
	public String getHeaderDetails(){
		StrBuilder jpql = new StrBuilder("SELECT ch FROM ContractHeader ch ");
		jpql.append(" JOIN FETCH ch.bidder bidder ");
		jpql.append(" JOIN FETCH ch.bidder bidder ");
		jpql.append(" JOIN FETCH bidder.partner partner ");
		jpql.append(" WHERE ch.contractHeaderId = :headerId");
		return jpql.toString();
	}
	
}
