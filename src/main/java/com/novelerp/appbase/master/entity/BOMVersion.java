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

/**
 * Ankita
 */

@Entity
@Table(name="m_bom_version")
public class BOMVersion extends ContextPO {

	
	private static final long serialVersionUID = 1L;
	private Long bomVersionId;
	private String name;
	private Material material;
	
	@Id
	@SequenceGenerator(name="m_bom_version_seq",sequenceName="m_bom_version_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_bom_version_seq")	
	@Column(name = "m_bom_version_id", updatable=false)
	public Long getBomVersionId() {
		return bomVersionId;
	}
	public void setBomVersionId(Long bomVersionId) {
		this.bomVersionId = bomVersionId;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_material_id")
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	
		
}
