package com.novelerp.core.dto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for response message transport.
 * @author Vivek Birdi
 *
 */
public class ResponseDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6525816530064901156L;
	private boolean hasError;
	private String message;
	private List<ErrorDto> errors;
	
	public ResponseDto(){
		
	}
	
	public ResponseDto(boolean hasError, String message){
		this.hasError = hasError;
		this.message =  message;
	}

	public ResponseDto(boolean hasError, String message, List<ErrorDto> errors){
		this(hasError, message);
		this.errors =  errors;
	}

	
	public boolean isHasError() {
		return hasError;
	}
	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<ErrorDto> getErrors() {
		return errors;
	}
	public void setErrors(List<ErrorDto> errors) {
		this.errors = errors;
	}
	
}
