package com.novelerp.appbase.master.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.TaxDao;
import com.novelerp.appbase.master.dto.TaxDto;
import com.novelerp.appbase.master.entity.Tax;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * @author Aman Sahu
 *
 */
@Repository
public class TaxDaoImpl extends AbstractJpaDAO<Tax, TaxDto> implements TaxDao{

private static final String ENTITY_NAME="M_TAX";

@PersistenceContext
private EntityManager em;
	
	@PostConstruct
	private void init() {
		setClazz(Tax.class, TaxDto.class);
	}
	
	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<Tax> findAll() {		 
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager(); 
		return em.createQuery(jpql.toString(),Tax.class).getResultList();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Tax> getTaxList() {
		StringBuilder sb=new StringBuilder("select t from Tax t ");
		sb.append(" LEFT JOIN FETCH t.taxcategory tc order by t.updated desc");
		Query q=em.createQuery(sb.toString(),Tax.class);
		List<Tax> taxList= q.getResultList();
		/*taxList=findAll();*/
		return taxList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Tax> getTaxById(Long id) {
		StringBuilder sb=new StringBuilder("select t from Tax t ");
		sb.append(" LEFT JOIN FETCH t.taxcategory tc where t.taxId=:id");
		Query q=em.createQuery(sb.toString(),Tax.class);
		System.err.println("id: "+id);
		q.setParameter("id", id);
		List<Tax> taxList= q.getResultList();
		return taxList;
	}
	
	
	public String getTaxByBPartner(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select t from Tax t ")
		.append(" LEFT JOIN FETCH t.taxcategory tc")
		.append(" WHERE t.partner.bPartnerId = :BPartnerId")
		.append(" AND t.isActive = 'Y'")
		.append(" order by t.updated desc");
		
		return jpql.toString();
	}

	

}

