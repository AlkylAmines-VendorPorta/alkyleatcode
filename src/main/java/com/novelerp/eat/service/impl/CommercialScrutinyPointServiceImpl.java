package com.novelerp.eat.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dao.CommercialScrutinyPointDao;
import com.novelerp.eat.dto.CommercialScrutinyPointDto;
import com.novelerp.eat.entity.CommercialScrutinyPoint;
import com.novelerp.eat.service.CommercialScrutinyPointService;

/**
 * @author Aman Sahu
 *
 */
@Service
public class CommercialScrutinyPointServiceImpl extends AbstractContextServiceImpl<CommercialScrutinyPoint, CommercialScrutinyPointDto> implements CommercialScrutinyPointService {

	@Autowired
	private CommercialScrutinyPointDao commercialScrutinyPointDao;
	
	@PostConstruct
	private void init() {
		super.init(CommercialScrutinyPointServiceImpl.class, commercialScrutinyPointDao, CommercialScrutinyPoint.class, CommercialScrutinyPointDto.class);
		setByPassProxy(false);
	}
}
