package com.novelerp.appcontext.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.novelerp.appcontext.dao.RoleDao;
import com.novelerp.appcontext.dto.RoleDto;
import com.novelerp.appcontext.entity.Role;
import com.novelerp.core.dao.impl.AbstractJpaDAO;


/**
 * @author sravan
 * RoleDaoImpl
 *
 */
@Repository
public class RoleDaoImpl extends AbstractJpaDAO<Role,RoleDto> implements RoleDao{

	Logger log=LoggerFactory.getLogger(RoleDaoImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void setClazz(){
		setClazz(Role.class,RoleDto.class);
	}
	
	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<Role> findAll() {
		return execute(getJpqlQuery().toString(), null, Role.class);		
	}
		
	public StringBuilder getJpqlQuery(){		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select c from Role c");
		return jpql;
	}
	
	private Map<String, Object> getParams(Long userID){
		Map<String, Object> params= new HashMap<>();
		params.put("userID", userID);
		return params;
	}
	
	private StringBuilder getQueryForUserRoles(){		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select r from Role r ")/*
		.append(" LEFT JOIN r.createdBy  rc")
		.append(" LEFT JOIN r.updatedBy ru")*/
		.append(" INNER JOIN FETCH r.userRoles ur")/*
		.append(" LEFT JOIN FETCH ur.createdBy urc" )
		.append(" LEFT JOIN FETCH ur.updatedBy uru")*/
		.append(" WHERE ur.user.userId = :userID");
		return jpql;
	}
	
	private StringBuilder getQueryForUserDefaultRoles(){		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select r from Role r ")/*
		.append(" LEFT JOIN r.createdBy  rc")
		.append(" LEFT JOIN r.updatedBy ru")*/
		.append(" INNER JOIN FETCH r.userRoles ur")/*
		.append(" LEFT JOIN FETCH ur.createdBy urc" )
		.append(" LEFT JOIN FETCH ur.updatedBy uru")*/
		.append(" WHERE ur.user.userId = :userID AND ur.isDefault='Y' ");
		return jpql;
	}
	
	public String getRoleByBPartner(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select r from Role r ")
		.append(" WHERE r.partner.bPartnerId = :BPartnerId")
		.append(" AND r.isActive = 'Y'");
		
		return jpql.toString();
	}
	
	
	@Override
	public List<Role> getUserRoles(Long userID) {
		StringBuilder jpql =  getQueryForUserDefaultRoles();
		return execute(jpql.toString(), getParams(userID), Role.class);
	}	
	
	public String getRolesForNewUser(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select r from Role r ")
		.append("where r.isActive = 'Y' order by name");
		return jpql.toString();
	}

	@Override
	public List<Role> getUserAllRoles(Long userId) {
		StringBuilder jpql =  getQueryForUserRoles();
		return execute(jpql.toString(), getParams(userId), Role.class);
	}

}
