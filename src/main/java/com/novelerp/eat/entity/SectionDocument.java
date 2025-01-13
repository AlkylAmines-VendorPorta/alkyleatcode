/**
 * @author Ankush
 */
package com.novelerp.eat.entity;

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
@Table(name="t_section_document")
public class SectionDocument extends ContextPO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long sectionDocumentId;
	private String code;
	private String name;
	private String description;
	private TAHDRDetail tahdrDetail;
	private TAHDRMaterial tahdrMaterial;
	
	@Id
	@SequenceGenerator(name="t_section_document_seq",sequenceName="t_section_document_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="t_section_document_seq")
	@Column(name="t_section_document_id")
	public Long getSectionDocumentId() {
		return sectionDocumentId;
	}
	public void setSectionDocumentId(Long sectionDocumentId) {
		this.sectionDocumentId = sectionDocumentId;
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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_tahdr_detail_id")
	public TAHDRDetail getTahdrDetail() {
		return tahdrDetail;
	}
	public void setTahdrDetail(TAHDRDetail tahdrDetail) {
		this.tahdrDetail = tahdrDetail;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="t_tahdr_material_id")
	public TAHDRMaterial getTahdrMaterial() {
		return tahdrMaterial;
	}
	public void setTahdrMaterial(TAHDRMaterial tahdrMaterial) {
		this.tahdrMaterial = tahdrMaterial;
	}
	
}
