package com.novelerp.appbase.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.novelerp.appcontext.entity.ContextPO;
@Entity
@Table(name="m_material_version")
public class MaterialVersion extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long materialVersionId;
	private String name;
	private String code;
	private String description;
	private Material material;
	private Attachment versionSpecification;
	
	@Id
	@SequenceGenerator(name="m_material_version_seq",sequenceName="m_material_version_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_material_version_seq")	
	@Column(name = "m_material_version_id", updatable=false)
	public Long getMaterialVersionId() {
		return materialVersionId;
	}
	public void setMaterialVersionId(Long materialVersionId) {
		this.materialVersionId = materialVersionId;
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
	@JoinColumn(name="m_material_id")
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="version_specification",referencedColumnName="m_attachment_id")
	public Attachment getVersionSpecification() {
		if(versionSpecification==null || versionSpecification.getAttachmentId()==null)
		{
			return null;
		}
		return versionSpecification;
	}
	public void setVersionSpecification(Attachment versionSpecification) {
		this.versionSpecification = versionSpecification;
	}
}
