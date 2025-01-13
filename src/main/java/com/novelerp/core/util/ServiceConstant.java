package com.novelerp.core.util;

/**
 * 
 * @author Vivek Birdi
 *
 */
public class ServiceConstant {

	private ServiceConstant(){
		
	}
	
	public static final int INTERNAL_ERROR =500;
	public static final int NOT_ACCESSIBLE= 400;
	public static final Integer SUCESS=200;
	public static final Integer VALIDATION_FAILURE=800;
	
	public static final String MANDATORY_FIELD_MSG = "Field is mandatory.";
	public static final String INVALID_CHANGE_MSG = "Invalid change by client.";
	
	public static final String ORM_UTIL_HIBERNATE = "ORM_UTIL_HIBERNATE";

}

