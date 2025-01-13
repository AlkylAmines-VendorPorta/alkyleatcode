package com.novelerp.alkyl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="t_vendor_profile_history")
public class VendorProfileHistory extends ContextPO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long vendorProfileHistoryID;
	private String module;
	private String oldValue;
	private String newValue;
	private String fieldName;
	
	@Id
	@SequenceGenerator(name = "t_vendor_profile_history_seq", sequenceName = "t_vendor_profile_history_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_vendor_profile_history_seq")
	@Column(name="t_vendor_profile_history_id",updatable = false)
	public Long getVendorProfileHistoryID() {
		return vendorProfileHistoryID;
	}
	public void setVendorProfileHistoryID(Long vendorProfileHistoryID) {
		this.vendorProfileHistoryID = vendorProfileHistoryID;
	}
	@Column(name="module")
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	@Column(name="old_value")
	public String getOldValue() {
		return oldValue;
	}
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	@Column(name="new_value")
	public String getNewValue() {
		return newValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	@Column(name="field_name")
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	
}
