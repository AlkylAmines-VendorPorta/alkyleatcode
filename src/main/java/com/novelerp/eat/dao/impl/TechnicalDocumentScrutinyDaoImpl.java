package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.TechnicalDocumentScrutinyDao;
import com.novelerp.eat.dto.TechnicalDocumentScrutinyDto;
import com.novelerp.eat.entity.TechnicalDocumentScrutiny;

/**
 * @author Aman Sahu
 *
 */
@Repository
public class TechnicalDocumentScrutinyDaoImpl extends AbstractJpaDAO<TechnicalDocumentScrutiny, TechnicalDocumentScrutinyDto> implements TechnicalDocumentScrutinyDao {
	
	@PostConstruct
	public void postConstruct() {
		setClazz(TechnicalDocumentScrutiny.class, TechnicalDocumentScrutinyDto.class);
	}
}
