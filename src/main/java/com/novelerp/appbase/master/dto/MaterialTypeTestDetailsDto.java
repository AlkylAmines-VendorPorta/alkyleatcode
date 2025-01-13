package com.novelerp.appbase.master.dto;

import com.novelerp.appcontext.dto.CommonContextDto;

/**
 * 
 * @author Varsha Patil
 *
 */
public class MaterialTypeTestDetailsDto extends CommonContextDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long typeTestDetailsId;
    private String docName;
    private MaterialDto material;
	public Long getTypeTestDetailsId() {
		return typeTestDetailsId;
	}
	public void setTypeTestDetailsId(Long typeTestDetailsId) {
		this.typeTestDetailsId = typeTestDetailsId;
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
