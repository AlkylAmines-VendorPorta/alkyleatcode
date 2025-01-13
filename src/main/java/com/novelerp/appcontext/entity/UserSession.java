package com.novelerp.appcontext.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ad_user_session")
public class UserSession extends ContextPO {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5496298479385489310L;
	private Long userSessionId;
	private String sessionId;
	private String remoteIp;
	private Date loginTime;
	private Date logoutTime;
	private String userName;
	private String deviceType;
    private String macAddress;
    private String authToken;
	

	@Id
	@SequenceGenerator(name = "ad_user_session_seq", sequenceName = "ad_user_session_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ad_user_session_seq")
	@Column(name = "ad_user_session_id", updatable = false)
	public Long getUserSessionId() {
		return userSessionId;
	}
	public void setUserSessionId(Long userSessionId) {
		this.userSessionId = userSessionId;
	}
	@Column(name="sessionid")
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	@Column(name="remote_ip")
	public String getRemoteIp() {
		return remoteIp;
	}
	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}
	@Column(name="login_time")
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	@Column(name="logout_time")
	public Date getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}
	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="device_type")
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	@Column(name="mac_address")
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	@Column(name="auth_token")
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
}
