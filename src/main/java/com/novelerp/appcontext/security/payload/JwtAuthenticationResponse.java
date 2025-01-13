package com.novelerp.appcontext.security.payload;

import java.util.List;

public class JwtAuthenticationResponse {
	
    private String accessToken;
    private String tokenType = "Bearer";
    private List<Object> tilesUrl;
    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public JwtAuthenticationResponse(String accessToken,List<Object> tilesUrl) {
        this.accessToken = accessToken;
        this.tilesUrl=tilesUrl;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

	public List<Object> getTilesUrl() {
		return tilesUrl;
	}

	public void setTilesUrl(List<Object> tilesUrl) {
		this.tilesUrl = tilesUrl;
	}
    
    
    
}