package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.VendorAccountGroupDao;
import com.novelerp.eat.dto.VendorAccountGroupDto;
import com.novelerp.eat.entity.VendorAccountGroup;

@Repository
public class VendorAccountGroupDaoImpl extends AbstractJpaDAO<VendorAccountGroup, VendorAccountGroupDto> implements VendorAccountGroupDao{

	@PostConstruct
	public void postConstruct() {
		setClazz(VendorAccountGroup.class, VendorAccountGroupDto.class);
	}
	
}
