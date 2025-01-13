package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.GtpScrutinyDao;
import com.novelerp.eat.dto.GtpScrutinyDto;
import com.novelerp.eat.entity.GtpScrutiny;

/**
 * @author Aman Sahu
 *
 */
@Repository
public class GtpScrutinyDaoImpl extends AbstractJpaDAO<GtpScrutiny, GtpScrutinyDto> implements GtpScrutinyDao {
	
	@PostConstruct
	public void postConstruct() {
		setClazz(GtpScrutiny.class, GtpScrutinyDto.class);
	}
}
