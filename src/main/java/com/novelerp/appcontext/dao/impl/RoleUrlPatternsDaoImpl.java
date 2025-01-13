package com.novelerp.appcontext.dao.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.novelerp.appcontext.dao.RoleUrlPatternsDao;
import com.novelerp.appcontext.dto.RoleUrlPatternDto;
import com.novelerp.appcontext.entity.RoleUrlPatterns;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class RoleUrlPatternsDaoImpl extends AbstractJpaDAO<RoleUrlPatterns, RoleUrlPatternDto>
		implements RoleUrlPatternsDao {

	@PostConstruct
	public void init() {
		setClazz(RoleUrlPatterns.class, RoleUrlPatternDto.class);
	}

}
