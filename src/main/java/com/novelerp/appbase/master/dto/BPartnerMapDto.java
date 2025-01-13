package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.UserDto;

public class BPartnerMapDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	
	private long mBPartnerMapId;
	private BPartnerDto requestPartnerId;
	private UserDto targetPartnerId;
	private String isRequestSend;
	private String isRequestApproved;
	public long getmBPartnerMapId() {
		return mBPartnerMapId;
	}
	public void setmBPartnerMapId(long mBPartnerMapId) {
		this.mBPartnerMapId = mBPartnerMapId;
	}
	
	public BPartnerDto getRequestPartnerId() {
		return requestPartnerId;
	}
	public void setRequestPartnerId(BPartnerDto requestPartnerId) {
		this.requestPartnerId = requestPartnerId;
	}

	public UserDto getTargetPartnerId() {
		return targetPartnerId;
	}
	public void setTargetPartnerId(UserDto targetPartnerId) {
		this.targetPartnerId = targetPartnerId;
	}
	public String getIsRequestSend() {
		return isRequestSend;
	}
	public void setIsRequestSend(String isRequestSend) {
		this.isRequestSend = isRequestSend;
	}
	public String getIsRequestApproved() {
		return isRequestApproved;
	}
	public void setIsRequestApproved(String isRequestApproved) {
		this.isRequestApproved = isRequestApproved;
	}
	
	
}
