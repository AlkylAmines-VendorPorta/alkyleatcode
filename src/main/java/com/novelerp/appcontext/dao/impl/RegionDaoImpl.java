package com.novelerp.appcontext.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.novelerp.appcontext.dao.RegionDao;
import com.novelerp.appcontext.dto.RegionDto;
import com.novelerp.appcontext.entity.Region;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * 
 * @author vivek.birdi
 *
 */
@Repository
public class RegionDaoImpl extends AbstractJpaDAO<Region, RegionDto> implements RegionDao{
	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	 private void init() {
		setClazz(Region.class, RegionDto.class);

	}
	
	@Override
	public List<Region> findAll() {
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager();
		return em.createQuery(jpql.toString(),Region.class).getResultList();
	}
	
	public StringBuilder getBasicQuery(){
		StringBuilder jpql =  new StringBuilder();
		jpql.append("SELECT u FROM Region u")
		.append(" LEFT JOIN FETCH u.partner p" )
		.append(" LEFT JOIN FETCH u.createdBy cb")
		.append(" LEFT JOIN FETCH u.updatedBy ub");
		return jpql;
	}
	public String getRegionQuery(){
		StringBuilder jpql =  new StringBuilder();
		jpql.append("SELECT u FROM Region u where u.isActive='Y' ")
		.append(" ORDER BY u.name");
		return jpql.toString();
	}
	
	public String getBasicOrderBy(){
		return " order by u.updated desc ";
	}
	
	public String getActiveClause(){
		return " u.isActive= 'Y'";
	}
	public String getRegionBycountry(){
		StringBuilder jpql =  new StringBuilder();
		jpql.append(" SELECT r FROM Region r ")
		.append(" where r.country.countryId=:countryId and r.isActive='Y' ORDER BY r.name  ");
		return jpql.toString();
	}
	public String getRegionByGstCode(){
		StringBuilder jpql =  new StringBuilder(" SELECT r FROM Region r ")
	    .append(" INNER JOIN FETCH r.country crty ")
	    .append(" where r.isActive='Y' and r.code=:gstStateCode ");
		return jpql.toString();
	}

	
}
