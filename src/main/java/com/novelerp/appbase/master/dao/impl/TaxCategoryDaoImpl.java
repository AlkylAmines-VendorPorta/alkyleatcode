package com.novelerp.appbase.master.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.TaxCategoryDao;
import com.novelerp.appbase.master.dto.TaxCategoryDto;
import com.novelerp.appbase.master.entity.TaxCategory;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
* 
* 
* @author Aman Sahu
*
*/
@Repository
public class TaxCategoryDaoImpl extends AbstractJpaDAO<TaxCategory, TaxCategoryDto> implements TaxCategoryDao{

private static final String ENTITY_NAME="M_TAXCATEGORY";
	
	@PostConstruct
	private void init() {
		setClazz(TaxCategory.class, TaxCategoryDto.class);
	}
	
	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<TaxCategory> findAll() {		 
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager(); 
		return em.createQuery(jpql.toString(),TaxCategory.class).getResultList();
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
	public String getTaxCatListByPartner(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select t from TaxCategory t ")
		.append(" WHERE t.partner.bPartnerId = :BPartnerId")
		.append(" AND t.isActive = 'Y'");
		
		return jpql.toString();
	}
	

}

