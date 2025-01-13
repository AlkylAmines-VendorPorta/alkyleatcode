package com.sap.document.sap.rfc.functions;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Z_QCF_VENDOR", propOrder = {
    "vendorname",
    "validitydatefrom",
    "validitydateto",
    "incoterms",
    "incodescription",
    "quotationrefno",
    "quotationrefdate",
    "rfqline"
   
})
public class ZQCFVENDOR_Type {
	 @XmlElement(name = "VENDOR_NAME", required = true)
	    protected String vendorname;
	    @XmlElement(name = "VALIDITYDATEFROM", required = true)
	    protected String validitydatefrom;
	    @XmlElement(name = "VALIDITYDATETO", required = true)
	    protected String validitydateto;
	    @XmlElement(name = "INCOTERMS", required = true)
	    protected String incoterms;
	    @XmlElement(name = "INCODESCRIPTION", required = true)
	    protected String incodescription;
	    @XmlElement(name = "QUOTATIONREFNO", required = true)
	    protected String quotationrefno;
	    @XmlElement(name = "QUOTATIONREFDATE", required = true)
	    protected String quotationrefdate;
	 @XmlElement(name = "RFQLINE", required = true)
	    protected List<ZQCFRFQLINE_Type> rfqline;
	

	public String getVendorname() {
		return vendorname;
	}

	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}
	
	public String getValiditydatefrom() {
		return validitydatefrom;
	}

	public String getValiditydateto() {
		return validitydateto;
	}

	public void setValiditydatefrom(String validitydatefrom) {
		this.validitydatefrom = validitydatefrom;
	}

	public void setValiditydateto(String validitydateto) {
		this.validitydateto = validitydateto;
	}
	
	

	public String getIncoterms() {
		return incoterms;
	}

	public void setIncoterms(String incoterms) {
		this.incoterms = incoterms;
	}

	
	public String getIncodescription() {
		return incodescription;
	}

	public void setIncodescription(String incodescription) {
		this.incodescription = incodescription;
	}

	public String getQuotationrefno() {
		return quotationrefno;
	}

	public String getQuotationrefdate() {
		return quotationrefdate;
	}

	public void setQuotationrefno(String quotationrefno) {
		this.quotationrefno = quotationrefno;
	}

	public void setQuotationrefdate(String quotationrefdate) {
		this.quotationrefdate = quotationrefdate;
	}

	public List<ZQCFRFQLINE_Type> getRfqline() {
		return rfqline;
	}

	public void setRfqline(List<ZQCFRFQLINE_Type> rfqline) {
		this.rfqline = rfqline;
	}


	 
	 

}
