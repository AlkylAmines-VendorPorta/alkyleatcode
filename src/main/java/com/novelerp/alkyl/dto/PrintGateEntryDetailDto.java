package com.novelerp.alkyl.dto;

import java.util.Date;
import java.util.List;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.appcontext.dto.UserDto;

public class PrintGateEntryDetailDto extends CommonContextDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long printGateEntryDetialsId;
	private String docType;
	private String objectNo;
	private String plant;
	public Long getPrintGateEntryDetialsId() {
		return printGateEntryDetialsId;
	}
	public void setPrintGateEntryDetialsId(Long printGateEntryDetialsId) {
		this.printGateEntryDetialsId = printGateEntryDetialsId;
	}
	
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getObjectNo() {
		return objectNo;
	}
	public void setObjectNo(String objectNo) {
		this.objectNo = objectNo;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	
	
}
