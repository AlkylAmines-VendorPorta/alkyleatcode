package com.novelerp.appcontext.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appcontext.dao.BPartnerGroupDao;
import com.novelerp.appcontext.dto.BPartnerGroupDto;
import com.novelerp.appcontext.entity.BPartnerGroup;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/** 
 * @author Aman
 *
 */
@Repository
public class BPartnerGroupDaoImpl extends AbstractJpaDAO<BPartnerGroup, BPartnerGroupDto> implements BPartnerGroupDao{

private static final String ENTITY_NAME="M_BPARTNER_GROUP";
	
	@PostConstruct
	private void init() {
		setClazz(BPartnerGroup.class, BPartnerGroupDto.class);
	}
	
	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<BPartnerGroup> findAll() {		 
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager(); 
		return em.createQuery(jpql.toString(),BPartnerGroup.class).getResultList();
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

