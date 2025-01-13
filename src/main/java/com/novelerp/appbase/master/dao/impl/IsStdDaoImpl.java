package com.novelerp.appbase.master.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.IsStdDao;
import com.novelerp.appbase.master.dto.IsStdDto;
import com.novelerp.appbase.master.entity.IsStd;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * @author Aman Sahu
 *
 */
@Repository
public class IsStdDaoImpl extends AbstractJpaDAO<IsStd, IsStdDto> implements IsStdDao{

private static final String ENTITY_NAME="M_ISSTD";
	
	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	private void init() {
		setClazz(IsStd.class, IsStdDto.class);
	}
	
	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<IsStd> findAll() {		 
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager(); 
		return em.createQuery(jpql.toString(),IsStd.class).getResultList();
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
	public List<IsStd> getIsStdList() {
		StringBuilder sb=new StringBuilder("select is from IsStd is ");
		/*sb.append(" LEFT JOIN FETCH is.material");*/
		Query q=em.createQuery(sb.toString(),IsStd.class);
		List<IsStd> isStdlist= q.getResultList();
		return isStdlist;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<IsStd> getIsStdById(Long id) {
		StringBuilder sb=new StringBuilder("select is from IsStd is ");
		/*sb.append(" LEFT JOIN FETCH is.material where is.id:=id");*/
		sb.append("  where is.isStdId:=id");
		Query q=em.createQuery(sb.toString(),IsStd.class);
		q.setParameter("id", id);
		List<IsStd> isStd= q.getResultList();
		return isStd;
	}
	

}

