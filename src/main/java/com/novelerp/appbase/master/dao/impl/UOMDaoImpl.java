package com.novelerp.appbase.master.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.UOMDao;
import com.novelerp.appbase.master.dto.UOMDto;
import com.novelerp.appbase.master.entity.UOM;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class UOMDaoImpl extends AbstractJpaDAO<UOM, UOMDto> implements UOMDao{

	private static final String ENTITY_NAME="M_UOM";
		
	@PersistenceContext
	private EntityManager em;
	
	
	@PostConstruct
	private void init() {
		setClazz(UOM.class, UOMDto.class);
	}
	
	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<UOM> findAll() {		 
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager(); 
		return em.createQuery(jpql.toString(),UOM.class).getResultList();
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
		
	@Override
	@SuppressWarnings("unchecked")
	public List<UOM> getSearchedUomList(String word)
	{
		
		List<UOM> uomlist=null;
		StringBuilder sb=new StringBuilder("select u from UOM u ");
			sb.append(" where upper(u.name) like upper(:literal) or upper(u.code) like upper(:literal) ");

			Query q=em.createQuery( sb.toString(),UOM.class);
			q.setParameter("literal", "%"+word+"%");
			/*q.setFirstResult(pageno==1?0:7*(pageno-1));
			q.setMaxResults(7);*/
			uomlist= q.getResultList();
			return uomlist;
	}
	public String getUomListByPartner(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select u from UOM u ")
		.append(" WHERE u.partner.bPartnerId = :BPartnerId")
		.append(" AND u.isActive = 'Y'");
		
		return jpql.toString();
	}
	
}
