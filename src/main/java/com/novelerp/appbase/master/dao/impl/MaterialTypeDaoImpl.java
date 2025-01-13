package com.novelerp.appbase.master.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.MaterialTypeDao;
import com.novelerp.appbase.master.dto.MaterialTypeDto;
import com.novelerp.appbase.master.entity.MaterialType;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class MaterialTypeDaoImpl extends AbstractJpaDAO<MaterialType, MaterialTypeDto> implements MaterialTypeDao{

private static final String ENTITY_NAME="M_MATERIAL_TYPE";
	
	@PostConstruct
	private void init() {
		setClazz(MaterialType.class, MaterialTypeDto.class);
	}
	
	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<MaterialType> findAll() {		 
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager(); 
		return em.createQuery(jpql.toString(),MaterialType.class).getResultList();
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
	public String getMaterialTypeByBPartner(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select m from MaterialType m ")
		.append(" WHERE m.partner.bPartnerId = :BPartnerId")
		.append(" AND m.isActive = 'Y'");
		
		return jpql.toString();
	}
	

}
