package com.novelerp.appbase.master.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class ResponseDto implements Serializable {

	private static final long serialVersionUID = -6956235527346229861L;

	private Integer responseCode;
	private String responseMsg;
	private String timestamp;
	private Long recordId;
	public ResponseDto() {
	
	}
	public ResponseDto(Integer responseCode, String responseMsg ){
		this.responseCode = responseCode;
		this.responseMsg = responseMsg;  
	}
	
	public ResponseDto(Integer responseCode, String responseMsg, Date responsetime ){
		this.responseCode = responseCode;
		this.responseMsg = responseMsg;  
		this.timestamp = String.valueOf(new Timestamp(responsetime.getTime()));
	}
	
	public ResponseDto(Integer responseCode, String responseMsg, Long recordId ){
		this.responseCode = responseCode;
		this.responseMsg = responseMsg;  
		this.recordId = recordId;
	}
	
	public Integer getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMsg() {
		return responseMsg;
	}
	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getRecordId() {
		return recordId;
	}
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	
	


}
