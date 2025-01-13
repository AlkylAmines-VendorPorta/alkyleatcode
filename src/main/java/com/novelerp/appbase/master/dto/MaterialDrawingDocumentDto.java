package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

/**
 * 
 * @author Varsha Patil
 *
 */
public class MaterialDrawingDocumentDto extends CommonContextDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long drawingDocumentId;
    private String docName;
    private MaterialDto material;
	public Long getDrawingDocumentId() {
		return drawingDocumentId;
	}
	public void setDrawingDocumentId(Long drawingDocumentId) {
		this.drawingDocumentId = drawingDocumentId;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public MaterialDto getMaterial() {
		return material;
	}
	public void setMaterial(MaterialDto material) {
		this.material = material;
	}
    
}
