package com.novelerp.appbase.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="m_tax")
public class Tax extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long taxId;
	private String name;
	private String code;
	private String description;
	private TaxCategory taxcategory;
	
	
	@Id
	@SequenceGenerator(name="m_tax_seq",sequenceName="m_tax_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_tax_seq")	
	@Column(name = "m_tax_id", updatable=false)
	public Long getTaxId() {
		return taxId;
	}
	public void setTaxId(Long taxId) {
		this.taxId = taxId;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "value")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_tax_category_id")
	public TaxCategory getTaxcategory() {
		return taxcategory;
	}
	public void setTaxcategory(TaxCategory taxcategory) {
		this.taxcategory = taxcategory;
	}
	
}

