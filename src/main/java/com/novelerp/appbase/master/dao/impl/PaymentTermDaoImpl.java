package com.novelerp.appbase.master.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.PaymentTermDao;
import com.novelerp.appbase.master.dto.PaymentTermDto;
import com.novelerp.appbase.master.entity.PaymentTerm;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * 
 * @author Vivek Birdi
 *
 */
@Repository
public class PaymentTermDaoImpl extends AbstractJpaDAO<PaymentTerm, PaymentTermDto> implements PaymentTermDao{

	private static final String ENTITY_NAME="C_PAYMENTTERM";
	
	@PostConstruct
	private void init() {
		setClazz(PaymentTerm.class, PaymentTermDto.class);

	}

	
	@Override
	public List<PaymentTerm> findAll() {
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager();
		return em.createQuery(jpql.toString(),PaymentTerm.class).getResultList();
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
