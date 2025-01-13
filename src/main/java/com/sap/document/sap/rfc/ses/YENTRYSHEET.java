
package com.sap.document.sap.rfc.ses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for YENTRYSHEET complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="YENTRYSHEET">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PONO" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="POITEM" type="{urn:sap-com:document:sap:rfc:functions}numeric5"/>
 *         &lt;element name="SERVICESHEETNO" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "YENTRYSHEET", propOrder = {
    "pono",
    "poitem",
    "servicesheetno"
})
public class YENTRYSHEET {

    @XmlElement(name = "PONO", required = true)
    protected String pono;
    @XmlElement(name = "POITEM", required = true)
    protected String poitem;
    @XmlElement(name = "SERVICESHEETNO", required = true)
    protected String servicesheetno;

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
     * Gets the value of the poitem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPOITEM() {
        return poitem;
    }

    /**
     * Sets the value of the poitem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPOITEM(String value) {
        this.poitem = value;
    }

    /**
     * Gets the value of the servicesheetno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSERVICESHEETNO() {
        return servicesheetno;
    }

    /**
     * Sets the value of the servicesheetno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSERVICESHEETNO(String value) {
        this.servicesheetno = value;
    }

}
