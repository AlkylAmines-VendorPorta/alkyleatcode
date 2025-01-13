package com.novelerp.eat.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dao.GtpScrutinyDao;
import com.novelerp.eat.dto.GtpScrutinyDto;
import com.novelerp.eat.entity.GtpScrutiny;
import com.novelerp.eat.service.GtpScrutinyService;

/**
 * @author Aman Sahu
 *
 */
@Service
public class GtpScrutinyServiceImpl extends AbstractContextServiceImpl<GtpScrutiny, GtpScrutinyDto> implements GtpScrutinyService {

	@Autowired
	private GtpScrutinyDao gtpScrutinyDao;
	
	@PostConstruct
	private void init() {
		super.init(GtpScrutinyServiceImpl.class, gtpScrutinyDao, GtpScrutiny.class, GtpScrutinyDto.class);
		setByPassProxy(false);
	}

}
