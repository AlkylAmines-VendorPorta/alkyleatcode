package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.SapVendorDetailDao;
import com.novelerp.eat.dto.SapVendorDetailDto;
import com.novelerp.eat.entity.SapVendorDetail;

@Repository
public class SapVendorDetailDaoImpl extends AbstractJpaDAO<SapVendorDetail, SapVendorDetailDto> implements SapVendorDetailDao{

	@PostConstruct
	public void postConstruct() {
		setClazz(SapVendorDetail.class, SapVendorDetailDto.class);
	}
	
}
