package com.novelerp.appcontext.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.dto.CommonContextDto;

@Entity
@Table(name="ad_form_tab")
public class FormTab extends CommonContextDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9196764014045984556L;
	private Long formTabId;	
	private String code;	
	private String name;
	private String formType;

	@Id
	@SequenceGenerator(name = "ad_form_tab_seq", sequenceName = "ad_form_tab_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ad_form_tab_seq")
	@Column(name = "ad_form_tab_id", updatable = false)
	public Long getFormTabId() {
		return formTabId;
	}
	public void setFormTabId(Long formTabId) {
		this.formTabId = formTabId;
	}
	@Column(name="value")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="form_type")
	public String getFormType() {
		return formType;
	}
	public void setFormType(String formType) {
		this.formType = formType;
	}
	
}
