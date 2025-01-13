package com.novelerp.eat.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dao.GSTVendorClassDao;
import com.novelerp.eat.dto.GSTVendorClassDto;
import com.novelerp.eat.entity.GSTVendorClass;
import com.novelerp.eat.service.GSTVendorClassService;
@Service
public class GSTVendorClassServiceImpl extends AbstractContextServiceImpl<GSTVendorClass, GSTVendorClassDto> implements GSTVendorClassService{

	@Autowired
	private GSTVendorClassDao sstVendorClassDao;
	
	@PostConstruct
	void init(){
		super.init(GSTVendorClassServiceImpl.class, sstVendorClassDao, GSTVendorClass.class, GSTVendorClassDto.class);
		setByPassProxy(true);
	}
}
