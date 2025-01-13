package com.novelerp.appbase.master.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appbase.master.dao.DepartmentDao;
import com.novelerp.appbase.master.dto.DepartmentDto;
import com.novelerp.appbase.master.entity.Department;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * @author Aman Sahu
 *
 */
@Repository
public class DepartmentDaoImpl extends AbstractJpaDAO<Department, DepartmentDto> implements DepartmentDao{

private static final String ENTITY_NAME="M_DEPARTMENT";
	
	@PostConstruct
	private void init() {
		setClazz(Department.class, DepartmentDto.class);
	}
	
	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<Department> findAll() {		 
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager(); 
		return em.createQuery(jpql.toString(),Department.class).getResultList();
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
	public String getDepartmentById(){
		StringBuilder sb= new StringBuilder();
		sb.append(" SELECT d from Department d "
				+ " LEFT JOIN FETCH d.partner bp "
				+ " WHERE d.departmentId=:departmentId ");
		
		return sb.toString();
	}
	
	public String getDepartmentListByPartner(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select d from Department d ")
		.append(" WHERE d.partner.bPartnerId = :BPartnerId")
		.append(" AND d.isActive = 'Y'");
		
		return jpql.toString();
	}

}
