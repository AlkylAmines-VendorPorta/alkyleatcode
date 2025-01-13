package com.novelerp.appbase.master.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.MaterialGroupDao;
import com.novelerp.appbase.master.dto.MaterialGroupDto;
import com.novelerp.appbase.master.entity.MaterialGroup;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * 
 * @author Ankita Tirodkar
 *
 */
@Repository
public class MaterialGroupDaoImpl extends AbstractJpaDAO<MaterialGroup, MaterialGroupDto> implements MaterialGroupDao{

	private static final String ENTITY_NAME="M_MATERIAL_GROUP";
	
	@PostConstruct
	private void init() {
		setClazz(MaterialGroup.class, MaterialGroupDto.class);
	}
	
	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<MaterialGroup> findAll() {		 
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager(); 
		return em.createQuery(jpql.toString(),MaterialGroup.class).getResultList();
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

	public String getMaterialGroupListByBPartner(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select mg from MaterialGroup mg ")
		.append(" WHERE mg.partner.bPartnerId = :BPartnerId")
		.append(" AND mg.isActive = 'Y'");
		
		return jpql.toString();
	}
	
}
