package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.ScrutinyPointDao;
import com.novelerp.appbase.master.dto.ScrutinyPointDto;
import com.novelerp.appbase.master.entity.ScrutinyPoint;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * 
 * @author Aman
 */
@Repository
public class ScrutinyPointDaoImpl extends AbstractJpaDAO<ScrutinyPoint, ScrutinyPointDto> implements ScrutinyPointDao{

	@PostConstruct
	private void init() {
		setClazz(ScrutinyPoint.class, ScrutinyPointDto.class);
	}
	
public String getOrderedScrutinyPointList(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" SELECT sp FROM ScrutinyPoint sp ");
		jpql.append(" WHERE sp.isActive='Y' ORDER BY sp.serialNo ");
		return jpql.toString();
	}
}
