package com.novelerp.alkyl.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

public class GSTINAuthDto extends CommonContextDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Status;
	private String ErrorDetails;
	private String InfoDtls;
	private GSTINAuthData Data;
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getErrorDetails() {
		return ErrorDetails;
	}
	public void setErrorDetails(String errorDetails) {
		ErrorDetails = errorDetails;
	}
	public String getInfoDtls() {
		return InfoDtls;
	}
	public void setInfoDtls(String infoDtls) {
		InfoDtls = infoDtls;
	}
	public GSTINAuthData getData() {
		return Data;
	}
	public void setData(GSTINAuthData data) {
		Data = data;
	}
	

}
