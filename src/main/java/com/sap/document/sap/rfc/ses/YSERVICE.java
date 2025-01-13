
package com.sap.document.sap.rfc.ses;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for YSERVICE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="YSERVICE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SERVICECODE" type="{urn:sap-com:document:sap:rfc:functions}char18"/>
 *         &lt;element name="POLINELITEM" type="{urn:sap-com:document:sap:rfc:functions}numeric10"/>
 *         &lt;element name="SERVICELINEITEM" type="{urn:sap-com:document:sap:rfc:functions}numeric10"/>
 *         &lt;element name="PONO" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="WORKDONE" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="UOM" type="{urn:sap-com:document:sap:rfc:functions}unit3"/>
 *         &lt;element name="PRICE" type="{urn:sap-com:document:sap:rfc:functions}decimal23.4"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "YSERVICE", propOrder = {
    "servicecode",
    "polinelitem",
    "servicelineitem",
    "pono",
    "workdone",
    "uom",
    "price",
    "invoiceNo",
    "invoiceDate",
    "invoiceAmount",
    "postingdate",
    "costCenter",
    "linecostcenter",
    "ssnCreationDate",
    "ssnCreationEmpCode",
    "ssnApprovalEmpCode",
    "serviceLocation",
    "serviceFromDate",
    "serviceToDate",
    "ssnNo"
    
})
public class YSERVICE {

    @XmlElement(name = "SERVICECODE", required = true)
    protected String servicecode;
    @XmlElement(name = "POLINELITEM", required = true)
    protected String polinelitem;
    @XmlElement(name = "SERVICELINEITEM", required = true)
    protected String servicelineitem;
    @XmlElement(name = "PONO", required = true)
    protected String pono;
    @XmlElement(name = "WORKDONE", required = true)
    protected BigDecimal workdone;
    @XmlElement(name = "UOM", required = true)
    protected String uom;
    @XmlElement(name = "PRICE", required = true)
    protected BigDecimal price;
    @XmlElement(name = "GLNO", required = true)
    protected String glno;
    @XmlElement(name = "INVOICENO", required = true)
    protected String invoiceNo;
    @XmlElement(name = "INVOICEDATE", required = true)
    protected String invoiceDate;
    @XmlElement(name = "INVOICEAMOUNT", required = true)
    protected String invoiceAmount;
    @XmlElement(name = "POSTINGDATE", required = true)
    protected String postingdate;
    @XmlElement(name = "COSTCENTER", required = true)
    protected String costCenter;
    @XmlElement(name = "LINECOSTCENTER", required = true)
    protected List<YSERVICECOSTCENTER> linecostcenter;
    @XmlElement(name = "SSNCREATIONDATE", required = true)
    protected String ssnCreationDate;
    @XmlElement(name = "SSNCREATIONEMPCODE", required = true)
    protected String ssnCreationEmpCode;
    @XmlElement(name = "SSNAPPROVALEMPCODE", required = true)
    protected String ssnApprovalEmpCode;
    
    @XmlElement(name = "SERVICELOCATION", required = true)
    protected String serviceLocation;
    @XmlElement(name = "SERVICEFROMDATE", required = true)
    protected String serviceFromDate;
    @XmlElement(name = "SERVICETODATE", required = true)
    protected String serviceToDate;
    @XmlElement(name = "SERVICENO", required = true)
    protected String ssnNo;
    /**
     * Gets the value of the servicecode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSERVICECODE() {
        return servicecode;
    }

    /**
     * Sets the value of the servicecode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSERVICECODE(String value) {
        this.servicecode = value;
    }

    /**
     * Gets the value of the polinelitem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPOLINELITEM() {
        return polinelitem;
    }

    /**
     * Sets the value of the polinelitem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPOLINELITEM(String value) {
        this.polinelitem = value;
    }

    /**
     * Gets the value of the servicelineitem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSERVICELINEITEM() {
        return servicelineitem;
    }

    /**
     * Sets the value of the servicelineitem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSERVICELINEITEM(String value) {
        this.servicelineitem = value;
    }

    /**
     * Gets the value of the pono property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPONO() {
        return pono;
    }

    /**
     * Sets the value of the pono property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPONO(String value) {
        this.pono = value;
    }

    /**
     * Gets the value of the workdone property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWORKDONE() {
        return workdone;
    }

    /**
     * Sets the value of the workdone property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWORKDONE(BigDecimal value) {
        this.workdone = value;
    }

    /**
     * Gets the value of the uom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUOM() {
        return uom;
    }

    /**
     * Sets the value of the uom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUOM(String value) {
        this.uom = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPRICE() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPRICE(BigDecimal value) {
        this.price = value;
    }

	public String getGlno() {
		return glno;
	}

	public void setGlno(String glno) {
		this.glno = glno;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}



	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}



	public String getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getPostingdate() {
		return postingdate;
	}

	public void setPostingdate(String postingdate) {
		this.postingdate = postingdate;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public List<YSERVICECOSTCENTER> getLinecostcenter() {
		return linecostcenter;
	}

	public void setLinecostcenter(List<YSERVICECOSTCENTER> linecostcenter) {
		this.linecostcenter = linecostcenter;
	}

	public String getSsnCreationDate() {
		return ssnCreationDate;
	}

	public void setSsnCreationDate(String ssnCreationDate) {
		this.ssnCreationDate = ssnCreationDate;
	}

	public String getSsnCreationEmpCode() {
		return ssnCreationEmpCode;
	}

	public void setSsnCreationEmpCode(String ssnCreationEmpCode) {
		this.ssnCreationEmpCode = ssnCreationEmpCode;
	}

	public String getSsnApprovalEmpCode() {
		return ssnApprovalEmpCode;
	}

	public void setSsnApprovalEmpCode(String ssnApprovalEmpCode) {
		this.ssnApprovalEmpCode = ssnApprovalEmpCode;
	}

	public String getServiceLocation() {
		return serviceLocation;
	}

	public String getServiceFromDate() {
		return serviceFromDate;
	}

	public String getServiceToDate() {
		return serviceToDate;
	}

	public void setServiceLocation(String serviceLocation) {
		this.serviceLocation = serviceLocation;
	}

	public void setServiceFromDate(String serviceFromDate) {
		this.serviceFromDate = serviceFromDate;
	}

	public void setServiceToDate(String serviceToDate) {
		this.serviceToDate = serviceToDate;
	}

	public String getSsnNo() {
		return ssnNo;
	}

	public void setSsnNo(String ssnNo) {
		this.ssnNo = ssnNo;
	}



	


}
