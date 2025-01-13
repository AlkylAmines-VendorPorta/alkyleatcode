package com.novelerp.appcontext.service.impl;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.dao.OfficeLocationDao;
import com.novelerp.appcontext.dto.OfficeLocationDto;
import com.novelerp.appcontext.entity.OfficeLocation;
import com.novelerp.appcontext.service.OfficeLocationService;

@Service
public class OfficeLocationServiceImpl extends AbstractContextServiceImpl<OfficeLocation, OfficeLocationDto> implements OfficeLocationService {

	@Autowired
	private OfficeLocationDao officeLocationDao;
	
	@PostConstruct
	private void init() {
		super.init(OfficeLocationServiceImpl.class, officeLocationDao, OfficeLocation.class, OfficeLocationDto.class);
/*		setObjectConverter(locationConverterPlain);*/
		setByPassProxy(true);
	}
	
	@Transactional
	@Override
	public boolean deleteReferenceList(Long officeTypeId) {
		// TODO Auto-generated method stub
		return deleteById(officeTypeId);
	}
	

}
