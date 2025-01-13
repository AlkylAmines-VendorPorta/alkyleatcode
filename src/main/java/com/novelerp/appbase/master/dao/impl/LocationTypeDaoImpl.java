package com.novelerp.appbase.master.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.LocationTypeDao;
import com.novelerp.appbase.master.dto.LocationTypeDto;
import com.novelerp.appbase.master.entity.LocationType;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * @author Aman Sahu
 *
 */
@Repository
public class LocationTypeDaoImpl extends AbstractJpaDAO<LocationType, LocationTypeDto> implements LocationTypeDao{

private static final String ENTITY_NAME="M_LOCATIONTYPE";
	
	@PostConstruct
	private void init() {
		setClazz(LocationType.class, LocationTypeDto.class);
	}
	
	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<LocationType> findAll() {		 
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager(); 
		return em.createQuery(jpql.toString(),LocationType.class).getResultList();
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

	public String getLocationTypeOnLevels(){
		StringBuilder jpql= new StringBuilder(" Select lt from LocationType lt ");
		jpql.append(" where lt.levels >=:levels ");
		return jpql.toString();
	}
	
	public String getLocationTypeOnType(){
		StringBuilder jpql= new StringBuilder(" Select lt from LocationType lt ");
		jpql.append(" where lt.code =:code ");
		return jpql.toString();
	}
	
	public String getAllActiveLocationType(){
		StringBuilder jpql= new StringBuilder(" Select lt from LocationType lt ");
		jpql.append(" where lt.isActive ='Y' ");
		return jpql.toString();
	}
	public String getLocationTypeByPartner(){
		StringBuilder jpql= new StringBuilder(" Select lt from LocationType lt ");
		jpql.append(" where lt.isActive ='Y' AND lt.partner.bPartnerId =:BPartnerId ");
		return jpql.toString();
	}

}

