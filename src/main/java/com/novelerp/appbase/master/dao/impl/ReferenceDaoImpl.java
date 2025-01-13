package com.novelerp.appbase.master.dao.impl;


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

import com.novelerp.appbase.master.dao.ReferenceDao;
import com.novelerp.appbase.master.dto.ReferenceDto;
import com.novelerp.appbase.master.entity.Reference;
import com.novelerp.appcontext.dao.impl.RoleDaoImpl;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class ReferenceDaoImpl extends AbstractJpaDAO<Reference, ReferenceDto> implements ReferenceDao {

	
Logger log=LoggerFactory.getLogger(RoleDaoImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	@PostConstruct
	public void postConstruct() {
		setClazz(Reference.class, ReferenceDto.class);
	}

	@Override
	@Transactional(propagation =Propagation.SUPPORTS)
	public List<Reference> findAll() {
		return execute(getJpqlQuery().toString(), null, Reference.class);		
	}
		
	public StringBuilder getJpqlQuery(){		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select c from Reference c");
		return jpql;
	}
	
	private Map<String, Object> getParams(Long userID){
		Map<String, Object> params= new HashMap<>();
		params.put("userID", userID);
		return params;
	}
	
	private StringBuilder getQueryForUserReferences(){		
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select r from Reference r ")/*
		.append(" LEFT JOIN r.createdBy  rc")
		.append(" LEFT JOIN r.updatedBy ru")*/
		.append(" INNER JOIN FETCH r.userReferences ur")/*
		.append(" LEFT JOIN FETCH ur.createdBy urc" )
		.append(" LEFT JOIN FETCH ur.updatedBy uru")*/
		.append(" WHERE ur.user.userId = :userID");
		return jpql;
	}
	
	public String getReferenceByBPartner(){
		StringBuilder jpql = new StringBuilder();
		jpql.append(" Select r from Reference r ")
		.append(" WHERE r.partner.bPartnerId = :BPartnerId")
		.append(" AND r.isActive = 'Y'");
		
		return jpql.toString();
	}	
	
	
	@Override
	public List<Reference> getUserReferences(Long userID) {
		StringBuilder jpql =  getQueryForUserReferences();
		return execute(jpql.toString(), getParams(userID), Reference.class);
	}
	
}