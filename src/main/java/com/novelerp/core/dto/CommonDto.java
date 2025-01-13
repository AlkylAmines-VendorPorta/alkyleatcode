package com.novelerp.core.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novelerp.commons.util.CommonUtil;

/**
 * Common DTO with common properties of all Data objects.
 * @author Vivek Birdi
 *
 */
public class CommonDto implements BaseDto {
	

	private static final long serialVersionUID = 1L;
	@JsonIgnore
	private String isActive;
	private Timestamp created;
	@JsonIgnore
	private Timestamp updated;
	
	private ResponseDto response;
	@JsonIgnore
	private boolean isLoadDefault;
	@JsonIgnore
	private String createdSessionId;
	@JsonIgnore
	private String updatedSessionId;
	
	public String getIsActive() {
		if(CommonUtil.isStringEmpty(isActive)){
			return "N";
		}
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Timestamp getUpdated() {
		return updated;
	}
	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}
	public ResponseDto getResponse() {
		return response;
	}
	public void setResponse(ResponseDto response) {
		this.response = response;
	}
	public boolean isLoadDefault() {
		return isLoadDefault;
	}
	public void setLoadDefault(boolean isLoadDefault) {
		this.isLoadDefault = isLoadDefault;
	}

	public String getCreatedSessionId() {
		return createdSessionId;
	}
	public void setCreatedSessionId(String createdSessionId) {
		this.createdSessionId = createdSessionId;
	}
	public String getUpdatedSessionId() {
		return updatedSessionId;
	}
	public void setUpdatedSessionId(String updatedSessionId) {
		this.updatedSessionId = updatedSessionId;
	}
	
}
