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
@Table(name="m_vendor_type")
public class VendorType extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long vendorTypeId;
	private String name;
	private String code;
	private String description;
	
	
	@Id
	@SequenceGenerator(name="m_vendor_type_seq",sequenceName="m_vendor_type_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_vendor_type_seq")	
	@Column(name = "m_vendor_type_id", updatable=false)
	public Long getVendorTypeId() {
		return vendorTypeId;
	}
	public void setVendorTypeId(Long vendorTypeId) {
		this.vendorTypeId = vendorTypeId;
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
