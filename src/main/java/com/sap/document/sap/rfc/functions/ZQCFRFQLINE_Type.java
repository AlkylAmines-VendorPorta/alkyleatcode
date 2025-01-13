package com.sap.document.sap.rfc.functions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Z_QCF_RFQ_LINE", propOrder = {
    "material",
    "rfqitem",
    "rfqqty",
    "uom",
    "deliverydate",
    "matgrp",
    "netprice",
    "taxcode",
    "prno",
    "pritem",
    "prdate",
    "freighttype",
    "freightvalue",
    "pftype",
    "pfvalue",
    "othertype",
    "othervalue",
    "purchasinggroup",
    "enqtype",
})
public class ZQCFRFQLINE_Type {
	 @XmlElement(name = "MATERIAL", required = true)
	    protected String material;
	 @XmlElement(name = "RFQ_ITEM", required = true)
	    protected String rfqitem;
	 @XmlElement(name = "RFQ_QTY", required = true)
	    protected String rfqqty;
	 @XmlElement(name = "UOM", required = true)
	    protected String uom;
	 @XmlElement(name = "DELIVERYDATE", required = true)
	    protected String deliverydate;
	 @XmlElement(name = "MATGRP", required = true)
	    protected String matgrp;
	 @XmlElement(name = "NETPRICE", required = true)
	    protected String netprice;
	 @XmlElement(name = "TAXCODE", required = true)
	    protected String taxcode;
	 @XmlElement(name = "PRNO", required = true)
	    protected String prno;
	 @XmlElement(name = "PRITEM", required = true)
	    protected String pritem;
	 @XmlElement(name = "PRDATE", required = true)
	    protected String prdate;
	 @XmlElement(name = "FREIGHTTYPE", required = true)
	    protected String freighttype;
	 @XmlElement(name = "FREIGHTVALUE", required = true)
	    protected String freightvalue;
	 @XmlElement(name = "PFTYPE", required = true)
	    protected String pftype;
	 @XmlElement(name = "PFVALUE", required = true)
	    protected String pfvalue;
	 @XmlElement(name = "OTHERTYPE", required = true)
	    protected String othertype;
	 @XmlElement(name = "OTHERVALUE", required = true)
	    protected String othervalue;
	 @XmlElement(name = "PURCHASINGGROUP", required = true)
	    protected String purchasinggroup;
	 @XmlElement(name = "EnqType", required = true)
	    protected String enqtype;
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getRfqitem() {
		return rfqitem;
	}
	public void setRfqitem(String rfqitem) {
		this.rfqitem = rfqitem;
	}
	public String getRfqqty() {
		return rfqqty;
	}
	public void setRfqqty(String rfqqty) {
		this.rfqqty = rfqqty;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getDeliverydate() {
		return deliverydate;
	}
	public void setDeliverydate(String deliverydate) {
		this.deliverydate = deliverydate;
	}
	public String getMatgrp() {
		return matgrp;
	}
	public void setMatgrp(String matgrp) {
		this.matgrp = matgrp;
	}
	public String getNetprice() {
		return netprice;
	}
	public void setNetprice(String netprice) {
		this.netprice = netprice;
	}
	public String getTaxcode() {
		return taxcode;
	}
	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
	}
	public String getPrno() {
		return prno;
	}
	public void setPrno(String prno) {
		this.prno = prno;
	}
	public String getPritem() {
		return pritem;
	}
	public void setPritem(String pritem) {
		this.pritem = pritem;
	}
	public String getPrdate() {
		return prdate;
	}
	public void setPrdate(String prdate) {
		this.prdate = prdate;
	}
	public String getFreighttype() {
		return freighttype;
	}
	public void setFreighttype(String freighttype) {
		this.freighttype = freighttype;
	}
	public String getFreightvalue() {
		return freightvalue;
	}
	public void setFreightvalue(String freightvalue) {
		this.freightvalue = freightvalue;
	}
	public String getPftype() {
		return pftype;
	}
	public void setPftype(String pftype) {
		this.pftype = pftype;
	}
	public String getPfvalue() {
		return pfvalue;
	}
	public void setPfvalue(String pfvalue) {
		this.pfvalue = pfvalue;
	}
	public String getOthertype() {
		return othertype;
	}
	public void setOthertype(String othertype) {
		this.othertype = othertype;
	}
	public String getOthervalue() {
		return othervalue;
	}
	public void setOthervalue(String othervalue) {
		this.othervalue = othervalue;
	}
	public String getPurchasinggroup() {
		return purchasinggroup;
	}
	public void setPurchasinggroup(String purchasinggroup) {
		this.purchasinggroup = purchasinggroup;
	}
	public String getEnqtype() {
		return enqtype;
	}
	public void setEnqtype(String enqtype) {
		this.enqtype = enqtype;
	}
	 
	 
}
