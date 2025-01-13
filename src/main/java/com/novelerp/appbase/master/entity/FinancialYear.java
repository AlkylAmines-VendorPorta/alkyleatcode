package com.novelerp.appbase.master.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="m_financial_year")
public class FinancialYear extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long financialYearId;
	private String name;
	private String code;
	private String description;
	private Date validfrom;
	private Date validTo;
	
	
	

	@Id
	@SequenceGenerator(name="m_financial_year_seq",sequenceName="m_financial_year_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_financial_year_seq")	
	@Column(name = "m_financial_year_id", updatable=false)
	public Long getFinancialYearId() {
		return financialYearId;
	}
	public void setFinancialYearId(Long financialYearId) {
		this.financialYearId = financialYearId;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="value")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="validfrom")
	public Date getValidfrom() {
		return validfrom;
	}
	public void setValidfrom(Date validfrom) {
		this.validfrom = validfrom;
	}
	@Column(name="validto")
	public Date getValidTo() {
		return validTo;
	}
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
	
	
	
	

}
