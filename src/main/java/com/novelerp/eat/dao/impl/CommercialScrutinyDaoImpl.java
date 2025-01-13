package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.CommercialScrutinyDao;
import com.novelerp.eat.dto.CommercialScrutinyDto;
import com.novelerp.eat.entity.CommercialScrutiny;

/**
 * @author Aman Sahu
 *
 */
@Repository
public class CommercialScrutinyDaoImpl extends AbstractJpaDAO<CommercialScrutiny, CommercialScrutinyDto> implements CommercialScrutinyDao {
	
	@PostConstruct
	public void postConstruct() {
		setClazz(CommercialScrutiny.class, CommercialScrutinyDto.class);
	}
}
