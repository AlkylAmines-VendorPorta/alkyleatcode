package com.novelerp.appcontext.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appcontext.dao.QueryMasterDao;
import com.novelerp.appcontext.dto.QueryMasterDto;
import com.novelerp.appcontext.entity.QueryMaster;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
@Repository
public class QueryMasterDaoImpl extends AbstractJpaDAO<QueryMaster, QueryMasterDto> implements QueryMasterDao{
	
	@PostConstruct
	public void postConstruct() {
		setClazz(QueryMaster.class, QueryMasterDto.class);
	}
}
