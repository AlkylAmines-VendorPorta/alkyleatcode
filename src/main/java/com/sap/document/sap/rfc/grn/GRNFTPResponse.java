package com.sap.document.sap.rfc.grn;

import javax.xml.bind.annotation.XmlElement;

public class GRNFTPResponse {
	@XmlElement(name = "MAT_DOC", required = true)
	private String mat_doc;
	@XmlElement(name = "DOC_YEAR", required = true)
	private String doc_year;
	@XmlElement(name = "ASNNO", required = true)
	private Integer asnno;
	@XmlElement(name = "MOV_TYPE", required = true)
	private String mov_typ;
	public String getMat_doc() {
		return mat_doc;
	}
	public void setMat_doc(String mat_doc) {
		this.mat_doc = mat_doc;
	}
	public String getDoc_year() {
		return doc_year;
	}
	public void setDoc_year(String doc_year) {
		this.doc_year = doc_year;
	}
	public Integer getAsnno() {
		return asnno;
	}
	public void setAsnno(Integer asnno) {
		this.asnno = asnno;
	}
	public String getMov_typ() {
		return mov_typ;
	}
	public void setMov_typ(String mov_typ) {
		this.mov_typ = mov_typ;
	}

	
	
	
}
