package com.novelerp.eat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name = "m_gst_vendor_class")
public class GSTVendorClass extends ContextPO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long gstVendorClassId;
	private String code;
	private String name;

	@Id
	@SequenceGenerator(name = "m_gst_vendor_class_seq", sequenceName = "m_gst_vendor_class_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_gst_vendor_class_seq")
	@Column(name = "m_gst_vendor_class_id", updatable = false)
	public Long getGstVendorClassId() {
		return gstVendorClassId;
	}

	public void setGstVendorClassId(Long gstVendorClassId) {
		this.gstVendorClassId = gstVendorClassId;
	}

	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
