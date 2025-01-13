package com.novelerp.appbase.master.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.PaymentModeDao;
import com.novelerp.appbase.master.dto.PaymentModeDto;
import com.novelerp.appbase.master.entity.PaymentMode;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * @author Aman Sahu
 *
 */
@Repository
public class PaymentModeDaoImpl extends AbstractJpaDAO<PaymentMode, PaymentModeDto> implements PaymentModeDao{

private static final String ENTITY_NAME="M_PAYMENTMODE";
	
	@PostConstruct
	private void init() {
		setClazz(PaymentMode.class, PaymentModeDto.class);
	}
	
	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<PaymentMode> findAll() {		 
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager(); 
		return em.createQuery(jpql.toString(),PaymentMode.class).getResultList();
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

