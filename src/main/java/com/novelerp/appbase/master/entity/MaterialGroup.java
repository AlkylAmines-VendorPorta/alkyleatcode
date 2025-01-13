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
@Table(name="m_material_group")
public class MaterialGroup  extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long materialGroupId;
	private String name;
	private String code;
	private String description;
	
	@Id
	@SequenceGenerator(name="m_material_group_seq",sequenceName="m_material_group_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_material_group_seq")	
	@Column(name = "m_material_group_id", updatable=false)
	public Long getMaterialGroupId() {
		return materialGroupId;
	}
	public void setMaterialGroupId(Long materialGroupId) {
		this.materialGroupId = materialGroupId;
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
