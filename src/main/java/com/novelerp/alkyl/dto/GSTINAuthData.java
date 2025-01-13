package com.novelerp.alkyl.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class GSTINAuthData extends CommonContextDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ClientId;
	private String UserName;
	private String AuthToken;
	private String Sek;
	private String TokenExpiry;
	public String getClientId() {
		return ClientId;
	}
	public void setClientId(String clientId) {
		ClientId = clientId;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getAuthToken() {
		return AuthToken;
	}
	public void setAuthToken(String authToken) {
		AuthToken = authToken;
	}
	public String getSek() {
		return Sek;
	}
	public void setSek(String sek) {
		Sek = sek;
	}
	public String getTokenExpiry() {
		return TokenExpiry;
	}
	public void setTokenExpiry(String tokenExpiry) {
		TokenExpiry = tokenExpiry;
	}

}
