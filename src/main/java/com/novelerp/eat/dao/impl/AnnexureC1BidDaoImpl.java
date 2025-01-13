/**
 * @author Ankush
 */
package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.AnnexureC1BidDao;
import com.novelerp.eat.dto.AnnexureC1BidDto;
import com.novelerp.eat.entity.AnnexureC1Bid;

@Repository
public class AnnexureC1BidDaoImpl extends AbstractJpaDAO<AnnexureC1Bid, AnnexureC1BidDto> implements AnnexureC1BidDao {

	@PostConstruct
	void init(){
		setClazz(AnnexureC1Bid.class, AnnexureC1BidDto.class);
	}
	
}
