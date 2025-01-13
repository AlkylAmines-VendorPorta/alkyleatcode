package com.novelerp.appbase.master.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.novelerp.appbase.master.dao.CurrencyDao;
import com.novelerp.appbase.master.dto.CurrencyDto;
import com.novelerp.appbase.master.entity.Currency;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
/**
 * 
 * @author Vivek Birdi
 *
 */
@Repository
public class CurrencyDaoImpl extends AbstractJpaDAO<Currency, CurrencyDto> implements CurrencyDao {

	private static final String ENTITY_NAME="C_CURRENCY";

	@PostConstruct
	private void inti() {
		setClazz(Currency.class, CurrencyDto.class);

	}
	
	@Override
	public List<Currency> findAll() {
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager();
		return em.createQuery(jpql.toString(),Currency.class).getResultList();
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

	public String getCurrencyByCode(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM Currency A ")
				.append(" WHERE A.code = :currencyCode ");
		return jpql.toString();
	}
	
}
