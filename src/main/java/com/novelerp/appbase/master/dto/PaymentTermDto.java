package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;
/**
 * @author Iqval Singh
 * (Changed By Vivek Birdi)
 */
public class PaymentTermDto extends CommonContextDto{

	private static final long serialVersionUID = 1L;
	private Long paymenttermId;
	private String name;
	private String code;
	private String description;
	
	
	public Long getPaymenttermId() {
		return paymenttermId;
	}
	public void setPaymenttermId(Long paymenttermId) {
		this.paymenttermId = paymenttermId;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}