package com.novelerp.core.exception;

public class CustomException extends RuntimeException{

	/** 
	 * Aman 
	 */
	private static final long serialVersionUID = 1L;

	public CustomException() {
		super();
	}
	
	public CustomException(String message){
		super(message);
	}
	
}
