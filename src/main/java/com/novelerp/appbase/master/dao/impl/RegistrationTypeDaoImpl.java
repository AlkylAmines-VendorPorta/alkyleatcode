package com.novelerp.appbase.master.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.RegistrationTypeDao;
import com.novelerp.appbase.master.dto.RegistrationTypeDto;
import com.novelerp.appbase.master.entity.RegistrationType;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * @author Aman Sahu
 *
 */
@Repository
public class RegistrationTypeDaoImpl extends AbstractJpaDAO<RegistrationType, RegistrationTypeDto> implements RegistrationTypeDao{

private static final String ENTITY_NAME="M_REGISTRATIONTYPE";
	
	@PostConstruct
	private void init() {
		setClazz(RegistrationType.class, RegistrationTypeDto.class);
	}
	
	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<RegistrationType> findAll() {		 
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager(); 
		return em.createQuery(jpql.toString(),RegistrationType.class).getResultList();
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

