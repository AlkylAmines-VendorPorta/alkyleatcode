package com.novelerp.eat.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dao.VendorAccountGroupDao;
import com.novelerp.eat.dto.VendorAccountGroupDto;
import com.novelerp.eat.entity.VendorAccountGroup;
import com.novelerp.eat.service.VendorAccountGroupService;

@Repository
public class VendorAccountGroupServiceImpl extends AbstractContextServiceImpl<VendorAccountGroup, VendorAccountGroupDto> implements VendorAccountGroupService {

	@Autowired
	private VendorAccountGroupDao vendorGroupDao; 
	
	@PostConstruct
	void init(){
		super.init(VendorAccountGroupServiceImpl.class, vendorGroupDao, VendorAccountGroup.class, VendorAccountGroupDto.class);
		setByPassProxy(true);
	}
}
