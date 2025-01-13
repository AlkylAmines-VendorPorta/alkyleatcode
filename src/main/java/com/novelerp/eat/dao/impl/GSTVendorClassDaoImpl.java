package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.GSTVendorClassDao;
import com.novelerp.eat.dto.GSTVendorClassDto;
import com.novelerp.eat.entity.GSTVendorClass;
@Repository
public class GSTVendorClassDaoImpl extends AbstractJpaDAO<GSTVendorClass, GSTVendorClassDto> implements GSTVendorClassDao{

	@PostConstruct
	public void postConstruct() {
		setClazz(GSTVendorClass.class, GSTVendorClassDto.class);
	}
	
}
