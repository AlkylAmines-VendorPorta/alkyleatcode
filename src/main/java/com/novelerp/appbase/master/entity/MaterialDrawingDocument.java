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
 * 
 * @author Varsha Patil
 *
 */
@Entity
@Table(name="m_material_drawing_document")
public class MaterialDrawingDocument extends ContextPO {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long drawingDocumentId;
    private String docName;
    private Material material;
    
    @Id
	@SequenceGenerator(name="m_material_drawing_document_seq",sequenceName="m_material_drawing_document_seq", allocationSize=1)	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="m_material_drawing_document_seq")	
	@Column(name = "m_material_drawing_document_id", updatable=false)
	public Long getDrawingDocumentId() {
		return drawingDocumentId;
	}
	public void setDrawingDocumentId(Long drawingDocumentId) {
		this.drawingDocumentId = drawingDocumentId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="m_material_id")
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	@Column(name="doc_name")
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
    
}
