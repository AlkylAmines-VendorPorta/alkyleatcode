package com.novelerp.appcontext.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.core.util.DateUtil;

public class UserSessionDto extends CommonContextDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7200654999684501666L;
	private Long userSessionId;
	private String sessionId;
	private String remoteIp;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date loginTime;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATETIME_FORMAT)
	private Date logoutTime;
    private String userName;
    private String deviceType;
    private String macAddress;
    private String authToken;
	public Long getUserSessionId() {
		return userSessionId;
	}
	public void setUserSessionId(Long userSessionId) {
		this.userSessionId = userSessionId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getRemoteIp() {
		return remoteIp;
	}
	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public Date getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	

}
