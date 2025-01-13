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
@Table(name="m_gtp_parameter")
public class GtpParameter extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long gtpParameterId;
	private String name;
	private String code;
	private String description;
	private GtpParameterType gtpParameterType;
	private Material material;
	private String isCopied;
	
	@Id
	@SequenceGenerator(name="m_gtp_parameter_seq",sequenceName="m_gtp_parameter_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_gtp_parameter_seq")	
	@Column(name = "m_gtp_parameter_id", updatable=false)
	public Long getGtpParameterId() {
		return gtpParameterId;
	}

	public void setGtpParameterId(Long gtpParameterId) {
		this.gtpParameterId = gtpParameterId;
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
	@JoinColumn(name="m_gtp_parametertype_id")
	public GtpParameterType getGtpParameterType() {
		return gtpParameterType;
	}

	public void setGtpParameterType(GtpParameterType gtpParameterType) {
		this.gtpParameterType = gtpParameterType;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="m_material_id")
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	@Column(name = "isCopied")
	public String getIsCopied() {
		return isCopied;
	}

	public void setIsCopied(String isCopied) {
		this.isCopied = isCopied;
	}
	
}


