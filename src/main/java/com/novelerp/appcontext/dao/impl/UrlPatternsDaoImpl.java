package com.novelerp.appcontext.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appcontext.dao.UrlPatternsDao;
import com.novelerp.appcontext.dto.UrlPatternsDto;
import com.novelerp.appcontext.entity.UrlPatterns;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class UrlPatternsDaoImpl extends AbstractJpaDAO<UrlPatterns, UrlPatternsDto> implements UrlPatternsDao{

	@PostConstruct
	public void init(){
		setClazz(UrlPatterns.class, UrlPatternsDto.class);
	}
	
}
