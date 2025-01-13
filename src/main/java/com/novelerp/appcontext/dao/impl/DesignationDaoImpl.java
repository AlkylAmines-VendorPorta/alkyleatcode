package com.novelerp.appcontext.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appcontext.dao.DesignationDao;
import com.novelerp.appcontext.dto.DesignationDto;
import com.novelerp.appcontext.entity.Designation;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * 
 * @author Ankita Tirodkar
 *
 */
@Repository
public class DesignationDaoImpl extends AbstractJpaDAO<Designation, DesignationDto> implements DesignationDao{
	
	@PostConstruct
	private void init() {
		setClazz(Designation.class, DesignationDto.class);
	}
	
	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<Designation> findAll() {		 
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager(); 
		return em.createQuery(jpql.toString(),Designation.class).getResultList();
	}
	
	public StringBuilder getBasicQuery(){
		StringBuilder jpql =  new StringBuilder();
		jpql.append("SELECT u FROM Designation u")
		.append(" LEFT JOIN FETCH u.partner p" )
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
	
	public String getQueryForDesignation(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select d from Designation d ")
		.append("ORDER BY d.name ");
		
		
		return jpql.toString();
	}

	

}
