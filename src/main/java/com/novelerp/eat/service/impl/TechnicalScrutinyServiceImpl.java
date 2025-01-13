package com.novelerp.eat.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dao.TechnicalScrutinyDao;
import com.novelerp.eat.dto.TechnicalScrutinyDto;
import com.novelerp.eat.entity.TechnicalScrutiny;
import com.novelerp.eat.service.TechnicalScrutinyService;

/**
 * @author Aman Sahu
 *
 */
@Service
public class TechnicalScrutinyServiceImpl extends AbstractContextServiceImpl<TechnicalScrutiny, TechnicalScrutinyDto> implements TechnicalScrutinyService {

	@Autowired
	private TechnicalScrutinyDao technicalScrutinyDao;
	
	@PostConstruct
	private void init() {
		super.init(TechnicalScrutinyServiceImpl.class, technicalScrutinyDao, TechnicalScrutiny.class, TechnicalScrutinyDto.class);
		setByPassProxy(false);
	}
}
