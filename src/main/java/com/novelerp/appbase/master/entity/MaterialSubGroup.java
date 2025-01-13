package com.novelerp.appbase.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;

@Entity
@Table(name="m_material_subgroup")
public class MaterialSubGroup  extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long materialSubGroupId;
	private String name;
	private String code;
	private String description;
	private MaterialGroup materialGroup;
	
	@Id
	@SequenceGenerator(name="m_material_subgroup_seq",sequenceName="m_material_subgroup_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_material_subgroup_seq")	
	@Column(name = "m_material_subgroup_id", updatable=false)
	public Long getMaterialSubGroupId() {
		return materialSubGroupId;
	}
	public void setMaterialSubGroupId(Long materialSubGroupId) {
		this.materialSubGroupId = materialSubGroupId;
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
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_material_group_id")
	public MaterialGroup getMaterialGroup() {
		return materialGroup;
	}
	public void setMaterialGroup(MaterialGroup materialGroup) {
		this.materialGroup = materialGroup;
	}
	
}
