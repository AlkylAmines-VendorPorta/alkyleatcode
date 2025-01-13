package com.novelerp.appbase.master.entity;

import java.math.BigDecimal;

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
@Table(name="m_material_specification")
public class MaterialSpecification extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long materialSpecificationId;
	private String name;
	private String code;
	private String description;
	private BOMVersion material;
	/*private String specification;*/
	private Material specification;
	private BigDecimal quantity;
	
	@Id
	@SequenceGenerator(name="m_material_specification_seq",sequenceName="m_material_specification_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_material_specification_seq")	
	@Column(name = "m_material_specification_id", updatable=false)
	public Long getMaterialSpecificationId() {
		return materialSpecificationId;
	}
	public void setMaterialSpecificationId(Long materialSpecificationId) {
		this.materialSpecificationId = materialSpecificationId;
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
	@JoinColumn(name="m_bom_version_id")
	public BOMVersion getMaterial() {
		return material;
	}
	public void setMaterial(BOMVersion material) {
		this.material = material;
	}
	/*@Column(name = "specification")
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}*/
	@Column(name = "quantity")
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="specification",referencedColumnName="m_material_id")
	public Material getSpecification() {
		return specification;
	}
	public void setSpecification(Material specification) {
		this.specification = specification;
	}
	
	
	
	
	
}
