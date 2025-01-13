package com.sap.document.sap.rfc.functions;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Z_QCF_ANNEXURE", propOrder = {
    "enqno",
    "qcfno",
//    "enqtype",
    "enqdate",
    "enqdeadline",
    "purchaseorg",
    "collectiveno",
    "vendordata",
    "approvedbyemailid",    
     "status",
     "cclistemailid"
})
public class ZQCFAnnexute_Type {
	 @XmlElement(name = "EnqNo", required = true)
	    protected String enqno;
	 @XmlElement(name = "QCFNo", required = true)
	    protected String qcfno;
//	 @XmlElement(name = "EnqType", required = true)
//	    protected String enqtype;
	 @XmlElement(name = "EnqDate", required = true)
	    protected String enqdate;
	 @XmlElement(name = "EnqDeadline", required = true)
	    protected String enqdeadline;
	 @XmlElement(name = "PurchaseOrg", required = true)
	    protected String purchaseorg;
	 @XmlElement(name = "CollectiveNo", required = true)
	    protected String collectiveno;
	 @XmlElement(name = "VendorData", required = true)
	    protected List<ZQCFVENDOR_Type> vendordata;
	 @XmlElement(name = "ApprovedbyEmailId", required = true)
	    protected String approvedbyemailid;
	 @XmlElement(name = "CCListEmailId", required = true)
	    protected List<String> cclistemailid;
	
	 @XmlElement(name = "Status", required = true)
	    protected String status;
	public String getEnqno() {
		return enqno;
	}
	public void setEnqno(String enqno) {
		this.enqno = enqno;
	}
//	public String getEnqtype() {
//		return enqtype;
//	}
//	public void setEnqtype(String enqtype) {
//		this.enqtype = enqtype;
//	}
	public String getEnqdate() {
		return enqdate;
	}
	public void setEnqdate(String enqdate) {
		this.enqdate = enqdate;
	}
	public String getEnqdeadline() {
		return enqdeadline;
	}
	public void setEnqdeadline(String enqdeadline) {
		this.enqdeadline = enqdeadline;
	}
	public String getPurchaseorg() {
		return purchaseorg;
	}
	public void setPurchaseorg(String purchaseorg) {
		this.purchaseorg = purchaseorg;
	}
	public String getCollectiveno() {
		return collectiveno;
	}
	public void setCollectiveno(String collectiveno) {
		this.collectiveno = collectiveno;
	}
	public List<ZQCFVENDOR_Type> getVendordata() {
		return vendordata;
	}
	public void setVendordata(List<ZQCFVENDOR_Type> vendordata) {
		this.vendordata = vendordata;
	}
	public String getApprovedbyemailid() {
		return approvedbyemailid;
	}
	public void setApprovedbyemailid(String approvedbyemailid) {
		this.approvedbyemailid = approvedbyemailid;
	}
	public String getQcfno() {
		return qcfno;
	}
	public void setQcfno(String qcfno) {
		this.qcfno = qcfno;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getCclistemailid() {
		return cclistemailid;
	}
	public void setCclistemailid(List<String> cclistemailid) {
		this.cclistemailid = cclistemailid;
	}
	
	
	
	 
}
