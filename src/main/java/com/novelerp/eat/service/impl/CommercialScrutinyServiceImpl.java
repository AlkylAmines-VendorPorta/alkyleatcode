package com.novelerp.eat.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dao.CommercialScrutinyDao;
import com.novelerp.eat.dto.CommercialScrutinyDto;
import com.novelerp.eat.entity.CommercialScrutiny;
import com.novelerp.eat.service.CommercialScrutinyService;

/**
 * @author Aman Sahu
 *
 */
@Service
public class CommercialScrutinyServiceImpl extends AbstractContextServiceImpl<CommercialScrutiny, CommercialScrutinyDto> implements CommercialScrutinyService {

	@Autowired
	private CommercialScrutinyDao commercialScrutinyDao;
	
	@PostConstruct
	private void init() {
		super.init(CommercialScrutinyServiceImpl.class, commercialScrutinyDao, CommercialScrutiny.class, CommercialScrutinyDto.class);
		setByPassProxy(false);
	}
}
