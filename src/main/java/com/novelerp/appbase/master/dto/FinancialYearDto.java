package com.novelerp.appbase.master.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.novelerp.appcontext.dto.CommonContextDto;
import com.novelerp.core.util.DateUtil;


public class FinancialYearDto extends CommonContextDto{
	
	private static final long serialVersionUID = 1L;
	private Long financialYearId;
	private String name;
	private String code;
	private String description;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date validfrom;
	@DateTimeFormat(pattern=DateUtil.DEFAULT_DATE_FORMAT)
	private Date validTo;
	
	
	public Long getFinancialYearId() {
		return financialYearId;
	}
	public void setFinancialYearId(Long financialYearId) {
		this.financialYearId = financialYearId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getValidfrom() {
		return validfrom;
	}
	public void setValidfrom(Date validfrom) {
		this.validfrom = validfrom;
	}
	public Date getValidTo() {
		return validTo;
	}
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
	
}
