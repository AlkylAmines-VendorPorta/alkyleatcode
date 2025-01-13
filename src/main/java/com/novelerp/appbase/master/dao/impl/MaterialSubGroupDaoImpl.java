package com.novelerp.appbase.master.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.MaterialSubGroupDao;
import com.novelerp.appbase.master.dto.MaterialSubGroupDto;
import com.novelerp.appbase.master.entity.MaterialSubGroup;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * @author Aman Sahu
 *
 */
@Repository
public class MaterialSubGroupDaoImpl extends AbstractJpaDAO<MaterialSubGroup, MaterialSubGroupDto> implements MaterialSubGroupDao{

private static final String ENTITY_NAME="M_MATERIALSUBGROUP";
	
	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	private void init() {
		setClazz(MaterialSubGroup.class, MaterialSubGroupDto.class);
	}
	
	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<MaterialSubGroup> findAll() {		 
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager(); 
		return em.createQuery(jpql.toString(),MaterialSubGroup.class).getResultList();
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
	public List<MaterialSubGroup> getMaterialSubGroupList() {
		StringBuilder sb=new StringBuilder("select msg from MaterialSubGroup msg ");
		sb.append(" LEFT JOIN FETCH msg.materialGroup r");
		Query q=em.createQuery(sb.toString(),MaterialSubGroup.class);
		List<MaterialSubGroup> materialSubGroupList= q.getResultList();
		return materialSubGroupList;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<MaterialSubGroup> getMaterialSubGroupById(Long id) {
		StringBuilder sb=new StringBuilder("select msg from MaterialSubGroup msg ");
		sb.append(" LEFT JOIN FETCH msg.materialGroup r where msg.materialSubGroupId=:id");
		Query q=em.createQuery(sb.toString(),MaterialSubGroup.class);
		System.err.println("id: "+id);
		q.setParameter("id", id);
		List<MaterialSubGroup> materialSubGroupList= q.getResultList();
		return materialSubGroupList;
	}
	
	@Override
	public List<MaterialSubGroup> getSubGroupByGroupId(Long id) {
		StringBuilder sb=new StringBuilder("select msg from MaterialSubGroup msg ");
		sb.append(" LEFT JOIN FETCH msg.materialGroup r where msg.materialGroup.materialGroupId=:id");
		Query q=em.createQuery(sb.toString(),MaterialSubGroup.class);
		q.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<MaterialSubGroup> materialSubGroupList= q.getResultList();
		return materialSubGroupList;
	}
	
	public String getMaterialSubGroupById(){
		StringBuilder jpql=new StringBuilder();
		jpql.append("Select m from MaterialSubGroup m" )
		.append(" Left JOIN FETCH m.materialGroup mg")
		.append(" WHERE m.materialSubGroupId= :materialSubGroupId");
		return jpql.toString();
		
	}
	
	public String getMaterialSubGroupListByBPartner(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select m from MaterialSubGroup m ")
		.append(" LEFT JOIN FETCH m.materialGroup mg")
		.append(" WHERE m.partner.bPartnerId = :BPartnerId")
		.append(" AND m.isActive = 'Y'");
		
		return jpql.toString();
	}
	
}

