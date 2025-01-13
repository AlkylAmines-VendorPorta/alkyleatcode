package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.BidderStatusDao;
import com.novelerp.eat.dto.BidderStatusDto;
import com.novelerp.eat.entity.BidderStatus;

@Repository
public class BidderStatusDaoImpl extends AbstractJpaDAO<BidderStatus, BidderStatusDto> implements BidderStatusDao {
	
	@PostConstruct
	public void postConstruct() {
		setClazz(BidderStatus.class, BidderStatusDto.class);
	}
}
