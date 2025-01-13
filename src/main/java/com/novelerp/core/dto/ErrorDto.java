package com.novelerp.core.dto;

/**
 * Dto for Error message transport
 * @author Vivek Birdi
 *
 */
public class ErrorDto {

	private int code;
	private String errorMessage;
	private String errorCode;
	
	public ErrorDto(){
		
	}
	
	public ErrorDto(int code, String errorMessage){
		this.code = code;
		this.errorMessage =  errorMessage;
	}
	
	public ErrorDto(String errorCode, String errorMessage){
		this.errorCode = errorCode;
		this.errorMessage =  errorMessage;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}
