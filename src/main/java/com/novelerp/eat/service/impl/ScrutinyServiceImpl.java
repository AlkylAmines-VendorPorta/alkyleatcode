package com.novelerp.eat.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.eat.dao.ScrutinyDao;
import com.novelerp.eat.dto.ScrutinyDto;
import com.novelerp.eat.entity.Scrutiny;
import com.novelerp.eat.service.ScrutinyService;

/**
 * @author Aman Sahu
 *
 */
@Service
public class ScrutinyServiceImpl extends AbstractContextServiceImpl<Scrutiny, ScrutinyDto> implements ScrutinyService {

	@Autowired
	private ScrutinyDao scrutinyDao;
	
	@PostConstruct
	private void init() {
		super.init(ScrutinyServiceImpl.class, scrutinyDao, Scrutiny.class, ScrutinyDto.class);
		setByPassProxy(false);
	}
}
