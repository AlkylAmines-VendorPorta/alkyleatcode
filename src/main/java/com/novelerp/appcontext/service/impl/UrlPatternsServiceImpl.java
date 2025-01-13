package com.novelerp.appcontext.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appcontext.dao.UrlPatternsDao;
import com.novelerp.appcontext.dto.UrlPatternsDto;
import com.novelerp.appcontext.entity.UrlPatterns;
import com.novelerp.appcontext.service.UrlPatternsService;

@Service
public class UrlPatternsServiceImpl extends AbstractContextServiceImpl<UrlPatterns, UrlPatternsDto> implements UrlPatternsService{
	
	@Autowired
	private UrlPatternsDao urlPatternDao;
	
	@PostConstruct
	private void init() {		
		super.init(UrlPatternsServiceImpl.class, urlPatternDao, UrlPatterns.class, UrlPatternsDto.class);
		setByPassProxy(true);
	}

}
