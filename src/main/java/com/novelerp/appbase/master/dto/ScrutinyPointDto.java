package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

/**
 * 
 * @author Aman Sahu
 *
 */
public class ScrutinyPointDto extends CommonContextDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long scrutinyPointId;
	private String code;
	private String name;
	private String description;
	private Long serialNo;
	
	public Long getScrutinyPointId() {
		return scrutinyPointId;
	}
	public void setScrutinyPointId(Long scrutinyPointId) {
		this.scrutinyPointId = scrutinyPointId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}
	
	
	
}
