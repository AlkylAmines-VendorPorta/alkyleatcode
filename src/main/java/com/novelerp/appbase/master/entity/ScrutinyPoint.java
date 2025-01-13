package com.novelerp.appbase.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

/**
 * 
 * @author Aman Sahu
 *
 */
@Entity
@Table(name="m_scrutiny_point")
public class ScrutinyPoint extends ContextPO{

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long scrutinyPointId;
	private String code;
	private String name;
	private String description;
	private Long serialNo;
	
	@Id
	@SequenceGenerator(name="m_scrutiny_point_seq",sequenceName="m_scrutiny_point_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_scrutiny_point_seq")	
	@Column(name = "m_scrutiny_point_id", updatable=false)
	public Long getScrutinyPointId() {
		return scrutinyPointId;
	}
	public void setScrutinyPointId(Long scrutinyPointId) {
		this.scrutinyPointId = scrutinyPointId;
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
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="serial_no")
	public Long getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}
	
	
}
