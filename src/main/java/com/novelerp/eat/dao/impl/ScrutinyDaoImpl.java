package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.ScrutinyDao;
import com.novelerp.eat.dto.ScrutinyDto;
import com.novelerp.eat.entity.Scrutiny;
/**
* @author Aman Sahu
*
*/
@Repository
public class ScrutinyDaoImpl extends AbstractJpaDAO<Scrutiny, ScrutinyDto> implements ScrutinyDao {
	
	@PostConstruct
	public void postConstruct() {
		setClazz(Scrutiny.class, ScrutinyDto.class);
	}
}
