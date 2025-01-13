package com.novelerp.appcontext.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="m_bpartner_group")
public class BPartnerGroup extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long bPartnerGroupId;
	private String name;
	private String code;
	private String description;

	
	@Id
	@SequenceGenerator(name="m_bpartner_group_seq",sequenceName="m_bpartner_group_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bpartner_group_seq")	
	@Column(name = "m_bpartner_group_id", updatable=false)
	public Long getbPartnerGroupId() {
		return bPartnerGroupId;
	}
	public void setbPartnerGroupId(Long bPartnerGroupId) {
		this.bPartnerGroupId = bPartnerGroupId;
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

