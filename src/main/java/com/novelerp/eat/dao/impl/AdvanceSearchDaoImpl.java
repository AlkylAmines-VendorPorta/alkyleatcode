package com.novelerp.eat.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.entity.Material;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.AdvanceSearchDao;
import com.novelerp.eat.dto.TAHDRDto;
import com.novelerp.eat.entity.TAHDR;

/**
 * @author Ankita Tirodkar
 *
 */

@Repository
public class AdvanceSearchDaoImpl  extends AbstractJpaDAO<TAHDR, TAHDRDto> implements AdvanceSearchDao {
	

private static final String ENTITY_NAME="T_TAHDR";

	
	
	@PostConstruct
	public void postConstruct() {
		setClazz(TAHDR.class, TAHDRDto.class);
	}

	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<TAHDR> findAll() {		 
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager(); 
		return em.createQuery(jpql.toString(),TAHDR.class).getResultList();
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
	public List<Material> getMaterialList() {
		
		StringBuilder sb=new StringBuilder("select * from T_TAHDR  ");
		Query q=getEntityManager().createQuery(sb.toString(),Material.class);
		List<Material> materialList= q.getResultList();
		return materialList;
	}

	

}
