package com.sap.document.sap.rfc.ses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "YSERVICE", propOrder = {
    "costcenter",
    "qty",
    "storage"
})
public class YSERVICECOSTCENTER {
	 @XmlElement(name = "COSTCENTER", required = true)
	    protected String costcenter;
	 @XmlElement(name = "QTY", required = true)
	    protected String qty;
	 @XmlElement(name = "STORAGE", required = true)
	    protected String storage;
//	 @XmlElement(name = "FUNDCENTER", required = true)
//	    protected String fundcenter;
	public String getCostcenter() {
		return costcenter;
	}
	public void setCostcenter(String costcenter) {
		this.costcenter = costcenter;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
//	public String getFundcenter() {
//		return fundcenter;
//	}
//	public void setFundcenter(String fundcenter) {
//		this.fundcenter = fundcenter;
//	}
	
	 
}
