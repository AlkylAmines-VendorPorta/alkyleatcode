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
@Table(name="m_documenttype")
public class DocumentType extends ContextPO{
	
	private static final long serialVersionUID = 1L;
	private Long documentTypeId;
	private String name;
	private String code;
	private String description;
	
	
	@Id
	@SequenceGenerator(name="m_documenttype_seq",sequenceName="m_documenttype_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_documenttype_seq")	
	@Column(name = "m_documenttype_id", updatable=false)
	public Long getDocumentTypeId() {
		return documentTypeId;
	}
	public void setDocumentTypeId(Long documentTypeId) {
		this.documentTypeId = documentTypeId;
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

