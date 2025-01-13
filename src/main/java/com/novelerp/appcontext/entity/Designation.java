package com.novelerp.appcontext.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="m_designation")
public class Designation extends ContextPO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long designationId;
	private String name;
	private String code;
	private String description;
	private String isBPDesignation;
	
	
	@Id
	@SequenceGenerator(name="m_designation_seq",sequenceName="m_designation_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_designation_seq")	
	@Column(name = "m_designation_id", updatable=false)
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
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
	@Column(name="is_bp_designation")
	public String getIsBPDesignation() {
		return isBPDesignation;
	}
	public void setIsBPDesignation(String isBPDesignation) {
		this.isBPDesignation = isBPDesignation;
	}
	
	
	

}

