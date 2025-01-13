package com.novelerp.appbase.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="m_tax_category")
public class TaxCategory extends ContextPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long taxCategoryId;
	private String name;
	private String code;
	private String description;
	
	

	@Id
	@SequenceGenerator(name="m_tax_category_seq",sequenceName="m_tax_category_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_tax_category_seq")	
	@Column(name = "m_tax_category_id", updatable=false)
	public Long getTaxCategoryId() {
		return taxCategoryId;
	}
	public void setTaxCategoryId(Long taxCategoryId) {
		this.taxCategoryId = taxCategoryId;
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

}

