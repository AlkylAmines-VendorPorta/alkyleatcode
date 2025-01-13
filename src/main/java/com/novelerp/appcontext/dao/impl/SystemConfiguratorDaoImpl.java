package com.novelerp.appcontext.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.novelerp.appcontext.dao.SystemConfiguratorDao;
import com.novelerp.appcontext.dto.SystemConfiguratorDto;
import com.novelerp.appcontext.entity.SystemConfigurator;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class SystemConfiguratorDaoImpl extends AbstractJpaDAO<SystemConfigurator, SystemConfiguratorDto> implements SystemConfiguratorDao {

	
	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	public void setClazz() {
		setClazz(SystemConfigurator.class, SystemConfiguratorDto.class);
	}
	
	public String getSysConfigurator(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select c from SystemConfigurator c  ");
		return jpql.toString();
	}

	public String getSysConfiguratorByType(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select sc from SystemConfigurator sc  ");
		jpql.append(" Where sc.value=:value  ");
		return jpql.toString();
	}
	
	public String getSpecficSysConfigurator(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select sc from SystemConfigurator sc  ");
		jpql.append(" Where sc.isSystemConfig=:isSystemConfig  ");
		return jpql.toString();
	}
	
	public String getPartnerSysConfigurator(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select sc from SystemConfigurator sc  ");
		jpql.append(" Where sc.partner.bPartnerId=:partnerId AND sc.isSystemConfig=:isSystemConfig ");
		return jpql.toString();
	}
	
	public String getSysConfigQueryForSFTP(){
		StringBuilder jpql= new StringBuilder("select sc from SystemConfigurator sc ")
		.append(" where sc.isActive='Y' and sc.referenceCode=:referenceCode ");
		return jpql.toString();
	}
}
