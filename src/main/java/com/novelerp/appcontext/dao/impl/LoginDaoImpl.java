package com.novelerp.appcontext.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.novelerp.appcontext.dao.LoginDao;
import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.entity.User;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class LoginDaoImpl extends AbstractJpaDAO<User,UserDto> implements LoginDao {
	
	private static final Logger log = LoggerFactory.getLogger(LoginDaoImpl.class);
	
	@PostConstruct
	public void init() {
		setClazz(User.class, UserDto.class);
	}
	
	@Override
	public String validateLogin(String userEmail, String passWord) 
	{
		log.info("Inside LoginDaoImpl validateLogin()");
		final String hql = "select u.name from User as u where u.email=? and u.password=? and u.isActive='Y'";
		Query query = (Query) getEntityManager().createQuery(hql);
		log.debug("Query : "+query);
		query.setParameter(0, userEmail);
		query.setParameter(1, passWord);
		List list = query.getResultList();
	
		String userName = (String)list.get(0);
		log.info("<---UserName : "+userName);
		return userName;
	}

	@Override
	public String getRole(int roleId) 
	{
		String roleName = null;
		log.info("Inside LoginDaoImple getRole()");
		String hql =  "select r.name from Role as r where r.roleId=? and r.isActive='Y'";
		Query query = (Query) getEntityManager().createQuery(hql);
		query.setParameter(0, roleId);
		List list = query.getResultList();
		roleName = (String)list.get(0);
		return roleName;
	}

	@Override
	public User getUserDetails(String userEmail, String passWord) 
	{
		log.info("<---Inside LoginDaoImple getUserDetails()");
		String hql = "SELECT u FROM User U where U.email=? and U.password=? and U.isActive='Y'";
		Query query =  getEntityManager().createQuery(hql);
		query.setParameter(0, userEmail);
		query.setParameter(1, passWord);
		List<User> list = query.getResultList();
		return list.get(0);
	}
}