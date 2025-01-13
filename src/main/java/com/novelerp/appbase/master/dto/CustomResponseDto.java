
package com.novelerp.appbase.master.dto;

import java.io.Serializable;
public class CustomResponseDto implements Serializable {

	private static final long serialVersionUID = -6956235527346229861L;

	private Object data;
	
	private String responseMsg;
	private boolean responseStatus;
	public CustomResponseDto() {
	
	}
	public CustomResponseDto(boolean responseStatus, String responseMsg ){
		this.responseStatus = responseStatus;
		this.responseMsg = responseMsg;  
	}
	
	public CustomResponseDto(Object data, String responseMsg,boolean responseStatus){
		this.data = data;
		this.responseMsg = responseMsg;  
		this.responseStatus=responseStatus;
	}
	public String getResponseMsg() {
		return responseMsg;
	}
	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public boolean isResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(boolean responseStatus) {
		this.responseStatus = responseStatus;
	}
	
	
	
}
