package com.novelerp.appbase.master.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.CityDao;
import com.novelerp.appbase.master.dto.CityDto;
import com.novelerp.appbase.master.entity.City;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * 
 * @author vivek.birdi
 *
 */
@Repository 
public class CityDaoImpl  extends AbstractJpaDAO<City, CityDto> implements CityDao{
	private static final String ENTITY_NAME="C_CITY";
	@PostConstruct
	private void init() {
		setClazz(City.class, CityDto.class);
	}
	
	@Override
	public List<City> findAll() {
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager();
		return em.createQuery(jpql.toString(),City.class).getResultList();
	}
	
	public StringBuilder getBasicQuery(){
		StringBuilder jpql =  new StringBuilder();
		jpql.append("SELECT u FROM ")
		.append(ENTITY_NAME)
		.append(" u LEFT JOIN FETCH u.partner p" )
		.append(" LEFT JOIN FETCH u.createdBy cb")
		.append(" LEFT JOIN FETCH u.updatedBy ub");
		return jpql;
	}
	
	public String getBasicOrderBy(){
		return " order by u.updated desc ";
	}
	
	public String getActiveClause(){
		return " u.isActive= 'Y'";
	}

}

