package com.novelerp.appcontext.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ad_query_master")
public class QueryMaster  extends ContextPO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long adQueryMasterId;
    private String name;
	private String value;
	
	@Id
	@SequenceGenerator(name="ad_query_master_seq",sequenceName="ad_query_master_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="ad_query_master_seq")	
	@Column(name = "ad_query_master_id", updatable=false)
	public Long getAdQueryMasterId() {
		return adQueryMasterId;
	}
	public void setAdQueryMasterId(Long adQueryMasterId) {
		this.adQueryMasterId = adQueryMasterId;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="value")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	

}
