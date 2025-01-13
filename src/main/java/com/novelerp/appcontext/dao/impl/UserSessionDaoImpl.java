package com.novelerp.appcontext.dao.impl;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.novelerp.appcontext.dao.UserSessionDao;
import com.novelerp.appcontext.dto.UserSessionDto;
import com.novelerp.appcontext.entity.UserSession;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class UserSessionDaoImpl extends AbstractJpaDAO<UserSession, UserSessionDto> implements UserSessionDao {

	@PostConstruct
	public void init() {
		setClazz(UserSession.class, UserSessionDto.class);
	}

	@Override
	public int updateUserSession(String sessionId) {
		StringBuilder jpql=new StringBuilder(" Update UserSession u set u.logoutTime=:logoutTime ")
		.append(" where u.sessionId=:sessionId ");
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("logoutTime", new Date());
		query.setParameter("sessionId", sessionId);
		int count=query.executeUpdate();
		return count;
	}

	@Override
	public String getSessionAuditCountQry(String searchColumn){
		StringBuilder sb=new StringBuilder("");
		if(!"none".equalsIgnoreCase(searchColumn)){
			if("userRole".equalsIgnoreCase(searchColumn)){
				sb.append(" c.createdBy.userId IN (SELECT ur.user.userId FROM UserRoles ur "
					+ " INNER JOIN ur.role r"
					+ " WHERE UPPER(r.value) LIKE :searchValue) " );
			}else{
				sb=new StringBuilder("UPPER(c."+searchColumn+") LIKE :searchValue");
			}
		}
		return sb.toString();
	}
	@Override
	public String getSessionListQuery(String searchColumn, String searchValue){
		StringBuilder sb=new StringBuilder("SELECT us FROM UserSession us ");
		sb.append(" WHERE us.isActive='Y' ");
		if(!"none".equalsIgnoreCase(searchColumn)){
			if("userRole".equalsIgnoreCase(searchColumn)){
				sb.append(" AND us.createdBy.userId IN (SELECT ur.user.userId FROM UserRoles ur "
					+ " INNER JOIN ur.role r"
					+ " WHERE UPPER(r.value) LIKE :searchValue )" );
			}else{
				sb.append(" AND UPPER(us."+searchColumn+") LIKE :searchValue");
			}
		}
		sb.append(" ORDER BY us.created DESC ");
		return sb.toString();
	}
	
    public String getUserSessionBySessionKey(){
		StringBuilder jpql= new StringBuilder();
		jpql.append(" Select DISTINCT us from UserSession us ");
		jpql.append(" INNER JOIN FETCH us.createdBy u ");
		jpql.append(" WHERE us.sessionId=:sessionString ");
		return jpql.toString();
	}
    
    @Override
	public int updateUserAuthTokenSession(String authToken) {
		StringBuilder jpql=new StringBuilder(" Update UserSession u set u.logoutTime=:logoutTime , u.isActive='N' ")
		.append(" where u.authToken=:authToken ");
		Query query=getEntityManager().createQuery(jpql.toString());
		query.setParameter("logoutTime", new Date());
		query.setParameter("authToken", authToken);
		int count=query.executeUpdate();
		return count;
	}
    
}
