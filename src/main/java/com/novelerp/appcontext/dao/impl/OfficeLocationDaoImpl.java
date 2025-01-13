package com.novelerp.appcontext.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appcontext.dao.OfficeLocationDao;
import com.novelerp.appcontext.dto.OfficeLocationDto;
import com.novelerp.appcontext.entity.OfficeLocation;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class OfficeLocationDaoImpl extends AbstractJpaDAO<OfficeLocation, OfficeLocationDto> implements OfficeLocationDao{

	@PostConstruct
	private void init() {
		setClazz(OfficeLocation.class, OfficeLocationDto.class);

	}
	
	public String getOfficeLocationByType(){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT l FROM OfficeLocation l ")
		.append(" WHERE upper(l.locationTypeRef) = upper(:locationtype) and l.isActive='Y' ORDER BY l.name" );
		return query.toString();
	}
	public String getOfficeTypeByLocationID(){
		StringBuilder query =  new StringBuilder();
		query.append("SELECT l FROM OfficeLocation l ")
		.append(" WHERE locationTypeId =:locationId" );
		return query.toString();
	}
}
