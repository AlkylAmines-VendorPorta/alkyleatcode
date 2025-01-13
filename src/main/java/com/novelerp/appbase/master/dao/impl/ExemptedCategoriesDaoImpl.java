package com.novelerp.appbase.master.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.ExemptedCategoriesDao;
import com.novelerp.appbase.master.dto.ExemptedCategoriesDto;
import com.novelerp.appbase.master.entity.ExemptedCategories;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
@Repository
public class ExemptedCategoriesDaoImpl extends AbstractJpaDAO<ExemptedCategories, ExemptedCategoriesDto> implements ExemptedCategoriesDao{

private static final String ENTITY_NAME="M_EXEMPTED_CATEGORIES";
	
	@PostConstruct
	private void init() {
		setClazz(ExemptedCategories.class, ExemptedCategoriesDto.class);
	}
	
	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<ExemptedCategories> findAll() {		 
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager(); 
		return em.createQuery(jpql.toString(),ExemptedCategories.class).getResultList();
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

