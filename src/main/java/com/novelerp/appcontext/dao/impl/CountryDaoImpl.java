package com.novelerp.appcontext.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.novelerp.appcontext.dao.CountryDao;
import com.novelerp.appcontext.dto.CountryDto;
import com.novelerp.appcontext.entity.Country;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * 
 * @author vivek.birdi
 *
 */
@Repository
public class CountryDaoImpl extends AbstractJpaDAO<Country, CountryDto>  implements CountryDao{

	private static final String ENTITY_NAME="m_country";
	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	private void init() {
		setClazz(Country.class, CountryDto.class);

	}
	
	@Override
	public List<Country> findAll() {
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager();
		return em.createQuery(jpql.toString(),Country.class).getResultList();
	}
	
	public StringBuilder getBasicQuery(){
		StringBuilder jpql =  new StringBuilder();
		jpql.append("SELECT u FROM Country")
		.append(" u LEFT JOIN FETCH u.partner p" )
		.append(" LEFT JOIN FETCH u.createdBy cb")
		.append(" LEFT JOIN FETCH u.updatedBy ub");
		return jpql;
	}
	public String getCountryQuery(){
		StringBuilder jpql =  new StringBuilder();
		jpql.append("SELECT u FROM Country u ")
		.append(" ORDER BY u.name");
		return jpql.toString();
	}
	
	public String getBasicOrderBy(){
		return " order by u.updated desc ";
	}
	
	public String getActiveClause(){
		return " u.isActive= 'Y'";
	}

}
