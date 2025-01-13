package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.TechnicalScrutinyDao;
import com.novelerp.eat.dto.TechnicalScrutinyDto;
import com.novelerp.eat.entity.TechnicalScrutiny;

/**
 * @author Aman Sahu
 *
 */
@Repository
public class TechnicalScrutinyDaoImpl extends AbstractJpaDAO<TechnicalScrutiny, TechnicalScrutinyDto> implements TechnicalScrutinyDao {
	
	@PostConstruct
	public void postConstruct() {
		setClazz(TechnicalScrutiny.class, TechnicalScrutinyDto.class);
	}
}
