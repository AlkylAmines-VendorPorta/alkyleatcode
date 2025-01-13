package com.novelerp.eat.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.core.dao.impl.AbstractJpaDAO;
import com.novelerp.eat.dao.MISDao;
import com.novelerp.eat.dto.MISDto;
import com.novelerp.eat.entity.MIS;

@Repository
public class MISDaoImpl extends AbstractJpaDAO<MIS, MISDto> implements MISDao{
	
	@PostConstruct
	public void postConstruct() {
		setClazz(MIS.class, MISDto.class);
	}

}
