package com.novelerp.core.exception;

/**
 * 
 * @author Vivek Birdi
 *
 */
public class InvalidDataException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidDataException() {
		super();
	}
	
	public InvalidDataException(String message){
		super(message);
	}
	
}
