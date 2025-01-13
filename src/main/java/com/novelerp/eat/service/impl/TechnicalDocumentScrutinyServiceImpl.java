package com.novelerp.eat.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dao.TechnicalDocumentScrutinyDao;
import com.novelerp.eat.dto.TechnicalDocumentScrutinyDto;
import com.novelerp.eat.entity.TechnicalDocumentScrutiny;
import com.novelerp.eat.service.TechnicalDocumentScrutinyService;

/**
 * @author Aman Sahu
 *
 */
@Service
public class TechnicalDocumentScrutinyServiceImpl extends AbstractContextServiceImpl<TechnicalDocumentScrutiny, TechnicalDocumentScrutinyDto> implements TechnicalDocumentScrutinyService {

	@Autowired
	private TechnicalDocumentScrutinyDao technicalDocumentScrutinyDao;
	
	@PostConstruct
	private void init() {
		super.init(TechnicalDocumentScrutinyServiceImpl.class, technicalDocumentScrutinyDao, TechnicalDocumentScrutiny.class, TechnicalDocumentScrutinyDto.class);
		setByPassProxy(false);
	}
}
