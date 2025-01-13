package com.novelerp.appcontext.dao;

import com.novelerp.appcontext.dto.UserSessionDto;
import com.novelerp.appcontext.entity.UserSession;
import com.novelerp.core.dao.CommonDao;

public interface UserSessionDao extends CommonDao<UserSession, UserSessionDto>{

	public int updateUserSession(String sessionId);
	public String getSessionAuditCountQry(String searchColumn);
	public String getSessionListQuery(String searchColumn, String searchValue);
	public int updateUserAuthTokenSession(String authToken);
}
