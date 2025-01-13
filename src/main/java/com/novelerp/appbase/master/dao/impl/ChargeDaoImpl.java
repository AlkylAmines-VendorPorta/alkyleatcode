package com.novelerp.appbase.master.dao.impl;

import javax.annotation.PostConstruct;

import com.novelerp.appbase.master.dao.ChargeDao;
import com.novelerp.appbase.master.dto.ChargeDto;
import com.novelerp.appbase.master.entity.Charge;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

/**
 * 
 * @author Vivek Birdi
 *
 */
public class ChargeDaoImpl extends AbstractJpaDAO<Charge, ChargeDto> implements ChargeDao{

	@PostConstruct
	public void init() {
		setClazz(Charge.class, ChargeDto.class);
	}
}
