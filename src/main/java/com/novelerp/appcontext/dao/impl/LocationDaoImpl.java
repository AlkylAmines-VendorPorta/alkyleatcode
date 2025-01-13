package com.novelerp.appcontext.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.novelerp.appcontext.dao.LocationDao;
import com.novelerp.appcontext.dto.LocationDto;
import com.novelerp.appcontext.entity.Location;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * 
 * @author Vivek Birdi
 *
 */
@Repository
public class LocationDaoImpl extends AbstractJpaDAO<Location, LocationDto> implements LocationDao {

	@PostConstruct
	private void init() {
		setClazz(Location.class, LocationDto.class);

	}
	
	
	@Override
	public List<Location> findAll() {
		StringBuilder jpql = getBasicQuery();
		jpql.append(" WHERE ")
		.append(getActiveClause())
		.append(getBasicOrderBy());		
		EntityManager em = getEntityManager();
		return em.createQuery(jpql.toString(),Location.class).getResultList();
	}
	
	public StringBuilder getBasicQuery(){
		StringBuilder jpql =  new StringBuilder();
		jpql.append("SELECT u FROM Location u")
		.append(" LEFT JOIN FETCH u.partner p" )
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
	
	public String getPartnerOrgLocationQuery(){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT l FROM location l ")
		.append(" INNER JOIN FETCH l.partner p")
		.append(" INNER JOIN FETCH l.createdBy c")
		.append(" INNER JOIN FETCH l.updatedBy u")
		.append(" INNER JOIN FETCH l.country cn")
		.append(" INNER JOIN FETCH l.region r")
		.append(" INNER JOIN FETCH l.district d")
		.append(" WHERE locationId = :locationId" );

		return query.toString();
	}

	/*public String getDefaultAddress(){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT l FROM Location l ")
		.append(" INNER JOIN FETCH l.country cn")
		.append(" INNER JOIN FETCH l.region r")
		.append(" INNER JOIN FETCH l.district d")
		.append(" WHERE l.partner.bPartnerId = :partnerId ORDER BY l.created ASC " );
		.append(" WHERE l.created=( SELECT Min(l.created) from Location WHERE l.partner.bPartnerId = :partnerId)" );
		return query.toString();
	}*/
	
	public String getOfficeLocationByType(){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT l FROM Location l ")
		.append(" WHERE upper(l.locationType) = upper(:locationtype)" );
		return query.toString();
	}
	
	
}
