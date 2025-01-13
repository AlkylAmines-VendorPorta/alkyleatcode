package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.CommercialScrutinyPointDao;
import com.novelerp.eat.dto.CommercialScrutinyPointDto;
import com.novelerp.eat.entity.CommercialScrutinyPoint;

/**
 * @author Aman Sahu
 *
 */
@Repository
public class CommercialScrutinyPointDaoImpl extends AbstractJpaDAO<CommercialScrutinyPoint, CommercialScrutinyPointDto> implements CommercialScrutinyPointDao {
	
	@PostConstruct
	public void postConstruct() {
		setClazz(CommercialScrutinyPoint.class, CommercialScrutinyPointDto.class);
	}
}
