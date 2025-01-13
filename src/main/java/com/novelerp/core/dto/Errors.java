package com.novelerp.core.dto;

import java.util.ArrayList;
import java.util.List;

import com.novelerp.commons.util.CommonUtil;

public class Errors {

	private List<ErrorDto> errorList = new ArrayList<>();
	
	public List<ErrorDto> getErrorList() {
		return errorList;
	}

	public void addError(String errorCode, String message){	
		ErrorDto error =  new ErrorDto(errorCode, message);
		errorList.add(error);
	}
	
	public int getErrorCount(){
		return errorList.size();
	}
	
	public String getErrorString(){
		if(CommonUtil.isCollectionEmpty(this.errorList)){
			return "";
		}
		StringBuilder errorString = new StringBuilder();
		int i=0;
		for(ErrorDto error : this.errorList){
			if(i==0){
				errorString.append(error.getErrorMessage()+";");
				i++;
			}else{
				errorString.append("\n "+error.getErrorMessage()+";");
			}
			
		}
		return errorString.toString();
	}
	
	public String getErrorStringWithCode(){
		if(CommonUtil.isCollectionEmpty(this.errorList)){
			return "";
		}
		StringBuilder errorString = new StringBuilder();
		int i=0;
		for(ErrorDto error : this.errorList){
			if(i==0){
				errorString.append(error.getErrorCode()+" - "+error.getErrorMessage()+";");
				i++;
			}else{
				errorString.append("\n "+error.getErrorCode()+" - "+error.getErrorMessage()+";");
			}
			
		}
		return errorString.toString();
	}
	
}
