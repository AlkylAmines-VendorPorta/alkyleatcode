package com.novelerp.appcontext.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.novelerp.appcontext.dto.UserDto;
import com.novelerp.appcontext.dto.UserSessionDto;
import com.novelerp.appcontext.entity.UserSession;
import com.novelerp.core.service.CommonService;

public interface UserSessionService extends CommonService<UserSession, UserSessionDto>{

	public UserSessionDto updateUserSession(String sessionId);
	public UserSessionDto saveUserSession(HttpServletRequest request,UserDto dto);
	public long getSessionAuditsQueryCount(String searchColumn, String searchValue);
	public List<UserSessionDto> getSessionAuditList(int pageNumber, int pageSize, String searchColumn, String searchValue);
	UserSessionDto updateUserAuthTokenSession(String authToken);
}
