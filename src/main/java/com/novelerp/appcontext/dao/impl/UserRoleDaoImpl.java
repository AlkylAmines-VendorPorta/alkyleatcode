package com.novelerp.appcontext.dao.impl;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.novelerp.appcontext.dao.UserRoleDao;
import com.novelerp.appcontext.dto.UserRolesDto;
import com.novelerp.appcontext.entity.UserRoles;
import com.novelerp.core.dao.impl.AbstractJpaDAO;
@Repository
public class UserRoleDaoImpl extends AbstractJpaDAO<UserRoles, UserRolesDto>  implements UserRoleDao {

	Logger log=LoggerFactory.getLogger(RoleDaoImpl.class);

	@PostConstruct
	public void setClazz(){
		setClazz(UserRoles.class, UserRolesDto.class);
	}
	
}
