package com.novelerp.appcontext.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.novelerp.appcontext.dao.DistrictDao;
import com.novelerp.appcontext.dto.DistrictDto;
import com.novelerp.appcontext.entity.District;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class DistrictDaoImpl extends AbstractJpaDAO<District, DistrictDto> implements DistrictDao {
	private static final String ENTITY_NAME="c_district";

	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	public void init() {
	 setClazz(District.class, DistrictDto.class);
	}
	
	public String getDistrictQuery(){
		StringBuilder jpql =  new StringBuilder();
		jpql.append("SELECT d FROM District d ")
		.append(" ORDER BY d.name");
		return jpql.toString();
	}
	public String getDistrictByState(){
		StringBuilder jpql =  new StringBuilder();
		jpql.append("SELECT d FROM District d ")
		.append(" where d.isActive='Y' AND d.region.regionId=:stateId  ORDER BY d.name");
		return jpql.toString();
	}
}
